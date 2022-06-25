package top.mxzero.common.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.mxzero.common.api.config.FeignConfig;
import top.mxzero.common.api.fallback.UserFallbackFactory;
import top.mxzero.common.dto.AddressDTO;
import top.mxzero.common.dto.UserDTO;

import java.util.List;
import java.util.Map;

@FeignClient(value = "userservice.provider", configuration = FeignConfig.class, fallbackFactory = UserFallbackFactory.class)
public interface UserFeignApi {

    @GetMapping("/users/{id}")
    public UserDTO get(@PathVariable("id") Integer id);

    @GetMapping("/users")
    public List<UserDTO> list();

    @RequestMapping("/users/header")
    public Map<String, Object> header();

    @GetMapping("/address/{id}")
    public AddressDTO getAddress(@PathVariable("id") Integer id);

}
