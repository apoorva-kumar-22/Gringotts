package com.example.Gringotts.Services;

import com.example.Gringotts.Entitiies.Accio;
import com.example.Gringotts.Entitiies.Vault;
import com.example.Gringotts.Repository.AccioRepository;
import com.example.Gringotts.Repository.VaultRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class VaultServiceBean implements VaultService{

    @Autowired
    private VaultRepository vaultRepository;

    @Autowired
    private AccioRepository accioRepository;

    public Vault createVault(String ownerName, BigDecimal initialGalleons){
        Vault vault=new Vault();
        vault.setOwnerName(ownerName);
        vault.setGalleons(initialGalleons);
        vault.setGoblinAccountant("Bodrig the Boss-Eyed");
        return vaultRepository.save(vault);
    }

    //get Vault details by vault id
    public Vault getVaultDetails(Long vaultId){
        return vaultRepository.findById(vaultId).orElseThrow(()-> new RuntimeException("Vault not found"));
    }

    @Transactional
    public Accio accioMoney(Long vaultId, BigDecimal galleons, String type, String description){
        Vault vault=getVaultDetails(vaultId);
        Accio accio=new Accio();
        accio.setVaultId(vaultId);
        accio.setAmount(galleons);
        accio.setType(type);
        accio.setDescription(description);
        accio.setDate(LocalDate.now());

        // Adjust vault balance based on transaction type
        if(type.equalsIgnoreCase("Expenditure")){

            vault.setGalleons(vault.getGalleons().subtract(galleons));

        } else if (type.equalsIgnoreCase("Deposit")) {

            vault.setGalleons(vault.getGalleons().add(galleons));
        }

        accioRepository.save(accio);
        vaultRepository.save(vault);

        return accio;
    }


    // Method to calculate the current balance for the vault
    public BigDecimal getGalleons(Long vaultId) {
        Vault vault = getVaultDetails(vaultId);
        return vault.getGalleons();
    }

    // Method to list all transactions for a vault
    public List<Accio> getAccios(Long vaultId) {
        return accioRepository.findByVaultId(vaultId);
    }

    // Method to calculate total expenditure for the vault
    public BigDecimal calculateTotalExpenditure(Long vaultId) {

        List<Accio> accios=accioRepository.findByVaultIdAndType(vaultId, "Expenditure");
        return accios.stream()
                .map(Accio::getGalleons)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Accio> getTransactionsByDateRange(Long vaultId, LocalDate startDate, LocalDate endDate) {
        return accioRepository.findByVaultIdAndDateBetween(vaultId,startDate,endDate);
    }

    @Override
    public List<Accio> getTransactionsByType(Long vaultId, String type) {
        return accioRepository.findByVaultIdAndType(vaultId,type);
    }


}
