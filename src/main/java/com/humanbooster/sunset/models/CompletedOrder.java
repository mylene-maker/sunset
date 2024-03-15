package com.humanbooster.sunset.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CompletedOrder implements Serializable {
    @Id
    private String payId;

    @Basic
    private String status;

    @OneToOne
    @JoinColumn(name = "command_id")
    Command command;

    public CompletedOrder() {
    }

    public CompletedOrder(String payId, String status) {
        this.payId = payId;
        this.status = status;
    }

    public CompletedOrder(String status) {
        this.status = status;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
