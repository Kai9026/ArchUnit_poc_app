package com.github.kai9026.archunit.poc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import javax.persistence.Entity;


public class AnnotationsTest {

    private JavaClasses javaClasses;

    @BeforeEach
    public void init() {
        this.javaClasses = new ClassFileImporter().importPackages("com.github.kai9026.archunit.poc");
    }

    @Test
    public void givenControllers_thenClassesAreAnnotatedWithRestController() {
        ArchRule controllerRule = classes()
                                        .that()
                                        .resideInAPackage("..controller..")
                                        .should().beAnnotatedWith(RestController.class);
        
        controllerRule.check(this.javaClasses);
    }

    @Test
    public void givenServices_thenClassesAreAnnotatedWithService() {
        ArchRule serviceRule = classes()
                                    .that()
                                    .resideInAPackage("..service.impl")
                                    .should().beAnnotatedWith(Service.class);

        serviceRule.check(this.javaClasses);
    }

    @Test
    public void givenRepositories_thenClassesAreAnnotatedWithRepository() {
        ArchRule persistenceRule = classes()
                                    .that()
                                    .resideInAPackage("..repository")
                                    .should().beAnnotatedWith(Repository.class);

        persistenceRule.check(this.javaClasses);
    }

    @Test
    public void givenEntities_thenClassesAreAnnotatedWithEntity() {
        ArchRule persistenceRule = classes()
                                    .that()
                                    .resideInAPackage("..repository.entity").and()
                                    .areNotNestedClasses()
                                    .should().beAnnotatedWith(Entity.class);

        persistenceRule.check(this.javaClasses);
    }

    @Test
    public void givenMappers_thenClassesAreAnnotatedWithComponent() {
        ArchRule persistenceRule = classes()
                                    .that()
                                    .resideInAPackage("..mapper.impl")
                                    .should().beAnnotatedWith(Component.class);

        persistenceRule.check(this.javaClasses);
    }
    
}
