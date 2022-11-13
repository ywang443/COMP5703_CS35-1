package com.ezeat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ezeat.mapper")
@SpringBootApplication
public class ReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationSystemApplication.class, args);
    }

}
