import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.service.rbac.GroupAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.AppConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-02-28 11:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class GroupTest {
    @Autowired
    private GroupAPI groupAPI;


    @Test
    public void  init() throws  Exception{
        List<Group> groups = new ArrayList<>();

        Group group = new Group();
        group.setName("父节点1");
        group.setCreateTime(LocalDateTime.now());
        group.setDescription("not has ");
        groups.add(group);

        Group group1 = new Group();
        group1.setName("父节点2");
        group1.setCreateTime(LocalDateTime.now());
        group1.setDescription("not has ");
        groups.add(group1);

        Group group2 = new Group();
        group2.setName("父节点3");
        group2.setCreateTime(LocalDateTime.now());
        group2.setDescription("not has ");
        groups.add(group2);


        Group group2_child = new Group();
        group2_child.setName("父节点3_子节点1");
        group2_child.setCreateTime(LocalDateTime.now());
        group2_child.setDescription("not has ");
        group2_child.setParent(group2);
        groups.add(group2_child);
        groupAPI.save(groups);


    }
}
