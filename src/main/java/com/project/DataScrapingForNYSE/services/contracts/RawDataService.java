package com.project.DataScrapingForNYSE.services.contracts;

import com.project.DataScrapingForNYSE.models.RawData;

import java.util.List;

public interface RawDataService {

    RawData findRawDataByData(String data);

    RawData saveNewDataToDatabase(RawData rawData);

    RawData findRawDataById(int id);

    List<RawData> findAllRawData();
}
