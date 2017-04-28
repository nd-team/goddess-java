package com.bjike.goddess.employeecontract.action.employeecontract;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.api.ContractChangeAPI;
import com.bjike.goddess.employeecontract.dto.ContractChangeDTO;
import com.bjike.goddess.employeecontract.to.ContractChangeTO;
import com.bjike.goddess.employeecontract.vo.ContractChangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 合同变更详细
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:23 ]
 * @Description: [ 合同变更详细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractchange")
public class ContractChangeAction {

    @Autowired
    private ContractChangeAPI contractChangeAPI;

    /**
     * 修改
     *
     * @param to 合同变更详细传输对象
     * @return class ContractChangeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ContractChangeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractChangeAPI.update(to), ContractChangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 合同变更详细数据id
     * @return class ContractChangeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractChangeAPI.delete(id), ContractChangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 合同变更详细数据传输对象
     * @return class ContractChangeVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ContractChangeDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractChangeAPI.maps(dto), ContractChangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(contractChangeAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取合同变更详细数据
     *
     * @param id 合同变更详细数据id
     * @return class ContractChangeVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractChangeAPI.getById(id), ContractChangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}