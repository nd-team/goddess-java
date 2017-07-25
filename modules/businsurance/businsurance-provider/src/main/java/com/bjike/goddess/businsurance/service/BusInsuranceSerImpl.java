package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.BusInsuranceBO;
import com.bjike.goddess.businsurance.excel.BusInsuranceExcel;
import com.bjike.goddess.businsurance.to.BusInsuranceTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.BusInsuranceDTO;
import com.bjike.goddess.businsurance.entity.BusInsurance;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 商业保险方案业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-21 09:44 ]
 * @Description: [ 商业保险方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class BusInsuranceSerImpl extends ServiceImpl<BusInsurance, BusInsuranceDTO> implements BusInsuranceSer {

    @Override
    public Long countBusInsurance(BusInsuranceDTO busInsuranceDTO) throws SerException {
        if( StringUtils.isNotBlank( busInsuranceDTO.getInsureComapny())){
            busInsuranceDTO.getConditions().add(Restrict.like("insureComapny",busInsuranceDTO.getInsureComapny()));
        }if( StringUtils.isNotBlank( busInsuranceDTO.getInsureCondition())){
            busInsuranceDTO.getConditions().add(Restrict.like("insureCondition",busInsuranceDTO.getInsureCondition()));
        }if( StringUtils.isNotBlank( busInsuranceDTO.getInsureType())){
            busInsuranceDTO.getConditions().add(Restrict.like("insureType",busInsuranceDTO.getInsureType()));
        }
        Long count = super.count(busInsuranceDTO);
        return count;
    }

    @Override
    public List<BusInsuranceBO> listBusInsurance(BusInsuranceDTO busInsuranceDTO) throws SerException {
        if( StringUtils.isNotBlank( busInsuranceDTO.getInsureComapny())){
            busInsuranceDTO.getConditions().add(Restrict.like("insureComapny",busInsuranceDTO.getInsureComapny()));
        }if( StringUtils.isNotBlank( busInsuranceDTO.getInsureCondition())){
            busInsuranceDTO.getConditions().add(Restrict.like("insureCondition",busInsuranceDTO.getInsureCondition()));
        }if( StringUtils.isNotBlank( busInsuranceDTO.getInsureType())){
            busInsuranceDTO.getConditions().add(Restrict.like("insureType",busInsuranceDTO.getInsureType()));
        }
        busInsuranceDTO.getSorts().add("createTime=asc");
        List<BusInsurance> list = super.findByCis(busInsuranceDTO,true);

        return BeanTransform.copyProperties(list, BusInsuranceBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO addBusInsurance(BusInsuranceTO busInsuranceTO) throws SerException {
        BusInsurance busInsurance = BeanTransform.copyProperties(busInsuranceTO,BusInsurance.class,true);
        busInsurance.setCreateTime(LocalDateTime.now());
        super.save( busInsurance );
        return BeanTransform.copyProperties(busInsurance, BusInsuranceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO editBusInsurance(BusInsuranceTO busInsuranceTO) throws SerException {
        BusInsurance busInsurance = BeanTransform.copyProperties(busInsuranceTO,BusInsurance.class,true);
        BusInsurance cusLevel = super.findById( busInsuranceTO.getId() );

        BeanUtils.copyProperties(busInsurance , cusLevel ,"id","createTime");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(busInsurance, BusInsuranceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteBusInsurance(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove( id );
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO editBuyCondition(BusInsuranceTO busInsuranceTO) throws SerException {
        if(StringUtils.isBlank(busInsuranceTO.getId())){
            throw  new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById( busInsuranceTO.getId() );

        cusLevel.setBuyQuired( busInsuranceTO.getBuyQuired());
        cusLevel.setFileRecord( busInsuranceTO.getFileRecord());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO editAdvice(BusInsuranceTO busInsuranceTO) throws SerException {
        if(StringUtils.isBlank(busInsuranceTO.getId())){
            throw  new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById( busInsuranceTO.getId() );


        cusLevel.setWarefareAdvice( busInsuranceTO.getWarefareAdvice());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO editBusAdvice(BusInsuranceTO busInsuranceTO) throws SerException {
        if(StringUtils.isBlank(busInsuranceTO.getId())){
            throw  new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById( busInsuranceTO.getId() );


        cusLevel.setOperaAdvice( busInsuranceTO.getOperaAdvice());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusInsuranceBO audit(BusInsuranceTO busInsuranceTO) throws SerException {
        if(StringUtils.isBlank(busInsuranceTO.getId())){
            throw  new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById( busInsuranceTO.getId() );

        if( StringUtils.isBlank(cusLevel.getWarefareAdvice()) || StringUtils.isBlank( cusLevel.getOperaAdvice())){
            throw new SerException("运营审核意见或福利模块意见还未填写，不能给总经办审核");
        }
        cusLevel.setManageAdvice( busInsuranceTO.getManageAdvice());
        cusLevel.setRemark( busInsuranceTO.getRemark());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }

    @Override
    public BusInsuranceBO getBusInsurance(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        BusInsurance cusLevel = super.findById( id  );
        return BeanTransform.copyProperties(cusLevel, BusInsuranceBO.class);
    }

    @Override
    public List<String> getAllInsureComapny() throws SerException {
        List<BusInsurance> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BusInsurance model : list) {
            String insureComapny = model.getInsureComapny();
            if (StringUtils.isNotBlank(model.getInsureComapny())) {
                set.add(insureComapny);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getAllInsureType() throws SerException {
        List<BusInsurance> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BusInsurance model : list) {
            String insureType = model.getInsureType();
            if (StringUtils.isNotBlank(model.getInsureType())) {
                set.add(insureType);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getAllInsureCondition() throws SerException {
        List<BusInsurance> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BusInsurance model : list) {
            String insureCondition = model.getInsureCondition();
            if (StringUtils.isNotBlank(model.getInsureCondition())) {
                set.add(insureCondition);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public byte[] exportExcel(BusInsuranceDTO busInsuranceDTO) throws SerException {
        if(StringUtils.isNotBlank(busInsuranceDTO.getInsureComapny())){
            busInsuranceDTO.getConditions().add(Restrict.eq("insureComapny",busInsuranceDTO.getInsureComapny()));
        }
        if(StringUtils.isNotBlank(busInsuranceDTO.getInsureType())){
            busInsuranceDTO.getConditions().add(Restrict.eq("insureType",busInsuranceDTO.getInsureType()));
        }
        if(StringUtils.isNotBlank(busInsuranceDTO.getInsureCondition())){
            busInsuranceDTO.getConditions().add(Restrict.eq("insureCondition",busInsuranceDTO.getInsureCondition()));
        }
        List<BusInsurance> busInsurances = super.findByCis(busInsuranceDTO);
        List<BusInsuranceExcel> busInsuranceExcels = new ArrayList<>();
        busInsurances.stream().forEach(str -> {
            BusInsuranceExcel excel = BeanTransform.copyProperties(str,BusInsuranceExcel.class);
            busInsuranceExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(busInsuranceExcels, excel);
        return bytes;
    }

}