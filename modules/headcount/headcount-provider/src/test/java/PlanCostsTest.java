import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.headcount.api.GroupInfoAPI;
import com.bjike.goddess.headcount.api.PlanCostsAPI;
import com.bjike.goddess.headcount.bo.GroupInfoBO;
import com.bjike.goddess.headcount.dao.GroupInfoRep;
import com.bjike.goddess.headcount.entity.plancost.GroupInfo;
import headcount_common_code.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: [yewenbo]
 * @Date: [2017-03-16 11:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PlanCostsTest {
    @Autowired
    private PlanCostsAPI planCostsAPI;

    @Autowired
    private GroupInfoAPI groupInfoAPI;

    @Test
    public void update()throws SerException{
        String planCostsId = "ff799d86-bb52-4941-8938-534eacc48dd6";
        GroupInfo groupInfo = BeanTransform.copyProperties(groupInfoAPI.findByPlanCosts_id(planCostsId), GroupInfo.class);
        System.out.println(groupInfo);
        groupInfo.setPlancostnum(666.66);
        groupInfo.setName("名稱");
        System.out.println(groupInfo);
    }



}
