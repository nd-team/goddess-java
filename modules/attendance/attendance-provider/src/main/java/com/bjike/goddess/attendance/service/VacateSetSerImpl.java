package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.VacateSetBO;
import com.bjike.goddess.attendance.dto.VacateSetDTO;
import com.bjike.goddess.attendance.entity.Vacate;
import com.bjike.goddess.attendance.entity.VacateSet;
import com.bjike.goddess.attendance.enums.GuideAddrStatus;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.VacateSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 请假设置业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-12 01:46 ]
 * @Description: [ 请假设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class VacateSetSerImpl extends ServiceImpl<VacateSet, VacateSetDTO> implements VacateSetSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
            flag = cusPermissionSer.getCusPermission("7");
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
            flag = cusPermissionSer.getCusPermission("7");
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
            flag = cusPermissionSer.getCusPermission("7");
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
            flag = cusPermissionSer.getCusPermission("7");
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void save(VacateSetTO to) throws SerException {
        checkAddIdentity();
        VacateSetDTO dto=new VacateSetDTO();
        dto.getConditions().add(Restrict.eq("vacateType",to.getVacateType()));
        VacateSet entity=super.findOne(dto);
        if (null!=entity){
            throw new SerException("已存在该请假类型的请假设置，不能再添加了");
        }
        String name=userAPI.currentUser().getUsername();
        entity = BeanTransform.copyProperties(to, VacateSet.class, true);
        entity.setName(name);
        super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(VacateSetTO to) throws SerException {
        checkAddIdentity();
        VacateSet entity = super.findById(to.getId());
        VacateSet vacateSet = BeanTransform.copyProperties(to, VacateSet.class, true);
        BeanUtils.copyProperties(vacateSet,entity,"id","createTime","name");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    private VacateSetBO tranBO(VacateSet vacateSet) throws SerException{
        VacateSetBO bo=BeanTransform.copyProperties(vacateSet,VacateSetBO.class);
        return bo;
    }

    @Override
    public List<VacateSetBO> list(VacateSetDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<VacateSet> list = super.findByCis(dto, true);
        List<VacateSetBO> bos=new ArrayList<>();
        for (VacateSet v:list){
            bos.add(tranBO(v));
        }
        return bos;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        VacateSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public VacateSetBO findByID(String id) throws SerException {
        VacateSet entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return tranBO(entity);
    }

    @Override
    public Long count(VacateSetDTO dto) throws SerException {
        return super.count(dto);
    }
}