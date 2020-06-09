package com.arthurbarbosa.beerstore.OutputDTO;

import com.arthurbarbosa.beerstore.model.Beer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerOutputDTO {

    private Long id;
    private String name;
    private String type;
    private BigDecimal volume;

    public BeerOutputDTO(Beer beer) {
        this.id = beer.getId();
        this.name = beer.getName();
        this.volume = beer.getVolume();
        this.type = beer.getType().name();
    }

}
