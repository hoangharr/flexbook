package com.example.flexbook.util;

import com.example.flexbook.models.User;
import com.example.flexbook.repository.UserRepo;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class UserFaker implements CommandLineRunner {
    private final UserRepo userRepo;
    private final Faker faker;

    @Autowired
    public UserFaker(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        int numUsers = 30;
        for (int i = 0; i < numUsers; i++) {
            User user = new User();
            user.setUsername(faker.name().username());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setProfile_picture(faker.internet().image());
            user.setBio(faker.lorem().sentence());
            user.setCreated_at(faker.date().past(5, TimeUnit.DAYS));
            user.setUpdated_at(faker.date().past(1, TimeUnit.DAYS));
            userRepo.save(user);
        }
    }
}
