package top.mxzero.common.service;


import top.mxzero.common.dto.OrderDTO;
import top.mxzero.common.dto.PageDTO;

import java.util.List;

/**
 * 订单业务接口
 * @author zero
 */
public interface OrderService extends BaseService<OrderDTO, Integer> {

    /**
     * 根据用户ID获取订单列表
     * @param id 用户ID
     * @return 以list返回订单数据
     */
    public List<OrderDTO> listByUserId(Integer id);

    /**
     * 根据用户ID获取订单分页列表
     * @param current 当前页偏移
     * @param size 每页大小
     * @param userId 用户ID
     * @return 以PageDTO封装分页数据
     */
    public PageDTO<OrderDTO> splitByUserId(long current, long size, Integer userId);


    /**
     * 获取指定用户的指定订单
     * @param userId 用户id
     * @param orderId 订单id
     * @return 返回订单DTO
     */
    public OrderDTO getByIdAndUserId(Integer userId, Integer orderId);

    /**
     * 取消订单
     * @param userId 用户id
     * @param orderId 订单id
     * @return 取消成功返回true，否者返回false
     */
    public boolean cancelOrder(Integer userId, Integer orderId);

    /**
     * 签收订单
     * @param userId 用户id
     * @param orderId 订单id
     * @return 成功返回true，否则返回false
     */
    public boolean receiptOrder(Integer userId, Integer orderId);

}
