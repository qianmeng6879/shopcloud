package top.mxzero.productservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Banner {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank(message = "图片链接不能为空")
    private String imageUrl;
    private String introduction;
}

