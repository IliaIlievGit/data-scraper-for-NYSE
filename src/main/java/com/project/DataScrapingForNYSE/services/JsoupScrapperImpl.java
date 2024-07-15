package com.project.DataScrapingForNYSE.services;

import com.project.DataScrapingForNYSE.helpers.ConstantHelpers;
import com.project.DataScrapingForNYSE.services.contracts.JsoupScraper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static com.project.DataScrapingForNYSE.helpers.ConstantHelpers.FILE_CREATION_TIME_STRING;

@Component
@Transactional
@RequiredArgsConstructor
public class JsoupScrapperImpl implements JsoupScraper {
    @Override
    public String scrapeBodyOfNasdaqTraderWebsite() {
        try {
            Document document = Jsoup.connect(ConstantHelpers.NASDAQ_TRADER_URL).get();
            Elements rawDataElements = document.select("body");
            return rawDataElements.text().substring(0, rawDataElements.text().indexOf(FILE_CREATION_TIME_STRING));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
