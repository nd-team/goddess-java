package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.ContractCategoryAPI;
import com.bjike.goddess.businessproject.bo.ContractCategoryBO;
import com.bjike.goddess.businessproject.dto.ContractCategoryDTO;
import com.bjike.goddess.businessproject.excel.ContractCategoryExcel;
import com.bjike.goddess.businessproject.to.ContractCategoryTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.vo.ContractCategoryVO;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 商务项目合同类型
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 04:24 ]
 * @Description: [ 商务项目合同类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractcategory")
public class ContractCategoryAction extends BaseFileAction {

    @Autowired
    private ContractCategoryAPI contractCategoryAPI;
    @Autowired
    private FileAPI fileAPI;


    /**
     * 导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = contractCategoryAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 列表总条数
     *
     * @param contractCategoryDTO 项目合同类型信息dto
     * @des 获取所有项目合同类型信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ContractCategoryDTO contractCategoryDTO) throws ActException {
        try {
            Long count = contractCategoryAPI.countContractCategory(contractCategoryDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个项目合同类型
     *
     * @param id 项目项目合同类型信息id
     * @return class ContractCategoryVO
     * @des 根据id获取项目项目合同类型信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ContractCategoryVO projectCarryVO = BeanTransform.copyProperties(
                    contractCategoryAPI.getOneById(id), ContractCategoryVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目合同类型列表
     *
     * @param contractCategoryDTO 项目合同类型信息dto
     * @return class ContractCategoryVO
     * @des 获取所有项目合同类型信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListContractCategory(ContractCategoryDTO contractCategoryDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ContractCategoryVO> contractCategoryVOList = BeanTransform.copyProperties(
                    contractCategoryAPI.listContractCategory(contractCategoryDTO), ContractCategoryVO.class, true);
            return ActResult.initialize(contractCategoryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目合同类型
     *
     * @param contractCategoryTO 项目合同类型基本信息数据to
     * @return class ContractCategoryVO
     * @des 添加项目合同类型
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addContractCategory(@Validated ContractCategoryTO contractCategoryTO) throws ActException {
        try {
            ContractCategoryBO contractCategoryBO1 = contractCategoryAPI.addContractCategory(contractCategoryTO);
            return ActResult.initialize(BeanTransform.copyProperties(contractCategoryBO1, ContractCategoryVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目合同类型
     *
     * @param contractCategoryTO 项目合同类型基本信息数据bo
     * @return class ContractCategoryVO
     * @des 添加项目合同类型
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editContractCategory(@Validated ContractCategoryTO contractCategoryTO) throws ActException {
        try {
            ContractCategoryBO contractCategoryBO1 = contractCategoryAPI.editContractCategory(contractCategoryTO);
            return ActResult.initialize(BeanTransform.copyProperties(contractCategoryBO1, ContractCategoryVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目合同类型信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteContractCategory(@PathVariable String id) throws ActException {
        try {
            contractCategoryAPI.deleteContractCategory(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有合同类型名称
     * chenjunhao
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allContractNames")
    public Result allContractNames() throws ActException {
        try {
            return ActResult.initialize(contractCategoryAPI.allContractNames());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     * chenjunhao
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<ContractCategoryExcel> toList = ExcelUtil.excelToClazz(is, ContractCategoryExcel.class, excel);
            contractCategoryAPI.leadExcel(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     * chenjunhao
     *
     * @param dto 商务项目合同类型信息
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(ContractCategoryDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务项目合同类型.xlsx";
            super.writeOutFile(response, contractCategoryAPI.exportExcel(dto), fileName);
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
     * @des 商务项目合同类型
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile")
    public Result uploadFile(HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
//            String path = "/businessproject";
            List<InputStream> inputStreams = getInputStreams(request);
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
    public Result download(@Validated({FileInfo.COMMON.class}) FileInfo fileInfo,BindingResult result, HttpServletResponse response) throws ActException {
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
}