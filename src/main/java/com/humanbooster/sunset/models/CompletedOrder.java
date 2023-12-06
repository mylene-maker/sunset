package com.humanbooster.sunset.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CompletedOrder {
    @Id
    private String payId;

    @Basic
    private String status;

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
}
