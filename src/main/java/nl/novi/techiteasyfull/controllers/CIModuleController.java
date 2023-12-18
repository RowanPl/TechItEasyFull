package nl.novi.techiteasyfull.controllers;


import jakarta.validation.Valid;
import nl.novi.techiteasyfull.dtos.input.CIModuleInputDto;
import nl.novi.techiteasyfull.dtos.output.CIModuleDto;
import nl.novi.techiteasyfull.exceptions.InvalidInputException;
import nl.novi.techiteasyfull.helper.BindingResultHelper;
import nl.novi.techiteasyfull.repository.SearchCriteria.CiModuleSearchCriteria;
import nl.novi.techiteasyfull.service.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cimodules")
public class CIModuleController {

    private final CIModuleService cimoduleService;

    public CIModuleController(CIModuleService cimoduleService) {
        this.cimoduleService = cimoduleService;
    }

    @PostMapping(value = "")
    public ResponseEntity<CIModuleDto> addCIModule(@Valid @RequestBody CIModuleInputDto cimoduleInputDto, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidInputException("Something went wrong, Please check the following fields. " + BindingResultHelper.getErrorMessage(bindingResult));
        }

        CIModuleDto cimodule = cimoduleService.addCIModule(cimoduleInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + cimodule.getId()).toUriString());

        return ResponseEntity.created(uri).body(cimodule);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CIModuleDto> getCIModuleById(@PathVariable("id") long id) {
        CIModuleDto cimodule = cimoduleService.getCIModuleById(id);
        return ResponseEntity.ok().body(cimodule);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        List<CIModuleDto> cimodules = cimoduleService.getAllCIModules();
        return ResponseEntity.ok().body(cimodules);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable("id") long id) {
        cimoduleService.deleteCIModule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<CIModuleDto>> searchCIModule(@RequestBody CiModuleSearchCriteria criteria) {
        List<CIModuleDto> cimodule = cimoduleService.searchCIModule(criteria);
        return ResponseEntity.ok().body(cimodule);
    }

}
