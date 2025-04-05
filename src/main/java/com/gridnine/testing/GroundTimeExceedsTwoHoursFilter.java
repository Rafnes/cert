package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {
    private static final long MAX_GROUND_TIME_MINUTES = 120L;
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (!exceedsGroundTime(flight)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    private boolean exceedsGroundTime(Flight flight) {
        List<Segment> segments = flight.getSegments();
        if (segments.size() < 2) {
            return false;
        }
        long totalGroundMinutes = 0;
        for (int i = 1; i < segments.size(); i++) {
            LocalDateTime prevArrival = segments.get(i - 1).getArrivalDate();
            LocalDateTime nextDeparture = segments.get(i).getDepartureDate();
            totalGroundMinutes += Duration.between(prevArrival, nextDeparture).toMinutes();
        }
        return totalGroundMinutes > MAX_GROUND_TIME_MINUTES;
    }
}
