package istu.pm.schedule.impls;

import istu.pm.schedule.entities.Group;
import istu.pm.schedule.entities.LessonData;
import istu.pm.schedule.repos.GroupRepo;
import istu.pm.schedule.repos.LessonDataRepo;
import istu.pm.schedule.services.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    private void deleteImages(Group group) {
        File bigImage = new File(group.getImagePath());
        File miniImage = new File(group.getMiniImagePath());
        bigImage.delete();
        miniImage.delete();
    }

    private void saveImages(byte[] bigImageData, byte[] miniImageData, Group group) {
        String bigImagePath = "/images/big-image-" + group.getId() + ".bin";
        String miniImagePath = "/images/mini-image-" + group.getId() + ".bin";
        try {
            FileOutputStream bigImageStream = new FileOutputStream(bigImagePath, false);
            FileOutputStream miniImageStream = new FileOutputStream(miniImagePath, false);
            bigImageStream.write(bigImageData);
            miniImageStream.write(miniImageData);
            bigImageStream.flush();
            bigImageStream.close();
            miniImageStream.flush();
            miniImageStream.close();
            group.setImagePath(bigImagePath);
            group.setMiniImagePath(miniImagePath);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
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
    public Group create(Group group, byte[] bigImageData, byte[] miniImageData) {
        saveImages(bigImageData, miniImageData, group);
        return groupRepo.save(group);
    }

    @Override
    public Group edit(Group groupDB, Group group, byte[] bigImageData, byte[] miniImageData) {
        deleteImages(groupDB);
        saveImages(bigImageData, miniImageData, group);
        BeanUtils.copyProperties(group, groupDB, "id");
        return groupRepo.save(groupDB);
    }

    @Override
    public void delete(Group group) {
        deleteImages(group);
        groupRepo.delete(group);
    }

}
