package com.twa.flights.api.catalog.architecture.general;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.slf4j.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = ArchitectureConstants.DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class GeneralCodingRulesTest {

    @ArchTest
    static final ArchRule loggersShouldBePrivateStaticAndFinal = fields().that().haveRawType(Logger.class).should()
            .bePrivate().andShould().beStatic().andShould().beFinal().andShould().haveName("LOGGER")
            .because("Logger variables should be private, static and final, and it should be named as LOGGER");

    @ArchTest
    static final ArchRule finalStaticVariablesInUppercase = fields().that().areStatic().and().areFinal().and()
            .doNotHaveName("serialVersionUID").and().doNotHaveModifier(JavaModifier.SYNTHETIC).should()
            .haveNameMatching(".*^[A-Z].*")
            .because("Variables with static and final modifiers should be named in uppercase");
}