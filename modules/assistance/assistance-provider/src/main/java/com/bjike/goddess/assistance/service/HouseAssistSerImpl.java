package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.HouseAssistBO;
import com.bjike.goddess.assistance.dto.HouseAssistDTO;
import com.bjike.goddess.assistance.entity.HouseAssist;
import com.bjike.goddess.assistance.to.HouseAssistTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 住宿补助业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 11:27 ]
 * @Description: [ 住宿补助业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class HouseAssistSerImpl extends ServiceImpl<HouseAssist, HouseAssistDTO> implements HouseAssistSer {

    @Override
    public Long countHouseAssist(HouseAssistDTO houseAssistDTO) throws SerException {
        if (StringUtils.isNotBlank(houseAssistDTO.getArea())) {
            houseAssistDTO.getConditions().add(Restrict.eq("area", houseAssistDTO.getArea()));
        }
        if (StringUtils.isNotBlank(houseAssistDTO.getProjectGroup())) {
            houseAssistDTO.getConditions().add(Restrict.eq("projectGroup", houseAssistDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(houseAssistDTO.getEmpName())) {
            houseAssistDTO.getConditions().add(Restrict.eq("empName", houseAssistDTO.getEmpName()));
        }
        houseAssistDTO.getSorts().add("createTime=desc");
        Long count = super.count(houseAssistDTO);
        return count;
    }

    @Override
    public List<HouseAssistBO> listHouseAssist(HouseAssistDTO houseAssistDTO) throws SerException {
        if (StringUtils.isNotBlank(houseAssistDTO.getArea())) {
            houseAssistDTO.getConditions().add(Restrict.eq("area", houseAssistDTO.getArea()));
        }
        if (StringUtils.isNotBlank(houseAssistDTO.getProjectGroup())) {
            houseAssistDTO.getConditions().add(Restrict.eq("projectGroup", houseAssistDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(houseAssistDTO.getEmpName())) {
            houseAssistDTO.getConditions().add(Restrict.eq("empName", houseAssistDTO.getEmpName()));
        }
        houseAssistDTO.getSorts().add("createTime=desc");
        List<HouseAssist> list = super.findByCis(houseAssistDTO, true);

        return BeanTransform.copyProperties(list, HouseAssistBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HouseAssistBO addHouseAssist(HouseAssistTO houseAssistTO) throws SerException {
        if( houseAssistTO.getOutDays()==null || houseAssistTO.getOutDays().isNaN()){
            throw new SerException("外宿天数不能为空，且只能是数字");
        }

        HouseAssist houseAssist = BeanTransform.copyProperties(houseAssistTO, HouseAssist.class, true);

        //住宿补助额度   200×（外术天数/计薪周期(开始/结束相减总天数）
        if(StringUtils.isNotBlank(houseAssistTO.getSalaryStartTime())
                || StringUtils.isNotBlank(houseAssistTO.getSalaryEndTime())){
            throw  new SerException("计薪开始时间和结束时间不能为空");
        }
        //设置钱
        Long day = houseAssist.getSalaryEndTime().toEpochDay()-houseAssist.getSalaryStartTime().toEpochDay();
        Double money = houseAssistTO.getOutDays()*(houseAssistTO.getOutDays()/day);
        houseAssist.setMoney( money );

        houseAssist.setCreateTime(LocalDateTime.now());
        super.save(houseAssist);
        return BeanTransform.copyProperties(houseAssist, HouseAssistBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public HouseAssistBO editHouseAssist(HouseAssistTO houseAssistTO) throws SerException {
        if (StringUtils.isBlank(houseAssistTO.getId())) {
            throw new SerException("id不能为空");
        }
        if( houseAssistTO.getOutDays()==null || houseAssistTO.getOutDays().isNaN()){
            throw new SerException("外宿天数不能为空，且只能是数字");
        }

        HouseAssist houseAssist = BeanTransform.copyProperties(houseAssistTO, HouseAssist.class, true);
        HouseAssist rs = super.findById(houseAssistTO.getId());

        //住宿补助额度   200×（外术天数/计薪周期(开始/结束相减总天数）
        if(StringUtils.isNotBlank(houseAssistTO.getSalaryStartTime())
                || StringUtils.isNotBlank(houseAssistTO.getSalaryEndTime())){
            throw  new SerException("计薪开始时间和结束时间不能为空");
        }
        //设置钱
        Long day = houseAssist.getSalaryEndTime().toEpochDay()-houseAssist.getSalaryStartTime().toEpochDay();
        Double money = houseAssistTO.getOutDays()*(houseAssistTO.getOutDays()/day);
        rs.setMoney( money );

        rs.setEmpName(houseAssist.getEmpName());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, HouseAssistBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteHouseAssist(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }



    
}