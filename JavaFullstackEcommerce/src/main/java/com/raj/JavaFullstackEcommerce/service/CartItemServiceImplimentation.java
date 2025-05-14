package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.exception.CartItemException;
import com.raj.JavaFullstackEcommerce.exception.UserException;
import com.raj.JavaFullstackEcommerce.model.Cart;
import com.raj.JavaFullstackEcommerce.model.CartItem;
import com.raj.JavaFullstackEcommerce.model.Product;
import com.raj.JavaFullstackEcommerce.model.User;
import com.raj.JavaFullstackEcommerce.repository.CartItemRepository;
import com.raj.JavaFullstackEcommerce.repository.CartRepository;
import com.raj.JavaFullstackEcommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImplimentation implements CartItemService {
    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private UserRepository userRepository;
    private  UserService userService;
    @Override
    public CartItem createCartItem(CartItem cartItem) {

        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountPrice() * cartItem.getQuantity());

        CartItem createdCartItem= cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item=findCartItemById(id);
        User user=userRepository.findById(item.getUserId())
                .orElseThrow(() -> new UserException("User not found"));

        if(user.getId().equals(userId)){
                    item.setQuantity(cartItem.getQuantity());
                    item.setPrice(item.getQuantity()* item.getProduct().getPrice());
                    item.setDiscountedPrice(item.getProduct().getDiscountPrice()*item.getQuantity());
                }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem=cartItemRepository.isCartItemExist(cart,product,size,userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
      CartItem item=findCartItemById(cartItemId);
      User user=userService.findUserById(item.getUserId());
      User requser=userService.findUserById(userId);
      if(requser.getId().equals(userId)){
          cartItemRepository.deleteById(cartItemId);
      }else {
          throw  new UserException("you can't remove another users item");
      }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {

        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        if(cartItem.isPresent()){
           return cartItem.get();
        }else {
            throw  new CartItemException("cartItem not found with id"+cartItemId);
        }

    }
}
