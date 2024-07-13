package com.project.DataScrapingForNYSE;

import com.project.DataScrapingForNYSE.helpers.RawDataExtractor;
import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import com.project.DataScrapingForNYSE.models.RawData;
import com.project.DataScrapingForNYSE.services.contracts.NasdaqTraderObjectService;
import com.project.DataScrapingForNYSE.services.contracts.RawDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor

public class DataScrapingForNyseApplication implements CommandLineRunner {

    private final NasdaqTraderObjectService nasdaqTraderObjectService;
    private final RawDataService rawDataService;
    private final RawDataExtractor rawDataExtractor;

    public static void main(String[] args) {
        SpringApplication.run(DataScrapingForNyseApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

//		System.out.println(nasdaqTraderObjectService.findNasdaqTraderObjectBySymbol("TEST"));

//        Document document = Jsoup.connect(ConstantHelpers.NASDAQ_TRADER_URL).get();
//        Elements rawData = document.select("body");
//
//        String text = rawData.text();
//        int index = text.indexOf(FILE_CREATION_TIME_STRING);
//        text = text.substring(INDEX_OF_FIRST_ROW_WITH_DATA_FOR_NASDAQ_TRADER_OBJECT, index);
//        System.out.println(text);
//
//        String[] rows = text.split("(?<=\\|[A-Z]) (?=[A-Z]{1,5}\\|)");
//        String[] emptyRows = new String[0];
//        for (String row : rows) {
//            System.out.println(row);
//            String[] elements = row.split("\\|");
//            for (String element : elements) {
//                System.out.print(element + " ");
//            }
//            System.out.println();
//        }

//        RawData rawDataObject = new RawData(0, text, LocalDateTime.now());
//        rawDataService.saveNewDataToDatabase(rawDataObject);

        List<NasdaqTraderObject> nasdaqTraderObjectList = new ArrayList<>();
        List<RawData> rawData = rawDataService.findAllRawData();
        for (RawData rawDatum : rawData) {
            nasdaqTraderObjectList.addAll(rawDataExtractor.extractNasdaqTraderObjectsFromRawData(rawDatum));
        }


        for (NasdaqTraderObject object : nasdaqTraderObjectList) {
            System.out.println(object);
            nasdaqTraderObjectService.saveNewNasdaqTraderObject(object);
        }

        /*NasdaqTraderObject(id=0, symbol=CCNR, companyName=Financial Investors Trust, nasdaqAction=Add, bxAction=Add, psxAction=Add, effectiveDate=7/11/2024, primaryListingMarket=G)
         */
//        NasdaqTraderObject object = new NasdaqTraderObject(0, "CCNR", "Financial Investors Trust","Add","Add","Add","7/11/2024",'G');
//        NasdaqTraderObject objectFound = nasdaqTraderObjectService.findNasdaqTraderObjectByObject(object);
//        System.out.println(objectFound);
    }
}