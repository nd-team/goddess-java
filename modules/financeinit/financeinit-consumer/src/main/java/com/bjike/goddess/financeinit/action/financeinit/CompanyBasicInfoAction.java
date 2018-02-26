package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.CompanyBasicInfoAPI;
import com.bjike.goddess.financeinit.bo.CompanyBasicInfoBO;
import com.bjike.goddess.financeinit.dto.CompanyBasicInfoDTO;
import com.bjike.goddess.financeinit.entity.CompanyBasicInfo;
import com.bjike.goddess.financeinit.to.CompanyBasicInfoTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.vo.CompanyBasicInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * 公司基本信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:08 ]
 * @Description: [ 公司基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("companybasicinfo")
public class CompanyBasicInfoAction extends BaseFileAction{
    @Autowired
    private CompanyBasicInfoAPI companyBasicInfoAPI;
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

            Boolean isHasPermission = companyBasicInfoAPI.guidePermission(guidePermissionTO);
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
     * @param companyBasicInfoDTO 公司基本信息dto
     * @des 获取所有公司基本信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CompanyBasicInfoDTO companyBasicInfoDTO) throws ActException {
        try {
            Long count = companyBasicInfoAPI.countBasicInfo(companyBasicInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个公司基本信息
     *
     * @param id 公司基本信息id
     * @return class CompanyBasicInfoVO
     * @des 根据id获取公司基本信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CompanyBasicInfoVO companyBasicInfoVO = BeanTransform.copyProperties(
                    companyBasicInfoAPI.getOneById(id), CompanyBasicInfoVO.class);
            return ActResult.initialize(companyBasicInfoVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司基本信息列表
     *
     * @param companyBasicInfoDTO 公司基本信息dto
     * @return class CompanyBasicInfoVO
     * @des 获取所有公司基本信息
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(CompanyBasicInfoDTO companyBasicInfoDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CompanyBasicInfoVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    companyBasicInfoAPI.listBaseInfo(companyBasicInfoDTO), CompanyBasicInfoVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司基本信息
     *
     * @param companyBasicInfoTO 公司基本信息数据to
     * @return class CompanyBasicInfoVO
     * @des 添加公司基本信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAccount(@Validated(value = ADD.class) CompanyBasicInfoTO companyBasicInfoTO, BindingResult bindingResult) throws ActException {
        try {
            CompanyBasicInfoBO companyBasicInfoBO = companyBasicInfoAPI.addBaseInfo(companyBasicInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyBasicInfoBO, CompanyBasicInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑公司基本信息
     *
     * @param companyBasicInfoTO 公司基本信息数据bo
     * @return class CompanyBasicInfoVO
     * @des 编辑公司基本信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAccount(@Validated(value = EDIT.class) CompanyBasicInfoTO companyBasicInfoTO, BindingResult bindingResult) throws ActException {
        try {
            CompanyBasicInfoBO companyBasicInfoBO = companyBasicInfoAPI.editBaseInfo(companyBasicInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyBasicInfoBO, CompanyBasicInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除公司基本信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAccount(@PathVariable String id) throws ActException {
        try {
            companyBasicInfoAPI.deleteBaseInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
    /**
     * 导出excel
     *
     * @des 导出公司基本信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport( HttpServletResponse response) throws ActException {
        try {
            String fileName = LocalDate.now()+"公司基本信息.xlsx";
            super.writeOutFile(response, companyBasicInfoAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}