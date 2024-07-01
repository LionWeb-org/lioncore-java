package io.lionweb.lioncore.java.self;

import io.lionweb.lioncore.java.language.*;
import java.util.*;

public class LionCore {

  public enum Version {
    v2023_1("2023.1"),
    v2024_1("2024.1");

    public static Version CURRENT = v2024_1;
    private String value;

    private Version(String value) {
      this.value = value;
    }

    public String getValue() {
      return this.value;
    }
  }

  private LionCore() {
    // prevent instantiation of instances outside of this class
  }

  public static Language getCurrentVersion() {
    return getVersion2024_1();
  }

  public static List<Language> allVersions() {
    return Arrays.asList(getVersion2023_1(), getVersion2024_1());
  }

  public static Language getVersion2023_1() {
    return LionCore_2023_1.getInstance();
  }

  public static Language getVersion2024_1() {
    return LionCore_2024_1.getInstance();
  }

  public static Concept getAnnotation() {
    return getCurrentVersion().requireConceptByName("Annotation");
  }

  public static Concept getConcept() {
    return getCurrentVersion().requireConceptByName("Concept");
  }

  public static Concept getInterface() {
    return getCurrentVersion().requireConceptByName("Interface");
  }

  public static Concept getContainment() {
    return getCurrentVersion().requireConceptByName("Containment");
  }

  public static Concept getDataType() {
    return getCurrentVersion().requireConceptByName("DataType");
  }

  public static Concept getEnumeration() {
    return getCurrentVersion().requireConceptByName("Enumeration");
  }

  public static Concept getEnumerationLiteral() {
    return getCurrentVersion().requireConceptByName("EnumerationLiteral");
  }

  public static Concept getFeature() {
    return getCurrentVersion().requireConceptByName("Feature");
  }

  public static Concept getClassifier() {
    return getCurrentVersion().requireConceptByName("Classifier");
  }

  public static Concept getLink() {
    return getCurrentVersion().requireConceptByName("Link");
  }

  public static Concept getLanguage() {
    return getCurrentVersion().requireConceptByName("Language");
  }

  public static Concept getLanguageEntity() {
    return getCurrentVersion().requireConceptByName("LanguageEntity");
  }

  public static Concept getPrimitiveType() {
    return getCurrentVersion().requireConceptByName("PrimitiveType");
  }

  public static Concept getProperty() {
    return getCurrentVersion().requireConceptByName("Property");
  }

  public static Concept getReference() {
    return getCurrentVersion().requireConceptByName("Reference");
  }
}
