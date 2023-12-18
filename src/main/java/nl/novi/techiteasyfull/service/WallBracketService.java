package nl.novi.techiteasyfull.service;

import nl.novi.techiteasyfull.repository.WallBracketRepository;
import org.springframework.stereotype.Service;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }


}
