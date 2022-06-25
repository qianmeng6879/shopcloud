package top.mxzero.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/errors/*") // 父路径
public class BlockController {
    @RequestMapping("block_handler")
    public Object globalBlockHandler() {
        Map<String, Object> result = new HashMap<>(); // 保存错误信息
        result.put("status", HttpServletResponse.SC_BAD_REQUEST); // 设置状态码
        result.put("message", "Blocked by Sentinel (flow limiting)");
        return result;
    }
}
