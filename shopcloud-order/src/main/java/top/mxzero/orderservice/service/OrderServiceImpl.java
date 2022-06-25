package top.mxzero.orderservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mxzero.common.dto.AddressDTO;
import top.mxzero.common.dto.OrderDTO;
import top.mxzero.common.dto.PageDTO;
import top.mxzero.common.service.OrderService;
import top.mxzero.common.util.DeepBeanUtil;
import top.mxzero.orderservice.enums.OrderState;
import top.mxzero.orderservice.mapper.OrderMapper;
import top.mxzero.orderservice.model.Order;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public OrderDTO get(Integer id) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> list() {
        List<Order> orders = orderMapper.selectList(null);
        return DeepBeanUtil.copyListProperties(orders, OrderDTO::new);
    }

    @Override
    public PageDTO<OrderDTO> split(long current, long size) {
        IPage<Order> page = new Page<>(current, size);
        orderMapper.selectPage(page, null);

        PageDTO<OrderDTO> orderDTOPageDTO = new PageDTO<>();
        orderDTOPageDTO.setCurrent(page.getCurrent());
        orderDTOPageDTO.setSize(page.getRecords().size());
        orderDTOPageDTO.setTotal(page.getTotal());
        orderDTOPageDTO.setData(DeepBeanUtil.copyListProperties(page.getRecords(), OrderDTO::new));

        return orderDTOPageDTO;
    }

    private String getCode(int userId) {
        StringBuilder code = new StringBuilder();
        code.append("M");

        String s1 = String.valueOf(new Date(System.currentTimeMillis()).getTime() / 1000);
        for (int i = s1.length(); i < 12; i++) {
            code.append("0");
        }

        code.append(s1);
        log.info("s1 = {}", s1);

        String s2 = String.valueOf(Math.round((Math.random() + 1) * 1000));
        log.info("s2 = {}", s2);
        code.append(s2);

        String uid = String.valueOf(userId);
        log.info("uid = {}", uid);
        if (uid.length() > 4) {
            code.substring(uid.length() - 4);
        } else {
            for (int i = uid.length(); i < 4; i++) {
                code.append("0");
            }
            code.append(uid);
        }

        log.info("code = {}", code);
        log.info("length = {}", code.length());
        return code.toString();
    }

    @Override
    @Transactional
    public boolean add(OrderDTO dto) {
        Order order = new Order();
        BeanUtils.copyProperties(dto, order);

        order.setTotalPrice(dto.getProduct().getCostPrice() * dto.getProductCount());
        order.setState(OrderState.CREATED.getId());
        order.setCreateTime(new Date());
        order.setCode(getCode(dto.getUserId()));

        order.setUserId(dto.getUser().getId());

        AddressDTO address = dto.getAddress();
        order.setProvince(address.getProvince());
        order.setCity(address.getCity());
        order.setArea(address.getArea());
        order.setDetail(address.getDetail());
        order.setSignerName(address.getSignerName());
        order.setSignerMobile(address.getSignerMobile());


        order.setProductName(dto.getProduct().getTitle());
        order.setUnitPrice(dto.getProduct().getCostPrice());

        log.info("【service】新增订单开始，{}", order);
        int result = orderMapper.insert(order);
        log.info("【service】新增订单结束，{}", result);

        return result > 0;
    }

    @Override
    @Transactional
    public boolean update(OrderDTO dto) {
        Order order = new Order();
        BeanUtils.copyProperties(dto, order);
        order.setCreateTime(new Date());

        log.info("【service】修改订单开始，{}", order);
        int result = orderMapper.updateById(order);
        log.info("【service】修改订单结束，{}", result);

        return result > 0;
    }

    @Override
    @Transactional
    public boolean remove(Integer id) {
        log.info("【service】删除订单开始，{}", id);
        int result = orderMapper.deleteById(id);
        log.info("【service】删除订单结束，{}", result);
        return result > 0;
    }

    @Override
    public long count() {
        return orderMapper.selectCount(null);
    }

    @Override
    public List<OrderDTO> listByUserId(Integer userId) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id", userId);

        return DeepBeanUtil.copyListProperties(orderMapper.selectList(orderQueryWrapper), OrderDTO::new);
    }

    @Override
    public PageDTO<OrderDTO> splitByUserId(long current, long size, Integer userId) {
        Page<Order> page = new Page<>(current, size);

        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id", userId);

        orderMapper.selectPage(page, orderQueryWrapper);

        PageDTO<OrderDTO> orderDTOPageDTO = new PageDTO<>();
        orderDTOPageDTO.setCurrent(current);
        orderDTOPageDTO.setSize(page.getRecords().size());
        orderDTOPageDTO.setTotal(page.getTotal());
        orderDTOPageDTO.setData(DeepBeanUtil.copyListProperties(orderMapper.selectList(orderQueryWrapper), OrderDTO::new));

        return orderDTOPageDTO;
    }

    @Override
    public OrderDTO getByIdAndUserId(Integer userId, Integer orderId) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("id", orderId).eq("user_id", userId);

        Order order = orderMapper.selectOne(orderQueryWrapper);

        if (order == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setProvince(order.getProvince());
        addressDTO.setCity(order.getCity());
        addressDTO.setArea(order.getArea());
        addressDTO.setDetail(order.getDetail());
        addressDTO.setSignerName(order.getSignerName());
        addressDTO.setSignerMobile(order.getSignerMobile());

        return orderDTO;
    }

    @Override
    public boolean cancelOrder(Integer userId, Integer orderId) {
        OrderDTO orderDTO = getByIdAndUserId(userId, orderId);
        if (orderDTO == null) {
            return false;
        }

        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setState(OrderState.CANCELED.getId());

        return orderMapper.updateById(order) > 0;
    }

    @Override
    public boolean receiptOrder(Integer userId, Integer orderId) {
        OrderDTO orderDTO = getByIdAndUserId(userId, orderId);
        if (orderDTO == null) {
            return false;
        }

        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setState(OrderState.ARRIVED.getId());

        return orderMapper.updateById(order) > 0;
    }
}
