package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dispatchcar.bo.CheckChangeCarBO;
import com.bjike.goddess.dispatchcar.dto.CheckChangeCarDTO;
import com.bjike.goddess.dispatchcar.entity.CheckChangeCar;
import com.bjike.goddess.dispatchcar.to.CorrectMistakeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 出车核对修改记录业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-25 11:24 ]
* @Description:	[ 出车核对修改记录业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="dispatchcarSerCache")
@Service
public class CheckChangeCarSerImpl extends ServiceImpl<CheckChangeCar, CheckChangeCarDTO> implements CheckChangeCarSer {


    @Override
    public List<CheckChangeCarBO> list(CheckChangeCarDTO dto) throws SerException {
        if(StringUtils.isNotBlank(dto.getCarUser())){
            dto.getConditions().add(Restrict.eq("carUser",dto.getCarUser()));
        }
        if(StringUtils.isNotBlank(dto.getNumber())){
            dto.getConditions().add(Restrict.eq("number",dto.getNumber()));
        }
        List<CheckChangeCar> checkChangeCars = super.findByCis(dto);
        List<CheckChangeCarBO> boList = BeanTransform.copyProperties(checkChangeCars,CheckChangeCarBO.class);
        return boList;
    }

    @Override
    public void modify(CorrectMistakeTO to) throws SerException {
        CheckChangeCar model = super.findById(to.getId());
        if(to.getIfSolve() != null){
            model.setIfSolve(to.getIfSolve());
        }
        if(StringUtils.isNotBlank(to.getSolution())){
            model.setSolution(to.getSolution());
        }
        if(StringUtils.isNotBlank(to.getSolutionTime())){
            model.setSolutionTime(DateUtil.parseDateTime(to.getSolutionTime()));
        }
        super.update(model);
    }
}