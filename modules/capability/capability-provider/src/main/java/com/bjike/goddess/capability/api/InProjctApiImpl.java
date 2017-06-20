package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.InProjctBO;
import com.bjike.goddess.capability.service.InProjctSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 尚在进行中项目数业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:23 ]
 * @Description: [ 尚在进行中项目数业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("inProjctApiImpl")
public class InProjctApiImpl implements InProjctAPI {
    @Autowired
    private InProjctSer inProjctSer;

    @Override
    public InProjctBO addInProjct(String[] inProjcts, String companyId) throws SerException {
        return inProjctSer.addInProjct(inProjcts, companyId);
    }

    @Override
    public InProjctBO editInProjct(String[] inProjcts, String companyId) throws SerException {
        return inProjctSer.editInProjct(inProjcts, companyId);
    }

    @Override
    public void deleteInProjct(String id) throws SerException {
        inProjctSer.deleteInProjct(id);
    }

}