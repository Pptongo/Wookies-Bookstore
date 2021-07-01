package com.wookie.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wookie.bookstore.persistance.entities.UserEntity;
import com.wookie.bookstore.persistance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The service that contains all the business logic for Authentication methods.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Service
public class AuthServiceImpl implements UserDetailsService {

    /**
     * Inject the User repository to run all database operations for user.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Load a user that matchs with the passed username.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param username The username to be used in the search.
     * @return The @see {@link UserDetails} for the user found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = null;
        user = userRepository.findByUsername(username);

        if (user == null) throw new UsernameNotFoundException("USER_NOT_FOUND");

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
