package com.raj.JavaFullstackEcommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private Set<CartItem> cartItems= new HashSet<>();

    private double totalPrice;

    private int totalItem;

    private int totalDiscountedPrice;

    private  int discount;

}
