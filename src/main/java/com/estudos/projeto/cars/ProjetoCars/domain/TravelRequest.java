package com.estudos.projeto.cars.ProjetoCars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class TravelRequest {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Passenger passenger;
	private String origin;
	private String destination;
	
}
