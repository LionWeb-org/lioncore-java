package io.lionweb.lioncore.java.model.impl;

import io.lionweb.lioncore.java.language.Annotation;
import io.lionweb.lioncore.java.language.Classifier;
import io.lionweb.lioncore.java.language.Containment;
import io.lionweb.lioncore.java.language.Reference;
import io.lionweb.lioncore.java.model.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractClassifierInstance<T extends Classifier<T>>
    implements ClassifierInstance<T> {
  protected final List<AnnotationInstance> annotations = new ArrayList<>();

  // Public methods for annotations

  @Override
  public List<AnnotationInstance> getAnnotations() {
    return Collections.unmodifiableList(annotations);
  }

  /**
   * Given a specific Annotation type it returns either the list of instances of that Annotation
   * associated to the Node.
   */
  @Nonnull
  public List<AnnotationInstance> getAnnotations(@Nonnull Annotation annotation) {
    return annotations.stream()
        .filter(a -> a.getAnnotationDefinition() == annotation)
        .collect(Collectors.toList());
  }

  /**
   * If an annotation instance was already associated under the Annotation link used by this
   * AnnotationInstance, and the annotation does not support multiple values, then the existing
   * instance will be removed and replaced by the instance specified in the call to this method.
   *
   * @throws IllegalArgumentException In case the specified Annotation link cannot be used on Nodes
   *     of this Concept.
   */
  public void addAnnotation(@Nonnull AnnotationInstance instance) {
    Objects.requireNonNull(instance);
    if (this.annotations.contains(instance)) {
      // necessary to avoid infinite loops and duplicate insertions
      return;
    }
    if (instance instanceof DynamicAnnotationInstance) {
      ((DynamicAnnotationInstance) instance).setAnnotated(this);
    }
    if (this.annotations.contains(instance)) {
      // necessary to avoid infinite loops and duplicate insertions
      // the previous setAnnotated could potentially have already set annotations
      return;
    }
    this.annotations.add(instance);
  }

  public void removeAnnotation(@Nonnull AnnotationInstance instance) {
    Objects.requireNonNull(instance);
    if (!this.annotations.remove(instance)) {
      throw new IllegalArgumentException();
    }
    if (instance instanceof DynamicAnnotationInstance) {
      ((DynamicAnnotationInstance) instance).setAnnotated(null);
    }
  }

  void tryToRemoveAnnotation(@Nonnull AnnotationInstance instance) {
    Objects.requireNonNull(instance);
    if (!this.annotations.remove(instance)) {
      return;
    }
    if (instance instanceof DynamicAnnotationInstance) {
      ((DynamicAnnotationInstance) instance).setAnnotated(null);
    }
  }

  // Public methods for containments

  @Override
  @Nonnull
  public List<Node> getChildren() {
    List<Node> allChildren = new LinkedList<>();
    getClassifier().allContainments().stream()
        .map(c -> getChildren(c))
        .forEach(children -> allChildren.addAll(children));
    return allChildren;
  }

  @Override
  public void removeChild(Node child) {
    for (Containment containment : this.getClassifier().allContainments()) {
      List<? extends Node> children = this.getChildren(containment);
      if (children.remove(child)) {
        if (child instanceof HasSettableParent) {
          ((HasSettableParent) child).setParent(null);
        }
        return;
      }
    }
  }

  @Override
  public void removeChild(@Nonnull Containment containment, int index) {
    if (!getClassifier().allContainments().contains(containment)) {
      throw new IllegalArgumentException("Containment not belonging to this concept");
    }
    List<? extends Node> children = this.getChildren(containment);
    if (children.size() > index) {
      children.remove(index);
    } else {
      throw new IllegalArgumentException(
          "Invalid index " + index + " when children are " + children.size());
    }
  }

  // Public methods for references

  @Nonnull
  @Override
  public List<ReferenceValue> getReferenceValues() {
    List<ReferenceValue> allReferredValues = new LinkedList<>();
    getClassifier().allReferences().stream()
        .map(r -> getReferenceValues(r))
        .forEach(referenceValues -> allReferredValues.addAll(referenceValues));
    return allReferredValues;
  }

  @Nonnull
  @Override
  public List<Node> getReferredNodes() {
    return getReferenceValues().stream()
        .map(rv -> rv.getReferred())
        .filter(n -> n != null)
        .collect(Collectors.toList());
  }

  @Override
  public void setOnlyReferenceValueByName(String referenceName, @Nullable ReferenceValue value) {}

  @Override
  public void setReferenceValuesByName(
      String referenceName, @Nonnull List<? extends ReferenceValue> values) {}

  @Override
  public void removeReferenceValue(@Nonnull Reference reference, int index) {
    if (!getClassifier().allReferences().contains(reference)) {
      throw new IllegalArgumentException("Reference not belonging to this concept");
    }
    getReferenceValues(reference).remove(index);
  }

  @Override
  public void removeReferenceValue(
      @Nonnull Reference reference, @Nullable ReferenceValue referenceValue) {
    if (!getClassifier().allReferences().contains(reference)) {
      throw new IllegalArgumentException("Reference not belonging to this concept");
    }
    if (!getReferenceValues(reference).remove(referenceValue)) {
      throw new IllegalArgumentException(
          "The given reference value could not be found under reference " + reference.getName());
    }
  }
}
