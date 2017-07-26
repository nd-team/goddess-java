package com.bjike.goddess.projectroyalty.action.projectroyalty;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.api.TargetAuotaAPI;
import com.bjike.goddess.projectroyalty.dto.TargetAuotaDTO;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.TargetAuotaTO;
import com.bjike.goddess.projectroyalty.vo.TargetAuotaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目提成目标定额
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:27 ]
 * @Description: [ 项目提成目标定额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("targetauota")
public class TargetAuotaAction {

    @Autowired
    private TargetAuotaAPI targetAuotaAPI;


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

            Boolean isHasPermission = targetAuotaAPI.guidePermission(guidePermissionTO);
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
     * 保存项目提成目标定额
     *
     * @param to 项目提成目标定额传输对象
     * @return class TargetAuotaVO
     * @version v1
     */
    @PostMapping("v1/target/save")
    public Result targetSave(@Validated(ADD.class) TargetAuotaTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetAuotaAPI.targetSave(to), TargetAuotaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存项目提成实际定额
     *
     * @param to 项目提成目标定额传输对象
     * @return class TargetAuotaVO
     * @version v1
     */
    @PostMapping("v1/actual/save")
    public Result actualSave(@Validated(ADD.class) TargetAuotaTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetAuotaAPI.actualSave(to), TargetAuotaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 项目提成目标定额传输对象
     * @return class TargetAuotaVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) TargetAuotaTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetAuotaAPI.update(to), TargetAuotaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 项目提成目标定额数据id
     * @return class TargetAuotaVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetAuotaAPI.delete(id), TargetAuotaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取业务提成定额数据
     *
     * @param id 项目提成目标定额数据id
     * @return class TargetAuotaVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetAuotaAPI.getById(id), TargetAuotaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成目标定额列表
     *
     * @param dto 项目提成目标定额数据传输对象
     * @return class TargetAuotaVO
     * @version v1
     */
    @GetMapping("v1/target/maps")
    public Result targetMaps(TargetAuotaDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetAuotaAPI.targetMaps(dto), TargetAuotaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成实际定额列表
     *
     * @param dto 项目提成目标定额数据传输对象
     * @return class TargetAuotaVO
     * @version v1
     */
    @GetMapping("v1/actual/maps")
    public Result actualMaps(TargetAuotaDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetAuotaAPI.actualMaps(dto), TargetAuotaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成目标定额总条数
     *
     * @version v1
     */
    @GetMapping("v1/target/getTotal")
    public Result getTargetTotal() throws ActException {
        try {
            return ActResult.initialize(targetAuotaAPI.getTargetTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成实际定额总条数
     *
     * @version v1
     */
    @GetMapping("v1/actual/getTotal")
    public Result getActualTotal() throws ActException {
        try {
            return ActResult.initialize(targetAuotaAPI.getActualTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}