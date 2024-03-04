package Weflo.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "motor_score")
    private Integer motorScore;

    @Column(name = "blade_score")
    private Integer bladeScore;

    @Column(name = "esc_score")
    private Integer escScore;

    @Column(name = "total_avg")
    private Integer totalAvg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id")
    private Drone drone;

}
