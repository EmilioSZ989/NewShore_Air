package com.newshoreair.api.apirest.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "journeys")
public class JourneyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "journey_id")
    private List<FlightModel> flights;

    private String origin;
    private String destination;
    private double price;

    public JourneyModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FlightModel> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightModel> flights) {
        this.flights = flights;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "JourneyModel [id=" + id + ", flights=" + flights + ", origin=" + origin + ", destination=" + destination
                + ", price=" + price + "]";
    }
}