package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.workjoin.bo.WorkJoinDutyBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDutyDTO;
import com.bjike.goddess.workjoin.entity.WorkJoinDuty;
import com.bjike.goddess.workjoin.enums.GuideAddrStatus;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.WorkJoinDutyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作交接责任义务业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:20 ]
 * @Description: [ 工作交接责任义务业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class WorkJoinDutySerImpl extends ServiceImpl<WorkJoinDuty, WorkJoinDutyDTO> implements WorkJoinDutySer {
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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public Long countWorkJoinDuty(WorkJoinDutyDTO workJoinDutyDTO) throws SerException {
        Long count = super.count(workJoinDutyDTO);
        return count;
    }

    @Override
    public WorkJoinDutyBO getOne(String id) throws SerException {
        WorkJoinDuty workJoinDuty = super.findById(id);
        return BeanTransform.copyProperties(workJoinDuty, WorkJoinDutyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<WorkJoinDutyBO> findListWorkJoinDuty(WorkJoinDutyDTO workJoinDutyDTO) throws SerException {
        checkSeeIdentity();
        workJoinDutyDTO.getSorts().add("createTime=desc");
        List<WorkJoinDuty> workJoinDuties = super.findByPage(workJoinDutyDTO);
        List<WorkJoinDutyBO> workJoinDutyBOS = BeanTransform.copyProperties(workJoinDuties, WorkJoinDutyBO.class);
        return workJoinDutyBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public WorkJoinDutyBO insertWorkJoinDuty(WorkJoinDutyTO workJoinDutyTO) throws SerException {
        checkAddIdentity();
        WorkJoinDuty workJoinDuty = BeanTransform.copyProperties(workJoinDutyTO, WorkJoinDuty.class, true);
        workJoinDuty.setCreateTime(LocalDateTime.now());
        super.save(workJoinDuty);
        return BeanTransform.copyProperties(workJoinDuty, WorkJoinDutyBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public WorkJoinDutyBO editWorkJoinDuty(WorkJoinDutyTO workJoinDutyTO) throws SerException {
        checkAddIdentity();
        WorkJoinDuty workJoinDuty = super.findById(workJoinDutyTO.getId());
        LocalDateTime creatTime = workJoinDuty.getCreateTime();
        workJoinDuty = BeanTransform.copyProperties(workJoinDutyTO, WorkJoinDuty.class, true);
        workJoinDuty.setCreateTime(creatTime);
        workJoinDuty.setModifyTime(LocalDateTime.now());
        super.update(workJoinDuty);
        return BeanTransform.copyProperties(workJoinDuty, WorkJoinDutyBO.class);

    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeWorkJoinDuty(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
}