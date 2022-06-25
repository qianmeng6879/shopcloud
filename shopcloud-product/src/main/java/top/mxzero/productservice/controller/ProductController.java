package top.mxzero.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.dto.ProductDTO;
import top.mxzero.common.service.ProductService;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public Object listProduct(
            @RequestParam(value = "current", required = false) Long current,
            @RequestParam(value = "size", required = false) Long size
    ) {
        if (size != null) {
            if (current == null){
                current = 1L;
            }

            // 分页查询
            return productService.split(current, size);
        }
        return productService.list();
    }

    @GetMapping("/products/{id}")
    public Object getProduct(@PathVariable("id") int id) {
        return productService.get(id);
    }

    @GetMapping("/products/category/{id}")
    public Object getByCategory(
            @PathVariable("id") int categoryId,
            @RequestParam(value = "current", required = false) Long current,
            @RequestParam(value = "size", required = false) Long size) {

        if (size != null) {
            if (current == null) {
                current = 0L;
            }
            return productService.splitByCategoryId(categoryId, current, size);
        }
        return productService.listByCategoryId(categoryId);
    }

    @JWTAuthentication(role = "staff")
    @PostMapping("/products")
    public Object addProduct(@Valid @RequestBody ProductDTO productDTO) {

        productDTO.setCode("P111111111111");
        return productService.add(productDTO);
    }

    @JWTAuthentication(role = "staff")
    @PutMapping("/products/{id}")
    public Object updateProduct(@PathVariable("id") int id, @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        return productService.update(productDTO);
    }

    @JWTAuthentication(role = "staff")
    @DeleteMapping("/products/{id}")
    public Object deleteProduct(@PathVariable("id") int id) {
        return productService.remove(id);
    }


}
