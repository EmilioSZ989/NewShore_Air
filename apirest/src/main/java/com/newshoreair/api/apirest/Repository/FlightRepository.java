package com.newshoreair.api.apirest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newshoreair.api.apirest.Model.FlightModel;

public interface FlightRepository extends JpaRepository<FlightModel,Long>{
	List<FlightModel> findByOrigin(String origin);
}
