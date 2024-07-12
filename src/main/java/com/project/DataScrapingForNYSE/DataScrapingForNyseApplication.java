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

        RawData rawDataObject = new RawData(1, text, LocalDateTime.now());
        rawDataService.saveNewDataToDatabase(rawDataObject);

//        System.out.println(text.indexOf("CCNR")); //93
//        System.out.println(text.indexOf("File Creation Time")); //539
//        System.out.println(text.length()); //577
//        // difference 577-339 = 38
//
//        String header = text.substring(0, 93);
//        String records = text.substring(93, text.length() - 38);
//        String rest = text.substring(text.length() - 38);
////		System.out.println(header);
////		System.out.println(records);
////		System.out.println(rest);
//
//        String[] headerColumns = header.split("\\|");
//
//        for (String column : headerColumns) {
//            System.out.printf("%-20s", column.trim());
//        }
//        System.out.println();
//
//        // Split records based on space followed by uppercase letter
//        String[] rows = records.split("(?=[A-Z]+\\|)");
//
//        // Print each row
//        for (String row : rows) {
//            String[] columns = row.trim().split("\\|");
//            for (String column : columns) {
//                System.out.printf("%-30s", column.trim());
//            }
//            System.out.println();
//        }

    }
}