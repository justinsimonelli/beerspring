package com.beerspring.controller;

import com.beerspring.model.CheckIn;
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

    @RequestMapping("/")
    public String getHomePage(Model model) {
        User mark = new User("mark", "Mark", "Ryan");
        User justin = new User("justin", "Justin", "Simonelli");

        userRepository.save(mark);
        userRepository.save(justin);

        checkInRepository.save(new CheckIn(mark, new Date()));
        checkInRepository.save(new CheckIn(mark, new Date()));
        checkInRepository.save(new CheckIn(mark, new Date()));

        checkInRepository.save(new CheckIn(justin, new Date()));

        Iterable<User> users = userRepository.findAll();
        System.out.println("Users found with findAll():");
        System.out.println("-------------------------------");
        for (User user: users) {
            System.out.println(user + " has " + checkInRepository.findByUser(user).size() + " checkins.");
        }

        model.addAttribute("user", userRepository.findByUserName("mark").get(0));
        return "home";
    }

}
