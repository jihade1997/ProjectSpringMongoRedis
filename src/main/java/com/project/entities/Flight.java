package com.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "flight")
public class Flight implements Serializable {

    @Id
    @Field("_id")
    private String id;

    @DocumentReference
    @Field("flight")
    private FlightMaster flightMaster;

    @Field("origin")
    private String departureAirport;

    @Field("dest")
    private String arrivalAirport;

    @Field("time_hour")
    private String departureDateTime;
}
