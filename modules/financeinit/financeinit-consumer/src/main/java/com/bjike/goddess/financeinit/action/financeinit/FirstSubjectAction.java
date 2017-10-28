package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.FirstSubjectAPI;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.dto.FirstSubjectDTO;
import com.bjike.goddess.financeinit.excel.FirstSubjectTemplateExport;
import com.bjike.goddess.financeinit.excel.SonPermissionObject;
import com.bjike.goddess.financeinit.to.FirstSubjectTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.vo.FirstSubjectVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.apache.commons.lang3.StringUtils;
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
 * 一级科目
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("firstsubject")
public class FirstSubjectAction extends BaseFileAction {


    @Autowired
    private FirstSubjectAPI firstSubjectAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;


    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = firstSubjectAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

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

            Boolean isHasPermission = firstSubjectAPI.guidePermission(guidePermissionTO);
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
     * @param customerBaseInfoDTO 一级科目信息dto
     * @des 获取所有一级科目信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FirstSubjectDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = firstSubjectAPI.countFirstSubject(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个一级科目
     *
     * @param id 项目一级科目信息id
     * @des 根据id获取项目一级科目信息
     * @return  class FirstSubjectVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            FirstSubjectVO projectCarryVO = BeanTransform.copyProperties(
                    firstSubjectAPI.getOneById(id), FirstSubjectVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一级科目列表
     *
     * @param firstSubjectDTO 一级科目信息dto
     * @return class FirstSubjectVO
     * @des 获取所有一级科目信息
     * @version v1
     */
    @GetMapping("v1/listFirstSubject")
    public Result findListFirstSubject(FirstSubjectDTO firstSubjectDTO, BindingResult bindingResult , HttpServletRequest request) throws ActException {
        try {
            List<FirstSubjectVO> firstSubjectVOList = BeanTransform.copyProperties(
                    firstSubjectAPI.listFirstSubject(firstSubjectDTO), FirstSubjectVO.class , request);
            return ActResult.initialize(firstSubjectVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 所有一级科目名称
     *
     * @des 获取所有一级科目信息
     * @version v1
     */
    @GetMapping("v1/listAllFirst")
    public Result listAllFirst( ) throws ActException {
        try {
            List<String> firstSubjectVOList = firstSubjectAPI.listAllFirst( );
            return ActResult.initialize(firstSubjectVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加一级科目
     *
     * @param firstSubjectTO 一级科目基本信息数据to
     * @return class FirstSubjectVO
     * @des 添加一级科目
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addFirstSubject(@Validated FirstSubjectTO firstSubjectTO, BindingResult bindingResult) throws ActException {
        try {
            FirstSubjectBO firstSubjectBO1 = firstSubjectAPI.addFirstSubject(firstSubjectTO);
            return ActResult.initialize(BeanTransform.copyProperties(firstSubjectBO1, FirstSubjectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑一级科目
     *
     * @param firstSubjectTO 一级科目基本信息数据bo
     * @return class FirstSubjectVO
     * @des 添加一级科目
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editFirstSubject(@Validated FirstSubjectTO firstSubjectTO) throws ActException {
        try {
            FirstSubjectBO firstSubjectBO1 = firstSubjectAPI.editFirstSubject(firstSubjectTO);
            return ActResult.initialize(BeanTransform.copyProperties(firstSubjectBO1, FirstSubjectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除一级科目信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteFirstSubject(@PathVariable String id) throws ActException {
        try {
            firstSubjectAPI.deleteFirstSubject(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
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
            List<FirstSubjectTemplateExport> tos = ExcelUtil.excelToClazz(is, FirstSubjectTemplateExport.class, excel);
            List<FirstSubjectTO> tocs = new ArrayList<>();
            for (FirstSubjectTemplateExport str : tos) {
                if(StringUtils.isBlank(str.getCategory()) ){
                    throw new ActException("级别所属类别不能为空，请检查导入的数据，导入失败");
                }
                if(StringUtils.isBlank(str.getName()) ){
                    throw new ActException("会计科目名称不能为空，请检查导入的数据，导入失败");
                }
                FirstSubjectTO firstSubjectTO = BeanTransform.copyProperties(str, FirstSubjectTO.class, "category");
                firstSubjectTO.setCategory( convertCategory( str.getCategory() ));
                tocs.add(firstSubjectTO);
            }
            //注意序列化
            firstSubjectAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private String convertCategory(String categoryName ) throws ActException {
        String category = "";
        if (null == categoryName) {
            throw new ActException("级别所属类别填写不正确,导入失败,正确填写方式（已签订/未签订）");
        }
        //  case "资产类":"负债类":"共同类": "权益类":"成本类":"损益类":
        switch (categoryName) {
            case "资产类":
                category = "资产类";
                break;
            case "负债类":
                category = "负债类";
                break;
            case "共同类":
                category = "共同类";
                break;
            case "权益类":
                category = "权益类";
                break;
            case "成本类":
                category = "成本类";
                break;
            case "损益类":
                category = "损益类";
                break;
            default:
                throw new ActException("级别所属类别填写不正确,导入失败,正确填写方式（资产类/负债类/共同类/权益类/成本类/损益类）");
        }
        return category;
    }


    /**
     * 多选导出
     *
     * @param dto 级别所属类别数组
     * @des 根据级别所属类别导出所有一级科目信息
     * @version v1
     */
    @GetMapping("v1/export")
    public Result exportReport(FirstSubjectDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "一级科目.xlsx";
            super.writeOutFile(response, firstSubjectAPI.exportExcel(dto), fileName);
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
     * @des 下载模板一级科目信息
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "一级科目导入模板.xlsx";
            super.writeOutFile(response, firstSubjectAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}