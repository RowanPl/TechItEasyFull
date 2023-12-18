package nl.novi.techiteasyfull.service;

import nl.novi.techiteasyfull.dtos.input.RemoteControllerInputDto;
import nl.novi.techiteasyfull.dtos.output.RemoteControllerDto;
import nl.novi.techiteasyfull.exceptions.RecordNotFoundException;
import nl.novi.techiteasyfull.models.RemoteController;
import nl.novi.techiteasyfull.models.Television;
import nl.novi.techiteasyfull.repository.RemoteControllerRepository;
import nl.novi.techiteasyfull.repository.SearchCriteria.RemoteControllerSearchCriteria;
import nl.novi.techiteasyfull.repository.Specifications.GenericSpecifications;
import nl.novi.techiteasyfull.repository.TelevisionRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static nl.novi.techiteasyfull.utils.PropertyMapper.copyProperties;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;
    private final TelevisionRepository televisionRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository, TelevisionRepository televisionRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
        this.televisionRepository = televisionRepository;
    }


    public List<RemoteControllerDto> getAllRemoteControllers() {

        List<RemoteController> remoteControllerDtoList = remoteControllerRepository.findAllByOrderByIdAsc();
        List<RemoteControllerDto> remoteControllerDtoList1 = new ArrayList<>();
        remoteControllerDtoList.forEach(remoteController -> remoteControllerDtoList1.add(remoteControllerToDto(remoteController)));
        return remoteControllerDtoList1;
    }

    private RemoteControllerDto remoteControllerToDto(RemoteController remoteController) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        copyProperties(remoteController, remoteControllerDto);
        if (remoteController.getTelevision() != null) {
            remoteControllerDto.setTelevisionId(remoteController.getTelevision().getId());
        }
        return remoteControllerDto;
    }


    private RemoteController dtoToRemoteController(RemoteControllerInputDto remoteControllerDto) {
        RemoteController remoteController = new RemoteController();
        copyProperties(remoteControllerDto, remoteController);
        return remoteController;
    }

    public List<RemoteControllerDto> searchRemoteController(RemoteControllerSearchCriteria criteria) {
        Specification<RemoteController> specification = GenericSpecifications.toPredicate(criteria);

        List<RemoteController> remoteControllerList = remoteControllerRepository.findAll(specification);
           remoteControllerList.sort(Comparator.comparing(RemoteController::getId));

        List<RemoteControllerDto> remoteControllerDtoList = new ArrayList<>();
        remoteControllerList.forEach(remoteController -> remoteControllerDtoList.add(remoteControllerToDto(remoteController)));

        return remoteControllerDtoList;
    }

    public RemoteControllerDto addRemoteController(RemoteControllerInputDto remoteControllerDto) {

        RemoteController remoteController = dtoToRemoteController(remoteControllerDto);
        if (remoteControllerDto.getTelevisionId() != null) {
            Optional<Television> optionalTelevision = televisionRepository.findById(remoteControllerDto.getTelevisionId());
            if (optionalTelevision.isEmpty()) {
                throw new RecordNotFoundException("Television with id " + remoteControllerDto.getTelevisionId() + " not found");
            }
            Television television = optionalTelevision.get();
            remoteController.setTelevision(television);
            television.setRemoteController(remoteController);
            remoteControllerRepository.save(remoteController);
            televisionRepository.save(television);
            return remoteControllerToDto(remoteController);
        }
        RemoteController savedRemoteController = remoteControllerRepository.save(remoteController);
        return remoteControllerToDto(savedRemoteController);
    }

    public RemoteControllerDto getRemoteControllerById(long id) {
        RemoteController remoteController = remoteControllerRepository.findById(id).orElseThrow();
        return remoteControllerToDto(remoteController);
    }


    public void deleteRemoteController(long id) {
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(id);
        if (optionalRemoteController.isEmpty()){
            throw new RecordNotFoundException( "RemoteController with id " + id + " not found");
        }

        RemoteController remoteController = optionalRemoteController.get();
        if (remoteController.getTelevision() != null) {
            Television television = remoteController.getTelevision();
            television.setRemoteController(null);
            televisionRepository.save(television);
        }
        remoteControllerRepository.deleteById(id);

    }

    public RemoteControllerDto updateRemoteControllerById(long id, RemoteControllerInputDto remoteControllerInputDto) {

        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(id);

        if (optionalRemoteController.isEmpty()){
            throw new RecordNotFoundException( "RemoteController with id " + id + " not found");
        }
       RemoteController remoteController =  optionalRemoteController.get();
        if (remoteControllerInputDto.getCompatibleWith() != null) {
            remoteController.setCompatibleWith(remoteControllerInputDto.getCompatibleWith());
        }
        if (remoteControllerInputDto.getBatteryType() != null) {
            remoteController.setBatteryType(remoteControllerInputDto.getBatteryType());
        }
        if (remoteControllerInputDto.getName() != null) {
            remoteController.setName(remoteControllerInputDto.getName());
        }
        if (remoteControllerInputDto.getBrand() != null) {
            remoteController.setBrand(remoteControllerInputDto.getBrand());
        }
        if (remoteControllerInputDto.getPrice() != null) {
            remoteController.setPrice(remoteControllerInputDto.getPrice());
        }
        if (remoteControllerInputDto.getOriginalStock() != null) {
            remoteController.setOriginalStock(remoteControllerInputDto.getOriginalStock());
        }

        RemoteController savedRemoteController = remoteControllerRepository.save(remoteController);
        return remoteControllerToDto(savedRemoteController);
    }
}


