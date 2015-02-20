package com.beerspring.repository;

import com.beerspring.model.Beer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Sims on 2/19/15.
 */
public interface BeerRepository extends CrudRepository<Beer, Long> {

    List<Beer> findByName(String name);

    Beer findOneByExternalId( String externalId );

}
