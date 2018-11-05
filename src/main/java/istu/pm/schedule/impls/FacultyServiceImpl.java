package istu.pm.schedule.impls;

import istu.pm.schedule.entities.Faculty;
import istu.pm.schedule.repos.FacultyRepo;
import istu.pm.schedule.services.FacultyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepo facultyRepo;

    public FacultyServiceImpl(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    public Faculty create(Faculty newFaculty) {
        return facultyRepo.save(newFaculty);
    }

    public Faculty edit(Faculty changedFaculty, Faculty newFaculty) {
        BeanUtils.copyProperties(newFaculty, changedFaculty, "id");
        return facultyRepo.save(changedFaculty);
    }

    public void delete(Faculty deletedFaculty) {
        facultyRepo.delete(deletedFaculty);
    }

}
