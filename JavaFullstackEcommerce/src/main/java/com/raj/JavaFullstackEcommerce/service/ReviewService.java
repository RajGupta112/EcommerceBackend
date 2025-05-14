package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.exception.ProductException;
import com.raj.JavaFullstackEcommerce.model.Review;
import com.raj.JavaFullstackEcommerce.model.User;

public interface ReviewService {

    public Review createReview(ReviewRequest req, User user)throws ProductException;
}
