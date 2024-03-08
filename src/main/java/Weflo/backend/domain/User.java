package Weflo.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "login_password")
    private String loginPassword;

    @Column(name = "name")
    private String name;

    @Column(name = "telephone")
    private String tel;

    @Column(name = "address")
    private String address;

    @Column(name = "is_join")
    private Boolean isJoin;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(name = "insurance_rate")
    private Integer insuranceRate;
}
