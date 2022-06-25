package top.mxzero.common.http;


import lombok.Data;

import java.util.Date;

@Data
public class ResponseData {
    private String message = "successful";
    private int status = 200;
    private Object data;
    private Date time;
}
