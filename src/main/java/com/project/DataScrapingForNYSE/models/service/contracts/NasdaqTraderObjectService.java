package com.project.DataScrapingForNYSE.models.service.contracts;

import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;

public interface NasdaqTraderObjectService {

    NasdaqTraderObject findNasdaqTraderObjectBySymbol(String symbol);
}
