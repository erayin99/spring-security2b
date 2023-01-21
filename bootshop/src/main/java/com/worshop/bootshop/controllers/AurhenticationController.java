package com.worshop.bootshop.controllers;

import com.worshop.bootshop.confog.JwtUtil;
import com.worshop.bootshop.dao.UserDao;
import com.worshop.bootshop.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AurhenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        authenticationManager.authenticate (
                new UsernamePasswordAuthenticationToken ( request.getEmail (), request.getPassword () )
        );
        final UserDetails user = userDao.findUserByEmail ( request.getEmail () );
        if (user != null){
            return ResponseEntity.ok ( jwtUtil.generateTiken ( user ) );
        }
        return ResponseEntity.status ( 400 ).body ( "some error has occured" );
    }
}

