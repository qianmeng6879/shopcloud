package top.mxzero.common.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class AddressDTO {
    private Integer id;
    @NotBlank(message = "省份不能为空")
    @Length(max = 15)
    private String province;
    @NotBlank(message = "城市不能为空")
    @Length(max = 15)
    private String city;
    @NotBlank(message = "地区不能为空")
    @Length(max = 15)
    private String area;
    @NotBlank(message = "详细地址不能为空")
    @Length(max = 50)
    private String detail;
    @NotBlank(message = "收件人姓名不能为空")
    @Length(max = 8)
    private String signerName;
    @NotBlank(message = "收件人联系电话不能为空")
    @Length(max = 11, min = 11)
    private String signerMobile;
    private Date createTime;
    private Date updateTime;
    private Integer userId;

}
