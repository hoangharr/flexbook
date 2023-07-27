package com.example.flexbook.util;

import com.example.flexbook.models.Message;
import com.example.flexbook.models.Post;
import com.example.flexbook.models.User;
import com.example.flexbook.repository.MessRepo;
import com.example.flexbook.repository.PostRepo;
import com.example.flexbook.repository.UserRepo;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
import java.util.*;

@Configuration
public class DataFaker implements CommandLineRunner {
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final MessRepo messRepo;
    private final Faker faker;

    @Autowired
    public DataFaker(UserRepo userRepo, PostRepo postRepo, MessRepo messRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.messRepo = messRepo;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) {
        int numUsers = 30;
        List<User> users = new ArrayList<>();

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
            users.add(user);

            int numPosts = 10;
            for (int j = 0; j < numPosts; j++) {
                Post post = new Post();
                post.setUser(user);
                post.setContent(faker.lorem().paragraph());
                post.setPost_image(faker.internet().image());
                post.setCreated_at(faker.date().past(5, TimeUnit.DAYS));
                post.setUpdated_at(faker.date().past(1, TimeUnit.DAYS));
                postRepo.save(post);
            }
        }
        int numMessages = 50;
        for (int i = 0; i < numMessages; i++) {
            User sender = users.get(faker.random().nextInt(users.size()));
            User receiver = users.get(faker.random().nextInt(users.size()));
            while (receiver == sender) {
                receiver = users.get(faker.random().nextInt(users.size()));
            }

            Message message = new Message();
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setMessage(faker.lorem().sentence());
            message.setCreated_at(faker.date().past(5, TimeUnit.DAYS));
            message.setUpdated_at(faker.date().past(1, TimeUnit.DAYS));
            messRepo.save(message);
        }
    }
}
