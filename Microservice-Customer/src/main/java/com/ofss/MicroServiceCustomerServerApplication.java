package com.ofss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient

public class MicroServiceCustomerServerApplication {
	
	public static final String STOCKS_SERVICE_URL="http://stocks-microservice";
	
	public static void main(String[] args) {
		SpringApplication.run(MicroServiceCustomerServerApplication.class, args);
	}
	
	@Bean
	public RemoteStockRepository remoteRepository()
	{
		RemoteStockRepository rsr=new RemoteStockRepository(STOCKS_SERVICE_URL);
		return rsr;
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTempate()
	{
		return new RestTemplate();
	}
	
}
