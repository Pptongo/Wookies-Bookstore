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

@Service
public class AuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = null;
        user = userRepository.findByUsername(username);

        if (user == null) throw new UsernameNotFoundException("USER_NOT_FOUND");

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
