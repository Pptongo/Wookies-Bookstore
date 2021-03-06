package com.wookie.bookstore.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * This class works as entry point for handled unauthorized access.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    /**
     * Send the unauthorized error.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
    
}
