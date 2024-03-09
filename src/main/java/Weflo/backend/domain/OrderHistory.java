package Weflo.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_history_id")
    private Long id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_history_status")
    private String orderHistoryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void updateOrderHistoryStatus(String status) {
        if (this.orderHistoryStatus.equals("주문대기중")) {
            this.orderHistoryStatus = status;
        }
    }
}
