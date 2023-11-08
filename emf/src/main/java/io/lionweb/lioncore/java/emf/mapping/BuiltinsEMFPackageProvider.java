package io.lionweb.lioncore.java.emf.mapping;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;

public class BuiltinsEMFPackageProvider {

  private static EPackage ePackage;

  public static EPackage getEPackage() {
    if (ePackage == null) {
      ePackage = prepareEPackage();
    }
    return ePackage;
  }

  private BuiltinsEMFPackageProvider() {}

  private static EPackage prepareEPackage() {
    EPackage p = EcoreFactory.eINSTANCE.createEPackage();
    p.setName("LionCoreBuiltins");
    p.setNsURI("http://lionweb.io/builtins");
    return p;
  }
}