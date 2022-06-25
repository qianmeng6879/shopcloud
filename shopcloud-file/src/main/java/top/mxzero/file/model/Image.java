package top.mxzero.file.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Image {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String url;
    private String prefix;
    private String introduction;
}
