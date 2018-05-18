package com.bjike.goddess.moneyprepare.action.moneyprepare;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyprepare.api.ProportionAPI;
import com.bjike.goddess.moneyprepare.bo.ProportionBO;
import com.bjike.goddess.moneyprepare.dto.ProportionDTO;
import com.bjike.goddess.moneyprepare.to.GuidePermissionTO;
import com.bjike.goddess.moneyprepare.to.ProportionObjectTO;
import com.bjike.goddess.moneyprepare.to.ProportionTO;
import com.bjike.goddess.moneyprepare.vo.ProportionVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 比例分配
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("proportion")
public class ProportionAction {
    @Autowired
    private ProportionAPI proportionAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;


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

            Boolean isHasPermission = proportionAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param proportionDTO 比例分配dto
     * @des 获取所有比例分配总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProportionDTO proportionDTO) throws ActException {
        try {
            Long count = proportionAPI.countProportion(proportionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个比例分配
     *
     * @param id 项目比例分配id
     * @return class ProportionVO
     * @des 根据id获取项目比例分配
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ProportionVO projectCarryVO = BeanTransform.copyProperties(
                    proportionAPI.getOneById(id), ProportionVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目比例分配列表
     *
     * @param proportionDTO 项目比例分配信息dto
     * @return class ProportionVO
     * @des 获取所有项目比例分配信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProportion(ProportionDTO proportionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ProportionBO> list = proportionAPI.listProportion(proportionDTO);
            List<ProportionVO> proportionVOList = new ArrayList<>();
            list.stream().forEach(str -> {
                ProportionVO vo = BeanTransform.copyProperties(str, ProportionVO.class);
                proportionVOList.add(vo);
            });

            return ActResult.initialize(proportionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加项目比例分配
     *
     * @param proportionObjectTO 项目比例分配基本信息数据to
     * @return class ProportionVO
     * @des 添加项目比例分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProportion(@Validated(ProportionTO.TestAdd.class) ProportionObjectTO proportionObjectTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProportionBO> proportionBOList = proportionAPI.addProportion(proportionObjectTO);
            return ActResult.initialize(BeanTransform.copyProperties(proportionBOList, ProportionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目比例分配
     *
     * @param proportionTO 项目比例分配基本信息数据bo
     * @return class ProportionVO
     * @des 添加项目比例分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editProportion(@Validated(ProportionTO.TestAdd.class) ProportionTO proportionTO, BindingResult bindingResult) throws ActException {
        try {
            ProportionBO proportionBO1 = proportionAPI.editProportion(proportionTO);
            return ActResult.initialize(BeanTransform.copyProperties(proportionBO1, ProportionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目比例分配信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProportion(@PathVariable String id) throws ActException {
        try {
            proportionAPI.deleteProportion(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获得全部的项目组
     *
     * @des 获得全部的项目组
     * @return class OpinionBO
     * @version v1
     */
    @GetMapping("v1/getDepartment")
    public Result getDepartment() throws ActException {
        try {
            List<OpinionBO> list = departmentDetailAPI.findThawOpinion();
            return ActResult.initialize(BeanTransform.copyProperties(list, OpinionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}