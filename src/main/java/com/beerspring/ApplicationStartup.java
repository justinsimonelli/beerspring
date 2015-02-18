package com.beerspring;

import com.beerspring.model.User;
import com.beerspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by jxs699 on 2/17/15.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {

        System.out.println("************************************************");
        System.out.println("onApplicationEvent(): adding initial users to DB");
        System.out.println("************************************************");

        userRepository.save(new User("mark", "Mark", "Ryan"));
        userRepository.save(new User("justinsims", "Justin", "Simonelli"));

        return;
    }

}
