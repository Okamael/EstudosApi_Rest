package com.estudos.projeto.cars.ProjetoCars.interfaces.incoming.mapping;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.estudos.projeto.cars.ProjetoCars.domain.Passenger;
import com.estudos.projeto.cars.ProjetoCars.domain.PassengerRepository;
import com.estudos.projeto.cars.ProjetoCars.domain.TravelRequest;
import com.estudos.projeto.cars.ProjetoCars.interfaces.incoming.PassengerAPI;
import com.estudos.projeto.cars.ProjetoCars.interfaces.incoming.input.TravelRequestInput;
import com.estudos.projeto.cars.ProjetoCars.interfaces.incoming.output.TravelRequestOutput;

@Component
public class TravelRequestMapper {

    @Autowired
    private PassengerRepository passengerRepository;

    public TravelRequest map(TravelRequestInput input) {

        Passenger passenger = passengerRepository.findById(input.getPassengerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        TravelRequest travelRequest = new TravelRequest();
        travelRequest.setOrigin(input.getOrigin());
        travelRequest.setDestination(input.getDestination());
        travelRequest.setPassenger(passenger);

        return travelRequest;
    }

    public TravelRequestOutput map(TravelRequest travelRequest) {
        TravelRequestOutput travelRequestOutput = new TravelRequestOutput();

        travelRequestOutput.setCreationDate(travelRequest.getCreationDate());
        travelRequestOutput.setDestination(travelRequest.getDestination());
        travelRequestOutput.setId(travelRequest.getId());
        travelRequestOutput.setOrigen(travelRequest.getOrigin());
        travelRequestOutput.setStatus(travelRequest.getStatus());

        return travelRequestOutput;
    }

    public EntityModel<TravelRequestOutput> buildOutputModel(TravelRequest travelRequest, TravelRequestOutput output) {
       
    	EntityModel<TravelRequestOutput> model = new EntityModel<>(output);

        Link passengerLink = WebMvcLinkBuilder
                .linkTo(PassengerAPI.class)
                .slash(travelRequest.getPassenger().getId())
                .withRel("passenger")
                .withTitle(travelRequest.getPassenger().getName());
        model.add(passengerLink);
        return model;
    }
    
    public List<EntityModel<TravelRequestOutput>> buildOutPutModel(List<TravelRequest>requests){
    	return requests.stream().map(tr -> buildOutputModel(tr, map(tr))).collect(Collectors.toList());
    }
}