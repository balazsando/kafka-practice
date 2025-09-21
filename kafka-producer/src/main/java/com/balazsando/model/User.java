package com.balazsando.model;

import com.balazsando.enums.UserId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {
    private UserId userId;
    private String userName;
    private Date dateOfBirth;
}
