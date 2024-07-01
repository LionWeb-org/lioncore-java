package io.lionweb.lioncore.java.language;

import static org.junit.Assert.assertEquals;

import io.lionweb.lioncore.java.utils.LanguageValidator;
import io.lionweb.lioncore.java.utils.ValidationResult;
import org.junit.Test;

public class LionCoreBuiltinsTest {

  @Test
  public void stringPrimitiveType() {
    PrimitiveType string =
        (PrimitiveType) LionCoreBuiltins.getCurrentVersion().getElementByName("String");
    assertEquals("String", string.getName());
    assertEquals("LionCore_builtins.String", string.qualifiedName());
  }

  @Test
  public void primitiveTypesHaveAgreedIDs() {
    assertEquals("LionCore-builtins-String", LionCoreBuiltins.getString().getID());
    assertEquals("LionCore-builtins-Boolean", LionCoreBuiltins.getBoolean().getID());
    assertEquals("LionCore-builtins-Integer", LionCoreBuiltins.getInteger().getID());
    assertEquals("LionCore-builtins-JSON", LionCoreBuiltins.getVersion2023_1().getJSON().getID());
  }

  @Test
  public void allVersionsOflionCoreBuiltinsIsValid() {
    for (Language builtins : LionCoreBuiltins.allVersions()) {
      ValidationResult vr = new LanguageValidator().validate(builtins);
      if (!vr.isSuccessful()) {
        throw new RuntimeException("LionCoreBuiltins Language is not valid: " + vr);
      }
    }
  }
}
