package com.tekarch.flights.TafFlightService.Service;

import com.tekarch.flights.TafFlightService.Model.Flight;
import com.tekarch.flights.TafFlightService.Service.Interface.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final String datastoreBaseUrl = "http://localhost:8081"; // Adjust based on taf-datastore-service URL

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Flight> getAllFlights() {
        return Arrays.asList(restTemplate.getForObject(datastoreBaseUrl + "/flights", Flight[].class));
    }

    @Override
    public Flight getFlightById(Long flightId) {
        return restTemplate.getForObject(datastoreBaseUrl + "/flights/" + flightId, Flight.class);
    }

    @Override
    public Flight addFlight(Flight flight) {
        return restTemplate.postForObject(datastoreBaseUrl + "/flights", flight, Flight.class);
    }

    @Override
    public Flight updateFlight(Long flightId, Flight flight) {
        restTemplate.put(datastoreBaseUrl + "/flights/" + flightId, flight);
        return flight;
    }

    @Override
    public void deleteFlight(Long flightId) {
        restTemplate.delete(datastoreBaseUrl + "/flights/" + flightId);
    }
}
