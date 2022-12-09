package com.davidminkovski.certifications.controller;

import com.davidminkovski.certifications.dto.JWTAuthResponse;
import com.davidminkovski.certifications.dto.LoginDTO;
import com.davidminkovski.certifications.dto.SignupDTO;
import com.davidminkovski.certifications.entity.Role;
import com.davidminkovski.certifications.entity.User;
import com.davidminkovski.certifications.repository.RoleRepository;
import com.davidminkovski.certifications.repository.UserRepository;
import com.davidminkovski.certifications.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody  LoginDTO dto){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtTokenProvider.generateToken(auth);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("signup")
    public ResponseEntity<String> signupUser(@RequestBody SignupDTO dto){
        if(userRepository.existsByUsername(dto.getUsername())){
            return new ResponseEntity<>("The username or email is already taken.", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(dto.getEmail())){
            return new ResponseEntity<>("The username or email is already taken.", HttpStatus.BAD_REQUEST);
        }

        Role role = roleRepository.findByName("ROLE_USER").get();
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUsername(dto.getUsername());
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);

        return new ResponseEntity<>("User successfully registered", HttpStatus.OK);
    }

}
