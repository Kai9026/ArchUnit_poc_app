package com.github.kai9026.archunit.poc.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

public class ClassesTest {
    
    private JavaClasses javaClasses;

    @BeforeEach
    public void init() {
        this.javaClasses = new ClassFileImporter().importPackages("com.github.kai9026.archunit.poc");
    }

    @Test
    public void givenClasses_thenAreContainedInCorrectPackages() {
        ArchRule rule1 = classes()
                                .that().haveSimpleNameEndingWith("Controller")
                                .should().resideInAPackage("..controller");

        ArchRule rule2 = classes()
                                .that().haveSimpleNameEndingWith("Service")
                                .should().resideInAPackage("..service");

        ArchRule rule3 = classes()
                                .that().haveSimpleNameEndingWith("Repository")
                                .should().resideInAPackage("..repository");

        ArchRule rule4 = classes()
                                .that().resideInAPackage("..model.dto")
                                .and().areNotNestedClasses()
                                .should().haveSimpleNameEndingWith("DTO");

        rule1.check(this.javaClasses);
        rule2.check(this.javaClasses);
        rule3.check(this.javaClasses);
        rule4.check(this.javaClasses);
    }

    @Test
    public void givenModelDTOAndEntity_thenFieldsArePrivate() {
        this.javaClasses = new ClassFileImporter().importPackages("com.github.kai9026.archunit.poc.model.dto");
        ArchRule dtoRule = fields()
                                .should().bePrivate();
        dtoRule.check(this.javaClasses);
        
        this.javaClasses = new ClassFileImporter().importPackages("com.github.kai9026.archunit.poc.repository.entity");
        ArchRule entityRule = fields()
                                .should().bePrivate();

        entityRule.check(this.javaClasses);
    }

    @Test
    public void givenServiceAndRepository_thenMustBeInterfaces() {
        ArchRule rule1 = classes()
                                .that().resideInAPackage("..service")
                                .should().beInterfaces();

        ArchRule rule2 = classes()
                                .that().resideInAPackage("..repository")
                                .should().beInterfaces();

        rule1.check(this.javaClasses);
        rule2.check(this.javaClasses);
    }

    
}
