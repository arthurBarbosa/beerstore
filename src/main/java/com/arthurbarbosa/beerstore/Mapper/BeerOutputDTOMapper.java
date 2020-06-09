package com.arthurbarbosa.beerstore.Mapper;

import com.arthurbarbosa.beerstore.OutputDTO.BeerOutputDTO;
import com.arthurbarbosa.beerstore.model.Beer;
import com.arthurbarbosa.beerstore.service.exception.BeerNotFoundException;
import org.hibernate.ObjectNotFoundException;

import java.util.Optional;

public class BeerOutputDTOMapper {

    public Beer convertToEntity(BeerOutputDTO objDto) {
        Beer beer = new Beer();
        beer.setId(objDto.getId());
        beer.setName(objDto.getName());
        beer.setType(beer.getType());
        beer.setVolume(beer.getVolume());
        return beer;
    }


}
