package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.Request.createProductRequest;
import com.raj.JavaFullstackEcommerce.exception.ProductException;
import com.raj.JavaFullstackEcommerce.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(createProductRequest req);


    public  String deleteProduct(Long productId) throws ProductException;

    public  Product updateProduct(Long id, Product product) throws  ProductException;

    public  Product findProductById(Long id) throws  ProductException;

    public List<Product> findProductByCategory(String category);

    public Page<Product> getAllProduct(String  category,List<String> colors,
                                       List<String> sizes,Integer minPrice,Integer maxPrice,
    Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize);
}
