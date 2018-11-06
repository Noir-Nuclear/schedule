package istu.pm.schedule.services;

import istu.pm.schedule.entities.LessonData;

import java.util.List;

public interface LessonDataService {

    List<LessonData> getLessonsByGroupId(Integer groupId);
    LessonData create(LessonData lessonData);
    LessonData edit(LessonData changedLesson, LessonData newLesson);
    void delete(LessonData lessonData);

}
