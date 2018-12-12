package istu.pm.schedule.controllers;

import istu.pm.schedule.entities.Faculty;
import istu.pm.schedule.entities.Group;
import istu.pm.schedule.entities.LessonData;
import istu.pm.schedule.entities.Teacher;
import istu.pm.schedule.services.FacultyService;
import istu.pm.schedule.services.GroupService;
import istu.pm.schedule.services.LessonDataService;
import istu.pm.schedule.services.TeacherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final LessonDataService lessonDataService;
    private final GroupService groupService;
    private final FacultyService facultyService;
    private final TeacherService teacherService;

    public AdminController(LessonDataService lessonDataService, GroupService groupService, FacultyService facultyService, TeacherService teacherService) {
        this.lessonDataService = lessonDataService;
        this.groupService = groupService;
        this.facultyService = facultyService;
        this.teacherService = teacherService;
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @PostMapping("/lessons")
    LessonData createLesson(@RequestBody LessonData lessonData) {
        return lessonDataService.create(lessonData);
    }

    @PutMapping("/lessons?id={id}")
    LessonData updateLesson(@PathVariable("id") LessonData lessonDataDB,
                            @RequestBody LessonData lessonData) {
        return lessonDataService.edit(lessonDataDB, lessonData);
    }

    @DeleteMapping("/lessons?id={id}")
    void deleteLesson(@PathVariable("id") LessonData lessonData) {
        lessonDataService.delete(lessonData);
    }

    @PostMapping("/groups")
    Group createGroup(@RequestBody Group group,
                      @RequestBody byte[] bigImageData,
                      @RequestBody byte[] miniImageData) {
        return groupService.create(group, bigImageData, miniImageData);
    }

    @PutMapping("/groups?id={id}")
    Group updateGroup(@PathVariable("id") Group groupDB,
                            @RequestBody Group group,
                      @RequestBody byte[] bigImageData,
                      @RequestBody byte[] miniImageData) {
        return groupService.edit(groupDB, group, bigImageData, miniImageData);
    }

    @DeleteMapping("/groups?id={id}")
    void deleteGroup(@PathVariable("id") Group group) {
        groupService.delete(group);
    }

    @PostMapping("/teachers")
    Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.create(teacher);
    }

    @PutMapping("/teachers?id={id}")
    Teacher updateTeacher(@PathVariable("id") Teacher teacherDB,
                      @RequestBody Teacher teacher) {
        return teacherService.edit(teacherDB, teacher);
    }

    @DeleteMapping("/teachers?id={id}")
    void deleteTeacher(@PathVariable("id") Teacher teacher) {
        teacherService.delete(teacher);
    }

    @PostMapping("/faculties")
    Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.create(faculty);
    }

    @PutMapping("/faculties?id={id}")
    Faculty updateFaculty(@PathVariable("id") Faculty facultyDB, @RequestBody Faculty faculty) {
        return facultyService.edit(facultyDB, faculty);
    }

    @DeleteMapping("faculties?id={id}")
    void deleteFaculty(@PathVariable("id") Faculty faculty) {
        facultyService.delete(faculty);
    }

    @GetMapping("?error={error}")
    String error(String error) {
        return error;
    }
}
