import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.user.api.rbac.GroupAPI;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.service.rbac.GroupSer;
import com.bjike.goddess.user.to.rbac.GroupTO;
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

    public static void main(String[] args) {
        Group group = new Group();
        group.setCreateTime(LocalDateTime.now());
        GroupTO to = BeanTransform.copyProperties(group,GroupTO.class);
        System.out.println(to);
    }

}
