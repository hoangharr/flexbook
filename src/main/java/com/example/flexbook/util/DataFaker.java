package com.example.flexbook.util;

import com.example.flexbook.models.*;
import com.example.flexbook.repository.*;
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
    private final LikeRepo likeRepo;
    private final FriendRepo friendRepo;
    private final CommentRepo commentRepo;
    private final Faker faker;
    private List<User> users;
    private List<Post> posts;

    @Autowired
    public DataFaker(UserRepo userRepo, PostRepo postRepo, MessRepo messRepo, LikeRepo likeRepo, FriendRepo friendRepo, CommentRepo commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.messRepo = messRepo;
        this.likeRepo = likeRepo;
        this.friendRepo = friendRepo;
        this.commentRepo = commentRepo;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) {
        users = userRepo.findAll();
        posts = postRepo.findAll();
        generateUsers();
        generatePosts();
        generateMessages();
        generateLikes();
        generateComments();
        generateFriends();
    }
    private <T> T getRandomElement(List<T> list) {
        return list.get(faker.random().nextInt(list.size()));
    }
    private void generateUsers() {
        int numUsers = 10;
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

    private void generatePosts() {
        for (User user : users) {
            int numPosts = 10;
            for (int i = 0; i < numPosts; i++) {
                Post post = new Post();
                post.setUser(user);
                post.setContent(faker.lorem().sentence());
                post.setPost_image(faker.internet().image());
                post.setCreated_at(faker.date().past(5, TimeUnit.DAYS));
                post.setUpdated_at(faker.date().past(1, TimeUnit.DAYS));
                postRepo.save(post);
            }
        }
    }

    private void generateMessages() {
        if (users.isEmpty()) {
            return;
        }
        int numMessages = 10;
        for (int i = 0; i < numMessages; i++) {
            User sender = getRandomElement(users);
            User receiver = getRandomElement(users);
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

    private void generateLikes() {
        if (users.isEmpty() || posts.isEmpty()) {
            return;
        }
        int numLikes = 10;
        for (int i = 0; i < numLikes; i++) {
            User user = getRandomElement(users);
            Post post = getRandomElement(posts);
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            like.setCreated_at(faker.date().past(10, TimeUnit.DAYS));
            likeRepo.save(like);
        }
    }

    private void generateComments() {
        if (users.isEmpty() || posts.isEmpty()) {
            return;
        }
        int numComments = 10;
        for (int i = 0; i < numComments; i++) {
            User user = getRandomElement(users);
            Post post = getRandomElement(posts);

            Comment comment = new Comment();
            comment.setUser(user);
            comment.setPost(post);
            comment.setComment(faker.lorem().sentence());
            comment.setCreated_at(faker.date().past(10, TimeUnit.DAYS));
            commentRepo.save(comment);
        }
    }

    private void generateFriends() {
        if (users.isEmpty()) {
            return;
        }
        int numFriends = 10;
        for (int i = 0; i < numFriends; i++) {
            User user1 = getRandomElement(users);
            User user2 = getRandomElement(users);
            while (user1 == user2) {
                user2 = getRandomElement(users);
            }
            Friend friend = new Friend();
            friend.setUser1(user1);
            friend.setUser2(user2);
            friend.setStatus(Status.values()[faker.random().nextInt(Status.values().length)]);
            friend.setActionUser(user1);
            friend.setCreated_at(faker.date().past(10, TimeUnit.DAYS));
            friendRepo.save(friend);
        }
    }
}
