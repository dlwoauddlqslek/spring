package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.ProductDto;
import web.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public boolean pRegister(ProductDto productDto){
        System.out.println("productDto = " + productDto);
        return productService.pRegister(productDto);
    }
    @GetMapping("/getAll")
    public List<ProductDto> getProductAll(){
        return productService.getProductAll();
    }
}
