package com.project.DataScrapingForNYSE.repositories;

import com.project.DataScrapingForNYSE.models.RawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RawDataRepository extends JpaRepository<RawData, Integer> {

    Optional<RawData> findRawDataByData (String data);
}
