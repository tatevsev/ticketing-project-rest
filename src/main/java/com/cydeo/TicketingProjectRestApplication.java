package com.cydeo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketingProjectRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketingProjectRestApplication.class, args);
    }


    @Bean //ModelMapper is not our class therefore we using @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
