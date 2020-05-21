package com.arthurbarbosa.beerstore.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

import com.arthurbarbosa.beerstore.model.Beer;
import com.arthurbarbosa.beerstore.model.BeerType;
import com.arthurbarbosa.beerstore.repository.Beers;
import com.arthurbarbosa.beerstore.service.BeerService;
import com.arthurbarbosa.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private Beers beersMocked;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        beerService = new BeerService(beersMocked);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists() {
        Beer beerInDatabase = new Beer();
        beerInDatabase.setId(10L);
        beerInDatabase.setName("Heinekein");
        beerInDatabase.setVolume(new BigDecimal("355"));
        beerInDatabase.setType(BeerType.LAGER);

        when(beersMocked.findByNameAndType("Heinekein", BeerType.LAGER)).thenReturn(Optional.of(beerInDatabase));

        Beer newBeer = new Beer();
        newBeer.setName("Heinekein");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        beerService.save(newBeer);
    }

    @Test
    public void should_create_new_beer() {
        Beer beer = new Beer();
        beer.setName("Heinekein");
        beer.setType(BeerType.LAGER);
        beer.setVolume(new BigDecimal("355"));

        Beer beerInDatabase = new Beer();
        beerInDatabase.setId(10L);
        beerInDatabase.setName("Heinekein");
        beerInDatabase.setType(BeerType.LAGER);
        beerInDatabase.setVolume(new BigDecimal("355"));
        when(beersMocked.save(beer)).thenReturn(beerInDatabase);

        Beer beerSaved = beerService.save(beer);
        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heinekein"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));

    }
}
