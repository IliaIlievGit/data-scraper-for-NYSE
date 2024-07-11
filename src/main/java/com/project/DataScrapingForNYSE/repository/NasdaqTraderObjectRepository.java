package com.project.DataScrapingForNYSE.repository;

import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasdaqTraderObjectRepository extends JpaRepository<NasdaqTraderObject, Integer> {

    NasdaqTraderObject findNasdaqTraderObjectBySymbol(String symbol);
}
