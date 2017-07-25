package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.InsureRecordBO;
import com.bjike.goddess.businsurance.dto.InsureRecordDTO;
import com.bjike.goddess.businsurance.entity.InsureRecord;
import com.bjike.goddess.businsurance.excel.InsureRecordExcel;
import com.bjike.goddess.businsurance.to.InsureRecordTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.InsureRecordDTO;
import com.bjike.goddess.businsurance.entity.InsureRecord;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 意外险记录业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 05:43 ]
 * @Description: [ 意外险记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class InsureRecordSerImpl extends ServiceImpl<InsureRecord, InsureRecordDTO> implements InsureRecordSer {


    @Override
    public Long countInsureRecord(InsureRecordDTO insureRecordDTO) throws SerException {
        Long count = super.count(insureRecordDTO);
        return count;
    }

    @Override
    public List<InsureRecordBO> listInsureRecord(InsureRecordDTO insureRecordDTO) throws SerException {
        insureRecordDTO.getSorts().add("createTime=asc");
        List<InsureRecord> list = super.findByCis(insureRecordDTO,true);

        return BeanTransform.copyProperties(list, InsureRecordBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InsureRecordBO addInsureRecord(InsureRecordTO insureRecordTO) throws SerException {
        InsureRecord insureRecord = BeanTransform.copyProperties(insureRecordTO,InsureRecord.class,true);
        insureRecord.setCreateTime(LocalDateTime.now());
        super.save( insureRecord );
        return BeanTransform.copyProperties(insureRecord, InsureRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InsureRecordBO editInsureRecord(InsureRecordTO insureRecordTO) throws SerException {
        InsureRecord insureRecord = BeanTransform.copyProperties(insureRecordTO,InsureRecord.class,true);
        InsureRecord cusLevel = super.findById( insureRecordTO.getId() );

        BeanUtils.copyProperties(insureRecord , cusLevel ,"id","createTime");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(insureRecord, InsureRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteInsureRecord(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove( id );
    }



    @Override
    public InsureRecordBO getInsureRecord(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        InsureRecord cusLevel = super.findById( id  );
        return BeanTransform.copyProperties(cusLevel, InsureRecordBO.class);
    }

    @Override
    public byte[] exportExcel( ) throws SerException {
        InsureRecordDTO insureRecordDTO = new InsureRecordDTO();
        List<InsureRecord> insureRecords = super.findByCis(insureRecordDTO);
        List<InsureRecordExcel> insureRecordExcels = new ArrayList<>();
        insureRecords.stream().forEach(str -> {
            InsureRecordExcel excel = BeanTransform.copyProperties(str,InsureRecordExcel.class);
            insureRecordExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(insureRecordExcels, excel);
        return bytes;
    }
    
}