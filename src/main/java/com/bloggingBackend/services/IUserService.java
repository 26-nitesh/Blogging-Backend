package com.bloggingBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloggingBackend.payloads.UserDto;
@Service
public interface IUserService {

     UserDto createUser(UserDto userDto);
     UserDto updateUserById(UserDto userDto,Long id);
     List<UserDto> getAllUser();
     UserDto getUserById(Long id);
     void deleteUserById(Long id);
}
