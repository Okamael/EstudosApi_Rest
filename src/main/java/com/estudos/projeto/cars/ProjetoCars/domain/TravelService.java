package com.estudos.projeto.cars.ProjetoCars.domain;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TravelService {
	
	@Autowired
	TravelRequestRepository travelRequestRepository;
	
	public TravelRequest saveTravelRequest(TravelRequest travelRequest) {
		travelRequest.setStatus(TravelRequestStatus.CREATED);
		travelRequest.setCreationDate(new Date());
		return travelRequestRepository.save(travelRequest);
	}
}
