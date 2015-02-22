package com.beerspring.controller;

import com.beerspring.model.Beer;
import com.beerspring.model.BeerList;
import com.beerspring.repository.BeerListRepository;
import com.beerspring.repository.BeerRepository;
import com.beerspring.repository.BreweryRepository;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
            return ("cannot find list with given id= " + listId);
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

    @RequestMapping( value="/add/list/new", method = RequestMethod.POST )
    public @ResponseBody String addNewBeerList(@RequestBody BeerList beerList)
    {
        if( logger().isDebugEnabled() )
        {
            logger().debug("addNewBeerList(): entered method.");
        }

        if( beerList != null )
        {
            //see if it already exists
            List<BeerList> lists = (List<BeerList>) beerListRepository.findByName(beerList.getName());
            BeerList list = null;

            if(CollectionUtils.isNotEmpty(lists) && lists.size() > 1)
            {
                return "more than one list! which one do you want?";
            }
            else if( CollectionUtils.isNotEmpty(lists) )
            {
                list = lists.get(0);
            }
            else
            {
                list = beerList;
            }

            if( logger().isDebugEnabled())
            {
                logger().debug("Creating a new BeerList: " + list);
            }

            beerListRepository.save(list);

        }

        return "good";
    }

    @RequestMapping( value="/proxy/", method = RequestMethod.GET )
    public @ResponseBody String getBeerDBInformation(@RequestParam String q)
    {
        //fix this to use constants and shit
        try{
            URL beerDBUrl = new URL("https://api.brewerydb.com/v2/search/?key=f455ba07edfba1d9b8261a6166fada13&type=beer&withBreweries=y&p=1&q=" + q);
            URLConnection conn = beerDBUrl.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null)
            {
                System.out.println(inputLine);
            }
            in.close();

        }
        catch (MalformedURLException e)
        {
            logger().warn("Bad URL supplied.", e );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "test";
    }

    private Logger logger()
    {
        return Logger.getLogger(this.getClass());
    }

}
