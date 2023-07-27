package com.example.flexbook.repository;

import com.example.flexbook.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<Like, Integer> {
}
