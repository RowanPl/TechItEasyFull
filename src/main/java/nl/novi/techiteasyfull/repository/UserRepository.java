package nl.novi.techiteasyfull.repository;


import nl.novi.techiteasyfull.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
