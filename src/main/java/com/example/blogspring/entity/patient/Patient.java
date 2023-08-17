package com.example.blogspring.entity.patient;

import com.example.blogspring.entity.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long patientSeq;

    @Column(nullable = false, length = 32)
    String loginId;

    @Column(nullable = false, length = 128)
    String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
