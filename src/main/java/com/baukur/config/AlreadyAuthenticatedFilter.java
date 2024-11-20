package com.baukur.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AlreadyAuthenticatedFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (request.getRequestURI().equals("/login") && authentication != null) {
            response.sendRedirect("/");
    } else if (authentication == null && !request.getRequestURI().equals("/login") && !request.getRequestURI().equals("/user") && !request.getRequestURI().equals("/categories/default")) {
            response.setStatus(403);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}