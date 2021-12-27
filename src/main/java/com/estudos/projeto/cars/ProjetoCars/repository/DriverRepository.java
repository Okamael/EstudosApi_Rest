package com.estudos.projeto.cars.ProjetoCars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.projeto.cars.ProjetoCars.domain.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long>{

}
