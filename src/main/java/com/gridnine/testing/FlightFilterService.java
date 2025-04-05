package com.gridnine.testing;

import java.util.List;

public class FlightFilterService {
    private List<FlightFilter> filters;

    public FlightFilterService(List<FlightFilter> filters) {
        this.filters = filters;
    }

    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> filteredFlights = flights;
        for (FlightFilter filter : filters) {
            filteredFlights = filter.filter(filteredFlights);
        }
        return filteredFlights;
    }
}
