package com.project.DataScrapingForNYSE.services;

import com.project.DataScrapingForNYSE.exceptions.DuplicateEntityException;
import com.project.DataScrapingForNYSE.exceptions.EntityNotFoundException;
import com.project.DataScrapingForNYSE.helpers.ValidationsHelper;
import com.project.DataScrapingForNYSE.models.RawData;
import com.project.DataScrapingForNYSE.repositories.RawDataRepository;
import com.project.DataScrapingForNYSE.services.contracts.RawDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public RawData findRawDataById(int id) {
        Optional<RawData> potentialRawData = repository.findRawDataById(id);
        ValidationsHelper.checkIfEntityIsPresent(potentialRawData, "Raw data", "id", String.valueOf(id));
        return potentialRawData.get();
    }

    @Override
    public List<RawData> findAllRawData() {
        return repository.findAll();
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
