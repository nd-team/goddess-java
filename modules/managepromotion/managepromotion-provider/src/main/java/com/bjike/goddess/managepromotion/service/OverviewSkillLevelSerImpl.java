package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.dto.OverviewSkillLevelDTO;
import com.bjike.goddess.managepromotion.entity.OverviewSkillLevel;
import com.bjike.goddess.managepromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.OverviewSkillLevelTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 技能等级情况概览业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class OverviewSkillLevelSerImpl extends ServiceImpl<OverviewSkillLevel, OverviewSkillLevelDTO> implements OverviewSkillLevelSer {

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
    public Long countOverviewSkillLevel(OverviewSkillLevelDTO overviewSkillLevelDTO) throws SerException {
        Long count = super.count(overviewSkillLevelDTO);
        return count;
    }

    @Override
    public OverviewSkillLevelBO getOne(String id) throws SerException {
        OverviewSkillLevel overviewSkillLevel = super.findById(id);
        return BeanTransform.copyProperties(overviewSkillLevel,OverviewSkillLevelBO.class);
    }

    @Override
    public List<OverviewSkillLevelBO> findListOverviewSkillLevel(OverviewSkillLevelDTO overviewSkillLevelDTO) throws SerException {
        checkSeeIdentity();
        overviewSkillLevelDTO.getSorts().add("createTime=desc");
        List<OverviewSkillLevel> overviewSkillLevels = super.findByPage(overviewSkillLevelDTO);
        List<OverviewSkillLevelBO> overviewSkillLevelBOS = BeanTransform.copyProperties(overviewSkillLevels,OverviewSkillLevelBO.class);
        return overviewSkillLevelBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OverviewSkillLevelBO insertOverviewSkillLevel(OverviewSkillLevelTO overviewSkillLevelTO) throws SerException {
        checkAddIdentity();
        OverviewSkillLevel overviewSkillLevel = BeanTransform.copyProperties(overviewSkillLevelTO,OverviewSkillLevel.class,true);
        overviewSkillLevel.setCreateTime(LocalDateTime.now());


//        String sql = "select is_subject from managepromotion_employeefunctionlevel where name='' and is_subject=0 ";
//        String[] fields = new String[]{"subject"};
//        List<EmployeeFunctionLevelBO> levelBOS = super.findBySql(sql, EmployeeFunctionLevelBO.class, fields);
//
//        if (null != levelBOS && levelBOS.size() > 0) {
//            throw new SerException("主项只能有一个");
//        }
//
//
//        sql = "SELECT is_subject from managepromotion_overviewskilllevel where is_subject=0 GROUP BY is_subject HAVING count(is_subject)>=5 ";
//        List<OverviewSkillLevelBO> skillLevelBOS = super.findBySql(sql, OverviewSkillLevelBO.class, fields);
//
//        if (null != skillLevelBOS && skillLevelBOS.size() > 5) {
//            throw new SerException("子项只能有5个");
//        }

        super.save(overviewSkillLevel);
        return BeanTransform.copyProperties(overviewSkillLevel,OverviewSkillLevelBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OverviewSkillLevelBO editOverviewSkillLevel(OverviewSkillLevelTO overviewSkillLevelTO) throws SerException {
        checkAddIdentity();
        OverviewSkillLevel overviewSkillLevel = super.findById(overviewSkillLevelTO.getId());
        BeanTransform.copyProperties(overviewSkillLevelTO,overviewSkillLevel,true);
        overviewSkillLevel.setModifyTime(LocalDateTime.now());
        super.update(overviewSkillLevel);
        return BeanTransform.copyProperties(overviewSkillLevel,OverviewSkillLevelBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeOverviewSkillLevel(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public OverviewSkillLevelBO findByName(String employeeName) throws SerException {
        OverviewSkillLevelDTO dto = new OverviewSkillLevelDTO();
        dto.getConditions().add(Restrict.eq("name",employeeName));
        OverviewSkillLevel overviewSkillLevel = super.findOne(dto);
        OverviewSkillLevelBO bo = BeanTransform.copyProperties(overviewSkillLevel,OverviewSkillLevelBO.class,true);
        return bo;
    }
}