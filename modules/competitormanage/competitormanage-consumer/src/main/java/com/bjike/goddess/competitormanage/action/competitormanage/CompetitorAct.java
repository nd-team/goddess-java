package com.bjike.goddess.competitormanage.action.competitormanage;

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
import com.bjike.goddess.competitormanage.api.CompetitorAPI;
import com.bjike.goddess.competitormanage.bo.CompetitorBO;
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.excel.CompetitorExcel;
import com.bjike.goddess.competitormanage.excel.SonPermissionObject;
import com.bjike.goddess.competitormanage.to.CompetitorDeleteFileTO;
import com.bjike.goddess.competitormanage.to.CompetitorOrganizaeTO;
import com.bjike.goddess.competitormanage.to.CompetitorTO;
import com.bjike.goddess.competitormanage.to.GuidePermissionTO;
import com.bjike.goddess.competitormanage.vo.CompetitorVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
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
 * 竞争对手信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("competitor")
public class CompetitorAct extends BaseFileAction {

    @Autowired
    private CompetitorAPI competitorAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private FileAPI fileAPI;
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

            List<SonPermissionObject> hasPermissionList = competitorAPI.sonPermission();
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

            Boolean isHasPermission = competitorAPI.guidePermission(guidePermissionTO);
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
     * 地区列表
     *
     * @return class AreaBO
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areaList(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> voList = BeanTransform.copyProperties(departmentDetailAPI.findArea(), AreaBO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增竞争对手
     *
     * @param to 竞争对手信息
     * @return class CompetitorVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) CompetitorTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CompetitorVO voList = BeanTransform.copyProperties(competitorAPI.saveCompetitor(to), CompetitorVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑竞争对手
     *
     * @param to 竞争对手信息
     * @return class CompetitorVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) CompetitorTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CompetitorVO vo = BeanTransform.copyProperties(competitorAPI.editCompetitor(to), CompetitorVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 竞争对手组织结构信息
     *
     * @param to 竞争对手组织结构信息
     * @return class CompetitorVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/organization")
    public Result editOrganization(@Validated({CompetitorOrganizaeTO.Organization.class}) CompetitorOrganizaeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CompetitorVO vo = BeanTransform.copyProperties(competitorAPI.editOrganization(to), CompetitorVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
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
            String path = "/competitor/" + id;
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
            String path = "/competitor/" + id;
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
    public Result delFile(@Validated(CompetitorDeleteFileTO.TestDEL.class) CompetitorDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/lead")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<CompetitorExcel> tos = ExcelUtil.excelToClazz(is, CompetitorExcel.class, excel);
            List<CompetitorTO> toList = BeanTransform.copyProperties(tos, CompetitorTO.class);
            competitorAPI.leadExcel(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出Excel
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportExcel(String startDate, String endDate, HttpServletResponse response) throws ActException {
        try {
            String fileName = "竞争对手管理.xlsx";
            super.writeOutFile(response, competitorAPI.exportExcel(startDate, endDate), fileName);
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
            String fileName = "竞争对手管理模板.xlsx";
            super.writeOutFile(response, competitorAPI.exportExcelModule(), fileName);
            return new ActResult(0, "导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 删除竞争对手
     *
     * @param id 竞争对手Id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            competitorAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表总条数
     *
     * @param dto 查询条件或分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CompetitorDTO dto) throws ActException {
        try {
            Long count = competitorAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询竞争对手记录
     *
     * @param id 竞争对手Id
     * @return class CompetitorVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CompetitorVO vo = BeanTransform.copyProperties(competitorAPI.findById(id), CompetitorVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 查询条件或分页条件
     * @return class CompetitorVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(CompetitorDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CompetitorVO> voList = BeanTransform.copyProperties(competitorAPI.pageList(dto), CompetitorVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}