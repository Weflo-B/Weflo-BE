package Weflo.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class CheckHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_history_id")
    private Long id;

    @Column(name = "check_date")
    private LocalDate checkDate;

    @Column(name = "location")
    private String location;

    @Column(name = "balance_score")
    private Integer balanceScore;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "issue")
    private String issue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id")
    private Drone drone;

}
