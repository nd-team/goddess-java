package com.bjike.goddess.businesscommission.action.businesscommission;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businesscommission.api.ProportionAPI;
import com.bjike.goddess.businesscommission.bo.ProportionBO;
import com.bjike.goddess.businesscommission.dto.ProportionDTO;
import com.bjike.goddess.businesscommission.excel.ProportionImportExcele;
import com.bjike.goddess.businesscommission.excel.SonPermissionObject;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.ProportionDeleteFileTO;
import com.bjike.goddess.businesscommission.to.ProportionExcelTO;
import com.bjike.goddess.businesscommission.to.ProportionTO;
import com.bjike.goddess.businesscommission.vo.ProportionVO;
import com.bjike.goddess.businessproject.api.SiginManageAPI;
import com.bjike.goddess.businessproject.bo.SiginManageBO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.projectroyalty.vo.WeightalsVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
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
 * 业务提成分配比例表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:29 ]
 * @Description: [ 业务提成分配比例表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("proportion")
public class ProportionAction extends BaseFileAction {
    @Autowired
    private ProportionAPI proportionAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private SiginManageAPI siginManageAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 列表总条数
     *
     * @param proportionDTO 业务提成分配比例dto
     * @des 获取所有业务提成分配比例总条数
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(ProportionDTO proportionDTO) throws ActException {
        try {
            Long count = proportionAPI.getTotal(proportionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个业务提成分配比例
     *
     * @param id 项目业务提成分配比例id
     * @return class ProportionVO
     * @des 根据id获取项目业务提成分配比例
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ProportionVO projectCarryVO = BeanTransform.copyProperties(
                    proportionAPI.getOneById(id), ProportionVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目业务提成分配比例列表
     *
     * @param proportionDTO 项目业务提成分配比例信息dto
     * @return class ProportionVO
     * @des 获取所有项目业务提成分配比例信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProportion(ProportionDTO proportionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ProportionBO> list = proportionAPI.listProportion(proportionDTO);
            List<ProportionVO> proportionVOList = new ArrayList<>();
            list.stream().forEach(str -> {
                ProportionVO vo = BeanTransform.copyProperties(str, ProportionVO.class);
                proportionVOList.add(vo);
            });

            return ActResult.initialize(proportionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加项目业务提成分配比例
     *
     * @param proportionTO 项目业务提成分配比例基本信息数据to
     * @return class ProportionVO
     * @des 添加项目业务提成分配比例
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProportion(@Validated(ProportionTO.TestAdd.class) ProportionTO proportionTO, BindingResult bindingResult) throws ActException {
        try {
            proportionAPI.addProportion(proportionTO);
            return ActResult.initialize("save success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目业务提成分配比例
     *
     * @param proportionTO 项目业务提成分配比例基本信息数据bo
     * @return class ProportionVO
     * @des 添加项目业务提成分配比例
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editProportion(@Validated(ProportionTO.TestAdd.class) ProportionTO proportionTO, BindingResult bindingResult) throws ActException {
        try {
            proportionAPI.editProportion(proportionTO);
            return ActResult.initialize("update success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目业务提成分配比例信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProportion(@PathVariable String id) throws ActException {
        try {
            proportionAPI.deleteProportion(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 审核项目业务提成分配比例
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 业务提成分配比例id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/" + id;
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
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param proportionDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(ProportionDeleteFileTO.TestDEL.class) ProportionDeleteFileTO proportionDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != proportionDeleteFileTO.getPaths() && proportionDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), proportionDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
//    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<ProportionImportExcele> tos = ExcelUtil.mergeExcelToClazz(is, ProportionImportExcele.class, excel);

            List<ProportionExcelTO> tocs = new ArrayList<>(0);
            for (ProportionImportExcele str : tos) {
                ProportionExcelTO proportionTO = BeanTransform.copyProperties(str, ProportionExcelTO.class, "confirm");
                if ("是".equals(str.getConfirm())) {
                    proportionTO.setConfirm(true);
                } else {
                    proportionTO.setConfirm(false);
                }
                tocs.add(proportionTO);
            }
            //注意序列化
            proportionAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto 项目业务提成分配比例
     * @des 导出项目业务提成分配比例
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(ProportionDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "业务提成分配比例.xlsx";
            super.writeOutFile(response, proportionAPI.exportExcel(dto), fileName);
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
     * @des 下载模板项目业务提成分配比例
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "业务提成分配比例导入模板.xlsx";
            super.writeOutFile(response, proportionAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
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
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
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

            List<SonPermissionObject> hasPermissionList = proportionAPI.sonPermission();
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

            Boolean isHasPermission = proportionAPI.guidePermission(guidePermissionTO);
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
     * 内部项目名称
     *
     * @version v1
     */
    @GetMapping("v1/find/projectName")
    public Result findProjectName() throws ActException {
        try {
            if (moduleAPI.isCheck("businessproject")) {
                List<String> projectNames = siginManageAPI.listInnerProject();
                return ActResult.initialize(projectNames);
            }
            return ActResult.initialize(null);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据内部项目名称获取地区部门
     *
     * @return class WeightalsVO
     * @version v1
     */
    @GetMapping("v1/findByProject")
    public Result findByProject(String projectName) throws ActException {
        try {
            SiginManageBO siginManageBO = siginManageAPI.findByProject(projectName);
            if (null != siginManageBO) {
                WeightalsVO weightalsVO = new WeightalsVO();
                weightalsVO.setArea(siginManageBO.getArea());
                weightalsVO.setDepartment(siginManageBO.getProjectGroup());
                return ActResult.initialize(weightalsVO);
            }
            return ActResult.initialize(null);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认
     *
     * @version v1
     */
    @PutMapping("v1/confirm")
    public Result confirm(Boolean tar) throws ActException {
        try {
            proportionAPI.confirm(tar);
            return ActResult.initialize("confirm success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 参与协商人
     *
     * @version v1
     */
    @GetMapping("v1/findName")
    public Result findName() throws ActException {
        try {
            List<UserBO> list = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}