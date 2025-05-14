package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.Request.AddItemRequest;
import com.raj.JavaFullstackEcommerce.exception.ProductException;
import com.raj.JavaFullstackEcommerce.model.Cart;
import com.raj.JavaFullstackEcommerce.model.CartItem;
import com.raj.JavaFullstackEcommerce.model.Product;
import com.raj.JavaFullstackEcommerce.model.User;
import com.raj.JavaFullstackEcommerce.repository.CartRepository;
import com.raj.JavaFullstackEcommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImplimentation implements CartService {
    private CartRepository cartRepository;
     private UserRepository userRepository;
     private CartItemService cartItemService;
     private ProductService productService;
    @Override
    public Cart createCart(User user) {
       Cart cart= new Cart();
       cart.setUser(user);
        return cartRepository.save(cart);

    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart=cartRepository.findByUserId(userId);
        Product product=productService.findProductById(req.getProductId());
        CartItem isPresent=cartItemService.isCartItemExist(cart,product,req.getSize(),userId);
        if(isPresent==null){
            CartItem cartItem= new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);

            int price=req.getQuantity() * product.getDiscountPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createdCartItem=cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }




        return "Item Add To Cart";
    }

    public Cart findUserCart(Long userId) {
    Cart cart=cartRepository.findByUserId(userId);

    int totalPrice=0;
    int totalDiscountedPrice=0;
    int totalItem=0;

    for(CartItem cartItem:cart.getCartItems()){
        totalPrice=totalPrice+cartItem.getPrice();
        totalDiscountedPrice=totalDiscountedPrice+cartItem.getDiscountedPrice();
        totalItem=totalItem+cartItem.getQuantity();
    }
     cart.setTotalDiscountedPrice(totalDiscountedPrice);
    cart.setTotalPrice(totalPrice);
    cart.setTotalItem(totalItem);
    cart.setDiscount(totalPrice-totalDiscountedPrice);
    return cartRepository.save(cart);
    }
}
