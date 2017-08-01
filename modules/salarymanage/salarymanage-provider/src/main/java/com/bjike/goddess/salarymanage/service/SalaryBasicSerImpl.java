package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.salarymanage.api.SalaryBasicAPI;
import com.bjike.goddess.salarymanage.bo.SalaryBasicBO;
import com.bjike.goddess.salarymanage.dto.SalaryBasicDTO;
import com.bjike.goddess.salarymanage.entity.SalaryBasic;
import com.bjike.goddess.salarymanage.to.SalaryBasicTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* 薪资管理业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 09:50 ]
* @Description:	[ 薪资管理业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="salarymanageSerCache")
@Service
public class SalaryBasicSerImpl extends ServiceImpl<SalaryBasic, SalaryBasicDTO> implements SalaryBasicSer {
    @Autowired
    private SalaryBasicSer salaryBasicSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private HierarchyAPI hierarchyAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;

    @Override
    public List<AreaBO> findArea() throws SerException {
        List<AreaBO> areaBOS = departmentDetailAPI.findArea();
        return areaBOS;
    }

    @Override
    public List<OpinionBO> findThawOpinion() throws SerException {
        List<OpinionBO> opinionBOS = departmentDetailAPI.findThawOpinion();
        return opinionBOS;
    }

    @Override
    public List<HierarchyBO> findStatus() throws SerException {
        List<HierarchyBO> hierarchyBOS = hierarchyAPI.findStatus();
        return hierarchyBOS;
    }

    @Override
    public List<PositionDetailBO> findPosition() throws SerException {
        List<PositionDetailBO> positionDetailBOS = positionDetailAPI.findStatus();
        return positionDetailBOS;
    }

    @Override
    public List<SalaryBasicBO> findSalaryBasic() throws SerException {
        List<SalaryBasic> salaryBasicBOS = super.findAll();
        List<SalaryBasicBO> salaryList = BeanTransform.copyProperties(salaryBasicBOS,SalaryBasicBO.class);
        return salaryList;
    }

    @Override
    public SalaryBasicBO findSalary(SalaryBasicDTO dto) throws SerException {
        if(StringUtils.isNotBlank(dto.getArea())){
            dto.getConditions().add(Restrict.eq("area",dto.getArea()));
        }
        if(StringUtils.isNotBlank(dto.getDepartment())){
            dto.getConditions().add(Restrict.eq("department",dto.getDepartment()));
        }
        if(StringUtils.isNotBlank(dto.getPosition())){
            dto.getConditions().add(Restrict.eq("position",dto.getPosition()));
        }
        if(StringUtils.isNotBlank(dto.getSystem())){
            dto.getConditions().add(Restrict.eq("system",dto.getSystem()));
        }
        SalaryBasic salaryBasic = super.findOne(dto);
        SalaryBasicBO salaryBasicBO = BeanTransform.copyProperties(salaryBasic,SalaryBasicBO.class);
        return salaryBasicBO;
    }

    @Override
    public SalaryBasicBO add(SalaryBasicTO to) throws SerException {
        SalaryBasic salaryBasic = BeanTransform.copyProperties(to,SalaryBasic.class);
        salaryBasic.setCreateTime(LocalDateTime.now());
        super.save(salaryBasic);
        SalaryBasicBO salaryBasicBO = BeanTransform.copyProperties(salaryBasic,SalaryBasicBO.class);
        return salaryBasicBO;
    }

    @Override
    public SalaryBasicBO edit(SalaryBasicTO to) throws SerException {
        SalaryBasic salaryBasic = super.findById(to.getId());
        salaryBasic.setArea(to.getArea());
        salaryBasic.setBasePay(to.getBasePay());
        salaryBasic.setDepartment(to.getDepartment());
        salaryBasic.setSystem(to.getSystem());
        salaryBasic.setPosition(to.getPosition());
        salaryBasic.setModifyTime(LocalDateTime.now());
        SalaryBasicBO salaryBasicBO = BeanTransform.copyProperties(salaryBasic,SalaryBasicBO.class);
        return salaryBasicBO;
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }
}