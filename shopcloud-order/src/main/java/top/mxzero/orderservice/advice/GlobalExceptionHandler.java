package top.mxzero.orderservice.advice;

import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.exception.ServiceNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler({NoHandlerFoundException.class, ServiceNotFoundException.class})
    public Object noHandlerFoundException(HttpServletRequest request, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        String requestURI = request.getRequestURI();
        Map<String, Object> map = new HashMap<>();
        map.put("path", requestURI);
        map.put("message", "Not Found");
        map.put("time", dateTimeFormatter.format(LocalDateTime.now()));
        map.put("status", HttpServletResponse.SC_NOT_FOUND);
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return map;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object httpRequestMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String requestURI = request.getRequestURI();
        map.put("path", requestURI);
        map.put("time", dateTimeFormatter.format(LocalDateTime.now()));
        map.put("message", "Request method '" + request.getMethod() + "' not supported");
        map.put("status", HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return map;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(422);
        List<String> errorMessages = new ArrayList<>(exception.getAllErrors().size());
        exception.getAllErrors().forEach(error->{
            errorMessages.add(error.getDefaultMessage());
        });
        Map<String,Object> map = new HashMap<>();
        map.put("status", 422);
        map.put("message", errorMessages);
        map.put("time", dateTimeFormatter.format(LocalDateTime.now()));
        map.put("path", request.getRequestURI());
        return map;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object httpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response){
        response.setStatus(400);
        Map<String,Object> map = new HashMap<>();
        map.put("status", 400);
        map.put("message", "缺失请求体");
        map.put("time", dateTimeFormatter.format(LocalDateTime.now()));
        map.put("path", request.getRequestURI());
        return map;
    }

    @ExceptionHandler(ServiceException.class)
    public Object customException(ServiceException exception, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(400);
        Map<String, Object> map = new HashMap<>();
        map.put("path", request.getRequestURI());
        map.put("message", exception.getMessage());
        map.put("time", dateTimeFormatter.format(LocalDateTime.now()));
        map.put("status", 400);
        return map;
    }
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public Object fileSizeLimitExceededException(FileSizeLimitExceededException exception, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        response.setStatus(400);
        map.put("path", request.getRequestURI());
        map.put("message", "文件大小限制在1MB");
        map.put("time", dateTimeFormatter.format(LocalDateTime.now()));
        map.put("status", HttpServletResponse.SC_BAD_REQUEST);

        return map;
    }

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception exception){
        log.error("error:{}",exception.getStackTrace()[0]);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        String requestURI = request.getRequestURI();
        Map<String, Object> map = new HashMap<>();
        map.put("path", requestURI);
        map.put("message", exception.getMessage());
        map.put("exception", exception.getClass().getName());
        map.put("time", dateTimeFormatter.format(LocalDateTime.now()));
        map.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return map;
    }
}
