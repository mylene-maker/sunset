package com.humanbooster.sunset.contraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumConstraintValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumConstraint {

    // Nous retrouvons ici notre messaage par défaut
    String message() default "Ce type d'équipement n'est pas disponible ...";
    // On retrouve les groupes sur lesquels la contrainte est appliquée
    Class<?>[] groups() default {};
    // On retrouve l'élément sur lequel notre contrainte est appliquée (le champ date dans notre requête)
    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> targetClassType();

}
