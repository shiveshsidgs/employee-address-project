package com.sidgs.cloudgateway.security;

import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/eureka",
            "/v2/api-docs/**",
            "/cloud-gateway/**",
            "/swagger-ui/**"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
