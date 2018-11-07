package istu.pm.schedule.impls;

import istu.pm.schedule.entities.LessonData;
import istu.pm.schedule.repos.LessonDataRepo;
import istu.pm.schedule.services.LessonDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LessonDataServiceImpl implements LessonDataService {
    private final LessonDataRepo lessonDataRepo;

    public LessonDataServiceImpl(LessonDataRepo lessonDataRepo) {
        this.lessonDataRepo = lessonDataRepo;
    }

    public List<LessonData> getLessonsByGroupId(Integer groupId) {
        return lessonDataRepo.getAllByGroup(groupId);
    }

    @Override
    public Set<Integer> getGroupIdsByTeacherId(Integer teacherId) {
        return lessonDataRepo.getAllByTeacher(teacherId).stream().map(lessonData -> lessonData.getId()).collect(new HashSet<>());
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
