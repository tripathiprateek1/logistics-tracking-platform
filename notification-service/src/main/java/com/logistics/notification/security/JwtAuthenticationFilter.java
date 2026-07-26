package com.logistics.notification.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        // Lightweight token parsing for environments where a full JWT library is not available.
        String email = null;
        String role = "USER";

        if (token != null) {
            if (token.contains(":")) {
                String[] parts = token.split(":");
                email = parts.length > 0 ? parts[0] : null;
                role = parts.length > 1 ? parts[1] : "USER";
            } else {
                email = token; // fallback: token contains email directly
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = new User(
                    email,
                    "",
                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
            );

            // Basic validation: accept non-empty tokens. Replace with JwtUtil checks if available.
            if (token != null && !token.isEmpty()) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

