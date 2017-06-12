package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
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
import com.bjike.goddess.supplier.api.SupplierInformationAPI;
import com.bjike.goddess.supplier.bo.SupplierInfoCollectBO;
import com.bjike.goddess.supplier.dto.SupplierInformationDTO;
import com.bjike.goddess.supplier.to.SupplierInformationTO;
import com.bjike.goddess.supplier.vo.SupplierInfoCollectTitleVO;
import com.bjike.goddess.supplier.vo.SupplierInfoCollectVO;
import com.bjike.goddess.supplier.vo.SupplierInformationVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 供应商基本信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.055 ]
 * @Description: [ 供应商基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("supplierinformation")
public class SupplierInformationAct extends BaseFileAction {

    @Autowired
    private SupplierInformationAPI supplierInformationAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 保存供应商基本信息数据
     *
     * @param to 供应商信息传输对象
     * @return class SupplierInformationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SupplierInformationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.save(to), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商基本信息数据
     *
     * @param to 供应商信息传输对象
     * @return class SupplierInformationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SupplierInformationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.update(to), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商详细信息数据
     *
     * @param to 供应商信息传输对象
     * @return class SupplierInformationVO
     * @version v1
     */
    @PutMapping("v1/updateDetail/{id}")
    public Result updateDetail(@Validated(ADD.class) SupplierInformationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.updateDetail(to), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询根据供应商名称排序的供应商信息
     *
     * @return class SupplierInformationVO
     * @version v1
     */
    @GetMapping("v1/findOrderName")
    public Result findOrderName() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.findOrderName(), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     *
     * @param id 供应商基本信息数据id
     * @return class SupplierInformationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.delete(id), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 供应商基本信息数据传输对象
     * @return class SupplierInformationVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(SupplierInformationDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.maps(dto), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取供应商基本信息数据
     *
     * @param id 供应商基本信息数据id
     * @return class SupplierInformationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.getById(id), SupplierInformationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param area 汇总地区
     * @return class SupplierInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(String... area) throws ActException {
        try {
            List<SupplierInfoCollectBO> bos = supplierInformationAPI.collect(area);
            List<SupplierInfoCollectVO> vos = new ArrayList<>(0);
            for (SupplierInfoCollectBO bo : bos) {
                SupplierInfoCollectVO vo = new SupplierInfoCollectVO();
                vo.setArea(bo.getArea());
                vo.setTitleVOs(new HashSet<>(0));
                if (bo.getTitleBOs() != null && bo.getTitleBOs().size() != 0)
                    vo.getTitleVOs().addAll(BeanTransform.copyProperties(bo.getTitleBOs(), SupplierInfoCollectTitleVO.class));
                vos.add(vo);
            }
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(supplierInformationAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param id 供应商信息id
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadEnclosure/{id}")
    public Result uploadEnclosure(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/supplier/information/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            supplierInformationAPI.changeEnclosure(id);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 供应商信息id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/supplier/information/" + id;
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
     * @param paths 多文件信息路径
     * @version v1
     */
    @DeleteMapping("v1/deleteFile")
    public Result delFile(@RequestParam String[] paths, HttpServletRequest request) throws SerException {
        Object storageToken = request.getAttribute("storageToken");
        fileAPI.delFile(storageToken.toString(), paths);
        return new ActResult("delFile success");
    }
}