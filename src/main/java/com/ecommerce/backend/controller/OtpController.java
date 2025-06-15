package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.service.services.OtpService;
import com.ecommerce.backend.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpService otpService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public OtpController(OtpService otpService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.otpService = otpService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String phoneNumber){
        otpService.generateAndSendOtp(phoneNumber);
        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(
            @RequestParam String phoneNumber,
            @RequestParam String otp
    ){
        boolean verifiedOtp = otpService.verifyOtp(phoneNumber, otp);
        if(!verifiedOtp){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or Expire OTP");
        }

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseGet(
                        () -> {
                            User newUser = new User();
                            newUser.setPhoneNumber(phoneNumber);
                            return userRepository.save(newUser);
                        }
                );

        String jwt = jwtUtil.generateToken(user);
        return ResponseEntity.ok("JWT Token: " + jwt);

    }
}
