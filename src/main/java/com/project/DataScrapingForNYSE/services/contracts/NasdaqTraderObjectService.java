package com.project.DataScrapingForNYSE.services.contracts;

import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;

import java.util.List;

public interface NasdaqTraderObjectService {

    NasdaqTraderObject findNasdaqTraderObjectBySymbol(String symbol);

    NasdaqTraderObject findNasdaqTraderObjectById(int id);

    List<NasdaqTraderObject> findAllNasdaqTraderObjects(String keyword, int pageNumber, int pageSize, String sortBy, String field);

    NasdaqTraderObject saveNewNasdaqTraderObject(NasdaqTraderObject nasdaqTraderObject);

    NasdaqTraderObject findNasdaqTraderObjectByObject(NasdaqTraderObject nasdaqTraderObject);
}
