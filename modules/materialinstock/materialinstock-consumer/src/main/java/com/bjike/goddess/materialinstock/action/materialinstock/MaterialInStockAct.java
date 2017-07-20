package com.bjike.goddess.materialinstock.action.materialinstock;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.to.GuidePermissionTO;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;
import com.bjike.goddess.materialinstock.to.StockDeleteFileTO;
import com.bjike.goddess.materialinstock.vo.MaterialInStockVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 物资入库
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialinstock")
public class MaterialInStockAct extends BaseFileAction {

    @Autowired
    private MaterialInStockAPI materialInStockAPI;

    @Autowired
    private FileAPI fileAPI;

    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
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

            Boolean isHasPermission = materialInStockAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询物资入库
     *
     * @param id      物资入库唯一标识
     * @return class MaterialInStockVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/materialinstock/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialInStockBO bo = materialInStockAPI.findById(id);
            MaterialInStockVO vo = BeanTransform.copyProperties(bo, MaterialInStockVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 物资入库dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MaterialInStockDTO dto, BindingResult result) throws ActException {
        try {
            Long count = materialInStockAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询物资入库
     *
     * @param dto           物资入库dto
     * @return class MaterialInStockVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialInStockDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<MaterialInStockBO> boList = materialInStockAPI.list(dto);
            List<MaterialInStockVO> voList = BeanTransform.copyProperties(boList, MaterialInStockVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资入库
     *
     * @param to      物资入库to
     * @return class MaterialInStockVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MaterialInStockTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialInStockBO bo = materialInStockAPI.save(to);
            MaterialInStockVO vo = BeanTransform.copyProperties(bo, MaterialInStockVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资入库
     *
     * @param id 物资入库唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialInStockAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资入库
     *
     * @param to     物资入库to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MaterialInStockTO to, BindingResult result) throws ActException {
        try {
            materialInStockAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
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
            String path = "/" + id;
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
     * @param stockDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(StockDeleteFileTO.TestDEL.class) StockDeleteFileTO stockDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != stockDeleteFileTO.getPaths() && stockDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), stockDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }
    /**
     * 所有部门下拉值
     *
     * @version v1
     */
    @GetMapping("v1/allOrageDepartment")
    public Result allOrageDepartment() throws ActException {
        try {
            List<String> detail = new ArrayList<>();
            detail = materialInStockAPI.findAddAllDetails();
            return ActResult.initialize(detail);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 添加中所有的地区
     *
     * @version v1
     */
    @GetMapping("v1/allArea")
    public Result allArea() throws ActException {
        try {
            List<AreaBO> area = departmentDetailAPI.findArea();
            return ActResult.initialize(area);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有用户
     *
     * @version v1
     */
    @GetMapping("v1/allGetPerson")
    public Result allGetPerson() throws ActException {
        try {
            List<String> getPerson = new ArrayList<>();
            getPerson = materialInStockAPI.findallUser();
            return ActResult.initialize(getPerson);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}