package com.test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchitectureTest {
    public static JavaClasses importedClasses = new ClassFileImporter().importPackages("com");

    @Test
    public void testArch(){
        ArchRule rule = classes().should().bePublic();
        rule.check(importedClasses);
    }

    @Test
    public void testArchRules(){
        ArchRule layerDependencies = layeredArchitecture().consideringAllDependencies()
                .layer("Model").definedBy("..model..")
                .layer("Services").definedBy("..service..")
                .layer("Controllers").definedBy("..controller..")
                .layer("DAO").definedBy("..dao..")
                .layer("Interfaces").definedBy("..interfaces..")
                .layer("Util").definedBy("..util..")
                .layer("Test").definedBy("..test..")
                .whereLayer("DAO").mayOnlyBeAccessedByLayers("Services")
                .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers");
        layerDependencies.check(importedClasses);
    }

}
