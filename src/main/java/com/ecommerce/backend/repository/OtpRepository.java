package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
  Optional<Otp> findOtpByPhoneNumberOrderByCreatedAtDesc(String phoneNumber);
}