package istu.pm.schedule.repos;

import istu.pm.schedule.entities.LessonData;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface LessonDataRepo extends JpaRepository <LessonData, Integer> {
    List<LessonData> getAllByGroup(Integer groupId);

    List<LessonData> getAllByTeacher(Integer teacherId);
}
