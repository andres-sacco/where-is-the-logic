package com.twa.flights.api.catalog.architecture.layers;

import static com.twa.flights.api.catalog.architecture.general.CommonArchRules.publicConstructorsRule;
import static com.twa.flights.api.catalog.architecture.general.ArchitectureConstants.CONTROLLER_PACKAGE;
import static com.twa.flights.api.catalog.architecture.general.ArchitectureConstants.CONTROLLER_SUFFIX;
import static com.twa.flights.api.catalog.architecture.general.ArchitectureConstants.DEFAULT_PACKAGE;
import static com.twa.flights.api.catalog.architecture.general.ArchitectureConstants.RESOURCES_PACKAGE;
import static com.twa.flights.api.catalog.architecture.general.ArchitectureConstants.ANNOTATED_EXPLANATION;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class ControllerRulesTest {

    @ArchTest
    static final ArchRule publicConstructorsAreOnlyAllowed = publicConstructorsRule(CONTROLLER_PACKAGE);

    @ArchTest
    static final ArchRule publicMethodsShouldAvoidUseRequestMapping = methods().that().areDeclaredInClassesThat()
            .resideInAPackage(CONTROLLER_PACKAGE).and().arePublic().should().notBeAnnotatedWith(RequestMapping.class)
            .because(
                    "Controller endpoints should not be annotated with @RequestMapping. Use @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, and @PatchMapping.");

    @ArchTest
    static final ArchRule publicMethodsShouldAvoidUseResponseBody = methods().that().areDeclaredInClassesThat()
            .resideInAPackage(CONTROLLER_PACKAGE).and().arePublic().should().notBeAnnotatedWith(ResponseBody.class)
            .because(
                    "Controller endpoints should not be annotated with @ResponseBody. Not include on the declaration the annotation.");

    @ArchTest
    static final ArchRule classesShouldBeAnnotated = classes().that().resideInAPackage(CONTROLLER_PACKAGE).and()
            .resideOutsideOfPackage(RESOURCES_PACKAGE).should().beAnnotatedWith(RestController.class).orShould()
            .beAnnotatedWith(Controller.class)
            .because(String.format(ANNOTATED_EXPLANATION, CONTROLLER_SUFFIX, "@RestController"));

}