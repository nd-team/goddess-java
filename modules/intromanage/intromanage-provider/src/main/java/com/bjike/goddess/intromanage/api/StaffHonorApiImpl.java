package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.StaffHonorBO;
import com.bjike.goddess.intromanage.dto.StaffHonorDTO;
import com.bjike.goddess.intromanage.entity.StaffHonor;
import com.bjike.goddess.intromanage.service.StaffHonorSer;
import com.bjike.goddess.intromanage.to.StaffHonorTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工荣誉业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:34 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffHonorApiImpl")
public class StaffHonorApiImpl implements StaffHonorAPI {

    @Autowired
    private StaffHonorSer staffHonorSer;

    /**
     * 根据id查询员工荣誉
     *
     * @param id 员工荣誉唯一标识
     * @return class StaffHonorBO
     * @throws SerException
     */
    @Override
    public StaffHonorBO findById(String id) throws SerException {
        StaffHonor model = staffHonorSer.findById(id);
        return BeanTransform.copyProperties(model, StaffHonorBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 员工荣誉dto
     * @throws SerException
     */
    @Override
    public Long count(StaffHonorDTO dto) throws SerException {
        return staffHonorSer.count(dto);
    }

    /**
     * 分页查询员工荣誉
     *
     * @return class StaffHonorBO
     * @throws SerException
     */
    @Override
    public List<StaffHonorBO> list(StaffHonorDTO dto) throws SerException {
        return staffHonorSer.list(dto);
    }

    /**
     * 保存员工荣誉
     *
     * @param to 员工荣誉to
     * @return class StaffHonorBO
     * @throws SerException
     */
    @Override
    public StaffHonorBO save(StaffHonorTO to) throws SerException {
        return staffHonorSer.save(to);
    }

    /**
     * 根据id删除员工荣誉
     *
     * @param id 员工荣誉唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        staffHonorSer.remove(id);
    }

    /**
     * 更新员工荣誉
     *
     * @param to 员工荣誉to
     * @throws SerException
     */
    @Override
    public void update(StaffHonorTO to) throws SerException {
        staffHonorSer.update(to);
    }
}