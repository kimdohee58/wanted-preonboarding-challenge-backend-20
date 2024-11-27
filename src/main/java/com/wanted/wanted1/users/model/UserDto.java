package com.wanted.wanted1.users.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String postcode;
}
