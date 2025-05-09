package com.raj.JavaFullstackEcommerce.controller;

import com.raj.JavaFullstackEcommerce.Request.LoginRequest;
import com.raj.JavaFullstackEcommerce.config.JwtProvider;
import com.raj.JavaFullstackEcommerce.exception.UserException;
import com.raj.JavaFullstackEcommerce.model.User;
import com.raj.JavaFullstackEcommerce.repository.UserRepository;
import com.raj.JavaFullstackEcommerce.response.AuthResponse;
import com.raj.JavaFullstackEcommerce.service.CustomUserServiceImplimentation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor

public class AuthController {



    private UserRepository userRepository;
    private  JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserServiceImplimentation customService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>  createUserHandler(@RequestBody User user) throws UserException {
        String email= user.getEmail();
        String password= user.getPassword();;
        String firstName= user.getFirstName();
        String lastName= user.getLastName();

        User isEmailExist= userRepository.findByEmail(email);



        if(isEmailExist!=null){
            throw new  UserException("Email is Already Used with Another Account");
        }

        User  createdUser= new User();

        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User savedUser= userRepository.save(createdUser);

        Authentication authentication= new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token,"Signup Success");
        return  new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public  ResponseEntity<AuthResponse>  LoginUserHnadler(@RequestBody LoginRequest loginRequest){
        String userName= loginRequest.getEmail();
        String password= loginRequest.getPassword();

        Authentication authentication=authentication(userName,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse(token,"SignIn Success");

        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

    public Authentication authentication(String username, String password){
        UserDetails userDetails=customService.loadUserByUsername(username);

        if(userDetails==null){
            throw new BadCredentialsException("Invalid UserName...");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }

     return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
