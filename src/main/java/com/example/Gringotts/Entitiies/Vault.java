package com.example.Gringotts.Entitiies;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Vault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique ID
    private Long vaultId;
    private String ownerName;
    private BigDecimal galleons; // balance stored in the vault
    private String goblinAccountant;

    public Long getVaultId() {
        return vaultId;
    }

    public void setVaultId(Long vaultId) {
        this.vaultId = vaultId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getGalleons() {
        return galleons;
    }

    public void setGalleons(BigDecimal galleons) {
        this.galleons = galleons;
    }

    public String getGoblinAccountant() {
        return goblinAccountant;
    }

    public void setGoblinAccountant(String goblinAccountant) {
        this.goblinAccountant = goblinAccountant;
    }

    @Override
    public String toString() {
        return "Vault{" +
                "vaultId=" + vaultId +
                ", ownerName='" + ownerName + '\'' +
                ", galleons=" + galleons +
                ", goblinAccountant='" + goblinAccountant + '\'' +
                '}';
    }

    //one to many relationship with Accio(transactions) i.e. one vault can have many transactions

}
