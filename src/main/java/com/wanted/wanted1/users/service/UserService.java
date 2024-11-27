package com.wanted.wanted1.users.service;

import com.wanted.wanted1.users.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> findByEmail(String email);
    ResponseEntity<?> save(UserDto user);
}
