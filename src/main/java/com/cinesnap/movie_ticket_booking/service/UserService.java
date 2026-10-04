package com.cinesnap.movie_ticket_booking.service;

import com.cinesnap.movie_ticket_booking.dto.TheatreDTO;
import com.cinesnap.movie_ticket_booking.dto.UserDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserService {
    void saveuser(UserDTO user);
    UserDTO  getselecteduser(String userid) throws ChangeSetPersister.NotFoundException;
    List<UserDTO> getallusers();
    void updateuser(String userId,UserDTO user);
    void deleteuser(String userId);
}
