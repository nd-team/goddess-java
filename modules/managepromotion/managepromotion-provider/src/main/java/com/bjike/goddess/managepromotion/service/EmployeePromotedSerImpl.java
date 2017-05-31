package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.EmployeePromotedBO;
import com.bjike.goddess.managepromotion.dto.EmployeePromotedDTO;
import com.bjike.goddess.managepromotion.entity.EmployeePromoted;
import com.bjike.goddess.managepromotion.to.EmployeePromotedTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工已晋升情况业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class EmployeePromotedSerImpl extends ServiceImpl<EmployeePromoted, EmployeePromotedDTO> implements EmployeePromotedSer {

    @Override
    public Long countEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        Long count = super.count(employeePromotedDTO);
        return count;
    }
    @Override
    public EmployeePromotedBO getOne(String id) throws SerException {
        EmployeePromoted employeePromoted = super.findById(id);
        return BeanTransform.copyProperties(employeePromoted,EmployeePromotedBO.class);
    }

    @Override
    public List<EmployeePromotedBO> findListEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        List<EmployeePromoted> employeePromoteds = super.findByPage(employeePromotedDTO);
        List<EmployeePromotedBO> employeePromotedBOS = BeanTransform.copyProperties(employeePromoteds,EmployeePromotedBO.class);
        return employeePromotedBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeePromotedBO insertEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {

        EmployeePromoted employeePromoted = BeanTransform.copyProperties(employeePromotedTO,EmployeePromoted.class,true);
        employeePromoted.setModifyTime(LocalDateTime.now());
        super.save(employeePromoted);
        return BeanTransform.copyProperties(employeePromoted,EmployeePromotedBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EmployeePromotedBO editEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        return null;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeEmployeePromoted(String id) throws SerException {
        super.remove(id);
    }
    @Override
    public List<EmployeePromotedBO> seach(EmployeePromotedDTO  employeePromotedDTO) throws SerException {
        /**
         * 姓名
         */

        if(StringUtils.isNotBlank(employeePromotedDTO.getName())){
            employeePromotedDTO.getConditions().add(Restrict.eq("name",employeePromotedDTO.getName()));
        }
        /**
         * 时间
         */
        if(StringUtils.isNotBlank(employeePromotedDTO.getTimes())){
            employeePromotedDTO.getConditions().add(Restrict.between("times",employeePromotedDTO.getTimes()));
        }
        /**
         * 状态
         */
        if(StringUtils.isNotBlank(employeePromotedDTO.getStatus())){
            employeePromotedDTO.getConditions().add(Restrict.eq("status",employeePromotedDTO.getStatus()));
        }
        List<EmployeePromoted> employeePromoteds = super.findByPage(employeePromotedDTO);
        List<EmployeePromotedBO> employeePromotedBOS = BeanTransform.copyProperties(employeePromoteds,EmployeePromotedBO.class);
        return  employeePromotedBOS;
    }
}