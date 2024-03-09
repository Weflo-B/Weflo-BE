package Weflo.backend.dto.common;

import Weflo.backend.domain.Drone;
import Weflo.backend.domain.OrderHistory;
import Weflo.backend.domain.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.hibernate.query.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPartsDto {
    private Long id;
    private String droneImg;
    private String nickname;
    private Integer balanceScore;
    private Integer totalScore;
    private LocalDate orderDate;
    private LocalDate estimateDate;
    private List<ProductInfoDto> productsInfo;
    private List<AbnormalPartsDto> abnormalities;

//    public static OrderPartsDto of(OrderHistory orderHistory, List<ProductInfoDto> productInfoDtoList) {
//        return builder()
//                .id(orderHistory.getDrone().getId())
//                .droneImg("https://weflo.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2024-03-07+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+3.48.15.png")
//                .nickname(orderHistory.getDrone().getNickname())
//                .balanceScore(orderHistory.getDrone().getCheckHistory().getBalanceScore())
//                .totalScore(orderHistory.getDrone().getCheckHistory().getTotalScore())
//                .orderDate(orderHistory.getOrderDate())
//                .estimateDate(orderHistory.getOrderDate().plusDays(3))
//                .productsInfo(productInfoDtoList)
//                .build();
//    }

//    public static OrderPartsDto of(OrderHistory orderHistory, List<ProductInfoDto> productInfoDtoList,List<AbnormalPartsDto> abnormalPartsDtos) {
//        return builder()
//                .id(orderHistory.getDrone().getId())
//                .droneImg("https://weflo.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2024-03-07+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+3.48.15.png")
//                .nickname(orderHistory.getDrone().getNickname())
//                .balanceScore(orderHistory.getDrone().getCheckHistory().getBalanceScore())
//                .totalScore(orderHistory.getDrone().getCheckHistory().getTotalScore())
//                .orderDate(orderHistory.getOrderDate())
//                .estimateDate(orderHistory.getOrderDate().plusDays(3))
//                .productsInfo(productInfoDtoList)
//                .abnormalities(abnormalPartsDtos)
//                .build();
//    }

    public static OrderPartsDto of(Drone findDrone, List<ProductInfoDto> productInfoDtoList, List<AbnormalPartsDto> abnormalPartsDtoList) {
        return OrderPartsDto.builder()
                .id(findDrone.getId())
                .droneImg(findDrone.getDroneImage())
                .nickname(findDrone.getNickname())
                .balanceScore(findDrone.getCheckHistory().getBalanceScore())
                .totalScore(findDrone.getCheckHistory().getTotalScore())
                .orderDate(LocalDate.now()) // 마지막 주문 날짜로 설정해야 할 수도 있습니다.
                .estimateDate(LocalDate.now().plusDays(3)) // 추정 배송 날짜는 비즈니스 로직에 따라 조정해야 할 수 있습니다.
                .productsInfo(productInfoDtoList)
                .abnormalities(abnormalPartsDtoList)
                .build();
    }

    public OrderPartsDto(Long id, String droneImg, String nickname, Integer balanceScore, Integer totalScore, LocalDate orderDate, LocalDate estimateDate, List<ProductInfoDto> productsInfo, List<AbnormalPartsDto> abnormalities) {
        this.id = id;
        this.droneImg = droneImg;
        this.nickname = nickname;
        this.balanceScore = balanceScore;
        this.totalScore = totalScore;
        this.orderDate = orderDate;
        this.estimateDate = estimateDate;
        this.productsInfo = productsInfo;
        this.abnormalities = abnormalities;
    }
}
