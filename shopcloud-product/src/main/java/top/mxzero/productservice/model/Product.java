package top.mxzero.productservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String title;
    private String description;
    private String mainPicture;
    private Double toPrice;
    private Double costPrice;
    private Integer stock;
    private Integer salesVolume;
    private String unit;
    private boolean hot;
    private Integer categoryId;
    @TableField(exist = false)
    private Category category;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    private boolean isDelete;
}
