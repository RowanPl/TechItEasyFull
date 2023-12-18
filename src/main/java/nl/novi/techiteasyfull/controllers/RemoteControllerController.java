package nl.novi.techiteasyfull.controllers;


import nl.novi.techiteasyfull.dtos.input.RemoteControllerInputDto;
import nl.novi.techiteasyfull.dtos.output.RemoteControllerDto;
import nl.novi.techiteasyfull.exceptions.InvalidInputException;
import nl.novi.techiteasyfull.helper.BindingResultHelper;
import nl.novi.techiteasyfull.repository.SearchCriteria.RemoteControllerSearchCriteria;
import nl.novi.techiteasyfull.service.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/remotecontrollers")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;


    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers() {
        List<RemoteControllerDto> remoteControllers = remoteControllerService.getAllRemoteControllers();
        return ResponseEntity.ok().body(remoteControllers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteControllerById(@PathVariable("id") long id) {
        RemoteControllerDto remoteController = remoteControllerService.getRemoteControllerById(id);
        return ResponseEntity.ok().body(remoteController);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<RemoteControllerDto>> searchRemoteController(@RequestBody RemoteControllerSearchCriteria criteria) {
        List<RemoteControllerDto> remoteController = remoteControllerService.searchRemoteController(criteria);
        return ResponseEntity.ok().body(remoteController);
    }

    @PostMapping(value = "")
    public ResponseEntity<RemoteControllerDto> addRemoteController(@RequestBody RemoteControllerInputDto remoteControllerDto, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidInputException("Something went wrong, Please check the following fields. " + BindingResultHelper.getErrorMessage(bindingResult));
        }

        RemoteControllerDto remoteController = remoteControllerService.addRemoteController(remoteControllerDto);

        URI location = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + remoteController.getId()).toUriString());


        return ResponseEntity.created(location).body(remoteController);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable("id") long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteControllerById(@PathVariable("id") long id, @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto remoteController = remoteControllerService.updateRemoteControllerById(id, remoteControllerInputDto);
        return ResponseEntity.ok().body(remoteController);
    }


}
