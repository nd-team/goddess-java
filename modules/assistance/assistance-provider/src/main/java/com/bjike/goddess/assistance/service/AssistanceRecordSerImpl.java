package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistanceRecordBO;
import com.bjike.goddess.assistance.dto.AssistanceRecordDTO;
import com.bjike.goddess.assistance.entity.AssistanceRecord;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.to.AssistanceRecordTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公司员工补助信息记录业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:07 ]
 * @Description: [ 公司员工补助信息记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class AssistanceRecordSerImpl extends ServiceImpl<AssistanceRecord, AssistanceRecordDTO> implements AssistanceRecordSer {
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
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public AssistanceRecordBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        AssistanceRecordBO rightSetBO = BeanTransform.copyProperties(super.findById( id ),AssistanceRecordBO.class);
        return null;
    }

    @Override
    public Long countAssistanceRecord(AssistanceRecordDTO assistanceRecordDTO) throws SerException {

        assistanceRecordDTO.getSorts().add("createTime=desc");
        Long count = super.count(assistanceRecordDTO);
        return count;
    }

    @Override
    public List<AssistanceRecordBO> listAssistanceRecord(AssistanceRecordDTO assistanceRecordDTO) throws SerException {
        checkSeeIdentity();
        assistanceRecordDTO.getSorts().add("createTime=desc");
        List<AssistanceRecord> list = super.findByCis(assistanceRecordDTO, true);

        return BeanTransform.copyProperties(list, AssistanceRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceRecordBO addAssistanceRecord(AssistanceRecordTO assistanceRecordTO) throws SerException {
        checkAddIdentity();
        AssistanceRecord assistanceRecord = BeanTransform.copyProperties(assistanceRecordTO, AssistanceRecord.class, true);

        assistanceRecord.setCreateTime(LocalDateTime.now());
        super.save(assistanceRecord);
        return BeanTransform.copyProperties(assistanceRecord, AssistanceRecordBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceRecordBO editAssistanceRecord(AssistanceRecordTO assistanceRecordTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(assistanceRecordTO.getId())) {
            throw new SerException("id不能为空");
        }

        AssistanceRecord assistanceRecord = BeanTransform.copyProperties(assistanceRecordTO, AssistanceRecord.class, true);
        AssistanceRecord rs = super.findById(assistanceRecordTO.getId());

        rs.setEmpName(assistanceRecord.getEmpName());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AssistanceRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAssistanceRecord(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }



}