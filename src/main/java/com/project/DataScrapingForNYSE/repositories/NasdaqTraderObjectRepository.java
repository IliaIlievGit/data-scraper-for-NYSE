package com.project.DataScrapingForNYSE.repositories;

import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NasdaqTraderObjectRepository extends JpaRepository<NasdaqTraderObject, Integer> {

    Optional<NasdaqTraderObject> findNasdaqTraderObjectBySymbol(String symbol);

    @Query("SELECT n FROM NasdaqTraderObject n " +
            "WHERE CONCAT (n.symbol, n.companyName, n.nasdaqAction, n.bxAction, n.psxAction, n.effectiveDate, n.primaryListingMarket) " +
            "LIKE %:keyword%")
    List<NasdaqTraderObject> findAllBy (String keyword, Pageable pageable);
}
