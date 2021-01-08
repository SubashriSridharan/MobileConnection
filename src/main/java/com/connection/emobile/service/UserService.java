package com.connection.emobile.service;

import java.util.List;
import java.util.Optional;

import com.connection.emobile.dto.UserRequestDto;
import com.connection.emobile.dto.UserResponseDto;

public interface UserService {

    Optional<UserResponseDto> save(final UserRequestDto userRequestDto);

    public List<Long> availableMobileNumbers();
}
