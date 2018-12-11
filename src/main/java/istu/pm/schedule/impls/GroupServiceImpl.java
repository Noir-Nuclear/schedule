package istu.pm.schedule.impls;

import istu.pm.schedule.entities.Group;
import istu.pm.schedule.entities.LessonData;
import istu.pm.schedule.repos.GroupRepo;
import istu.pm.schedule.repos.LessonDataRepo;
import istu.pm.schedule.services.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private final GroupRepo groupRepo;
    @Autowired
    private final LessonDataRepo lessonDataRepo;

    public GroupServiceImpl(GroupRepo groupRepo, LessonDataRepo lessonDataRepo) {
        this.groupRepo = groupRepo;
        this.lessonDataRepo = lessonDataRepo;
    }

    public List<Group> getGroupsByTeacherId(String groupName, List<Integer> facultyIds, int pageIndex) {
        List<Group> groups;
        if (groupName == null) {
            groups = groupRepo.findAll();
        } else {
            groups = groupRepo.getAllByNameContaining(groupName);
            if (facultyIds.size() > 0) {
                groups = groups.stream().filter(group -> facultyIds.contains(group.getFaculty().getId())).collect(Collectors.toList());
            }
        }
        int pagination = 10;
        return groups.subList(
                (pageIndex - 1) * pagination,
                groups.size() <= (pagination * pageIndex) ?
                        groups.size() - 1 :
                        pagination * pageIndex
        );
    }

    @Override
    public List<Group> getGroupsByTeacherId(Integer teacherId, Integer pageIndex) {
        List groups = groupRepo.findAll();
        List<LessonData> lessonsOfTeacher = lessonDataRepo.getAllByTeacher(teacherId);
        List<Group> teacherGroups = (List<Group>) groups.stream().filter((Object group) -> {
            for (LessonData lesson : lessonsOfTeacher) {
                if (lesson.getGroup().equals(group))
                    return true;
            }
            return false;
        }).collect(Collectors.toList());
        return teacherGroups.subList((pageIndex - 1) * pageIndex,
                groups.size() <= (pageIndex * pageIndex) ?
                        groups.size() - 1 :
                        pageIndex * pageIndex);
    }

    @Override
    public Group create(Group group) {
        return groupRepo.save(group);
    }

    @Override
    public Group edit(Group groupDB, Group group) {
        BeanUtils.copyProperties(group, groupDB, "id");
        return groupRepo.save(groupDB);
    }

    @Override
    public void delete(Group group) {
        groupRepo.delete(group);
    }

}
