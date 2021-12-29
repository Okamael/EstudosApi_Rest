package com.estudos.projeto.cars.ProjetoCars.interfaces.outcoming;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GMapsService {
	private static final String GMAPS_TEMPLATE = "https://maps.googleapis.com/maps/api/directions/json?origin={origin}&destination={destination}&key={key}";
	
	@Value("${app.car.domain.googlemaps.apikey}")
	private String appKey;
	
	
	public Integer getDistanceBetweenAddresses(String addresOne, String addressTwo) {
		RestTemplate template = new RestTemplate();
		String jsonResult =  template.getForObject(GMAPS_TEMPLATE,String.class, addresOne, addressTwo, appKey);
		
		return 0;
	}
}
