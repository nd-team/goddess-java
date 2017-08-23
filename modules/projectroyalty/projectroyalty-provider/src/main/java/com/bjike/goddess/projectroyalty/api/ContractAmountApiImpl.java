package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.ContractAmountBO;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.dto.ContractAmountDTO;
import com.bjike.goddess.projectroyalty.service.ContractAmountSer;
import com.bjike.goddess.projectroyalty.to.ContractAmountTO;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同金额业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:45 ]
 * @Description: [ 合同金额业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractAmountApiImpl")
public class ContractAmountApiImpl implements ContractAmountAPI {

    @Autowired
    private ContractAmountSer contractAmountSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return contractAmountSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractAmountSer.guidePermission(guidePermissionTO);
    }


    @Override
    public ContractAmountBO save(ContractAmountTO to) throws SerException {
        return contractAmountSer.save(to);
    }

    @Override
    public ContractAmountBO update(ContractAmountTO to) throws SerException {
        return contractAmountSer.update(to);
    }

    @Override
    public ContractAmountBO delete(String id) throws SerException {
        return contractAmountSer.delete(id);
    }

    @Override
    public ContractAmountBO getById(String id) throws SerException {
        return contractAmountSer.getById(id);
    }

    @Override
    public List<ContractAmountBO> maps(ContractAmountDTO dto) throws SerException {
        return contractAmountSer.maps(dto);
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        return contractAmountSer.findOpinion();
    }

    @Override
    public Long getTotal() throws SerException {
        return contractAmountSer.getTotal();
    }
}