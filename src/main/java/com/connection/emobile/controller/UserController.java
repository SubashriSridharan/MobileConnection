package com.connection.emobile.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.connection.emobile.entity.MobileNumber;
import com.connection.emobile.repository.MobileNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connection.emobile.dto.UserRequestDto;
import com.connection.emobile.dto.UserResponseDto;
import com.connection.emobile.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    public ResponseEntity<UserResponseDto> save(@RequestBody final UserRequestDto userRequestDto) {

        Optional<UserResponseDto> userResponseDto = userService.save(userRequestDto);

        return new ResponseEntity<>(userResponseDto.get(), HttpStatus.OK);

    }

    @GetMapping(value = "/mobileNumbers")
    public ResponseEntity<List<Long>> availableMobileNumbers() {
        return new ResponseEntity<>(userService.availableMobileNumbers(),HttpStatus.OK);
    }

}
