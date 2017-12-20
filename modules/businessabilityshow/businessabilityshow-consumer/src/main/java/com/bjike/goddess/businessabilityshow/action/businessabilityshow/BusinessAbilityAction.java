package com.bjike.goddess.businessabilityshow.action.businessabilityshow;

import com.bjike.goddess.businessabilityshow.api.BusinessAbilityAPI;
import com.bjike.goddess.businessabilityshow.bo.BusinessAbilityBO;
import com.bjike.goddess.businessabilityshow.dto.BusinessAbilityDTO;
import com.bjike.goddess.businessabilityshow.excel.BusinessAbilityExcel;
import com.bjike.goddess.businessabilityshow.to.BusinessAbilityTO;
import com.bjike.goddess.businessabilityshow.to.GuidePermissionTO;
import com.bjike.goddess.businessabilityshow.vo.BusinessAbilityVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目合同基本信息
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-16 02:50 ]
 * @Description: [ 项目合同基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessability")
public class BusinessAbilityAction extends BaseFileAction{

    @Autowired
    BusinessAbilityAPI businessAbilityAPI;

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

            Boolean isHasPermission = businessAbilityAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取商业能力展示列表
     *
     * @param dto dto
     * @return class BusinessAbilityVO
     * @version v1
     */
    @GetMapping("v1/list")
    @LoginAuth
    public Result listBusinessAbility(BusinessAbilityDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<BusinessAbilityBO> bos = businessAbilityAPI.list(dto);
            List<BusinessAbilityVO> vos = BeanTransform.copyProperties(bos, BusinessAbilityVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取商业能力展示总数
     *
     * @param dto dto
     * @version v1
     */
    @GetMapping("v1/list")
    @LoginAuth
    public Result countBusinessAbility(BusinessAbilityDTO dto, BindingResult bindingResult) throws ActException {
        try {
            Long count = businessAbilityAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取单个商业能力
     *
     * @param id id
     * @return class BusinessAbilityVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    @LoginAuth
    public Result getBusinessAbility(@PathVariable String id) throws ActException {
        try {
            BusinessAbilityBO bo = businessAbilityAPI.getOne(id);
            BusinessAbilityVO vo = BeanTransform.copyProperties(bo, BusinessAbilityVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加商业能力
     *
     * @param to　to
     * @version v1
     */
    @PostMapping("v1/add")
    @LoginAuth
    public Result addBusinessAbility(BusinessAbilityTO to, BindingResult bindingResult) throws ActException {
        try {
            businessAbilityAPI.add(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改商业能力
     *
     * @param to to
     * @version v1
     */
    @PostMapping("v1/edit")
    @LoginAuth
    public Result editBusinessAbility(BusinessAbilityTO to, BindingResult bindingResult) throws ActException {
        try {
            businessAbilityAPI.update(to);
            return ActResult.initialize("update success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除商业能力
     *
     * @param id id
     * @version v1
     */
    @PostMapping("v1/delete/{id}")
    @LoginAuth
    public Result deleteBusinessAbility(@PathVariable String id, BindingResult bindingResult) throws ActException {
        try {
            businessAbilityAPI.delete(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<BusinessAbilityExcel> tos = ExcelUtil.excelToClazz(is, BusinessAbilityExcel.class, excel);
            List<BusinessAbilityTO> tocs = new ArrayList<>();
            for (BusinessAbilityExcel str : tos) {
                BusinessAbilityTO to = BeanTransform.copyProperties(str, BusinessAbilityTO.class);
                tocs.add(to);
            }
            //注意序列化
            businessAbilityAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto 条件
     * @des 导出商业能力
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(@Validated() BusinessAbilityDTO dto, HttpServletResponse response, BindingResult
            result) throws ActException {
        try {
            String fileName = "商业能力展示.xlsx";
            super.writeOutFile(response, businessAbilityAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }





}