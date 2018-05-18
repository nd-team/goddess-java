package com.bjike.goddess.taskallotment.action.taskallotment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.api.ConfirmCountEmailAPI;
import com.bjike.goddess.taskallotment.bo.ConfirmCountEmailBO;
import com.bjike.goddess.taskallotment.dto.ConfirmCountEmailDTO;
import com.bjike.goddess.taskallotment.to.ConfirmCountEmailTO;
import com.bjike.goddess.taskallotment.vo.ConfirmCountEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分配及确认汇总设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:44 ]
 * @Description: [ 分配及确认汇总设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("confirmcountemail")
public class ConfirmCountEmailAction {
    @Autowired
    private ConfirmCountEmailAPI confirmCountEmailAPI;

    /**
     * 列表
     *
     * @param dto 分配及确认汇总设置数据传输
     * @return class ConfirmCountEmailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ConfirmCountEmailDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ConfirmCountEmailBO> list = confirmCountEmailAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ConfirmCountEmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 分配及确认汇总设置传输对象
     * @return class ConfirmCountEmailVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ConfirmCountEmailTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ConfirmCountEmailBO bo = confirmCountEmailAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ConfirmCountEmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 分配及确认汇总设置id
     * @return class ConfirmCountEmailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/confirmCountEmail/{id}")
    public Result ConfirmCountEmail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ConfirmCountEmailBO bo = confirmCountEmailAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ConfirmCountEmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 分配及确认汇总设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ConfirmCountEmailTO to, BindingResult result) throws ActException {
        try {
            confirmCountEmailAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 分配及确认汇总设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            confirmCountEmailAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 分配及确认汇总设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ConfirmCountEmailDTO dto) throws ActException {
        try {
            return ActResult.initialize(confirmCountEmailAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 定时发送邮件
     *
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/send")
    public Result send() throws ActException {
        try {
            confirmCountEmailAPI.send();
            return new ActResult("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}