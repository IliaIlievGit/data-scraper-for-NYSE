package com.project.DataScrapingForNYSE.services;

import com.project.DataScrapingForNYSE.helpers.ValidationsHelper;
import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import com.project.DataScrapingForNYSE.services.contracts.NasdaqTraderObjectService;
import com.project.DataScrapingForNYSE.repositories.NasdaqTraderObjectRepository;
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
        Optional<NasdaqTraderObject> potentialNasdaqTraderObject = repository.findNasdaqTraderObjectBySymbol(symbol);
        ValidationsHelper.checkIfEntityIsPresent(potentialNasdaqTraderObject, "Nasdaq trader object", "symbol", symbol);
        return potentialNasdaqTraderObject.get();
    }

    @Override
    public List<NasdaqTraderObject> findAllNasdaqTraderObjects(String keyword, int pageNumber, int pageSize, String sortBy, String field) {
        Sort sort = sortBy.equals("DESC") ? Sort.by(Sort.Direction.DESC, field) : Sort.by(Sort.Direction.ASC, field);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return repository.findAllBy(keyword, pageable);
    }
}
