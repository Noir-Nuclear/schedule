package istu.pm.schedule.services;

import istu.pm.schedule.entities.Group;

import java.util.List;

public interface GroupService {
    List<Group> findFiveFirstGroups();
}
