package nl.novi.techiteasyfull.service;

import nl.novi.techiteasyfull.repository.RemoteControllerRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }


}

