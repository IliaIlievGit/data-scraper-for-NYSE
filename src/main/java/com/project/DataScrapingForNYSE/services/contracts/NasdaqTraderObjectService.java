package com.project.DataScrapingForNYSE.services.contracts;

import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;

public interface NasdaqTraderObjectService {

    NasdaqTraderObject findNasdaqTraderObjectBySymbol(String symbol);
}
