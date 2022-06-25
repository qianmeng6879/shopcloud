package top.mxzero.productservice.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.mxzero.common.dto.UserDTO;
import top.mxzero.jwt.annotation.JWTAuthentication;
import top.mxzero.jwt.service.ITokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private ITokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true; // 拦截
        if (!(handler instanceof HandlerMethod)) {  // 类型不匹配
            return flag;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler; // 因为需要对Action进行解析处理
        Method method = handlerMethod.getMethod(); // 获取调用的方法对象
        if (method.isAnnotationPresent(JWTAuthentication.class)) {
            JWTAuthentication annotation = method.getAnnotation(JWTAuthentication.class);
            if (annotation.require()) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                String token = request.getHeader("token");
                if (token == null || "".equals(token)) {
                    token = request.getParameter("token");
                }
                if (token == null || "".equals(token)) {
                    flag = false;
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                } else {
                    if (!tokenService.verifyToken(token)) {
                        flag = false;
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    } else {
                        UserDTO userDTO = JSONObject.parseObject(tokenService.parseToken(token).getBody().getSubject(), UserDTO.class);
                        // 需要管理员角色时
                        if (annotation.role().equals("staff") && !userDTO.getStaff()) {
                            flag = false;
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        }
                        if(flag){
                            request.setAttribute("USERID", userDTO.getId());
                            request.setAttribute("ROLE", userDTO.getStaff() ? "staff" : "user");
                        }
                    }
                }
            }
        }


        if (!flag) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", response.getStatus());
            map.put("message", "身份认证失败");
            map.put("path", request.getRequestURI());
            map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            response.getWriter().print(JSONObject.toJSONString(map));
        }


        return flag;
    }
}
