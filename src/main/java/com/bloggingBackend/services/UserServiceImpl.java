package com.bloggingBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bloggingBackend.entites.User;
import com.bloggingBackend.payloads.UserDto;
import com.bloggingBackend.repositories.UserRepository;
import com.bloggingBackend.exceptions.CustomExceptions;
import com.bloggingBackend.exceptions.ResourceNotFoundException;
import com.bloggingBackend.constants.CommonConstants;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		modelMapper.map(user, userDto);
		return userDto;
	}

	private User dtoToUser(UserDto userDto) {
		User user = new User();
		modelMapper.map(userDto, user);
		return user;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		 
        try {
        	  return userToDto(userRepository.save(dtoToUser(userDto)));
		} catch (Exception e) {
			throw new CustomExceptions(e.getMessage(),null);
		}
	}

	@Override
	public UserDto updateUserById(UserDto userDto, Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", String.valueOf(id)));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return userToDto(userRepository.save(user));
	}

	@Override
	public List<UserDto> getAllUser() {
//     List<UserDto> userDtos = new ArrayList<UserDto>();
//		 userRepository.findAll().forEach(user -> userDtos.add(userToDto(user)));
		return userRepository.findAll().stream().map(user->userToDto(user)).collect(Collectors.toList());
//		return userDtos;
	}

	@Override
	public UserDto getUserById(Long id) {
		return userToDto(userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", id.toString())));

	}

	@Override
	public void deleteUserById(Long id) {
//		userRepository.delete(dtoToUser(getUserById(id)));
		userRepository.delete(userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", String.valueOf(id))));

	}

}
