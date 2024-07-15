package com.project.DataScrapingForNYSE.helpers;

import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import com.project.DataScrapingForNYSE.models.RawData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.project.DataScrapingForNYSE.helpers.ConstantHelpers.INDEX_OF_FIRST_ROW_WITH_DATA_FOR_NASDAQ_TRADER_OBJECT;

@Component
public class RawDataExtractor {

    public List<NasdaqTraderObject> extractNasdaqTraderObjectsFromRawData(RawData rawData) {

        String text = rawData.getData();
        text = text.substring(INDEX_OF_FIRST_ROW_WITH_DATA_FOR_NASDAQ_TRADER_OBJECT);

        String[] rows = text.split("(?<=\\|[A-Z]) (?=[A-Z0-9=-]{1,7}\\|)");
        List<NasdaqTraderObject> nasdaqTraderObjectList = new ArrayList<>();

        for (String row : rows) {
            String[] elements = row.split("\\|");
            NasdaqTraderObject object = new NasdaqTraderObject();
            for (int index = 0; index < elements.length; index++) {
                switch (index) {
                    case 0:
                        object.setSymbol(elements[0]);
                        break;
                    case 1:
                        object.setCompanyName(elements[1]);
                        break;
                    case 2:
                        object.setNasdaqAction(elements[2]);
                        break;
                    case 3:
                        object.setBxAction(elements[3]);
                        break;
                    case 4:
                        object.setPsxAction(elements[4]);
                        break;
                    case 5:
                        object.setEffectiveDate(elements[5]);
                        break;
                    case 6:
                        object.setPrimaryListingMarket(elements[6].charAt(0));
                        break;
                }
            }
            nasdaqTraderObjectList.add(object);
        }
        return nasdaqTraderObjectList;
    }
}