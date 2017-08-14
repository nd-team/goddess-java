package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AssistanceEmpBO;
import com.bjike.goddess.assistance.dto.AssistanceEmpDTO;
import com.bjike.goddess.assistance.entity.AssistanceEmp;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.to.AssistanceEmpTO;
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
 * 补助员工名单业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:12 ]
 * @Description: [ 补助员工名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class AssistanceEmpSerImpl extends ServiceImpl<AssistanceEmp, AssistanceEmpDTO> implements AssistanceEmpSer {
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
            flag = cusPermissionSer.busCusPermission("1");
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
            flag = cusPermissionSer.busCusPermission("1");
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
    public AssistanceEmpBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        AssistanceEmpBO assistanceEmpBO = BeanTransform.copyProperties(super.findById( id ),AssistanceEmpBO.class);

        return BeanTransform.copyProperties(assistanceEmpBO, AssistanceEmpBO.class);
    }

    @Override
    public Long countAssistanceEmp(AssistanceEmpDTO assistanceEmpDTO) throws SerException {

        assistanceEmpDTO.getSorts().add("createTime=desc");
        Long count = super.count(assistanceEmpDTO);
        return count;
    }

    @Override
    public List<AssistanceEmpBO> listAssistanceEmp(AssistanceEmpDTO assistanceEmpDTO) throws SerException {
        checkSeeIdentity();
        assistanceEmpDTO.getSorts().add("createTime=desc");
        List<AssistanceEmp> list = super.findByCis(assistanceEmpDTO, true);

        return BeanTransform.copyProperties(list, AssistanceEmpBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceEmpBO addAssistanceEmp(AssistanceEmpTO assistanceEmpTO) throws SerException {
        checkAddIdentity();
        AssistanceEmp assistanceEmp = BeanTransform.copyProperties(assistanceEmpTO, AssistanceEmp.class, true);

        assistanceEmp.setCreateTime(LocalDateTime.now());
        super.save(assistanceEmp);
        return BeanTransform.copyProperties(assistanceEmp, AssistanceEmpBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public AssistanceEmpBO editAssistanceEmp(AssistanceEmpTO assistanceEmpTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(assistanceEmpTO.getId())) {
            throw new SerException("id不能为空");
        }
        AssistanceEmp assistanceEmp = BeanTransform.copyProperties(assistanceEmpTO, AssistanceEmp.class, true);
        AssistanceEmp rs = super.findById(assistanceEmpTO.getId());

        rs.setEmpName(assistanceEmp.getEmpName());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AssistanceEmpBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAssistanceEmp(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

}