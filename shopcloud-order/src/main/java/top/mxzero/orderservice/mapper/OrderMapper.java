package top.mxzero.orderservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.mxzero.orderservice.model.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
