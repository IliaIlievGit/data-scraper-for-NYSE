package com.project.DataScrapingForNYSE.scheduled;

import com.project.DataScrapingForNYSE.helpers.RawDataExtractor;
import com.project.DataScrapingForNYSE.models.NasdaqTraderObject;
import com.project.DataScrapingForNYSE.models.RawData;
import com.project.DataScrapingForNYSE.services.contracts.JsoupScraper;
import com.project.DataScrapingForNYSE.services.contracts.NasdaqTraderObjectService;
import com.project.DataScrapingForNYSE.services.contracts.RawDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NasdaqTraderAutomationCall {

    private final RawDataService rawDataService;
    private final NasdaqTraderObjectService nasdaqTraderObjectService;
    private final JsoupScraper jsoupScraper;
    private final RawDataExtractor rawDataExtractor;

    @Scheduled(cron = "00 10 12 * * MON-FRI")
    public void automaticallySaveNewRawData() {
        try {
            String bodyElement = jsoupScraper.scrapeBodyOfNasdaqTraderWebsite();
            RawData newRawData = rawDataService.saveNewDataToDatabase(new RawData(0, bodyElement, LocalDateTime.now()));

            List<NasdaqTraderObject> nasdaqTraderObjectList = rawDataExtractor.extractNasdaqTraderObjectsFromRawData(newRawData);
            for (NasdaqTraderObject nasdaqTraderObject : nasdaqTraderObjectList) {
                nasdaqTraderObjectService.saveNewNasdaqTraderObject(nasdaqTraderObject);
            }
        } catch (RuntimeException e) {
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " : " + e.getMessage());
        }
    }
}

