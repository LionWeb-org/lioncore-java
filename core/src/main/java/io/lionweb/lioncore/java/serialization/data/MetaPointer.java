package io.lionweb.lioncore.java.serialization.data;

import io.lionweb.lioncore.java.language.HasKey;
import io.lionweb.lioncore.java.language.Language;
import io.lionweb.lioncore.java.language.LanguageElement;
import java.util.Objects;

/**
 * A MetaPointer is the combination of the pair Metamodel and Version with a Key, which identify one
 * element within that metamodel.
 */
public class MetaPointer {
  private String key;
  private String version;
  private String language;

  public MetaPointer(String language, String version, String key) {
    this.key = key;
    this.version = version;
    this.language = language;
  }

  public MetaPointer() {}

  public static MetaPointer from(LanguageElement<?> languageElement) {
    MetaPointer metaPointer = new MetaPointer();
    metaPointer.setKey(languageElement.getKey());
    if (languageElement.getLanguage() != null) {
      metaPointer.setLanguage(languageElement.getLanguage().getKey());
      if (languageElement.getLanguage().getVersion() != null) {
        metaPointer.setVersion(languageElement.getLanguage().getVersion());
      }
    }
    return metaPointer;
  }

  public static MetaPointer from(HasKey<?> elementWithKey, Language language) {
    MetaPointer metaPointer = new MetaPointer();
    metaPointer.setKey(elementWithKey.getKey());
    if (language != null) {
      metaPointer.setLanguage(language.getKey());
      if (language.getVersion() != null) {
        metaPointer.setVersion(language.getVersion());
      }
    }
    return metaPointer;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getVersion() {
    return version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MetaPointer)) return false;
    MetaPointer that = (MetaPointer) o;
    return Objects.equals(key, that.key)
        && Objects.equals(version, that.version)
        && Objects.equals(language, that.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, version, language);
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "MetaPointer{"
        + "key='"
        + key
        + '\''
        + ", version='"
        + version
        + '\''
        + ", language='"
        + language
        + '\''
        + '}';
  }
}
