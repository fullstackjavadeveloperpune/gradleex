package com.fullstack.controller;

import com.fullstack.entity.Product;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        log.info("########Trying to save data for Product: " + product.getProductName());
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/findbyid/{productId}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable int productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> update(@PathVariable int productId, @RequestBody Product product) {
        Product product1 = productService.findById(productId).orElseThrow(() -> new RecordNotFoundException("Product #ID Does Not Exist"));

        product1.setProductName(product.getProductName());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductLaunchDate(product.getProductLaunchDate());

        return ResponseEntity.ok(productService.update(product1));
    }

    @DeleteMapping("/deletebyid/{productId}")
    public ResponseEntity<String> deleteById(@PathVariable int productId) {
        productService.deleteById(productId);
        return ResponseEntity.ok("Product Deleted Successfully");
    }
}
