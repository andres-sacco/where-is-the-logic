package com.twa.flights.architecture.rules.resource;

import com.societegenerale.commons.plugin.rules.ArchRuleTest;
import com.societegenerale.commons.plugin.service.ScopePathProvider;
import com.societegenerale.commons.plugin.utils.ArchUtils;
import com.twa.flights.architecture.general.ArchitectureConstants;

import java.util.Collection;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.twa.flights.architecture.general.ArchitectureConstants.*;

public class ResourceShouldBeNameProperlyRuleTest implements ArchRuleTest {

    @Override
    public void execute(String packagePath, ScopePathProvider scopePathProvider, Collection<String> excludedPaths) {
        classes().that().resideInAPackage(RESOURCE_PACKAGE).should().haveSimpleNameEndingWith(RESOURCE_SUFFIX)
                .because(namingExplanation(ArchitectureConstants.RESOURCE_PACKAGE, RESOURCE_SUFFIX))
                .check(ArchUtils.importAllClassesInPackage(scopePathProvider.getMainClassesPath(), packagePath));
    }
}
