package istu.pm.schedule.controllers;

import istu.pm.schedule.entities.Group;
import istu.pm.schedule.entities.LessonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/admin/search?searchedGroup={searchedGroup}")
    List<Group> findGroup(String searchedGroup) {
        return null;
    }

    @GetMapping("/admin/group?id={id}")
    List<LessonData> getLessonsOfGroup(String id) {
        return null;
    }

    

    @GetMapping("?error={error}")
    String error(String error) {
        return error;
    }
}
