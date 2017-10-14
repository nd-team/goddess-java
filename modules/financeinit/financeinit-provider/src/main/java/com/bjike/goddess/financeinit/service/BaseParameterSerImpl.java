package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.bo.CompanyBasicInfoBO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.financeinit.entity.BaseParameter;
import com.bjike.goddess.financeinit.entity.CompanyBasicInfo;
import com.bjike.goddess.financeinit.to.BaseParameterTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import redis.clients.jedis.BinaryClient;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 基本参数业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:11 ]
 * @Description: [ 基本参数业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class BaseParameterSerImpl extends ServiceImpl<BaseParameter, BaseParameterDTO> implements BaseParameterSer {
    @Autowired
    private CompanyBasicInfoSer companyBasicInfoSer;
    @Override
    public Long countBasicPara(BaseParameterDTO baseParameterDTO) throws SerException {
        seachCondi(baseParameterDTO);
        Long count = super.count(baseParameterDTO);
        return count;
    }

    @Override
    public BaseParameterBO getOneById(String id) throws SerException {
        BaseParameter baseParameter = super.findById(id);
        return BeanTransform.copyProperties(baseParameter,BaseParameterBO.class);
    }

    @Override
    public List<BaseParameterBO> listBasicPara(BaseParameterDTO baseParameterDTO) throws SerException {
        seachCondi(baseParameterDTO);
        List<BaseParameter> baseParameters = super.findByCis(baseParameterDTO);
        return BeanTransform.copyProperties(baseParameters,BaseParameterBO.class);
    }

    public void seachCondi(BaseParameterDTO baseParameterDTO)throws SerException{
        if(StringUtils.isNotBlank(baseParameterDTO.getCompanyName())){
            baseParameterDTO.getConditions().add(Restrict.eq("companyName",baseParameterDTO.getCompanyName()));
        }
    }

    @Override
    public BaseParameterBO addBasicPara(BaseParameterTO baseParameterTO) throws SerException {
        BaseParameter baseParameter = BeanTransform.copyProperties(baseParameterTO,BaseParameterBO.class,true);
        baseParameter.setCreateTime(LocalDateTime.now());
        CompanyBasicInfoBO companyBasicInfoBO = companyBasicInfoSer.findByCompanyName(baseParameterTO.getCompanyName());
        baseParameter.setEin(companyBasicInfoBO.getEin());
        baseParameter.setPhone(companyBasicInfoBO.getPhone());
        baseParameter.setAddress(companyBasicInfoBO.getAddress());
        baseParameter.setBank(companyBasicInfoBO.getBank());
        baseParameter.setAccount(companyBasicInfoBO.getAccount());
        baseParameter.setScaleShape(companyBasicInfoBO.getScaleShape());
        baseParameter.setRemark(companyBasicInfoBO.getRemark());
        super.save(baseParameter);
        return BeanTransform.copyProperties(baseParameter,BaseParameterBO.class);
    }

    @Override
    public BaseParameterBO editBasicPara(BaseParameterTO baseParameterTO) throws SerException {
        BaseParameter baseParameter = super.findById(baseParameterTO.getId());
        LocalDateTime date = baseParameter.getCreateTime();
        baseParameter = BeanTransform.copyProperties(baseParameterTO,BaseParameter.class,true);
        baseParameter.setCreateTime(date);
        baseParameter.setModifyTime(LocalDateTime.now());
        super.update(baseParameter);
        return BeanTransform.copyProperties(baseParameter,BaseParameterBO.class);
    }

    @Override
    public void deleteBasicPara(String id) throws SerException {
        super.remove(id);
    }
}