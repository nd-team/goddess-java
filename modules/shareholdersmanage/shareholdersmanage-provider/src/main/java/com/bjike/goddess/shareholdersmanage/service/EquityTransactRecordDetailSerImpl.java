package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareOpenAccountBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDetailDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecordDetail;
import com.bjike.goddess.shareholdersmanage.entity.ShareOpenAccount;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 股权交易记录详情业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:11 ]
 * @Description: [ 股权交易记录详情业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class EquityTransactRecordDetailSerImpl extends ServiceImpl<EquityTransactRecordDetail, EquityTransactRecordDetailDTO> implements EquityTransactRecordDetailSer {
    @Override
    public Long countTranDetail(EquityTransactRecordDetailDTO equityTransactRecordDetailDTO) throws SerException {
        Long count = super.count(equityTransactRecordDetailDTO);
        return count;
    }

    @Override
    public EquityTransactRecordDetailBO getOne(String id) throws SerException {
        EquityTransactRecordDetail equityTransactRecordDetail = super.findById(id);
        return BeanTransform.copyProperties(equityTransactRecordDetail, EquityTransactRecordDetailBO.class);
    }

    @Override
    public List<EquityTransactRecordDetailBO> findByName(String shareholderName) throws SerException {
        List<EquityTransactRecordDetailBO> equityTransactRecordDetailBOS = new ArrayList<>();
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        List<EquityTransactRecordDetail> equityTransactRecordDetails = super.findByCis(equityTransactRecordDetailDTO);
        equityTransactRecordDetailBOS = BeanTransform.copyProperties(equityTransactRecordDetails, EquityTransactRecordDetailBO.class);
        EquityTransactRecordDetailBO equityTransactRecordDetailBO1 = new EquityTransactRecordDetailBO();
        Double totalAmount = equityTransactRecordDetailBOS.stream().mapToDouble(e->e.getAmount()).sum();
        equityTransactRecordDetailBO1.setTransactDate("合计");
        equityTransactRecordDetailBO1.setAmount(totalAmount);
        equityTransactRecordDetailBOS.add(equityTransactRecordDetailBO1);
        return equityTransactRecordDetailBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(EquityTransactRecordDetailTO equityTransactRecordDetailTO) throws SerException {
        EquityTransactRecordDetail equityTransactRecordDetail = BeanTransform.copyProperties(equityTransactRecordDetailTO,EquityTransactRecordDetail.class,true);
        equityTransactRecordDetail.setCreateTime(LocalDateTime.now());
        super.update(equityTransactRecordDetail);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void edit(EquityTransactRecordDetailTO equityTransactRecordDetailTO) throws SerException {
        EquityTransactRecordDetail equityTransactRecordDetail = super.findById(equityTransactRecordDetailTO.getId());
        if (equityTransactRecordDetail == null) {
            throw new SerException("该对象不存在");
        }
        BeanTransform.copyProperties(equityTransactRecordDetailTO, equityTransactRecordDetail, true);
        equityTransactRecordDetail.setModifyTime(LocalDateTime.now());
        super.update(equityTransactRecordDetail);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteByName(String shareholderName) throws SerException {
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTODTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTODTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        List<EquityTransactRecordDetail> equityTransactRecordDetails = super.findByCis(equityTransactRecordDetailDTODTO);
        super.remove(equityTransactRecordDetails);
    }

    @Override
    public List<EquityTransactRecordDetailBO> getByName(String shareholderName) throws SerException {
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        List<EquityTransactRecordDetail> equityTransactRecordDetails = super.findByCis(equityTransactRecordDetailDTO);
        return BeanTransform.copyProperties(equityTransactRecordDetails,EquityTransactRecordDetailBO.class);
    }

    @Override
    public EquityTransactRecordDetailBO getByNameId(String shareholderName, String transactId) throws SerException {
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("transactId",transactId));
        EquityTransactRecordDetail equityTransactRecordDetail = super.findOne(equityTransactRecordDetailDTO);
        return BeanTransform.copyProperties(equityTransactRecordDetail,EquityTransactRecordDetailBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteByTransactId(String transactId) throws SerException {
        EquityTransactRecordDetailDTO equityTransactRecordDetailDTO = new EquityTransactRecordDetailDTO();
        equityTransactRecordDetailDTO.getConditions().add(Restrict.eq("transactId",transactId));
        List<EquityTransactRecordDetail> equityTransactRecordDetails = super.findByCis(equityTransactRecordDetailDTO);
        super.remove(equityTransactRecordDetails);
    }
}