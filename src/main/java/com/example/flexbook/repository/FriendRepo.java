package com.example.flexbook.repository;

import com.example.flexbook.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepo extends JpaRepository<Friend, Integer> {
}
