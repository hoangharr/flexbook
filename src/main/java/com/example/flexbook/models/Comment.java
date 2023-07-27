package com.example.flexbook.models;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_ID;
    private String comment;
    private Date created_at;
    private Date updated_at;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;
//    public static Comment generateComments(){
//        Faker faker = new Faker();
//        String comment = faker.lorem().sentence();
//        Date created_at = faker.date().birthday();
//        Date updated_at = faker.date().birthday();
//
//
//        return new Comment(comment, created_at, updated_at);
//    }
}
