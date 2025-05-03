package com.project.SpringBootMangoDBRedis.repositories;

import com.project.entities.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlightRepository extends MongoRepository<Flight, String> {
    List<Flight> findAllByDepartureAirportAndArrivalAirport(String departureAirport, String arrivalAirport);
}
