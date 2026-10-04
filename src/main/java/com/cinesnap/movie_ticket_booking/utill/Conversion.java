package com.cinesnap.movie_ticket_booking.utill;

import com.cinesnap.movie_ticket_booking.dto.*;
import com.cinesnap.movie_ticket_booking.entities.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Conversion {

    private final ModelMapper mapper;

    //--------------User-Conversion--------------
    public UserDTO toUserDTO(UserEntity entity) {

        UserDTO dto = mapper.map(entity, UserDTO.class);

        dto.setUserId(entity.getUserId());

        return dto;
    }

    public UserEntity toUserEntity (UserDTO userDTO) {

        UserEntity entity = mapper.map(userDTO, UserEntity.class);

        entity.setUserId(userDTO.getUserId());

        return entity;
    }

    public List<UserDTO> toUserDTOList(List<UserEntity> entities) {

        return entities.stream()
                .map(this::toUserDTO)
                .toList();
    }

    //--------------Movie-Conversion--------------
    public MovieDTO toMovieDTO(MovieEntity entity) {

        MovieDTO dto = mapper.map(entity, MovieDTO.class);

        dto.setMovieId(entity.getMovieId());

        return dto;
    }

    public MovieEntity toMovieEntity (MovieDTO movieDTO) {

        MovieEntity entity = mapper.map(movieDTO, MovieEntity.class);

        entity.setMovieId(movieDTO.getMovieId());

        return entity;
    }

    public List<MovieDTO> toMovieDTOList(List<MovieEntity> entities) {

        return entities.stream()
                .map(this::toMovieDTO)
                .toList();
    }

    //--------------Theatre-Conversion--------------

    public TheatreDTO toTheatreDTO(TheatreEntity entity) {

        TheatreDTO dto = mapper.map(entity, TheatreDTO.class);

        dto.setTheatreId(entity.getTheatreId());

        return dto;
    }

    public TheatreEntity toTheatreEntity (TheatreDTO theatreDTO) {

        TheatreEntity entity = mapper.map(theatreDTO, TheatreEntity.class);

        entity.setTheatreId(theatreDTO.getTheatreId());

        return entity;
    }

    public List<TheatreDTO> toTheatreDTOList(List<TheatreEntity> entities) {

        return entities.stream()
                .map(this::toTheatreDTO)
                .toList();
    }
    //--------------Show-Conversion--------------

    public ShowDTO toShowDTO(ShowEntity entity) {

        ShowDTO dto = mapper.map(entity, ShowDTO.class);

        dto.setShowId(entity.getShowId());

        return dto;
    }

    public ShowEntity toShowEntity (ShowDTO showDTO) {

        ShowEntity entity = mapper.map(showDTO, ShowEntity.class);

        entity.setShowId(showDTO.getShowId());

        return entity;
    }

    public List<ShowDTO> toShowDTOList(List<ShowEntity> entities) {

        return entities.stream()
                .map(this::toShowDTO)
                .toList();
    }
    //--------------Booking-Conversion--------------

    public BookingDTO toBookingDTO(BookingEntity entity) {

        BookingDTO dto = mapper.map(entity, BookingDTO.class);

        dto.setBookingId(entity.getBookingId());

        return dto;
    }

    public BookingEntity toBookingEntity (BookingDTO bookingDTO) {

        BookingEntity entity = mapper.map(bookingDTO, BookingEntity.class);

        entity.setBookingId(bookingDTO.getBookingId());

        return entity;
    }

    public List<BookingDTO> toBookingDTOList(List<BookingEntity> entities) {

        return entities.stream()
                .map(this::toBookingDTO)
                .toList();
    }
    //--------------Payment-Conversion--------------

    public PaymentDTO toPaymentDTO(PaymentEntity entity) {

        PaymentDTO dto = mapper.map(entity, PaymentDTO.class);

        dto.setPaymentId(entity.getPaymentId());

        if (entity.getBooking() != null) {
            dto.setBookingId(entity.getBooking().getBookingId());
        }

        return dto;
    }

    public PaymentEntity toPaymentEntity (PaymentDTO paymentDTO) {

        PaymentEntity entity = mapper.map(paymentDTO, PaymentEntity.class);

        entity.setPaymentId(paymentDTO.getPaymentId());

        return entity;
    }

    public List<PaymentDTO> toPaymentDTOList(List<PaymentEntity> entities) {

        return entities.stream()
                .map(this::toPaymentDTO)
                .toList();
    }



}
