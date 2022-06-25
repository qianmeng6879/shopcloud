package top.mxzero.userservice.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String picture;
    private Double balance;
    private Date createTime;
    private Date updateTime;
    private Boolean staff;
    @TableLogic
    private boolean isDelete;
}