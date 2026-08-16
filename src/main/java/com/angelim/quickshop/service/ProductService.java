package com.angelim.quickshop.service;

import com.angelim.quickshop.client.PlatziStoreClient;
import com.angelim.quickshop.client.response.PlatziProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }

    public List<PlatziProductResponse> getAllProducts(){
        return platziStoreClient.getAllProducts();
    }

    public PlatziProductResponse getProductById(Long id){
        return platziStoreClient.getProductById(id);
    }

}
