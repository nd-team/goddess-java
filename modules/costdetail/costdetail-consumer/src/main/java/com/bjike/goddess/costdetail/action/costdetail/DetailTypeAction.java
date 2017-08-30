package com.bjike.goddess.costdetail.action.costdetail;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.costdetail.api.DetailTypeAPI;
import com.bjike.goddess.costdetail.bo.DetailTypeBO;
import com.bjike.goddess.costdetail.dto.DetailTypeDTO;
import com.bjike.goddess.costdetail.entity.DetailType;
import com.bjike.goddess.costdetail.excel.SonPermissionObject;
import com.bjike.goddess.costdetail.to.DetailTypeTO;
import com.bjike.goddess.costdetail.to.GuidePermissionTO;
import com.bjike.goddess.costdetail.vo.CostDetailsVO;
import com.bjike.goddess.costdetail.vo.DetailTypeVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 明细分类
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-07 09:43 ]
 * @Description: [ 明细分类 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("detailtype")
public class DetailTypeAction {

    @Autowired
    private DetailTypeAPI detailTypeAPI;

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

            List<SonPermissionObject> hasPermissionList = detailTypeAPI.sonPermission();
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

            Boolean isHasPermission = detailTypeAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param detailTypeDTO 明细分类
     * @des 获取所有明细分类总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DetailTypeDTO detailTypeDTO) throws ActException {
        try {
            Long count = detailTypeAPI.countDetailType(detailTypeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个明细分类
     *
     * @param id 明细分类id
     * @return class DetailTypeVO
     * @des 根据id明细分类
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            DetailTypeVO detailTypeVO = BeanTransform.copyProperties(
                    detailTypeAPI.getOneById(id), DetailTypeVO.class);
            return ActResult.initialize(detailTypeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 明细分类列表
     *
     * @param detailTypeDTO 明细分类dto
     * @return class DetailTypeVO
     * @des 获取所有明细分类
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(DetailTypeDTO detailTypeDTO, HttpServletRequest request) throws ActException {
        try {
            List<DetailTypeVO> detailTypeVOS = BeanTransform.copyProperties(
                    detailTypeAPI.list(detailTypeDTO), DetailTypeVO.class, request);
            return ActResult.initialize(detailTypeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 添加明细分类
     *
     * @param detailTypeTO 明细分类to
     * @return class DetailTypeVO
     * @des 添加明细分类
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCost(@Validated({ADD.class}) DetailTypeTO detailTypeTO, BindingResult result) throws ActException {
        try {
            DetailTypeBO detailTypeBO = detailTypeAPI.add(detailTypeTO);
            return ActResult.initialize(BeanTransform.copyProperties(detailTypeBO, DetailTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑明细分类
     *
     * @param detailTypeTO 明细分类bo
     * @return class DetailTypeVO
     * @des 编辑明细分类
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editCost(@Validated({EDIT.class}) DetailTypeTO detailTypeTO, BindingResult result) throws ActException {
        try {
            DetailTypeBO detailTypeBO = detailTypeAPI.edit(detailTypeTO);
            return ActResult.initialize(BeanTransform.copyProperties(detailTypeBO, DetailTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id
     * @des 根据id删除成本明细
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            detailTypeAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据父节点查询所有明细分类数据
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllByNode")
    public Result findAllByNode(@RequestParam String parNode) throws ActException {
        try {
            List<DetailTypeBO> detailTypeBOS = detailTypeAPI.findByNode(parNode);
            List<DetailTypeVO> detailTypeVOS = BeanTransform.copyProperties(detailTypeBOS, DetailTypeVO.class);
            return ActResult.initialize(detailTypeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据父节点查询所有明细分类名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findTypeName")
    public Result findTypeName(@RequestParam String parNode) throws ActException {
        try {
            List<String> typeName = new ArrayList<>();
            typeName = detailTypeAPI.findTypeName(parNode);
            return ActResult.initialize(typeName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}