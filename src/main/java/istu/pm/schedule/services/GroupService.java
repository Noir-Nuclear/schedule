package istu.pm.schedule.services;

import istu.pm.schedule.entities.Group;

import java.util.List;
import java.util.Set;

public interface GroupService {
    List<Group> getGroups(String groupName, List<Integer> facultyIds, int pageIndex);

    List<Group> getGroupsByIds(Set groupIds);
}
