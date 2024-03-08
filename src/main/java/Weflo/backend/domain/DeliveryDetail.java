package Weflo.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class DeliveryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_detail_id")
    private Long id;

    @Column(name = "estimate_date")
    private LocalDate estimateDate;

    @Column(name = "delivery_detail_status")
    private String deliveryDetailStatus;

    @Column(name = "delivery_company")
    private String deliveryCompany;

    @Column(name = "delivery_tel")
    private String deliveryTel;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "seller")
    private String seller;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_history_id")
    private OrderHistory orderHistory;
}
