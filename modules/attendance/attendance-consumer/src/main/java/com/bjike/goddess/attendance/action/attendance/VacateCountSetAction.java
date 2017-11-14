package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.VacateCountSetAPI;
import com.bjike.goddess.attendance.bo.VacateCountSetBO;
import com.bjike.goddess.attendance.dto.VacateCountSetDTO;
import com.bjike.goddess.attendance.to.VacateCountSetTO;
import com.bjike.goddess.attendance.vo.VacateCountSetVO;
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
 * 请假汇总通报设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:19 ]
 * @Description: [ 请假汇总通报设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("vacatecountset")
public class VacateCountSetAction {
    @Autowired
    private VacateCountSetAPI vacateCountSetAPI;

    /**
     * 列表
     *
     * @param dto 请假汇总通报设置数据传输
     * @return class VacateCountSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(VacateCountSetDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<VacateCountSetBO> list = vacateCountSetAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, VacateCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 请假汇总通报设置传输对象
     * @return class VacateCountSetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) VacateCountSetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            VacateCountSetBO bo = vacateCountSetAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, VacateCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 请假汇总通报设置id
     * @return class VacateCountSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/vacate/count/set/{id}")
    public Result VacateCountSet(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            VacateCountSetBO bo = vacateCountSetAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, VacateCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 请假汇总通报设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) VacateCountSetTO to, BindingResult result) throws ActException {
        try {
            vacateCountSetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 请假汇总通报设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            vacateCountSetAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 请假汇总通报设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(VacateCountSetDTO dto) throws ActException {
        try {
            return ActResult.initialize(vacateCountSetAPI.count(dto));
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
            vacateCountSetAPI.send();
            return new ActResult("aa");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}