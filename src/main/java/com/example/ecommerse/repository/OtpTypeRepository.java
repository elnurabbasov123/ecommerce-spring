package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.OtpType;
import com.example.ecommerse.model.enums.type.OtpName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpTypeRepository extends JpaRepository<OtpType, Long> {
    Optional<OtpType> getByName(OtpName otpName);
}