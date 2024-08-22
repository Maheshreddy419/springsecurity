package com.spring.security.services;

import com.spring.security.dto.LoginDto;
import com.spring.security.dto.SignUpDto;
import com.spring.security.dto.UserDto;
import com.spring.security.entity.User;
import com.spring.security.exceptions.ResourceNotFoundException;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with this"+username+" not found"));
    }

    public UserDto signUp(SignUpDto signUpDto) {
      Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
      if (user.isPresent()) {
          throw new BadCredentialsException("User with this email already exists"+signUpDto.getEmail());
      }
      User toCreateUser   = modelMapper.map(signUpDto, User.class);
      toCreateUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
      User savedUser = userRepository.save(toCreateUser);
      return modelMapper.map(savedUser, UserDto.class);
    }


}
