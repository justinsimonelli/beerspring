package com.beerspring.repository;

import com.beerspring.model.Brewery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Sims on 2/19/15.
 */
public interface BreweryRepository extends CrudRepository<Brewery, Long> {

    List<Brewery> findByName( String name );

}
