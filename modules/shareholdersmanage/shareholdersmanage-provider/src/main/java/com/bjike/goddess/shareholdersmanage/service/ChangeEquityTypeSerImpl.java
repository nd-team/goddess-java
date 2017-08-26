package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.ChangeEquityTypeBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.dto.ChangeEquityTypeDTO;
import com.bjike.goddess.shareholdersmanage.entity.ChangeEquityType;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecordDetail;
import com.bjike.goddess.shareholdersmanage.to.ChangeEquityTypeTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 变更股权类型业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:42 ]
 * @Description: [ 变更股权类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class ChangeEquityTypeSerImpl extends ServiceImpl<ChangeEquityType, ChangeEquityTypeDTO> implements ChangeEquityTypeSer {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;

    @Override
    public Long countChangeType(ChangeEquityTypeDTO changeEquityTypeDTO) throws SerException {
        Long count = super.count(changeEquityTypeDTO);
        return count;
    }

    @Override
    public ChangeEquityTypeBO getOne(String id) throws SerException {
        ChangeEquityType changeEquityType = super.findById(id);
        return BeanTransform.copyProperties(changeEquityType, ChangeEquityTypeBO.class);
    }

    @Override
    public List<ChangeEquityTypeBO> findList(ChangeEquityTypeDTO changeEquityTypeDTO) throws SerException {
        changeEquityTypeDTO.getSorts().add("createTime=desc");
        List<ChangeEquityType> changeEquityTypes = super.findByCis(changeEquityTypeDTO);
        return BeanTransform.copyProperties(changeEquityTypes, ChangeEquityTypeBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ChangeEquityTypeBO save(ChangeEquityTypeTO changeEquityTypeTO) throws SerException {
        //添加本条数据
        ChangeEquityType changeEquityType = BeanTransform.copyProperties(changeEquityTypeTO,ChangeEquityType.class,true);
        changeEquityType.setCreateTime(LocalDateTime.now());
        changeEquityType = super.save(changeEquityType);

        List<EquityTransactRecordBO> equityTransactRecordBOS = equityTransactRecordSer.getByEquityType(changeEquityTypeTO.getChangeBeforeType());
        if(equityTransactRecordBOS!=null && equityTransactRecordBOS.size()>0){
            List<EquityTransactRecord> list = new ArrayList<>();
            List<EquityTransactRecordDetail> detailList = new ArrayList<>();
            for (EquityTransactRecordBO equityTransactRecordBO : equityTransactRecordBOS){
                //添加交易记录明细记录
                EquityTransactRecordDetail equityTransactRecordDetail = new EquityTransactRecordDetail();
                equityTransactRecordDetail.setShareholderName(equityTransactRecordBO.getShareholderName());
                equityTransactRecordDetail.setTransactDate(LocalDate.now());
                equityTransactRecordDetail.setHoldNum(0);
                equityTransactRecordDetail.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
                equityTransactRecordDetail.setAmount(0d);
                equityTransactRecordDetail.setTransactType("变更股权类型");
                equityTransactRecordDetail.setTransactId(changeEquityType.getId());
                detailList.add(equityTransactRecordDetail);
                //修改交易记录的股权类型
                EquityTransactRecord equityTransactRecord = new EquityTransactRecord();
                equityTransactRecord.setEquityType(changeEquityTypeTO.getChangeAfterType());
                equityTransactRecord.setId(equityTransactRecordBO.getId());
                list.add(equityTransactRecord);
            }
            equityTransactRecordDetailSer.save(detailList);
            equityTransactRecordSer.update(list);
            //重新设置所有股东的占股比例
            equityTransactRecordSer.updateTransList();
        }
        return BeanTransform.copyProperties(changeEquityType,ChangeEquityTypeBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ChangeEquityTypeBO edit(ChangeEquityTypeTO changeEquityTypeTO) throws SerException {
        ChangeEquityType changeEquityType = super.findById(changeEquityTypeTO.getId());
        String changeBeforeType = changeEquityType.getChangeBeforeType(); //修改之前的变更前股权类型
        String changeAfterType = changeEquityType.getChangeAfterType(); //修改之前的变更后股权类型
        //判断变更前股权类型和变更后股权类型是否被修改
        if(!changeBeforeType.equals(changeEquityTypeTO.getChangeBeforeType()) || !changeAfterType.equals(changeEquityTypeTO.getChangeAfterType())) {
            //恢复之前数据
            reinstateType(changeEquityTypeTO.getId(),changeBeforeType);
            equityTransactRecordDetailSer.deleteByTransactId(changeEquityTypeTO.getId());
            //重新进行修改交易记录数据和添加交易记录明细数据
            List<EquityTransactRecordBO> equityTransactRecordBOS = equityTransactRecordSer.getByEquityType(changeEquityTypeTO.getChangeBeforeType());
            if (equityTransactRecordBOS != null && equityTransactRecordBOS.size() > 0) {
                List<EquityTransactRecord> list = new ArrayList<>();
                List<EquityTransactRecordDetail> detailList = new ArrayList<>();
                for (EquityTransactRecordBO equityTransactRecordBO : equityTransactRecordBOS) {
                    //添加交易记录明细记录
                    EquityTransactRecordDetail equityTransactRecordDetail = new EquityTransactRecordDetail();
                    equityTransactRecordDetail.setShareholderName(equityTransactRecordBO.getShareholderName());
                    equityTransactRecordDetail.setTransactDate(LocalDate.now());
                    equityTransactRecordDetail.setHoldNum(0);
                    equityTransactRecordDetail.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
                    equityTransactRecordDetail.setAmount(0d);
                    equityTransactRecordDetail.setTransactType("变更股权类型");
                    equityTransactRecordDetail.setTransactId(changeEquityType.getId());
                    detailList.add(equityTransactRecordDetail);
                    //修改交易记录的股权类型
                    EquityTransactRecord equityTransactRecord = new EquityTransactRecord();
                    equityTransactRecord.setEquityType(changeEquityTypeTO.getChangeAfterType());
                    equityTransactRecord.setId(equityTransactRecordBO.getId());
                    list.add(equityTransactRecord);
                }
                equityTransactRecordDetailSer.save(detailList);
                equityTransactRecordSer.update(list);
                //重新设置所有股东的占股比例
                equityTransactRecordSer.updateTransList();
            }
        }
        LocalDateTime date = changeEquityType.getCreateTime();
        changeEquityType = BeanTransform.copyProperties(changeEquityTypeTO,ChangeEquityType.class,true);
        changeEquityType.setCreateTime(date);
        changeEquityType.setModifyTime(LocalDateTime.now());
        super.update(changeEquityType);
        return BeanTransform.copyProperties(changeEquityType,ChangeEquityTypeBO.class);
    }

    /**
     * 交易记录表恢复所有的类型
     */
    public void reinstateType(String id,String changeBeforeType)throws SerException{
        List<EquityTransactRecordDetailBO> equityTransactRecordDetailBOS = equityTransactRecordDetailSer.getByName(id);
        if (equityTransactRecordDetailBOS != null ){
            List<EquityTransactRecord> list = new ArrayList<>();
            for (EquityTransactRecordDetailBO detailBO : equityTransactRecordDetailBOS){
                EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(detailBO.getShareholderName());
                EquityTransactRecord equityTransactRecord = new EquityTransactRecord();
                equityTransactRecord.setEquityType(changeBeforeType);
                equityTransactRecord.setId(equityTransactRecordBO.getId());
                list.add(equityTransactRecord);
            }
            equityTransactRecordSer.update(list);
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        ChangeEquityType changeEquityType = super.findById(id);
        //恢复所有数据
        reinstateType(id,changeEquityType.getChangeBeforeType());
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本条记录
        super.remove(id);
    }

}