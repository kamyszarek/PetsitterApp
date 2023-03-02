package com.example.Petsitter_App.repository;

import com.example.Petsitter_App.model.Reservation;
import com.example.Petsitter_App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT u FROM reservation u WHERE petsitter_id = ?1")
    public List<Reservation> findAllByPetId(User user);

    @Query("SELECT u FROM reservation u WHERE owner_id = ?1")
    public List<Reservation> findAllByOwnerId(User user);

    @Query("SELECT u FROM reservation u WHERE petsitter_id = ?1 AND start_date < ?2")
    List<Reservation> findAllFutureByOwnerId(User user, @Param("date") Date date);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM Reservation WHERE end_date < ?1", nativeQuery = true)
    void deleteOld(@Param("date") java.sql.Date date);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM Reservation WHERE id = ?1", nativeQuery = true)
    void deleteResById(long delete_id);

    @Query("SELECT r FROM reservation r WHERE id = ?1")
    Reservation getResById(long id);
}
