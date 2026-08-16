package com.angelim.quickshop.controller.request;

import java.util.List;

public record BasketRequest(Long clientId, List<ProductRequest> products) {
}
