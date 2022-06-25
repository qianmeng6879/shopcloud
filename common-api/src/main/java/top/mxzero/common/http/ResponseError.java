package top.mxzero.common.http;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseError {
    private String error;
    private String exception;
    private String path;
    private int status;
    private Date time;
}
