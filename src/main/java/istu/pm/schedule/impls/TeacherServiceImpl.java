package istu.pm.schedule.impls;

import istu.pm.schedule.entities.Teacher;
import istu.pm.schedule.repos.TeacherRepo;
import istu.pm.schedule.services.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepo teacherRepo;

    public TeacherServiceImpl(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public Teacher create(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public Teacher edit(Teacher changedTeacher, Teacher newTeacher) {
        BeanUtils.copyProperties(newTeacher, changedTeacher, "id");
        return teacherRepo.save(changedTeacher);
    }

    public void delete(Teacher teacher) {
        teacherRepo.delete(teacher);
    }

    public Integer getTeacherIdByName(String name) {
        return teacherRepo.getByNameContaining(name).getId();
    }
}
