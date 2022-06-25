package top.mxzero.productservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.mxzero.productservice.model.Category;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
