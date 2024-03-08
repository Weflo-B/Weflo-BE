package Weflo.backend.service.deliverydetail;

import Weflo.backend.domain.DeliveryDetail;
import Weflo.backend.domain.OrderHistory;
import Weflo.backend.domain.Product;
import Weflo.backend.domain.User;
import Weflo.backend.dto.common.BuyerInfoDto;
import Weflo.backend.dto.common.PastDateInfoDto;
import Weflo.backend.dto.common.ProductInfoDto;
import Weflo.backend.dto.common.SellerInfoDto;
import Weflo.backend.dto.deliverydetail.response.ChangeDeliveryStatusResponse;
import Weflo.backend.dto.deliverydetail.response.DeliveryDetailResponse;
import Weflo.backend.repository.deliverydetail.DeliveryDetailRepository;
import Weflo.backend.repository.orderhistory.OrderHistoryRepository;
import Weflo.backend.repository.product.ProductRepository;
import com.amazonaws.services.kms.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryDetailServiceImpl implements DeliveryDetailService {
    private static String[] PAST_DATES = {"배송시작", "집화처리", "간선상차", "간선하차", "배송완료", "종료"};
    private final DeliveryDetailRepository deliveryDetailRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final ProductRepository productRepository;

    @Override
    public DeliveryDetailResponse getDeliveryDetails(Long orderHistoryId) {
        OrderHistory orderHistory = orderHistoryRepository.findById(orderHistoryId)
                .orElseThrow(() -> new NotFoundException("OrderHistory not found"));

        DeliveryDetail deliveryDetail = deliveryDetailRepository.findByOrderHistoryId(orderHistory.getId());
        Product productInfo = productRepository.findById(orderHistory.getProduct().getId())
                .orElseThrow(() -> new NotFoundException("OrderHistory not found"));
        User userInfo = orderHistory.getDrone().getUser();

        LocalDate startDate = orderHistory.getOrderDate();
        String curStatus = deliveryDetail.getDeliveryDetailStatus();

        return DeliveryDetailResponse.builder()
                .orderDate(startDate)
                .estimateDate(startDate.plusDays(5))
                .currentStatus(curStatus)
                .pastDates(createPastDates(curStatus, startDate))
                .productInfos(createProductInfoDto(productInfo, orderHistory.getAmount()))
                .sellerInfos(createSellerInfoDto(deliveryDetail))
                .buyerInfos(createBuyerInfoDto(userInfo))
                .build();
    }

    @Override
    public ChangeDeliveryStatusResponse changeDeliveryStatus(Long orderHistoryId, String changedStatus) {
        OrderHistory orderHistory = orderHistoryRepository.findById(orderHistoryId)
                .orElseThrow(() -> new NotFoundException("OrderHistory not found"));

        DeliveryDetail deliveryDetail = deliveryDetailRepository.findByOrderHistoryId(orderHistoryId);

        OrderHistory newOrderHistory =  OrderHistory.builder()
                .id(orderHistory.getId())
                .amount(orderHistory.getTotalPrice())
                .totalPrice(orderHistory.getTotalPrice())
                .orderName(orderHistory.getOrderName())
                .orderDate(orderHistory.getOrderDate())
                .orderHistoryStatus(changedStatus)
                .drone(orderHistory.getDrone())
                .product(orderHistory.getProduct())
                .build();

        DeliveryDetail newDeliveryDetail = DeliveryDetail.builder()
                .id(deliveryDetail.getId())
                .estimateDate(deliveryDetail.getEstimateDate())
                .deliveryDetailStatus(changedStatus)
                .deliveryCompany(deliveryDetail.getDeliveryCompany())
                .deliveryTel(deliveryDetail.getDeliveryTel())
                .invoiceNumber(deliveryDetail.getInvoiceNumber())
                .seller(deliveryDetail.getSeller())
                .orderHistory(deliveryDetail.getOrderHistory())
                .build();

        orderHistoryRepository.save(newOrderHistory);
        deliveryDetailRepository.save(newDeliveryDetail);

        return ChangeDeliveryStatusResponse.builder()
                .deliveryDetailStatus(changedStatus)
                .build();
    }

    private List<PastDateInfoDto> createPastDates(String status, LocalDate startDate) {
        List<PastDateInfoDto> pastDates = new ArrayList<>();

        for (int i = 0; i < Arrays.asList(PAST_DATES).indexOf(status); i++) {
            startDate = startDate.plusDays(1);

            pastDates.add(PastDateInfoDto.builder()
                    .status(PAST_DATES[i])
                    .date(startDate)
                    .build());
        }

        return pastDates;
    }

    private ProductInfoDto createProductInfoDto(Product product, Integer amount) {
        return ProductInfoDto.builder()
                .category(product.getCategory())
                .name(product.getName())
                .salePrice(product.getSalePrice())
                .amount(amount)
                .productImage(product.getProductImage())
                .build();
    }

    private SellerInfoDto createSellerInfoDto(DeliveryDetail deliveryDetail) {
        return SellerInfoDto.builder()
                .deliveryCompany(deliveryDetail.getDeliveryCompany())
                .deliveryTel(deliveryDetail.getDeliveryTel())
                .invoiceNumber(deliveryDetail.getInvoiceNumber())
                .seller(deliveryDetail.getSeller())
                .build();
    }

    private BuyerInfoDto createBuyerInfoDto(User user) {
        return BuyerInfoDto.builder()
                .name(user.getName())
                .tel(user.getTel())
                .address(user.getAddress())
                .build();
    }
}
