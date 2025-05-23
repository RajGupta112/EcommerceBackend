package com.raj.JavaFullstackEcommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private  String description;

    private  int  price;

    private int discountPrice;

    private  int discountPercent;

    private int quantity;

    private String brand;

    private  String color;

    @Embedded
    @ElementCollection
    private Set<Size> size= new HashSet<>();

    private  String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating> ratings= new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews= new ArrayList<>();



    private int numratings;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createdAT;







}
