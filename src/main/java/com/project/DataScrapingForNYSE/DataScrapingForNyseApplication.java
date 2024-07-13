package com.project.DataScrapingForNYSE;

import com.project.DataScrapingForNYSE.helpers.ConstantHelpers;
import com.project.DataScrapingForNYSE.models.RawData;
import com.project.DataScrapingForNYSE.services.contracts.NasdaqTraderObjectService;
import com.project.DataScrapingForNYSE.services.contracts.RawDataService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import static com.project.DataScrapingForNYSE.helpers.ConstantHelpers.FILE_CREATION_TIME_STRING;
import static com.project.DataScrapingForNYSE.helpers.ConstantHelpers.INDEX_OF_FIRST_ROW_WITH_DATA_FOR_NASDAQ_TRADER_OBJECT;

@SpringBootApplication
@RequiredArgsConstructor

public class DataScrapingForNyseApplication implements CommandLineRunner {

    private final NasdaqTraderObjectService nasdaqTraderObjectService;
    private final RawDataService rawDataService;

    public static void main(String[] args) {
        SpringApplication.run(DataScrapingForNyseApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

//		System.out.println(nasdaqTraderObjectService.findNasdaqTraderObjectBySymbol("TEST"));

        Document document = Jsoup.connect(ConstantHelpers.NASDAQ_TRADER_URL).get();
        Elements rawData = document.select("body");

        String text = rawData.text();
        int index = text.indexOf(FILE_CREATION_TIME_STRING);
        text = text.substring(INDEX_OF_FIRST_ROW_WITH_DATA_FOR_NASDAQ_TRADER_OBJECT, index);
        System.out.println(text);

        String[] rows = text.split("(?<=\\|[A-Z]) (?=[A-Z]{1,5}\\|)");
        String[] emptyRows = new String[0];
        for (String row : rows) {
            System.out.println(row);
            String[] elements = row.split("\\|");
            for (String element : elements) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

//        RawData rawDataObject = new RawData(0, text, LocalDateTime.now());
//        rawDataService.saveNewDataToDatabase(rawDataObject);


    }
}