package com.github.kai9026.archunit.poc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class LayerTest {

    private JavaClasses javaClasses;

    @BeforeEach
    public void init() {
        this.javaClasses = new ClassFileImporter().importPackages("com.github.kai9026.archunit.poc");
    }

    @Test
    public void givenControllerLayer_thenDependsOnServiceLayer() {
        ArchRule controllerRule = classes()
                                        .that()
                                        .resideInAPackage("..controller..")
                                        .should().dependOnClassesThat()
                                        .resideInAPackage("..service..");
        
        controllerRule.check(this.javaClasses);
    }

    @Test
    public void givenServiceLayer_thenDependsOnRepositoryLayer() {
        ArchRule serviceRule = classes()
                                        .that()
                                        .resideInAPackage("..service.impl")
                                        .should().dependOnClassesThat()
                                        .resideInAPackage("..repository..")
                                        .andShould().dependOnClassesThat()
                                        .resideInAPackage("..mapper.impl");
        
        serviceRule.check(this.javaClasses);
    }


    @Test
    public void givenControllerLayer_thenNoDependsOnRepositoryLayer() {
        ArchRule noRepositoryRule = noClasses()
                                            .that()
                                            .resideInAPackage("..controller..")
                                            .should().dependOnClassesThat()
                                            .resideInAPackage("..repository..");

    
        noRepositoryRule.check(this.javaClasses);
    }

    @Test
    public void givenALayerArchitecture_thenNoLayerViolationShouldExist() {
        LayeredArchitecture architecture = layeredArchitecture()
                                                            .layer("controller").definedBy("..controller..")
                                                            .layer("service").definedBy("..service.impl")
                                                            .layer("persistence").definedBy("..repository..")
                                                            .layer("mapper").definedBy("..mapper.impl")
                                                            .whereLayer("controller").mayNotBeAccessedByAnyLayer()
                                                            .whereLayer("service").mayOnlyBeAccessedByLayers("controller")
                                                            .whereLayer("persistence").mayOnlyBeAccessedByLayers("service", "mapper");
        
                                                        
        architecture.check(this.javaClasses);

    }


    
}
