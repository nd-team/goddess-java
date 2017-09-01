package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransferBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransferDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecordDetail;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransfer;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransferTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 股权转让业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:32 ]
 * @Description: [ 股权转让业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class EquityTransferSerImpl extends ServiceImpl<EquityTransfer, EquityTransferDTO> implements EquityTransferSer {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;

    @Override
    public Long countEquityfer(EquityTransferDTO equityTransferDTO) throws SerException {
        Long count = super.count(equityTransferDTO);
        return count;
    }

    @Override
    public EquityTransferBO getOne(String id) throws SerException {
        EquityTransfer equityTransfer = super.findById(id);
        return BeanTransform.copyProperties(equityTransfer, EquityTransferBO.class);
    }

    @Override
    public List<EquityTransferBO> findList(EquityTransferDTO equityTransferDTO) throws SerException {
        searchCondi(equityTransferDTO);
        equityTransferDTO.getSorts().add("createTime=desc");
        List<EquityTransfer> equityTransferList = super.findByCis(equityTransferDTO);
        return BeanTransform.copyProperties(equityTransferList, EquityTransferBO.class);
    }

    /**
     * 根据条件查询数据
     *
     * @param equityTransferDTO
     * @throws SerException
     */
    public void searchCondi(EquityTransferDTO equityTransferDTO) throws SerException {
        if (StringUtils.isNotBlank(equityTransferDTO.getArea())) {
            equityTransferDTO.getConditions().add(Restrict.eq("area", equityTransferDTO.getArea()));
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityTransferBO save(EquityTransferTO equityTransferTO) throws SerException {
        //判断输入的转让人股数是否大于此股东所拥有的股数
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(equityTransferTO.getAssignor());
        if (equityTransactRecordBO.getHoldNum() < equityTransferTO.getTransferHoldNum()) {
            throw new SerException(equityTransferTO.getAssignor()+"没有那么多股权数量");
        }
        //添加本条数据
        EquityTransfer equityTransfer = BeanTransform.copyProperties(equityTransferTO, EquityTransfer.class, true);
        equityTransfer.setCreateTime(LocalDateTime.now());
        equityTransfer = super.save(equityTransfer);
        //修改交易记录转让人信息
        EquityTransactRecordTO equityTransactRecordTO = new EquityTransactRecordTO();
        equityTransactRecordTO.setHoldNum(equityTransactRecordBO.getHoldNum() - equityTransferTO.getTransferHoldNum());
        equityTransactRecordTO.setAmount(equityTransactRecordBO.getAmount() - equityTransferTO.getAmount());
        equityTransactRecordTO.setId(equityTransactRecordBO.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTO);
        //修改交易记录受让人信息
        EquityTransactRecordBO equityTransactRecordBO2 = equityTransactRecordSer.getByName(equityTransferTO.getReceiving());
        EquityTransactRecordTO equityTransactRecordTO2 = new EquityTransactRecordTO();
        equityTransactRecordTO2.setHoldNum(equityTransactRecordBO2.getHoldNum() + equityTransferTO.getTransferHoldNum());
        equityTransactRecordTO2.setAmount(equityTransactRecordBO2.getAmount() + equityTransferTO.getAmount());
        equityTransactRecordTO2.setId(equityTransactRecordBO2.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTO2);
        //重新设置所有股东占股比例
        equityTransactRecordSer.updateTransList();
        //添加交易记录明细转让人信息
        EquityTransactRecordDetailTO equityTransactRecordDetailTO = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTO.setShareholderName(equityTransferTO.getAssignor());
        equityTransactRecordDetailTO.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTO.setPerSharePrice(equityTransferTO.getPerSharePrice());
        equityTransactRecordDetailTO.setHoldNum(-equityTransferTO.getTransferHoldNum());
        equityTransactRecordDetailTO.setAmount(-equityTransferTO.getAmount());
        equityTransactRecordDetailTO.setTransactType("股东转让");
        equityTransactRecordDetailTO.setTransactId(equityTransfer.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTO);
        //添加交易记录明细受让人信息
        EquityTransactRecordDetailTO equityTransactRecordDetailTO2 = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTO2.setShareholderName(equityTransferTO.getReceiving());
        equityTransactRecordDetailTO2.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTO2.setPerSharePrice(equityTransferTO.getPerSharePrice());
        equityTransactRecordDetailTO2.setHoldNum(equityTransferTO.getTransferHoldNum());
        equityTransactRecordDetailTO2.setAmount(equityTransferTO.getAmount());
        equityTransactRecordDetailTO2.setTransactType("股东转让");
        equityTransactRecordDetailTO2.setTransactId(equityTransfer.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTO2);
        return BeanTransform.copyProperties(equityTransfer,EquityTransferBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityTransferBO edit(EquityTransferTO equityTransferTO) throws SerException {
        EquityTransfer equityTransfer = super.findById(equityTransferTO.getId());
        String assignor = equityTransfer.getAssignor();//转让人
        String receiving = equityTransfer.getReceiving();//受让人
        Integer transferHoldNum = equityTransfer.getTransferHoldNum();//转让股数
        //判断转让人受让人转让股数是否被修改
        if(!assignor.equals(equityTransferTO.getAssignor()) || !receiving.equals(equityTransferTO.getReceiving()) || transferHoldNum!=equityTransferTO.getTransferHoldNum()){
            //恢复转让人受让人交易记录信息
            EquityTransactRecordBO equityTransactRecordBOA = equityTransactRecordSer.getByName(assignor);//修改之前的转让人信息交易记录//恢复转让人受让人交易记录信息
            EquityTransactRecordDetailBO equityTransactRecordDetailBOA = equityTransactRecordDetailSer.getByNameId(assignor,equityTransferTO.getId());//修改之前的转让人信息交易记录明细
            EquityTransactRecordBO equityTransactRecordBOR = equityTransactRecordSer.getByName(receiving);//修改之前的受让人信息交易记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBOR = equityTransactRecordDetailSer.getByNameId(receiving,equityTransferTO.getId());//修改之前的受让人信息交易记录明细
            equityTransactRecordSer.reinstate(equityTransactRecordBOA,equityTransactRecordDetailBOA);//转让人
            equityTransactRecordSer.reinstate(equityTransactRecordBOR,equityTransactRecordDetailBOR);//受让人
            //判断转让人是否有足够多的股权数量
            EquityTransactRecordBO equityTransactRecordBOAA = equityTransactRecordSer.getByName(equityTransferTO.getAssignor());
            if (equityTransactRecordBOAA.getHoldNum() < equityTransferTO.getTransferHoldNum()) {
                throw new SerException(equityTransferTO.getAssignor()+"没有那么多股权数量");
            }
            //修改转让人交易记录明细信息
            EquityTransactRecordDetailTO equityTransactRecordDetailTOA = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOA.setId(equityTransactRecordDetailBOA.getId());
            equityTransactRecordDetailTOA.setShareholderName(equityTransferTO.getAssignor());
            equityTransactRecordDetailTOA.setHoldNum(-equityTransferTO.getTransferHoldNum());
            equityTransactRecordDetailTOA.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOA.setPerSharePrice(equityTransferTO.getPerSharePrice());
            equityTransactRecordDetailTOA.setAmount(-equityTransferTO.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOA);
            //修改受让人交易记录明细信息
            EquityTransactRecordDetailTO equityTransactRecordDetailTOR = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTOR.setId(equityTransactRecordDetailBOR.getId());
            equityTransactRecordDetailTOR.setShareholderName(equityTransferTO.getReceiving());
            equityTransactRecordDetailTOR.setHoldNum(equityTransferTO.getTransferHoldNum());
            equityTransactRecordDetailTOR.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTOR.setPerSharePrice(equityTransferTO.getPerSharePrice());
            equityTransactRecordDetailTOR.setAmount(equityTransferTO.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTOR);

            //修改修改后转让人交易记录信息
            EquityTransactRecordTO equityTransactRecordTOA = new EquityTransactRecordTO();
            equityTransactRecordTOA.setHoldNum(equityTransactRecordBOAA.getHoldNum() - equityTransferTO.getTransferHoldNum());
            equityTransactRecordTOA.setAmount(equityTransactRecordBOAA.getAmount() - equityTransferTO.getAmount());
            equityTransactRecordTOA.setId(equityTransactRecordBOAA.getId());
            //修改修改后受让人交易记录信息
            EquityTransactRecordBO equityTransactRecordBOAR = equityTransactRecordSer.getByName(equityTransferTO.getReceiving());
            EquityTransactRecordTO equityTransactRecordTOR = new EquityTransactRecordTO();
            equityTransactRecordTOR.setHoldNum(equityTransactRecordBOAR.getHoldNum() + equityTransferTO.getTransferHoldNum());
            equityTransactRecordTOR.setAmount(equityTransactRecordBOAR.getAmount() + equityTransferTO.getAmount());
            equityTransactRecordTOR.setId(equityTransactRecordBOAR.getId());
            equityTransactRecordSer.updateTrans(equityTransactRecordTOR);
            //重新设置所有股东占股比例
            equityTransactRecordSer.updateTransList();

        }
        //修改本条信息
        LocalDateTime date = equityTransfer.getCreateTime();
        equityTransfer = BeanTransform.copyProperties(equityTransferTO,EquityTransfer.class,true);
        equityTransfer.setCreateTime(date);
        equityTransfer.setModifyTime(LocalDateTime.now());
        super.update(equityTransfer);
        return BeanTransform.copyProperties(equityTransfer,EquityTransferBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        EquityTransfer equityTransfer = super.findById(id);
        //恢复受让人数据
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(equityTransfer.getAssignor());
        EquityTransactRecordDetailBO equityTransactRecordDetailBO = equityTransactRecordDetailSer.getByNameId(equityTransfer.getAssignor(),id);
        equityTransactRecordSer.reinstate(equityTransactRecordBO,equityTransactRecordDetailBO);
        //恢复转让人数据
        EquityTransactRecordBO equityTransactRecordBO1 = equityTransactRecordSer.getByName(equityTransfer.getReceiving());
        EquityTransactRecordDetailBO equityTransactRecordDetailBO1 = equityTransactRecordDetailSer.getByNameId(equityTransfer.getAssignor(),id);
        equityTransactRecordSer.reinstate(equityTransactRecordBO1,equityTransactRecordDetailBO1);
        //删除交易明细数据
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本条数据
        super.remove(id);
    }
}