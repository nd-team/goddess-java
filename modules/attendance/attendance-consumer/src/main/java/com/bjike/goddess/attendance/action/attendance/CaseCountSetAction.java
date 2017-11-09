package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.CaseCountSetAPI;
import com.bjike.goddess.attendance.bo.CaseCountSetBO;
import com.bjike.goddess.attendance.dto.CaseCountSetDTO;
import com.bjike.goddess.attendance.to.CaseCountSetTO;
import com.bjike.goddess.attendance.vo.CaseCountSetVO;
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
 * 考勤情况汇总设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-19 06:18 ]
 * @Description: [ 考勤情况汇总设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("casecountset")
public class CaseCountSetAction {
    @Autowired
    private CaseCountSetAPI caseCountSetAPI;

    /**
     * 列表
     *
     * @param dto 考勤情况汇总设置数据传输
     * @return class CaseCountSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CaseCountSetDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CaseCountSetBO> list = caseCountSetAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CaseCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 考勤情况汇总设置传输对象
     * @return class CaseCountSetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CaseCountSetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            CaseCountSetBO bo = caseCountSetAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CaseCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 考勤情况汇总设置id
     * @return class CaseCountSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/case/count/set/{id}")
    public Result CaseCountSet(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CaseCountSetBO bo = caseCountSetAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CaseCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 考勤情况汇总设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) CaseCountSetTO to, BindingResult result) throws ActException {
        try {
            caseCountSetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 考勤情况汇总设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            caseCountSetAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 考勤情况汇总设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CaseCountSetDTO dto) throws ActException {
        try {
            return ActResult.initialize(caseCountSetAPI.count(dto));
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
            caseCountSetAPI.send();
            return new ActResult("aa");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}