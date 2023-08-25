package com.project.OrderService.Controllers;

import com.project.OrderService.Models.Requests.OrderCartRequest;
import com.project.OrderService.Models.Requests.OrderRequest;
import com.project.OrderService.Models.Responses.CartResponse;
import com.project.OrderService.Models.Responses.OrderResponse;
import com.project.OrderService.Models.Responses.ResponseOrderDetails;
import com.project.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;




    @PostMapping("/place")
    public ResponseEntity<OrderResponse> place(@RequestBody OrderRequest orderRequest)
    {
        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> add(@RequestBody OrderCartRequest orderCartRequest)
    {
        return new ResponseEntity<>(orderService.addCart(orderCartRequest), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<ResponseOrderDetails> show(@PathVariable("id") Long orderId)
    {
        return new ResponseEntity<>(orderService.showOrder(orderId), HttpStatus.OK);
    }
}
