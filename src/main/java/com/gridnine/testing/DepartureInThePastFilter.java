package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepartureInThePastFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Flight flight : flights) {
            if (!hasDepartureInThePast(flight, now)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    private boolean hasDepartureInThePast(Flight flight, LocalDateTime now) {
        List<Segment> segments = flight.getSegments();
        for (Segment segment : segments) {
            if (segment.getDepartureDate().isBefore(now)) {
                return true;
            }
        }
        return false;
    }
}
