package com.humanbooster.sunset.contraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumConstraintValidator implements ConstraintValidator<EnumConstraint, Object> {

    private Set< String > allowedValues;

    @Override
    public void initialize(EnumConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        // On réccupére notre enum
        Class < ? extends Enum > enumSelected = constraintAnnotation.targetClassType();

        // On les stocke en Set de string dan un atteribut
        allowedValues = (Set < String > ) EnumSet.allOf(enumSelected).stream().map(e -> ((Enum < ? extends Enum < ? >> ) e).name())
                .collect(Collectors.toSet());
    }


    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext){
        // SSi notre set contient la chaine de caractère on retourne true sinon false
        if(!allowedValues.contains((String) o)){
            return false;
        } else {
            return true;
        }
    }

}
