package com.humanbooster.sunset.repositories;


import com.humanbooster.sunset.models.CompletedOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompleteOrderRepository extends CrudRepository<CompletedOrder, String> {
    CompletedOrder findByPayId(String payId);
}
