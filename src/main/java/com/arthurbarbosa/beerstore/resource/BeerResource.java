package com.arthurbarbosa.beerstore.resource;

import com.arthurbarbosa.beerstore.model.Beer;
import com.arthurbarbosa.beerstore.repository.Beers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private Beers beers;

    @GetMapping
    public List<Beer> all() {
        return beers.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer) {
        return beers.save(beer);
    }
}
