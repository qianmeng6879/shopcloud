package top.mxzero.productservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Category {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    private Integer parentId;
}

