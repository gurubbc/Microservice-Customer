package com.ofss;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RemoteStockRepository {
	
	@Autowired
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	Stock stocks[];
	ArrayList<Stock> stockList;
	
	// constructor
 	public RemoteStockRepository(String serviceUrl) {
 		//http://stocks-microserice
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	public void getAllStocks() {
		stocks = restTemplate.getForObject(serviceUrl+"/Stocks", Stock[].class);
		stockList=new ArrayList<Stock>(Arrays.asList(stocks));
		
				
	}

	public Stock getStock(int stockId) {
		System.out.println("serviceUrl is "+serviceUrl + "/stocks/{stockId}");
		// http://stocks-microservice/stocks/4
		return restTemplate.getForObject(serviceUrl + "/stocks/{stockId}",
				Stock.class, stockId);
	}


}
