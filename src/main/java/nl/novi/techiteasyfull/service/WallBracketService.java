package nl.novi.techiteasyfull.service;

import nl.novi.techiteasyfull.dtos.input.WallBracketInputDto;
import nl.novi.techiteasyfull.dtos.output.WallBracketDto;
import nl.novi.techiteasyfull.exceptions.RecordNotFoundException;
import nl.novi.techiteasyfull.models.Television;
import nl.novi.techiteasyfull.models.WallBracket;
import nl.novi.techiteasyfull.repository.SearchCriteria.WallBracketSearchCriteria;
import nl.novi.techiteasyfull.repository.Specifications.GenericSpecifications;
import nl.novi.techiteasyfull.repository.TelevisionRepository;
import nl.novi.techiteasyfull.repository.WallBracketRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static nl.novi.techiteasyfull.utils.PropertyMapper.copyProperties;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;
    private final TelevisionRepository televisionRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository, TelevisionRepository televisionRepository) {
        this.wallBracketRepository = wallBracketRepository;
        this.televisionRepository = televisionRepository;
    }

    public WallBracketDto wallbracketToDto(WallBracket wallBracket){
        WallBracketDto wallBracketDto = new WallBracketDto();
        copyProperties(wallBracket, wallBracketDto);
        return wallBracketDto;
    }

    public WallBracket dtoToWallBracket(WallBracketInputDto wallBracketDto){
        WallBracket wallBracket = new WallBracket();
        copyProperties(wallBracketDto, wallBracket);
        return wallBracket;
    }


    public WallBracketDto addWallBracket(WallBracketInputDto wallbracketDto) {
        WallBracket wallbracket = dtoToWallBracket(wallbracketDto);
        wallbracket = wallBracketRepository.save(wallbracket);
        return wallbracketToDto(wallbracket);
    }

    public WallBracketDto getWallBracketById(long id) {
        Optional<WallBracket> optionalWallBracket = wallBracketRepository.findById(id);
        if (optionalWallBracket.isPresent()) {
            return wallbracketToDto(optionalWallBracket.get());
        }
        throw new RecordNotFoundException("WallBracket not found with id " + id);
    }

    public List<WallBracketDto> getAllWallBrackets() {
        List<WallBracket> wallbrackets = wallBracketRepository.findAll();
        List<WallBracketDto> wallbracketDtos = new ArrayList<>();
        for (WallBracket wallbracket : wallbrackets) {
            wallbracketDtos.add(wallbracketToDto(wallbracket));
        }
        return wallbracketDtos;
    }

    public List<WallBracketDto> searchWallBracket(WallBracketSearchCriteria criteria) {
        Specification<WallBracket> specification = GenericSpecifications.toPredicate(criteria);

        List<WallBracket> wallbrackets = wallBracketRepository.findAll(specification);
        List<WallBracketDto> wallbracketDtos = new ArrayList<>();
        wallbrackets.forEach(wallbracket -> wallbracketDtos.add(wallbracketToDto(wallbracket)));

        return wallbracketDtos;
    }

    public void updateWallBracket(long id, WallBracketInputDto wallbracketDto) {
        Optional<WallBracket> optionalWallBracket = wallBracketRepository.findById(id);
        if (optionalWallBracket.isPresent()) {
            WallBracket wallbracket = optionalWallBracket.get();

            if (wallbracketDto.getAdjustable() != null) {
                wallbracket.setAdjustable(wallbracketDto.getAdjustable());
            }
            if (wallbracketDto.getName() != null) {
                wallbracket.setName(wallbracketDto.getName());
            }
            if (wallbracketDto.getPrice() != null) {
                wallbracket.setPrice(wallbracketDto.getPrice());
            }
            if (wallbracketDto.getSize() != null) {
                wallbracket.setSize(wallbracketDto.getSize());
            }
            wallBracketRepository.save(wallbracket);
        } else {
            throw new RecordNotFoundException("WallBracket not found with id " + id);
        }
    }

    public void deleteWallBracket(long id) {
        Optional<WallBracket> optionalWallBracket = wallBracketRepository.findById(id);

        if (optionalWallBracket.isPresent() ) {
            WallBracket wallbracket = optionalWallBracket.get();

            televisionRepository.findAll().forEach(television -> {
                if (television.getWallBrackets().contains(wallbracket)) {
                    television.getWallBrackets().remove(wallbracket);
                    televisionRepository.save(television);
                }
            });

            if (wallbracket.getTelevisions().removeAll(wallbracket.getTelevisions())) {
                wallBracketRepository.save(wallbracket);
            }
            wallBracketRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("WallBracket not found with id " + id);
        }
    }
}
