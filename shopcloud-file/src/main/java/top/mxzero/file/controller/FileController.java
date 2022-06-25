package top.mxzero.file.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.mxzero.file.config.FileConfig;
import top.mxzero.file.model.Image;
import top.mxzero.file.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class FileController {
    @Autowired
    private ImageService service;

    @Autowired
    private FileConfig fileConfig;

    @PostMapping("/files/upload")
    public Object fileUpload(MultipartFile file, String prefix, String introduction) throws IOException {
        String filename = null;

        if (prefix == null || "".equals(prefix)) {
            filename = UUID.randomUUID() + getExtendName(file.getOriginalFilename());
        } else {
            filename = prefix + File.separator + UUID.randomUUID() + getExtendName(file.getOriginalFilename());
        }

        File currentFile = new File(fileConfig.getPath() + filename);
        if (!currentFile.getParentFile().isDirectory()) {
            currentFile.getParentFile().mkdirs();
        }

        file.transferTo(currentFile);

        Image image = new Image();
        image.setUrl(filename);
        image.setPrefix(prefix);
        image.setIntroduction(introduction);
        service.save(image);

        Map<String, Object> map = new HashMap<>();
        map.put("time", new Date());
        map.put("size", file.getSize() / 1024 + "KB");
        map.put("filename", filename);
        map.put("prefix", prefix);
        map.put("introduction", introduction);
        return map;
    }

    @GetMapping(value = "/static/{parent}/{filename}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public BufferedImage staticFile(@PathVariable String parent, @PathVariable("filename") String filename) throws IOException {
        return ImageIO.read(new File(fileConfig.getPath() + parent + File.separator + filename));
    }

    @GetMapping("/files")
    public Object listFile(Long current, Long size){
        if(size != null){
            if(current == null){
                current = 1L;
            }
            Page<Image> page = new Page<>(current, size);
            service.page(page);
            Map<String,Object> map = new HashMap<>();
            map.put("current", page.getCurrent());
            map.put("size", page.getRecords().size());
            map.put("total", page.getTotal());
            map.put("data", page.getRecords());
            return map;
        }
        return service.list();
    }

    @DeleteMapping("/files/{id}")
    public Object deleteImage(@PathVariable("id") int id){
        Image image = service.getById(id);

        // 图片ID不存在时
        if(image == null){
            return false;
        }

        return service.removeById(id) & new File(fileConfig.getPath() + image.getUrl()).delete();
    }

    private static String getExtendName(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
