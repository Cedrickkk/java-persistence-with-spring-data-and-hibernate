package org.spring.data.springdatajpa;

import org.spring.data.springdatajpa.models.User;
import org.spring.data.springdatajpa.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner configure(UserRepository userRepository) {
        return env -> {
            User user = new User("ced", LocalDate.of(2025, Month.OCTOBER, 12));
            User _user = new User("hula@nm0", LocalDate.of(2025, Month.OCTOBER, 12));

            userRepository.save(user);
            userRepository.save(_user);

            userRepository.findAll().forEach(System.out::println);
        };
    }
}
