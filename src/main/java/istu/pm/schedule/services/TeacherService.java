package istu.pm.schedule.services;

import istu.pm.schedule.entities.Teacher;

public interface TeacherService {

    Teacher create(Teacher teacher);
    Teacher edit(Teacher changedTeacher, Teacher newTeacher);
    void delete(Teacher teacher);


}
