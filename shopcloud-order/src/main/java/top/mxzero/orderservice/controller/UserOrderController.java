package top.mxzero.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.api.ProductFeignApi;
import top.mxzero.common.api.UserFeignApi;
import top.mxzero.common.dto.AddressDTO;
import top.mxzero.common.dto.OrderDTO;
import top.mxzero.common.dto.ProductDTO;
import top.mxzero.common.dto.UserDTO;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.service.OrderService;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
public class UserOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserFeignApi userFeignApi;

    @Autowired
    private ProductFeignApi productFeignApi;

    @JWTAuthentication
    @GetMapping("/orders/current")
    public Object orderList(Long current, Long size, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("USERID");

        if (size != null) {
            if (current == null) {
                current = 1L;
            }
            return orderService.splitByUserId(current, size, userId);
        }
        return orderService.listByUserId(userId);
    }

    @JWTAuthentication
    @GetMapping("/orders/current/{id}")
    public Object getOrder(@PathVariable Integer id, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("USERID");

        OrderDTO orderDTO = orderService.getByIdAndUserId(userId, id);

        if (orderDTO != null) {

            // 当前订单不属于当前登录用户
            if(orderDTO.getUserId().intValue() != userId.intValue()){
                return null;
            }

            log.info("调用商品微服务--start");
            ProductDTO productDTO = productFeignApi.get(orderDTO.getProductId());
            log.info("调用商品微服务--end,result:{}", productDTO);

            orderDTO.setProduct(productDTO);

        }
        return orderDTO;
    }


    // 创建订单
    @PostMapping("/orders")
    @JWTAuthentication
    public Object createOrder(@Valid @RequestBody OrderDTO orderDTO,HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("USERID");
        orderDTO.setUserId(userId);

        ProductDTO productDTO = productFeignApi.get(orderDTO.getProductId());
        if(productDTO == null){
            throw new ServiceException("商品不存在");
        }

        AddressDTO address = userFeignApi.getAddress(orderDTO.getAddressId());

        if(address == null){
            throw new ServiceException("收获地址不存在");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);


        orderDTO.setAddress(address);
        orderDTO.setUser(userDTO);
        orderDTO.setProduct(productDTO);
        return orderService.add(orderDTO);
    }
}
