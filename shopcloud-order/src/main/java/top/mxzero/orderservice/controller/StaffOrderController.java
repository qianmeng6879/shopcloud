package top.mxzero.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.common.api.UserFeignApi;
import top.mxzero.common.dto.OrderDTO;
import top.mxzero.common.service.OrderService;
import top.mxzero.jwt.annotation.JWTAuthentication;

@RestController
@Slf4j
public class StaffOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserFeignApi userFeignApi;

    @JWTAuthentication(role = "staff")
    @GetMapping("/orders")
    public Object orderList(Long current, Long size) {
        if (size != null) {
            if (current == null) {
                current = 1L;
            }
            return orderService.split(current, size);
        }
        return orderService.list();
    }

    @JWTAuthentication(role = "staff")
    @GetMapping("/orders/{id}")
    public Object getOrder(@PathVariable Integer id) {
        OrderDTO orderDTO = orderService.get(id);
        return orderDTO;
    }

}
