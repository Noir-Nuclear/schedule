package istu.pm.schedule.services;

import istu.pm.schedule.entities.Group;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GroupService {
    List<Group> getGroupsByTeacherId(String groupName, List<Integer> facultyIds, int pageIndex);

    List<Group> getGroupsByTeacherId(Integer teacherId, Integer pageIndex);

    Map<String, byte[]> getImages(Integer groupId);

    Group create(Group group, byte[] bigImageData, byte[] miniImageData);

    Group edit(Group groupDB, Group group, byte[] bigImageData, byte[] miniImageData);

    void delete(Group group);
}
