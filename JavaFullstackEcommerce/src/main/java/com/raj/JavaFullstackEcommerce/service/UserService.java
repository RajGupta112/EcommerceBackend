package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.exception.UserException;
import com.raj.JavaFullstackEcommerce.model.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public User findUserById(Long userId)throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
