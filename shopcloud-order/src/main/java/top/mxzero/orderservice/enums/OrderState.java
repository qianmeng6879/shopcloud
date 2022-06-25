package top.mxzero.orderservice.enums;

import lombok.Getter;

@Getter
public enum OrderState {
    CANCELED(0, "已取消"),
    CREATED(1,"已创建"),
    UNPAID(2,"未支付"),
    PAID(3, "已支付"),
    WAIT_SEND(4, "待发货"),
    ARRIVED(5, "已送达"),
    COMPLETE(6, "已完成");

    private final int id;
    private final String value;

    private OrderState(int id, String value){
        this.id = id;
        this.value = value;
    }
}
