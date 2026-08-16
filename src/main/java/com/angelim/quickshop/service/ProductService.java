package com.angelim.quickshop.service;

import com.angelim.quickshop.client.PlatziStoreClient;
import com.angelim.quickshop.client.response.PlatziProductResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts(){
        return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "product", key = "#productId")
    public PlatziProductResponse getProductById(Long productId){
        return platziStoreClient.getProductById(productId);
    }

}
