package nl.novi.techiteasyfull.service;

import nl.novi.techiteasyfull.dtos.output.CIModuleDto;
import nl.novi.techiteasyfull.models.CIModule;
import nl.novi.techiteasyfull.repository.CIModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }


    public CIModuleDto CIModuleToDto(CIModule ciModule) {

        CIModuleDto ciModuleDto = new CIModuleDto();

        ciModuleDto.setId(ciModule.getId());
        ciModuleDto.setPrice(ciModule.getPrice());
        ciModuleDto.setName(ciModule.getName());
        ciModuleDto.setType(ciModule.getType());

        return ciModuleDto;
    }


    public CIModule dtoToCIModule(CIModuleDto ciModuleDto) {

        CIModule ciModule = new CIModule();

        ciModule.setId(ciModuleDto.getId());
        ciModule.setPrice(ciModuleDto.getPrice());
        ciModule.setName(ciModuleDto.getName());
        ciModule.setType(ciModuleDto.getType());

        return ciModule;
    }

}
