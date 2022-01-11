package com.jpa.jpa;

import com.jpa.jpa.model.User;
import com.jpa.jpa.repository.UserRepository;
import com.jpa.jpa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, UserService userService) {
        return (args -> {
            userService.getUserByEmail("test","motdepasse");
            userRepository.save(new User("Jack","Toto","titi","password"));
            userRepository.save(new User("Jack","Toto","titi","password"));
            userRepository.save(new User("David","Guetta","titi","password"));
            userRepository.save(new User("email","Toto","toto@titi.com","password"));

            for (User user: userRepository.findAll()) {
                System.out.println(user.toString());
            }
            System.out.println("");

            User user = userRepository.findUserById(1L);
            System.out.println(user);
            System.out.println("");

            User david = userRepository.findByFirstname("David");
            System.out.println("Voici David :" + david.toString());
            System.out.println("");

            Optional<User> email = userRepository.getUserByEmail("toto@titi.com");
            if (email.isPresent()) {
                User emailDb = email.get();
                System.out.println(emailDb.toString());
                System.out.println("");
            }


        });
    }

}
