package com.bjike.goddess.receivable.action.receivable;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.receivable.api.ContractorAPI;
import com.bjike.goddess.receivable.bo.ContractorBO;
import com.bjike.goddess.receivable.dto.ContractorDTO;
import com.bjike.goddess.receivable.to.ContractorTO;
import com.bjike.goddess.receivable.vo.ContractorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 承包商列表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 03:14 ]
 * @Description: [ 承包商列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("receivable/contractor")
public class ContractorAction {
    @Autowired
    private ContractorAPI contractorAPI;

    /**
     * 获取承包商列表
     *
     * @param contractorDTO 承包商列表dto
     * @return class ContractorVO
     * @des 获取所有承包商列表
     * @version v1
     */
    @GetMapping("v1/listContractor")
    public Result findListContractor(ContractorDTO contractorDTO) throws ActException {
        try {
            List<ContractorVO> contractorVOS = BeanTransform.copyProperties
                    (contractorAPI.findListContractor(contractorDTO), ContractorVO.class);
            return ActResult.initialize(contractorVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加承包商列表
     *
     * @param contractorTO 承包商列表数据to
     * @return class ContractorVO
     * @des 添加承包商列表
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addContractor(ContractorTO contractorTO) throws ActException {
        try {
            ContractorBO contractorBO = contractorAPI.insertContractor(contractorTO);
            return ActResult.initialize(contractorBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑承包商列表
     *
     * @param contractorTO 承包商列表数据to
     * @return class ContractorVO
     * @des 编辑承包商列表
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editContractor(ContractorTO contractorTO) throws ActException {
        try {
            ContractorBO contractorBO = contractorAPI.editContractor(contractorTO);
            return ActResult.initialize(contractorBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除承包商列表
     *
     * @param id 用户id
     * @des 根据用户id删除承包商列表记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeContractor(String id) throws ActException {
        try {
            contractorAPI.removeContractor(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}