package com.raj.JavaFullstackEcommerce.Request;

import com.raj.JavaFullstackEcommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class RatingRequest {
    private Long productId;
    private double rating;
}
