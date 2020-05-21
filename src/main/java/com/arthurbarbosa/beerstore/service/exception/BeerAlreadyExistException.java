package com.arthurbarbosa.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerAlreadyExistException extends BusinessException {

    public BeerAlreadyExistException() {
        super("beers-5", "Beer", HttpStatus.BAD_REQUEST);
    }
}
