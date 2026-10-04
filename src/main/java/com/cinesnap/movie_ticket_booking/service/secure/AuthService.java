package com.cinesnap.movie_ticket_booking.service.secure;

import com.cinesnap.movie_ticket_booking.dto.UserDTO;
import com.cinesnap.movie_ticket_booking.dto.secure.JWTResponse;
import com.cinesnap.movie_ticket_booking.dto.secure.SignIn;

public interface AuthService {
    JWTResponse signIn(SignIn signIn);
    JWTResponse signUp(UserDTO user);
}
