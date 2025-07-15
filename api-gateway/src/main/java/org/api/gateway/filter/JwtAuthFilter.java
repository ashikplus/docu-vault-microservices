package org.api.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

//@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    // Make sure this secret matches the one used in your AuthService
    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret"; // must be 256-bit (32+ chars)

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // Allow public endpoints without token
        if (request.getURI().getPath().contains("/auth")) {
            return chain.filter(exchange);
        }

        // Check for Authorization header
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Validate JWT token
        String token = authHeader.substring(7);
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts
            		.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Optionally, you can check roles/username here
            String username = claims.getSubject();

            // Forward the username to downstream services
            ServerHttpRequest modifiedRequest = request.mutate()
                    .header("X-User-Name", username)
                    .build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -1; // Ensure it runs early
    }
}
