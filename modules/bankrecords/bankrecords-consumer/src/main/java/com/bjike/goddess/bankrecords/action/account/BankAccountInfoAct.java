package com.bjike.goddess.bankrecords.action.account;

import com.bjike.goddess.bankrecords.api.BankAccountInfoAPI;
import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.to.BankAccountInfoTO;
import com.bjike.goddess.bankrecords.vo.BankAccountInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 账号信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("bankaccountinfo")
public class BankAccountInfoAct {

    @Autowired
    private BankAccountInfoAPI bankAccountInfoAPI;


    /**
     * 查询列表总条数
     *
     * @param dto 查询条件或分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BankAccountInfoDTO dto) throws ActException {
        try {
            Long count = bankAccountInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询银行流水
     *
     * @param id 竞争对手Id
     * @return class BankAccountInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BankAccountInfoVO vo = BeanTransform.copyProperties(bankAccountInfoAPI.findById(id), BankAccountInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增账号信息
     *
     * @param to 账号信息信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) BankAccountInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            BankAccountInfoVO vo = BeanTransform.copyProperties(bankAccountInfoAPI.add(to), BankAccountInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑账号信息
     *
     * @param to 账号信息信息
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) BankAccountInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            BankAccountInfoVO vo = BeanTransform.copyProperties(bankAccountInfoAPI.edit(to), BankAccountInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除账号信息
     *
     * @param id 账号信息ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            bankAccountInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(BankAccountInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BankAccountInfoVO> voList = BeanTransform.copyProperties(bankAccountInfoAPI.pageList(dto), BankAccountInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}