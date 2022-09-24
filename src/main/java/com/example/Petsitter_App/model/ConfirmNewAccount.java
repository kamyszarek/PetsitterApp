package com.example.Petsitter_App.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ConfirmNewAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String u_email;
    Integer code;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        long id;
        String u_email;
        Integer code;

        private Builder() {
        }


        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder u_email(String u_email) {
            this.u_email = u_email;
            return this;
        }

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public ConfirmNewAccount build() {
            ConfirmNewAccount confirmNewAccount = new ConfirmNewAccount();
            confirmNewAccount.setId(id);
            confirmNewAccount.setU_email(u_email);
            confirmNewAccount.setCode(code);
            return confirmNewAccount;
        }
    }
}
