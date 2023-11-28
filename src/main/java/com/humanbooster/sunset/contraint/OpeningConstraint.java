package com.humanbooster.sunset.contraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OpeningConstraintValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpeningConstraint {
    // Nous retrouvons ici notre messaage par défaut
    String message() default "Nous sommes fermés à cette date ...";
    // On retrouve les groupes sur lesquels la contrainte est appliquée
    Class<?>[] groups() default {};
    // On retrouve l'élément sur lequel notre contrainte est appliquée (le champ date dans notre requête)
    Class<? extends Payload>[] payload() default {};

}
