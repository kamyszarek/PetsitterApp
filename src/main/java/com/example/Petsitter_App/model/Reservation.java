package com.example.Petsitter_App.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "reservation")
@Getter
@Setter
public class Reservation {
    //private static final String AU_DATE_FORMAT = "dd-MM-yyyy";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Transient
    private String sfrom;
    @Transient
    private String suntil;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "petsitter_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User owner;


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private long id;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String sfrom;
        private String suntil;
        private User user;
        private User owner;

        private Builder() {
        }

        public static Builder aReservation() {
            return new Builder();
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder sfrom(String sfrom) {
            this.sfrom = sfrom;
            return this;
        }

        public Builder suntil(String suntil) {
            this.suntil = suntil;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder owner(User owner) {
            this.owner = owner;
            return this;
        }

        public Reservation build() {
            Reservation reservation = new Reservation();
            reservation.setId(id);
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservation.setSfrom(sfrom);
            reservation.setSuntil(suntil);
            reservation.setUser(user);
            reservation.setOwner(owner);
            return reservation;
        }
    }
}
