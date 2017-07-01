package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.DebtBO;
import com.bjike.goddess.reportmanagement.bo.DetailBO;
import com.bjike.goddess.reportmanagement.bo.StructureBO;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
import com.bjike.goddess.reportmanagement.service.DebtSer;
import com.bjike.goddess.reportmanagement.to.DebtTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 负债表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("debtApiImpl")
public class DebtApiImpl implements DebtAPI {
    @Autowired
    private DebtSer debtSer;

    @Override
    public List<StructureBO> debtStructure(DebtDTO dto) throws SerException {
        return debtSer.debtStructure(dto);
    }

    @Override
    public List<DebtBO> list(DebtDTO dto) throws SerException {
        return debtSer.list(dto);
    }

    @Override
    public DebtBO save(DebtTO to) throws SerException {
        return debtSer.save(to);
    }

    @Override
    public List<DetailBO> findDetails(String id, DebtDTO dto) throws SerException {
        return debtSer.findDetails(id, dto);
    }

    @Override
    public Long count(DebtDTO dto) throws SerException {
        return debtSer.count(dto);
    }

    @Override
    public DebtBO findByID(String id) throws SerException {
        return debtSer.findByID(id);
    }

    @Override
    public void edit(DebtTO to) throws SerException {
        debtSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        debtSer.delete(id);
    }
}