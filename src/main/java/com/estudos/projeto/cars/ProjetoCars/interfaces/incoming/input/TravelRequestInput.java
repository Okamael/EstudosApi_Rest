package com.estudos.projeto.cars.ProjetoCars.interfaces.incoming.input;

import lombok.Data;

@Data
public class TravelRequestInput {
	
	Long passengerId;
	String origin;
	String destination;

}
