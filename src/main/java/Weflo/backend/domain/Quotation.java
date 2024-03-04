package Weflo.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_id")
    private Long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "sum_price")
    private Integer sumPrice;

}
