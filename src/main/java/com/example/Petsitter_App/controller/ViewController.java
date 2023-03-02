package com.example.Petsitter_App.controller;

import com.example.Petsitter_App.dto.ReservationDto;
import com.example.Petsitter_App.dto.ReservationDtoMapper;
import com.example.Petsitter_App.model.*;
import com.example.Petsitter_App.repository.*;
import com.example.Petsitter_App.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class ViewController {

    @Autowired
    private DatesService datesService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    Users_RolesRepository users_rolesRepository;
    @Autowired
    CNARepository cnaRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ReservationRepository reservationRepository;


    @Autowired
    private final UserService userService;
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final ReservationService reservationService;

    @Autowired
    private final MailService mailService;


    @RequestMapping("/dates")
    public String getDates(Reservation reservation, Model model){
        List<String>hours = new ArrayList<>();
        for(int i=0; i<24; i++){
            hours.add(String.valueOf(i) +":00");
        }
        User user = new User();
        model.addAttribute("options", hours);
        model.addAttribute("user", user);

        return "dates";
    }




    @RequestMapping("/")
    public String getHomepage() {return "homepage";}

    @RequestMapping("/Homepage")
    public String getHomePage() {return "homepage";}

    @RequestMapping("/HomepageLogged")
    public String getHomePageLogged() {return "homepage_logged";}

    @RequestMapping("/Logging")
    public String getLogging() {return "login";}


    //Registration
    @RequestMapping("/RegisterUserPage")
    public String RegisterUserPage(Model model){
            List<String> options = new ArrayList<>();
            options.add("PETSITTER");
            options.add("OWNER");
            model.addAttribute("options", options);
            User user = new User(); model.addAttribute("user", user);
            Address address = new Address(); model.addAttribute("address", address);

            return "registeruser";

    }

    @PostMapping("/RegisterUser1")
    public String RegisterUser1(@ModelAttribute(value="user") User user , @ModelAttribute(value="address") Address address, @ModelAttribute("options") String option, Model model) {

        boolean right_login = true;
        boolean right_fields = true;
        List<User> users = userService.getUsers();
        for(User x:users) {
            if(x.getEmail().equals(user.getEmail()) && x.getLogin().equals(user.getLogin())){
                model.addAttribute("hint_login", "Użytkownik o podanym e-mailu i loginie już istnieje!");
                right_login = false;
                break;
            }
            else if(x.getEmail().equals(user.getEmail())){
                model.addAttribute("hint_login", "Użytkownik o podanym e-mailu już istnieje!");
                right_login = false;
                break;
            }
            else if(x.getLogin().equals(user.getLogin())){
                model.addAttribute("hint_login", "Użytkownik o podanym loginie już istnieje!");
                right_login = false;
                break;
            }
        }
        if(user.getName().equals("") || user.getLastname().equals("") || user.getLogin().equals("") || user.getPassword().equals("")
                || user.getEmail().equals("")) {
            model.addAttribute("hint_fields", "Wypełnij wszystkie pola!");
            right_fields = false;
        }
        if(right_login && right_fields) {
            if(user.getLogin().length()<4 || user.getPassword().length()<4){
                model.addAttribute("hint_fields", "Login and password must have at least 4 signs!");
                return "registeruser";
            } else{
                model = RegisterUserM(model, option, user, address);
                return "activateaccount";
            }
        } else {
            return "registeruser";
        }

    }

    @PostMapping("/RegisterUser")
    public String RegisterUser(@ModelAttribute(value="user") User user , @ModelAttribute(value="address") Address address, @ModelAttribute("options") String option, Model model) {
        if(addressService.isAddressGood(address) && userService.isUserGood(user)){
            if(userService.isLoginFree(user)){
                if(userService.isLongEnough(user)){
                    model = RegisterUserM(model, option, user, address);
                    return "activateaccount";
                }else{
                    model.addAttribute("hint_fields", "Login and password must have at least 4 signs!");
                    return "registeruser";
                }
            }else{
                model.addAttribute("hint_fields", "User with passed mail or password already exist!");
                return "registeruser";
            }
        } else{
            model.addAttribute("hint_fields", "Fill all required fields!");
            return "registeruser";
        }

    }



    @PostMapping("/ActivateUser")
    public String activateUser(@ModelAttribute(value="cna") ConfirmNewAccount cna, Model model) {

        if(cna.getCode()==null){
            model.addAttribute("wrongcode", "Wrong code!");  return "activateaccount";
        }
        User user = userRepository.findByEmail(cna.getU_email());
        ConfirmNewAccount cna2 = cnaRepository.findByEmail(cna.getU_email());

        if(cna.getCode().equals(cna2.getCode()))
        {
            userRepository.activateUser(true, user.getEmail()); return "activatesuccess";
        }
        else{
            model.addAttribute("wrongcode", "Wrong code!"); return "activateaccount";
        }

    }


    @RequestMapping("/owner/findPetsitterPage")
    public String findPetsitterPage(Model model){
        List<String>cities = new ArrayList<>();
        List<Address>adresses = addressRepository.findAll();
        for(Address x:adresses) {
            if(!cities.contains(x.getCity())) {cities.add(x.getCity());}
        }

        model.addAttribute("cities", cities);
        return "findpetsitter";
    }

    @RequestMapping("/owner/PetsittersList")
    public String showPetsittersList(@ModelAttribute("options") String option, Model model){
        List<Address>addresses = addressService.getByCity(option);
        List<Long>ids = new ArrayList<>();
        List<User> petsitters = new ArrayList<>();
        for(Address x:addresses){
            ids.add(x.getId());
        }
        for(Long y:ids){
            long u_id = userRepository.findUserByAddressId(y).getUser_id();
            if(users_rolesRepository.getRolesByUserId(u_id).getRole_id()==2){
                petsitters.add(userRepository.findUserByAddressId(y));
            }
        }
        model.addAttribute("petsitters", petsitters);

        return "petsitters_list";
    }

    @PostMapping("/owner/PetsitterInfo")
    public String PetsitterInfo(@ModelAttribute("options") String option, Model model) {
        String[] parts = option.split(",");
        String id = parts[1];
        String id2 = id.substring(1);
        User user =  userRepository.findUserById(Long.parseLong(id2));
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());

        List<Reservation> reservations = reservationService.getAllFutureByOwnerId(user);
        List<ReservationDto> reservationDtos = ReservationDtoMapper.mapToReservationDtos(reservations);

        model.addAttribute("reservations", reservationDtos);

        return "petsitter_info";
    }


    @PostMapping("/owner/petsitterReservationPage")
    public String petsitterReservationPage(@ModelAttribute("user") User user, Model model){
        List<String>hours = datesService.hourList();

        User user1 = userRepository.findUserById(user.getUser_id());
        Reservation reservation1 = new Reservation();
        model.addAttribute("options", hours);
        model.addAttribute("user", user1);
        model.addAttribute("reservation", reservation1);

        List<Reservation> reservations = reservationService.getAllFutureByOwnerId(user1);
        List<ReservationDto> reservationDtos = ReservationDtoMapper.mapToReservationDtos(reservations);
        model.addAttribute("reservations", reservationDtos);

        return "petsitter_reservation";
    }


    @PostMapping("/owner/makeReservation")
    public String petsitterReservation(@ModelAttribute("reservation") Reservation reservation, Model model, @ModelAttribute("user") User user,
                                    @ModelAttribute("options1") String option1, @ModelAttribute("options2") String option2) throws ParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String s1 = reservation.getSfrom() + " " + option1;
        String s2 = reservation.getSuntil() + " " + option2;
        LocalDateTime startDate = LocalDateTime.parse(s1, formatter);
        LocalDateTime endDate = LocalDateTime.parse(s2, formatter);


        if(!datesService.isDateRight(startDate, endDate, user)){
            model.addAttribute("wrong_dates", "Wrong date given!");
            model.addAttribute("options", datesService.hourList());
            List<Reservation> reservations = reservationService.getAllFutureByOwnerId(user);
            List<ReservationDto> reservationDtos = ReservationDtoMapper.mapToReservationDtos(reservations);
            model.addAttribute("reservations", reservationDtos);
            return "petsitter_reservation";

        } else{
        User owner = userService.getAuth();
        Reservation reservation2 = Reservation.builder()
                .startDate(LocalDateTime.parse(s1, formatter))
                .endDate(LocalDateTime.parse(s2, formatter))
                .owner(owner)
                .user(userRepository.findUserById(user.getUser_id()))
                .build();
        reservationRepository.save(reservation2);
            return "homepage_logged";
        }



    }


    @RequestMapping("/pet/myReservations")
    public String getPetReservations(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User owner = userService.getByLogin(auth.getName());
        long owner_id = owner.getUser_id();
        User u_owner = userRepository.findUserById(owner_id);

        List<Reservation> reservations = reservationService.getAllByPetId(u_owner);
        List<ReservationDto> reservationDtos = ReservationDtoMapper.mapToReservationDtos(reservations);

        model.addAttribute("reservations", reservationDtos);


        return "pet_myreservations";
    }

    @RequestMapping("/owner/deleteResPage")
    public String deleteResPage(Model model){
        User u_owner = userService.getAuth();
        List<Reservation> reservations = reservationService.getAllByOwnerId(u_owner);
        List<ReservationDto> reservationDtos = ReservationDtoMapper.mapToReservationDtos(reservations);
        model.addAttribute("reservations", reservationDtos);

        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);


        return "owner_delete_res";
    }

    @PostMapping("/owner/deleteRes")
    public String deleteRes(Model model, @ModelAttribute("reservation") Reservation reservation){
        User owner = userService.getAuth();
        Reservation reservation1 = reservationService.getResById(reservation.getId());
        if(owner.equals(reservation1.getOwner())){
            reservationService.deleteById(reservation.getId());
        } else{
            model.addAttribute("hint", "Wrong number given");
        }

        List<Reservation> reservations = reservationService.getAllByOwnerId(owner);
        List<ReservationDto> reservationDtos = ReservationDtoMapper.mapToReservationDtos(reservations);
        model.addAttribute("reservations", reservationDtos);
        model.addAttribute("reservation", reservation);
        return "owner_delete_res";
    }


    @RequestMapping("/user/editMyAddressPage")
    public String editMyAddressPage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(auth.getName());
        Address address = user.getAddress();
        model.addAttribute("address", address);

        return "edit_address";
    }

    @PostMapping("/user/editMyAddress")
    public String editMyAddress(@ModelAttribute("address") Address address, Model model){
        if(addressService.isAddressGood(address)){
            addressService.updateAddress(address);
            model.addAttribute("success", "Address updated!");
        } else{
            model.addAttribute("address", address);
            model.addAttribute("hint_fields", "Fill all required fields!");
        }
        return "edit_address";
    }

    @RequestMapping("/user/changePasswordPage")
    public String changePasswordPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "change_password";
    }

    @PostMapping("/user/changePassword")
    public String changePassword(@ModelAttribute("user") User user, Model model){
        model.addAttribute("hint", userService.changePassword(user));
        return "change_password";
    }












    //Rejestracja Usera
    private Model RegisterUserM(Model model, String option, User user, Address address){

        Address address1 = Address.builder()
                .country(address.getCountry())
                .state(address.getState())
                .city(address.getCity())
                .street(address.getStreet())
                .house_no(address.getHouse_no())
                .flat_no(address.getFlat_no())
                .build();
        addressRepository.save(address1);

        User user1 = User.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .login(user.getLogin())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .enabled(false)
                .build();
        user1.setAddress(address1);
        long u_id = userRepository.save(user1).getUser_id();

        ConfirmNewAccount cna = new ConfirmNewAccount();
        cna.setU_email(user1.getEmail());
        model.addAttribute("cna", cna);

        ConfirmNewAccount cna2 = new ConfirmNewAccount();
        cna2.setU_email(cna.getU_email());
        Random ran = new Random();
        int code = ran.nextInt(900000) + 100000;
        cna2.setCode(code);
        cnaRepository.save(cna2);

        new Thread(() -> {
            mailService.sendSimpleEmail(user1.getName() + " "+ "<"+user1.getEmail()+">", "Aktywuj konto", "Activation code: "+ code);
        }).start();

        Users_roles users_roles = new Users_roles();
        users_roles.setUser_id(u_id);

        switch (option) {
            case "PETSITTER":
                users_roles.setRole_id(2);
                break;
            case "OWNER":
                users_roles.setRole_id(3);
                break;

        }
        users_rolesRepository.save(users_roles);

        model.addAttribute("success", "Użytkownik pomyślnie zarejestrowany!");

        return model;
    }




}
