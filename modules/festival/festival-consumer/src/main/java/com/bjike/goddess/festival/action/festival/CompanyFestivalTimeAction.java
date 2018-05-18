package com.bjike.goddess.festival.action.festival;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.api.CompanyFestivalTimeAPI;
import com.bjike.goddess.festival.bo.CompanyFestivalTimeBO;
import com.bjike.goddess.festival.dto.CompanyFestivalTimeDTO;
import com.bjike.goddess.festival.excel.SonPermissionObject;
import com.bjike.goddess.festival.to.CompanyFestivalTimeTO;
import com.bjike.goddess.festival.to.GuidePermissionTO;
import com.bjike.goddess.festival.vo.CompanyFestivalTimeVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司放假时间安排
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("companyfestivaltime")
public class CompanyFestivalTimeAction {

    @Autowired
    private CompanyFestivalTimeAPI companyFestivalTimeAPI;


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

            Boolean isHasPermission = companyFestivalTimeAPI.guidePermission(guidePermissionTO);
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
     *  公司放假时间安排列表总条数
     *
     * @param companyFestivalTimeDTO  公司放假时间安排信息dto
     * @des 获取所有公司放假时间安排信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws ActException {
        try {
            Long count = companyFestivalTimeAPI.countCompanyFestivalTime(companyFestivalTimeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个放假时间安排
     *
     * @param id 放假方案id
     * @des 根据id获取一个放假时间安排
     * @return  class CompanyFestivalTimeVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CompanyFestivalTimeVO companyFestivalTimeVO = BeanTransform.copyProperties(
                    companyFestivalTimeAPI.getOneById(id), CompanyFestivalTimeVO.class);
            return ActResult.initialize(companyFestivalTimeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司放假时间安排列表
     *
     * @param companyFestivalTimeDTO 公司放假时间安排信息dto
     * @des 获取所有公司放假时间安排信息
     * @return  class CompanyFestivalTimeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CompanyFestivalTimeVO> companyFestivalTimeVOList = BeanTransform.copyProperties(
                    companyFestivalTimeAPI.listCompanyFestivalTime(companyFestivalTimeDTO), CompanyFestivalTimeVO.class, request);
            return ActResult.initialize(companyFestivalTimeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司放假时间安排
     *
     * @param companyFestivalTimeTO 公司放假时间安排基本信息数据to
     * @des 添加公司放假时间安排
     * @return  class CompanyFestivalTimeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCompanyFestivalTime(@Validated({CompanyFestivalTimeTO.TESTAddAndEdit.class}) CompanyFestivalTimeTO companyFestivalTimeTO, BindingResult bindingResult) throws ActException {
        try {
            CompanyFestivalTimeBO companyFestivalTimeBO1 = companyFestivalTimeAPI.addCompanyFestivalTime(companyFestivalTimeTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyFestivalTimeBO1,CompanyFestivalTimeVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑公司放假时间安排
     *
     * @param companyFestivalTimeTO 公司放假时间安排基本信息数据bo
     * @des 添加公司放假时间安排
     * @return  class CompanyFestivalTimeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCompanyFestivalTime(@Validated({CompanyFestivalTimeTO.TESTAddAndEdit.class}) CompanyFestivalTimeTO companyFestivalTimeTO) throws ActException {
        try {
            CompanyFestivalTimeBO companyFestivalTimeBO1 = companyFestivalTimeAPI.editCompanyFestivalTime(companyFestivalTimeTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyFestivalTimeBO1,CompanyFestivalTimeVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除公司放假时间安排信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCompanyFestivalTime(@PathVariable String id) throws ActException {
        try {
            companyFestivalTimeAPI.deleteCompanyFestivalTime(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     *  查看公司放假时间安排总条数
     *
     * @param companyFestivalTimeDTO  公司放假时间安排信息dto
     * @des 获取所有公司放假时间安排信息总条数
     * @version v1
     */
    @GetMapping("v1/countByName")
    public Result countByName(@Validated(CompanyFestivalTimeDTO.TESTGetOne.class) CompanyFestivalTimeDTO companyFestivalTimeDTO) throws ActException {
        try {
            Long count = companyFestivalTimeAPI.countFestivalTimeByName(companyFestivalTimeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看公司放假时间安排
     *
     * @param companyFestivalTimeDTO 公司放假时间安排dto
     * @des 查看公司放假时间安排
     * @return  class CompanyFestivalTimeVO
     * @version v1
     */
    @GetMapping("v1/getComDetail")
    public Result getCompanyFestivalTimeDetail (@Validated(CompanyFestivalTimeDTO.TESTGetOne.class) CompanyFestivalTimeDTO companyFestivalTimeDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CompanyFestivalTimeVO> companyFestivalTimeVOS = BeanTransform.copyProperties(
                    companyFestivalTimeAPI.getCompanyFestivalTime(companyFestivalTimeDTO), CompanyFestivalTimeVO.class, request);
            return ActResult.initialize(companyFestivalTimeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

     /**
     * 获取所有节日名称
     *
     * @des 查看公司放假时间安排获取所有节日名称
     * @version v1
     */
    @GetMapping("v1/listName")
    public Result listName ( ) throws ActException {
        try {
            List<String> list = companyFestivalTimeAPI.listFestivalName(  );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}