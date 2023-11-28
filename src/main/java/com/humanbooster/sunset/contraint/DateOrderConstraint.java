package com.humanbooster.sunset.contraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateOrderConstraintValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateOrderConstraint {

    // Je retrouve le message par défaut
    String message() default "La date de début doit être avant la date de fin !";

    // Appliquer par défaut sur tout les groupes
    Class<?>[] groups() default {};

    // Contenu de ma requête
    Class<? extends Payload>[] payload() default {};

    // Field qui correspond à ma date de fin
    String fieldEnd();

    // Field start qui correspond à ma date de début
    String fieldStart();

}
