package com.arthurbarbosa.beerstore.resource;

import com.arthurbarbosa.beerstore.InputDTO.BeerInputDTO;
import com.arthurbarbosa.beerstore.Mapper.BeerInputDTOMapper;
import com.arthurbarbosa.beerstore.Mapper.BeerOutputDTOMapper;
import com.arthurbarbosa.beerstore.OutputDTO.BeerOutputDTO;
import com.arthurbarbosa.beerstore.model.Beer;
import com.arthurbarbosa.beerstore.repository.Beers;
import com.arthurbarbosa.beerstore.service.BeerService;
import com.arthurbarbosa.beerstore.service.exception.BeerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private Beers beers;

    @Autowired
    private BeerService beerService;

    @GetMapping
    public List<BeerOutputDTO> all() {
        List<Beer> list = beers.findAll();
        List<BeerOutputDTO> listDto = list.stream().map(obj -> new BeerOutputDTO(obj)).collect(Collectors.toList());
        return listDto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerOutputDTO> findById(@PathVariable Long id) {
        Optional<Beer> obj = beers.findById(id);
        return ResponseEntity.ok().body(new BeerOutputDTO(obj.get()));
    }

    @PostMapping
    public ResponseEntity<Beer> create(@Valid @RequestBody BeerInputDTO beerInputDTO) {
        Beer obj = new BeerInputDTOMapper().convertToEntity(beerInputDTO);
        beerService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Beer> update(@Valid @PathVariable Long id, @RequestBody BeerInputDTO beerInputDTO) {
        Beer beer = new BeerInputDTOMapper().convertToEntity(beerInputDTO);
        beer.setId(id);
        return ResponseEntity.ok().body(beerService.save(beer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        beerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
