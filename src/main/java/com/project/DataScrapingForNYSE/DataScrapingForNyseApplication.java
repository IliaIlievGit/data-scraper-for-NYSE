package com.project.DataScrapingForNYSE;

import com.project.DataScrapingForNYSE.models.service.contracts.NasdaqTraderObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor

public class DataScrapingForNyseApplication implements CommandLineRunner {

	private final NasdaqTraderObjectService nasdaqTraderObjectService;

	public static void main(String[] args) {
		SpringApplication.run(DataScrapingForNyseApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(nasdaqTraderObjectService.findNasdaqTraderObjectBySymbol("TEST"));
	}
}
