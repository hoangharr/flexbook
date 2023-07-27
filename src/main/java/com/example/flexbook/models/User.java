package com.example.flexbook.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String username;
    private String email;
    private String password;
    private String profile_picture;
    private String bio;
    private Date created_at;
    private Date updated_at;

}
