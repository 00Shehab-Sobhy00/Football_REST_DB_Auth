package com.example.FootballRest.SecurityConfiguration;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityUsersConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //user_id based in by login form :
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                // telling spring security this is
                // How we find the given user in our system based on user_name
                "SELECT user_id , pw , active FROM members WHERE user_id = ?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                // telling spring security this is
                // How we retrieve the authorities/Roles for given user_name
                "SELECT user_id , role FROM roles WHERE user_id = ? ");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.POST, "/leagues").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/referees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/teams").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/coaches").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/players").hasRole("COACH")
                        .requestMatchers(HttpMethod.POST, "/matches/start").hasRole("REFEREE")
                        .requestMatchers(HttpMethod.GET, "/matches/{match_id}").hasAnyRole
                                ("ADMIN", "MANAGER", "COACH", "REFEREE")
                        .anyRequest().permitAll()

        );

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults())

                // disable Cross Site Request Forgery (CSRF)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults());


        return http.build();
    }

    // constructor value pass
//    SerializationFeature.FAIL_ON_EMPTY_BEANS)
//   (through reference chain: com.example.FootballRest.model.entity.Match["referee"]-
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return builder;
    }


}


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }





















