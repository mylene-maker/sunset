package com.humanbooster.sunset.repositories;


import com.humanbooster.sunset.models.CompletedOrder;
import org.springframework.data.repository.CrudRepository;

public interface CompleteOrderRepository extends CrudRepository<CompletedOrder, String> {
}
