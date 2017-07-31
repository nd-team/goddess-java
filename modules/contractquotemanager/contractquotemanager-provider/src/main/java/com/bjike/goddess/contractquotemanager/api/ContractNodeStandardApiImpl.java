package com.bjike.goddess.contractquotemanager.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.bo.ColationBO;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.to.FilterTO;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractNodeStandard;
import com.bjike.goddess.contractquotemanager.service.ContractNodeStandardSer;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;
import com.bjike.goddess.contractquotemanager.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同节点标准信息业务接口实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.724 ]
 * @Description: [ 合同节点标准信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractNodeStandardApiImpl")
public class ContractNodeStandardApiImpl implements ContractNodeStandardAPI {

    @Override
    public Boolean sonPermission() throws SerException {
        return contractNodeStandardSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractNodeStandardSer.guidePermission(guidePermissionTO);
    }

    @Autowired
    private ContractNodeStandardSer contractNodeStandardSer;

    /**
     * 根据id查询合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    public ContractNodeStandardBO findById(String id) throws SerException {
        ContractNodeStandard model = contractNodeStandardSer.findById(id);
        return BeanTransform.copyProperties(model, ContractNodeStandardBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 合同节点标准信息dto
     * @throws SerException
     */
    @Override
    public Long count(ContractNodeStandardDTO dto) throws SerException {
        contractNodeStandardSer.condiy(dto);
        return contractNodeStandardSer.count(dto);
    }

    /**
     * 添加合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @return class ContractNodeStandardBO
     * @throws SerException
     */
    @Override
    public ContractNodeStandardBO save(ContractNodeStandardTO to) throws SerException {
        return contractNodeStandardSer.save(to);
    }

    /**
     * 分页查询合同节点标准信息
     *
     * @param dto 合同节点标准信息dto
     * @return class ContractNodeStandardBO
     * @throws SerException
     */
    @Override
    public List<ContractNodeStandardBO> list(ContractNodeStandardDTO dto) throws SerException {
        return contractNodeStandardSer.list(dto);
    }

    /**
     * 编辑合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @throws SerException
     */
    @Override
    public void update(ContractNodeStandardTO to) throws SerException {
        contractNodeStandardSer.update(to);
    }

    /**
     * 根据id删除合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        contractNodeStandardSer.remove(id);
    }

    /**
     * 汇总合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @return class ContractNodeStandardBO
     * @throws SerException
     */
    @Override
    public List<ContractNodeStandardBO> collect(ContractNodeStandardTO to) throws SerException {
        return contractNodeStandardSer.collect(to);
    }

    /**
     * 搜索合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @return
     * @throws SerException
     */
    @Override
    public List<ContractNodeStandardBO> searchContractNodeStandard(ContractNodeStandardTO to) throws SerException {
        return contractNodeStandardSer.searchContractNodeStandard(to);
    }

    @Override
    public List<ColationBO> findType() throws SerException {
        return contractNodeStandardSer.findType();
    }

    @Override
    public List<ColationBO> findNode() throws SerException {
        return contractNodeStandardSer.findNode();
    }

    @Override
    public List<ContractNodeStandardBO> findByTo(FilterTO to) throws SerException {
        return contractNodeStandardSer.findByTo(to);
    }
}