package com.example.tview.service;

import com.example.tview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// trả về 1 UserDetails để spring security xác thực người dùng
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) {
        User user = userService.userRepository.findUserByUserName(userName);
        List<GrantedAuthority> authorities = getUserAuthority(user.getType());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(String role) {
        Set<GrantedAuthority> roles = new HashSet<>();
        if(role=="admin")
            roles.add(new SimpleGrantedAuthority("admin"));
        else if (role =="partner")
            roles.add(new SimpleGrantedAuthority("partner"));
        else
            roles.add(new SimpleGrantedAuthority("user"));
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                user.getActive(), true, true, true, authorities);
    }
}

