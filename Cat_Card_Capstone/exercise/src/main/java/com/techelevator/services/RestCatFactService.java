package com.techelevator.services;


import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	public static final String API_BASE_URL = "https://cat-data.netlify.app/api/facts/random";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatFact getFact() throws RestClientResponseException {
		// TODO Auto-generated method stub
		CatFact catFact = restTemplate.getForObject(API_BASE_URL, CatFact.class);
		return catFact;
	}
}
