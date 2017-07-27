package com.bjike.goddess.projectroyalty.action.projectroyalty;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.projectroyalty.api.CollectionPeriodAPI;
import com.bjike.goddess.projectroyalty.dto.CollectionPeriodDTO;
import com.bjike.goddess.projectroyalty.excel.SonPermissionObject;
import com.bjike.goddess.projectroyalty.to.CollectionPeriodTO;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.vo.CollectionPeriodVO;
import com.bjike.goddess.projectroyalty.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 回款周期
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:55 ]
 * @Description: [ 回款周期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collectionperiod")
public class CollectionPeriodAction {

    @Autowired
    private CollectionPeriodAPI collectionPeriodAPI;
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

            List<SonPermissionObject> hasPermissionList = collectionPeriodAPI.sonPermission();
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

            Boolean isHasPermission = collectionPeriodAPI.guidePermission(guidePermissionTO);
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
     * @param to 回款周期传输对象
     * @return class CollectionPeriodVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CollectionPeriodTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectionPeriodAPI.save(to), CollectionPeriodVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 回款周期传输对象
     * @return class CollectionPeriodVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CollectionPeriodTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectionPeriodAPI.update(to), CollectionPeriodVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 回款周期数据id
     * @return class CollectionPeriodVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectionPeriodAPI.delete(id), CollectionPeriodVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取回款周期数据
     *
     * @param id 回款周期数据id
     * @return class CollectionPeriodVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectionPeriodAPI.getById(id), CollectionPeriodVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取选项
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/findOpinion")
    public Result findOpinion() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectionPeriodAPI.findOpinion(), OpinionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 回款周期数据传输对象
     * @return class CollectionPeriodVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CollectionPeriodDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectionPeriodAPI.maps(dto), CollectionPeriodVO.class, request));
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
            return ActResult.initialize(collectionPeriodAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}