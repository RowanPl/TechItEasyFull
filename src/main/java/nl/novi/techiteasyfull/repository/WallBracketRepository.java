package nl.novi.techiteasyfull.repository;


import nl.novi.techiteasyfull.models.WallBracket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WallBracketRepository extends JpaSpecificationExecutor<WallBracket>,JpaRepository<WallBracket, Long> {
}
