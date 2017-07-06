package com.bjike.goddess.projectcost.action.projectcost;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcost.api.CostControlAPI;
import com.bjike.goddess.projectcost.dto.CostControlDTO;
import com.bjike.goddess.projectcost.to.CostControlTO;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.to.GuidePermissionTO;
import com.bjike.goddess.projectcost.vo.CostControlVO;
import com.bjike.goddess.projectcost.vo.HistogramVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目成本控制
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目成本控制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("costcontrol")
public class CostControlAct {

    @Autowired
    private CostControlAPI costControlAPI;

    /**
     * 保存
     *
     * @param to 项目成本控制传输对象
     * @return class CostControlVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CostControlTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costControlAPI.save(to), CostControlVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 项目成本控制传输对象
     * @return class CostControlVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CostControlTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costControlAPI.update(to), CostControlVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 项目成本控制数据id
     * @return class CostControlVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costControlAPI.delete(id), CostControlVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改实际收入
     *
     * @param to 项目成本控制传输对象
     * @return class CostControlVO
     * @version v1
     */
    @PutMapping("v1/updateActual/{id}")
    public Result updateActual(CostControlTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costControlAPI.updateActual(to), CostControlVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 项目成本控制数据传输对象
     * @return class CostControlVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CostControlDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costControlAPI.maps(dto), CostControlVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询项目成本控制数据
     *
     * @param id 项目成本控制数据id
     * @return class CostControlVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costControlAPI.getById(id), CostControlVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @return class CostControlVO
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(costControlAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 柱状图汇总数据
     *
     * @param to
     * @return class HistogramVO
     * @version v1
     */
    @GetMapping("v1/histogramCollect")
    public Result histogramCollect(FindTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costControlAPI.histogramCollect(to), HistogramVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据查询条件查询成本控制数据
     *
     * @param to 查询条件传输对象
     * @return class CostControlVO
     * @version v1
     */
    @GetMapping("v1/findByTo")
    public Result findByTo(FindTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costControlAPI.findByTo(to), CostControlVO.class, request));
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

            Boolean isHasPermission = costControlAPI.guidePermission(guidePermissionTO);
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

}