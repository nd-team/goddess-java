package com.bjike.goddess.workhandover.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.workhandover.bo.TransInfoBO;
import com.bjike.goddess.workhandover.dto.TransInfoDTO;
import com.bjike.goddess.workhandover.service.TransInfoSer;
import com.bjike.goddess.workhandover.to.TransInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作交接业务接口实现
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-10 05:08 ]
 * @Description: [ 工作交接业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("TransInfoApiImpl")
public class TransInfoApiImpl implements TransInfoAPI {

    @Autowired
    TransInfoSer transInfoSer;

    @Override
    public List<TransInfoBO> findWorkHandOver(TransInfoDTO to) throws SerException {
        return transInfoSer.findWorkHandOver ( to );
    }

    @Override
    public TransInfoBO getOneWorkhandOver(String id) throws SerException {
        return transInfoSer.getOneWorkHandOver ( id );
    }

    @Override
    public TransInfoBO insertWorkHandOver(TransInfoTO to) throws SerException {
        return transInfoSer.insertWorkHandOver ( to );
    }

    @Override
    public TransInfoBO editWorkHandOver(TransInfoTO to) throws SerException {
        return transInfoSer.editWorkHandOver ( to );
    }

    @Override
    public void removeWorkHandOver(String id) throws SerException {
        transInfoSer.removeWorkHandOver ( id );
    }

    @Override
    public Long countWorkHandOver(TransInfoDTO dto) throws SerException {
        return transInfoSer.countWorkHandOver ( dto );
    }

    @Override
    public TransInfoBO importExcel(List<TransInfoTO> to) throws SerException {
        return transInfoSer.importExcel ( to );
    }

    @Override
    public byte[] exportExcel(TransInfoDTO dto) throws SerException {
        return transInfoSer.exportExcel ( dto );
    }

    @Override
    public byte[] templateExport() throws SerException {
        return new byte[0];
    }


}