package com.project.DataScrapingForNYSE.services.contracts;

import com.project.DataScrapingForNYSE.models.RawData;

public interface RawDataService {

    RawData findRawDataByData (String data);
    RawData saveNewDataToDatabase (RawData rawData);
}
