package com.bjike.goddess.businesscommission.action.businesscommission;

import com.bjike.goddess.businesscommission.api.WeightAllotAPI;
import com.bjike.goddess.businesscommission.bo.WeightAllotBO;
import com.bjike.goddess.businesscommission.dto.WeightAllotDTO;
import com.bjike.goddess.businesscommission.excel.SonPermissionObject;
import com.bjike.goddess.businesscommission.excel.WeightAllotExcel;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.WeightAllotDeleteFileTO;
import com.bjike.goddess.businesscommission.to.WeightAllotTO;
import com.bjike.goddess.businesscommission.vo.WeightAllotVO;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:34 ]
 * @Description: [ 业务提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("weightallot")
public class WeightAllotAction extends BaseFileAction {

    @Autowired
    private WeightAllotAPI weightAllotAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    /**
     * 列表总条数
     *
     * @param weightAllotDTO 业务提成权重分配dto
     * @des 获取所有业务提成权重分配总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WeightAllotDTO weightAllotDTO) throws ActException {
        try {
            Long count = weightAllotAPI.countWeightAllot(weightAllotDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 一个业务提成权重分配
     *
     * @param id 项目业务提成权重分配id
     * @return class WeightAllotVO
     * @des 根据id获取项目业务提成权重分配
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            WeightAllotVO projectCarryVO = BeanTransform.copyProperties(
                    weightAllotAPI.getOneById(id), WeightAllotVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目业务提成权重分配列表
     *
     * @param weightAllotDTO 项目业务提成权重分配信息dto
     * @return class WeightAllotVO
     * @des 获取所有项目业务提成权重分配信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListWeightAllot(WeightAllotDTO weightAllotDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<WeightAllotBO> list = weightAllotAPI.listWeightAllot(weightAllotDTO);
            List<WeightAllotVO> weightAllotVOList = new ArrayList<>();
            list.stream().forEach(str -> {
                WeightAllotVO vo = BeanTransform.copyProperties(str, WeightAllotVO.class);
                weightAllotVOList.add(vo);
            });

            return ActResult.initialize(weightAllotVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加项目业务提成权重分配
     *
     * @param weightAllotTO 项目业务提成权重分配基本信息数据to
     * @return class WeightAllotVO
     * @des 添加项目业务提成权重分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addWeightAllot(@Validated(WeightAllotTO.TestAdd.class) WeightAllotTO weightAllotTO, BindingResult bindingResult) throws ActException {
        try {
            WeightAllotBO weightAllotBO1 = weightAllotAPI.addWeightAllot(weightAllotTO);
            return ActResult.initialize(BeanTransform.copyProperties(weightAllotBO1, WeightAllotVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目业务提成权重分配
     *
     * @param weightAllotTO 项目业务提成权重分配基本信息数据bo
     * @return class WeightAllotVO
     * @des 添加项目业务提成权重分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editWeightAllot(@Validated(WeightAllotTO.TestAdd.class) WeightAllotTO weightAllotTO, BindingResult bindingResult) throws ActException {
        try {
            WeightAllotBO weightAllotBO1 = weightAllotAPI.editWeightAllot(weightAllotTO);
            return ActResult.initialize(BeanTransform.copyProperties(weightAllotBO1, WeightAllotVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目业务提成权重分配信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteWeightAllot(@PathVariable String id) throws ActException {
        try {
            weightAllotAPI.deleteWeightAllot(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 审核项目业务提成权重分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/businessproject/siginmanage/" + id;
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
     * @param id 业务提成权重分配id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/businessproject/siginmanage/" + id;
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
     * @param weightAllotDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(WeightAllotDeleteFileTO.TestDEL.class) WeightAllotDeleteFileTO weightAllotDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != weightAllotDeleteFileTO.getPaths() && weightAllotDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),weightAllotDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
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
            List<WeightAllotExcel> tos = ExcelUtil.excelToClazz(is, WeightAllotExcel.class, excel);
            List<WeightAllotTO> tocs = new ArrayList<>();
            for (WeightAllotExcel str : tos) {
                WeightAllotTO weightAllotTO = BeanTransform.copyProperties(str, WeightAllotTO.class);
                tocs.add(weightAllotTO);
            }
            //注意序列化
            weightAllotAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    private String convertSiginStatus(SiginStatus siginStatus) throws ActException {
//        String status = "";
//        if (null == siginStatus) {
//            throw new ActException("签订状态填写不正确,导入失败,正确填写方式（已签订/未签订）");
//        }
//        switch (siginStatus) {
//            case HASSIGN:
//                status = "已签订";
//                break;
//            case HASNOSIGN:
//                status = "未签订";
//                break;
//            default:
//                throw new ActException("签订状态填写不正确,导入失败,正确填写方式（已签订/未签订）");
//        }
//        return status;
//    }

//    private String convertMakeProject(MakeProjectStatus makeProjectStatus) throws ActException {
//        String status = "";
//        if (null == makeProjectStatus) {
//            throw new ActException("立项情况填写不正确,导入失败,正确填写方式（已立项/未立项）");
//        }
//        switch (makeProjectStatus) {
//            case SIGN:
//                status = "已立项";
//                break;
//            case NOSIGN:
//                status = "未立项";
//                break;
//            default:
//                throw new ActException("立项情况填写不正确,导入失败,正确填写方式（已立项/未立项）");
//        }
//        return status;
//    }


    /**
     * 导出excel
     *
     * @param dto 项目业务提成权重分配
     * @des 导出项目业务提成权重分配
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(WeightAllotDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目业务提成权重分配.xlsx";
            super.writeOutFile(response, weightAllotAPI.exportExcel(dto), fileName);
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
     * @des 下载模板项目业务提成权重分配
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目业务提成权重分配导入模板.xlsx";
            super.writeOutFile(response, weightAllotAPI.templateExport( ), fileName);
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

            List<SonPermissionObject> hasPermissionList = weightAllotAPI.sonPermission();
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

            Boolean isHasPermission = weightAllotAPI.guidePermission(guidePermissionTO);
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