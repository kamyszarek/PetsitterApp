package com.example.Petsitter_App.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String country;
    private String state;
    private String city;
    private String street;
    private String house_no;
    private String flat_no;

    @OneToOne(mappedBy = "address")
    private User user;


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private long id;
        private String country;
        private String state;
        private String city;
        private String street;
        private String house_no;
        private String flat_no;
        private User user;

        private Builder() {
        }


        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder house_no(String house_no) {
            this.house_no = house_no;
            return this;
        }

        public Builder flat_no(String flat_no) {
            this.flat_no = flat_no;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.setId(id);
            address.setCountry(country);
            address.setState(state);
            address.setCity(city);
            address.setStreet(street);
            address.setHouse_no(house_no);
            address.setFlat_no(flat_no);
            address.setUser(user);
            return address;
        }
    }
}

