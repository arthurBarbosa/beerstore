package com.arthurbarbosa.beerstore;

import com.arthurbarbosa.beerstore.model.Beer;
import com.arthurbarbosa.beerstore.model.BeerType;
import com.arthurbarbosa.beerstore.service.BeerService;
import com.arthurbarbosa.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Test;

import java.math.BigDecimal;

public class BerrServiceTest {


    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists(){
        BeerService beerService = new BeerService();

        Beer newBeer = new Beer();
        newBeer.setName("Heinekein");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        beerService.save(newBeer);
    }
}
