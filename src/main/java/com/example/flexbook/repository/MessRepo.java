package com.example.flexbook.repository;

import com.example.flexbook.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessRepo extends JpaRepository<Message, Integer> {
}
