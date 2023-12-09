package com.example.carbook.service.impl;

import com.example.carbook.model.dto.AddTripDTO;
import com.example.carbook.model.entity.CarEntity;
import com.example.carbook.model.entity.TripEntity;
import com.example.carbook.repo.TripRepository;
import com.example.carbook.service.TripService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public void createTrip(AddTripDTO addTripDTO) {

        TripEntity tripEntity = map(addTripDTO);


        tripRepository.save(tripEntity);
    }

    private TripEntity map(AddTripDTO addTripDTO) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter dateFormatter1 = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("M/dd/yyyy");
        DateTimeFormatter dateFormatter3 = DateTimeFormatter.ofPattern("MM/d/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        DateTimeFormatter timeFormatter2 = DateTimeFormatter.ofPattern("h:mma");

        TripEntity tripEntity = new TripEntity();
        tripEntity.setPickUpLocation(addTripDTO.getPickUpLocation());
        tripEntity.setDropOffLocation(addTripDTO.getDropOffLocation());
        tripEntity.setPickUpDate(parseDate(addTripDTO.getPickUpDate(),dateFormatter,dateFormatter1,dateFormatter2,dateFormatter3));
        tripEntity.setDropOffDate(parseDate(addTripDTO.getDropOffDate(),dateFormatter,dateFormatter1,dateFormatter2,dateFormatter3));
        tripEntity.setPickUpTime(parsePickUpTime(addTripDTO.getPickUpTime(), timeFormatter, timeFormatter2));

        return tripEntity;
    }
    private LocalTime parsePickUpTime(String pickUpTime, DateTimeFormatter... formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalTime.parse(pickUpTime.toUpperCase(), formatter);
            } catch (DateTimeParseException e) {
                // Ignore and try the next formatter
            }
        }

        throw new IllegalArgumentException("Unable to parse pickUpTime: " + pickUpTime);
    }
    private LocalDate parseDate(String date, DateTimeFormatter... formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // Ignore and try the next formatter
            }
        }

        throw new IllegalArgumentException("Unable to parse date: " + date);
    }

}
