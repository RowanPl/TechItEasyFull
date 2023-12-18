package nl.novi.techiteasyfull.repository;

import nl.novi.techiteasyfull.models.RemoteController;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RemoteControllerRepository extends JpaSpecificationExecutor<RemoteController>,JpaRepository<RemoteController, Long> {
    List<RemoteController> findAllByOrderByIdAsc();

}
