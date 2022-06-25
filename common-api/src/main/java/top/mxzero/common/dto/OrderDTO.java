package top.mxzero.common.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderDTO {
    private Integer id;
    private String code;
    @NotNull(message = "商品ID不能为空")
    private Integer productId;
    private String productName;
    @NotNull(message = "购买的商品数量不能为空")
    private Integer productCount;
    private ProductDTO product;
    @NotNull(message = "收货地址不能为空")
    private Integer addressId;
    private AddressDTO address;
    private Double unitPrice;
    private Double totalPrice;
    private Integer userId;
    private UserDTO user;
    private Integer state;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String province;
    private String city;
    private String area;
    private String detail;
    private String signerName;
    private String signerMobile;
}
