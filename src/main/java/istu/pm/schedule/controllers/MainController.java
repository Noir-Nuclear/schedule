package istu.pm.schedule.controllers;

import istu.pm.schedule.entities.Group;
import istu.pm.schedule.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("schedule")
public class MainController {
    private final GroupService groupService;

    public MainController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/search?group={searchedGroup}")
    List<Group> findGroupsByName(@PathVariable String searchedGroup) {
        List<String> groupAttributes = Arrays.asList(searchedGroup.split(","));
        return null;
    }

    @GetMapping
    List<Group> schedule() {
        return groupService.findFiveFirstGroups();
    }

    @GetMapping("/search")
    String getpp() {
        return "getPP";
    }

    @GetMapping("/teacher/search?name={teacherName}")
    List<Group> findGroupsByTeacher(@PathVariable String teacherName) {
        return null;
    }
}
