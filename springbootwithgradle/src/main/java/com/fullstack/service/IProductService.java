package com.fullstack.service;

import com.fullstack.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Product save(Product product);

    Optional<Product> findById(int productId);

    List<Product> findAll();

    Product update(Product product);

    void deleteById(int productId);
}
