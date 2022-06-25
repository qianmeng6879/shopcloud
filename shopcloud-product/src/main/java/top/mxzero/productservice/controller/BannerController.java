package top.mxzero.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.jwt.annotation.JWTAuthentication;
import top.mxzero.productservice.model.Banner;
import top.mxzero.productservice.service.BannerServiceImpl;

import javax.validation.Valid;

@RestController
public class BannerController {

    @Autowired
    private BannerServiceImpl service;

    @GetMapping("/banners")
    public Object listBanner(){
        return service.list();
    }

    @JWTAuthentication(role = "staff")
    @PostMapping("/banners")
    public Object addBanner(@Valid @RequestBody Banner banner){
        return service.save(banner);
    }

    @JWTAuthentication(role = "staff")
    @DeleteMapping("/banners/{id}")
    public Object deleteBanner(@PathVariable("id") int id){
        return service.removeById(id);
    }

}
