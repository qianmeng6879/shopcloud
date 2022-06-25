package top.mxzero.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.mxzero.file.Mapper.ImageMapper;
import top.mxzero.file.model.Image;

@Service
public class ImageService extends ServiceImpl<ImageMapper, Image> implements IService<Image> {
}
