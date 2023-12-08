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
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");

        TripEntity tripEntity = new TripEntity();
        tripEntity.setPickUpLocation(addTripDTO.getPickUpLocation());
        tripEntity.setDropOffLocation(addTripDTO.getDropOffLocation());
        tripEntity.setPickUpDate(LocalDate.parse(addTripDTO.getPickUpDate(),dateFormatter));
        tripEntity.setDropOffDate(LocalDate.parse(addTripDTO.getDropOffDate(), dateFormatter));
        tripEntity.setPickUpTime(LocalTime.parse(addTripDTO.getPickUpTime().toUpperCase(),  timeFormatter));

        return tripEntity;
    }
}
