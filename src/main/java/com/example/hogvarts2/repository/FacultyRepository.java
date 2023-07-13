package com.example.hogvarts2.repository;

import com.example.hogvarts2.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findAllByColor(String color);

    List<Faculty> findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(
            String color,
            String name
    );
}
