package com.bjike.goddess.businessproject.api;

import com.bjike.goddess.businessproject.bo.OutsourcBusinessContractBO;
import com.bjike.goddess.businessproject.dto.OutsourcBusinessContractDTO;
import com.bjike.goddess.businessproject.service.OutsourcBusinessContractSer;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.to.OutsourcBusinessContractTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 外包半外包项目合同管理业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:55 ]
 * @Description: [ 外包半外包项目合同管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("outsourcBusinessContractApiImpl")
public class OutsourcBusinessContractApiImpl implements OutsourcBusinessContractAPI {
    @Autowired
    private OutsourcBusinessContractSer outsourcBusinessContractSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return outsourcBusinessContractSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return outsourcBusinessContractSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(OutsourcBusinessContractDTO dto) throws SerException {
        return outsourcBusinessContractSer.count(dto);
    }

    @Override
    public OutsourcBusinessContractBO getOneById(String id) throws SerException {
        return outsourcBusinessContractSer.getOneById(id);
    }

    @Override
    public List<OutsourcBusinessContractBO> list(OutsourcBusinessContractDTO dto) throws SerException {
        return outsourcBusinessContractSer.list(dto);
    }

    @Override
    public OutsourcBusinessContractBO add(OutsourcBusinessContractTO to) throws SerException {
        return outsourcBusinessContractSer.add(to);
    }

    @Override
    public OutsourcBusinessContractBO edit(OutsourcBusinessContractTO to) throws SerException {
        return outsourcBusinessContractSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        outsourcBusinessContractSer.delete(id);

    }

    @Override
    public Set<String> areas() throws SerException {
        return outsourcBusinessContractSer.areas();
    }

    @Override
    public OutsourcBusinessContractBO importExcel(List<OutsourcBusinessContractTO> contractTOS) throws SerException {
        return outsourcBusinessContractSer.importExcel(contractTOS);
    }

    @Override
    public byte[] exportExcel(OutsourcBusinessContractDTO dto) throws SerException {
        return outsourcBusinessContractSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return outsourcBusinessContractSer.templateExport();
    }
}