package com.bjike.goddess.contractware.action.contractware;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.vo.DispatchSheetVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.api.ContractManagementAPI;
import com.bjike.goddess.contractware.bo.ContractManagementBO;
import com.bjike.goddess.contractware.bo.InvoiceBouncesBO;
import com.bjike.goddess.contractware.dto.ContractManagementDTO;
import com.bjike.goddess.contractware.to.ContractManagementDeleteFileTO;
import com.bjike.goddess.contractware.to.ContractManagementTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.vo.ContractManagementVO;
import com.bjike.goddess.contractware.vo.InvoiceBouncesVO;
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
import java.io.InputStream;
import java.util.List;

/**
* 合同保管
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-30 06:13 ]
* @Description:	[ 合同保管 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("contractmanagement")
public class ContractManagementAction extends BaseFileAction{
    @Autowired
    private ContractManagementAPI contractManagementAPI;

    @Autowired
    private FileAPI fileAPI;

    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = contractManagementAPI.guidePermission(guidePermissionTO);
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
     * 新增
     * @param contractManagementTO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ContractManagementTO contractManagementTO) throws ActException{
        try {
            contractManagementAPI.add(contractManagementTO);
            return new ActResult("添加成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     * @param id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(String id) throws ActException{
        try {
            contractManagementAPI.delete(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     * @param contractManagementTO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/modify")
    public Result modify(@Validated(EDIT.class) ContractManagementTO contractManagementTO) throws ActException{
        try {
            contractManagementAPI.modify(contractManagementTO);
            return new ActResult("修改成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     * @param contractManagementDTO
     * @return class ContractManagementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(ContractManagementDTO contractManagementDTO) throws ActException{
        try {
            List<ContractManagementBO> contractManagementBOS = contractManagementAPI.pageList(contractManagementDTO);
            List<ContractManagementVO> contractManagementVOS = BeanTransform.copyProperties(contractManagementBOS,ContractManagementVO.class);
            return ActResult.initialize(contractManagementVOS);
        }catch (SerException e){
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
            String path = "/contractManagement/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            contractManagementAPI.updateContract(id);
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
            String path = "/contractManagement/" + id;
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
     * @param contractManagementDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/delfile")
    public Result delFile(@Validated(ContractManagementDeleteFileTO.TestDEL.class) ContractManagementDeleteFileTO contractManagementDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != contractManagementDeleteFileTO.getPaths() && contractManagementDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), contractManagementDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }

    /**
     * 根据id查询单条数据
     * @param id 数据id
     * @return class ContractManagementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException{
        try {
            ContractManagementBO bo = contractManagementAPI.findOne(id);
            ContractManagementVO VO = BeanTransform.copyProperties(bo,ContractManagementVO.class);
            return ActResult.initialize(VO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     * @param id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/freeze")
    public Result freeze(@RequestParam String id) throws ActException{
        try {
            contractManagementAPI.freeze(id);
            return new ActResult("冻结成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     * @param id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/break/freeze")
    public Result breakFreeze(@RequestParam String id) throws ActException{
        try {
            contractManagementAPI.breakFreeze(id);
            return new ActResult("解冻成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查询地区-专业－派工单号－内部项目名称－派工项目名称
     * @return class DispatchSheetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/infomation")
    public Result findInformation() throws ActException{
        try {
            List<DispatchSheetBO> dispatchSheetBOS = contractManagementAPI.findInformation();
            List<DispatchSheetVO> dispatchSheetVOS = BeanTransform.copyProperties(dispatchSheetBOS,DispatchSheetVO.class);
            return ActResult.initialize(dispatchSheetVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据内部合同编号查询弹框信息
     * @param innerProjectNumber
     * @return class InvoiceBouncesVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/number")
    public Result findByNumber(@RequestParam String innerProjectNumber) throws ActException{
        try {
            InvoiceBouncesBO invoiceBouncesBO = contractManagementAPI.findByNumber(innerProjectNumber);
            InvoiceBouncesVO invoiceBouncesVO = BeanTransform.copyProperties(invoiceBouncesBO,InvoiceBouncesVO.class);
            return ActResult.initialize(invoiceBouncesVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表总条数
     * @param contractManagementDTO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ContractManagementDTO contractManagementDTO) throws ActException{
        try {
            Long number = contractManagementAPI.count(contractManagementDTO);
            return ActResult.initialize(number);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }