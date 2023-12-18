package nl.novi.techiteasyfull.repository;

import nl.novi.techiteasyfull.models.Television;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TelevisionRepository extends JpaSpecificationExecutor<Television>, JpaRepository<Television, Long> {

    List<Television> findAllByNameContaining(String name);

    List<Television> findAllByOrderByIdAsc();
}
