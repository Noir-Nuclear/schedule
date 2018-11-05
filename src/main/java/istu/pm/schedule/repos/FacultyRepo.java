package istu.pm.schedule.repos;

import istu.pm.schedule.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepo extends JpaRepository<Faculty, Integer> {
}
