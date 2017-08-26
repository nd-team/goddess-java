package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.NewEquityBO;
import com.bjike.goddess.shareholdersmanage.bo.NewEquityLinkDateBO;
import com.bjike.goddess.shareholdersmanage.dto.NewEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.NewEquity;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.NewEquityTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 新增股权业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:17 ]
 * @Description: [ 新增股权业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class NewEquitySerImpl extends ServiceImpl<NewEquity, NewEquityDTO> implements NewEquitySer {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;
    @Override
    public Long countNewEquity(NewEquityDTO newEquityDTO) throws SerException {
        Long count = super.count(newEquityDTO);
        return count;
    }

    @Override
    public NewEquityBO getOne(String id) throws SerException {
        NewEquity newEquity = super.findById(id);
        return BeanTransform.copyProperties(newEquity, NewEquityBO.class);
    }

    @Override
    public List<NewEquityBO> findList(NewEquityDTO newEquityDTO) throws SerException {
        searchCondi(newEquityDTO);
        newEquityDTO.getSorts().add("createTime=desc");
        List<NewEquity> newEquities = super.findByCis(newEquityDTO);
        return BeanTransform.copyProperties(newEquities, NewEquityBO.class);
    }
    /**
     * 根据条件查询数据
     *
     * @param newEquityDTO
     * @throws SerException
     */
    public void searchCondi(NewEquityDTO newEquityDTO) throws SerException {
        if (StringUtils.isNotBlank(newEquityDTO.getArea())) {
            newEquityDTO.getConditions().add(Restrict.eq("area", newEquityDTO.getArea()));
        }
    }

    @Override
    public NewEquityBO save(NewEquityTO newEquityTO) throws SerException {
        //添加新增股权记录
        NewEquity newEquity = BeanTransform.copyProperties(newEquityTO,NewEquity.class,true);
        newEquity.setCreateTime(LocalDateTime.now());
        newEquity = super.save(newEquity);
        //编辑交易记录数据
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(newEquityTO.getShareholderName());
        EquityTransactRecordTO equityTransactRecordTO = new EquityTransactRecordTO();
        equityTransactRecordTO.setHoldNum(equityTransactRecordBO.getHoldNum()+newEquityTO.getNewHoldNum());
        equityTransactRecordTO.setAmount(equityTransactRecordBO.getAmount()+newEquityTO.getAmount());
        equityTransactRecordTO.setId(equityTransactRecordBO.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTO);
        //重新设置所有股东的股权
        equityTransactRecordSer.updateTransList();
        //添加交易记录明细
        EquityTransactRecordDetailTO equityTransactRecordDetailTO = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTO.setShareholderName(newEquityTO.getShareholderName());
        equityTransactRecordDetailTO.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTO.setPerSharePrice(newEquityTO.getPerSharePrice());
        equityTransactRecordDetailTO.setHoldNum(newEquityTO.getNewHoldNum());
        equityTransactRecordDetailTO.setAmount(newEquityTO.getAmount());
        equityTransactRecordDetailTO.setTransactType("新增股权");
        equityTransactRecordDetailTO.setTransactId(newEquity.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTO);
        return BeanTransform.copyProperties(newEquity,NewEquityBO.class);
    }

    @Override
    public NewEquityBO edit(NewEquityTO newEquityTO) throws SerException {
        //判断股东名称是否被修改
        NewEquity newEquity = super.findById(newEquityTO.getId());
        String shareholderName = newEquity.getShareholderName();
        if(!shareholderName.equals(newEquityTO.getShareholderName())){
            EquityTransactRecordDetailBO equityTransactRecordDetailBOB = equityTransactRecordDetailSer.getByNameId(shareholderName,newEquityTO.getId());//修改之前的变更前股东信息交易记录明细
            EquityTransactRecordBO equityTransactRecordBOB = equityTransactRecordSer.getByName(shareholderName);//修改之前的变更前股东信息交易记录
            //恢复交易记录修改前股东的数据
            equityTransactRecordSer.reinstate(equityTransactRecordBOB,equityTransactRecordDetailBOB);
            //修改交易记录修改后的股东数据
            EquityTransactRecordBO equityTransactRecordBOA = equityTransactRecordSer.getByName(newEquityTO.getShareholderName());
            EquityTransactRecordTO equityTransactRecordTO1 = new EquityTransactRecordTO();
            equityTransactRecordTO1.setHoldNum(equityTransactRecordBOA.getHoldNum()+newEquityTO.getNewHoldNum());
            equityTransactRecordTO1.setAmount(equityTransactRecordBOA.getAmount()+newEquityTO.getAmount());
            equityTransactRecordTO1.setId(equityTransactRecordBOA.getId());
            equityTransactRecordSer.updateTrans(equityTransactRecordTO1);
            //重新设置所有股东的股权
            equityTransactRecordSer.updateTransList();
            //编辑交易记录明细数据
            EquityTransactRecordDetailTO equityTransactRecordDetailTO = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTO.setId(equityTransactRecordDetailBOB.getId());
            equityTransactRecordDetailTO.setShareholderName(newEquityTO.getShareholderName());
            equityTransactRecordDetailTO.setHoldNum(newEquityTO.getNewHoldNum());
            equityTransactRecordDetailTO.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTO.setPerSharePrice(newEquityTO.getPerSharePrice());
            equityTransactRecordDetailTO.setAmount(newEquityTO.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTO);
        }
        //编辑本条数据
        LocalDateTime date = newEquity.getCreateTime();
        newEquity = BeanTransform.copyProperties(newEquityTO,NewEquity.class,true);
        newEquity.setCreateTime(date);
        newEquity.setModifyTime(LocalDateTime.now());
        super.update(newEquity);
        return BeanTransform.copyProperties(newEquity,NewEquityBO.class);
    }

    @Override
    public void delete(String id) throws SerException {
        //恢复交易记录数据
        NewEquity newEquity = super.findById(id);
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(newEquity.getShareholderName());//变更前股东信息交易记录
        EquityTransactRecordDetailBO equityTransactRecordDetailBO = equityTransactRecordDetailSer.getByNameId(newEquity.getShareholderName(),id);//变更前股东信息交易记录明细
        equityTransactRecordSer.reinstate(equityTransactRecordBO,equityTransactRecordDetailBO);
        //删除交易记录明细信息
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本条数据
        super.remove(id);
    }

    @Override
    public NewEquityLinkDateBO newEqLinkDate(Integer newHoldNum,String shareholderName) throws SerException {
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(shareholderName);
        NewEquityLinkDateBO newEquityLinkDateBO = new NewEquityLinkDateBO();
        newEquityLinkDateBO.setShareholderName(equityTransactRecordBO.getShareholderName());
        newEquityLinkDateBO.setEquityType(equityTransactRecordBO.getEquityType());
        newEquityLinkDateBO.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
        //计算金额
        newEquityLinkDateBO.setAmount(equityTransactRecordBO.getPerSharePrice()*newHoldNum);
        return newEquityLinkDateBO;
    }
}