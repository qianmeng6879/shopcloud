package top.mxzero.common.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.mxzero.common.api.config.FeignConfig;
import top.mxzero.common.api.fallback.OrderFallbackFactory;
import top.mxzero.common.dto.OrderDTO;

import java.util.List;

@FeignClient(value = "orderservice.provider", configuration = FeignConfig.class, fallbackFactory = OrderFallbackFactory.class)
public interface OrderFeignApi {

    @GetMapping("/orders")
    public List<OrderDTO> list();

    @GetMapping("/orders/{id}")
    public OrderDTO get(@PathVariable Integer id);

}
