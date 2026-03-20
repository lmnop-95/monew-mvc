package com.monew.monew_server.domain.user.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monew.monew_server.domain.user.dto.UserDto;
import com.monew.monew_server.domain.user.dto.UserLoginRequest;
import com.monew.monew_server.domain.user.dto.UserRegisterRequest;
import com.monew.monew_server.domain.user.dto.UserUpdateRequset;
import com.monew.monew_server.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserRegisterRequest request) {
		UserDto userDto = userService.register(request);
		return ResponseEntity.status(201).body(userDto);
	}

	@PostMapping("/login")
	public ResponseEntity<UserDto> loginUser(@Valid @RequestBody UserLoginRequest request){
		UserDto userDto = userService.login(request);
		return ResponseEntity.ok(userDto);
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<UserDto> updateNickname(@PathVariable UUID userId, @Valid @RequestBody UserUpdateRequset request) {
		UserDto userDto = userService.updateNickname(userId, request);
		return ResponseEntity.ok(userDto);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{userId}/hard")
	public ResponseEntity<Void> hardDeleteUser(@PathVariable UUID userId) {
		userService.hardDeleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
