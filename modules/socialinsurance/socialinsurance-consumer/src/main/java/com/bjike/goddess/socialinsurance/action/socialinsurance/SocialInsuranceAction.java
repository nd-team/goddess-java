package com.bjike.goddess.socialinsurance.action.socialinsurance;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.socialinsurance.api.SocialInsuranceAPI;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceDTO;
import com.bjike.goddess.socialinsurance.excel.SocialInsuranceExportExcel;
import com.bjike.goddess.socialinsurance.excel.SocialInsuranceImportExcel;
import com.bjike.goddess.socialinsurance.to.GuidePermissionTO;
import com.bjike.goddess.socialinsurance.to.SocialInsuranceTO;
import com.bjike.goddess.socialinsurance.vo.SocialInsuranceVO;
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
 * 社会保险缴费管理
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-21 04:23 ]
 * @Description: [ 社会保险缴费管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("socialinsurance")
public class SocialInsuranceAction extends BaseFileAction{

    @Autowired
    SocialInsuranceAPI socialInsuranceAPI;

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

            Boolean isHasPermission = socialInsuranceAPI.guidePermission(guidePermissionTO);
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
     * 获取社会保险列表
     *
     * @param dto dto
     * @return class SocialInsuranceVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(SocialInsuranceDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<SocialInsuranceBO> bos = socialInsuranceAPI.list(dto);
            List<SocialInsuranceVO> vos = BeanTransform.copyProperties(bos, SocialInsuranceVO.class);
            return ActResult.initialize(vos);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取社会保险总数
     *
     * @param dto dto
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result countBusinessContractProgress(SocialInsuranceDTO dto, BindingResult bindingResult) throws ActException {
        try {
            Long amount = socialInsuranceAPI.count(dto);
            return ActResult.initialize(amount);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加社会保险
     *
     * @param to to
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBusinessContractProgress(SocialInsuranceTO to, BindingResult bindingResult) throws ActException {
        try {

            socialInsuranceAPI.add(to);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改社会保险合同
     *
     * @param to to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result modifyBusinessContractProgress(@Validated(EDIT.class) SocialInsuranceTO to, BindingResult bindingResult) throws ActException {
        try {
            socialInsuranceAPI.update(to);
            return ActResult.initialize(true);
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
            List<SocialInsuranceImportExcel> tos = ExcelUtil.excelToClazz(is, SocialInsuranceImportExcel.class, excel);
            List<SocialInsuranceTO> tocs = new ArrayList<>();
            for (SocialInsuranceImportExcel str : tos) {
                SocialInsuranceTO contractTO = BeanTransform.copyProperties(str, SocialInsuranceTO.class);

                tocs.add(contractTO);
            }
            //注意序列化
            socialInsuranceAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto 条件
     * @des 导出项目实施进度
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(@Validated() SocialInsuranceDTO dto, HttpServletResponse response, BindingResult
            result) throws ActException {
        try {
            String fileName = "商务项目合同实施进度.xlsx";
            super.writeOutFile(response, socialInsuranceAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}