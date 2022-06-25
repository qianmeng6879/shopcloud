package top.mxzero.common.service;


import top.mxzero.common.dto.PageDTO;

import java.util.List;

/**
 * 公共Service接口
 * @author zero
 * @param <T> DTO类型
 * @param <K> 主键
 */
public interface BaseService<T, K> {
    /**
     * 根据主键获取数据
     * @param id 主键值
     * @return 获取成功返回对应的DTO，否者返回null
     */
    public T get(K id);

    /**
     * 获取所有数据
     * @return 以List封装DTO返回
     */
    public List<T> list();

    /**
     * 分页查询数据
     * @param current 当前页偏移量
     * @param size 每页大小
     * @return 以PageDTO封装分页数据
     */
    public PageDTO<T> split(long current, long size);

    /**
     * 新增数据
     * @param dto 对应的DTO
     * @return 新增成功返回true，否则返回false
     */
    public boolean add(T dto);

    /**
     * 根据主键值修改数据
     * @param dto 对应的DTO
     * @return 修改成功返回true，否则返回false
     */
    public boolean update(T dto);

    /**
     * 根据主键值删除数据
     * @param id 主键值
     * @return 删除成功返回true，否则返回false
     */
    public boolean remove(K id);

    /**
     * 获取记录总数
     * @return 记录数量
     */
    public long count();
}
