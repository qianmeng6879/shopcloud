package top.mxzero.common.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.mxzero.common.api.config.FeignConfig;
import top.mxzero.common.dto.ProductDTO;

@FeignClient(value = "productservice.provider", configuration = FeignConfig.class)
public interface ProductFeignApi {

    @GetMapping("/products/{id}")
    public ProductDTO get(@PathVariable("id") Integer id);

}
