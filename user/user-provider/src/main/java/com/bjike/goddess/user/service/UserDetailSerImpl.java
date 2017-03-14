package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.dto.DepartmentDTO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.dto.rbac.GroupDTO;
import com.bjike.goddess.user.entity.Department;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.UserDetail;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.service.rbac.GroupSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    private DepartmentSer departmentAPI;
    @Autowired
    private GroupSer groupAPI;
    @Autowired
    private UserSer userAPI;

    @Transactional
    @Override
    public UserDetailBO add() throws SerException {
        UserDTO dto = new UserDTO();
        dto.getConditions().add(Restrict.eq("phone", "13457910241"));
        User user = userAPI.findOne(dto);
        UserDetail userDetail = new UserDetail();
        userDetail.setUser(user);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.getConditions().add(Restrict.eq("name", "a研发部"));
        Department department = departmentAPI.findOne(departmentDTO);
        userDetail.setDepartment(department);

        GroupDTO groupDTO = new GroupDTO();
        groupDTO.getConditions().add(Restrict.eq("name", "项目组"));
        Group group = groupAPI.findOne(groupDTO);
        userDetail.setGroup(group);
        super.save(userDetail);
        return BeanTransform.copyProperties(userDetail, UserDetailBO.class);
    }

    @Override
    public UserDetailBO findByUserId(String userId) throws SerException {
        UserDetailDTO detailDTO = new UserDetailDTO();
        detailDTO.getConditions().add(Restrict.eq("user.id", userId));
        return BeanTransform.copyProperties(detailDTO, UserDetailBO.class);
    }
}
