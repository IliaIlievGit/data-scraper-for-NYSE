package com.project.DataScrapingForNYSE.models.service;

import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import com.project.DataScrapingForNYSE.models.service.contracts.NasdaqTraderObjectService;
import com.project.DataScrapingForNYSE.repository.NasdaqTraderObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NasdaqTraderObjectServiceImpl implements NasdaqTraderObjectService {

    private final NasdaqTraderObjectRepository repository;
    @Override
    public NasdaqTraderObject findNasdaqTraderObjectBySymbol(String symbol) {
        return repository.findNasdaqTraderObjectBySymbol(symbol);
    }
}
