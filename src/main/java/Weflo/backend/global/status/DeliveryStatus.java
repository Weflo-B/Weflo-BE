package Weflo.backend.global.status;

public enum DeliveryStatus {
    DELIVERY_START("배송시작"),
    COLLECTION_PROCESS("집화처리"),
    INTER_CITY_LOAD("간선상차"),
    INTER_CITY_UNLOAD("간선하차"),
    DELIVERY_COMPLETE("배송완료"),
    END("종료");

    private final String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}