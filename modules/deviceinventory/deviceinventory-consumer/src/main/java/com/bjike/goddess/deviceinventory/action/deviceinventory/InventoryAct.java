package com.bjike.goddess.deviceinventory.action.deviceinventory;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.deviceinventory.api.InventoryAPI;
import com.bjike.goddess.deviceinventory.bo.InventoryBO;
import com.bjike.goddess.deviceinventory.dto.InventoryDTO;
import com.bjike.goddess.deviceinventory.to.GuidePermissionTO;
import com.bjike.goddess.deviceinventory.to.InventoryTO;
import com.bjike.goddess.deviceinventory.vo.InventoryVO;
import com.bjike.goddess.deviceinventory.vo.SonPermissionObject;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 盘点
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 盘点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("inventory")
public class InventoryAct extends BaseFileAction {
    @Autowired
    private InventoryAPI inventoryAPI;
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
    public Result i() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = inventoryAPI.sonPermission();
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

            Boolean isHasPermission = inventoryAPI.guidePermission(guidePermissionTO);
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
     * 盘点列表
     *
     * @param dto 盘点dto
     * @return class InventoryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated(InventoryDTO.LIST.class) InventoryDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<InventoryBO> list = inventoryAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, InventoryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找盘点信息
     *
     * @param id 盘点id
     * @return class InventoryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/inventoryOne/{id}")
    public Result inventoryOne(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InventoryBO bo = inventoryAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, InventoryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 盘点
     *
     * @param to 盘点to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/inventory")
    public Result inventory(@Validated({EDIT.class}) InventoryTO to, BindingResult result) throws ActException {
        try {
            inventoryAPI.inventory(to);
            return ActResult.initialize("盘点成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel(根据时间区间，时间具体到日)
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export/{startTime}/{endTime}")
    public Result export(@PathVariable String startTime, @PathVariable String endTime, HttpServletResponse response) throws ActException {
        try {
            String fileName = "设备入库盘点表.xlsx";
            super.writeOutFile(response, inventoryAPI.export(startTime, endTime), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 盘点dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InventoryDTO dto) throws ActException {
        try {
            return ActResult.initialize(inventoryAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有入库编号
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allstockEncoding")
    public Result allstockEncoding() throws ActException {
        try {
            return ActResult.initialize(inventoryAPI.allstockEncoding());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}