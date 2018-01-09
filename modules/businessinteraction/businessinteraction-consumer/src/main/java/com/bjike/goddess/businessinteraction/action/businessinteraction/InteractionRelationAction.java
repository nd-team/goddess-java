package com.bjike.goddess.businessinteraction.action.businessinteraction;

import com.bjike.goddess.businessinteraction.api.InteractionRelationAPI;
import com.bjike.goddess.businessinteraction.bo.InteractionRelationBO;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.businessinteraction.excel.InteractionRelationExcel;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.InteractionRelationTO;
import com.bjike.goddess.businessinteraction.vo.InteractionRelationVO;
import com.bjike.goddess.common.api.entity.ADD;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 08:58 ]
 * @Description: [ 公司信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("interactionrelation")
public class InteractionRelationAction extends BaseFileAction{
    @Autowired
    private InteractionRelationAPI interactionRelationAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = interactionRelationAPI.guidePermission(guidePermissionTO);
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
     * @param interactionRelationDTO 公司信息
     * @des 获取所有公司信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InteractionRelationDTO interactionRelationDTO) throws ActException {
        try {
            Long count = interactionRelationAPI.countInter(interactionRelationDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个公司信息
     *
     * @param id 公司信息id
     * @return class InteractionRelationVO
     * @des 根据id获取公司信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            InteractionRelationVO interactionRelationVO = BeanTransform.copyProperties(
                    interactionRelationAPI.getOneById(id), InteractionRelationVO.class);
            return ActResult.initialize(interactionRelationVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司信息列表
     *
     * @param interactionRelationDTO 公司信息dto
     * @return class InteractionRelationVO
     * @des 获取所有公司信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListMoneyPerpare(InteractionRelationDTO interactionRelationDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<InteractionRelationVO> MoneyPerpareVOList = BeanTransform.copyProperties(
                    interactionRelationAPI.listIntera(interactionRelationDTO), InteractionRelationVO.class, request);
            return ActResult.initialize(MoneyPerpareVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加公司信息
     *
     * @param interactionRelationTO 公司信息to
     * @return class InteractionRelationVO
     * @des 添加公司信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBaseInfoManage(@Validated({ADD.class}) InteractionRelationTO interactionRelationTO, BindingResult result) throws ActException {
        try {
            InteractionRelationBO interactionRelationBO = interactionRelationAPI.addIntera(interactionRelationTO);
            return ActResult.initialize(BeanTransform.copyProperties(interactionRelationBO, InteractionRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑公司信息
     *
     * @param interactionRelationTO 公司信息数据bo
     * @return class InteractionRelationVO
     * @des 编辑公司信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editBaseInfoManage(@Validated({EDIT.class}) InteractionRelationTO interactionRelationTO, BindingResult result) throws ActException {
        try {
            InteractionRelationBO interactionRelationBO = interactionRelationAPI.editIntera(interactionRelationTO);
            return ActResult.initialize(BeanTransform.copyProperties(interactionRelationBO, InteractionRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除公司信息
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBaseInfoManage(@PathVariable String id) throws ActException {
        try {
            interactionRelationAPI.deleteIntera(id);
            return new ActResult("delete success!");
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
            List<InteractionRelationExcel> tos = ExcelUtil.excelToClazz(is, InteractionRelationExcel.class, excel);
            List<InteractionRelationTO> tocs = new ArrayList<>();
            for (InteractionRelationExcel str : tos) {
                InteractionRelationTO interactionRelationTO = BeanTransform.copyProperties(str, InteractionRelationTO.class);
                tocs.add(interactionRelationTO);
            }
            //注意序列化
            interactionRelationAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出公司信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "公司信息.xlsx";
            super.writeOutFile(response, interactionRelationAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * excel模板下载
     *
     * @des 下载模板公司信息
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "公司信息模板.xlsx";
            super.writeOutFile(response, interactionRelationAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}