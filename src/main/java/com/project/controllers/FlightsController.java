package com.project.controllers;

import com.project.entities.Flight;
import com.project.SpringBootMangoDBRedis.services.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightsController {

    private final FlightService flightService;

    public FlightsController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping("/hello")
    public String HelloMethod(){
        return "Hello World !";
    }
    @GetMapping("/flights")
    public List<Flight> getAllActiveFlightAccessesByFlightId(@RequestParam String source, @RequestParam String destination) {
        return flightService.getAllFlights(source, destination);
    }
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable String id) {
        return flightService.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight Flight) {
        return flightService.saveFlight(Flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String id, @RequestBody Flight updatedFlight) {
        return flightService.getFlightById(id).map(existingFlight -> {
            existingFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            existingFlight.setDepartureDateTime(updatedFlight.getDepartureDateTime());
            existingFlight.setFlightMaster(updatedFlight.getFlightMaster());
            return ResponseEntity.ok(flightService.saveFlight(existingFlight));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String id) {
        flightService.deleteflight(id);
        return ResponseEntity.noContent().build();
    }
}