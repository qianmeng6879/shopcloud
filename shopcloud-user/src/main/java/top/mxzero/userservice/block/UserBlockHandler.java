package top.mxzero.userservice.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import top.mxzero.common.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserBlockHandler {

    public static Object listUserBlock(Long current, Long size, BlockException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        map.put("rule", e.getRule());
        map.put("result", new ArrayList<>(0));
        return map;
    }


    public static Object getUserBlock(int id, BlockException e) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername("【Block】username");
        return userDTO;
    }

}
