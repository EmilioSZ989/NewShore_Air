package com.newshoreair.api.apirest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newshoreair.api.apirest.Model.TransportModel;

public interface TransportRepository extends JpaRepository<TransportModel,String>{

}
