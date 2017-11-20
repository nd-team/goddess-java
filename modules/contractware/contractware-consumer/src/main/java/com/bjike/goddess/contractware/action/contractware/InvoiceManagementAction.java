package com.bjike.goddess.contractware.action.contractware;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.api.InvoiceManagementAPI;
import com.bjike.goddess.contractware.bo.ContractManagementBO;
import com.bjike.goddess.contractware.bo.InvoiceManagementBO;
import com.bjike.goddess.contractware.dto.InvoiceManagementDTO;
import com.bjike.goddess.contractware.entity.InvoiceManagement;
import com.bjike.goddess.contractware.to.ContractManagementDeleteFileTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.to.InvoiceManagementTO;
import com.bjike.goddess.contractware.vo.ContractManagementVO;
import com.bjike.goddess.contractware.vo.InvoiceManagementVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 发票管理
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-11-01 11:04 ]
 * @Description: [ 发票管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("invoicemanagement")
public class InvoiceManagementAction extends BaseFileAction {
    @Autowired
    private InvoiceManagementAPI invoiceManagementAPI;

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
            Boolean isHasPermission = invoiceManagementAPI.guidePermission ( guidePermissionTO );
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult ( 0, "没有权限", false );
            } else {
                return new ActResult ( 0, "有权限", true );
            }
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 添加
     *
     * @param invoiceManagementTO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) InvoiceManagementTO invoiceManagementTO) throws ActException {
        try {
            invoiceManagementAPI.add ( invoiceManagementTO );
            return new ActResult ( "添加成功" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 列表
     *
     * @param invoiceManagementDTO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(InvoiceManagementDTO invoiceManagementDTO) throws ActException {
        try {
            List<InvoiceManagementBO> boList = invoiceManagementAPI.pageList ( invoiceManagementDTO );
            List<InvoiceManagementVO> voList = BeanTransform.copyProperties ( boList, InvoiceManagementVO.class );
            return ActResult.initialize ( voList );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 删除
     *
     * @param id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@RequestParam String id) throws ActException {
        try {
            invoiceManagementAPI.delete ( id );
            return new ActResult ( "删除成功" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 修改
     *
     * @param invoiceManagementTO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/modify")
    public Result modify(@Validated(EDIT.class) InvoiceManagementTO invoiceManagementTO, BindingResult result) throws ActException {
        try {
            invoiceManagementAPI.modify ( invoiceManagementTO );
            return new ActResult ( "修改成功" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 根据内部合同编号获取地区－合作单位－项目内部名称
     *
     * @param number 合同编号
     * @return class ContractManagementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/contract")
    public Result findNumber(@RequestParam String number) throws ActException {
        try {
            ContractManagementBO bo = invoiceManagementAPI.findByNumber ( number );
            ContractManagementVO vo = BeanTransform.copyProperties ( bo, ContractManagementVO.class );
            return ActResult.initialize ( vo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
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
            List<InputStream> inputStreams = super.getInputStreams ( request, path );
            fileAPI.upload ( inputStreams );
            invoiceManagementAPI.updateElectronic ( id );
            return new ActResult ( "上传成功" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
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
            FileInfo fileInfo = new FileInfo ();
            fileInfo.setPath ( path );
            Object storageToken = request.getAttribute ( "storageToken" );
            fileInfo.setStorageToken ( storageToken.toString () );
            List<FileVO> files = BeanTransform.copyProperties ( fileAPI.list ( fileInfo ), FileVO.class );
            return ActResult.initialize ( files );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
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
            Object storageToken = request.getAttribute ( "storageToken" );
            FileInfo fileInfo = new FileInfo ();
            fileInfo.setPath ( path );
            fileInfo.setStorageToken ( storageToken.toString () );
            String filename = org.apache.commons.lang3.StringUtils.substringAfterLast ( fileInfo.getPath (), "/" );
            byte[] buffer = fileAPI.download ( fileInfo );
            writeOutFile ( response, buffer, filename );
            return new ActResult ( "下载成功" );
        } catch (Exception e) {
            throw new ActException ( e.getMessage () );
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
        if (null != contractManagementDeleteFileTO.getPaths () && contractManagementDeleteFileTO.getPaths ().length >= 0) {
            Object storageToken = request.getAttribute ( "storageToken" );
            fileAPI.delFile ( storageToken.toString (), contractManagementDeleteFileTO.getPaths () );
        }
        return new ActResult ( "删除成功" );
    }

    /**
     * 列表总条数
     *
     * @param invoiceManagementDTO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InvoiceManagementDTO invoiceManagementDTO) throws ActException {
        try {
            long number = invoiceManagementAPI.count ( invoiceManagementDTO );
            return ActResult.initialize ( number );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 根据id查询单条数据
     *
     * @param id
     * @return class InvoiceManagementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException {
        try {
            InvoiceManagementBO bo = invoiceManagementAPI.findOne ( id );
            InvoiceManagementVO vo = BeanTransform.copyProperties ( bo, InvoiceManagementVO.class );
            return ActResult.initialize ( vo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

}