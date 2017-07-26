package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.BusInsuranceAPI;
import com.bjike.goddess.businsurance.bo.BusInsuranceBO;
import com.bjike.goddess.businsurance.dto.BusInsuranceDTO;
import com.bjike.goddess.businsurance.to.BusInsuranceTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.to.SiginManageDeleteFileTO;
import com.bjike.goddess.businsurance.vo.BusInsuranceVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
 * 商业保险方案
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-21 09:44 ]
 * @Description: [ 商业保险方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businsurance")
public class BusInsuranceAction extends BaseFileAction{

    @Autowired
    private BusInsuranceAPI busInsuranceAPI;
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

            Boolean isHasPermission = busInsuranceAPI.guidePermission(guidePermissionTO);
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
     *  商业保险方案列表总条数
     *
     * @param busInsuranceDTO  商业保险方案信息dto
     * @des 获取所有商业保险方案信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusInsuranceDTO busInsuranceDTO) throws ActException {
        try {
            Long count = busInsuranceAPI.countBusInsurance(busInsuranceDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业保险方案列表
     *
     * @param busInsuranceDTO 商业保险方案信息dto
     * @des 获取所有商业保险方案信息
     * @return  class BusInsuranceVO
     * @version v1
     */
    @GetMapping("v1/listBusInsurance")
    public Result findList(BusInsuranceDTO busInsuranceDTO, BindingResult bindingResult) throws ActException {
        try {
            List<BusInsuranceVO> busInsuranceVOList = BeanTransform.copyProperties(
                    busInsuranceAPI.listBusInsurance(busInsuranceDTO), BusInsuranceVO.class, true);
            return ActResult.initialize(busInsuranceVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加商业保险方案
     *
     * @param busInsuranceTO 商业保险方案基本信息数据to
     * @des 添加商业保险方案
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.addBusInsurance(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑商业保险方案
     *
     * @param busInsuranceTO 商业保险方案基本信息数据bo
     * @des 编辑商业保险方案
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.editBusInsurance(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除商业保险方案信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            busInsuranceAPI.deleteBusInsurance(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 编辑购买条件
     *
     * @param busInsuranceTO 商业保险方案基本信息数据bo
     * @des 编辑购买条件
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editCondition")
    public Result editCondition(@Validated BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.editBuyCondition(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 福利模块审核
     *
     * @param busInsuranceTO 商业保险方案基本信息数据bo
     * @des 编辑商业保险方案审核
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editWareAdvice")
    public Result editWareAdvice(@Validated(BusInsuranceTO.editWareAdvice.class) BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.editAdvice(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 运营商务部审核
     *
     * @param busInsuranceTO 商业保险方案基本信息数据bo
     * @des 编辑商业保险方案审核
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editBusAdvice")
    public Result editBusAdvice(@Validated(BusInsuranceTO.editBusAdvice.class) BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.editBusAdvice(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 总经办审核
     *
     * @param busInsuranceTO 商业保险方案基本信息数据bo
     * @des 总经办审核
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/audit")
    public Result audit(@Validated(BusInsuranceTO.audit.class)  BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.audit(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个或查看方案查看详细
     *
     * @param id id
     * @des 根据id查看详细
     * @return  class BusInsuranceVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id ) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.getBusInsurance(id);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 导出excel
     *
     * @param dto 商业保险方案
     * @des 导出 商业保险方案
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(BusInsuranceDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "商业保险方案.xlsx";
            super.writeOutFile(response, busInsuranceAPI.exportExcel(dto), fileName);
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
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/businsurance/busInsure/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
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
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/businsurance/busInsure/" + id;
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
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 获取所有保险公司
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllInsureComapny")
    public Result findAllInsureComapny() throws ActException {
        try {
            List<String> insureComapny = new ArrayList<>();
            insureComapny = busInsuranceAPI.getAllInsureComapny();
            return ActResult.initialize(insureComapny);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取投保险种
     */
    @GetMapping("v1/findAllInsureType")
    public Result findAllInsureType() throws ActException {
        try {
            List<String> insureType = new ArrayList<>();
            insureType = busInsuranceAPI.getAllInsureType();
            return ActResult.initialize(insureType);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取保险条件
     */
    @GetMapping("v1/findAllInsureCondition")
    public Result findAllInsureCondition() throws ActException {
        try {
            List<String> insureCondition = new ArrayList<>();
            insureCondition = busInsuranceAPI.getAllInsureCondition();
            return ActResult.initialize(insureCondition);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}