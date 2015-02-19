package com.beerspring.controller;

import com.beerspring.model.Beer;
import com.beerspring.model.BeerList;
import com.beerspring.model.Brewery;
import com.beerspring.repository.BeerListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sims on 2/18/15.
 */
@Controller
@RequestMapping("/api/beer-lists")
public class BeerListController {

    @Autowired
    private BeerListRepository beerListRepository;

    //HUGE HACK TO GET THIS TEMP WORKING
    private Brewery brew = new Brewery();

    @RequestMapping("/add/beer")
    public @ResponseBody String addBeerToList(@RequestParam(value="listId") long listId,
                                              @RequestParam(value = "name") String name,
                                              @RequestParam(value = "type") String type,
                                              @RequestParam(value = "abv")  Double abv,
                                              @RequestParam(value = "brewery") String brewery )
    {
        /**
         *
         * THIS WHOLE THING NEEDS TO BE CLEANED. JUST A POC.
         * NEED TO MAKE SURE YOU CAN'T ADD THE SAME BEER TO A LIST TWICE
         * IF IT ALREADY EXISTS IN THE LIST
         *
         *
          */
        BeerList list = beerListRepository.findOne(listId);

        if( list == null )
        {
            return ("cannot find brewery with given id= " + listId);
        }

        brew.setName(brewery);

        Beer beer = new Beer(name, type, abv, brew);

        if( list.getBeers().contains(beer) )
        {
            return ("this beer already exists in the list! beer= " + beer.getName() + ", list= " + list.getName());
        }

        list.getBeers().add(beer);

        beerListRepository.save(list);

        return "good";
    }

}
