package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.salarymanage.bo.SalaryBasicBO;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryBasicDTO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.entity.SalaryInformation;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.SalaryBasicTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
* 薪资管理业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 01:45 ]
* @Description:	[ 薪资管理业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="salarymanageSerCache")
@Service
public class SalaryInformationSerImpl extends ServiceImpl<SalaryInformation, SalaryInformationDTO> implements SalaryInformationSer {
    @Override
    public List<SalaryInformationBO> pageList(SalaryInformationDTO dto) throws SerException {
//        if(dto.getPayStarTime() != null && dto.getPayEndTime() != null){
//            LocalDate[] localDates = new LocalDate[]{dto.getPayStarTime(),dto.getPayEndTime()};
//            dto.getConditions().add(Restrict.between("payStarTime",localDates));
//        }
//        if(dto.getPayStarTime() != null && dto.getPayEndTime() == null){
//
//        }
        return null;
    }

    @Override
    public SalaryInformationBO addSalaryInformation(SalaryInformationTO to) throws SerException {

        return null;
    }

    @Override
    public SalaryInformationBO editSalaryInformation(SalaryInformationTO to) throws SerException {
        return null;
    }

    @Override
    public void deleteSalaryInformation(String id) throws SerException {

    }

    @Override
    public void leadExcel(List<SalaryInformationTO> toList) throws SerException {

    }

    @Override
    public byte[] exportExcel(ExportSalaryInformationTO to) throws SerException {
        return new byte[0];
    }
}