package com.twa.flights.api.catalog.architecture.general;

import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;

public class CommonArchRules {

    public static ArchRule interfacesAreOnlyAllowedRule(String packageName, String... excludedPackages) {
        return classes().that().resideInAPackage(packageName).and().resideOutsideOfPackages(excludedPackages).should()
                .beInterfaces().because("Resources should be interfaces in " + packageName);
    }

    public static ArchRule publicConstructorsRule(String packageName) {
        return constructors().that().areDeclaredInClassesThat().resideInAPackage(packageName).and()
                .areDeclaredInClassesThat().areNotAnonymousClasses().should().bePublic()
                .because("Public constructors are only allowed in " + packageName);
    }
}
