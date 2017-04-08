package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.DispatchSheetAPI;
import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.to.DispatchSheetTO;
import com.bjike.goddess.businessproject.vo.DispatchSheetVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 商务项目派工单信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dispatchsheet")
public class DispatchSheetAction {
    @Autowired
    private DispatchSheetAPI dispatchSheetAPI;

    /**
     * 项目派工单列表
     *
     * @param dispatchSheetDTO 项目派工单信息dto
     * @return class DispatchSheetVO
     * @des 获取所有项目派工单信息
     * @version v1
     */
    @GetMapping("v1/listDispatchSheet")
    public Result findListDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws ActException {
        try {
            List<DispatchSheetVO> dispatchSheetVOList = BeanTransform.copyProperties(
                    dispatchSheetAPI.listDispatchSheet(dispatchSheetDTO), DispatchSheetVO.class, true);
            return ActResult.initialize(dispatchSheetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param dispatchSheetDTO 项目签订与立项信息dto
     * @return class DispatchSheetVO
     * @des 分页搜索获取所有项目签订与立项
     * @version v1
     */
    @GetMapping("v1/searchDispatchSheet")
    public Result searchListDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws ActException {
        try {
            List<DispatchSheetVO> baseInfoManageVOList = BeanTransform.copyProperties(
                    dispatchSheetAPI.searchDispatchSheet(dispatchSheetDTO), DispatchSheetVO.class, true);
            return ActResult.initialize(baseInfoManageVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目派工单
     *
     * @param dispatchSheetTO 项目派工单基本信息数据to
     * @return class DispatchSheetVO
     * @des 添加项目派工单
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addDispatchSheet(@Validated DispatchSheetTO dispatchSheetTO) throws ActException {
        try {
            DispatchSheetBO dispatchSheetBO1 = dispatchSheetAPI.addDispatchSheet(dispatchSheetTO);
            return ActResult.initialize(BeanTransform.copyProperties(dispatchSheetBO1, DispatchSheetVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目派工单
     *
     * @param dispatchSheetTO 项目派工单基本信息数据bo
     * @return class DispatchSheetVO
     * @des 添加项目派工单
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editDispatchSheet(@Validated DispatchSheetTO dispatchSheetTO) throws ActException {
        try {
            DispatchSheetBO dispatchSheetBO1 = dispatchSheetAPI.editDispatchSheet(dispatchSheetTO);
            return ActResult.initialize(BeanTransform.copyProperties(dispatchSheetBO1, DispatchSheetVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目派工单信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteDispatchSheet(@PathVariable String id) throws ActException {
        try {
            dispatchSheetAPI.deleteDispatchSheet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}