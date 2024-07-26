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
        layeredArchitecture().consideringAllDependencies().layer(CONTROLLER_SUFFIX).definedBy(CONTROLLER_PACKAGE)
                .whereLayer(CONTROLLER_SUFFIX).mayNotBeAccessedByAnyLayer()

                .layer(SERVICE_SUFFIX).definedBy(SERVICE_PACKAGE).whereLayer(SERVICE_SUFFIX)
                .mayOnlyBeAccessedByLayers(SERVICE_SUFFIX, CONTROLLER_SUFFIX)

                .check(ArchUtils.importAllClassesInPackage(scopePathProvider.getMainClassesPath(), packagePath));
    }
}
