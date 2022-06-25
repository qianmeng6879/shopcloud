package top.mxzero.productservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.mxzero.productservice.mapper.CategoryMapper;
import top.mxzero.productservice.model.Category;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements IService<Category> {
}
