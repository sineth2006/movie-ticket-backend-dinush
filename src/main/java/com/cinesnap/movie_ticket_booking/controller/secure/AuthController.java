package com.cinesnap.movie_ticket_booking.controller.secure;

import com.cinesnap.movie_ticket_booking.dto.UserDTO;
import com.cinesnap.movie_ticket_booking.dto.secure.JWTResponse;

import com.cinesnap.movie_ticket_booking.dto.secure.SignIn;
import com.cinesnap.movie_ticket_booking.service.secure.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    ///--------sign up--------///
    @PostMapping("/signup")
    public ResponseEntity<JWTResponse> signUp(@RequestBody UserDTO signInUser){
        return ResponseEntity.ok(authService.signUp(signInUser));
    }
    ///--------sign in--------///
    @PostMapping("/signin")
    public ResponseEntity<JWTResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }
}
