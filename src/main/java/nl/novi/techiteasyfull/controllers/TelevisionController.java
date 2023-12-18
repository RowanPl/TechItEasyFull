package nl.novi.techiteasyfull.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasyfull.dtos.input.TelevisionInputDto;
import nl.novi.techiteasyfull.dtos.output.TelevisionDto;
import nl.novi.techiteasyfull.exceptions.InvalidInputException;
import nl.novi.techiteasyfull.helper.BindingResultHelper;
import nl.novi.techiteasyfull.repository.SearchCriteria.TelevisionSearchCriteria;
import nl.novi.techiteasyfull.service.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<TelevisionDto> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult bindingResult) {

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

    @GetMapping(value = "")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        List<TelevisionDto> televisions = televisionService.getAllTelevisions();
        return ResponseEntity.ok().body(televisions);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TelevisionDto> getTelevisionById(@PathVariable("id") long id) {
        TelevisionDto television = televisionService.getTelevisionById(id);
        return ResponseEntity.ok().body(television);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteTelevisionById(@PathVariable("id") long id) {
        televisionService.deleteTelevisionById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TelevisionDto> updateTelevisionById(@PathVariable("id") long id, @Valid @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto televisionDto = televisionService.updateTelevisionById(id, televisionInputDto);
        return ResponseEntity.ok().body(televisionDto);
    }

    @PutMapping(value = "/{id}/remotecontrollers/{remoteControllerId}")
    public ResponseEntity<TelevisionDto> addRemoteControllerToTelevision(@PathVariable("id") long id, @PathVariable("remoteControllerId") long remoteControllerId) {
        TelevisionDto televisionDto = televisionService.addRemoteControllerToTelevision(id, remoteControllerId);
        return ResponseEntity.ok().body(televisionDto);
    }

    @PutMapping(value = "/{id}/cimodules/{ciModuleId}")
    public ResponseEntity<TelevisionDto> addCIModuleToTelevision(@PathVariable("id") long id, @PathVariable("ciModuleId") long ciModuleId) {
        TelevisionDto televisionDto = televisionService.addCIModuleToTelevision(id, ciModuleId);
        return ResponseEntity.ok().body(televisionDto);
    }

    @PutMapping(value = "/{id}/remove/cimodules/{ciModuleId}")
    public ResponseEntity<TelevisionDto> removeCIModuleFromTelevision(@PathVariable("id") long id, @PathVariable("ciModuleId") long ciModuleId) {
        TelevisionDto televisionDto = televisionService.removeCIModuleFromTelevision(id, ciModuleId);
        return ResponseEntity.ok().body(televisionDto);
    }


}
