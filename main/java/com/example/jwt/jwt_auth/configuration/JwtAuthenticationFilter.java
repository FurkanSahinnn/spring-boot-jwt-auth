package com.example.jwt.jwt_auth.configuration;

import com.example.jwt.jwt_auth.services.impls.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String token;
        if (header.startsWith("Bearer ")) {
            token = header.substring(7);
            String username;
            try {
                username = jwtService.extractUsername(token);
                // Proceed only if the username was successfully extracted from the token
                // and there is no existing authentication in the SecurityContext
                // (to avoid overriding or duplicating authentication)
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Check and fetch username in DB
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (userDetails != null && jwtService.validateToken(token, userDetails)) {
                        // Add user to SecurityContext
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        username,
                                        null,
                                        userDetails.getAuthorities()
                                );
                        authenticationToken.setDetails(userDetails);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (ExpiredJwtException e) {
                System.out.println("The token is expired :" + e.getMessage());
            } catch (Exception e) {
                System.out.println("Global error: " + e.getMessage());;
            }
            filterChain.doFilter(request, response);
        }
    }
}
