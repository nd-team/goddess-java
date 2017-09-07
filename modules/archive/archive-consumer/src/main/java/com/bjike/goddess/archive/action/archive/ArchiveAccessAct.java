package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.ArchiveAccessAPI;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.excel.SonPermissionObject;
import com.bjike.goddess.archive.to.AccessAuditTO;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.vo.ArchiveAccessVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 档案调阅
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("archiveaccess")
public class ArchiveAccessAct {

    @Autowired
    private ArchiveAccessAPI archiveAccessAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

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

            List<SonPermissionObject> hasPermissionList = archiveAccessAPI.sonPermission();
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

            Boolean isHasPermission = archiveAccessAPI.guidePermission(guidePermissionTO);
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
     * 保存
     *
     * @param to 档案调阅传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ArchiveAccessTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.save(to), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 档案调阅传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ArchiveAccessTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.update(to), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 档案调阅数据id
     * @return class ArchiveAccessVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.delete(id), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to 档案调阅传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @PutMapping("v1/audit/{id}")
    public Result audit(AccessAuditTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.audit(to), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 档案调阅数据传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ArchiveAccessDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.maps(dto), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取档案调阅数据
     *
     * @param id 档案调阅数据id
     * @return class ArchiveAccessVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.getById(id), ArchiveAccessVO.class, request));
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
            return ActResult.initialize(archiveAccessAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}