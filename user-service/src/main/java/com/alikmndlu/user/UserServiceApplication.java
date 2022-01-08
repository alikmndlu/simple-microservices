package com.alikmndlu.user;

import com.alikmndlu.user.model.User;
import com.alikmndlu.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserService userService
    ) {
        return args -> {
            userService.saveUser(
                    User.builder()
                            .firstName("Ali")
                            .lastName("Kmndlu")
                            .emailAddress("alikmndlu1@gmail.com")
                            .password("123")
                            .build()
            );
        };
    }
}
