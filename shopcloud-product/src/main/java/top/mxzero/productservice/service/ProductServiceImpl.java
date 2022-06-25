package top.mxzero.productservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.common.dto.PageDTO;
import top.mxzero.common.dto.ProductDTO;
import top.mxzero.common.service.ProductService;
import top.mxzero.common.util.DeepBeanUtil;
import top.mxzero.productservice.mapper.ProductMapper;
import top.mxzero.productservice.model.Product;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ProductDTO get(Integer id) {
        Product product = productMapper.selectById(id);
        if(product == null){
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        return productDTO;
    }

    @Override
    public List<ProductDTO> list() {
        List<Product> products = productMapper.selectList(null);

        return DeepBeanUtil.copyListProperties(products, ProductDTO::new);
    }

    @Override
    public PageDTO<ProductDTO> split(long current, long size) {
        Page<Product> page = new Page<>(current, size);
        productMapper.selectPage(page, null);

        return new PageDTO<>(page.getCurrent(), page.getRecords().size(), page.getTotal(), DeepBeanUtil.copyListProperties(page.getRecords(), ProductDTO::new));
    }

    @Override
    public boolean add(ProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        product.setCreateTime(new Date());
        int result = productMapper.insert(product);

        return result > 0;
    }

    @Override
    public boolean update(ProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        product.setUpdateTime(new Date());
        int result = productMapper.updateById(product);

        return result > 0;
    }

    @Override
    public boolean remove(Integer id) {
        int result = productMapper.deleteById(id);

        return  result > 0;
    }

    @Override
    public long count() {
        return productMapper.selectCount(null);
    }

    @Override
    public List<ProductDTO> listByCategoryId(Integer categoryId) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("category_id", categoryId);

        List<Product> productList = productMapper.selectList(productQueryWrapper);

        return DeepBeanUtil.copyListProperties(productList,ProductDTO::new);
    }

    @Override
    public PageDTO<ProductDTO> splitByCategoryId(Integer categoryId, long current, long size) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("category_id", categoryId);

        Page<Product> page = new Page<>(current, size);
        productMapper.selectPage(page, productQueryWrapper);

        return new PageDTO<>(page.getCurrent(), page.getRecords().size(), page.getTotal(), DeepBeanUtil.copyListProperties(page.getRecords(), ProductDTO::new));

    }
}
