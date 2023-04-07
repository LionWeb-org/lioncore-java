package org.lionweb.lioncore.java.metamodel;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleLanguageMetamodelTest {

  @Test
  public void emptyMetamodelDefinition() {
    Metamodel metamodel = new Metamodel("SimpleLanguage").setID("myM3ID");
    assertEquals("SimpleLanguage", metamodel.getName());
    assertEquals("SimpleLanguage", metamodel.namespaceQualifier());
    assertEquals(0, metamodel.dependsOn().size());
    assertEquals(0, metamodel.getElements().size());
  }

  @Test
  public void emptyConceptDefinition() {
    Metamodel metamodel = new Metamodel("SimpleLanguage").setID("myM3ID");
    Concept expression = new Concept(metamodel, "Expression");
    assertEquals("Expression", expression.getName());
    assertSame(metamodel, expression.getContainer());
    assertSame(metamodel, expression.getMetamodel());
    assertEquals("SimpleLanguage.Expression", expression.qualifiedName());
    assertEquals("SimpleLanguage.Expression", expression.namespaceQualifier());
    assertNull(expression.getExtendedConcept());
    assertEquals(0, expression.getImplemented().size());
    assertEquals(0, expression.getFeatures().size());
    assertFalse(expression.isAbstract());
  }

  @Test
  public void emptyConceptInterfaceDefinition() {
    Metamodel metamodel = new Metamodel("SimpleLanguage").setID("myM3ID");
    ConceptInterface deprecated = new ConceptInterface(metamodel, "Deprecated");
    assertEquals("Deprecated", deprecated.getName());
    assertSame(metamodel, deprecated.getContainer());
    assertSame(metamodel, deprecated.getMetamodel());
    assertEquals("SimpleLanguage.Deprecated", deprecated.qualifiedName());
    assertEquals("SimpleLanguage.Deprecated", deprecated.namespaceQualifier());
    assertEquals(0, deprecated.getExtendedInterfaces().size());
    assertEquals(0, deprecated.getFeatures().size());
  }
}
