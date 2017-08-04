package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.salaryconfirm.api.SalaryconfirmAPI;
import com.bjike.goddess.salaryconfirm.bo.SalaryconfirmBO;
import com.bjike.goddess.salaryconfirm.dto.SalaryconfirmDTO;
import com.bjike.goddess.salarymanage.api.SalaryInformationAPI;
import com.bjike.goddess.salarymanage.bo.SalaryBasicBO;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryBasicDTO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.entity.SalaryInformation;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.SalaryBasicTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private UserAPI userAPI;
    @Override
    public List<SalaryInformationBO> pageList(SalaryInformationDTO dto) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        if(dto.getPayStarTime() != null && dto.getPayEndTime() != null){
            LocalDate[] localDates = new LocalDate[]{dto.getPayStarTime(),dto.getPayEndTime()};
            dto.getConditions().add(Restrict.between("payStarTime",localDates));
            dto.getConditions().add(Restrict.between("payEndTime",localDates));
        }
        if(dto.getPayStarTime() != null && dto.getPayEndTime() == null){
            LocalDate endTime = LocalDate.now();
            LocalDate[] localDates = new LocalDate[]{dto.getPayStarTime(),endTime};
            dto.getConditions().add(Restrict.between("payStarTime",localDates));
            dto.getConditions().add(Restrict.between("payEndTime",localDates));
        }
        if(dto.getPayStarTime() == null && dto.getPayEndTime() != null){
            dto.getConditions().add(Restrict.lt_eq("payEndTime",dto.getPayEndTime()));
        }
        dto.getConditions().add(Restrict.eq("employeeId",userBO.getEmployeeNumber()));
        List<SalaryInformation> list = super.findByCis(dto);
        List<SalaryInformationBO> salaryInformationBOS = BeanTransform.copyProperties(list,SalaryInformationBO.class);
        return salaryInformationBOS;
    }

    @Override
    public SalaryInformationBO addSalaryInformation(SalaryInformationTO to) throws SerException {
        SalaryInformation salaryInformation = BeanTransform.copyProperties(to,SalaryInformation.class);
        salaryInformation.setCreateTime(LocalDateTime.now());
        super.save(salaryInformation);
        SalaryInformationBO salaryInformationBO = BeanTransform.copyProperties(salaryInformation,SalaryInformationBO.class);
        return salaryInformationBO;
    }

    @Override
    public SalaryInformationBO editSalaryInformation(SalaryInformationTO to) throws SerException {
        SalaryInformation salaryInformation = super.findById(to.getId());
        try {
            DateUtil.parseDate(to.getPayStarTime());
            DateUtil.parseDate(to.getPayEndTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对");
        }
        Sa
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

    @Override
    public List<String> findTime() throws SerException {
        SalaryInformationDTO dto = new SalaryInformationDTO();
        List<SalaryInformationBO> list = this.pageList(dto);
        List<String> timeList = new ArrayList<>();
        for(SalaryInformationBO salaryInformationBO : list){
            timeList.add(salaryInformationBO.getPayStarTime());
            timeList.add(salaryInformationBO.getPayEndTime());
        }
        return timeList;
    }
}