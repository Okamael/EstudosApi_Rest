package com.estudos.projeto.cars.ProjetoCars.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRequestRepository extends JpaRepository<TravelRequest, Long>{
	
	List<TravelRequest> findByStatus(TravelRequestStatus status);
}
