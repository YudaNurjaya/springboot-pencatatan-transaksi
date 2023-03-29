package com.enigma.livecodeecommerce.controller;

import com.enigma.livecodeecommerce.model.User;
import com.enigma.livecodeecommerce.model.request.LoginRequest;
import com.enigma.livecodeecommerce.model.request.RegistrationRequest;
import com.enigma.livecodeecommerce.model.response.SuccessResponse;
import com.enigma.livecodeecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationRequest request){
        User user = modelMapper.map(request,User.class);
        User regist = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<String>("Resgistration Success"," " + regist.getEmail()));
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){
        User user = modelMapper.map(request,User.class);
        String token= userService.login(user);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<String>("Login Success",("Token " + token)));
    }
}
