package com.bjike.goddess.equipmentprepared.action.equipmentprepared;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.equipmentprepared.api.MoneyReadyAPI;
import com.bjike.goddess.equipmentprepared.bo.MoneyReadyBO;
import com.bjike.goddess.equipmentprepared.bo.MoneyReadyCountBO;
import com.bjike.goddess.equipmentprepared.dto.MoneyReadyDTO;
import com.bjike.goddess.equipmentprepared.to.MoneyReadyTO;
import com.bjike.goddess.equipmentprepared.vo.MoneyReadyCountVO;
import com.bjike.goddess.equipmentprepared.vo.MoneyReadyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金准备审核
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-16 11:15 ]
 * @Description: [ 资金准备审核 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("moneyready")
public class MoneyReadyAct {
    @Autowired
    private MoneyReadyAPI moneyReadyAPI;

    /**
     * 添加
     *
     * @param to      资金准备审核信息
     * @param request 请求对象
     * @return class MoneyReadyVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) MoneyReadyTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MoneyReadyBO bo = moneyReadyAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, MoneyReadyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 资金准备审核信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MoneyReadyTO to, BindingResult result) throws ActException {
        try {
            moneyReadyAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资金准备审核id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            moneyReadyAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     资金准备审核分页信息
     * @param request 请求对象
     * @return class MoneyReadyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MoneyReadyDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<MoneyReadyBO> list = moneyReadyAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, MoneyReadyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      资金准备审核id
     * @param request 请求对象
     * @return class MoneyReadyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/moneyready/{id}")
    public Result moneyready(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MoneyReadyBO bo = moneyReadyAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, MoneyReadyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总对比
     *
     * @param month   汇总的月份
     * @param request 请求对象
     * @return class MoneyReadyCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count/{month}")
    public Result count(@PathVariable Integer month, HttpServletRequest request) throws ActException {
        try {
            List<MoneyReadyCountBO> list = moneyReadyAPI.count(month);
            return ActResult.initialize(BeanTransform.copyProperties(list, MoneyReadyCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/countSum")
    public Result countSum(MoneyReadyDTO dto) throws ActException {
        try {
            Long sum = moneyReadyAPI.countSum(dto);
            return ActResult.initialize(sum);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}