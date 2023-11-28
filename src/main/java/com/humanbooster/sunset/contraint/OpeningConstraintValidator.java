package com.humanbooster.sunset.contraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OpeningConstraintValidator implements ConstraintValidator<OpeningConstraint, Object> {

    @Override
    public void initialize(OpeningConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    // La méthode isValid sera appelée
    // Si elle retourne true, la contrainte ne déclanche pas l'erreur
    // Si elle retourne false, la validation retiendra une erreur sur le champ en question
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        // Je réccupére la date saisie par l'utilisateur
        Date date = (Date) o;
        // Par défaut je dis que ma date n'est pas valide
        boolean retour = false;

        // Si ma date n'est pas nulle, je vérifier qu'elle est bien dans les dates d'ouverture
        if (date != null) {
            // Je cré un nouvel objet grégorian calendar à partir de ma date pour retrouver facilement le mois
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            // Je réccupére le mois de la date
            int month = calendar.get(Calendar.MONTH);

            // Si la réservation est en juin, c'est OK donc le retour sera à true
            if (month == Calendar.JULY) {
                retour = true;
            }

            // Si la réservation est en aout, c'est OK donc le retour sera à true
            if (month == Calendar.AUGUST) {
                retour = true;
            }

            // Si la réservation est en septembre et le jour du mois inférieur ou égal à 15,
            // c'est OK donc le retour sera à true
            if (month == Calendar.SEPTEMBER) {
                retour = calendar.get(Calendar.DAY_OF_MONTH) <= 15;
            }
        }

        return retour;
    }

}
