package com.cinesnap.movie_ticket_booking.dto.secure.IMPL;

import com.cinesnap.movie_ticket_booking.config.JWTUtil;
import com.cinesnap.movie_ticket_booking.dao.UserDao;
import com.cinesnap.movie_ticket_booking.dto.UserDTO;
import com.cinesnap.movie_ticket_booking.dto.secure.JWTResponse;
import com.cinesnap.movie_ticket_booking.dto.secure.SignIn;
import com.cinesnap.movie_ticket_booking.entities.UserEntity;
import com.cinesnap.movie_ticket_booking.service.secure.AuthService;
import com.cinesnap.movie_ticket_booking.utill.Conversion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceIMPL  implements AuthService {

    private final Conversion conversion;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    @Override
    public JWTResponse signIn(SignIn signIn) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signIn.getEmail(),
                        signIn.getPassword()
                ));

        var authUser= userDao.findByEmail(signIn.getEmail())
                .orElseThrow(()->
                        new UsernameNotFoundException("User not found"));

        String token = jwtUtil.generateToken(
                authUser.getEmail(),
                authUser.getAuthorities());

        return JWTResponse
                .builder()
                .token(token)
                .build();


    }

    @Override
    public JWTResponse signUp(UserDTO user) {
        if(userDao.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("User already exists");
        }
        user.setUserId(user.getUserId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity savedUser =
                userDao.save(conversion.toUserEntity(user));

        String token = jwtUtil.generateToken(
                savedUser.getEmail(),
                savedUser.getAuthorities()
        );
        return JWTResponse
                .builder()
                .token(token)
                .build();
    }
}
