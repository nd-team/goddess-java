package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.SettlementProcessBO;
import com.bjike.goddess.projectprocing.dto.SettlementProcessDTO;
import com.bjike.goddess.projectprocing.service.SettlementProcessSer;
import com.bjike.goddess.projectprocing.to.SettlementProcessTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 结算流程存储记录业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:16 ]
 * @Description: [ 结算流程存储记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("settlementProcessApiImpl")
public class SettlementProcessApiImpl implements SettlementProcessAPI {
    @Autowired
    private SettlementProcessSer settlementProcessSer;
    @Override
    public Long countSetProcess(SettlementProcessDTO settlementProcessDTO) throws SerException {
        return settlementProcessSer.countSetProcess(settlementProcessDTO);
    }

    @Override
    public SettlementProcessBO getOneById(String id) throws SerException {
        return settlementProcessSer.getOneById(id);
    }

    @Override
    public List<SettlementProcessBO> listSetProcess(SettlementProcessDTO settlementProcessDTO) throws SerException {
        return settlementProcessSer.listSetProcess(settlementProcessDTO);
    }

    @Override
    public SettlementProcessBO addSetProcess(SettlementProcessTO settlementProcessTO) throws SerException {
        return settlementProcessSer.addSetProcess(settlementProcessTO);
    }

    @Override
    public SettlementProcessBO editSetProcess(SettlementProcessTO settlementProcessTO) throws SerException {
        return settlementProcessSer.editSetProcess(settlementProcessTO);
    }

    @Override
    public void deleteSetProcess(String id) throws SerException {
        settlementProcessSer.deleteSetProcess(id);
    }

    @Override
    public void updateSettProceAttach(String id) throws SerException {
        settlementProcessSer.updateSettProceAttach(id);
    }

    @Override
    public List<String> findOutUnit() throws SerException {
        return settlementProcessSer.findOutUnit();
    }
}