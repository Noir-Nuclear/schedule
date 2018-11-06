package istu.pm.schedule.impls;

import istu.pm.schedule.entities.Group;
import istu.pm.schedule.repos.GroupRepo;
import istu.pm.schedule.services.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepo groupRepo;

    public GroupServiceImpl(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public List<Group> getGroups(String groupName, List<String> facultyIds, int pageIndex) {
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

}
