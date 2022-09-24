package com.example.Petsitter_App.service;

import com.example.Petsitter_App.model.Reservation;
import com.example.Petsitter_App.model.User;
import com.example.Petsitter_App.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;



@Service
@RequiredArgsConstructor
public class ReservationService {


    private final ReservationRepository reservationRepository;


        public List<Reservation> getAllByPetId(User user){
            return reservationRepository.findAllByPetId(user);
        }

        public List<Reservation> getAllFutureByOwnerId(User user){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 14);
            java.sql.Date oneWeek = new java.sql.Date(cal.getTimeInMillis());

            return reservationRepository.findAllFutureByOwnerId(user, oneWeek);
        }

        public void deleteoldReservations(Date threedays) {
            reservationRepository.deleteOld(threedays);
        }


        public List<Reservation> getAllByOwnerId(User u_owner) {
            return reservationRepository.findAllByOwnerId(u_owner);
        }

        public void deleteById(long delete_id) {
            reservationRepository.deleteResById(delete_id);
        }

    public Reservation getResById(long id) {
            return reservationRepository.getResById(id);
    }
}
