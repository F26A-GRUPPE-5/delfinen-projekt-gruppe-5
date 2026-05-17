package dk.delfinen.gruppe5.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HexagonalArchitectureTest {

    JavaClasses importedClasses;

    @BeforeAll
    void setup() {
        importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("dk.delfinen.gruppe5");
    }

    @Test
    void domain_must_only_depend_on_domain_and_java() {
        classes()
                .that().resideInAPackage("..domain..")
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage("..domain..", "java..")
                .check(importedClasses);
    }

    @Test
    void application_must_not_depend_on_adapters() {
        noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat()
                .resideInAPackage("..adapter..")
                .check(importedClasses);
    }

    @Test
    void adapters_must_not_depend_on_usecases() {
        noClasses()
                .that().resideInAPackage("..adapter..")
                .should().dependOnClassesThat()
                .resideInAPackage("..application.usecase..")
                .check(importedClasses);
    }

    @Test
    void adapters_should_only_depend_on_allowed_areas() {
        classes()
                .that().resideInAPackage("..adapter..")
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "..application.port..",
                        "..adapter..",
                        "java.."
                )
                .check(importedClasses);
    }
}