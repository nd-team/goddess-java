package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.AuditTimeSetAPI;
import com.bjike.goddess.attendance.bo.AuditTimeSetBO;
import com.bjike.goddess.attendance.dto.AuditTimeSetDTO;
import com.bjike.goddess.attendance.to.AuditTimeSetTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.vo.AuditTimeSetVO;
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
 * 审批时间设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:37 ]
 * @Description: [ 审批时间设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("audittimeset")
public class AuditTimeSetAction {
    @Autowired
    private AuditTimeSetAPI auditTimeSetAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = auditTimeSetAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 审批时间设置数据传输
     * @return class AuditTimeSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AuditTimeSetDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AuditTimeSetBO> list = auditTimeSetAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AuditTimeSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 审批时间设置传输对象
     * @return class AuditTimeSetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AuditTimeSetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AuditTimeSetBO bo = auditTimeSetAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AuditTimeSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 审批时间设置id
     * @return class AuditTimeSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/audit/time/set/{id}")
    public Result AuditTimeSet(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AuditTimeSetBO bo = auditTimeSetAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AuditTimeSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 审批时间设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AuditTimeSetTO to, BindingResult result) throws ActException {
        try {
            auditTimeSetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 审批时间设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            auditTimeSetAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 审批时间设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AuditTimeSetDTO dto) throws ActException {
        try {
            return ActResult.initialize(auditTimeSetAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}