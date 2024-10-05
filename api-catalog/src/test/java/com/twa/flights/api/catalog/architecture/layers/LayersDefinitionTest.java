package com.twa.flights.api.catalog.architecture.layers;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.twa.flights.api.catalog.architecture.general.ArchitectureConstants.*;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
public class LayersDefinitionTest {

    @ArchTest
    static final ArchRule architectureStructure = layeredArchitecture().consideringAllDependencies()
            .layer(CONTROLLER_LAYER).definedBy(CONTROLLER_PACKAGE).layer(SERVICE_LAYER).definedBy(SERVICE_PACKAGE)
            .layer(REPOSITORY_LAYER).definedBy(REPOSITORY_PACKAGE)

            .whereLayer(CONTROLLER_LAYER).mayNotBeAccessedByAnyLayer().whereLayer(SERVICE_LAYER)
            .mayOnlyBeAccessedByLayers(CONTROLLER_LAYER, SERVICE_LAYER).whereLayer(REPOSITORY_LAYER)
            .mayOnlyBeAccessedByLayers(SERVICE_LAYER)
            .because("There is some rule related about the interaction that is not okay");
}
