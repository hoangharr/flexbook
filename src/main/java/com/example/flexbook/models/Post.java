package com.example.flexbook.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;
    private String content;
    private String post_image;
    private Date created_at;
    private Date updated_at;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
