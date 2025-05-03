package com.project.SpringBootMangoDBRedis.services;

import com.project.entities.Flight;
import com.project.SpringBootMangoDBRedis.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    @Cacheable(value = "flightsCache", key = "#source + '_' + #destination")
    public List<Flight> getAllFlights(String source, String destination) {
        return this.flightRepository.findAllByDepartureAirportAndArrivalAirport(source, destination);
    }


    // Get flight by ID and cache it
    @Cacheable(value = "flights", key = "#id")
    public Optional<Flight> getFlightById(String id) {
        return flightRepository.findById(id);
    }

    // Get all flights (optional cache)
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Create or update flight, update cache
    @CachePut(value = "flights", key = "#flight.id")
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Delete flight and remove from cache
    @CacheEvict(value = "flights", key = "#id")
    public void deleteflight(String id) {
        flightRepository.deleteById(id);
    }
}