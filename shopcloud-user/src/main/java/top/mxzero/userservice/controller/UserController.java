package top.mxzero.userservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.dto.UserDTO;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.service.UserService;
import top.mxzero.common.vo.UserVO;
import top.mxzero.jwt.annotation.JWTAuthentication;
import top.mxzero.jwt.service.ITokenService;
import top.mxzero.userservice.block.UserBlockHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ITokenService tokenService;

    /**
     * 用户列表查询
     * @param current 当前页偏移
     * @param size 每页大小
     * @return size为null时，以List返回所有数据，否则返回分页数据
     */
    @JWTAuthentication(role = "staff")
    @SentinelResource(value = "/users_list", blockHandlerClass = UserBlockHandler.class, blockHandler = "listUserBlock")
    @GetMapping("/users")
    public Object dataList(Long current, Long size) {
        if (size != null) {
            return userService.split(current, size);
        } else {
            return userService.list();
        }
    }

    /**
     * 通过ID查询用户
     * @param id 用户ID
     * @return ID存在返回对应的DTO数据，否则返回null
     */
    @SentinelResource(value = "/users_get", blockHandlerClass = UserBlockHandler.class, blockHandler = "getUserBlock")
    @GetMapping("/users/{id}")
    public Object getOntUser(@PathVariable int id) {
        return userService.get(id);
    }

    /**
     * 新增用户
     * @param userVO 包含用户基础信息的VO类
     * @return 新增成功返回true，否者返回false
     */
    @PostMapping("/users")
    public Object createData(@RequestBody @Valid UserVO userVO) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userVO, userDTO);
        return userService.add(userDTO);
    }

    /**
     * 根据用户ID修改用户信息
     * @param id 用户ID
     * @param userDTO 用户dto类
     * @return 修改成功返回true，否者返回false
     */
    @PutMapping("/users/{id}")
    public Object updateUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        return userService.update(userDTO);
    }

    /**
     * 通过ID删除用户信息
     * @param id 用户ID
     * @return 删除成功返回true，否者返回false
     */
    @DeleteMapping("/users/{id}")
    public Object deleteUser(@PathVariable Integer id) {
        return userService.remove(id);
    }

    /**
     * 测试使用，获取请求头信息
     * @param request 请求对象
     * @return 以map返回
     */
    @RequestMapping("/users/header")
    public Object header(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String,Object> map = new HashMap<>();
        while (headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key,value);
        }
        return map;
    }

    @PostMapping("/users/token/create")
    public Object login(@Valid @RequestBody UserVO userVO){
        UserDTO user = userService.getByUsername(userVO.getUsername());
        if(user == null){
            throw new ServiceException("用户名或密码错误");
        }

        if(!user.getPassword().equals(userVO.getPassword())){
            throw new ServiceException("用户名与密码不匹配");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("staff", user.getStaff());
        map.put("picture", user.getPicture());

        return tokenService.createToken(UUID.randomUUID().toString(), map);
    }

    @RequestMapping("/users/current")
    public Object currentUser(HttpServletRequest request){
        String token = request.getHeader("token");
        if(token == null || "".equals(token)){
            token = request.getParameter("token");
        }

        if(token == null || "".equals(token)){
            throw new ServiceException("身份未认证");
        }

        if(!tokenService.verifyToken(token)){
            throw new ServiceException("用户认证失败");
        }

        Claims body = tokenService.parseToken(token).getBody();
        String subject = body.getSubject();
        return JSONObject.parseObject(subject, UserDTO.class);
    }

}
