package com.cinesnap.movie_ticket_booking.dto.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignIn {//logign using unique emal and password
    private String email;
    private String password;

}
