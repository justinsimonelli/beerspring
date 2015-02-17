package com.beerspring.controller;

import com.beerspring.Repository.UserRepository;
import com.beerspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by markryan on 2/16/15.
 */
@Controller
public class Home {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String getHomePage(Model model) {
        userRepository.save(new User("mark", "Mark", "Ryan"));
        userRepository.save(new User("justin", "Justin", "Simonelli"));

        Iterable<User> users = userRepository.findAll();
        System.out.println("Users found with findAll():");
        System.out.println("-------------------------------");
        for (User user: users) {
            System.out.println(user);
        }

        model.addAttribute("user", userRepository.findByUserName("mark").get(0));
        return "home";
    }

}
