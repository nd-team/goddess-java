package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.SiginManageAPI;
import com.bjike.goddess.businessproject.bo.SiginManageBO;
import com.bjike.goddess.businessproject.dto.SiginManageDTO;
import com.bjike.goddess.businessproject.enums.MakeProjectStatus;
import com.bjike.goddess.businessproject.enums.SiginStatus;
import com.bjike.goddess.businessproject.excel.SiginManageExcel;
import com.bjike.goddess.businessproject.to.SiginManageTO;
import com.bjike.goddess.businessproject.vo.SiginManageVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
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
 * 商务项目合同签订与立项管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T19:37:28.298 ]
 * @Description: [ 商务项目合同签订与立项管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("siginmanage")
public class SiginManageAction extends BaseFileAction {

    @Autowired
    private SiginManageAPI siginManageAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 列表总条数
     *
     * @param siginManageDTO 签订与立项dto
     * @des 获取所有签订与立项总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SiginManageDTO siginManageDTO) throws ActException {
        try {
            Long count = siginManageAPI.countSiginManage(siginManageDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个签订与立项
     *
     * @param id 项目签订与立项id
     * @return class SiginManageVO
     * @des 根据id获取项目签订与立项
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SiginManageVO projectCarryVO = BeanTransform.copyProperties(
                    siginManageAPI.getOneById(id), SiginManageVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目签订与立项列表
     *
     * @param siginManageDTO 项目签订与立项信息dto
     * @return class SiginManageVO
     * @des 获取所有项目签订与立项信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListSiginManage(SiginManageDTO siginManageDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<SiginManageBO> list = siginManageAPI.listSiginManage(siginManageDTO);
            List<SiginManageVO> siginManageVOList = new ArrayList<>();
            list.stream().forEach(str -> {
                SiginManageVO vo = BeanTransform.copyProperties(str, SiginManageVO.class, "businessType", "businessCooperate", "contractProperty");
                vo.setBusinessType(str.getBusinessType());
                vo.setBusinessCooperate(str.getBusinessCooperate());
                vo.setContractProperty(str.getContractProperty());
                siginManageVOList.add(vo);
            });

            return ActResult.initialize(siginManageVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    @GetMapping("v1/list")
//    public Result findListSiginManage1(SiginManageDTO siginManageDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            List<SiginManageBO> list = siginManageAPI.listSiginManage(null);
//
//            return ActResult.initialize(siginManageVOList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 添加项目签订与立项
     *
     * @param siginManageTO 项目签订与立项基本信息数据to
     * @return class SiginManageVO
     * @des 添加项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSiginManage(@Validated(SiginManageTO.TestAdd.class) SiginManageTO siginManageTO, BindingResult bindingResult) throws ActException {
        try {
            SiginManageBO siginManageBO1 = siginManageAPI.addSiginManage(siginManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(siginManageBO1, SiginManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目签订与立项
     *
     * @param siginManageTO 项目签订与立项基本信息数据bo
     * @return class SiginManageVO
     * @des 添加项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editSiginManage(@Validated(SiginManageTO.TestAdd.class) SiginManageTO siginManageTO, BindingResult bindingResult) throws ActException {
        try {
            SiginManageBO siginManageBO1 = siginManageAPI.editSiginManage(siginManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(siginManageBO1, SiginManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目签订与立项信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSiginManage(@PathVariable String id) throws ActException {
        try {
            siginManageAPI.deleteSiginManage(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 审核
     *
     * @param siginManageTO 项目签订与立项基本信息数据bo
     * @return class SiginManageVO
     * @des 审核项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/audit")
    public Result auditSiginManage(SiginManageTO siginManageTO, BindingResult bindingResult) throws ActException {
        try {
            SiginManageBO siginManageBO1 = siginManageAPI.auditSiginManage(siginManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(siginManageBO1, SiginManageVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有地区
     *
     * @des 获取所有项目签订与立项所有地区
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result listArea() throws ActException {
        try {
            List<String> list = siginManageAPI.listArea();

            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @des 审核项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile")
    public Result uploadFile(HttpServletRequest request, BindingResult bindingResult) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/businessproject";
            List<InputStream> inputStreams = getInputStreams(request,path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @GetMapping("v1/listFile")
    public Result list(@Validated(FileInfo.COMMON.class) FileInfo fileInfo, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@Validated({FileInfo.COMMON.class}) FileInfo fileInfo, HttpServletResponse response, BindingResult result) throws ActException {
        try {
            //该文件的路径
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
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
            List<SiginManageExcel> tos = ExcelUtil.excelToClazz(is, SiginManageExcel.class, excel);
            List<SiginManageTO> tocs = new ArrayList<>();
            for(SiginManageExcel str :tos ){
                SiginManageTO siginManageTO = BeanTransform.copyProperties(str, SiginManageTO.class, "startProjectTime", "endProjectTime",
                        "siginStatus", "makeProject", "manager", "auditAdvice");
                siginManageTO.setStartProjectTime(String.valueOf(str.getStartProjectTime()));
                siginManageTO.setEndProjectTime(String.valueOf(str.getEndProjectTime()));
                siginManageTO.setSiginStatus(convertSiginStatus(str.getSiginStatus()));
                siginManageTO.setMakeProject(convertMakeProject(str.getMakeProject()));
                siginManageTO.setManager("") ;
                siginManageTO.setAuditAdvice( "" );
                tocs.add(siginManageTO);
            }
            //注意序列化
            siginManageAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private String convertSiginStatus(SiginStatus siginStatus) throws ActException {
        String status = "";
        switch (siginStatus) {
            case HASSIGN:
                status = "已签订";
                break;
            case HASNOSIGN:
                status = "未签订";
                break;
            default:
                throw new ActException("签订状态填写不正确,导入失败,正确填写方式（已签订/未签订）");
        }
        return status;
    }
    private String convertMakeProject(MakeProjectStatus makeProjectStatus) throws ActException {
        String status = "";
        switch (makeProjectStatus) {
            case  SIGN:
                status = "已立项";
                break;
            case NOSIGN:
                status = "未立项";
                break;
            default:
                throw new ActException("立项情况填写不正确,导入失败,正确填写方式（已立项/未立项）");
        }
        return status;
    }


    /**
     * 下载模板
     *
     * @param dto 项目签订与立项
     * @des 下载模板项目签订与立项
     * @version v1
     */
//    @LoginAuth
//    @PostMapping("v1/export")
//    public Result exportReport(SiginManageDTO dto, HttpServletResponse response) throws ActException {
//        try {
//            String fileName = "项目签订与立项.xlsx";
//            super.writeOutFile(response, siginManageAPI.exportExcel(dto), fileName);
//            return new ActResult("导出成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        } catch (IOException e1) {
//            throw new ActException(e1.getMessage());
//        }
//    }

    /**
     * 导出excel
     *
     * @param dto 项目签订与立项
     * @des 导出项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/export")
    public Result exportReport(SiginManageDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目签订与立项.xlsx";
            super.writeOutFile(response, siginManageAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}