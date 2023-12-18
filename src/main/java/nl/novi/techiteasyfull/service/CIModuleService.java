package nl.novi.techiteasyfull.service;

import nl.novi.techiteasyfull.dtos.input.CIModuleInputDto;
import nl.novi.techiteasyfull.dtos.output.CIModuleDto;
import nl.novi.techiteasyfull.exceptions.RecordNotFoundException;
import nl.novi.techiteasyfull.models.CIModule;
import nl.novi.techiteasyfull.repository.CIModuleRepository;
import nl.novi.techiteasyfull.repository.SearchCriteria.CiModuleSearchCriteria;
import nl.novi.techiteasyfull.repository.Specifications.GenericSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static nl.novi.techiteasyfull.utils.PropertyMapper.copyProperties;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }


    public CIModuleDto CIModuleToDto(CIModule ciModule) {

        CIModuleDto ciModuleDto = new CIModuleDto();
        copyProperties(ciModule, ciModuleDto);


        return ciModuleDto;
    }

    public CIModule dtoToCIModule(CIModuleInputDto ciModuleDto) {

        CIModule ciModule = new CIModule();
        copyProperties(ciModuleDto, ciModule);

        return ciModule;
    }

    public CIModuleDto addCIModule(CIModuleInputDto cimoduleInputDto) {

            CIModule cimodule = dtoToCIModule(cimoduleInputDto);
            CIModule savedCIModule = ciModuleRepository.save(cimodule);

            return CIModuleToDto(savedCIModule);
    }


    public void deleteCIModule(long id) {
        ciModuleRepository.deleteById(id);
    }

    public List<CIModuleDto> getAllCIModules() {
    List<CIModule> ciModuleList = ciModuleRepository.findAll();
    List<CIModuleDto> ciModuleDtoList = new ArrayList<>();
    ciModuleList.forEach(ciModule -> ciModuleDtoList.add(CIModuleToDto(ciModule)));
    return ciModuleDtoList;
    }

    public CIModuleDto getCIModuleById(long id) {
        CIModule ciModule = ciModuleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Invalid ciModule id : " + id));
        return CIModuleToDto(ciModule);
    }

    public List<CIModuleDto> searchCIModule(CiModuleSearchCriteria criteria) {
        Specification<CIModule> specification = GenericSpecifications.toPredicate(criteria);

        List<CIModule> ciModuleList = ciModuleRepository.findAll(specification);
        ciModuleList.sort(Comparator.comparing(CIModule::getId));
        List<CIModuleDto> ciModuleDtoList = new ArrayList<>();
        ciModuleList.forEach(ciModule -> ciModuleDtoList.add(CIModuleToDto(ciModule)));

        return ciModuleDtoList;
    }
}
