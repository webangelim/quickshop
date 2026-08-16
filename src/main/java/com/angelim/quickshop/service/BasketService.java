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

    public Basket getBasketById(Long id) {
        return basketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Basket not found"));
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

    public Basket updateBasket(Long basketId, BasketRequest request) {

        Basket basket = getBasketById(basketId);

        List<Product> products = new ArrayList<>();
        request.products().forEach(product -> {
            PlatziProductResponse platziProductResponse = productService.getProductById(product.id());
            products.add(new Product(platziProductResponse.id(),
                    platziProductResponse.title(),
                    platziProductResponse.price(),
                    product.quantity()));
        });

        basket.setProducts(products);
        basket.calculateTotalPrice();

        return basketRepository.save(basket);
    }
}
