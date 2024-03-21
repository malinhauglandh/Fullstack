package org.ntnu.rest.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.ntnu.rest.model.UserLogin;
import org.ntnu.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserService userService;

    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);

    @PostMapping("/createUser")
    @ResponseBody
    public boolean createUser(@RequestBody UserLogin login) {
        return userService.createUser(login);
    }

    @PostMapping("/login")
    public ResponseEntity<String> getLogin(@RequestBody UserLogin login) {
        boolean correct = userService.login(login);
        if (correct) {
            return ResponseEntity.ok(generateToken(login.getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied, wrong credentials...");
        }
    }

    @PostMapping(value = "/token")
    public ResponseEntity<String> generateToken(final @RequestBody UserLogin loginRequest) throws ResponseStatusException {
        // if username and password are valid, issue an access token
        // note that subsequent requests need this token
        if (userService.login(loginRequest)) {
            return ResponseEntity.ok(generateToken(loginRequest.getUsername()));
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied, wrong credentials...");
    }

    private String generateToken(final String userId) {
        final Instant now = Instant.now();
        String keyStr = "darwinnunezthegreatestofalltime";
        final Algorithm hmac512 = Algorithm.HMAC512(keyStr);;
        JWT.require(hmac512).build();
        return JWT.create()
                .withSubject(userId)
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(hmac512);
    }
}