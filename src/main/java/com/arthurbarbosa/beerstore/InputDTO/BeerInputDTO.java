package com.arthurbarbosa.beerstore.InputDTO;

import com.arthurbarbosa.beerstore.model.Beer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerInputDTO {

    private Long id;
    private String name;
    private String type;
    private BigDecimal volume;
    private Boolean favorite;

    public BeerInputDTO(Beer beer) {
        this.id = beer.getId();
        this.name = beer.getName();
        this.type = beer.getType().name();
        this.volume = beer.getVolume();
        this.favorite = beer.getFavorite();
    }
}
