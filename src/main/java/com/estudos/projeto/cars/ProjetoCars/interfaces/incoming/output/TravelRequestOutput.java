package com.estudos.projeto.cars.ProjetoCars.interfaces.incoming.output;

import java.util.Date;

import com.estudos.projeto.cars.ProjetoCars.domain.TravelRequestStatus;

import lombok.Data;

@Data
public class TravelRequestOutput {
	
	Long id;
	String origen;
	String destination;
	TravelRequestStatus status;
	Date creationDate;
}
