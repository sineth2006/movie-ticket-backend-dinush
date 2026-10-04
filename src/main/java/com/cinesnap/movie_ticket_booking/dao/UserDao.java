package com.cinesnap.movie_ticket_booking.dao;

import com.cinesnap.movie_ticket_booking.dto.enums.Role;
import com.cinesnap.movie_ticket_booking.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);//this jparepository automaticaly generate "save","delete",e.t.c.

    List<UserEntity> findByRole(Role role);
}
