package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.SenioritySubsidiesStandardBO;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesStandardDTO;
import com.bjike.goddess.assistance.entity.SenioritySubsidiesStandard;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SenioritySubsidiesStandardTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工龄补助标准业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:07 ]
 * @Description: [ 工龄补助标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class SenioritySubsidiesStandardSerImpl extends ServiceImpl<SenioritySubsidiesStandard, SenioritySubsidiesStandardDTO> implements SenioritySubsidiesStandardSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对审核权限（岗位级别）
     */
    private void checkAduitIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对审核权限（岗位级别）
     */
    private Boolean guideAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    private Boolean allTrueIdentity() throws SerException{
        return true;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAuditIdentity();
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case MANAGEAUDIT:
                flag = guideAuditIdentity();
                break;
            case CONFIRM:
                flag = allTrueIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countSenior(SenioritySubsidiesStandardDTO senioritySubsidiesStandardDTO) throws SerException {
        Long count = super.count(senioritySubsidiesStandardDTO);
        return count;
    }

    @Override
    public SenioritySubsidiesStandardBO getOneById(String id) throws SerException {
        SenioritySubsidiesStandard senioritySubsidiesStandard = super.findById(id);
        return BeanTransform.copyProperties(senioritySubsidiesStandard, SenioritySubsidiesStandardBO.class);
    }

    @Override
    public List<SenioritySubsidiesStandardBO> listSenior(SenioritySubsidiesStandardDTO senioritySubsidiesStandardDTO) throws SerException {
        checkSeeIdentity();
        List<SenioritySubsidiesStandard> senioritySubsidiesStandards = super.findByPage(senioritySubsidiesStandardDTO);
        return BeanTransform.copyProperties(senioritySubsidiesStandards, SenioritySubsidiesStandardBO.class);
    }

    @Override
    public SenioritySubsidiesStandardBO addSenior(SenioritySubsidiesStandardTO senioritySubsidiesStandardTO) throws SerException {
        checkSeeIdentity();
        SenioritySubsidiesStandard senioritySubsidiesStandard = BeanTransform.copyProperties(senioritySubsidiesStandardTO, SenioritySubsidiesStandard.class, true);
        senioritySubsidiesStandard.setCreateTime(LocalDateTime.now());
        super.save(senioritySubsidiesStandard);
        return BeanTransform.copyProperties(senioritySubsidiesStandard, SenioritySubsidiesStandardBO.class);
    }

    @Override
    public SenioritySubsidiesStandardBO editSenior(SenioritySubsidiesStandardTO senioritySubsidiesStandardTO) throws SerException {
        checkSeeIdentity();
        SenioritySubsidiesStandard senioritySubsidiesStandard = super.findById(senioritySubsidiesStandardTO.getId());
        BeanTransform.copyProperties(senioritySubsidiesStandardTO, senioritySubsidiesStandard, true);
        senioritySubsidiesStandard.setModifyTime(LocalDateTime.now());
        super.update(senioritySubsidiesStandard);
        return BeanTransform.copyProperties(senioritySubsidiesStandard, SenioritySubsidiesStandardBO.class);
    }

    @Override
    public void deleteSubsidy(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }
}