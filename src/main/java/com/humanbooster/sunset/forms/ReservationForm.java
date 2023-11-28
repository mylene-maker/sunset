package com.humanbooster.sunset.forms;


import com.humanbooster.sunset.contraint.DateOrderConstraint;
import com.humanbooster.sunset.contraint.OpeningConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DateOrderConstraint(
        fieldStart = "dateDebut",
        fieldEnd = "dateFin"
)
public class ReservationForm {

    @DateTimeFormat(pattern = "yyy-MM-dd")
    @NotNull
    @Future(message = "Impossible de reserver sur une date du passé")
    @OpeningConstraint(message = "La date de début n'est pas dans nos jours d'ouverture")
    private Date dateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Future(message = "Cette date est déjà passée ...")
    @OpeningConstraint()
    private Date dateEnd;

    private String remarque;

    @Valid
    private List<EmplacementForm> emplacements;


    public ReservationForm() {
        this.emplacements = new ArrayList<>();
        this.emplacements.add(new EmplacementForm());
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public List<EmplacementForm> getEmplacements() {
        return emplacements;
    }

    public void setEmplacements(List<EmplacementForm> emplacements) {
        this.emplacements = emplacements;
    }
    public void addEmplacement(EmplacementForm emplacementForm){
        this.emplacements.add(emplacementForm);
    }
    public void removeEmplacement(EmplacementForm emplacementForm){
        this.emplacements.remove(emplacementForm);
    }
}
