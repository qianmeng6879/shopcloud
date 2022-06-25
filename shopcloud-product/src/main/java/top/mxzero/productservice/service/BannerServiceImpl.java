package top.mxzero.productservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.mxzero.productservice.mapper.BannerMapper;
import top.mxzero.productservice.model.Banner;

@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IService<Banner>   {
}
