package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDetailDTO;
import com.bjike.goddess.shareholdersmanage.service.EquityTransactRecordDetailSer;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股权交易记录详情业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:11 ]
 * @Description: [ 股权交易记录详情业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("equityTransactRecordDetailApiImpl")
public class EquityTransactRecordDetailApiImpl implements EquityTransactRecordDetailAPI {
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;

    @Override
    public Long countTranDetail(EquityTransactRecordDetailDTO equityTransactRecordDetailDTO) throws SerException {
        return equityTransactRecordDetailSer.countTranDetail(equityTransactRecordDetailDTO);
    }

    @Override
    public EquityTransactRecordDetailBO getOne(String id) throws SerException {
        return equityTransactRecordDetailSer.getOne(id);
    }

    @Override
    public List<EquityTransactRecordDetailBO> findByName(String shareholderName) throws SerException {
        return equityTransactRecordDetailSer.findByName(shareholderName);
    }

    @Override
    public void save(EquityTransactRecordDetailTO equityTransactRecordDetailTO) throws SerException {
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTO);
    }

    @Override
    public void edit(EquityTransactRecordDetailTO equityTransactRecordDetailTO) throws SerException {
        equityTransactRecordDetailSer.edit(equityTransactRecordDetailTO);
    }

    @Override
    public void delete(String id) throws SerException {
        equityTransactRecordDetailSer.delete(id);
    }

    @Override
    public void deleteByName(String shareholderName) throws SerException {
        equityTransactRecordDetailSer.deleteByName(shareholderName);
    }

    @Override
    public List<EquityTransactRecordDetailBO> getByName(String shareholderName) throws SerException {
        return equityTransactRecordDetailSer.getByName(shareholderName);
    }

    @Override
    public EquityTransactRecordDetailBO getByNameId(String shareholderName, String transactId) throws SerException {
        return equityTransactRecordDetailSer.getByNameId(shareholderName,transactId);
    }

    @Override
    public void deleteByTransactId(String transactId) throws SerException {
        equityTransactRecordDetailSer.deleteByTransactId(transactId);
    }
}