package com.example.Petsitter_App.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String name;
    private String lastname;
    private String login;
    private String email;
    private String password;

    @Transient
    private String new_password;
    @Transient
    private String confirm_password;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(mappedBy = "user")
    private Reservation reservation;

    @OneToOne(mappedBy = "owner")
    private Reservation reservation1;


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private long user_id;
        private String name;
        private String lastname;
        private String login;
        private String email;
        private String password;
        private String new_password;
        private String confirm_password;
        private boolean enabled;
        private Set<Role> roles = new HashSet<>();

        private Builder() {
        }


        public Builder user_id(long user_id) {
            this.user_id = user_id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder new_password(String new_password) {
            this.new_password = new_password;
            return this;
        }

        public Builder confirm_password(String confirm_password) {
            this.confirm_password = confirm_password;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            User user = new User();
            user.setUser_id(user_id);
            user.setName(name);
            user.setLastname(lastname);
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(password);
            user.setNew_password(new_password);
            user.setConfirm_password(confirm_password);
            user.setEnabled(enabled);
            user.setRoles(roles);
            return user;
        }
    }
}
