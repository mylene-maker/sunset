package com.humanbooster.sunset.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotNull(message = "Veuillez indiquer si la commande est payée")
    private Boolean payment = false;
    @Column(nullable = true)
    private String remarque;
    @OneToMany(mappedBy = "command")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    public Command(@NotNull(message = "Veuillez indiquer si la commande est payée") Boolean payment, String remarque, List<Reservation> reservations, User user) {
        this.payment = payment;
        this.remarque = remarque;
        this.reservations = reservations;
        this.user = user;
    }

    public Command(long id, @NotNull(message = "Veuillez indiquer si la commande est payée") Boolean payment, String remarque, List<Reservation> reservations, User user) {
        this.id = id;
        this.payment = payment;
        this.remarque = remarque;
        this.reservations = reservations;
        this.user = user;
    }

    public Command() {
        this.reservations = new ArrayList<Reservation>();
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User customer) {
        this.user = customer;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public User getCustomer() {
        return user;
    }

    public void setCustomer(User customer) {
        this.user = customer;
    }

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation){
        this.reservations.remove(reservation);
    }

    public List<Reservation> getReservations(){
        return this.reservations;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}