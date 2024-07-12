package com.project.DataScrapingForNYSE.services;

import com.project.DataScrapingForNYSE.helpers.ValidationsHelper;
import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import com.project.DataScrapingForNYSE.services.contracts.NasdaqTraderObjectService;
import com.project.DataScrapingForNYSE.repositories.NasdaqTraderObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NasdaqTraderObjectServiceImpl implements NasdaqTraderObjectService {

    private final NasdaqTraderObjectRepository repository;
    @Override
    public NasdaqTraderObject findNasdaqTraderObjectBySymbol(String symbol) {
        Optional<NasdaqTraderObject> potentialNasdaqTraderObject = repository.findNasdaqTraderObjectBySymbol(symbol);
        ValidationsHelper.checkIfEntityIsPresent(potentialNasdaqTraderObject, "Nasdaq trader object", "symbol", symbol);
        return potentialNasdaqTraderObject.get();
    }
}
