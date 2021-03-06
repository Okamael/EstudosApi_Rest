package com.estudos.projeto.cars.ProjetoCars.interfaces.incoming;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.estudos.projeto.cars.ProjetoCars.domain.Passenger;
import com.estudos.projeto.cars.ProjetoCars.domain.PassengerRepository;

@Service
@RestController
@RequestMapping(path = "/passengers", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassengerAPI {
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@GetMapping()
	public List<Passenger> listPassengers(){
		return passengerRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Passenger findPassenger(@PathVariable("id")Long id) {
		return passengerRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public Passenger createPassenger(@RequestBody Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	
	@PutMapping("/{id}")
	public Passenger fullUpdatepPassenger(@PathVariable("id") Long id, @RequestBody Passenger passenger) {
		Passenger passengerFound = findPassenger(id);
		passengerFound.setName(passenger.getName());
		return passengerRepository.save(passengerFound);
	}
	
	@PatchMapping("/{id}")
	public Passenger incrementalUpdatePassenger(@PathVariable("id")Long id, @RequestBody Passenger passenger) {
		
		Passenger foundPassenger =  findPassenger(id);
		foundPassenger.setName(Optional.ofNullable(passenger.getName()).orElse(foundPassenger.getName()));
		return passengerRepository.save(foundPassenger);
		
	}
	
	@DeleteMapping("/{id}")
	public void deletePassenger(@PathVariable("id")Long id) {
		passengerRepository.delete(findPassenger(id));
	}
}
