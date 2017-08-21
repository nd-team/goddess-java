package com.bjike.goddess.managementpromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managementpromotion.bo.LevelShowBO;
import com.bjike.goddess.managementpromotion.dto.LevelShowDTO;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.managementpromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managementpromotion.to.GuidePermissionTO;
import com.bjike.goddess.managementpromotion.to.LevelShowTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理等级情况慨览业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:53 ]
 * @Description: [ 管理等级情况慨览业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managementpromotionSerCache")
@Service
public class LevelShowSerImpl extends ServiceImpl<LevelShow, LevelShowDTO> implements LevelShowSer {
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
    @Transactional(rollbackFor = {SerException.class})
    public LevelShowBO save(LevelShowTO to) throws SerException {
        checkAddIdentity();
        LevelShow entity = BeanTransform.copyProperties(to, LevelShow.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, LevelShowBO.class);
    }

    @Override
    public List<LevelShowBO> find(LevelShowDTO dto) throws SerException {
        checkSeeIdentity();
        return BeanTransform.copyProperties(super.findByCis(dto, true), LevelShowBO.class);
    }

    @Override
    public Long count(LevelShowDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<LevelShowBO> findAll(LevelShowDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByCis(dto), LevelShowBO.class);
    }

    @Override
    public LevelShowBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), LevelShowBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(LevelShowTO to) throws SerException {
        checkAddIdentity();
        LevelShow entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, LevelShow.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public LevelShow findByEmployeeId(String employeeId) throws SerException {
        String[] strings = new String[]{employeeId};
        List<LevelShow> list = null;
        for (String s : strings) {
            String sql = "SELECT id,promotionNum from managementpromotion_levelshow\n" +
                    "where employeeId='" + s + "'";
            String[] fileds = new String[]{"id", "promotionNum"};
            list = super.findBySql(sql, LevelShow.class, fileds);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0);
        }
        return null;
    }
}