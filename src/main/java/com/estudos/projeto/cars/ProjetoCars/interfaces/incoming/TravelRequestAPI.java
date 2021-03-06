package com.estudos.projeto.cars.ProjetoCars.interfaces.incoming;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.projeto.cars.ProjetoCars.domain.TravelRequest;
import com.estudos.projeto.cars.ProjetoCars.domain.TravelService;
import com.estudos.projeto.cars.ProjetoCars.interfaces.incoming.input.TravelRequestInput;
import com.estudos.projeto.cars.ProjetoCars.interfaces.incoming.mapping.TravelRequestMapper;
import com.estudos.projeto.cars.ProjetoCars.interfaces.incoming.output.TravelRequestOutput;

@Service
@RestController
@RequestMapping(path = "/travelRequest",produces = MediaType.APPLICATION_JSON_VALUE)
public class TravelRequestAPI {
	@Autowired
	TravelService travelService;
	@Autowired
	TravelRequestMapper mapper;
	
	@PostMapping
	public EntityModel<TravelRequestOutput> makeTravelRequest(@RequestBody TravelRequestInput travelRequestInput) {
		
		TravelRequest request = travelService.saveTravelRequest(mapper.map(travelRequestInput));
		
		TravelRequestOutput output = mapper.map(request);
		return mapper.buildOutputModel(request, output);
	}
	
	@GetMapping("/nearby")
	public List<EntityModel<TravelRequestOutput>> listNearbyRequests(@RequestParam String currentAddress){
		
		List<TravelRequest> request  =  travelService.listNearbyTravelRequest(currentAddress);
		
		return mapper.buildOutPutModel(request);
	}
	
}
