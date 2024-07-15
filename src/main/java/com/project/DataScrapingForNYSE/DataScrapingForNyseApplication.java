package com.project.DataScrapingForNYSE;

import com.project.DataScrapingForNYSE.helpers.RawDataExtractor;
import com.project.DataScrapingForNYSE.services.contracts.NasdaqTraderObjectService;
import com.project.DataScrapingForNYSE.services.contracts.RawDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class DataScrapingForNyseApplication implements CommandLineRunner {

    private final NasdaqTraderObjectService nasdaqTraderObjectService;
    private final RawDataService rawDataService;
    private final RawDataExtractor rawDataExtractor;

    public static void main(String[] args) {
        SpringApplication.run(DataScrapingForNyseApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {


    }

}