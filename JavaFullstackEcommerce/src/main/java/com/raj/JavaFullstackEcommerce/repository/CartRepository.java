package com.raj.JavaFullstackEcommerce.repository;

import com.raj.JavaFullstackEcommerce.model.Cart;
import com.raj.JavaFullstackEcommerce.model.CartItem;
import com.raj.JavaFullstackEcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends JpaRepository<Cart,Long> {

    @Query("SELECT c FROM Cart c.userid=:userId")
    public Cart findByUserId(@Param("userId")   Long userId);


}
