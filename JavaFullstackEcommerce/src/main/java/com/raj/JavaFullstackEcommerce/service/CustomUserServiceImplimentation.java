package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.model.User;
import com.raj.JavaFullstackEcommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserServiceImplimentation implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= userRepository.findByEmail(username);


        if(user==null){
            throw new UsernameNotFoundException("user not found with email"+username);
        }
        List<GrantedAuthority> authority= new ArrayList<>();

        return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authority);

    }
}
