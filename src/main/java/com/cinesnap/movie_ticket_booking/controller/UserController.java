package com.cinesnap.movie_ticket_booking.controller;

import com.cinesnap.movie_ticket_booking.dto.UserDTO;
import com.cinesnap.movie_ticket_booking.entities.UserEntity;
import com.cinesnap.movie_ticket_booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/users")
@RestController
@RequiredArgsConstructor//this is use when you type like final method"private final UserService userService;"
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    ///--------save user--------///
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        logger.info("Incoming create user detail is {}", userDTO);
        userService.saveuser(userDTO);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }
    ///--------get user by id--------///
    @GetMapping(value= "{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getSelectedUser(@PathVariable String userId)throws ChangeSetPersister.NotFoundException  {
        System.out.println("Selected User: " + userId);
        userService.getselecteduser(userId);
        return new  ResponseEntity<>(userService.getselecteduser(userId), HttpStatus.OK);
    }

    ///--------get all users--------///
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers() {
        userService.getallusers();//github eke meka gahaa thibbe na poddak balanna
        return new ResponseEntity<>(userService.getallusers(), HttpStatus.OK);
    }
    ///--------update user--------///
    @PatchMapping(value = "{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable String userId,@RequestBody UserDTO userDTO) {
        userService.updateuser(userId,userDTO);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    ///--------delete user--------///
    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteuser(userId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
