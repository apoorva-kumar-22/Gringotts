package com.example.Gringotts.Entitiies;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Accio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Long vaultId;
    private BigDecimal galleons;
    private LocalDate date;
    private String type; // Expenditure or Deposit
    private String description;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getVaultId() {
        return vaultId;
    }

    public void setVaultId(Long vaultId) {
        this.vaultId = vaultId;
    }

    public BigDecimal getGalleons() {
        return galleons;
    }

    public void setAmount(BigDecimal amount) {
        this.galleons = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Accio{" +
                "transactionId=" + transactionId +
                ", vaultId=" + vaultId +
                ", amount=" + galleons +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
