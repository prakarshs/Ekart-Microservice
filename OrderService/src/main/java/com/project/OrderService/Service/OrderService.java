package com.project.OrderService.Service;

import com.project.OrderService.Models.Requests.OrderCartRequest;
import com.project.OrderService.Models.Requests.OrderRequest;
import com.project.OrderService.Models.Responses.CartResponse;
import com.project.OrderService.Models.Responses.OrderData;
import com.project.OrderService.Models.Responses.OrderResponse;
import com.project.OrderService.Models.Responses.ResponseOrderDetails;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);

    ResponseOrderDetails showOrder(Long orderId);

    CartResponse addCart(OrderCartRequest orderCartRequest);

    List<OrderData> showData();
}
