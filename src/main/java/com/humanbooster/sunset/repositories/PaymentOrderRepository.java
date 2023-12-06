package com.humanbooster.sunset.repositories;


import com.humanbooster.sunset.models.PaymentOrder;
import org.springframework.data.repository.CrudRepository;

public interface PaymentOrderRepository extends CrudRepository<PaymentOrder, String> {

}
