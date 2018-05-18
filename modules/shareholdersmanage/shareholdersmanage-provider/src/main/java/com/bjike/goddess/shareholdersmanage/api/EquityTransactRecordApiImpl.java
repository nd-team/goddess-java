package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareAndTypeBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDTO;
import com.bjike.goddess.shareholdersmanage.service.EquityTransactRecordSer;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股权交易记录业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:05 ]
 * @Description: [ 股权交易记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("equityTransactRecordApiImpl")
public class EquityTransactRecordApiImpl implements EquityTransactRecordAPI {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return equityTransactRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return equityTransactRecordSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countTrans(EquityTransactRecordDTO equityTransactRecordDTO) throws SerException {
        return equityTransactRecordSer.countTrans(equityTransactRecordDTO);
    }

    @Override
    public EquityTransactRecordBO getOne(String id) throws SerException {
        return equityTransactRecordSer.getOne(id);
    }

    @Override
    public List<EquityTransactRecordBO> findList(EquityTransactRecordDTO equityTransactRecordDTO) throws SerException {
        return equityTransactRecordSer.findList(equityTransactRecordDTO);
    }

    @Override
    public Double transTotalAmount(String equityType) throws SerException {
        return equityTransactRecordSer.transTotalAmount(equityType);
    }

    @Override
    public void updateTrans(EquityTransactRecordTO equityTransactRecordTO) throws SerException {
        equityTransactRecordSer.updateTrans(equityTransactRecordTO);
    }

    @Override
    public void updateTransList() throws SerException {
        equityTransactRecordSer.updateTransList();
    }

    @Override
    public void deleteTransact(String id) throws SerException {
        equityTransactRecordSer.deleteTransact(id);
    }

    @Override
    public void deleteByName(String shareholderName) throws SerException {
        equityTransactRecordSer.deleteByName(shareholderName);
    }

    @Override
    public EquityTransactRecordBO getByName(String shareholderName) throws SerException {
        return equityTransactRecordSer.getByName(shareholderName);
    }

    @Override
    public List<EquityTransactRecordBO> summationTrans() throws SerException {
        return equityTransactRecordSer.summationTrans();
    }

    @Override
    public void reinstate(EquityTransactRecordBO equityTransactRecordBO, EquityTransactRecordDetailBO equityTransactRecordDetailBO) throws SerException {
        equityTransactRecordSer.reinstate(equityTransactRecordBO,equityTransactRecordDetailBO);
    }

    @Override
    public List<EquityTransactRecordBO> getByEquityType(String equityType) throws SerException {
        return equityTransactRecordSer.getByEquityType(equityType);
    }

    @Override
    public List<String> getNameByEquityType(String equityType) throws SerException {
        return equityTransactRecordSer.getNameByEquityType(equityType);
    }

    @Override
    public List<ShareAndTypeBO> getNameAndType() throws SerException {
        return equityTransactRecordSer.getNameAndType();
    }

    @Override
    public List<String> findEquityType() throws SerException {
        return equityTransactRecordSer.findEquityType();
    }
}