package com.twa.flights.architecture.rules.general;

import com.societegenerale.commons.plugin.rules.ArchRuleTest;
import com.societegenerale.commons.plugin.service.ScopePathProvider;
import com.societegenerale.commons.plugin.utils.ArchUtils;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.twa.flights.architecture.general.ArchitectureConstants.*;

import java.util.Collection;

public class LayeredArchitectureRuleTest implements ArchRuleTest {

    @Override
    public void execute(String packagePath, ScopePathProvider scopePathProvider, Collection<String> excludedPaths) {
        layeredArchitecture().consideringAllDependencies().layer(CONTROLLER_LAYER).definedBy(CONTROLLER_PACKAGE)
                .layer(SERVICE_LAYER).definedBy(SERVICE_PACKAGE).layer(REPOSITORY_LAYER).definedBy(REPOSITORY_PACKAGE)

                .whereLayer(CONTROLLER_LAYER).mayNotBeAccessedByAnyLayer().whereLayer(SERVICE_LAYER)
                .mayOnlyBeAccessedByLayers(CONTROLLER_LAYER, SERVICE_LAYER).whereLayer(REPOSITORY_LAYER)
                .mayOnlyBeAccessedByLayers(SERVICE_LAYER)

                .check(ArchUtils.importAllClassesInPackage(scopePathProvider.getMainClassesPath(), packagePath));
    }
}
