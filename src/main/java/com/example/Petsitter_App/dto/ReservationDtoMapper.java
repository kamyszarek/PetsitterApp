package com.example.Petsitter_App.dto;

import com.example.Petsitter_App.model.Reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationDtoMapper {
    
    public ReservationDtoMapper() {
        
    }
    
    public static List<ReservationDto> mapToReservationDtos(List<Reservation> reservations) {
        reservations.sort(Comparator.comparing(Reservation::getStartDate));

        return reservations.stream()
                .map(reservation -> mapToReservationDto(reservation))
                .collect(Collectors.toList());
    }

    private static ReservationDto mapToReservationDto(Reservation reservation) {
        String sdate = String.valueOf(reservation.getStartDate());
        String edate = String.valueOf(reservation.getEndDate());


        return ReservationDto.builder()
                .id(reservation.getId())
                .from(sdate.substring(0,10)+", " + sdate.substring(11))
                .until(edate.substring(0,10)+", " + edate.substring(11))
                .owner((reservation.getOwner().getName()+ " " + reservation.getOwner().getLastname()))
                .petsitter((reservation.getUser().getName()+ " " + reservation.getUser().getLastname()))
                .address(reservation.getOwner().getAddress().getCity() + ", "+
                        reservation.getOwner().getAddress().getStreet() + ", "+
                        reservation.getOwner().getAddress().getHouse_no() + "/" +
                        reservation.getOwner().getAddress().getFlat_no())
                .build();
    }


}
