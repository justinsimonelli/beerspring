package com.beerspring.repository;

import com.beerspring.model.CheckIn;
import com.beerspring.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by markryan on 2/16/15.
 */
public interface CheckInRepository extends CrudRepository<CheckIn, Long>{
    List<CheckIn> findByUser(User user);
}
