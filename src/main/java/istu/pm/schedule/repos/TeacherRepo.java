package istu.pm.schedule.repos;

import istu.pm.schedule.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    List<Teacher> getAllByName(String name);
}
