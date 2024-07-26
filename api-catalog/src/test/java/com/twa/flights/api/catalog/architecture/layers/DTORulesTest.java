package com.twa.flights.api.catalog.architecture.layers;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.twa.flights.api.catalog.architecture.general.ArchitectureConstants;

import java.util.HashMap;
import java.util.Map;

import static com.twa.flights.api.catalog.architecture.rules.AccessFieldsRule.fieldsShouldHaveSetterAndGetterRule;

@AnalyzeClasses(packages = ArchitectureConstants.DEFAULT_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
public class DTORulesTest {

    @ArchTest
    static final ArchRule fieldsShouldHaveSetterAndGetterRule = fieldsShouldHaveSetterAndGetterRule(
            getExclusionFieldsWithSettersAndGetters(), ArchitectureConstants.DTO_PACKAGE);

    private static Map<String, String> getExclusionFieldsWithSettersAndGetters() {
        String classExclusion = "com.twa.flights.api.catalog.dto.ErrorDTO";

        Map<String, String> exclusions = new HashMap<>();
        exclusions.put("code", classExclusion);
        exclusions.put("shortDescription", classExclusion);
        exclusions.put("reasons", classExclusion);

        return exclusions;
    }
}
