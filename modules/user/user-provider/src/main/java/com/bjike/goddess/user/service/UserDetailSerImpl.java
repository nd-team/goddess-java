package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.entity.Department;
import com.bjike.goddess.user.entity.Position;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.UserDetail;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.service.rbac.GroupSer;
import com.bjike.goddess.user.to.UserDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 部门业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserDetailSerImpl extends ServiceImpl<UserDetail, UserDetailDTO> implements UserDetailSer {


    @Autowired
    private DepartmentSer departmentSer;
    @Autowired
    private GroupSer groupSer;
    @Autowired
    private UserSer userSer;
    @Autowired
    private PositionSer positionSer;

    @Transactional
    @Override
    public void update(UserDetailTO detailTO) throws SerException {
        UserDetailDTO dto = new UserDetailDTO();
        UserBO userBO = userSer.currentUser();
        User user = userSer.findById(userBO.getId());
        dto.getConditions().add(Restrict.eq("user.id", user.getId()));
        UserDetail userDetail = super.findOne(dto);
        if (null != userDetail) {
            BeanTransform.copyProperties(userDetail, detailTO, true);
            userDetail.setGroup(groupSer.findById(detailTO.getGroupId()));
            userDetail.setDepartment(departmentSer.findById(detailTO.getDepartmentId()));
            userDetail.setPosition(positionSer.findById(detailTO.getPositionId()));
            super.update(userDetail);
        } else {
            userDetail = new UserDetail();
            BeanTransform.copyProperties(userDetail, detailTO);
            userDetail.setUser(user);
            userDetail.setGroup(groupSer.findById(detailTO.getGroupId()));
            userDetail.setDepartment(departmentSer.findById(detailTO.getDepartmentId()));
            userDetail.setPosition(positionSer.findById(detailTO.getPositionId()));
            userDetail.setModifyTime(LocalDateTime.now());
            super.save(userDetail);
        }


    }

    @Override
    public UserDetailBO findByUserId(String userId) throws SerException {
        UserDetailDTO detailDTO = new UserDetailDTO();
        detailDTO.getConditions().add(Restrict.eq("user.id", userId));
        UserDetail detail = super.findOne(detailDTO);
        if (null != detail) {
            UserDetailBO userDetailBO = BeanTransform.copyProperties(detail, UserDetailBO.class);
            Department department = detail.getDepartment();
            Group group = detail.getGroup();
            Position position = detail.getPosition();
            if (null != department) {
                userDetailBO.setDepartmentId(detail.getDepartment().getId());
                userDetailBO.setDepartmentName(detail.getDepartment().getName());
            }
            if (null != group) {
                userDetailBO.setGroupId(detail.getGroup().getId());
                userDetailBO.setGroupName(detail.getGroup().getName());
            }
            if (null != position) {
                userDetailBO.setPositionId(detail.getPosition().getId());
                userDetailBO.setPositionName(detail.getPosition().getName());
            }
            return userDetailBO;
        }

        return null;
    }

    @Override
    public List<UserDetailBO> findByMonth(UserDetailDTO dto, Integer month) throws SerException {

        // TODO: 17-4-5 离职尚未处理
        StringBuilder sql = new StringBuilder();
        sql.append("select userdetail.realName , department.name as departmentName, usergroup.name as groupName ," +
                "userdetail.address , MONTH(userdetail.birthday) as birthMonth from user_detail userdetail" +
                " , user_department department,rbac_group usergroup where userdetail.department_id=department.id and userdetail.group_id=usergroup.id ");
        if (month != null) {
            sql.append("and MONTH(userdetail.birthday) = " + month);
        }
        String[] fields = new String[]{"realName", "departmentName", "groupName", "address", "birthMonth"};
        List<UserDetailBO> list = super.findBySql(sql.toString(), UserDetailBO.class, fields);

        return list;
    }
}
