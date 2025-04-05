package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class ArrivalBeforeDepartureFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            boolean isValid = true;
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}
