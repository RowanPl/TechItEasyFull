package nl.novi.techiteasyfull.repository;

import nl.novi.techiteasyfull.models.CIModule;
import nl.novi.techiteasyfull.repository.SearchCriteria.CiModuleSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CIModuleRepository extends JpaSpecificationExecutor<CIModule>,JpaRepository<CIModule, Long> {
}
