package top.mxzero.userservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String province;
    private String city;
    private String area;
    private String detail;
    private String signerName;
    private String signerMobile;
    private Integer userId;
    private Date createTime;
    private Date updateTime;
    @TableLogic
    private boolean isDelete;
}
