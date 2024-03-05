package com.newshoreair.api.apirest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newshoreair.api.apirest.Model.JourneyModel;

public interface JourneyRepository extends JpaRepository<JourneyModel,Long>{

}
