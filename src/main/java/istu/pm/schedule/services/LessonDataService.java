package istu.pm.schedule.services;

import istu.pm.schedule.entities.LessonData;

public interface LessonDataService {

    LessonData create(LessonData lessonData);
    LessonData edit(LessonData changedLesson, LessonData newLesson);
    void delete(LessonData lessonData);

}
