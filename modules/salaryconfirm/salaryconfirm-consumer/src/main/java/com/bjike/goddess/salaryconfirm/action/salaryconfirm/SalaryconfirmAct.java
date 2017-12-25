package com.bjike.goddess.salaryconfirm.action.salaryconfirm;

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
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.salaryconfirm.api.SalaryconfirmAPI;
import com.bjike.goddess.salaryconfirm.dto.SalaryconfirmDTO;
import com.bjike.goddess.salaryconfirm.excel.SalaryconfirmExcel;
import com.bjike.goddess.salaryconfirm.to.CommunicateDeleteFileTO;
import com.bjike.goddess.salaryconfirm.to.GuidePermissionTO;
import com.bjike.goddess.salaryconfirm.to.SalaryconfirmTO;
import com.bjike.goddess.salaryconfirm.vo.SalaryconfirmVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.vo.UserVO;
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
 * 薪资核算确认
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 薪资核算确认 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("salaryconfirm")
public class SalaryconfirmAct extends BaseFileAction {

    @Autowired
    private SalaryconfirmAPI salaryconfirmAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI detailUserAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private FileAPI fileAPI;

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

            Boolean isHasPermission = salaryconfirmAPI.guidePermission(guidePermissionTO);
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
     * 地区列表查询
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result allAreas(HttpServletRequest request) throws ActException {
        try {
            List<AreaVO> voList = BeanTransform.copyProperties(departmentDetailAPI.findArea(), AreaVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 用户列表查询
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("v1/users")
    public Result allUsers(HttpServletRequest request) throws ActException {
        try {
            List<UserVO> voList = BeanTransform.copyProperties(detailUserAPI.findUserList(), UserVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门列表查询
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/departments")
    public Result allDepartments(HttpServletRequest request) throws ActException {
        try {
            List<OpinionVO> voList = BeanTransform.copyProperties(departmentDetailAPI.findThawOpinion(), OpinionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 职位列表查询
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/positions")
    public Result allPositions(HttpServletRequest request) throws ActException {
        try {
            List<OpinionVO> voList = BeanTransform.copyProperties(positionDetailAPI.findThawOpinion(), OpinionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增薪资核算确认
     *
     * @param to 薪资核算确认
     * @return class SalaryconfirmVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SalaryconfirmTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            SalaryconfirmVO voList = BeanTransform.copyProperties(salaryconfirmAPI.add(to), SalaryconfirmVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑薪资核算确认
     *
     * @param to 薪资核算确认
     * @return class SalaryconfirmVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) SalaryconfirmTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            SalaryconfirmVO vo = BeanTransform.copyProperties(salaryconfirmAPI.edit(to), SalaryconfirmVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除薪资核算确认
     *
     * @param id 薪资核算确认ID
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            salaryconfirmAPI.delete(id);
            return new ActResult("删除成功");
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
    @PostMapping("v1/import")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<SalaryconfirmExcel> tos = ExcelUtil.excelToClazz(is, SalaryconfirmExcel.class, excel);
            List<SalaryconfirmTO> toList = BeanTransform.copyProperties(tos, SalaryconfirmTO.class);
            salaryconfirmAPI.importExcel(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     *
     * @param year  年份
     * @param month 月份
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportExcel(@RequestParam Integer year, @RequestParam Integer month, HttpServletResponse response) throws ActException {
        try {
            String fileName = year + "年" + month + "月薪资核算确认.xlsx";
            super.writeOutFile(response, salaryconfirmAPI.exportExcel(year, month), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导出Excel模板
     *
     * @version v1
     */
    @GetMapping("v1/module")
    public Result exportModule(HttpServletResponse response) throws ActException {
        try {
            String fileName = "薪资核算确认.xlsx";
            super.writeOutFile(response, salaryconfirmAPI.exportExcelModule(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @param id      id
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/upload/{id}")
    public Result upload(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/salaryconfirm/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/files/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/salaryconfirm/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/download")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = org.apache.commons.lang3.StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("下载成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/delfile")
    public Result delFile(@Validated(CommunicateDeleteFileTO.TestDEL.class) CommunicateDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class SalaryconfirmVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(SalaryconfirmDTO dto) throws ActException {
        try {
            List<SalaryconfirmVO> voList = BeanTransform.copyProperties(salaryconfirmAPI.pageList(dto), SalaryconfirmVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryconfirmDTO dto) throws ActException {
        try {
            Long count = salaryconfirmAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询薪资核算确认
     *
     * @param id 薪资核算确认id
     * @return class SalaryconfirmVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SalaryconfirmVO vo = BeanTransform.copyProperties(salaryconfirmAPI.findById(id), SalaryconfirmVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}