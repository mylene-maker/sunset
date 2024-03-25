package com.humanbooster.sunset.services;


import com.humanbooster.sunset.models.Command;
import com.humanbooster.sunset.models.CompletedOrder;
import com.humanbooster.sunset.models.PaymentOrder;
import com.humanbooster.sunset.repositories.CommandRepository;
import com.humanbooster.sunset.repositories.CompleteOrderRepository;
import com.humanbooster.sunset.repositories.PaymentOrderRepository;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class PaypalService {

    @Autowired
    private PayPalHttpClient payPalHttpClient;

    @Autowired
    CompleteOrderRepository completeOrderRepository;

    @Autowired
    PaymentOrderRepository paymentOrderRepository;

    @Autowired
    CommandService commandService;

    @Autowired
    CommandRepository commandRepository;

    private Command command;

    public PaymentOrder createPayment(BigDecimal fee, Long commandId) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        AmountWithBreakdown amountWithBreakdown = new AmountWithBreakdown().currencyCode("EUR").value(fee.toString());
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(amountWithBreakdown);
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));
        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl("http://localhost:8080/panier/paypal/capture")
                .cancelUrl("http://localhost:8080/panier/paypal/cancel");
        orderRequest.applicationContext(applicationContext);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
        try {
            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.result();
            String redirectUrl = order.links().stream()
                    .filter(link -> "approve".equals(link.rel()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new)
                    .href();
            PaymentOrder paymentOrder = new PaymentOrder(order.id(), "success", redirectUrl);
            this.paymentOrderRepository.save(paymentOrder);
            return paymentOrder;
        }catch (IOException e){
            return new PaymentOrder("error");
        }
    }

    public CompletedOrder capturePayment(String token) {
        CompletedOrder completedOrder;
        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        try{
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            if (httpResponse.result().status() != null){
                completedOrder = new CompletedOrder(token, "success");
                commandService.isPay(2L, token); // ????????????????????????
            }else {
                completedOrder = new CompletedOrder("error");
            }
        }catch (IOException e){
            completedOrder = new CompletedOrder("error");
        }
        this.completeOrderRepository.save(completedOrder);

        return completedOrder;
    }


}
