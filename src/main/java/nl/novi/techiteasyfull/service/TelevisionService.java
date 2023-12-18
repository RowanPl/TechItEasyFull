package nl.novi.techiteasyfull.service;

import nl.novi.techiteasyfull.dtos.input.TelevisionInputDto;
import nl.novi.techiteasyfull.dtos.output.CIModuleDto;
import nl.novi.techiteasyfull.dtos.output.RemoteControllerDto;
import nl.novi.techiteasyfull.dtos.output.TelevisionDto;
import nl.novi.techiteasyfull.dtos.output.WallBracketDto;
import nl.novi.techiteasyfull.exceptions.RecordNotFoundException;
import nl.novi.techiteasyfull.models.CIModule;
import nl.novi.techiteasyfull.models.RemoteController;
import nl.novi.techiteasyfull.models.Television;
import nl.novi.techiteasyfull.models.WallBracket;
import nl.novi.techiteasyfull.repository.CIModuleRepository;
import nl.novi.techiteasyfull.repository.RemoteControllerRepository;
import nl.novi.techiteasyfull.repository.SearchCriteria.TelevisionSearchCriteria;
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
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final CIModuleRepository ciModuleRepository;

    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, CIModuleRepository ciModuleRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
    }


    public TelevisionDto addTelevision(TelevisionInputDto televisionInputDto) {

        Television television = dtoToTelevision(televisionInputDto);
        Television savedTelevision = televisionRepository.save(television);
        return televisionToDto(savedTelevision);

    }

    public Television dtoToTelevision(TelevisionInputDto inputDto) {
        Television television = new Television();
        copyProperties(inputDto, television);
        return television;
    }

    public TelevisionDto televisionToDto(Television television) {

        TelevisionDto televisionDto = new TelevisionDto();

        copyProperties(television, televisionDto);

        if (television.getRemoteController() != null) {
            RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
            copyProperties(television.getRemoteController(), remoteControllerDto);
            televisionDto.setRemoteControllerDto(remoteControllerDto);
        }
        if (television.getCiModule() != null) {
            CIModuleDto ciModuleDto = new CIModuleDto();
            copyProperties(television.getCiModule(), ciModuleDto);
            televisionDto.setCiModuleDto(ciModuleDto);
        }
        if (television.getWallBrackets() != null) {
            List<WallBracket> wallBrackets = television.getWallBrackets();
            List<WallBracketDto> wallBracketIds = new ArrayList<>();
            for (WallBracket wallBracket : wallBrackets) {
                WallBracketDto wallBracketDto = new WallBracketDto();
                copyProperties(wallBracket, wallBracketDto);
                wallBracketIds.add(wallBracketDto);
            }
            televisionDto.setWallBracketDto(wallBracketIds);

        }
        return televisionDto;

    }

    public List<TelevisionDto> searchTelevision(TelevisionSearchCriteria criteria) {
        Specification<Television> specification = GenericSpecifications.toPredicate(criteria);

        List<Television> tvlist = televisionRepository.findAll(specification);
        tvlist.sort(Comparator.comparing(Television::getId));
        List<TelevisionDto> tvDtoList = new ArrayList<>();
        tvlist.forEach(tv -> tvDtoList.add(televisionToDto(tv)));

        return tvDtoList;
    }


    public List<TelevisionDto> getAllTelevisions() {
        List<Television> televisions = televisionRepository.findAllByOrderByIdAsc();
        List<TelevisionDto> televisionDtoList = new ArrayList<>();
        televisions.forEach(television -> televisionDtoList.add(televisionToDto(television)));
        return televisionDtoList;
    }


    public TelevisionDto getTelevisionById(long id) {
        Television television = televisionRepository.findById(id).orElseThrow();
        return televisionToDto(television);
    }

    public void deleteTelevisionById(long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Television television = optionalTelevision.orElseThrow();
        if (television.getRemoteController() != null) {
            RemoteController remoteController = television.getRemoteController();
            remoteController.setTelevision(null);
            remoteControllerRepository.save(remoteController);
        }
        if (television.getCiModule() != null) {
            CIModule ciModule = television.getCiModule();
            ciModule.getTelevisions().remove(television);
            ciModuleRepository.save(ciModule);
        }
        if (television.getWallBrackets() != null) {
            List<WallBracket> wallBrackets = television.getWallBrackets();
            for (WallBracket wallBracket : wallBrackets) {
                wallBracket.getTelevisions().remove(television);
            }
        }
        televisionRepository.deleteById(id);
    }

    public TelevisionDto updateTelevisionById(long id, TelevisionInputDto televisionInputDto) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
            Television tv = television.get();

            if (televisionInputDto.getBrand() != null) {
                tv.setBrand(televisionInputDto.getBrand());
            }
            if (televisionInputDto.getName() != null) {
                tv.setName(televisionInputDto.getName());
            }
            if (televisionInputDto.getPrice() != null) {
                tv.setPrice(televisionInputDto.getPrice());
            }
            if (televisionInputDto.getAvailableSize() != null) {
                tv.setAvailableSize(televisionInputDto.getAvailableSize());
            }
            if (televisionInputDto.getRefreshRate() != null) {
                tv.setRefreshRate(televisionInputDto.getRefreshRate());
            }
            if (televisionInputDto.getScreenType() != null) {
                tv.setScreenType(televisionInputDto.getScreenType());
            }
            if (televisionInputDto.getScreenQuality() != null) {
                tv.setScreenQuality(televisionInputDto.getScreenQuality());
            }
            if (televisionInputDto.getSmartTv() != null) {
                tv.setSmartTv(televisionInputDto.getSmartTv());
            }
            if (televisionInputDto.getWifi() != null) {
                tv.setWifi(televisionInputDto.getWifi());
            }
            if (televisionInputDto.getVoiceControl() != null) {
                tv.setVoiceControl(televisionInputDto.getVoiceControl());
            }
            if (televisionInputDto.getHdr() != null) {
                tv.setHdr(televisionInputDto.getHdr());
            }
            if (televisionInputDto.getBluetooth() != null) {
                tv.setBluetooth(televisionInputDto.getBluetooth());
            }
            if (televisionInputDto.getAmbiLight() != null) {
                tv.setAmbiLight(televisionInputDto.getAmbiLight());
            }
            if (televisionInputDto.getOriginalStock() != null) {
                tv.setOriginalStock(televisionInputDto.getOriginalStock());
            }
            if (televisionInputDto.getSold() != null) {
                tv.setSold(televisionInputDto.getSold());
            }
//            if (televisionInputDto.getCiModuleDto() != null) {
//                tv.setCiModuleDto(televisionInputDto.getCiModuleDto());
//            }
//            if (televisionInputDto.getRemoteControllerDto() != null) {
//                tv.setRemoteControllerDto(televisionInputDto.getRemoteControllerDto());
//            }
            televisionRepository.save(tv);

            return televisionToDto(tv);
        }
        throw new RecordNotFoundException("Television not found for id: " + id);
    }

    public TelevisionDto addRemoteControllerToTelevision(long id, long remoteControllerId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);

        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {

            Television television = optionalTelevision.get();
            RemoteController remoteController = optionalRemoteController.get();
            if (television.getRemoteController() != null) {
                RemoteController oldRemoteController = television.getRemoteController();
                oldRemoteController.setTelevision(null);
                remoteControllerRepository.save(oldRemoteController);
            }
            television.setRemoteController(remoteController);
            remoteController.setTelevision(television);
            remoteControllerRepository.save(remoteController);
            televisionRepository.save(television);

            return televisionToDto(television);
        }
        throw new RecordNotFoundException("Television not found for id: " + id + " or RemoteController not found for id: " + remoteControllerId);
    }

    public TelevisionDto addCIModuleToTelevision(long id, long ciModuleId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(ciModuleId);

        if (optionalTelevision.isPresent() && optionalCIModule.isPresent()) {
            CIModule ciModule = optionalCIModule.get();
            Television television = optionalTelevision.get();

            if (television.getCiModule() != null) {
                CIModule oldCIModule = television.getCiModule();
                oldCIModule.getTelevisions().remove(television);
                ciModuleRepository.save(oldCIModule);
            }
            television.setCiModule(ciModule);
            ciModule.getTelevisions().add(television);
            ciModuleRepository.save(ciModule);
            televisionRepository.save(television);

            return televisionToDto(television);

        }
        throw new RecordNotFoundException("Television not found for id: " + id + " or CIModule not found for id: " + ciModuleId);
    }

    public TelevisionDto removeCIModuleFromTelevision(long id, long ciModuleId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(ciModuleId);

        if (optionalTelevision.isPresent() && optionalCIModule.isPresent()) {
            CIModule ciModule = optionalCIModule.get();
            Television television = optionalTelevision.get();

            television.setCiModule(null);
            ciModule.getTelevisions().remove(television);
            ciModuleRepository.save(ciModule);
            televisionRepository.save(television);

            return televisionToDto(television);

        }
        throw new RecordNotFoundException("Television not found for id: " + id + " or CIModule not found for id: " + ciModuleId);
    }
}

