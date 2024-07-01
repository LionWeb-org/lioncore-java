package io.lionweb.lioncore.java.self;

import io.lionweb.lioncore.java.language.*;
import io.lionweb.lioncore.java.model.impl.M3Node;
import io.lionweb.lioncore.java.serialization.JsonSerialization;
import java.util.Collections;
import java.util.List;

public class LionCore_2024_1 {

  private LionCore_2024_1() {
    // prevent instantiation of instances outside of this class
  }

  private static Language INSTANCE;

  public static Concept getAnnotation() {
    return getInstance().requireConceptByName("Annotation");
  }

  public static Concept getConcept() {
    return getInstance().requireConceptByName("Concept");
  }

  public static Concept getInterface() {
    return getInstance().requireConceptByName("Interface");
  }

  public static Concept getContainment() {
    return getInstance().requireConceptByName("Containment");
  }

  public static Concept getDataType() {
    return getInstance().requireConceptByName("DataType");
  }

  public static Concept getEnumeration() {
    return getInstance().requireConceptByName("Enumeration");
  }

  public static Concept getEnumerationLiteral() {
    return getInstance().requireConceptByName("EnumerationLiteral");
  }

  public static Concept getFeature() {
    return getInstance().requireConceptByName("Feature");
  }

  public static Concept getClassifier() {
    return getInstance().requireConceptByName("Classifier");
  }

  public static Concept getLink() {
    return getInstance().requireConceptByName("Link");
  }

  public static Concept getLanguage() {
    return getInstance().requireConceptByName("Language");
  }

  public static Concept getLanguageEntity() {
    return getInstance().requireConceptByName("LanguageEntity");
  }

  public static Concept getPrimitiveType() {
    return getInstance().requireConceptByName("PrimitiveType");
  }

  public static Concept getProperty() {
    return getInstance().requireConceptByName("Property");
  }

  public static Concept getReference() {
    return getInstance().requireConceptByName("Reference");
  }

  public static Language getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new Language("LionCore_M3");
      INSTANCE.setVersion(JsonSerialization.SERIALIZATION_FORMAT_2024_1);
      INSTANCE.setID("-id-LionCore-M3-" + INSTANCE.getVersion().replaceAll("\\.", "-"));
      INSTANCE.setKey("LionCore-M3");

      // We first instantiate all Concepts and Interfaces
      // we add features only after as the features will have references to these elements
      LionCore.Version lionCoreVersion = LionCore.Version.v2024_1;
      Concept annotation = INSTANCE.addElement(new Concept("Annotation", lionCoreVersion));
      Concept concept = INSTANCE.addElement(new Concept("Concept", lionCoreVersion));
      Concept iface = INSTANCE.addElement(new Concept("Interface", lionCoreVersion));
      Concept containment = INSTANCE.addElement(new Concept("Containment", lionCoreVersion));
      Concept dataType = INSTANCE.addElement(new Concept("DataType", lionCoreVersion));
      Concept enumeration = INSTANCE.addElement(new Concept("Enumeration", lionCoreVersion));
      Concept enumerationLiteral =
          INSTANCE.addElement(new Concept("EnumerationLiteral", lionCoreVersion));
      Concept feature = INSTANCE.addElement(new Concept("Feature", lionCoreVersion));
      Concept classifier = INSTANCE.addElement(new Concept("Classifier", lionCoreVersion));
      Concept link = INSTANCE.addElement(new Concept("Link", lionCoreVersion));
      Concept language = INSTANCE.addElement(new Concept("Language", lionCoreVersion));
      Concept languageEntity = INSTANCE.addElement(new Concept("LanguageEntity", lionCoreVersion));
      Interface iKeyed = INSTANCE.addElement(new Interface("IKeyed", lionCoreVersion));
      Concept primitiveType = INSTANCE.addElement(new Concept("PrimitiveType", lionCoreVersion));
      Concept property = INSTANCE.addElement(new Concept("Property", lionCoreVersion));
      Concept reference = INSTANCE.addElement(new Concept("Reference", lionCoreVersion));

      // Now we start adding the features to all the Concepts and Interfaces

      concept.setExtendedConcept(classifier);
      concept.addFeature(
          Property.createRequired(
              "abstract",
              LionCoreBuiltins_2024_1.getBoolean(),
              "-id-Concept-abstract",
              lionCoreVersion));
      concept.addFeature(
          Property.createRequired(
              "partition",
              LionCoreBuiltins_2024_1.getBoolean(),
              "-id-Concept-partition",
              lionCoreVersion));
      concept.addFeature(
          Reference.createOptional("extends", concept, "-id-Concept-extends", lionCoreVersion));
      concept.addFeature(
          Reference.createMultiple("implements", iface, "-id-Concept-implements", lionCoreVersion));

      iface.setExtendedConcept(classifier);
      iface.addFeature(
          Reference.createMultiple("extends", iface, "-id-Interface-extends", lionCoreVersion));

      containment.setExtendedConcept(link);

      dataType.setExtendedConcept(languageEntity);
      dataType.setAbstract(true);

      enumeration.setExtendedConcept(dataType);
      enumeration.addFeature(
          Containment.createMultiple("literals", enumerationLiteral, lionCoreVersion)
              .setID("-id-Enumeration-literals"));

      enumerationLiteral.addImplementedInterface(iKeyed);

      feature.setAbstract(true);
      feature.addImplementedInterface(iKeyed);
      feature.addFeature(
          Property.createRequired(
              "optional",
              LionCoreBuiltins_2024_1.getBoolean(),
              "-id-Feature-optional",
              lionCoreVersion));

      classifier.setAbstract(true);
      classifier.setExtendedConcept(languageEntity);
      classifier.addFeature(
          Containment.createMultiple(
              "features", feature, "-id-Classifier-features", lionCoreVersion));

      link.setAbstract(true);
      link.setExtendedConcept(feature);
      link.addFeature(
          Property.createRequired(
              "multiple",
              LionCoreBuiltins_2024_1.getBoolean(),
              "-id-Link-multiple",
              lionCoreVersion));
      link.addFeature(
          Reference.createRequired("type", classifier, "-id-Link-type", lionCoreVersion));

      language.setPartition(true);
      language.addImplementedInterface(iKeyed);
      language.addFeature(
          Property.createRequired(
              "version",
              LionCoreBuiltins_2024_1.getString(),
              "-id-Language-version",
              lionCoreVersion));
      language.addFeature(
          Reference.createMultiple("dependsOn", language, lionCoreVersion)
              .setID("-id-Language-dependsOn"));
      language.addFeature(
          Containment.createMultiple(
                  "entities", languageEntity, "-id-Language-entities", lionCoreVersion)
              .setKey("Language-entities"));

      languageEntity.setAbstract(true);
      languageEntity.addImplementedInterface(iKeyed);

      primitiveType.setExtendedConcept(dataType);

      property.setExtendedConcept(feature);
      property.addFeature(
          Reference.createRequired("type", dataType, "-id-Property-type", lionCoreVersion)
              .setKey("Property-type"));

      reference.setExtendedConcept(link);

      iKeyed.addExtendedInterface(LionCoreBuiltins_2024_1.getINamed());
      iKeyed.addFeature(
          Property.createRequired("key", LionCoreBuiltins_2024_1.getString(), lionCoreVersion)
              .setID("-id-IKeyed-key"));

      annotation.setExtendedConcept(classifier);
      annotation.addFeature(
          Reference.createOptional(
              "annotates", classifier, "-id-Annotation-annotates", lionCoreVersion));
      annotation.addFeature(
          Reference.createOptional(
              "extends", annotation, "-id-Annotation-extends", lionCoreVersion));
      annotation.addFeature(
          Reference.createMultiple(
              "implements", iface, "-id-Annotation-implements", lionCoreVersion));

      checkIDs(INSTANCE, INSTANCE.getVersion());
    }
    checkIDs(INSTANCE, INSTANCE.getVersion());
    return INSTANCE;
  }

  private static void checkIDs(M3Node node, String version) {
    if (node.getID() == null) {
      if (node instanceof NamespacedEntity) {
        NamespacedEntity namespacedEntity = (NamespacedEntity) node;
        node.setID(
            "-id-"
                + namespacedEntity.getName().replaceAll("\\.", "_")
                + version.replaceAll("\\.", "-"));
        if (node instanceof IKeyed<?> && ((IKeyed<?>) node).getKey() == null) {
          ((IKeyed<?>) node).setKey(namespacedEntity.getName());
        }
      } else {
        throw new IllegalStateException(node.toString());
      }
    }
    if (node instanceof Classifier<?>) {
      Classifier<?> classifier = (Classifier<?>) node;
      classifier
          .getFeatures()
          .forEach(
              feature -> {
                if (feature.getKey() == null) {
                  feature.setKey(classifier.getName() + "-" + feature.getName());
                }
              });
    }

    // TODO To be changed once getChildren is implemented correctly
    getChildrenHelper(node).forEach(c -> checkIDs(c, version));
  }

  private static List<? extends M3Node> getChildrenHelper(M3Node node) {
    if (node instanceof Language) {
      return ((Language) node).getElements();
    } else if (node instanceof Classifier) {
      return ((Classifier) node).getFeatures();
    } else if (node instanceof Feature) {
      return Collections.emptyList();
    } else {
      throw new UnsupportedOperationException("Unsupported " + node);
    }
  }
}
