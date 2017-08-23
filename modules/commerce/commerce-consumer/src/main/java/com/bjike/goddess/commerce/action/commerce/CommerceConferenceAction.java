package com.bjike.goddess.commerce.action.commerce;

import com.bjike.goddess.commerce.api.CommerceConferenceAPI;
import com.bjike.goddess.commerce.dto.CommerceConferenceDTO;
import com.bjike.goddess.commerce.excel.CommerceConferenceExcel;
import com.bjike.goddess.commerce.to.CollectTO;
import com.bjike.goddess.commerce.to.CommerceConferenceExcelTO;
import com.bjike.goddess.commerce.to.CommerceConferenceTO;
import com.bjike.goddess.commerce.to.GuidePermissionTO;
import com.bjike.goddess.commerce.vo.CommerceConferenceVO;
import com.bjike.goddess.commerce.vo.SonPermissionObject;
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
 * 商务会议
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-02 10:36 ]
 * @Description: [ 商务会议 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("commerceconference")
public class CommerceConferenceAction extends BaseFileAction {

    @Autowired
    private CommerceConferenceAPI commerceConferenceAPI;


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
            List<SonPermissionObject> hasPermissionList = commerceConferenceAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存
     *
     * @param to 商务会议传输对象
     * @return class CommerceConferenceVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CommerceConferenceTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.save(to), CommerceConferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 商务会议传输对象
     * @return class CommerceConferenceVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CommerceConferenceTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.update(to), CommerceConferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 商务会议数据id
     * @return class CommerceConferenceVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.congeal(id), CommerceConferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询商务会议数据
     *
     * @param id 商务会议数据id
     * @return class CommerceConferenceVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.getById(id), CommerceConferenceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 商务会议数据传输对象
     * @return class CommerceConferenceVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CommerceConferenceDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceConferenceAPI.maps(dto), CommerceConferenceVO.class, request));
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
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(commerceConferenceAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<CommerceConferenceExcelTO> list = ExcelUtil.excelToClazz(is, CommerceConferenceExcelTO.class, excel);
            commerceConferenceAPI.upload(list);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出
     *
     * @param to 导出查询条件传输对象
     * @version v1
     */
    @GetMapping("v1/export")
    public Result exportExcel(@Validated(EDIT.class) CollectTO to, BindingResult result, HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务会议.xlsx";
            super.writeOutFile(response, commerceConferenceAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (Exception e) {
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

            Boolean isHasPermission = commerceConferenceAPI.guidePermission(guidePermissionTO);
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
            List<CommerceConferenceExcel> tos = ExcelUtil.excelToClazz(is, CommerceConferenceExcel.class, excel);
            List<CommerceConferenceTO> tocs = new ArrayList<>();
            for (CommerceConferenceExcel str : tos) {
                CommerceConferenceTO commerceConferenceTO = BeanTransform.copyProperties(str, CommerceConferenceTO.class);

                commerceConferenceTO.setConferenceTime(String.valueOf(str.getConferenceTime()));
                commerceConferenceTO.setWay(str.getWay());
                commerceConferenceTO.setArea(str.getArea());
                commerceConferenceTO.setPersonnel(str.getPersonnel());
                commerceConferenceTO.setQuantity(str.getQuantity());
                commerceConferenceTO.setOrganization(str.getOrganization());
                commerceConferenceTO.setEmcee(str.getEmcee());
                commerceConferenceTO.setRecorder(str.getRecorder());
                commerceConferenceTO.setContent(str.getContent());
                commerceConferenceTO.setBulletin(str.getBulletin());
                commerceConferenceTO.setConsult(str.getConsult());
                commerceConferenceTO.setNegotiation(str.getNegotiation());
                commerceConferenceTO.setCooperation(str.getCooperation());
                commerceConferenceTO.setRemark(str.getRemark());
                tocs.add(commerceConferenceTO);
            }
            //注意序列化
            commerceConferenceAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板商务会议
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务会议导入模板.xlsx";
            super.writeOutFile(response, commerceConferenceAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}