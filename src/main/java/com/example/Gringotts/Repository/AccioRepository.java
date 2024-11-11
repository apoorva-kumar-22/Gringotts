package com.example.Gringotts.Repository;

import com.example.Gringotts.Entitiies.Accio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AccioRepository extends JpaRepository<Accio, Long> {

    // Custom method to find transactions by vaultId
    List<Accio> findByVaultId(Long vaultId);

    // Custom method to find transactions by vaultId and type
    List<Accio> findByVaultIdAndType(Long vaultId, String type);

    // Custom method to find transactions by vaultId and date range
    List<Accio> findByVaultIdAndDateBetween(Long vaultId, LocalDate startDate, LocalDate endDate);
}



