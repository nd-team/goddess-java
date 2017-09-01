package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityGiftBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityInheritanceBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityGiftDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityGift;
import com.bjike.goddess.shareholdersmanage.to.EquityGiftTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 股权赠与业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:08 ]
 * @Description: [ 股权赠与业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class EquityGiftSerImpl extends ServiceImpl<EquityGift, EquityGiftDTO> implements EquityGiftSer {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;
    @Override
    public Long countGift(EquityGiftDTO equityGiftDTO) throws SerException {
        Long count = super.count(equityGiftDTO);
        return count;
    }

    @Override
    public EquityGiftBO getOne(String id) throws SerException {
        EquityGift equityGift = super.findById(id);
        return BeanTransform.copyProperties(equityGift, EquityGiftBO.class);
    }

    @Override
    public List<EquityGiftBO> findList(EquityGiftDTO equityGiftDTO) throws SerException {
        searchCondi(equityGiftDTO);
        equityGiftDTO.getSorts().add("createTime=desc");
        List<EquityGift> equityGifts = super.findByCis(equityGiftDTO);
        return BeanTransform.copyProperties(equityGifts, EquityGiftBO.class);
    }
    /**
     * 根据条件查询数据
     *
     * @param equityGiftDTO
     * @throws SerException
     */
    public void searchCondi(EquityGiftDTO equityGiftDTO) throws SerException {
        if (StringUtils.isNotBlank(equityGiftDTO.getArea())) {
            equityGiftDTO.getConditions().add(Restrict.eq("area", equityGiftDTO.getArea()));
        }
    }

    @Override
    public EquityGiftBO save(EquityGiftTO equityGiftTO) throws SerException {
        //添加本条数据
        EquityGift equityGift = BeanTransform.copyProperties(equityGiftTO,EquityGift.class,true);
        equityGift.setCreateTime(LocalDateTime.now());
        equityGift = super.save(equityGift);
        EquityTransactRecordBO equityTransactRecordBOR = equityTransactRecordSer.getByName(equityGiftTO.getDonor());//赠与人交易记录信息
        EquityTransactRecordBO equityTransactRecordBOE = equityTransactRecordSer.getByName(equityGiftTO.getDonee());//受赠人交易记录信息
        //添加赠与人交易记录明细数据
        EquityTransactRecordDetailTO equityTransactRecordDetailTOR = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOR.setShareholderName(equityGiftTO.getDonor());
        equityTransactRecordDetailTOR.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOR.setPerSharePrice(equityTransactRecordBOR.getPerSharePrice());
        equityTransactRecordDetailTOR.setHoldNum(-equityTransactRecordBOR.getHoldNum());
        equityTransactRecordDetailTOR.setAmount(-equityTransactRecordBOR.getAmount());
        equityTransactRecordDetailTOR.setTransactType("股权赠与");
        equityTransactRecordDetailTOR.setTransactId(equityGift.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOR);

        //添加受赠人交易记录明细数据
        EquityTransactRecordDetailTO equityTransactRecordDetailTOE = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTOE.setShareholderName(equityGiftTO.getDonee());
        equityTransactRecordDetailTOE.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTOE.setPerSharePrice(equityTransactRecordBOR.getPerSharePrice());
        equityTransactRecordDetailTOE.setHoldNum(equityTransactRecordBOR.getHoldNum());
        equityTransactRecordDetailTOE.setAmount(equityTransactRecordBOR.getAmount());
        equityTransactRecordDetailTOE.setTransactType("股权赠与");
        equityTransactRecordDetailTOE.setTransactId(equityGift.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTOE);

        //修改受赠人交易记录数据
        EquityTransactRecordTO equityTransactRecordTOE = new EquityTransactRecordTO();
        equityTransactRecordTOE.setHoldNum(equityTransactRecordBOE.getHoldNum()+equityTransactRecordBOR.getHoldNum());
        equityTransactRecordTOE.setAmount(equityTransactRecordBOE.getAmount()+equityTransactRecordBOR.getAmount());
        equityTransactRecordTOE.setId(equityTransactRecordBOE.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOE);
        //修改赠与人交易记录数据
        EquityTransactRecordTO equityTransactRecordTOR = new EquityTransactRecordTO();
        equityTransactRecordTOR.setHoldNum(0);
        equityTransactRecordTOR.setAmount(0d);
        equityTransactRecordTOR.setId(equityTransactRecordBOR.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTOR);

        equityTransactRecordSer.updateTransList();
        return BeanTransform.copyProperties(equityGift,EquityGiftBO.class);
    }

    @Override
    public EquityGiftBO edit(EquityGiftTO equityGiftTO) throws SerException {
        return null;
    }

    @Override
    public void delete(String id) throws SerException {

    }
}