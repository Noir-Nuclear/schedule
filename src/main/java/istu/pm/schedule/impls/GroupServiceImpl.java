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

    public List<Group> findPartOfGroups(int i) {
        List<Group> groups = groupRepo.findAll();
        int pagination = 10;
        return groups.subList((i - 1) * pagination, groups.size() <= pagination * i ? groups.size() - 1 : pagination * i);
    }

}
