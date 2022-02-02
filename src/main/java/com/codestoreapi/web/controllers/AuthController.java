package com.codestoreapi.web.controllers;

import com.codestoreapi.core.services.AuthService;
import com.codestoreapi.core.services.UserService;
import com.codestoreapi.vo.SignInVO;
import com.codestoreapi.vo.SignUpVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Authentication Module Controller", description = "Authentication module methods")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public @ResponseBody ResponseEntity<?> signUp(@Valid @RequestBody SignUpVO signUpVO) {
        if (userService.existsByUsername(signUpVO.getUsername())) {
            return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userService.existsByEmail(signUpVO.getEmail())) {
            return new ResponseEntity<>("Error: Email is already in use!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(authService.signUp(signUpVO), HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public @ResponseBody ResponseEntity<?> signIn(@Valid @RequestBody SignInVO signInVO) {
        log.info("Login: {}", signInVO.toString());
        return new ResponseEntity(authService.signIn(signInVO), HttpStatus.OK);
    }

    @PostMapping("/sign-out")
    public @ResponseBody ResponseEntity<?> signOut() {
        authService.signOut();
        return new ResponseEntity("Signed Out Sucessfully", HttpStatus.OK);
    }
}
