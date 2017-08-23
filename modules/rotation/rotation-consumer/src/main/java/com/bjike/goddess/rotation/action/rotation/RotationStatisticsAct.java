package com.bjike.goddess.rotation.action.rotation;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.api.RotationStatisticsAPI;
import com.bjike.goddess.rotation.dto.RotationStatisticsDTO;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.RotationStatisticsTO;
import com.bjike.goddess.rotation.vo.DetailVO;
import com.bjike.goddess.rotation.vo.RotationStatisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 岗位轮换统计
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:38 ]
 * @Description: [ 岗位轮换统计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("rotationstatistics")
public class RotationStatisticsAct {

    @Autowired
    private RotationStatisticsAPI rotationStatisticsAPI;

    /**
     * 保存
     *
     * @param to 岗位轮换统计传输对象
     * @return class RotationStatisticsVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) RotationStatisticsTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(rotationStatisticsAPI.save(to), RotationStatisticsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 岗位轮换统计传输对象
     * @return class RotationStatisticsVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) RotationStatisticsTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(rotationStatisticsAPI.update(to), RotationStatisticsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 岗位轮换统计数据id
     * @return class RotationStatisticsVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(rotationStatisticsAPI.delete(id), RotationStatisticsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询岗位轮换统计数据
     *
     * @param id 岗位轮换统计数据id
     * @return class RotationStatisticsVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(rotationStatisticsAPI.getById(id), RotationStatisticsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 岗位轮换统计数据传输对象
     * @return class RotationStatisticsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(RotationStatisticsDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(rotationStatisticsAPI.maps(dto), RotationStatisticsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(rotationStatisticsAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = rotationStatisticsAPI.guidePermission(guidePermissionTO);
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
     * 目前岗位情况
     * @return class DetailVO
     * @version v1
     */
    @GetMapping("v1/getDetail")
    public Result getDetail() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(rotationStatisticsAPI.getDetail(), DetailVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}