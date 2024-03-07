package Weflo.backend.global.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {
    _GET_ALL_ORDER_PARTS_MESSAGE("주문할 모든 부품을 조회합니다."),
    _GET_ORDER_PARTS_DETAILS_MESSAGE("해당 드론의 부품 주문을 상세 조회합니다."),
    _GET_QUOTATION_MESSAGE("견적서를 조회합니다."),
    _ORDER_SUCCESS_MESSAGE("부품 주문에 성공했습니다."),
    _GET_ALL_ORDER_HISTORIES_MESSAGE("주문 배송 내역을 조회합니다."),
    _GET_DELIVERY_DETAILS_MESSAGE("특정 배송의 상세 내역을 조회합니다."),
    _CHANGE_DELIVERY_STATUS_MESSAGE("배송 상태 변경에 성공했습니다."),
    _GET_INSURANCE_MESSAGE("유저의 보험 페이지를 조회합니다."),
    _JOIN_SUCCESS_MESSAGE("유저가 보험 가입에 성공했습니다."),
    _GET_ALL_CHECK_HISTORIES_MESSAGE("모든 수리/점검 내역을 조회합니다."),
    _GET_CHECK_HISTORY_DETAILS_MESSAGE("특정 드론의 수리/점검 상세 내역을 조회합니다."),
    _DISPOSE_SUCCESS_MESSAGE("드론 폐기에 성공했습니다.");

    private final String message;
}