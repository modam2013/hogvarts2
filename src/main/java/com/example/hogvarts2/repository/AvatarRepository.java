package com.example.hogvarts2.repository;

import com.example.hogvarts2.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudent_Id(long studentId);
}
