package com.example.flexbook.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "friends")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int friend_id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date created_at;
    private Date updated_at;
    @ManyToOne
    @JoinColumn(name = "user_id1", referencedColumnName = "user_id")
    private User user1;
    @ManyToOne
    @JoinColumn(name = "user_id2", referencedColumnName = "user_id")
    private User user2;
    @ManyToOne
    @JoinColumn(name = "action_user_id", referencedColumnName = "user_id")
    private User actionUser;

}
