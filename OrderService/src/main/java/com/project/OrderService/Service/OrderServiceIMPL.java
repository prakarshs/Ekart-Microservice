package com.project.OrderService.Service;

import com.project.OrderService.Entities.Order;
import com.project.OrderService.Errors.CustomError;
import com.project.OrderService.External.Client.StockService;
import com.project.OrderService.External.Objects.Stock;
import com.project.OrderService.Models.Details.OrderDetails;
import com.project.OrderService.Models.Details.StockDetails;
import com.project.OrderService.Models.Requests.OrderCartRequest;
import com.project.OrderService.Models.Requests.OrderRequest;
import com.project.OrderService.Models.Responses.CartResponse;
import com.project.OrderService.Models.Responses.OrderResponse;
import com.project.OrderService.Models.Responses.ResponseOrderDetails;
import com.project.OrderService.Repositories.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceIMPL implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StockService stockService;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public CartResponse addCart(OrderCartRequest orderCartRequest) {

        Stock stock;
        Order order = null;
        try {
            stock = restTemplate.getForObject("http://STOCK-SERVICE/stocks/show/"+orderCartRequest.getStockId(),Stock.class);
        }catch (Exception e)
        {
            throw new CustomError("Stock Not Found","Try With A Different StockId");
        }
        assert stock != null;

        if(orderCartRequest.getOrderQuantity()<stock.getStockQuantity())
        {
            log.info("ADDING TO CART");
            order = Order.builder()
                    .stockId(stock.getStockId())
                    .orderQuantity(orderCartRequest.getOrderQuantity())
                    .orderTime(Instant.now())
                    .orderStatus("CREATED")
                    .build();
            orderRepository.save(order);
        }
        else {
            log.info("INSUFFICIENT STOCKS");
            throw new CustomError("We Do Not Have Enough Stocks","Try With A Lower Quantity");
        }


        return CartResponse.builder()
                .message("YOUR CART HAS BEEN UPDATED!")
                .orderId(order.getOrderId())
                .stockName(stock.getStockName())
                .orderTime(Instant.now())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderRepository.findById(orderRequest.getOrderId()).orElseThrow(()->new CustomError("Cart Item Does Not Exist.","Try With A Different ID"));
        log.info("CHECKING FOR STOCK...");
        stockService.reduce(order.getStockId(), order.getOrderQuantity());
        Stock stock = restTemplate.getForObject("http://STOCK-SERVICE/stocks/show/"+order.getStockId(),Stock.class);
        assert stock != null;
        order.setOrderAmount(order.getOrderQuantity()*stock.getStockPrice());
        orderRepository.save(order);

        return OrderResponse.builder()
                .message("YOUR ORDER HAS BEEN PLACED!")
                .orderId(order.getOrderId())
                .orderTime(Instant.now())
                .orderAmount(order.getOrderAmount())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    @Override
    public ResponseOrderDetails showOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new CustomError("The OrderId Doesnt Exist","Try A Different OrderId"));
        if(order!=null){log.info("ORDER FOUND!");}else{log.info("ORDER NOT FOUND.");}
        Stock stock = restTemplate.getForObject("http://STOCK-SERVICE/stocks/show/"+order.getStockId(),Stock.class);

        StockDetails stockDetails = StockDetails.builder()
                .stockMessage("Here's Stock Information :")
                .stockId(stock.getStockId())
                .stockName(stock.getStockName())
                .stockPrice(stock.getStockPrice())
                .stockQuantity(stock.getStockQuantity())
                .stockTime(stock.getStockTime())
                .build();
        OrderDetails orderDetails = OrderDetails.builder()
                .orderMessage("Here's Your Order Information :")
                .orderId(order.getOrderId())
                .orderTime(order.getOrderTime())
                .orderStatus(order.getOrderStatus())
                .build();
        return ResponseOrderDetails.builder()
                .message("DETAILED ORDER INFORMATION:")
                .orderDetails(orderDetails)
                .stockDetails(stockDetails)
                .build();
    }
}
