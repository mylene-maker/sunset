package com.humanbooster.sunset.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateFacture;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar datePayment;

    @Column(unique = true)
    private String libelle;

    @ManyToOne(fetch = FetchType.EAGER)
    private  User user;

    @OneToMany (mappedBy = "facture", fetch = FetchType.EAGER)
    private List<Reservation> lignes;

    public Facture() {
        this.lignes = new ArrayList<Reservation>();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GregorianCalendar getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(GregorianCalendar dateFacture) {
        this.dateFacture = dateFacture;
    }

    public GregorianCalendar getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(GregorianCalendar datePayment) {
        this.datePayment = datePayment;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Reservation> getLignes() {
        return lignes;
    }

    public void setLignes(List<Reservation> lignes) {
        this.lignes = lignes;
    }
}
