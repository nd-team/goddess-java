package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.SecureCaseBO;
import com.bjike.goddess.secure.bo.SecureCaseCollectBO;
import com.bjike.goddess.secure.dto.SecureCaseDTO;
import com.bjike.goddess.secure.service.SecureCaseSer;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.SecureCaseCollectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 社保购买情况（汇总明细表）业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-23 02:59 ]
 * @Description: [ 社保购买情况（汇总明细表）业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("secureCaseApiImpl")
public class SecureCaseApiImpl implements SecureCaseAPI {
    @Autowired
    private SecureCaseSer secureCaseSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return secureCaseSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return secureCaseSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<SecureCaseBO> list(SecureCaseDTO dto) throws SerException {
        return secureCaseSer.list(dto);
    }
    @Override
    public List<SecureCaseCollectBO> collect(SecureCaseCollectTO to) throws SerException {
        return secureCaseSer.collect(to);
    }
    @Override
    public Set<String> allArea() throws SerException{
        return secureCaseSer.allArea();
    }
    @Override
    public Set<String> allProjectGroup() throws SerException{
        return secureCaseSer.allProjectGroup();
    }
    @Override
    public Set<String> allUnit()throws SerException{
        return secureCaseSer.allUnit();
    }
}