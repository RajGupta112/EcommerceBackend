package com.raj.JavaFullstackEcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @ManyToOne
    @JsonIgnore
    private Order order;

    @ManyToOne
    private  Product product;

    private int quantity;

    private Integer price;

    private Integer discpuntedPrice;

    private  Long UserId;

    private LocalDateTime  deliveryDate;


}
