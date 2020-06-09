package com.arthurbarbosa.beerstore.Mapper;

import com.arthurbarbosa.beerstore.InputDTO.BeerInputDTO;
import com.arthurbarbosa.beerstore.model.Beer;
import com.arthurbarbosa.beerstore.model.BeerType;

public class BeerInputDTOMapper {

    public Beer convertToEntity(BeerInputDTO inputDTO) {
        Beer beer = new Beer();
        beer.setId(inputDTO.getId());
        beer.setName(inputDTO.getName());
        beer.setType(BeerType.valueOf(inputDTO.getType()));
        beer.setVolume(inputDTO.getVolume());
        return beer;
    }
}
