package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.entity.EmployeeFunctionLevel;
import com.bjike.goddess.managepromotion.entity.OverviewSkillLevel;
import com.bjike.goddess.managepromotion.entity.SkillPromotionApply;
import com.bjike.goddess.managepromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managepromotion.to.EmployeeFunctionLevelTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 员工职能定级业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 04:53 ]
 * @Description: [ 员工职能定级业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class EmployeeFunctionLevelSerImpl extends ServiceImpl<EmployeeFunctionLevel, EmployeeFunctionLevelDTO> implements EmployeeFunctionLevelSer {

    @Autowired
    private OverviewSkillLevelSer overviewSkillLevelSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countEmployeeFunctionLevel(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws SerException {
        Long count = super.count(employeeFunctionLevelDTO);
        return count;
    }

    @Override
    public EmployeeFunctionLevelBO getOne(String id) throws SerException {
        EmployeeFunctionLevel employeeFunctionLevel = super.findById(id);
        return BeanTransform.copyProperties(employeeFunctionLevel, EmployeeFunctionLevelBO.class);
    }

    @Override
    public List<EmployeeFunctionLevelBO> findListEmployeeFunctionLevel(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws SerException {
        checkSeeIdentity();
        employeeFunctionLevelDTO.getSorts().add("createTime=desc");
        List<EmployeeFunctionLevel> employeeFunctionLevels = super.findByCis(employeeFunctionLevelDTO);
        List<EmployeeFunctionLevelBO> employeeFunctionLevelBOS = BeanTransform.copyProperties(employeeFunctionLevels, EmployeeFunctionLevelBO.class);
        return employeeFunctionLevelBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeeFunctionLevelBO insertSkillGrading(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        checkAddIdentity();
        EmployeeFunctionLevel employeeFunctionLevel = BeanTransform.copyProperties(employeeFunctionLevelTO, EmployeeFunctionLevel.class, true);
        employeeFunctionLevel.setCreateTime(LocalDateTime.now());

        String sql = "select is_subject from managepromotion_employeefunctionlevel where name='' and is_subject=0 ";
        String[] fields = new String[]{"subject"};
        List<EmployeeFunctionLevelBO> levelBOS = super.findBySql(sql, EmployeeFunctionLevelBO.class, fields);

        if (null != levelBOS && levelBOS.size() > 0) {
            throw new SerException("主项只能有一个");
        }
        super.save(employeeFunctionLevel);
        return BeanTransform.copyProperties(employeeFunctionLevel, EmployeeFunctionLevelBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeeFunctionLevelBO editEmployeeFunctionLevel(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        checkAddIdentity();
        EmployeeFunctionLevel employeeFunctionLevel = super.findById(employeeFunctionLevelTO.getId());
        BeanTransform.copyProperties(employeeFunctionLevelTO,employeeFunctionLevel,true);
        employeeFunctionLevel.setModifyTime(LocalDateTime.now());
        String sql = "select is_subject from managepromotion_employeefunctionlevel where name='a' and is_subject=0 ";
        String[] fields = new String[]{"subject"};
        List<EmployeeFunctionLevelBO> levelBOS = super.findBySql(sql, EmployeeFunctionLevelBO.class, fields);

        if (null != levelBOS && levelBOS.size() > 0) {
            throw new SerException("主项只能有一个");
        }
        super.update(employeeFunctionLevel);
        return BeanTransform.copyProperties(employeeFunctionLevel, EmployeeFunctionLevelBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeEmployeeFunctionLevel(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);

    }
    @Override
    public OverviewSkillLevelBO skill(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {

        EmployeeFunctionLevel employeeFunctionLevel = super.findById(employeeFunctionLevelTO.getId());
        BeanTransform.copyProperties(employeeFunctionLevelTO,employeeFunctionLevel,true);

        OverviewSkillLevel overviewSkillLevel = new OverviewSkillLevel();
        BeanUtils.copyProperties(employeeFunctionLevel,overviewSkillLevel);
        overviewSkillLevel.setPromotedNumber(0);
        if(overviewSkillLevel.getName().equals("")){
            overviewSkillLevel.setPromotedNumber(0+1);
        }
        overviewSkillLevelSer.save(overviewSkillLevel);
        return BeanTransform.copyProperties(overviewSkillLevel,OverviewSkillLevelBO.class);
    }
}