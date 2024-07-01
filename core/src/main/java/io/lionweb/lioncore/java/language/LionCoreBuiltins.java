package io.lionweb.lioncore.java.language;

import java.util.Arrays;
import java.util.List;

public class LionCoreBuiltins {
  public static LionCoreBuiltins_2024_1 getCurrentVersion() {
    return getVersion2024_1();
  }

  public static List<Language> allVersions() {
    return Arrays.asList(getVersion2023_1(), getVersion2024_1());
  }

  public static LionCoreBuiltins_2023_1 getVersion2023_1() {
    return LionCoreBuiltins_2023_1.getInstance();
  }

  public static LionCoreBuiltins_2024_1 getVersion2024_1() {
    return LionCoreBuiltins_2024_1.getInstance();
  }

  public static PrimitiveType getString() {
    return getCurrentVersion().getPrimitiveTypeByName("String");
  }

  public static PrimitiveType getInteger() {
    return getCurrentVersion().getPrimitiveTypeByName("Integer");
  }

  public static PrimitiveType getBoolean() {
    return getCurrentVersion().getPrimitiveTypeByName("Boolean");
  }

  public static Interface getINamed() {
    return getCurrentVersion().getInterfaceByName("INamed");
  }

  public static Concept getNode() {
    return getCurrentVersion().getConceptByName("Node");
  }
}
