package com.learn.main.productMeta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory/product-metas")
public class ProductMetaController {

    private final ProductMetaService productMetaService;

    public ProductMetaController(ProductMetaService productMetaService) {
        this.productMetaService = productMetaService;
    }

    @GetMapping
    public String getProducts() {
        return this.productMetaService.getProductMeta();
    }
}
