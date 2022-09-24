package com.example.Petsitter_App.security;

import com.example.Petsitter_App.service.CustomUsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUsersDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder1() {
        return new BCryptPasswordEncoder();
    }

    @Bean public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder1());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/studentk1/*").hasAnyAuthority("STUDENTKL1", "ADMIN", "TEACHER")
//                .antMatchers("/student/*").hasAnyAuthority("STUDENT", "ADMIN")
                .antMatchers("/owner/*").hasAuthority("OWNER")
                .antMatchers("/", "/RegisterUserPage", "/RegisterUser", "/ActivatePage", "/ActivateUser").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("/Homepage")
                 .successHandler(authenticationSuccessHandler)
                .permitAll()
                //  .defaultSuccessUrl("/Homepage")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .logout().logoutSuccessUrl("/Homepage").permitAll();

    }

}
