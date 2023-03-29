package com.enigma.livecodeecommerce.service;

import com.enigma.livecodeecommerce.exception.DataEmptyException;
import com.enigma.livecodeecommerce.model.User;
import com.enigma.livecodeecommerce.repository.UserRepository;
import com.enigma.livecodeecommerce.utils.validation.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;
    public User register(User user){
        try {
            return userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public Iterable<User> findAll(Pageable pageable){
        try {
            return userRepository.findAll(pageable);
        }catch (DataEmptyException e){
            throw new DataEmptyException();
        }
    }
    public String login(User user){
        try{
            User getUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            String token = jwtUtil.generateToken("enigma");
            if(user.getEmail().equals(getUser.getEmail()) && user.getPassword().equals(getUser.getPassword())){
                return token;
            }else{
                throw new RuntimeException("Email or password incorrect");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
