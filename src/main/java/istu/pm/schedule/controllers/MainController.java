package istu.pm.schedule.controllers;

import istu.pm.schedule.entities.Group;
import istu.pm.schedule.entities.LessonData;
import istu.pm.schedule.entities.Teacher;
import istu.pm.schedule.services.FacultyService;
import istu.pm.schedule.services.GroupService;
import istu.pm.schedule.services.LessonDataService;
import istu.pm.schedule.services.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("schedule")
public class MainController {
    private final GroupService groupService;
    private final FacultyService facultyService;
    private final LessonDataService lessonDataService;
    private final TeacherService teacherService;

    public MainController(GroupService groupService, FacultyService facultyService, LessonDataService lessonDataService, TeacherService teacherService) {
        this.groupService = groupService;
        this.facultyService = facultyService;
        this.lessonDataService = lessonDataService;
        this.teacherService = teacherService;
    }

    @GetMapping({"/search?group={searchedGroup}&pagination={pagination}", "./admin/search?group={searchedGroup}&pagination={pagination}"})
    List<Group> findGroupsByName(@PathVariable String searchedGroup, @PathVariable Integer pagination) {
        List<String> groupAttributes = Arrays.asList(searchedGroup.split(","));
        groupAttributes.forEach(groupAttribute -> groupAttribute.replaceAll("\\s", ""));
        List<Integer> facultyIds = null;
        if (groupAttributes.size() > 1) {
            facultyIds = facultyService.getFacultyIdsByName(groupAttributes.get(0));
        }
        return groupService.getGroupsByTeacherId(groupAttributes.get(groupAttributes.size() - 1), facultyIds, pagination);
    }

    @GetMapping
    List<Group> schedule() {
        return groupService.getGroupsByTeacherId(null, null, 1);
    }

    @GetMapping("/group?id={id}")
    public Map getLessons(@PathVariable Integer id) {
        return groupService.getImages(id);
    }

    @GetMapping("/teacher/search?name={teacherName}")
    List<Teacher> findTeacherByName(@PathVariable String teacherName) {
        return teacherService.getTeachersByNameContaining(teacherName);
    }

    @GetMapping("/teacher/groups?id={teacherId}")
    List<Group> getGroupsByTeacherId(@PathVariable Integer teacherId, @PathVariable Integer pagination) {
        return groupService.getGroupsByTeacherId(teacherId, pagination);
    }


}
