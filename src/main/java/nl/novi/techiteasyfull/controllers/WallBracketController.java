package nl.novi.techiteasyfull.controllers;

import nl.novi.techiteasyfull.dtos.input.WallBracketInputDto;
import nl.novi.techiteasyfull.dtos.output.WallBracketDto;
import nl.novi.techiteasyfull.repository.SearchCriteria.WallBracketSearchCriteria;
import nl.novi.techiteasyfull.service.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/wallbrackets")
public class WallBracketController {

    private final WallBracketService wallbracketService;

    public WallBracketController(WallBracketService wallbracketService) {
        this.wallbracketService = wallbracketService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {
        List<WallBracketDto> wallbrackets = wallbracketService.getAllWallBrackets();
        return ResponseEntity.ok().body(wallbrackets);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WallBracketDto> getWallBracketById(@PathVariable("id") long id) {
        WallBracketDto wallbracket = wallbracketService.getWallBracketById(id);
        return ResponseEntity.ok().body(wallbracket);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<WallBracketDto>> searchWallBracket(@RequestBody WallBracketSearchCriteria criteria) {
        List<WallBracketDto> wallbracket = wallbracketService.searchWallBracket(criteria);
        return ResponseEntity.ok().body(wallbracket);
    }

    @PostMapping(value = "")
    public ResponseEntity<WallBracketDto> addWallBracket(@RequestBody WallBracketInputDto wallbracketDto) {
        WallBracketDto wallbracket = wallbracketService.addWallBracket(wallbracketDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/wallbrackets/{id}")
                .toUriString());
        return ResponseEntity.created(uri).body(wallbracket);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateWallBracket(@PathVariable("id") long id, @RequestBody WallBracketInputDto wallbracketDto) {
        wallbracketService.updateWallBracket(id, wallbracketDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable("id") long id) {
        wallbracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }
}
