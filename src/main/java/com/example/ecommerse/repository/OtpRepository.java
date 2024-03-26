package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Otp;
import com.example.ecommerse.model.enums.type.OtpName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    @Query("select o from otp o " +
            "inner join users u on o.user=u " +
            "where u.email =:email " +
            "and o.otp =:otp " +
            "and o.expired > current_timestamp " +
            "and o.confirm = true" +
            " and o.otpType.name =:otpName")
    Optional<Otp> getOtp(@Param("otp") String  otp,
                         @Param("email") String email,@Param("otpName") OtpName otpName);
}