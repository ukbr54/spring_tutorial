package restapi.exceptionhandling.oneToMany;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ujjwal Gupta on Sep,2019
 */
public interface MobileRepository extends JpaRepository<Mobile,Long> {
}
