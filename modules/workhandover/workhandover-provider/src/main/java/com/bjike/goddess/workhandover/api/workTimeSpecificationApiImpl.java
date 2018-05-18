package com.bjike.goddess.workhandover.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.workhandover.bo.workTimeSpecificationBO;
import com.bjike.goddess.workhandover.dto.workTimeSpecificationDTO;
import com.bjike.goddess.workhandover.to.workTimeSpecificationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作交接时间规范业务接口实现
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-11 04:47 ]
 * @Description: [ 工作交接时间规范业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("workTimeSpecificationApiImpl")
public class workTimeSpecificationApiImpl implements workTimeSpecificationAPI {

    @Autowired
    workTimeSpecificationAPI workTimeSpecificationAPI;

    @Override
    public List<workTimeSpecificationBO> findWorkTimeSpecification(workTimeSpecificationDTO to) throws SerException {
        return workTimeSpecificationAPI.findWorkTimeSpecification ( to );
    }

    @Override
    public workTimeSpecificationBO insertWorkTimeSpecification(workTimeSpecificationTO to) throws SerException {
        return workTimeSpecificationAPI.insertWorkTimeSpecification ( to );
    }

    @Override
    public workTimeSpecificationBO editWorkTimeSpecification(workTimeSpecificationTO to) throws SerException {
        return workTimeSpecificationAPI.editWorkTimeSpecification ( to );
    }

    @Override
    public void removeWorkTimeSpecification(String id) throws SerException {
        workTimeSpecificationAPI.removeWorkTimeSpecification ( id );
    }

    @Override
    public List<String> findWorkHandoverReason() throws SerException {
        return workTimeSpecificationAPI.findWorkHandoverReason ();
    }

    @Override
    public workTimeSpecificationBO importExcel(List<workTimeSpecificationTO> to) throws SerException {
        return workTimeSpecificationAPI.importExcel ( to );
    }

    @Override
    public byte[] exportExcel(workTimeSpecificationDTO dto) throws SerException {
        return workTimeSpecificationAPI.exportExcel ( dto );
    }

    @Override
    public byte[] templateExport() throws SerException {
        return new byte[0];
    }
}