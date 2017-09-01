package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareChangeBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDetailDTO;
import com.bjike.goddess.shareholdersmanage.dto.ShareChangeDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecordDetail;
import com.bjike.goddess.shareholdersmanage.entity.ShareChange;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.ShareChangeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 股东变更业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:37 ]
 * @Description: [ 股东变更业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class ShareChangeSerImpl extends ServiceImpl<ShareChange, ShareChangeDTO> implements ShareChangeSer {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;
    @Override
    public Long countShareChange(ShareChangeDTO shareChangeDTO) throws SerException {
        Long count = super.count(shareChangeDTO);
        return count;
    }

    @Override
    public ShareChangeBO getOne(String id) throws SerException {
        ShareChange shareChange = super.findById(id);
        return BeanTransform.copyProperties(shareChange, ShareChangeBO.class);
    }

    @Override
    public List<ShareChangeBO> findList(ShareChangeDTO shareChangeDTO) throws SerException {
        searchCondi(shareChangeDTO);
        shareChangeDTO.getSorts().add("createTime=desc");
        List<ShareChange> shareChanges = super.findByCis(shareChangeDTO);
        return BeanTransform.copyProperties(shareChanges, ShareChangeBO.class);
    }
    /**
     * 根据条件查询数据
     *
     * @param shareChangeDTO
     * @throws SerException
     */
    public void searchCondi(ShareChangeDTO shareChangeDTO) throws SerException {
        if (StringUtils.isNotBlank(shareChangeDTO.getArea())) {
            shareChangeDTO.getConditions().add(Restrict.eq("area", shareChangeDTO.getArea()));
        }
    }

    @Override
    public ShareChangeBO save(ShareChangeTO shareChangeTO) throws SerException {
        ShareChange shareChange = BeanTransform.copyProperties(shareChangeTO,ShareChange.class,true);
        shareChange.setCreateTime(LocalDateTime.now());
        shareChange = super.save(shareChange);
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(shareChangeTO.getChangeBeforeName());//变更前股东信息
        EquityTransactRecordBO equityTransactRecordBO2 = equityTransactRecordSer.getByName(shareChangeTO.getChangeAfterName());//变更后股东信息
        //添加交易记录明细
        EquityTransactRecordDetailTO equityTransactRecordDetailTO = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTO.setShareholderName(shareChangeTO.getChangeBeforeName());
        equityTransactRecordDetailTO.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTO.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
        equityTransactRecordDetailTO.setHoldNum(-equityTransactRecordBO.getHoldNum());
        equityTransactRecordDetailTO.setAmount(-equityTransactRecordBO.getAmount());
        equityTransactRecordDetailTO.setTransactType("股东变更");
        equityTransactRecordDetailTO.setTransactId(shareChange.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTO);

        EquityTransactRecordDetailTO equityTransactRecordDetailTO2 = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTO2.setShareholderName(shareChangeTO.getChangeAfterName());
        equityTransactRecordDetailTO2.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTO2.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
        equityTransactRecordDetailTO2.setHoldNum(equityTransactRecordBO.getHoldNum());
        equityTransactRecordDetailTO2.setAmount(equityTransactRecordBO.getAmount());
        equityTransactRecordDetailTO2.setTransactType("股东变更");
        equityTransactRecordDetailTO2.setTransactId(shareChange.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTO2);
        //编辑交易信息数据
        EquityTransactRecordTO equityTransactRecordTO2 = new EquityTransactRecordTO();
        equityTransactRecordTO2.setHoldNum(equityTransactRecordBO2.getHoldNum()+equityTransactRecordBO.getHoldNum());
        equityTransactRecordTO2.setAmount(equityTransactRecordBO2.getAmount()+equityTransactRecordBO.getAmount());
        equityTransactRecordTO2.setId(equityTransactRecordBO2.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTO2);

        EquityTransactRecordTO equityTransactRecordTO = new EquityTransactRecordTO();
        equityTransactRecordTO.setHoldNum(0);
        equityTransactRecordTO.setAmount(0d);
        equityTransactRecordTO.setId(equityTransactRecordBO.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTO);
        equityTransactRecordSer.updateTransList();
        return BeanTransform.copyProperties(shareChange,ShareChangeBO.class);
    }

    @Override
    public ShareChangeBO edit(ShareChangeTO shareChangeTO) throws SerException {
        ShareChange shareChange = super.findById(shareChangeTO.getId());
        String changeBeforeName = shareChange.getChangeBeforeName();
        String changeAfterName = shareChange.getChangeAfterName();
        //如果修改了变更前的股东姓名或者修改了变更后的股东姓名
        if(!changeBeforeName.equals(shareChangeTO.getChangeBeforeName()) || !changeAfterName.equals(shareChangeTO.getChangeAfterName())) {
            EquityTransactRecordBO equityTransactRecordBOB = equityTransactRecordSer.getByName(changeBeforeName);//修改之前的变更前股东信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOB = equityTransactRecordDetailSer.getByNameId(changeBeforeName, shareChangeTO.getId());//修改之前的变更前股东信息交易记录明细
            EquityTransactRecordBO equityTransactRecordBOA = equityTransactRecordSer.getByName(changeAfterName);//修改之前的变更后股东信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOA = equityTransactRecordDetailSer.getByNameId(changeAfterName, shareChangeTO.getId());//修改之前的变更后股东信息交易记录明细
            //恢复信息
            equityTransactRecordSer.reinstate(equityTransactRecordBOB, equityTransactRecordDetailBOB);//恢复变更前股东信息
            equityTransactRecordSer.reinstate(equityTransactRecordBOA, equityTransactRecordDetailBOA);//恢复变更后股东信息

            //查询修改过后的变更前股东信息交易记录和变更后股东信息交易记录
            EquityTransactRecordBO equityTransactRecordBOAB = equityTransactRecordSer.getByName(shareChangeTO.getChangeBeforeName());//修改之前的变更前股东信息交易记录
            EquityTransactRecordBO equityTransactRecordBOAA = equityTransactRecordSer.getByName(shareChangeTO.getChangeAfterName());//修改之前的变更后股东信息交易记录

            //修改修改过后的变更前股东信息交易记录明细信息
            EquityTransactRecordDetailTO equityTransactRecordDetailTOB = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOB.setId(equityTransactRecordDetailBOB.getId());
            equityTransactRecordDetailTOB.setShareholderName(shareChangeTO.getChangeBeforeName());
            equityTransactRecordDetailTOB.setHoldNum(-equityTransactRecordBOAB.getHoldNum());
            equityTransactRecordDetailTOB.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOB.setPerSharePrice(equityTransactRecordBOAB.getPerSharePrice());
            equityTransactRecordDetailTOB.setAmount(-equityTransactRecordBOAB.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOB);

            //修改修改过后的变更后股东信息交易记录明细信息
            EquityTransactRecordDetailTO equityTransactRecordDetailTOA = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOA.setId(equityTransactRecordDetailBOA.getId());
            equityTransactRecordDetailTOA.setShareholderName(shareChangeTO.getChangeAfterName());
            equityTransactRecordDetailTOA.setHoldNum(equityTransactRecordBOAB.getHoldNum());
            equityTransactRecordDetailTOA.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOA.setPerSharePrice(equityTransactRecordBOAB.getPerSharePrice());
            equityTransactRecordDetailTOA.setAmount(equityTransactRecordBOAB.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOA);

            //修改修改过后的变更前股东信息交易记录
            EquityTransactRecordTO equityTransactRecordTOAB = new EquityTransactRecordTO();
            equityTransactRecordTOAB.setId(equityTransactRecordBOAB.getId());
            equityTransactRecordTOAB.setHoldNum(0);
            equityTransactRecordTOAB.setAmount(0d);
            equityTransactRecordSer.updateTrans(equityTransactRecordTOAB);

            //修改修改过后的变更后股东信息交易记录
            EquityTransactRecordTO equityTransactRecordTOAA = new EquityTransactRecordTO();
            equityTransactRecordTOAA.setId(equityTransactRecordBOAA.getId());
            equityTransactRecordTOAA.setHoldNum(equityTransactRecordBOAA.getHoldNum() + equityTransactRecordBOAB.getHoldNum());
            equityTransactRecordTOAA.setAmount(equityTransactRecordBOAA.getAmount() + equityTransactRecordBOAB.getAmount());
            equityTransactRecordSer.updateTrans(equityTransactRecordTOAA);

            equityTransactRecordSer.updateTransList();
        }
        //修改股东变更
        LocalDateTime date = shareChange.getCreateTime();
        shareChange = BeanTransform.copyProperties(shareChangeTO,ShareChange.class,true);
        shareChange.setCreateTime(date);
        shareChange.setModifyTime(LocalDateTime.now());
        super.update(shareChange);
        return BeanTransform.copyProperties(shareChange,ShareChangeBO.class);
    }

    @Override
    public void delete(String id) throws SerException {
        ShareChange shareChange = super.findById(id);
        String changeBeforeName = shareChange.getChangeBeforeName();
        String changeAfterName = shareChange.getChangeAfterName();
        //变更前股东数据
        EquityTransactRecordBO equityTransactRecordBOB = equityTransactRecordSer.getByName(changeBeforeName);//变更前股东信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOB = equityTransactRecordDetailSer.getByNameId(changeBeforeName,id);//变更前股东信息交易记录明细
        //变更后股东数据
        EquityTransactRecordBO equityTransactRecordBOA = equityTransactRecordSer.getByName(changeAfterName);//变更后股东信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBOA = equityTransactRecordDetailSer.getByNameId(changeAfterName,id);//变更后股东信息交易记录明细
        //恢复信息
        equityTransactRecordSer.reinstate(equityTransactRecordBOB,equityTransactRecordDetailBOB);
        equityTransactRecordSer.reinstate(equityTransactRecordBOA,equityTransactRecordDetailBOA);
        //删除交易记录明细
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本表本条数据
        super.remove(id);
    }
}