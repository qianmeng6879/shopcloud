package top.mxzero.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ProductDTO {
    private Integer id;
    private String code;
    @NotBlank(message = "商品标题不能为空")
    private String title;
    private String description;
    @NotBlank(message = "商品主图不能为空")
    private String mainPicture;
    @NotNull(message = "商品出售价格不能为空")
    private Double toPrice;
    @NotNull(message = "商品定价不能为空")
    private Double costPrice;
    @NotNull(message = "商品库存不能为空")
    private Integer stock;
    @NotBlank(message = "商品单位不能为空")
    private String unit;
    private Integer salesVolume;
    private boolean hot;
    @NotNull(message = "商品分类不能为空")
    private Integer categoryId;
    private Date createTime;
    private Date updateTime;
}
