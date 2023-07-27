package com.example.flexbook.models;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int message_id;
    private String message;
    private Date created_at;
    private Date updated_at;
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;

}
