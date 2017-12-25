package com.bjike.goddess.contractquotemanager.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;
import com.bjike.goddess.contractquotemanager.excel.SonPermissionObject;
import com.bjike.goddess.contractquotemanager.service.ContractQuoteDataSer;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import com.bjike.goddess.contractquotemanager.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同单价资料信息业务接口实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:01:53.321 ]
 * @Description: [ 合同单价资料信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractQuoteDataApiImpl")
public class ContractQuoteDataApiImpl implements ContractQuoteDataAPI {

    @Autowired
    private ContractQuoteDataSer contractQuoteDataSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return contractQuoteDataSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractQuoteDataSer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    @Override
    public ContractQuoteDataBO findById(String id) throws SerException {
        ContractQuoteData model = contractQuoteDataSer.findById(id);
        return BeanTransform.copyProperties(model, ContractQuoteDataBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 合同单价资料信息dto
     * @throws SerException
     */
    @Override
    public Long count(ContractQuoteDataDTO dto) throws SerException {
        contractQuoteDataSer.condiy(dto);
        return contractQuoteDataSer.count(dto);
    }

    /**
     * 添加合同单价资料信息
     *
     * @param to 合同单价资料信息to
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    @Override
    public ContractQuoteDataBO save(ContractQuoteDataTO to) throws SerException {
        return contractQuoteDataSer.save(to);
    }

    /**
     * 分页查询合同单价资料信息
     *
     * @param dto 合同单价资料信息dto
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    @Override
    public List<ContractQuoteDataBO> list(ContractQuoteDataDTO dto) throws SerException {
        return contractQuoteDataSer.list(dto);
    }

    /**
     * 编辑合同单价资料信息
     *
     * @param to 合同单价资料信息to
     * @throws SerException
     */
    @Override
    public void update(ContractQuoteDataTO to) throws SerException {
        contractQuoteDataSer.update(to);
    }

    /**
     * 根据id删除合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        contractQuoteDataSer.remove(id);
    }

    /**
     * 冻结合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws SerException
     */
    @Override
    public void congealStatus(String id) throws SerException {
        contractQuoteDataSer.congealStatus(id);
    }

    /**
     * 解冻合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws SerException
     */
    @Override
    public void thawStatus(String id) throws SerException {
        contractQuoteDataSer.thawStatus(id);
    }

    /**
     * 汇总合同单价资料信息
     *
     * @param dto 合同单价资料信息dto
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    @Override
    public List<ContractQuoteDataBO> collect(ContractQuoteDataDTO dto) throws SerException {
        return contractQuoteDataSer.collect(dto);
    }

    /**
     * 搜索合同单价资料信息
     *
     * @param to 合同单价资料信息bo
     * @return
     * @throws SerException
     */
    /**
     * 根据地区或项目组查询合同单价资料信息
     *
     * @param area 地区
     * @param project 项目组
     * @return
     * @throws SerException
     */
    @Override
    public List<ContractQuoteDataBO> searchs(String area, String project) throws SerException {
        return contractQuoteDataSer.searchs(area, project);
    }

}