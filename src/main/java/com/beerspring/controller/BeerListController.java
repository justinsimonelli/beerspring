package com.beerspring.controller;

import com.beerspring.model.Beer;
import com.beerspring.model.BeerList;
import com.beerspring.repository.BeerListRepository;
import com.beerspring.repository.BeerRepository;
import com.beerspring.repository.BreweryRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Sims on 2/18/15.
 */
@Controller
@RequestMapping("/api/beer-lists")
public class BeerListController {

    @Autowired
    private BeerListRepository beerListRepository;

    @Autowired
    private BreweryRepository breweryRepository;

    @Autowired
    private BeerRepository beerRepository;

    @RequestMapping( value="/add/beer/{listId}", method = RequestMethod.POST )
    public @ResponseBody String addBeerToList(@PathVariable long listId,
                                              @RequestBody List<Beer> beers)
    {

        if( logger().isDebugEnabled() )
        {
            logger().debug("addBeerToList(): entered method.");
        }

        BeerList list = beerListRepository.findOne(listId);

        if( list == null )
        {
            return ("cannot find brewery with given id= " + listId);
        }

        if( logger().isDebugEnabled() )
        {
            logger().debug("addBeerToList(): beer=" + beers);
        }

        for( Beer beer : beers )
        {
            //at this point we should have already hit the brewdb API for beer info
            if( list.getBeers().contains(beer) )
            {
                //need to return a 400 here?
                return ("this beer already exists in the list! beer= " + beer.getName() + ", list= " + list.getName());
            }

            list.getBeers().add(beer);
        }

        beerListRepository.save(list);

        return "good";
    }

    private Logger logger()
    {
        return Logger.getLogger(this.getClass());
    }

}
