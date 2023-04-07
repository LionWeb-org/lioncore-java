package org.lionweb.lioncore.java.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.lionweb.lioncore.java.metamodel.*;
import org.lionweb.lioncore.java.model.impl.M3Node;

public class MetamodelValidator {

  final class ValidationResult {
    private final Set<Issue> issues = new HashSet<>();

    public Set<Issue> getIssues() {
      return issues;
    }

    public boolean isSuccessful() {
      return issues.stream().noneMatch(issue -> issue.getSeverity() == IssueSeverity.Error);
    }

    public ValidationResult addError(String message, Object subject) {
      issues.add(new Issue(IssueSeverity.Error, message, subject));
      return this;
    }

    public <S> ValidationResult checkForError(boolean check, String message, S subject) {
      if (check) {
        issues.add(new Issue(IssueSeverity.Error, message, subject));
      }
      return this;
    }
  }

  private void validateNamesAreUnique(
      List<? extends NamespacedEntity> elements, ValidationResult result) {
    Map<String, List<NamespacedEntity>> elementsByName =
        elements.stream()
            .filter(namespacedEntity -> namespacedEntity.getName() != null)
            .collect(Collectors.groupingBy((NamespacedEntity::getName)));
    elementsByName
        .entrySet()
        .forEach(
            (Map.Entry<String, List<NamespacedEntity>> entry) -> {
              if (entry.getValue().size() > 1) {
                entry
                    .getValue()
                    .forEach((NamespacedEntity el) -> result.addError("Duplicate name", el));
              }
            });
  }

  public boolean isMetamodelValid(Metamodel metamodel) {
    return validateMetamodel(metamodel).isSuccessful();
  }

  private void checkAncestors(Concept concept, ValidationResult validationResult) {
    checkAncestorsHelper(new HashSet<>(), concept, validationResult, true);
  }

  private void checkAncestors(
      ConceptInterface conceptInterface, ValidationResult validationResult) {
    checkAncestorsHelper(new HashSet<>(), conceptInterface, validationResult, false);
  }

  private void checkAncestorsHelper(
      Set<FeaturesContainer> alreadyExplored,
      Concept concept,
      ValidationResult validationResult,
      boolean examiningConcept) {
    if (alreadyExplored.contains(concept)) {
      validationResult.addError("Cyclic hierarchy found", concept);
    } else {
      alreadyExplored.add(concept);
      if (concept.getExtendedConcept() != null) {
        checkAncestorsHelper(
            alreadyExplored, concept.getExtendedConcept(), validationResult, examiningConcept);
      }
      concept
          .getImplemented()
          .forEach(
              interf ->
                  checkAncestorsHelper(
                      alreadyExplored, interf, validationResult, examiningConcept));
    }
  }

  private void checkAncestorsHelper(
      Set<FeaturesContainer> alreadyExplored,
      ConceptInterface conceptInterface,
      ValidationResult validationResult,
      boolean examiningConcept) {
    if (alreadyExplored.contains(conceptInterface)) {
      // It is ok to indirectly implement multiple time the same interface for a Concept.
      // It is instead an issue in case we are looking into interfaces.
      //
      // For example, this is fine:
      // class A extends B, implements I
      // class B implements I
      //
      // This is not fine:
      // interface I1 extends I2
      // interface I2 extends I1
      if (!examiningConcept) {
        validationResult.addError("Cyclic hierarchy found", conceptInterface);
      }
    } else {
      alreadyExplored.add(conceptInterface);
      conceptInterface
          .getExtendedInterfaces()
          .forEach(
              interf ->
                  checkAncestorsHelper(
                      alreadyExplored, interf, validationResult, examiningConcept));
    }
  }

  private void checkAncestorsHelperForConceptInterfaces(
      Set<ConceptInterface> alreadyExplored,
      ConceptInterface conceptInterface,
      ValidationResult validationResult) {
    if (alreadyExplored.contains(conceptInterface)) {
      validationResult.addError("Cyclic hierarchy found", conceptInterface);
    } else {
      alreadyExplored.add(conceptInterface);
      conceptInterface
          .getExtendedInterfaces()
          .forEach(
              interf ->
                  checkAncestorsHelperForConceptInterfaces(
                      alreadyExplored, interf, validationResult));
    }
  }

  public ValidationResult validateMetamodel(Metamodel metamodel) {
    ValidationResult result = new ValidationResult();

    result.checkForError(metamodel.getName() == null, "Qualified name not set", metamodel);

    validateNamesAreUnique(metamodel.getElements(), result);

    // TODO once we implement the Node interface we could navigate the tree differently

    metamodel
        .getElements()
        .forEach(
            (MetamodelElement el) -> {
              result
                  .checkForError(el.getName() == null, "Simple name not set", el)
                  .checkForError(el.getMetamodel() == null, "Metamodel not set", el)
                  .checkForError(
                      el.getMetamodel() != null && el.getMetamodel() != metamodel,
                      "Metamodel not set correctly",
                      el);

              if (el instanceof Enumeration) {
                Enumeration enumeration = (Enumeration) el;
                enumeration
                    .getLiterals()
                    .forEach(
                        (EnumerationLiteral lit) ->
                            result.checkForError(
                                lit.getName() == null, "Simple name not set", lit));
                validateNamesAreUnique(enumeration.getLiterals(), result);
              }
              if (el instanceof FeaturesContainer) {
                FeaturesContainer<M3Node> featuresContainer = (FeaturesContainer) el;
                featuresContainer
                    .getFeatures()
                    .forEach(
                        (Feature feature) ->
                            result
                                .checkForError(
                                    feature.getName() == null, "Simple name not set", feature)
                                .checkForError(
                                    feature.getContainer() == null, "Container not set", feature)
                                .checkForError(
                                    feature.getContainer() != null
                                        && feature.getContainer() != featuresContainer,
                                    "Features container not set correctly",
                                    feature));
                validateNamesAreUnique(featuresContainer.getFeatures(), result);
              }
              if (el instanceof Concept) {
                Concept concept = (Concept) el;
                checkAncestors(concept, result);
                result.checkForError(
                    concept.getImplemented().size()
                        != concept.getImplemented().stream().distinct().count(),
                    "The same interface has been implemented multiple times",
                    concept);
              }
              if (el instanceof ConceptInterface) {
                checkAncestors((ConceptInterface) el, result);
              }
            });

    return result;
  }
}
