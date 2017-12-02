package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.OutBillAPI;
import com.bjike.goddess.marketdevelopment.dto.OutBillDTO;
import com.bjike.goddess.marketdevelopment.to.OutBillTO;
import com.bjike.goddess.marketdevelopment.vo.OutBillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 外出单
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 11:12 ]
 * @Description: [ 外出单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("outbill")
public class OutBillAction extends BaseFileAction {
    @Autowired
    private OutBillAPI outBillAPI;

    /**
     //     * 功能导航权限
     //     *
     //     * @param guidePermissionTO 导航类型数据
     //     * @throws ActException
     //     * @version v1
     //     */
//    @GetMapping("v1/guidePermission")
//    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            Boolean isHasPermission = outBillAPI.guidePermission(guidePermissionTO);
//            if (!isHasPermission) {
//                //int code, String msg
//                return new ActResult(0, "没有权限", false);
//            } else {
//                return new ActResult(0, "有权限", true);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 列表
     *
     * @param dto 外出单数据传输对象
     * @return class OutBillVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(OutBillDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(outBillAPI.maps(dto), OutBillVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 外出单传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) OutBillTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            outBillAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改外出单数据
     *
     * @param to 外出单传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) OutBillTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            outBillAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 冻结外出单数据
//     *
//     * @param to 外出单传输对象
//     * @version v1
//     */
//    @PutMapping("v1/congeal/{id}")
//    public Result congeal(OutBillTO to, HttpServletRequest request) throws ActException {
//        try {
//            outBillAPI.congeal(to);
//            return ActResult.initialize("congeal success");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

//    /**
//     * 解冻外出单数据
//     *
//     * @param to 外出单传输对象
//     * @version v1
//     */
//    @PutMapping("v1/thaw/{id}")
//    public Result thaw(OutBillTO to, HttpServletRequest request) throws ActException {
//        try {
//            outBillAPI.thaw(to);
//            return ActResult.initialize("thaw success");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 删除外出单数据
     *
     * @param to 外出单传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(OutBillTO to, HttpServletRequest request) throws ActException {
        try {
            outBillAPI.delete(to);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取外出单
     *
     * @param id 业务方向数据id
     * @return class OutBillVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(outBillAPI.getById(id), OutBillVO.class, request));
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
    public Result getTotal(OutBillDTO dto) throws ActException {
        try {
            return ActResult.initialize(outBillAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 导出导入的excel模板
//     *
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/templateExcel")
//    public Result templateExcel(HttpServletResponse response) throws ActException {
//        try {
//            String fileName = "excel模板下载.xlsx";
//            super.writeOutFile(response, outBillAPI.templateExcel(), fileName);
//            return new ActResult("导出成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        } catch (IOException e1) {
//            throw new ActException(e1.getMessage());
//        }
//    }
//
//
//    /**
//     * 导出excel
//     *
//     * @param dto 外出单
//     * @des 导出外出单
//     * @version v1
//     */
////    @LoginAuth
//    @GetMapping("v1/export")
//    public Result exportReport(OutBillDTO dto, HttpServletResponse response) throws ActException {
//        try {
//            String fileName = "外出单.xlsx";
//            super.writeOutFile(response, outBillAPI.exportExcel(dto), fileName);
//            return new ActResult("导出成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        } catch (IOException e1) {
//            throw new ActException(e1.getMessage());
//        }
//    }
//
//
//    /**
//     * 导入
//     *
//     * @version v1
//     */
//    @PostMapping("v1/upload")
//    public Result upload(HttpServletRequest request) throws ActException {
//        try {
//            List<InputStream> inputStreams = super.getInputStreams(request);
//            InputStream is = inputStreams.get(1);
//            Excel excel = new Excel(0, 1);
//            List<OutBillImportExcel> tos = ExcelUtil.mergeExcelToClazz(is, OutBillImportExcel.class, excel);
//            outBillAPI.upload(tos);
//            return new ActResult("导入成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

}