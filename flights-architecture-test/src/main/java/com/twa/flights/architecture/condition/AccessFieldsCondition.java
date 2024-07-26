package com.twa.flights.architecture.condition;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

public class AccessFieldsCondition {

    private static final String HAVE_GETTER_AND_SETTER_DESCRIPTION = "have getter and setter";
    private static final String GETTER_OR_SETTER_NOT_PRESENT_ERROR_MESSAGE = "Field %s of %s does not have %s";
    private static final String GETTER_PREFIX = "get";
    private static final String IS_PREFIX = "is";
    private static final String SETTER_PREFIX = "set";
    private static final String BOOLEAN_TYPE = "boolean";

    private AccessFieldsCondition() {

    }

    public static ArchRule fieldsShouldHaveSetterAndGetterRule(Map<String, String> exclusions, String... packageNames) {
        return fields().that().areDeclaredInClassesThat().resideInAnyPackage(packageNames).and()
                .areDeclaredInClassesThat().areNotMemberClasses().and().arePrivate().and().areNotFinal().and()
                .areNotStatic().should(buildFieldHaveGetterAndSetterCondition(exclusions))
                .because("Private fields should have setters and getters in " + Arrays.toString(packageNames));
    }

    private static ArchCondition<JavaField> buildFieldHaveGetterAndSetterCondition(Map<String, String> exclusions) {
        return new ArchCondition<JavaField>(HAVE_GETTER_AND_SETTER_DESCRIPTION) {

            @Override
            public void check(JavaField field, ConditionEvents events) {
                Set<String> publicMethods = field.getOwner().getMethods().stream()
                        .filter(m -> m.getModifiers().contains(JavaModifier.PUBLIC)).map(JavaMember::getName)
                        .collect(Collectors.toSet());

                String name = field.getName();

                if (exclusions.containsKey(name)) {
                    String className = exclusions.get(name);
                    if (field.getOwner().getName().equals(className)) {
                        return;
                    }
                }

                String getter = calculateGetterPrefix(field.reflect().getType().getName()) + capitalize(name);

                if (!publicMethods.contains(getter)) {
                    String message = String.format(GETTER_OR_SETTER_NOT_PRESENT_ERROR_MESSAGE, field.getName(),
                            field.getOwner().getName(), GETTER_PREFIX);
                    events.add(SimpleConditionEvent.violated(field, message));
                }

                String setter = SETTER_PREFIX + capitalize(name);

                if (!publicMethods.contains(setter)) {
                    String message = String.format(GETTER_OR_SETTER_NOT_PRESENT_ERROR_MESSAGE, field.getName(),
                            field.getOwner().getName(), SETTER_PREFIX);
                    events.add(SimpleConditionEvent.violated(field, message));
                }

            }
        };
    }

    private static String capitalize(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    private static String calculateGetterPrefix(String type) {
        return !type.equals(BOOLEAN_TYPE) ? GETTER_PREFIX : IS_PREFIX;
    }
}