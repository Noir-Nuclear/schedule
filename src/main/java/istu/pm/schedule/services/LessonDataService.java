package istu.pm.schedule.services;

import istu.pm.schedule.entities.LessonData;

import java.util.List;
import java.util.Set;

public interface LessonDataService {

    List<LessonData> getLessonsByGroupId(Integer groupId);
    Set<Integer> getGroupIdsByTeacherId(Integer teacherId);

    LessonData create(LessonData lessonData);
    LessonData edit(LessonData changedLesson, LessonData newLesson);
    void delete(LessonData lessonData);
}
