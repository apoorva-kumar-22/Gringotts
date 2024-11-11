package com.example.Gringotts.Controller;

import com.example.Gringotts.Entitiies.Accio;
import com.example.Gringotts.Entitiies.Vault;
import com.example.Gringotts.Services.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vaults")
public class VaultController {

    //Handles vault-related requests, such as creating vaults,
    // viewing vault information, and checking balances.

   @Autowired
    VaultService vaultService;

   //end point to create a new vault
    @PostMapping("/vault")
    public ResponseEntity<Vault>  createVault(@RequestParam String ownerName, @RequestParam BigDecimal initialGalleons){
        Vault vault = vaultService.createVault(ownerName,initialGalleons);
        return ResponseEntity.ok(vault);
    }

    @GetMapping("/{vaultId}")
    public ResponseEntity<Vault> getVaultDetails(@PathVariable Long vaultId){
        Vault vault=vaultService.getVaultDetails(vaultId);
        return ResponseEntity.ok(vault);
    }

    // Endpoint to add a transaction to a vault
    @PostMapping("/{vaultId}/accio")
    public ResponseEntity<Accio> addAccio(
            @PathVariable Long vaultId,
            @RequestParam BigDecimal galleons,
            @RequestParam String type,
            @RequestParam String description
    ){
       Accio accio= vaultService.accioMoney(vaultId,galleons,type,description);
       return ResponseEntity.ok(accio);
    }

    // Endpoint to get the current balance of a vault
    @GetMapping("/{vaultId}/galleons")
    public ResponseEntity<BigDecimal> getGalleons(@PathVariable Long vaultId) {
        BigDecimal galleons = vaultService.getGalleons(vaultId);
        return ResponseEntity.ok(galleons);
    }

    // Endpoint to get all transactions for a vault
    @GetMapping("/{vaultId}/accios")
    public ResponseEntity<List<Accio>> getAccios(@PathVariable Long vaultId) {
        List<Accio> transactions = vaultService.getAccios(vaultId);
        return ResponseEntity.ok(transactions);
    }

    // Endpoint to calculate total expenditure of a vault
    @GetMapping("/{vaultId}/total-expenditure")
    public ResponseEntity<BigDecimal> calculateTotalExpenditure(@PathVariable Long vaultId) {
        BigDecimal totalExpenditure = vaultService.calculateTotalExpenditure(vaultId);
        return ResponseEntity.ok(totalExpenditure);
    }

    // Endpoint to get transactions by type for a specific vault
    @GetMapping("/vault/{vaultId}/type")
    public ResponseEntity<List<Accio>> getAcciosByType(
            @PathVariable Long vaultId,
            @RequestParam String type) {
        List<Accio> accios = vaultService.getTransactionsByType(vaultId, type);
        return ResponseEntity.ok(accios);
    }

    // Endpoint to get transactions within a date range for a specific vault
    @GetMapping("/vault/{vaultId}/date-range")
    public ResponseEntity<List<Accio>> getAcciosByDateRange(
            @PathVariable Long vaultId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Accio> accios = vaultService.getTransactionsByDateRange(vaultId, startDate, endDate);
        return ResponseEntity.ok(accios);
    }
}
