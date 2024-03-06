package com.newshoreair.api.apirest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.newshoreair.api.apirest.Model.TransportModel;
import com.newshoreair.api.apirest.Repository.TransportRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transports")
@CrossOrigin(origins = "http://localhost:4200/")
public class TransportController {

    @Autowired
    private TransportRepository transportRepository;

    // Obtener todos los transportes
    @GetMapping
    public ResponseEntity<List<TransportModel>> getAllTransports() {
        List<TransportModel> transports = transportRepository.findAll();
        return new ResponseEntity<>(transports, HttpStatus.OK);
    }

    // Obtener un transporte por su número de vuelo
    @GetMapping("/{flightNumber}")
    public ResponseEntity<TransportModel> getTransportByFlightNumber(@PathVariable String flightNumber) {
        Optional<TransportModel> transport = transportRepository.findById(flightNumber);
        if (transport.isPresent()) {
            return new ResponseEntity<>(transport.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo transporte
    @PostMapping
    public ResponseEntity<TransportModel> createTransport(@RequestBody TransportModel transport) {
        TransportModel newTransport = transportRepository.save(transport);
        return new ResponseEntity<>(newTransport, HttpStatus.CREATED);
    }

    // Actualizar un transporte existente
    @PutMapping("/{flightNumber}")
    public ResponseEntity<TransportModel> updateTransport(@PathVariable String flightNumber, @RequestBody TransportModel transport) {
        Optional<TransportModel> existingTransport = transportRepository.findById(flightNumber);
        if (existingTransport.isPresent()) {
            TransportModel updatedTransport = existingTransport.get();
            updatedTransport.setFlightCarrier(transport.getFlightCarrier());
            // Actualizar otros campos según sea necesario
            transportRepository.save(updatedTransport);
            return new ResponseEntity<>(updatedTransport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un transporte
    @DeleteMapping("/{flightNumber}")
    public ResponseEntity<Void> deleteTransport(@PathVariable String flightNumber) {
        Optional<TransportModel> transport = transportRepository.findById(flightNumber);
        if (transport.isPresent()) {
            transportRepository.delete(transport.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
