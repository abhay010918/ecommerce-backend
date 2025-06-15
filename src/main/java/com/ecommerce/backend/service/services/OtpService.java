package com.ecommerce.backend.service.services;

import com.ecommerce.backend.entity.Otp;
import com.ecommerce.backend.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpService {

    @Value("${otp.expiry.minutes}")
    private int otpExpiryMinutes;

    private final OtpRepository otpRepository;
    private final TwilioService twilioService;

    public OtpService(OtpRepository otpRepository, TwilioService twilioService) {
        this.otpRepository = otpRepository;
        this.twilioService = twilioService;
    }

    public void generateAndSendOtp(String phoneNumber){
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);

        Otp otpEntity = new Otp();
        otpEntity.setPhoneNumber(phoneNumber);
        otpEntity.setOtp(otp);
        otpEntity.setCreatedAt(LocalDateTime.now());
        otpEntity.setVerifyToken(false);
        otpRepository.save(otpEntity);

        twilioService.sendOtp(phoneNumber, otp);

    }

    public boolean verifyOtp(String phoneNumber, String inputOtp) {

        Optional<Otp> optionalOtp =
                otpRepository.findOtpByPhoneNumberOrderByCreatedAtDesc(phoneNumber);

        if (optionalOtp.isEmpty()) {
            return false;
        }
        Otp otp = optionalOtp.get();
        if (otp.isVerifyToken()) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        if (otp.getOtp().equals(inputOtp)
                && otp.getCreatedAt().plusMinutes(otpExpiryMinutes).isAfter(now)
        ) {
            otp.setVerifyToken(true);
            otpRepository.save(otp);
            return true;
        }
        return false;
    }
}
