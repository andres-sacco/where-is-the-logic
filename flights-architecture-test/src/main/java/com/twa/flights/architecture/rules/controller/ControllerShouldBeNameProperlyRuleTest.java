package com.twa.flights.architecture.rules.controller;

import com.societegenerale.commons.plugin.rules.ArchRuleTest;
import com.societegenerale.commons.plugin.service.ScopePathProvider;
import com.societegenerale.commons.plugin.utils.ArchUtils;
import com.twa.flights.architecture.general.ArchitectureConstants;

import java.util.Collection;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.twa.flights.architecture.general.ArchitectureConstants.*;

public class ControllerShouldBeNameProperlyRuleTest implements ArchRuleTest {

    @Override
    public void execute(String packagePath, ScopePathProvider scopePathProvider, Collection<String> excludedPaths) {
        classes().that().resideInAPackage(CONTROLLER_PACKAGE).and().resideOutsideOfPackage(RESOURCE_PACKAGE).should()
                .haveSimpleNameEndingWith(CONTROLLER_SUFFIX)
                .because(namingExplanation(ArchitectureConstants.CONTROLLER_PACKAGE, CONTROLLER_SUFFIX))
                .check(ArchUtils.importAllClassesInPackage(scopePathProvider.getMainClassesPath(), packagePath));

    }
}
