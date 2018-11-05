package istu.pm.schedule.services;

import istu.pm.schedule.entities.Faculty;

public interface FacultyService {

    Faculty create(Faculty newFaculty);
    Faculty edit(Faculty changedFaculty, Faculty newFaculty);
    void delete(Faculty deletedFaculty);
    
}
