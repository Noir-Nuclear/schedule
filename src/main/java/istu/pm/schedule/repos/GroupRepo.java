package istu.pm.schedule.repos;

import istu.pm.schedule.entities.Group;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.*;

public interface GroupRepo extends JpaRepository<Group, Integer> {
    List<Group> getAllByNameContaining(String name);
}
