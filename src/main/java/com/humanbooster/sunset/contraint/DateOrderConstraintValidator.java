package com.humanbooster.sunset.contraint;

import com.humanbooster.sunset.forms.ReservationForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateOrderConstraintValidator implements ConstraintValidator<DateOrderConstraint, Object> {

    @Override
    public void initialize(DateOrderConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // Méthode isValid qui retournera un booléen
    // true si dateDébut <= date de fin
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        // Je réccupére mon objet puis je le cast en objet ReservationForm
        ReservationForm reservationForm = (ReservationForm) o;
        // Si les 2 dates ne sont pas nulles
        if(reservationForm.getDateEnd() != null && reservationForm.getDateEnd() != null){
            // Je retourne true si la date de début est avant ou égale à la date de fin
            // J'utilise la méthode getTime de mon objet date qui retourne le timestamp
            return reservationForm.getDateStart().getTime()
                    <= reservationForm.getDateEnd().getTime();
        } else {
            return true;
        }

    }

}
