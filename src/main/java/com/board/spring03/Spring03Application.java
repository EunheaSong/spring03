package com.board.spring03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class Spring03Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring03Application.class, args);
    }
        @PostConstruct
        public void started(){
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        }
}
