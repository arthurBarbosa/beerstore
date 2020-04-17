package com.arthurbarbosa.beerstore.service;

import com.arthurbarbosa.beerstore.model.Beer;
import com.arthurbarbosa.beerstore.service.exception.BeerAlreadyExistException;

public class BeerService {

    public void save(Beer beer){
        throw new BeerAlreadyExistException();

    }

}



