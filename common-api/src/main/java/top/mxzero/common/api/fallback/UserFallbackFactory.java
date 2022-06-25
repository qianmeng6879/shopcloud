package top.mxzero.common.api.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import top.mxzero.common.api.UserFeignApi;
import top.mxzero.common.dto.AddressDTO;
import top.mxzero.common.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class UserFallbackFactory implements FallbackFactory<UserFeignApi> {
    @Override
    public UserFeignApi create(Throwable cause) {
        return new UserFeignApi() {
            @Override
            public List<UserDTO> list() {
                log.error("userservice.provider-list--失败回退");
                return new ArrayList<>();
            }

            @Override
            public Map<String, Object> header() {
                return new HashMap<>();
            }

            @Override
            public AddressDTO getAddress(Integer id) {
                return null;
            }

            @Override
            public UserDTO get(Integer id) {
                log.error("userservice.provider-get--失败回退");
                UserDTO userDTO = new UserDTO();
                userDTO.setUsername("【Fallback-username】");
                userDTO.setPassword("【Fallback-password】");
                return userDTO;
            }
        };
    }
}
