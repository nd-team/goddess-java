package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

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
import com.bjike.goddess.marketdevelopment.api.BusinessAPI;
import com.bjike.goddess.marketdevelopment.dto.BusinessDTO;
import com.bjike.goddess.marketdevelopment.entity.SonPermissionObject;
import com.bjike.goddess.marketdevelopment.excel.BusinessImportExcel;
import com.bjike.goddess.marketdevelopment.to.BusinessTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.vo.BusinessReturnVO;
import com.bjike.goddess.marketdevelopment.vo.BusinessVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
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
 * 业务对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-28 02:54 ]
 * @Description: [ 业务对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("business")
public class BusinessAction extends BaseFileAction {

    @Autowired
    private BusinessAPI businessAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

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

            Boolean isHasPermission = businessAPI.guidePermission(guidePermissionTO);
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
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
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
            List<SonPermissionObject> hasPermissionList = businessAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 业务对象数据传输对象
     * @return class BusinessReturnVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(BusinessDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessAPI.maps(dto), BusinessVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 业务对象传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) BusinessTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            businessAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改业务对象数据
     *
     * @param to 业务对象传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) BusinessTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            businessAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结业务对象数据
     *
     * @param to 业务对象传输对象
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(BusinessTO to, HttpServletRequest request) throws ActException {
        try {
            businessAPI.congeal(to);
            return ActResult.initialize("congeal success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻业务对象数据
     *
     * @param to 业务对象传输对象
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(BusinessTO to, HttpServletRequest request) throws ActException {
        try {
            businessAPI.thaw(to);
            return ActResult.initialize("thaw success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除业务对象数据
     *
     * @param to 业务对象传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(BusinessTO to, HttpServletRequest request) throws ActException {
        try {
            businessAPI.delete(to);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取业务对象
     *
     * @param id 业务方向数据id
     * @return class BusinessReturnVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessAPI.getById(id), BusinessReturnVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(BusinessDTO dto) throws ActException {
        try {
            return ActResult.initialize(businessAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出导入的excel模板
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/templateExcel")
    public Result templateExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "excel模板下载.xlsx";
            super.writeOutFile(response, businessAPI.templateExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @param dto 业务对象
     * @des 导出业务对象
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(BusinessDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "业务对象.xlsx";
            super.writeOutFile(response, businessAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导入
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<BusinessImportExcel> tos = ExcelUtil.mergeExcelToClazz(is, BusinessImportExcel.class, excel);
            businessAPI.upload(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}