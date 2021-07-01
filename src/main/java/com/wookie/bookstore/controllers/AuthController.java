package com.wookie.bookstore.controllers;

import com.wookie.bookstore.requests.CredentialsRequest;
import com.wookie.bookstore.response.ApiResponse;
import com.wookie.bookstore.service.impl.AuthServiceImpl;
import com.wookie.bookstore.shared.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manage all the Authentication requests.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/api/1.0/auth")
public class AuthController {
    
    /**
     * Inject the Authentication Manager Bean.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;

    /**
     * Inject the Authentication service.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private AuthServiceImpl authService;

    /**
     * Inject the JWT Utily to create the JWT object.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Authenticate the user with their credentials.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param credentials The @see {@link CredentialRequest} that contains the username and password.
     * @return @see {@link ApiResponse} The status of the authentication.
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> auth(@RequestBody CredentialsRequest credentials) {
        try {
            authenticate(credentials.getUsername(), credentials.getPassword());
            final UserDetails userDetails = authService.loadUserByUsername(credentials.getUsername());
            final String token = jwtUtil.generateToken(userDetails);

            if (userDetails != null) return new ResponseEntity<>(new ApiResponse((Object) token), HttpStatus.OK);

            return new ResponseEntity<>(new ApiResponse("INVALID_USER"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Try to authenticate using the username and passowrd with the database
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param username The username
     * @param password The password
     * @throws Exception
     */
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS");
        }
    }

}