package com.bjike.goddess.dimission.action.dimission;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.dimission.enums.DimissionType;
import com.bjike.goddess.dimission.excel.SonPermissionObject;
import com.bjike.goddess.dimission.to.*;
import com.bjike.goddess.dimission.vo.DimissionInfoCollectVO;
import com.bjike.goddess.dimission.vo.DimissionInfoVO;
import com.bjike.goddess.dimission.vo.DimissionReasonVO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 离职信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:12 ]
 * @Description: [ 离职信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dimissioninfo")
public class DimissionInfoAct extends BaseFileAction {

    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private ModuleAPI moduleAPI;


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
            List<SonPermissionObject> hasPermissionList = dimissionInfoAPI.sonPermission();
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

            Boolean isHasPermission = dimissionInfoAPI.guidePermission(guidePermissionTO);
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
     * 申请离职
     *
     * @param to 离职信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/apply")
    public Result apply(@Validated(ADD.class) DimissionInfoAddEditTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.apply(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请离职编辑
     *
     * @param to 离职信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/applyUpdate/{id}")
    public Result applyUpdate(@Validated(EDIT.class) DimissionInfoAddEditTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.applyUpdate(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 自离信息添加
     *
     * @param to 离职信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PostMapping("v1/presume")
    public Result presume(@Validated(ADD.class) FromInfoTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.presume(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 自离信息编辑
     *
     * @param to 离职信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/preUpdate/{id}")
    public Result update(@Validated(EDIT.class) FromInfoTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.preUpdate(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 离职信息数据id
     * @return class DimissionInfoVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.delete(id), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存负责人面谈记录
     *
     * @param to 离职面谈信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/authority/{id}")
    public Result authority(@Validated(ADD.class) DimissionInterviewTo to, BindingResult result) throws ActException {
        try {
            to.setAuthority(Boolean.TRUE);
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.interview(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存项目经理面谈记录
     *
     * @param to 离职面谈信息传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/interview/{id}")
    public Result interview(@Validated(EDIT.class) DimissionInterviewTo to, BindingResult result) throws ActException {
        try {
            to.setAuthority(Boolean.FALSE);
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.interview(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审核
     *
     * @param to 离职审核传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/audit/{id}")
    public Result audit(@Validated(ADD.class) DimissionAuditTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.audit(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 薪资确认
     *
     * @param to 离职薪资确认传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/affirm/{id}")
    public Result affirm(@Validated(ADD.class) DimissionAffirmTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.affirm(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认离职
     *
     * @param id 离职信息数据id
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/success/{id}")
    public Result success(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.success(id), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改离职类型
     *
     * @param to 离职类型修改传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @PutMapping("v1/editType/{id}")
    public Result editType(@Validated(EDIT.class) DimissionTypeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.editType(to), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据离职类型查询离职信息
     *
     * @param type 离职类型
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(DimissionType type, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.findByType(type), DimissionInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 自离信息列表
     *
     * @param dto 离职信息数据传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/presumeList")
    public Result presumeList(DimissionInfoDTO dto) throws ActException {
        try {
            dimissionInfoAPI.presumeList(dto);
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.presumeList(dto), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 离职信息列表
     *
     * @param dto 离职信息数据传输对象
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(DimissionInfoDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.maps(dto), DimissionInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询指定离职时间内的离职信息
     *
     * @param start 查询开始时间
     * @param end   查询结束时间
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/findByDate")
    public Result findByDimissionDate(String start, String end, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.findByDimissionDate(start, end), DimissionInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部离职信息
     *
     * @return class DimissionInfoVO
     * @version v1
     */
    @GetMapping("v1/all")
    public Result all(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.all(), DimissionInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门离职汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/departmentCollect")
    public Result departmentCollect(DimissionCollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.departmentCollect(to), DimissionInfoCollectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 岗位离职汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/positionCollect")
    public Result positionCollect(DimissionCollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.positionCollect(to), DimissionInfoCollectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 入职时间汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/entryCollect")
    public Result entryCollect(DimissionCollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.entryCollect(to), DimissionInfoCollectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工龄汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/seniorityCollect")
    public Result seniorityCollect(DimissionCollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.seniorityCollect(to), DimissionInfoCollectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 学历汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionInfoCollectVO
     * @version v1
     */
    @GetMapping("v1/educationCollect")
    public Result educationCollect(DimissionCollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.educationCollect(to), DimissionInfoCollectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 原因汇总
     *
     * @param to 离职信息汇总传输对象
     * @return class DimissionReasonVO
     * @version v1
     */
    @GetMapping("v1/reasonCollect")
    public Result reasonCollect(DimissionCollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.reasonCollect(to), DimissionReasonVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @param id 离职信息数据id
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadEnclosure/{id}")
    public Result uploadEnclosure(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/dimission/info/" + id;
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
     * @param id 离职信息数据id
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/dimission/info/" + id;
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
    @PostMapping("v1/deleteFile")
    public Result delFile(@RequestParam String[] paths, HttpServletRequest request) throws ActException {
        try {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), paths);
            return new ActResult("delFile success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取离职信息数据
     *
     * @param id 离职信息数据id
     * @return class DimissionReasonVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimissionInfoAPI.getById(id), DimissionInfoVO.class, request));
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
            return ActResult.initialize(dimissionInfoAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的用户名
     *
     * @version v1
     */
    @GetMapping("v1/getAllName")
    public Result getAllName() throws ActException {
        try {
            return ActResult.initialize(dimissionInfoAPI.getAllName());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部岗位
     *
     * @version v1
     */
    @GetMapping("v1/getPosition")
    public Result getPosition() throws ActException {
        try {
            if (moduleAPI.isCheck("organize")) {
                return ActResult.initialize(positionDetailUserAPI.getAllPosition());
            } else {
                return ActResult.initialize(null);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部部门
     *
     * @version v1
     */
    @GetMapping("v1/getDepartment")
    public Result getDepartment() throws ActException {
        try {
            if (moduleAPI.isCheck("organize")) {
                return ActResult.initialize(positionDetailUserAPI.getAllDepartment());
            } else {
                return ActResult.initialize(null);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}