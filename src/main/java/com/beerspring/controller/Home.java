package com.beerspring.controller;

import com.beerspring.model.Beer;
import com.beerspring.model.BeerList;
import com.beerspring.model.CheckIn;
import com.beerspring.repository.BeerListRepository;
import com.beerspring.repository.CheckInRepository;
import com.beerspring.repository.UserRepository;
import com.beerspring.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by markryan on 2/16/15.
 */
@Controller
public class Home {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private BeerListRepository beerListRepository;


    @RequestMapping("/")
    public String getHomePage(Model model) {

        Iterable<User> users = userRepository.findAll();

        if( logger().isDebugEnabled() )
        {
            logger().debug("Users found with findAll():");
            for (User user: users)
            {
                logger().debug(user + " has " + checkInRepository.findByUser(user).size() + " checkins.");
            }
        }

        User user = userRepository.findByUserName("justinsims").get(0);
        Iterable<CheckIn> checkIns = checkInRepository.findByUser(user);

        Iterable<BeerList> lists = beerListRepository.findAll();
        System.out.println("-------------------------------");
        for (BeerList list: lists) {
            System.out.println(user + " has " + beerListRepository.findByUser(user).size() + " beer list(s).");
        }

        BeerList beerList = beerListRepository.findByUser(user).get(0);

        model.addAttribute("user", user );
        model.addAttribute("checkins", checkIns);
        model.addAttribute("beerLists", beerList);

        return "home";
    }

    private Logger logger()
    {
        return Logger.getLogger(this.getClass());
    }

}
