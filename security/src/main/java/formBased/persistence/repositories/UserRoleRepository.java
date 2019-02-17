package formBased.persistence.repositories;

import formBased.persistence.model.Role;
import formBased.persistence.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRole.Id> {

    Optional<UserRole> findByRole(Role role);
}
