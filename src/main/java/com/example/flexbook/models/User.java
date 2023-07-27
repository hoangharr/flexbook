package com.example.flexbook.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable=false, updatable=false)
    private int user_id;
    private String username;
    private String email;
    private String password;
    private String profile_picture;
    private String bio;
    private Timestamp created_at;
    private Timestamp updated_at;
}
