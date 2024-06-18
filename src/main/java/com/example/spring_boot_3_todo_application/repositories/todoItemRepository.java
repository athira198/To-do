package com.example.spring_boot_3_todo_application.repositories;

import com.example.spring_boot_3_todo_application.models.todoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface todoItemRepository extends JpaRepository<todoItem,Long> {
}
