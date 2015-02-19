package com.beerspring.controller;

import com.beerspring.model.Beer;
import com.beerspring.model.BeerList;
import com.beerspring.model.CheckIn;
import com.beerspring.repository.BeerListRepository;
import com.beerspring.repository.CheckInRepository;
import com.beerspring.repository.UserRepository;
import com.beerspring.model.User;
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
        System.out.println("Users found with findAll():");
        System.out.println("-------------------------------");
        for (User user: users) {
            System.out.println(user + " has " + checkInRepository.findByUser(user).size() + " checkins.");
        }

        User user = userRepository.findByUserName("justinsims").get(0);
        Iterable<CheckIn> checkIns = checkInRepository.findByUser(user);

        Iterable<BeerList> lists = beerListRepository.findAll();
        System.out.println("-------------------------------");
        for (BeerList list: lists) {
            System.out.println(user + " has " + beerListRepository.findByUser(user).size() + " beer list(s).");
        }

        BeerList beerList = beerListRepository.findByUser(user).get(0);

        for( Beer beer : beerList.getBeers() )
        {
            System.out.println("beer: " + beer);
        }

        model.addAttribute("user", user );
        model.addAttribute("checkins", checkIns);
        model.addAttribute("beerLists", beerList);
        return "home";
    }

}
