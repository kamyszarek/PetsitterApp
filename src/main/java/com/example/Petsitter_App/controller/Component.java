package com.example.Petsitter_App.controller;

import com.example.Petsitter_App.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;

@org.springframework.stereotype.Component
@RequiredArgsConstructor
public class Component {

    @Autowired
    private final ReservationService reservationService;

    @Scheduled(cron = "0 0 */1 * * *")
    public void deleteOldTests() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);

        java.sql.Date threedays = new java.sql.Date(cal.getTimeInMillis());

        reservationService.deleteoldReservations(threedays);
    }


}
