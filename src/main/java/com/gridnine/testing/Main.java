package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        //Изначальный список полетов
        flights.forEach(System.out::println);
        System.out.println();

        //Фильтр для полетов с отправлением из прошлого
        FlightFilterService departureInThePastFilterService = new FlightFilterService(List.of(new DepartureInThePastFilter()));
        System.out.println("Полеты без отправлений из прошлого:");
        List<Flight> filteredFlights = departureInThePastFilterService.filterFlights(flights);
        filteredFlights.forEach(System.out::println);
        System.out.println();

        //Фильтр для сегментов, где дата прилета раньше даты вылета
        FlightFilterService arrivalBeforeDepartureFilterService = new FlightFilterService(List.of(new ArrivalBeforeDepartureFilter()));
        System.out.println("Полеты без сегментов, где дата прилета раньше даты вылета:");
        filteredFlights = arrivalBeforeDepartureFilterService.filterFlights(filteredFlights);
        filteredFlights.forEach(System.out::println);
        System.out.println();

        //Фильтр для полетов с временем на земле меньше 2 часов
        FlightFilterService groundTimeFilterService = new FlightFilterService(List.of(new GroundTimeExceedsTwoHoursFilter()));
        System.out.println("Полеты с временем на земле менее 2 часов:");
        filteredFlights = groundTimeFilterService.filterFlights(filteredFlights);
        filteredFlights.forEach(System.out::println);
    }
}