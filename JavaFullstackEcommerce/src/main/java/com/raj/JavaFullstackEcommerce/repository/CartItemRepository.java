package com.raj.JavaFullstackEcommerce.repository;

import com.raj.JavaFullstackEcommerce.model.Cart;
import com.raj.JavaFullstackEcommerce.model.CartItem;
import com.raj.JavaFullstackEcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT ci FROM CartIem ci Where ci.cart=:cart AND ci.product=:product AND ci.size=:size AND ci.userId=:userId")
    public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product, @Param("size") String size, @Param("userId") Long userId);
}
