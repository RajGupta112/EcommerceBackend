package com.raj.JavaFullstackEcommerce.Request;

import com.raj.JavaFullstackEcommerce.model.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class createProductRequest {

    private String title;

    private String description;

    private  int price;

    private  int discountPrice;

    private  int dicountPercent;

    private  int quantity;

    private  String brand;

    private  String color;

    private Set<Size> sizes= new HashSet<>();


    private  String imageUrl;

    private  String  topLevelCategory;

    private  String  secondLevelCategory;
    private  String thirdLevelCategory;

}
