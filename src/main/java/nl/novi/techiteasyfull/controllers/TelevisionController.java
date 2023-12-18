package nl.novi.techiteasyfull.controllers;

import nl.novi.techiteasyfull.dtos.input.TelevisionInputDto;
import nl.novi.techiteasyfull.dtos.output.TelevisionDto;
import nl.novi.techiteasyfull.exceptions.InvalidInputException;
import nl.novi.techiteasyfull.helper.BindingResultHelper;
import nl.novi.techiteasyfull.models.Television;
import nl.novi.techiteasyfull.repository.Specifications.TelevisionSearchCriteria;
import nl.novi.techiteasyfull.service.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }


    @PostMapping(value = "")
    public ResponseEntity<TelevisionDto> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult bindingResult)  {

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidInputException("Something went wrong, Please check the following fields. " + BindingResultHelper.getErrorMessage(bindingResult));
        }

        TelevisionDto television = televisionService.addTelevision(televisionInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + television.getId()).toUriString());

        return ResponseEntity.created(uri).body(television);

    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<TelevisionDto>> searchTelevision(@RequestBody TelevisionSearchCriteria criteria) {
        List<TelevisionDto> television = televisionService.searchTelevision(criteria);
        return ResponseEntity.ok().body(television);
    }

}
