package com.newshoreair.api.apirest.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transports")
public class TransportModel {
	
	@Id
    private String flightNumber;
    private String flightCarrier;
    
	public TransportModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFlightCarrier() {
		return flightCarrier;
	}

	public void setFlightCarrier(String flightCarrier) {
		this.flightCarrier = flightCarrier;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Override
	public String toString() {
		return "TransportModel [flightCarrier=" + flightCarrier + ", flightNumber=" + flightNumber + "]";
	}

    
}
