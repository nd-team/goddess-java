package com.bjike.goddess.capability.action.capability;

import com.bjike.goddess.capability.api.CompanyCapabilityAPI;
import com.bjike.goddess.capability.bo.CompanyCapabilityBO;
import com.bjike.goddess.capability.dto.CompanyCapabilityDTO;
import com.bjike.goddess.capability.excele.SonPermissionObject;
import com.bjike.goddess.capability.to.CapabilityDeleteFileTO;
import com.bjike.goddess.capability.to.CompanyCapabilityTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.capability.vo.CompanyCapabilityVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
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
 * 公司能力展示
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:08 ]
 * @Description: [ 公司能力展示 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("companycapability")
public class CompanyCapabilityAction extends BaseFileAction{

    @Autowired
    private CompanyCapabilityAPI companyCapabilityAPI;

    @Autowired
    private FileAPI fileAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 公司能力总条数
     *
     * @param companyCapabilityDTO
     * @des 获取所有公司能力总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CompanyCapabilityDTO companyCapabilityDTO) throws ActException {
        try {
            Long count = companyCapabilityAPI.counts(companyCapabilityDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个公司能力
     *
     * @param id
     * @param request 前端过滤参数
     * @des 获取一个公司能力
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id,HttpServletRequest request) throws ActException {
        try {
            CompanyCapabilityBO companyCapabilityBO = companyCapabilityAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(companyCapabilityBO,CompanyCapabilityVO.class , request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司能力列表
     *
     * @param companyCapabilityDTO 公司能力信息dto
     * @param request 前端过滤参数
     * @des 获取所有公司能力信息
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/listCompanyCapability")
    public Result findListCompanyCapability(CompanyCapabilityDTO companyCapabilityDTO,HttpServletRequest request) throws ActException {
        try {
            List<CompanyCapabilityVO> companyCapabilityVOList = BeanTransform.copyProperties(
                    companyCapabilityAPI.listCompanyCapability(companyCapabilityDTO), CompanyCapabilityVO.class, request);
            return ActResult.initialize(companyCapabilityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司能力
     *
     * @param companyCapabilityTO 公司能力基本信息数据to
     * @des 添加公司能力,公司名称不能为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCompanyCapability(@Validated CompanyCapabilityTO companyCapabilityTO) throws ActException {
        try {
            CompanyCapabilityBO companyCapabilityBO1 = companyCapabilityAPI.addCompanyCapability(companyCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyCapabilityBO1,CompanyCapabilityVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑公司能力
     *
     * @param companyCapabilityTO 公司能力基本信息数据bo
     * @des 添加公司能力,公司名称不能为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCompanyCapability(@Validated  CompanyCapabilityTO companyCapabilityTO) throws ActException {
        try {
            CompanyCapabilityBO companyCapabilityBO1 = companyCapabilityAPI.editCompanyCapability(companyCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyCapabilityBO1,CompanyCapabilityVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除公司能力信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCompanyCapability(@PathVariable String id) throws ActException {
        try {
            companyCapabilityAPI.deleteCompanyCapability(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索模糊搜索
     *
     * @param companyCapabilityDTO 公司能力信息dto里面的公司名称
     * @des 获取搜索到的所有公司能力信息
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/listCapabilityByCompanyName")
    public Result listCompanyCbilityByCompanyName(CompanyCapabilityDTO companyCapabilityDTO) throws ActException {
        try {
            List<CompanyCapabilityVO> companyCapabilityVOList = BeanTransform.copyProperties(
                    companyCapabilityAPI.listCompanyCapabilityByName(companyCapabilityDTO), CompanyCapabilityVO.class, true);
            return ActResult.initialize(companyCapabilityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @param companyCapabilityTO 公司能力基本信息数据to
     * @des 导入公司能力,公司名称不能为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcelCCapability(@Validated CompanyCapabilityTO companyCapabilityTO) throws ActException {
        return null;
    }


    /**
     * 导出
     *
     * @param companyName 公司名称
     * @des 导出公司能力,公司名称可以为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcelCapability(String companyName,HttpServletResponse response) throws ActException {
        try {
            String fileName = "公司能力展示.xlsx";
            super.writeOutFile(response, companyCapabilityAPI.exportExcel(companyName), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 查看公司组织结构规划
     *
     * @param companyCapabilityTO 公司能力to
     * @des 导出公司能力,公司名称可以为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/lookLayout")
    public Result lookLayout(CompanyCapabilityTO companyCapabilityTO ) throws ActException {
        return null;
    }

    /**
     * 获取所有公司名
     *
     * @des 获取所有公司名
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/listAllCompanyName")
    public Result listAllCompanyName(  ) throws ActException {
        try {
            List<String> list = companyCapabilityAPI.listAllCompanyName();
            return ActResult.initialize(list);
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
            String path = "/capability/" + id;
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
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/files/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/capability/" + id;
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
     * 附件下载
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
    public Result delFile(@Validated(CapabilityDeleteFileTO.TestDEL.class) CapabilityDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
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

            List<SonPermissionObject> hasPermissionList = companyCapabilityAPI.sonPermission();
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

            Boolean isHasPermission = companyCapabilityAPI.guidePermission(guidePermissionTO);
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




}