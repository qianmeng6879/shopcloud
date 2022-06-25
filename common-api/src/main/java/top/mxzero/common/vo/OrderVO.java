package top.mxzero.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderVO {
    @NotNull(message = "商品ID不能为空")
    private Integer productId;
    @NotNull(message = "购买的商品数量不能为空")
    private Integer productCount;
    @NotNull(message = "收货地址不能为空")
    public Integer addressId;
}
