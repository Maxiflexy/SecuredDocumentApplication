package com.maxiflexy.securedocumentapplication.controller;

import com.maxiflexy.securedocumentapplication.domain.Response;
import com.maxiflexy.securedocumentapplication.dto.UserRequest;
import com.maxiflexy.securedocumentapplication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.maxiflexy.securedocumentapplication.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/user"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Response> saveUser(
            @RequestBody @Valid UserRequest userRequest, HttpServletRequest servletRequest){

        userService.createUser(userRequest.getFirstName(), userRequest.getLastName(),
                userRequest.getEmail(), userRequest.getPassword());
        return ResponseEntity.created(getUri()).body(getResponse(servletRequest, emptyMap(),
                "Account created. Check your email to enable your account", CREATED));
    }

    @GetMapping("/verify/account")
    public ResponseEntity<Response> verifyAccount(@RequestParam("key") String key, HttpServletRequest servletRequest){

        userService.verifyAccountKey(key);
        return ResponseEntity.ok().body(getResponse(servletRequest, emptyMap(),
                "Account verified.", OK));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody UserRequest userRequest){
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(userRequest.getEmail(), userRequest.getPassword()));
        return ResponseEntity.ok().build();
    }

    private URI getUri() {
        return URI.create("");
    }
}
