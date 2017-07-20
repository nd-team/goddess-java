package com.bjike.goddess.royalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.royalty.bo.DepartmentBetABO;
import com.bjike.goddess.royalty.bo.DepartmentBetBO;
import com.bjike.goddess.royalty.dto.DepartmentBetBDTO;
import com.bjike.goddess.royalty.dto.DepartmentBetDDTO;
import com.bjike.goddess.royalty.dto.DepartmentBetDTO;
import com.bjike.goddess.royalty.entity.DepartmentBet;
import com.bjike.goddess.royalty.service.DepartmentBetSer;
import com.bjike.goddess.royalty.to.DepartmentBetATO;
import com.bjike.goddess.royalty.to.DepartmentBetTO;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.SystemBetATO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门间对赌表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:16 ]
 * @Description: [ 部门间对赌表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("departmentBetApiImpl")
public class DepartmentBetApiImpl implements DepartmentBetAPI {
    @Autowired
    private DepartmentBetSer departmentBetSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return departmentBetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return departmentBetSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long count(DepartmentBetDDTO dto) throws SerException {
        return departmentBetSer.count(dto);
    }

    @Override
    public DepartmentBetABO getOne(String id) throws SerException {
        return departmentBetSer.getOne(id);
    }

    @Override
    public List<DepartmentBetBO> list(DepartmentBetDTO dto) throws SerException {
        return departmentBetSer.list(dto);
    }

    @Override
    public void insert(DepartmentBetATO departmentBetATO) throws SerException {
        departmentBetSer.insert(departmentBetATO);
    }

    @Override
    public void edit(DepartmentBetATO departmentBetATO) throws SerException {
        departmentBetSer.edit(departmentBetATO);
    }

    @Override
    public void delete(String id) throws SerException {
        departmentBetSer.delete(id);
    }
}