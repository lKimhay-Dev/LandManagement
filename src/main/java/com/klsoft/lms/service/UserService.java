package com.klsoft.lms.service;

import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.user.CreateUserDto;
import com.klsoft.lms.dto.user.UpdateUserDto;

public interface UserService {

    ResponseDto findAll();

    ResponseDto findOne(int userId);

    ResponseDto create(CreateUserDto createUserDto);

    ResponseDto update(UpdateUserDto userUpdateDto);

    ResponseDto delete(int id);
}
