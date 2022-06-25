package top.mxzero.common.service;

import top.mxzero.common.dto.PageDTO;
import top.mxzero.common.dto.ProductDTO;

import java.util.List;

/**
 * @author zero
 * 商品业务接口
 */
public interface ProductService extends BaseService<ProductDTO, Integer> {
    /**
     * 根据分类ID查询商品列表
     * @param categoryId 分类ID
     * @return 以list返回productDTO
     */
    List<ProductDTO> listByCategoryId(Integer categoryId);

    /**
     * 根据分类ID分页查询商品列表
     * @param categoryId 分类ID
     * @param current 当前页偏移
     * @param size 每页带线啊哦
     * @return 以list返回productDTO
     */
    PageDTO<ProductDTO> splitByCategoryId(Integer categoryId, long current, long size);
}
