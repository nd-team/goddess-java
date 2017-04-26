package com.bjike.goddess.employeecontract.action.employeecontract;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.api.ContractNatureAPI;
import com.bjike.goddess.employeecontract.dto.ContractNatureDTO;
import com.bjike.goddess.employeecontract.to.ContractNatureTO;
import com.bjike.goddess.employeecontract.vo.ContractNatureChoiceVO;
import com.bjike.goddess.employeecontract.vo.ContractNatureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 合同性质
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:58 ]
 * @Description: [ 合同性质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractnature")
public class ContractNatureAction {

    @Autowired
    private ContractNatureAPI contractNatureAPI;

    /**
     * 添加
     *
     * @param to 合同性质传输对象
     * @return class ContractNatureVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(ContractNatureTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNatureAPI.save(to), ContractNatureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 合同性质传输对象
     * @return class ContractNatureVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(ContractNatureTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNatureAPI.update(to), ContractNatureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 合同性质数据id
     * @return class ContractNatureVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNatureAPI.delete(id), ContractNatureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 合同性质数据id
     * @return class ContractNatureVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNatureAPI.congeal(id), ContractNatureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 合同性质数据id
     * @return class ContractNatureVO
     * @version v1
     */
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNatureAPI.thaw(id), ContractNatureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结合同性质数据
     *
     * @return class ContractNatureVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNatureAPI.findThaw(), ContractNatureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 合同性质数据传输对象
     * @return class ContractNatureVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ContractNatureDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNatureAPI.maps(dto), ContractNatureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取合同性质数据
     *
     * @param id 合同性质数据id
     * @return class ContractNatureVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNatureAPI.getById(id), ContractNatureVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取选项
     *
     * @return class ContractNatureChoiceVO
     * @version v1
     */
    @GetMapping("v1/getChoice")
    public Result getOption(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNatureAPI.findThaw(), ContractNatureChoiceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(contractNatureAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}