package com.estudos.projeto.cars.ProjetoCars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Passenger {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
}