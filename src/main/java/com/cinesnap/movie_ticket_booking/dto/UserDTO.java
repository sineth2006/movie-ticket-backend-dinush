package com.cinesnap.movie_ticket_booking.dto;

import com.cinesnap.movie_ticket_booking.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {

    private String userId;
    private String name;
    private String email;
    private String password;
    private Role role;
}
