package com.example.backend.Service;

import com.example.backend.Repository.UserRepository;
import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.SignupRequest;
import com.example.backend.entity.User;
import com.example.backend.enums.UserRole;
import com.example.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already in use";
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole(UserRole.valueOf("USER")); // mặc định


        userRepository.save(user);

        return "Signup successful";
    }
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }
    return jwtUtil.generateToken(user.getEmail());
    }
}