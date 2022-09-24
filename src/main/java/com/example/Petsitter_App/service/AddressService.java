package com.example.Petsitter_App.service;

import com.example.Petsitter_App.model.Address;
import com.example.Petsitter_App.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    public List<Address> getByCity(String city) { return addressRepository.findAllByCity(city);}

    public void updateAddress(Address address) {
         addressRepository.updateAddress(address.getId(), address.getCountry(), address.getState(), address.getCity(),
                address.getStreet(), address.getHouse_no(), address.getFlat_no());
    }

    public boolean isAddressGood(Address address) {
        return !address.getCountry().equals("") && !address.getState().equals("") && !address.getCity().equals("") &&
                !address.getStreet().equals("") && !address.getHouse_no().equals("");

    }


}
