package top.mxzero.common.api.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import top.mxzero.common.api.OrderFeignApi;
import top.mxzero.common.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class OrderFallbackFactory implements FallbackFactory<OrderFeignApi> {
    @Override
    public OrderFeignApi create(Throwable cause) {
        return new OrderFeignApi() {
            @Override
            public List<OrderDTO> list() {
                log.error("top.mxzero.orderservice.provider--失败回退");
                return new ArrayList<>();
            }

            @Override
            public OrderDTO get(Integer id) {
                log.error("top.mxzero.orderservice.provider--失败回退");
                return null;
            }
        };
    }
}
