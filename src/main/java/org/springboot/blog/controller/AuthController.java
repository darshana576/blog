package org.springboot.blog.controller;

import org.springboot.blog.dto.*;
import org.springboot.blog.model.User;
import org.springboot.blog.service.*;
import org.springboot.blog.config.JwtUtils;
import org.springboot.blog.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    // ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserDto userDto) {
        User user = userService.registerUser(userDto);
        return new ResponseEntity<>(new ApiResponse("User registered successfully!", true), HttpStatus.CREATED);
    }

    // ✅ Login user
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails.getUsername());

        JwtResponse response = new JwtResponse(token, userDetails.getUsername());
        return ResponseEntity.ok(response);
    }
}
