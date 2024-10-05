package com.twa.flights.api.catalog.architecture.layers;

import static com.twa.flights.api.catalog.architecture.general.ArchitectureConstants.DEFAULT_PACKAGE;
import static com.twa.flights.api.catalog.architecture.general.ArchitectureConstants.RESOURCES_PACKAGE;
import static com.twa.flights.api.catalog.architecture.general.CommonArchRules.interfacesAreOnlyAllowedRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@AnalyzeClasses(packages = DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class ResourcesRulesTest {

    @ArchTest
    static final ArchRule resourcesShouldBeInterfaces = interfacesAreOnlyAllowedRule(RESOURCES_PACKAGE);

    @ArchTest
    static final ArchRule resourcesMethodsShouldBeAnnotated = methods().that().areDeclaredInClassesThat()
            .resideInAPackage(RESOURCES_PACKAGE).should().beAnnotatedWith(Operation.class).orShould()
            .beAnnotatedWith(Parameter.class).orShould().beAnnotatedWith(ApiResponse.class)
            .because("Resources methods should be annotated by Swagger annotations like @Operation, @Parameter, and @ApiResponse");
}
