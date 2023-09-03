package com.project.UserService.Repositories;

import com.project.UserService.Entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository  extends JpaRepository<Session,Long> {
    Optional<Session> findFirstByOrderByUsersIdDesc();
}
