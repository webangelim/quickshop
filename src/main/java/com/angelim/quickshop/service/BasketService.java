package com.angelim.quickshop.service;

import com.angelim.quickshop.client.response.PlatziProductResponse;
import com.angelim.quickshop.controller.request.BasketRequest;
import com.angelim.quickshop.entity.Basket;
import com.angelim.quickshop.entity.Product;
import com.angelim.quickshop.entity.Status;
import com.angelim.quickshop.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public BasketService(BasketRepository basketRepository, ProductService productService) {
        this.basketRepository = basketRepository;
        this.productService = productService;
    }

    public Basket createBasket(BasketRequest basketRequest) {

        basketRepository.findByClientAndStatus(basketRequest.clientId(),
                Status.OPEN).ifPresent(basket -> {
                    throw new IllegalStateException("There is already an open basket for this client");
        });


        List<Product> products = new ArrayList<>();

        basketRequest.products().forEach(product -> {
            PlatziProductResponse platziProductResponse = productService.getProductById(product.id());
            products.add(new Product(platziProductResponse.id(),
                    platziProductResponse.title(),
                    platziProductResponse.price(),
                    product.quantity()));
        });

        Basket basket = new Basket(basketRequest.clientId(),
                products,
                Status.OPEN);

        basket.calculateTotalPrice();

        return basketRepository.save(basket);
    }
}
