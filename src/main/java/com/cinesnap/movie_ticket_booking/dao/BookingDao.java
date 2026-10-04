package com.cinesnap.movie_ticket_booking.dao;

import com.cinesnap.movie_ticket_booking.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<BookingEntity,String> {
}
