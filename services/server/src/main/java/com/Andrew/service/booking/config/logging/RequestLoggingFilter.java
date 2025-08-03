package com.Andrew.service.booking.config.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        String fullUrl = (query != null) ? uri + "?" + query : uri;

        logger.info("Request: " + method + " " + fullUrl);

        filterChain.doFilter(request, response);

        logger.info("Response status: " + response.getStatus());
    }
}
