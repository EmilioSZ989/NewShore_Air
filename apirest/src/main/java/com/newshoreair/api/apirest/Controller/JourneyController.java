package com.newshoreair.api.apirest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.newshoreair.api.apirest.Model.FlightModel;
import com.newshoreair.api.apirest.Model.JourneyModel;
import com.newshoreair.api.apirest.Repository.FlightRepository;
import com.newshoreair.api.apirest.Repository.JourneyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journeys")
@CrossOrigin(origins = "http://localhost:4200/")
public class JourneyController {

	@Autowired
	private JourneyRepository journeyRepository;

	@Autowired
	private FlightRepository flightRepository; // Asegúrate de tener un repositorio para FlightModel

	// Endpoint para obtener la ruta de vuelo
	@GetMapping("/route")
	public ResponseEntity<JourneyModel> getFlightRoute(@RequestParam String origin, @RequestParam String destination) {
		List<FlightModel> flightRoute = findFlightRoute(origin, destination);
		if (flightRoute.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		double totalPrice = calculateTotalPrice(flightRoute);
		JourneyModel journey = new JourneyModel();
		journey.setOrigin(origin);
		journey.setDestination(destination);
		journey.setFlights(flightRoute);
		journey.setPrice(totalPrice);

		// Guardar el Journey en la base de datos
		JourneyModel savedJourney = journeyRepository.save(journey);

		// Devolver el Journey con el ID actualizado
		return new ResponseEntity<>(savedJourney, HttpStatus.OK);
	}

	// Método para encontrar la ruta de vuelo (directa o indirecta)
	private List<FlightModel> findFlightRoute(String origin, String destination) {
		List<FlightModel> route = new ArrayList<>();
		findFlightRouteHelper(origin, destination, new ArrayList<>(), route);
		return route;
	}

	// Método auxiliar recursivo para encontrar la ruta de vuelo
	private boolean findFlightRouteHelper(String currentOrigin, String destination, List<String> visited,
			List<FlightModel> route) {
		if (currentOrigin.equals(destination)) {
			return true; // Llegamos al destino
		}

		visited.add(currentOrigin); // Marcamos el origen como visitado

		List<FlightModel> flights = flightRepository.findByOrigin(currentOrigin);
		for (FlightModel flight : flights) {
			if (!visited.contains(flight.getDestination())) {
				route.add(flight); // Agregamos el vuelo a la ruta
				if (findFlightRouteHelper(flight.getDestination(), destination, visited, route)) {
					return true; // Se encontró una ruta
				}
				route.remove(flight); // Retrocedemos y eliminamos el vuelo de la ruta
			}
		}

		return false; // No se encontró ninguna ruta
	}

	// Método para calcular el precio total de la ruta
	private double calculateTotalPrice(List<FlightModel> flights) {
		double totalPrice = 0.0;
		for (FlightModel flight : flights) {
			totalPrice += flight.getPrice();
		}
		return totalPrice;
	}

	// Obtener todos los viajes
	@GetMapping
	public ResponseEntity<List<JourneyModel>> getAllJourneys() {
		List<JourneyModel> journeys = journeyRepository.findAll();
		return new ResponseEntity<>(journeys, HttpStatus.OK);
	}

	// Obtener un viaje por su ID
	@GetMapping("/{id}")
	public ResponseEntity<JourneyModel> getJourneyById(@PathVariable Long id) {
		Optional<JourneyModel> journey = journeyRepository.findById(id);
		if (journey.isPresent()) {
			return new ResponseEntity<>(journey.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Crear un nuevo viaje
	@PostMapping
	public ResponseEntity<JourneyModel> createJourney(@RequestBody JourneyModel journey) {
		JourneyModel createdJourney = journeyRepository.save(journey);
		return new ResponseEntity<>(createdJourney, HttpStatus.CREATED);
	}

	// Actualizar un viaje existente
	@PutMapping("/{id}")
	public ResponseEntity<JourneyModel> updateJourney(@PathVariable Long id, @RequestBody JourneyModel journey) {
		Optional<JourneyModel> journeyOptional = journeyRepository.findById(id);
		if (journeyOptional.isPresent()) {
			journey.setId(id);
			JourneyModel updatedJourney = journeyRepository.save(journey);
			return new ResponseEntity<>(updatedJourney, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Eliminar un viaje por su ID
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteJourney(@PathVariable Long id) {
		try {
			journeyRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
