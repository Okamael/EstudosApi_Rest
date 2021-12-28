package com.estudos.projeto.cars.ProjetoCars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.projeto.cars.ProjetoCars.domain.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
