package com.example.Petsitter_App.service;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.Petsitter_App.model.Reservation;
import com.example.Petsitter_App.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatesService {

    private final ReservationService reservationService;

    public long calculateDateInterval(Date startDate, Date endDate) {
        return ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
    }

    public boolean isDateRight(LocalDateTime startDate, LocalDateTime endDate, User user){

        if(endDate.isBefore(startDate) || startDate.isBefore(LocalDateTime.now())){
            return false;
        }
        else{
            List<Reservation> reservations = reservationService.getAllByPetId(user);
            for(Reservation res:reservations){
                if(startDate.isAfter(res.getStartDate()) && startDate.isBefore(res.getEndDate()) ||
                   endDate.isAfter(res.getStartDate()) && endDate.isBefore(res.getEndDate())){
                    return false;
                }
            }
            return true;
        }

    }


    public boolean isDateRight1(Date startDate, Date endDate, String startHour, String stopHour) {
        Date today = new Date();
        if(calculateDateInterval(today, startDate )<1){
            return false;
        }

        long x = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
        if(x<0){return false;}

        String[] parts1 = startHour.split(":");
        String sHour = parts1[0];
        int x1 = Integer.parseInt(sHour);

        String[] parts2 = stopHour.split(":");
        String stHour = parts2[0];
        int x2 = Integer.parseInt(stHour);

        if(x2-x1<1){
            return x > 0;
        } else{
            return true;
        }
    }

    public List<String> hourList(){
        List<String>hours = new ArrayList<>();
        for(int i=0; i<24; i++){
            if(i<10) hours.add("0"+ String.valueOf(i) +":00");
            else hours.add(String.valueOf(i) +":00");
        }
        return hours;
    }


}

