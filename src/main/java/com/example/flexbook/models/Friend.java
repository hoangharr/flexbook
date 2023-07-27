package com.example.flexbook.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "friends")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int friend_id;
    private int user_id1;
    private int user_id2;

    public enum Status {
        PENDING,
        ACCEPTED,
        DECLINED,
        BLOCKED
    }
    @Column(insertable=false, updatable=false)
    private int action_user_id;
    private Timestamp created_at;
    private Timestamp updated_at;
    @ManyToOne
    @JoinColumn(name = "user_id1", referencedColumnName = "user_id", insertable=false, updatable=false)
    private User user1;
    @ManyToOne
    @JoinColumn(name = "user_id2", referencedColumnName = "user_id", insertable=false, updatable=false)
    private User user2;
    @ManyToOne
    @JoinColumn(name = "action_user_id", referencedColumnName = "user_id")
    private User actionUser;
}
