package com.twa.flights.architecture.rules.dto;

import com.societegenerale.commons.plugin.rules.ArchRuleTest;
import com.societegenerale.commons.plugin.service.ScopePathProvider;
import com.societegenerale.commons.plugin.utils.ArchUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.twa.flights.architecture.condition.AccessFieldsCondition.fieldsShouldHaveSetterAndGetterRule;
import static com.twa.flights.architecture.general.ArchitectureConstants.DTO_PACKAGE;

public class DTOShouldHaveSetAndGetRuleTest implements ArchRuleTest {

    @Override
    public void execute(String packagePath, ScopePathProvider scopePathProvider, Collection<String> excludedPaths) {
        fieldsShouldHaveSetterAndGetterRule(getExclusionFieldsWithSettersAndGetters(), DTO_PACKAGE)
                .check(ArchUtils.importAllClassesInPackage(scopePathProvider.getMainClassesPath(), packagePath));
    }

    private static Map<String, String> getExclusionFieldsWithSettersAndGetters() {
        String classExclusion = "com.twa.flights.api.reservation.dto.ErrorDTO";

        Map<String, String> exclusions = new HashMap<>();
        exclusions.put("reasons", classExclusion);
        exclusions.put("description", classExclusion);
        return exclusions;
    }
}
