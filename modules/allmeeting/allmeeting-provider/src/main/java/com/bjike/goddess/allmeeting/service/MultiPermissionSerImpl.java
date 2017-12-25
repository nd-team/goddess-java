package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.MultiPermissionBO;
import com.bjike.goddess.allmeeting.dto.MultiPermissionDTO;
import com.bjike.goddess.allmeeting.entity.MultiPermission;
import com.bjike.goddess.allmeeting.enums.PermissionType;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调阅权限业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-28 03:02 ]
 * @Description: [ 调阅权限业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class MultiPermissionSerImpl extends ServiceImpl<MultiPermission, MultiPermissionDTO> implements MultiPermissionSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    //查询调阅权限
    @Override
    public Boolean getPermission(String userNum) throws SerException {
        MultiPermissionDTO dto = new MultiPermissionDTO();
        dto.getConditions().add(Restrict.eq("userNum", userNum));
        dto.getConditions().add(Restrict.eq("type", PermissionType.ALLOW));
        List<MultiPermission> permissionList = super.findByCis(dto);
        if (!CollectionUtils.isEmpty(permissionList)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MultiPermissionBO insertModel() throws SerException {
        UserBO userBO = userAPI.currentUser();

        //查询当前用户是否已经申请
        if (isExist(userBO.getEmployeeNumber())) {
            throw new SerException("亲,您已经申请了调阅,无需重复申请,请等待审核!");
        }
        MultiPermission model = new MultiPermission();
        model.setUserName(userBO.getUsername());
        model.setUserNum(userBO.getEmployeeNumber());
        //默认禁止
        model.setType(PermissionType.FORBID);
        super.save(model);
        return BeanTransform.copyProperties(model, MultiPermissionBO.class);
    }

    @Override
    public List<MultiPermissionBO> pageList(MultiPermissionDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), MultiPermissionBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void agree(String id) throws SerException {
        UserBO userBO = userAPI.currentUser();
        PositionDetailUserBO position = positionDetailUserAPI.findOneByUser(userBO.getId());
        if (position != null) {
            if (position.getPosition().equals("总经理") || position.getPosition().equals("总经办")) {
                MultiPermission model = super.findById(id);
                if (model != null) {
                    model.setType(PermissionType.ALLOW);
                    model.setModifyTime(LocalDateTime.now());
                    super.update(model);
                } else {
                    throw new SerException("非法ID,申请调阅对象不存在!");
                }
            }else{
                throw new SerException("只有总经办或者总经理才可审核!");
            }
        } else {
            throw new SerException("只有总经办或者总经理才可审核!");
        }
    }

    public Boolean isExist(String userNum) throws SerException {
        MultiPermissionDTO dto = new MultiPermissionDTO();
        dto.getConditions().add(Restrict.eq("userNum", userNum));
        List<MultiPermission> list = super.findByCis(dto);
        if (!CollectionUtils.isEmpty(list)) {
            return true;
        } else {
            return false;
        }
    }
}