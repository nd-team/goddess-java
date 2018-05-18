package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.api.BusinessCourseAPI;
import com.bjike.goddess.marketdevelopment.dto.BusinessCourseDTO;
import com.bjike.goddess.marketdevelopment.excel.BusinessCourseImportExcel;
import com.bjike.goddess.marketdevelopment.to.BusinessCourseTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.vo.BusinessCourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 业务方向科目
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businesscourse")
public class BusinessCourseAct extends BaseFileAction {

    @Autowired
    private BusinessCourseAPI businessCourseAPI;

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
            Boolean isHasPermission = businessCourseAPI.guidePermission(guidePermissionTO);
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
     * 列表
     *
     * @param dto 业务方向科目数据传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(BusinessCourseDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.maps(dto), BusinessCourseVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) BusinessCourseTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.save(to), BusinessCourseVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) BusinessCourseTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.update(to), BusinessCourseVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(BusinessCourseTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.congeal(to), BusinessCourseVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(BusinessCourseTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.thaw(to), BusinessCourseVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(BusinessCourseTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.delete(to), BusinessCourseVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 查询对应业务类型的业务方向科目数据
//     *
//     * @param id 业务类型ID
//     * @return class BusinessCourseVO
//     * @version v1StaffRecords
//     */
//    @GetMapping("v1/findByType/{id}")
//    public Result findByType(@PathVariable String id, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.findByType(id), BusinessCourseVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 查询正常数据的业务方向科目数据
//     *
//     * @return class BusinessCourseVO
//     * @version v1
//     */
//    @GetMapping("v1/findThaw")
//    public Result findThaw(HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.findThaw(), BusinessCourseVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 根据id获取业务方向科目
     *
     * @param id 业务方向数据id
     * @return class BusinessCourseVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.getById(id), BusinessCourseVO.class, request));
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
    public Result getTotal(BusinessCourseDTO dto) throws ActException {
        try {
            return ActResult.initialize(businessCourseAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 查询正常数据的业务方向科目
//     *
//     * @version v1
//     */
//    @GetMapping("v1/getProjectName")
//    public Result getProjectName() throws ActException {
//        try {
//            return ActResult.initialize(businessCourseAPI.getProjectName());
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


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
            super.writeOutFile(response, businessCourseAPI.templateExcel(), fileName);
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
     * @param dto 业务方向科目
     * @des 导出业务方向科目
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(BusinessCourseDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "业务方向科目.xlsx";
            super.writeOutFile(response, businessCourseAPI.exportExcel(dto), fileName);
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
            List<BusinessCourseImportExcel> tos = ExcelUtil.mergeExcelToClazz(is, BusinessCourseImportExcel.class, excel);
            businessCourseAPI.upload(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部的业务方向名称
     *
     * @version v1
     */
    @GetMapping("v1/find/businessType")
    public Result findBusinessType() throws ActException {
        try {
            return ActResult.initialize(businessCourseAPI.findBusinessType());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务方向分类查询业务方向科目
     *
     * @version v1
     */
    @GetMapping("v1/find/subject")
    public Result findSubject(@RequestParam String businessType) throws ActException {
        try {
            return ActResult.initialize(businessCourseAPI.findSubject(businessType));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部的业务方向科目
     *
     * @version v1
     */
    @GetMapping("v1/find/allSubject")
    public Result findAllSubject() throws ActException {
        try {
            return ActResult.initialize(businessCourseAPI.findAllSubject());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}