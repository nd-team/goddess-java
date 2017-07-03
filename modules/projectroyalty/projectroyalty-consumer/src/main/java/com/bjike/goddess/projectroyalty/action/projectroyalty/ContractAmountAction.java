package com.bjike.goddess.projectroyalty.action.projectroyalty;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.api.ContractAmountAPI;
import com.bjike.goddess.projectroyalty.dto.ContractAmountDTO;
import com.bjike.goddess.projectroyalty.to.ContractAmountTO;
import com.bjike.goddess.projectroyalty.vo.ContractAmountVO;
import com.bjike.goddess.projectroyalty.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 合同金额
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:45 ]
 * @Description: [ 合同金额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractamount")
public class ContractAmountAction {

    @Autowired
    private ContractAmountAPI contractAmountAPI;


    /**
     * 保存
     *
     * @param to 合同金额传输对象
     * @return class ContractAmountVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ContractAmountTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractAmountAPI.save(to), ContractAmountVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 合同金额传输对象
     * @return class ContractAmountVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ContractAmountTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractAmountAPI.update(to), ContractAmountVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 合同金额数据id
     * @return class ContractAmountVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractAmountAPI.delete(id), ContractAmountVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取合同金额数据
     *
     * @param id 合同金额数据id
     * @return class ContractAmountVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractAmountAPI.getById(id), ContractAmountVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取选项
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/findOpinion")
    public Result findOpinion() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractAmountAPI.findOpinion(), OpinionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 合同金额数据传输对象
     * @return class ContractAmountVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ContractAmountDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractAmountAPI.maps(dto), ContractAmountVO.class, request));
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
            return ActResult.initialize(contractAmountAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}