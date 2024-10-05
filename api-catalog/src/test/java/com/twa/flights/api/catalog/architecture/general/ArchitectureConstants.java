package com.twa.flights.api.catalog.architecture.general;

public class ArchitectureConstants {

    // Layers
    public static final String CONTROLLER_LAYER = "Controller";
    public static final String RESOURCES_LAYER = "Resources";
    public static final String DTO_LAYER = "DTO";
    public static final String SERVICE_LAYER = "Service";
    public static final String REPOSITORY_LAYER = "Repository";

    // Suffixes
    public static final String CONTROLLER_SUFFIX = "Controller";
    public static final String RESOURCES_SUFFIX = "Resources";
    public static final String DTO_SUFFIX = "DTO";
    public static final String SERVICE_SUFFIX = "Service";
    public static final String REPOSITORY_SUFFIX = "Repository";

    // Packages
    public static final String CONTROLLER_PACKAGE = "..controller..";
    public static final String RESOURCES_PACKAGE = "..controller.documentation..";
    public static final String DTO_PACKAGE = "..dto..";
    public static final String SERVICE_PACKAGE = "..service..";
    public static final String REPOSITORY_PACKAGE = "..repository..";

    // Explanations
    public static final String ANNOTATED_EXPLANATION = "Classes in %s package should be annotated with %s";
    public static final String NAMING_EXPLANATION = "Classes in %s package should be named with %s suffix";

    // Package to scan
    public static final String DEFAULT_PACKAGE = "com.twa.flights.api";

    private ArchitectureConstants() {

    }

    public static String namingExplanation(String packageName, String suffix) {
        return String.format(NAMING_EXPLANATION, packageName, suffix);
    }
}
