package com.example.Petsitter_App.dto;

import com.example.Petsitter_App.model.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationDto {

    private long id;

    private String from;
    private String until;

    private String owner;
    private String petsitter;

    private String address;


}
