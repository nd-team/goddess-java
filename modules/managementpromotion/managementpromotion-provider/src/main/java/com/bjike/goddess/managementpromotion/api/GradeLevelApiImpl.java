package com.bjike.goddess.managementpromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managementpromotion.bo.GradeLevelBO;
import com.bjike.goddess.managementpromotion.dto.GradeLevelDTO;
import com.bjike.goddess.managementpromotion.service.GradeLevelSer;
import com.bjike.goddess.managementpromotion.to.GradeLevelTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 管理等级定级业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:57 ]
 * @Description: [ 管理等级定级业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("gradeLevelApiImpl")
public class GradeLevelApiImpl implements GradeLevelAPI {
    @Autowired
    private GradeLevelSer gradeLevelSer;

    @Override
    public GradeLevelBO save(GradeLevelTO to) throws SerException {
        return gradeLevelSer.save(to);
    }

    @Override
    public void delete(String id) throws SerException {
        gradeLevelSer.delete(id);
    }

    @Override
    public void edit(GradeLevelTO to) throws SerException {
        gradeLevelSer.edit(to);
    }

    @Override
    public List<GradeLevelBO> find(GradeLevelDTO dto) throws SerException {
        return gradeLevelSer.find(dto);
    }

    @Override
    public GradeLevelBO findByID(String id) throws SerException {
        return gradeLevelSer.findByID(id);
    }

    @Override
    public Long count(GradeLevelDTO dto) throws SerException {
        return gradeLevelSer.count(dto);
    }

    @Override
    public Set<String> allDepartments() throws SerException {
        return gradeLevelSer.allDepartments();
    }


}