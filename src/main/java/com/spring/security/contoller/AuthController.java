package com.spring.security.contoller;

import com.spring.security.dto.LoginDto;
import com.spring.security.dto.SignUpDto;
import com.spring.security.dto.UserDto;
import com.spring.security.services.AuthService;
import com.spring.security.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

   @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto) {
     UserDto  userDto = userService.signUp(signUpDto);
     return ResponseEntity.ok(userDto);
   }

   @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest, HttpServletResponse response) {
       String token = authService.login(loginDto);
       Cookie cookie = new Cookie("token", token);
       cookie.setHttpOnly(true);
       response.addCookie(cookie);
       return ResponseEntity.ok(token);
   }
}
