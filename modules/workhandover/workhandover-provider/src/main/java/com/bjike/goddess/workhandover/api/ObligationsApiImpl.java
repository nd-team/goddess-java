package com.bjike.goddess.workhandover.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.workhandover.bo.ObligationsBO;
import com.bjike.goddess.workhandover.dto.ObligationsDTO;
import com.bjike.goddess.workhandover.to.ObligationsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作交接时间规范业务接口实现
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-13 06:26 ]
 * @Description: [ 工作交接时间规范业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("obligationsApiImpl")
public class ObligationsApiImpl implements ObligationsAPI {

    @Override
    public List<ObligationsBO> findObligations(ObligationsDTO to) throws SerException {
        return null;
    }

    @Override
    public ObligationsBO insertObligations(ObligationsTO to) throws SerException {
        return null;
    }

    @Override
    public ObligationsBO editObligations(ObligationsTO to) throws SerException {
        return null;
    }

    @Override
    public void removeObligations(String id) throws SerException {

    }
}