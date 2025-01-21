package com.tekarch.flights.TafFlightService.Controller;

import com.tekarch.flights.TafFlightService.Model.Flight;
import com.tekarch.flights.TafFlightService.Service.Interface.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights); // HTTP 200 with the list of flights
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) {
        Flight flight = flightService.getFlightById(flightId);
        if (flight != null) {
            return ResponseEntity.ok(flight); // HTTP 200 with the flight details
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404 if flight not found
        }
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.addFlight(flight);
        return ResponseEntity.status(201).body(createdFlight); // HTTP 201 Created
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(flightId, flight);
        if (updatedFlight != null) {
            return ResponseEntity.ok(updatedFlight); // HTTP 200 with updated flight details
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404 if flight not found
        }
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}

