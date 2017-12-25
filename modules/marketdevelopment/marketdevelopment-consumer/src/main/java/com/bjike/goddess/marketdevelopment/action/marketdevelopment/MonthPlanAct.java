//package com.bjike.goddess.marketdevelopment.action.marketdevelopment;
//
//import com.bjike.goddess.common.api.entity.ADD;
//import com.bjike.goddess.common.api.entity.EDIT;
//import com.bjike.goddess.common.api.exception.ActException;
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.api.restful.Result;
//import com.bjike.goddess.common.consumer.action.BaseFileAction;
//import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
//import com.bjike.goddess.common.consumer.restful.ActResult;
//import com.bjike.goddess.common.utils.bean.BeanTransform;
//import com.bjike.goddess.marketdevelopment.api.MonthPlanAPI;
//import com.bjike.goddess.marketdevelopment.dto.MonthPlanDTO;
//import com.bjike.goddess.marketdevelopment.to.CollectTO;
//import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
//import com.bjike.goddess.marketdevelopment.to.MonthPlanTO;
//import com.bjike.goddess.marketdevelopment.vo.MonthPlanChoiceVO;
//import com.bjike.goddess.marketdevelopment.vo.MonthPlanVO;
//import com.bjike.goddess.storage.api.FileAPI;
//import com.bjike.goddess.storage.to.FileInfo;
//import com.bjike.goddess.storage.vo.FileVO;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.InputStream;
//import java.util.List;
//
///**
// * 月计划
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 06:41 ]
// * @Description: [ 月计划 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@RestController
//@RequestMapping("monthplan")
//public class MonthPlanAct extends BaseFileAction {
//
//    @Autowired
//    private FileAPI fileAPI;
//    @Autowired
//    private MonthPlanAPI monthPlanAPI;
//
//
//    /**
//     * 功能导航权限
//     *
//     * @param guidePermissionTO 导航类型数据
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/guidePermission")
//    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//
//            Boolean isHasPermission = monthPlanAPI.guidePermission(guidePermissionTO);
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
//
//    /**
//     * 列表
//     *
//     * @param dto 月计划数据传输对象
//     * @return class MonthPlanVO
//     * @version v1
//     */
//    @GetMapping("v1/maps")
//    public Result maps(MonthPlanDTO dto, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.maps(dto), MonthPlanVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 保存月计划数据
//     *
//     * @param to 月计划传输对象
//     * @return class MonthPlanVO
//     * @version v1
//     */
//    @PostMapping("v1/save")
//    public Result save(@Validated(ADD.class) MonthPlanTO to, BindingResult result, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.save(to), MonthPlanVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 修改月计划数据
//     *
//     * @param to 月计划传输对象
//     * @return class MonthPlanVO
//     * @version v1
//     */
//    @PutMapping("v1/update/{id}")
//    public Result update(@Validated(EDIT.class) MonthPlanTO to, BindingResult result, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.update(to), MonthPlanVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 删除月计划数据
//     *
//     * @param to 月计划传输书对象
//     * @return class MonthPlanVO
//     * @version v1
//     */
//    @DeleteMapping("v1/delete/{id}")
//    public Result delete(MonthPlanTO to, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.delete(to), MonthPlanVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 根据年计划ID查询月计划数据
//     *
//     * @param id 年计划ID
//     * @return class MonthPlanVO
//     * @version v1
//     */
//    @GetMapping("v1/findByYearId/{id}")
//    public Result findByYearID(@PathVariable String id, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.findByYearID(id), MonthPlanVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 根据年份查询月计划数据
//     *
//     * @param year 年份
//     * @return class MonthPlanVO
//     * @version v1
//     */
//    @GetMapping("v1/findByYear")
//    public Result findByYear(Integer year, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.findByYear(year), MonthPlanVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 根据id获取月计划
//     *
//     * @param id 月计划数据id
//     * @return class MonthPlanVO
//     * @version v1
//     */
//    @GetMapping("v1/findById/{id}")
//    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.getById(id), MonthPlanVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 获取总条数
//     *
//     * @version v1
//     */
//    @GetMapping("v1/getTotal")
//    public Result getTotal() throws ActException {
//        try {
//            return ActResult.initialize(monthPlanAPI.getTotal());
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 获取月计划选择对象
//     *
//     * @return class MonthPlanChoiceVO
//     * @version v1
//     */
//    @GetMapping("v1/getChoice")
//    public Result getChoice(HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.getChoice(), MonthPlanChoiceVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 上传附件
//     *
//     * @param id 月计划数据id
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/uploadEnclosure/{id}")
//    public Result uploadEnclosure(@PathVariable String id, HttpServletRequest request) throws ActException {
//        try {
//            String path = "/marketdevelopment/monthplan/" + id;
//            List<InputStream> inputStreams = getInputStreams(request, path);
//            fileAPI.upload(inputStreams);
//            return new ActResult("upload success");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 文件附件列表
//     *
//     * @param id 月计划数据id
//     * @version v1
//     */
//    @GetMapping("v1/listFile/{id}")
//    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
//        try {
//            String path = "/marketdevelopment/monthplan/" + id;
//            FileInfo fileInfo = new FileInfo();
//            fileInfo.setPath(path);
//            Object storageToken = request.getAttribute("storageToken");
//            fileInfo.setStorageToken(storageToken.toString());
//            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
//            return ActResult.initialize(files);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 文件下载
//     *
//     * @param path 文件信息路径
//     * @version v1
//     */
//    @GetMapping("v1/downloadFile")
//    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
//        try {
//            //该文件的路径
//            Object storageToken = request.getAttribute("storageToken");
//            FileInfo fileInfo = new FileInfo();
//            fileInfo.setPath(path);
//            fileInfo.setStorageToken(storageToken.toString());
//            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
//            byte[] buffer = fileAPI.download(fileInfo);
//            writeOutFile(response, buffer, filename);
//            return new ActResult("download success");
//        } catch (Exception e) {
//            throw new ActException(e.getMessage());
//        }
//
//    }
//
//    /**
//     * 删除文件或文件夹
//     *
//     * @param paths 多文件信息路径
//     * @version v1
//     */
//    @PostMapping("v1/deleteFile")
//    public Result delFile(@RequestParam String[] paths, HttpServletRequest request) throws ActException {
//        try {
//            Object storageToken = request.getAttribute("storageToken");
//            fileAPI.delFile(storageToken.toString(), paths);
//            return new ActResult("delFile success");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 导出
//     *
//     * @param to 导出查询条件传输对象
//     * @version v1
//     */
//    @GetMapping("v1/exportExcel")
//    public Result exportExcel(CollectTO to, HttpServletResponse response) throws ActException {
//        try {
//            String fileName = "月计划.xlsx";
//            super.writeOutFile(response, monthPlanAPI.exportExcel(to), fileName);
//            return new ActResult("导出成功");
//        } catch (Exception e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//}