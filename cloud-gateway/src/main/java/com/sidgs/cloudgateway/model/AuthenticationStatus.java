package com.sidgs.cloudgateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationStatus {
    private Boolean isAuthenticated;
    private String message;
}
