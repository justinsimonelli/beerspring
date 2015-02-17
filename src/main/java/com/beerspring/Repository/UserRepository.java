package com.beerspring.repository;

import com.beerspring.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by markryan on 2/16/15.
 */
public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findByLastName(String lastName);

    List<User> findByUserName(String userName);

}
