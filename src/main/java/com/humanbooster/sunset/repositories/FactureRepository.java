package com.humanbooster.sunset.repositories;

import com.humanbooster.sunset.models.Facture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FactureRepository  extends CrudRepository<Facture, Long> {


}
