package com.arthurbarbosa.beerstore.resource;

import com.arthurbarbosa.beerstore.model.Beer;
import com.arthurbarbosa.beerstore.repository.Beers;
import com.arthurbarbosa.beerstore.service.BeerService;
import com.arthurbarbosa.beerstore.service.exception.BeerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private Beers beers;

    @Autowired
    private BeerService beerService;

    @GetMapping
    public List<Beer> all() {
        return beers.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beer> findById(@PathVariable Long id) {
        Optional<Beer> obj = beers.findById(id);
        return ResponseEntity.ok().body(obj.orElseThrow(() -> new BeerNotFoundException()));
    }

    @PostMapping
    public ResponseEntity<Beer> create(@Valid @RequestBody Beer beer) {
        Beer obj = beerService.save(beer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Beer> update(@Valid @PathVariable Long id, @RequestBody Beer beer) {
        beer.setId(id);
        return ResponseEntity.ok().body(beerService.save(beer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        beerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
