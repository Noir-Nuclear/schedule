package istu.pm.schedule.controllers;

import istu.pm.schedule.entities.Group;
import istu.pm.schedule.services.FacultyService;
import istu.pm.schedule.services.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("schedule")
public class MainController {
    private final GroupService groupService;
    private final FacultyService facultyService;

    public MainController(GroupService groupService, FacultyService facultyService) {
        this.groupService = groupService;
        this.facultyService = facultyService;
    }

    @GetMapping("/search?group={searchedGroup}&pagination={pagination}")
    List<Group> findGroupsByName(@PathVariable String searchedGroup, @PathVariable Integer pagination) {
        List<String> groupAttributes = Arrays.asList(searchedGroup.split(","));
        groupAttributes.forEach(groupAttribute -> {
            groupAttribute.replaceAll("\\s", "");
        });
        List<String> facultyIds = null;
        if (groupAttributes.size() > 1) {
            facultyIds = facultyService.getFacultyIdsByName(groupAttributes.get(0));
        }
        groupService.getGroups(groupAttributes.get(groupAttributes.size() - 1), facultyIds, pagination);
        return null;
    }

    @GetMapping
    List<Group> schedule() {
        return groupService.getGroups(null, null, 1);
    }

    @GetMapping("/teacher/search?name={teacherName}")
    List<Group> findGroupsByTeacher(@PathVariable String teacherName) {
        return null;
    }
}
