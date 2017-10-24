package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.LeaseCarCostAPI;
import com.bjike.goddess.dispatchcar.dto.LeaseCarCostDTO;
import com.bjike.goddess.dispatchcar.to.LeaseCarCostTO;
import com.bjike.goddess.dispatchcar.vo.LeaseCarCostVO;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 租车费用基本信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-13 10:55 ]
 * @Description: [ 租车费用基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("leasecarcost")
public class LeaseCarCostAct {

//    @Autowired
//    private LeaseCarCostAPI leaseCarCostAPI;
//
//    /**
//     * 查询总记录数
//     *
//     * @param dto 查询条件
//     * @version v1
//     */
//    @GetMapping("v1/count")
//    public Result count(LeaseCarCostDTO dto) throws ActException {
//        try {
//            Long count = leaseCarCostAPI.count(dto);
//            return ActResult.initialize(count);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 根据id查询租车费用基本信息
//     *
//     * @param id 租车费用基本信息id
//     * @return class LeaseCarCostVO
//     * @version v1
//     */
//    @GetMapping("v1/find/{id}")
//    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
//        try {
//            LeaseCarCostVO vo = BeanTransform.copyProperties(leaseCarCostAPI.findById(id), LeaseCarCostVO.class, request);
//            return ActResult.initialize(vo);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 新增租车费用基本信息
//     *
//     * @param to 租车费用基本信息
//     * @return class LeaseCarCostVO
//     * @version v1
//     */
//    @PostMapping("v1/add")
//    public Result add(@Validated({ADD.class}) LeaseCarCostTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            LeaseCarCostVO vo = BeanTransform.copyProperties(leaseCarCostAPI.addModel(to), LeaseCarCostVO.class);
//            return ActResult.initialize(vo);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 编辑租车费用基本信息
//     *
//     * @param to 租车费用基本信息
//     * @return class LeaseCarCostVO
//     * @version v1
//     */
//    @PutMapping("v1/edit")
//    public Result edit(@Validated({EDIT.class}) LeaseCarCostTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            LeaseCarCostVO vo = BeanTransform.copyProperties(leaseCarCostAPI.editModel(to), LeaseCarCostVO.class);
//            return ActResult.initialize(vo);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 删除租车费用基本信息
//     *
//     * @param id 租车费用基本信息id
//     * @version v1
//     */
//    @PatchMapping("v1/delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            leaseCarCostAPI.delete(id);
//            return new ActResult("删除成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 列表分页查询
//     *
//     * @param dto 分页条件
//     * @return class LeaseCarCostVO
//     * @version v1
//     */
//    @GetMapping("v1/list")
//    public Result pageList(LeaseCarCostDTO dto, HttpServletRequest request) throws ActException {
//        try {
//            List<LeaseCarCostVO> voList = BeanTransform.copyProperties(leaseCarCostAPI.pageList(dto), LeaseCarCostVO.class, request);
//            return ActResult.initialize(voList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 查询所有地区
//     * @return class AreaVO
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/find/area")
//    public Result findArea() throws ActException{
//        try {
//            List<AreaBO> boList = leaseCarCostAPI.findArea();
//            List<AreaVO> voList = BeanTransform.copyProperties(boList,AreaVO.class);
//            return ActResult.initialize(voList);
//        }catch (SerException e){
//            throw new ActException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 查询所有未冻结部门
//     * @return class OpinionVO
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/find/department")
//    public Result findDepartment() throws ActException{
//        try {
//            List<OpinionBO> boList = leaseCarCostAPI.findDeapartment();
//            List<OpinionVO> voList = BeanTransform.copyProperties(boList,OpinionVO.class);
//            return ActResult.initialize(voList);
//        }catch (SerException e){
//            throw new ActException(e.getMessage());
//        }
//    }


}