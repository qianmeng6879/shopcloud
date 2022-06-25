package top.mxzero.orderservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private Integer productId;
    private String productName;
    private Integer productCount;
    private Double unitPrice;
    private Double totalPrice;
    private Integer userId;
    private Integer state;
    private String province;
    private String city;
    private String area;
    private String detail;
    private String signerName;
    private String signerMobile;
    private Date createTime;
    private Date updateTime;
    @TableField
    private boolean isDelete;
}