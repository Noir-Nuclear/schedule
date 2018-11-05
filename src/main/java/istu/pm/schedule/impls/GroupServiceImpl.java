package istu.pm.schedule.impls;

import istu.pm.schedule.entities.Group;
import istu.pm.schedule.repos.GroupRepo;
import istu.pm.schedule.services.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepo groupRepo;

    public GroupServiceImpl(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public List<Group> findFiveFirstGroups() {
        List<Group> groups = groupRepo.findAll();
        return groups.subList(0, groups.size() <= 5 ? groups.size() : 5);
    }
}
