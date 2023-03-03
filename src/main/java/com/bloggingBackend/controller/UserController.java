package com.bloggingBackend.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingBackend.exceptions.CustomExceptions;
import com.bloggingBackend.exceptions.ResourceNotFoundException;
import com.bloggingBackend.payloads.APIResponse;
import com.bloggingBackend.payloads.UserDto;
import com.bloggingBackend.services.IUserService;
import com.bloggingBackend.services.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/blogging-backend/api")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/create-user")
	public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
//		return new ResponseEntity<UserDto>
//		(userService.createUser(userDto), HttpStatus.CREATED);
		try {
			return APIResponse.generateResponse(HttpStatus.CREATED.name(), HttpStatus.CREATED,
					userService.createUser(userDto));
		} catch (CustomExceptions e) {
			return APIResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
		}

	}

//	@PutMapping("/update-user/{userId}")
//	public ResponseEntity<?> updateUser
//								(@RequestBody UserDto userDto,@PathVariable("userId") Long uid){
//		
//
////		try {
//			return new ResponseEntity(userService.updateUserById(userDto, uid),HttpStatus.OK);
//
////		}catch (Exception e) {
//			System.out.println("coming");
////			return new ResponseEntity(new APIResponse("good", "true"),HttpStatus.OK);
//
//		}
////	}
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {

		try {
			userService.deleteUserById(userId);
			return APIResponse.generateResponse("deleted", HttpStatus.OK, null);
		} catch (ResourceNotFoundException e) {
			return APIResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
		}
	}

}
