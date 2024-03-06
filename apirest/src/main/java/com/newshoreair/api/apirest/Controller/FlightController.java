package com.newshoreair.api.apirest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.newshoreair.api.apirest.Model.FlightModel;
import com.newshoreair.api.apirest.Repository.FlightRepository;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:4200/")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    // Obtener todos los vuelos
    @GetMapping
    public ResponseEntity<List<FlightModel>> getAllFlights() {
        List<FlightModel> flights = flightRepository.findAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // Obtener un vuelo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<FlightModel> getFlightById(@PathVariable("id") Long id) {
        Optional<FlightModel> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            return new ResponseEntity<>(flightOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo vuelo
    @PostMapping
    public ResponseEntity<FlightModel> createFlight(@RequestBody FlightModel flightModel) {
        FlightModel createdFlight = flightRepository.save(flightModel);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    // Actualizar un vuelo existente
    @PutMapping("/{id}")
    public ResponseEntity<FlightModel> updateFlight(@PathVariable("id") Long id, @RequestBody FlightModel flightModel) {
        Optional<FlightModel> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            flightModel.setId(id);
            FlightModel updatedFlight = flightRepository.save(flightModel);
            return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un vuelo existente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable("id") Long id) {
        Optional<FlightModel> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            flightRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
