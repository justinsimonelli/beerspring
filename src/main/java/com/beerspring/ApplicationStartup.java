package com.beerspring;

import com.beerspring.model.*;
import com.beerspring.repository.BeerListRepository;
import com.beerspring.repository.CheckInRepository;
import com.beerspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jxs699 on 2/17/15.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private BeerListRepository beerListRepository;


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {

        System.out.println("************************************************");
        System.out.println("onApplicationEvent(): adding initial users to DB");
        System.out.println("************************************************");

        User mark = new User("mark", "markryan1988@gmail.com", "Mark", "Ryan");
        User justin = new User("justinsims", "justin.simonelli@gmail.com", "Justin", "Simonelli");

        userRepository.save(mark);
        userRepository.save(justin);

        checkInRepository.save(new CheckIn(mark, new Date()));
        checkInRepository.save(new CheckIn(mark, new Date()));
        checkInRepository.save(new CheckIn(mark, new Date()));

        checkInRepository.save(new CheckIn(justin, new Date()));

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy HH:mm");

        Date dateIntended = null;
        Date today = new Date();
        Brewery glbc = new Brewery("Great Lakes Brewing Company");

        try {
            String intended = "02-21-2015";
            DateFormat format = new SimpleDateFormat("MM-dd-yy");
            dateIntended = format.parse(intended);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer("Lake Erie Monster", "Imperial IPA", new Double(9.5), glbc));
        beers.add(new Beer("Christmas Ale", "Spice Beer", new Double(7.5), glbc));
        beers.add(new Beer("Spacewalker", "Wheat", new Double(6.5), glbc));

        beerListRepository.save(new BeerList(justin, "Test List", beers, dateIntended, today, today));

        return;
    }

}
