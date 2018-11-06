package istu.pm.schedule.impls;

import istu.pm.schedule.entities.LessonData;
import istu.pm.schedule.repos.LessonDataRepo;
import istu.pm.schedule.services.LessonDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonDataServiceImpl implements LessonDataService {
    private final LessonDataRepo lessonDataRepo;

    public LessonDataServiceImpl(LessonDataRepo lessonDataRepo) {
        this.lessonDataRepo = lessonDataRepo;
    }

    public List<LessonData> getLessonsByGroupId(Integer groupId) {
        lessonDataRepo.getAllByGroup(groupId);
    }

    public LessonData create(LessonData lessonData) {
        return lessonDataRepo.save(lessonData);
    }

    public LessonData edit(LessonData changedLesson, LessonData newLesson) {
        BeanUtils.copyProperties(newLesson, changedLesson, "id");
        return lessonDataRepo.save(changedLesson);
    }

    public void delete(LessonData lessonData) {
        lessonDataRepo.delete(lessonData);
    }
}
