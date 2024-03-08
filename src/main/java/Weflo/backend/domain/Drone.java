package Weflo.backend.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "drone_status")
    private String droneStatus;

    @Column(name = "product_date")
    private String productDate;

    @Column(name = "last_check_date")
    private LocalDate lastCheckDate;

    @Column(name = "is_dispose")
    private Boolean isDispose;

    @Column(name = "need_parts_count")
    private Integer count;

    @Column(name = "drone_image")
    private String droneImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY)
    private List<Part> parts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_history_id")
    private CheckHistory checkHistory;

    public Integer setCount(Integer lowBladeCount, Integer lowMotorCount, Integer lowEscCount) {
        this.count = lowBladeCount + lowMotorCount + lowEscCount;
        return this.count;
    }

}
