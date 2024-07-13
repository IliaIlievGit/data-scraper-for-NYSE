package com.project.DataScrapingForNYSE.services;

import com.project.DataScrapingForNYSE.exceptions.DuplicateEntityException;
import com.project.DataScrapingForNYSE.exceptions.EntityNotFoundException;
import com.project.DataScrapingForNYSE.helpers.ValidationsHelper;
import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import com.project.DataScrapingForNYSE.repositories.NasdaqTraderObjectRepository;
import com.project.DataScrapingForNYSE.services.contracts.NasdaqTraderObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NasdaqTraderObjectServiceImpl implements NasdaqTraderObjectService {

    private final NasdaqTraderObjectRepository repository;

    @Override
    public NasdaqTraderObject findNasdaqTraderObjectBySymbol(String symbol) {
        Optional<NasdaqTraderObject> potentialNasdaqTraderObject = repository.findBySymbol(symbol);
        ValidationsHelper.checkIfEntityIsPresent(potentialNasdaqTraderObject, "Nasdaq trader object", "symbol", symbol);
        return potentialNasdaqTraderObject.get();
    }

    @Override
    public NasdaqTraderObject findNasdaqTraderObjectById(int id) {
        Optional<NasdaqTraderObject> potentialNasdaqTraderObject = repository.findById(id);
        ValidationsHelper.checkIfEntityIsPresent(potentialNasdaqTraderObject, "Nasdaq trader object", "id", String.valueOf(id));
        return potentialNasdaqTraderObject.get();
    }

    @Override
    public List<NasdaqTraderObject> findAllNasdaqTraderObjects(String keyword, int pageNumber, int pageSize, String sortBy, String field) {
        Sort sort = sortBy.equals("DESC") ? Sort.by(Sort.Direction.DESC, field) : Sort.by(Sort.Direction.ASC, field);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return repository.findAllBy(keyword, pageable);
    }

    @Override
    public NasdaqTraderObject saveNewNasdaqTraderObject(NasdaqTraderObject nasdaqTraderObject) {
        checkIfNasdaqTraderObjectAlreadyExists(nasdaqTraderObject);
        return repository.saveAndFlush(nasdaqTraderObject);
    }

    @Override
    public NasdaqTraderObject findNasdaqTraderObjectByObject(NasdaqTraderObject nasdaqTraderObject) {
        Optional<NasdaqTraderObject> potentialNasdaqTraderObject = repository.findBySymbolAndAndCompanyNameAndNasdaqActionAndBxActionAndPsxActionAndEffectiveDateAndPrimaryListingMarket(
                nasdaqTraderObject.getSymbol(), nasdaqTraderObject.getCompanyName(), nasdaqTraderObject.getNasdaqAction(),
                nasdaqTraderObject.getBxAction(), nasdaqTraderObject.getPsxAction(), nasdaqTraderObject.getEffectiveDate(),
                nasdaqTraderObject.getPrimaryListingMarket());
        ValidationsHelper.checkIfEntityIsPresent(potentialNasdaqTraderObject, "Nasdaq trader object", "symbol", nasdaqTraderObject.getSymbol());
        return potentialNasdaqTraderObject.get();
    }

    private void checkIfNasdaqTraderObjectAlreadyExists(NasdaqTraderObject nasdaqTraderObject) {
        boolean duplicateExists = true;

        try {
            findNasdaqTraderObjectByObject(nasdaqTraderObject);

        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Nasdaq trader object", "symbol", nasdaqTraderObject.getSymbol());
        }
    }
}
