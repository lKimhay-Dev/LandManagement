package com.klsoft.lms.controller;

import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.user.CreateUserDto;
import com.klsoft.lms.dto.user.UpdateUserDto;
import com.klsoft.lms.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserRestController {
    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseDto findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseDto findOne(@PathVariable int userId) {
        return this.userService.findOne(userId);
    }

    @PostMapping
    public ResponseDto create(@Valid @RequestBody CreateUserDto createUserDto) {
        return this.userService.create(createUserDto);
    }

    @PutMapping
    public ResponseDto update(@Valid @RequestBody UpdateUserDto userUpdateDto) {
        return this.userService.update(userUpdateDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseDto delete(@PathVariable int userId) {
        return this.userService.delete(userId);
    }
}
