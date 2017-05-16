package com.bjike.goddess.managefee.action.managefee;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managefee.api.OutFeeAPI;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.to.OutFeeTO;
import com.bjike.goddess.managefee.vo.OutFeeVO;
import com.bjike.goddess.managefee.vo.OutFeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 外包费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:39 ]
 * @Description: [ 外包费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("outfee")
public class OutFeeAction {

    @Autowired
    private OutFeeAPI outFeeAPI;

    /**
     *  列表总条数
     *
     * @param outFeeDTO  外包费信息dto
     * @des 获取所有外包费信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OutFeeDTO outFeeDTO) throws ActException {
        try {
            Long count = outFeeAPI.countOutFee(outFeeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个外包费
     *
     * @param id 外包费信息id
     * @des 根据id获取所有外包费信息
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            OutFeeVO outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.getOneById( id ), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 外包费列表
     *
     * @param outFeeDTO 外包费信息dto
     * @param request 前端过滤参数
     * @des 获取所有外包费信息
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListOutFee(OutFeeDTO outFeeDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.listOutFee(outFeeDTO), OutFeeVO.class, request);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加外包费
     *
     * @param outFeeTO 外包费基本信息数据to
     * @des 添加外包费
     * @return  class OutFeeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addOutFee(@Validated(OutFeeTO.TestAdd.class) OutFeeTO outFeeTO, BindingResult bindingResult) throws ActException {
        try {
            OutFeeBO outFeeBO1 = outFeeAPI.addOutFee(outFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(outFeeBO1,OutFeeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑外包费
     *
     * @param outFeeTO 外包费基本信息数据bo
     * @des 编辑外包费
     * @return  class OutFeeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editOutFee(@Validated(OutFeeTO.TestAdd.class) OutFeeTO outFeeTO) throws ActException {
        try {
            OutFeeBO outFeeBO1 = outFeeAPI.editOutFee(outFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(outFeeBO1,OutFeeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除外包费信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteOutFee(@PathVariable String id) throws ActException {
        try {
            outFeeAPI.deleteOutFee(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 根据地区汇总
     *
     * @param outFeeDTO 外包费信息dto
     * @des 根据地区汇总
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/ctArea")
    public Result collectCom(OutFeeDTO outFeeDTO) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectArea(outFeeDTO), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据项目组汇总
     *
     * @param outFeeDTO 外包费信息dto
     * @des 根据项目组汇总
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/ctGroup")
    public Result ctGroup(OutFeeDTO outFeeDTO) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectGroup(outFeeDTO), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目汇总
     *
     * @param outFeeDTO 外包费信息dto
     * @des 根据项目汇总
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/ctProject")
    public Result collectPro(OutFeeDTO outFeeDTO) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectProject(outFeeDTO), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据类别汇总
     *
     * @param outFeeDTO 外包费信息dto
     * @des 根据类别汇总
     * @return  class OutFeeVO
     * @version v1
     */
    @GetMapping("v1/ctType")
    public Result ctType(OutFeeDTO outFeeDTO) throws ActException {
        try {
            List<OutFeeVO> outFeeVOList = BeanTransform.copyProperties(
                    outFeeAPI.collectType(outFeeDTO), OutFeeVO.class);
            return ActResult.initialize(outFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有年份
     *
     * @version v1
     */
    @GetMapping("v1/listYear")
    public Result yearList( ) throws ActException {
        try {
            List<String> list = outFeeAPI.yearList( );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总地区
     *
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result areaList() throws ActException {
        try {
            List<String> list = outFeeAPI.areaList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目组
     *
     * @version v1
     */
    @GetMapping("v1/listGroup")
    public Result groupList() throws ActException {
        try {
            List<String> list = outFeeAPI.groupList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目
     *
     * @version v1
     */
    @GetMapping("v1/listProject")
    public Result projectList() throws ActException {
        try {
            List<String> list = outFeeAPI.projectList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    

}