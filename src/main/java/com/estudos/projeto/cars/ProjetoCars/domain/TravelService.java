package com.estudos.projeto.cars.ProjetoCars.domain;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estudos.projeto.cars.ProjetoCars.interfaces.outcoming.GMapsService;

@Component
public class TravelService {
	
	@Autowired
	TravelRequestRepository travelRequestRepository;
	
	
	@Autowired
	GMapsService gMapsService;
	
	private static final int MAX_TRAVEL_TIME = 600;
	
	public TravelRequest saveTravelRequest(TravelRequest travelRequest) {
		travelRequest.setStatus(TravelRequestStatus.CREATED);
		travelRequest.setCreationDate(new Date());
		return travelRequestRepository.save(travelRequest);
	}
	
	public List<TravelRequest> listNearbyTravelRequest(String currentAddres){
		List<TravelRequest> request = travelRequestRepository.findByStatus(TravelRequestStatus.CREATED);
		
		return request.stream().filter(tr -> gMapsService.getDistanceBetweenAddresses(currentAddres, tr.getOrigin())<MAX_TRAVEL_TIME).collect(Collectors.toList());
	}
}
