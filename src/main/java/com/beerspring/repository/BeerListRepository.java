package com.beerspring.repository;

import com.beerspring.model.BeerList;
import com.beerspring.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Sims on 2/18/15.
 */
public interface BeerListRepository extends CrudRepository<BeerList, Long> {

    List<BeerList> findByUser( User user);

}
