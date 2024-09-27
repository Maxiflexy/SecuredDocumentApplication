package com.maxiflexy.securedocumentapplication.controller;

import com.maxiflexy.securedocumentapplication.domain.Response;
import com.maxiflexy.securedocumentapplication.dto.UserRequest;
import com.maxiflexy.securedocumentapplication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.maxiflexy.securedocumentapplication.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = {"/user"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> saveUser(
            @RequestBody @Valid UserRequest userRequest, HttpServletRequest servletRequest){
        userService.createUser(userRequest.getFirstName(), userRequest.getLastName(),
                userRequest.getEmail(), userRequest.getPassword());
        return ResponseEntity.created(getUri()).body(getResponse(servletRequest, emptyMap(),
                "Account created. Check your email to enable your account", CREATED));
    }

    private URI getUri() {
        return URI.create("");
    }
}
