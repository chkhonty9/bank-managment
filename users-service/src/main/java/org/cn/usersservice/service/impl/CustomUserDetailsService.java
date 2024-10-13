package org.cn.usersservice.service.impl;

import org.cn.usersservice.dto.CustomerDTO;
import org.cn.usersservice.service.CustomerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerService customerService;

    public CustomUserDetailsService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerDTO customerDTO = customerService.findByUsername(username);
        if(customerDTO == null) new UsernameNotFoundException("User not found with username: " + username);

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        customerDTO.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(customerDTO.getUsername(), customerDTO.getPassword(), authorities);
    }

}
