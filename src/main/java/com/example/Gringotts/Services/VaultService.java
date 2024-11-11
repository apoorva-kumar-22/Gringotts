package com.example.Gringotts.Services;

import com.example.Gringotts.Entitiies.Accio;
import com.example.Gringotts.Entitiies.Vault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface VaultService {

    public Vault createVault(String ownerName, BigDecimal initialGalleons);

    public Vault getVaultDetails(Long vaultId);

    public Accio accioMoney(Long vaultId, BigDecimal galleons, String type, String description);

    public BigDecimal getGalleons(Long vaultId);

    public List<Accio> getAccios(Long vaultId);

    public BigDecimal calculateTotalExpenditure(Long vaultId);

    public  List<Accio> getTransactionsByDateRange(Long vaultId, LocalDate startDate,LocalDate endDate);

    public List<Accio> getTransactionsByType(Long vaultId,String type);

}
