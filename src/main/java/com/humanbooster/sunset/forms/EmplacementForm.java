package com.humanbooster.sunset.forms;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class EmplacementForm {
    @Min(value = 1, message = "la file 1 est la plus proche de la mer")
    @Max(value = 8, message = "la file 8 est la plus éloigné de la mer")
    private Integer file;

    private String equipment;

    public Integer getFile() {
        return file;
    }

    public void setFile(Integer file) {
        this.file = file;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
