package com.project.DataScrapingForNYSE.services;

import com.project.DataScrapingForNYSE.exceptions.DuplicateEntityException;
import com.project.DataScrapingForNYSE.exceptions.EntityNotFoundException;
import com.project.DataScrapingForNYSE.helpers.ValidationsHelper;
import com.project.DataScrapingForNYSE.models.RawData;
import com.project.DataScrapingForNYSE.repositories.RawDataRepository;
import com.project.DataScrapingForNYSE.services.contracts.RawDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RawDataServiceImpl implements RawDataService {

    private final RawDataRepository repository;

    @Override
    public RawData findRawDataByData(String data) {
        Optional<RawData> potentialRawData = repository.findRawDataByData(data);
        ValidationsHelper.checkIfEntityIsPresent(potentialRawData, "Raw data", "data", data);
        return potentialRawData.get();
    }

    @Override
    public RawData saveNewDataToDatabase(RawData rawData) {
        checkIfRawDataAlreadyExists(rawData);
        return repository.saveAndFlush(rawData);
    }

    private void checkIfRawDataAlreadyExists(RawData rawData) {
        boolean duplicateExists = true;

        try {
            findRawDataByData(rawData.getData());

        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Raw data", "data", rawData.getData());
        }
    }
}
