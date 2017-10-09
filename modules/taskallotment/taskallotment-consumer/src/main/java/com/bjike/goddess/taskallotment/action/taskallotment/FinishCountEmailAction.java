package com.bjike.goddess.taskallotment.action.taskallotment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.api.FinishCountEmailAPI;
import com.bjike.goddess.taskallotment.bo.FinishCountEmailBO;
import com.bjike.goddess.taskallotment.dto.FinishCountEmailDTO;
import com.bjike.goddess.taskallotment.to.FinishCountEmailTO;
import com.bjike.goddess.taskallotment.vo.FinishCountEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 完成情况汇总设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:31 ]
 * @Description: [ 完成情况汇总设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("finishcountemail")
public class FinishCountEmailAction {
    @Autowired
    private FinishCountEmailAPI finishCountEmailAPI;

    /**
     * 列表
     *
     * @param dto 完成情况汇总设置数据传输
     * @return class FinishCountEmailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FinishCountEmailDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FinishCountEmailBO> list = finishCountEmailAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, FinishCountEmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 完成情况汇总设置传输对象
     * @return class FinishCountEmailVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) FinishCountEmailTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            FinishCountEmailBO bo = finishCountEmailAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, FinishCountEmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 完成情况汇总设置id
     * @return class FinishCountEmailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/finishcountemail/{id}")
    public Result FinishCountEmail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FinishCountEmailBO bo = finishCountEmailAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, FinishCountEmailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 完成情况汇总设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) FinishCountEmailTO to, BindingResult result) throws ActException {
        try {
            finishCountEmailAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 完成情况汇总设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            finishCountEmailAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 完成情况汇总设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FinishCountEmailDTO dto) throws ActException {
        try {
            return ActResult.initialize(finishCountEmailAPI.count(dto));
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
            finishCountEmailAPI.send();
            return new ActResult("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}