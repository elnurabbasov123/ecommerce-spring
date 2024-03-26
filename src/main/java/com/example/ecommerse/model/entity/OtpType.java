package com.example.ecommerse.model.entity;

import com.example.ecommerse.model.enums.type.OtpName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mapstruct.EnumMapping;

@Getter
@Entity(name = "otp_types")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OtpName name;
}
