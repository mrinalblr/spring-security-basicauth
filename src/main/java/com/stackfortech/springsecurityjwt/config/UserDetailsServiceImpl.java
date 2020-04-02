package com.stackfortech.springsecurityjwt.config;

import com.stackfortech.springsecurityjwt.entity.User;
import com.stackfortech.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       User  user = userRepository.findByUsername(s);
       if(user == null){
           throw new UsernameNotFoundException(s);
       }

        SimpleGrantedAuthority authorities = new SimpleGrantedAuthority("ROLE_"+user.getRoles());
       List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<>();
       updatedAuthorities.add(authorities);
       return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), updatedAuthorities);
    }
}
