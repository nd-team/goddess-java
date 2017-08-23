package com.bjike.goddess.materialbuy.action.materialbuy;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.api.MaterialBuyAPI;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.dto.DeviceTypeDTO;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.to.DeleteFileTO;
import com.bjike.goddess.materialbuy.to.GuidePermissionTO;
import com.bjike.goddess.materialbuy.to.MaterialBuyTO;
import com.bjike.goddess.materialbuy.vo.MaterialBuyVO;
import com.bjike.goddess.materialbuy.vo.SonPermissionObject;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
import java.util.Set;

/**
 * 物资购买
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialbuy")
public class MaterialBuyAct extends BaseFileAction{

    @Autowired
    private MaterialBuyAPI materialBuyAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private UserAPI userAPI;

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

            List<SonPermissionObject> hasPermissionList = materialBuyAPI.sonPermission();
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

            Boolean isHasPermission = materialBuyAPI.guidePermission(guidePermissionTO);
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
            String path = "/materialbuy/materialbuy/" + id;
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
            String path = "/materialbuy/materialbuy/" + id;
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
     * @param deleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(DeleteFileTO.TestDEL.class) DeleteFileTO deleteFileTO, HttpServletRequest request) throws SerException {
        if (null != deleteFileTO.getPaths() && deleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), deleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }
    
    /**
     * 根据id查询物资购买
     *
     * @param id 物资购买唯一标识
     * @return class MaterialBuyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findbyid/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            MaterialBuyBO bo = materialBuyAPI.findById(id);
            MaterialBuyVO vo = BeanTransform.copyProperties(bo, MaterialBuyVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询物资购买
     *
     * @param dto 物资购买dto
     * @return class MaterialBuyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialBuyDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<MaterialBuyBO> boList = materialBuyAPI.list(dto);
            List<MaterialBuyVO> voList = BeanTransform.copyProperties(boList, MaterialBuyVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资购买
     *
     * @param to 物资购买to
     * @return class MaterialBuyVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({MaterialBuyTO.MaterialBuyAdd.class}) MaterialBuyTO to, BindingResult result) throws ActException {
        try {
            MaterialBuyBO bo = materialBuyAPI.save(to);
            MaterialBuyVO vo = BeanTransform.copyProperties(bo, MaterialBuyVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资购买
     *
     * @param id 物资购买唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialBuyAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资购买
     *
     * @param to 物资购买to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(MaterialBuyTO.MaterialBuyEdit.class) MaterialBuyTO to, BindingResult result) throws ActException {
        try {
            materialBuyAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看详情
     *
     * @param id 物资购买唯一标识
     * @return class MaterialBuyVO
     * @throws SerException
     * @version v1
     */
    @GetMapping("v1/checkdetail/{id}")
    public Result checkDetail(@PathVariable String id) throws ActException {
        try {
            MaterialBuyBO bo = materialBuyAPI.checkDetail(id);
            MaterialBuyVO vo = BeanTransform.copyProperties(bo, MaterialBuyVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区负责人审核
     *
     * @param to 物资购买to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/areaprincipalaudit")
    public Result areaPrincipalAudit(@Validated(MaterialBuyTO.PrincipalAudit.class) MaterialBuyTO to, BindingResult result) throws ActException {
        try {
            materialBuyAPI.areaPrincipalAudit(to);
            return new ActResult("areaPrincipalAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有员工姓名
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findUserNames")
    public Result findUserNames() throws ActException {
        try {
            Set<String> set = new HashSet<>();
            List<UserBO> boList = userAPI.findAllUser();
            for (UserBO userBO : boList) {
                set.add(userBO.getUsername());
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findArea")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> list = departmentDetailAPI.findArea();
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目组
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MaterialBuyDTO dto) throws ActException {
        try {
            return ActResult.initialize(materialBuyAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}