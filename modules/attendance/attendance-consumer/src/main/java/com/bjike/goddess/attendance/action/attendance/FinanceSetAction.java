package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.FinanceSetAPI;
import com.bjike.goddess.attendance.bo.FinanceSetBO;
import com.bjike.goddess.attendance.dto.FinanceSetDTO;
import com.bjike.goddess.attendance.to.FinanceSetTO;
import com.bjike.goddess.attendance.vo.FinanceSetVO;
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
 * 财务出勤设置
 *
 * @Author: [chenjunhao]
 * @Date: [ 2017-11-03 04:15 ]
 * @Description: [ 财务出勤设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("financeset")
public class FinanceSetAction {
    @Autowired
    private FinanceSetAPI financeSetAPI;

    /**
     * 列表
     *
     * @param dto 财务出勤设置数据传输
     * @return class FinanceSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FinanceSetDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FinanceSetBO> list = financeSetAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, FinanceSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 财务出勤设置传输对象
     * @return class FinanceSetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) FinanceSetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            FinanceSetBO bo = financeSetAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, FinanceSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 财务出勤设置id
     * @return class FinanceSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/finance/set/{id}")
    public Result FinanceSet(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FinanceSetBO bo = financeSetAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, FinanceSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 财务出勤设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) FinanceSetTO to, BindingResult result) throws ActException {
        try {
            financeSetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 财务出勤设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            financeSetAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 财务出勤设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FinanceSetDTO dto) throws ActException {
        try {
            return ActResult.initialize(financeSetAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 定时检测发送
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/send")
    public Result send() throws ActException {
        try {
            financeSetAPI.send();
            return new ActResult("aa");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}