package istu.pm.schedule.services;

import istu.pm.schedule.entities.Group;

import java.util.List;
import java.util.Set;

public interface GroupService {
    List<Group> getGroupsByTeacherId(String groupName, List<Integer> facultyIds, int pageIndex);

    List<Group> getGroupsByTeacherId(Integer teacherId, Integer pageIndex);

    Group create(Group group);

    Group edit(Group groupDB, Group group);

    void delete(Group group);
}
