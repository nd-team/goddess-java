package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.entity.StaffRecords;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工档案业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class StaffRecordsSerImpl extends ServiceImpl<StaffRecords, StaffRecordsDTO> implements StaffRecordsSer {

    @Override
    public void upload(List<StaffRecordsExcelTO> toList) throws SerException {
        for (int i = 1; i <= toList.size(); i++) {
            this.isExist(toList.get(i - 1), i);
        }
        List<StaffRecords> list = BeanTransform.copyProperties(toList, StaffRecords.class, true);
        super.save(list);
    }

    private void isExist(StaffRecordsExcelTO to, Integer row) throws SerException {
        if (this.findByName(to.getUsername()) != null)
            throw new SerException(String.format("第%d行的姓名已存在", row));
        if (this.findByNumber(to.getSerialNumber()) != null)
            throw new SerException(String.format("第%d行的员工编号已存在", row));

    }

    @Override
    public StaffRecordsBO findByName(String username) throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        dto.getConditions().add(Restrict.eq("username", username));
        StaffRecords entity = super.findOne(dto);
        if (null == entity)
            return null;
        else
            return BeanTransform.copyProperties(super.findOne(dto), StaffRecordsBO.class);
    }

    @Override
    public StaffRecordsBO findByNumber(String serialNumber) throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        dto.getConditions().add(Restrict.eq("serialNumber", serialNumber));
        StaffRecords entity = super.findOne(dto);
        if (null == entity)
            return null;
        else
            return BeanTransform.copyProperties(entity, StaffRecordsBO.class);
    }

    @Override
    public List<StaffRecordsBO> maps(StaffRecordsDTO dto) throws SerException {
        dto.getSorts().add("serialNumber=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), StaffRecordsBO.class);
    }

    @Override
    public StaffRecordsBO getById(String id) throws SerException {
        StaffRecords entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, StaffRecordsBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        StaffRecordsDTO dto = new StaffRecordsDTO();
        return super.count(dto);
    }
}