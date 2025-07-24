package com.example.mall.repository;

import org.apache.logging.log4j.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mall.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{


}
