package com.bjike.goddess.staffwelfaremanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.bo.StaffBirthDayWelfareBO;
import com.bjike.goddess.staffwelfaremanage.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfaremanage.entity.StaffBirthDayWelfare;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工生日福利记录业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 10:49 ]
 * @Description: [ 员工生日福利记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class StaffBirthDayWelfareSerImpl extends ServiceImpl<StaffBirthDayWelfare, StaffBirthDayWelfareDTO> implements StaffBirthDayWelfareSer {

    @Override
    public List<StaffBirthDayWelfareBO> pageList(StaffBirthDayWelfareDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<StaffBirthDayWelfare> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list,StaffBirthDayWelfareBO.class);
    }
}