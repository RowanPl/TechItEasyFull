package nl.novi.techiteasyfull.service;

import nl.novi.techiteasyfull.dtos.input.TelevisionInputDto;
import nl.novi.techiteasyfull.dtos.output.CIModuleDto;
import nl.novi.techiteasyfull.dtos.output.TelevisionDto;
import nl.novi.techiteasyfull.models.Television;
import nl.novi.techiteasyfull.repository.Specifications.TelevisionSearchCriteria;
import nl.novi.techiteasyfull.repository.Specifications.TelevisionSpecifications;
import nl.novi.techiteasyfull.repository.TelevisionRepository;
import nl.novi.techiteasyfull.utils.PropertyMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static nl.novi.techiteasyfull.utils.PropertyMapper.copyProperties;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }


    public TelevisionDto addTelevision(TelevisionInputDto televisionInputDto) {

        Television television = dtoToTelevision(televisionInputDto);
        Television savedTelevision = televisionRepository.save(television);
        return televisionToDto(savedTelevision);

    }

    public Television dtoToTelevision(TelevisionInputDto inputDto){
        Television television = new Television();
        copyProperties(inputDto, television);
        return television;
    }
    public TelevisionDto televisionToDto(Television television) {

        TelevisionDto televisionDto = new TelevisionDto();

        copyProperties(television, televisionDto);

        return televisionDto;

    }

    public List<TelevisionDto> searchTelevision(TelevisionSearchCriteria criteria) {
        Specification<Television> specification = TelevisionSpecifications.toPredicate(criteria);

        List<Television> tvlist = televisionRepository.findAll(specification);
        List<TelevisionDto> tvDtoList = new ArrayList<>();
        tvlist.forEach(tv -> tvDtoList.add(televisionToDto(tv)));
        return tvDtoList;
    }

}
