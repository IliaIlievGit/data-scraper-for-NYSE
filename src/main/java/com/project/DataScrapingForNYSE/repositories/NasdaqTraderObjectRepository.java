package com.project.DataScrapingForNYSE.repositories;

import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NasdaqTraderObjectRepository extends JpaRepository<NasdaqTraderObject, Integer> {

    Optional<NasdaqTraderObject> findNasdaqTraderObjectBySymbol(String symbol);
}
