package restapi.exceptionhandling.bird;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdRepository extends JpaRepository<Bird,Long> {
}
