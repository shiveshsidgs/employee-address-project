package com.sidgs.cloudgateway.controller;
import com.sidgs.cloudgateway.model.AuthenticationStatus;
import com.sidgs.cloudgateway.model.ErrorResponseDto;
import com.sidgs.cloudgateway.model.JwtRequest;
import com.sidgs.cloudgateway.model.JwtResponse;
import com.sidgs.cloudgateway.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sidgs.cloudgateway.constants.Constants.API_GATEWAY_PREDICATE;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * * *** NOTE: ***
     * * Api Gateway should match predicate
     * * path to be discoverable in swagger
     */
    //@RequestMapping(value = API_GATEWAY_PREDICATE + "/authenticate", method = RequestMethod.POST)
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        AuthenticationStatus status = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (!status.getIsAuthenticated()) {
            List<String> details = new ArrayList<>();
            details.add(status.getMessage());
            ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details, "uri");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private AuthenticationStatus authenticate(String username, String password) {
        AuthenticationStatus status;

        if (!username.equals("sidgs") && !password.equals("password")) {
            status = new AuthenticationStatus(false, "Invalid Username/Password");
        }
        else {
            status = new AuthenticationStatus(true, "Authentication Successful");
        }

        return status;
    }
}
