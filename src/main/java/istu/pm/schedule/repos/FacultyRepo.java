package istu.pm.schedule.repos;

import istu.pm.schedule.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepo extends JpaRepository<Faculty, Integer> {
    List<Faculty> getAllByNameContaining(String name);
}
