package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.Request.createProductRequest;
import com.raj.JavaFullstackEcommerce.exception.ProductException;
import com.raj.JavaFullstackEcommerce.model.Category;
import com.raj.JavaFullstackEcommerce.model.Product;
import com.raj.JavaFullstackEcommerce.repository.CategoryRepository;
import com.raj.JavaFullstackEcommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImplimentation implements ProductService {

    private  UserService userService;

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;
    @Override
    public Product createProduct(createProductRequest req) {

        Category topLevel= categoryRepository.findByName(req.getTopLevelCategory());
        if(topLevel==null){
            Category topLevelCategory= new Category();
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel = categoryRepository.save(topLevelCategory);
        }

        Category  secondLevel= categoryRepository.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());
        if(secondLevel==null){
            Category seconfLevelCategory= new Category();
            seconfLevelCategory.setName(req.getSecondLevelCategory());
            seconfLevelCategory.setParentCategory(topLevel);
            seconfLevelCategory.setLevel(2);

            secondLevel=categoryRepository.save(seconfLevelCategory);
        }

        Category thirdLevel= categoryRepository.findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());

        if(thirdLevel==null){
            Category thirdLevelCategory= new Category();
            thirdLevelCategory.setName(req.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel= categoryRepository.save(thirdLevelCategory);
        }

        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountPrice(req.getDiscountPrice());
        product.setDiscountPercent(req.getDicountPercent());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSize(req.getSizes());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAT(LocalDateTime.now());
        Product savedProduct= productRepository.save(product);
        return savedProduct;

    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product=findProductById(productId);
        product.getSize().clear();
        productRepository.delete(product);


        return "Product deleted Successfully";
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductException {
        Product product1= findProductById(id);

        if(product1.getQuantity()!=0){
            product1.setQuantity(product.getQuantity());
        }
        return productRepository.save(product1);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> op= productRepository.findById(id);
        if(op.isPresent()){
            return op.get();
        }
        throw new ProductException("Product not found with id"+id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return List.of();
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
