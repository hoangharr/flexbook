package com.example.flexbook.models;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_ID;
    @Column(insertable=false, updatable=false)
    private int user_ID;
    @Column(insertable=false, updatable=false)
    private int post_ID;
    private String comment;
    private Timestamp created_at;
    private Timestamp updated_at;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;
    public static Comment generateComment(){
        Faker faker = new Faker();

    }
}
