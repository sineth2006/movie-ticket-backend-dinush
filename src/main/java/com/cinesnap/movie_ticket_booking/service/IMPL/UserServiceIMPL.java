package com.cinesnap.movie_ticket_booking.service.IMPL;

import com.cinesnap.movie_ticket_booking.dao.UserDao;
import com.cinesnap.movie_ticket_booking.dto.UserDTO;
import com.cinesnap.movie_ticket_booking.dto.enums.Role;
import com.cinesnap.movie_ticket_booking.entities.UserEntity;
import com.cinesnap.movie_ticket_booking.exception.DataNotFoundException;
import com.cinesnap.movie_ticket_booking.service.UserService;
import com.cinesnap.movie_ticket_booking.utill.Conversion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    private final UserDao userDao;
    private  final Conversion conversion;

    @Override
    public void saveuser(UserDTO user) {
        UserEntity userEntity = conversion.toUserEntity(user);
        userDao.save(userEntity);
    }

    @Override
    public UserDTO getselecteduser(String userId) throws ChangeSetPersister.NotFoundException {
        UserEntity userEntity = userDao.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found!"));
        return conversion.toUserDTO(userEntity);
    }

    @Override
    public List<UserDTO> getallusers() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String) {
            return conversion.toUserDTOList(userDao.findAll());
        }

        if (principal instanceof UserDetails currentUser) {
            boolean isAdmin = currentUser.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")
                            || auth.getAuthority().equals("ADMIN"));

            if (isAdmin) {

                return conversion.toUserDTOList(userDao.findAll());
            } else {

                return conversion.toUserDTOList(userDao.findByRole(Role.CUSTOMER));
            }
        }
        return conversion.toUserDTOList(userDao.findAll());
    }


    @Override
    public void updateuser(String userId, UserDTO user) {
        UserEntity foundUser = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        foundUser.setName(user.getName());
        foundUser.setEmail(user.getEmail());
        foundUser.setPassword(user.getPassword());
        foundUser.setRole(user.getRole());

    }

    @Override
    public void deleteuser(String userId) {
        UserEntity foundUser = userDao.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found!"));
        userDao.delete(foundUser);
    }
}
