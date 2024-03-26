package com.example.ecommerse.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Entity(name = "otp")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String otp;
    private boolean confirm;
    private LocalDateTime expired;
    @ManyToOne
    private OtpType otpType;
    @OneToOne
    private User user;

}
