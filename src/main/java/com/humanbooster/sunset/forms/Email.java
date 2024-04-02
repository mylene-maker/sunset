package com.humanbooster.sunset.forms;

import jakarta.validation.constraints.NotBlank;

public class Email {
    @jakarta.validation.constraints.Email(message = "l'email n'est pas valide")
    @NotBlank(message = "veuillez saisir un email")
    private String email;
    @NotBlank(message = "veuillez saisir un objet")
    private String object;
    @NotBlank(message = "veuillez saisir un contenu")
    private String contenu;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
