package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.config.JwtProvider;
import com.raj.JavaFullstackEcommerce.exception.UserException;
import com.raj.JavaFullstackEcommerce.model.User;
import com.raj.JavaFullstackEcommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplimentation implements UserService {

  private   UserRepository userRepository;
  private JwtProvider jwtProvider;
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()){
            user.get();
        }

        throw new UserException("user not find with this id"+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email= jwtProvider.getEmailFromToken(jwt);

        User user= userRepository.findByEmail(email);

        if(user==null){
            throw new UserException("user not found with email"+email);

        }
        return user;
    }
}
