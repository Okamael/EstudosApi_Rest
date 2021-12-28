package com.estudos.projeto.cars.ProjetoCars.interfaces;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.projeto.cars.ProjetoCars.domain.TravelRequest;

@Service
@RestController
@RequestMapping(path = "/travelRequest",produces = MediaType.APPLICATION_JSON_VALUE)
public class TravelRequestAPI {
	
	@PostMapping
	public void makeTravelRequest(@RequestBody TravelRequest travelRequest) {
		
	}
}
