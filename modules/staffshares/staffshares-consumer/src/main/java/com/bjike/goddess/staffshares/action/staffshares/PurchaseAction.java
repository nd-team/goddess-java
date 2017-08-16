package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.PurchaseAPI;
import com.bjike.goddess.staffshares.bo.PurchaseBO;
import com.bjike.goddess.staffshares.dto.PurchaseDTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.staffshares.vo.PurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 干股申购表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("purchase")
public class PurchaseAction {
    @Autowired
    private PurchaseAPI purchaseAPI;

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

            Boolean isHasPermission = purchaseAPI.guidePermission(guidePermissionTO);
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
     * 编辑
     *
     * @param to 干股申购传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PurchaseTO to, BindingResult result) throws ActException {
        try {
            purchaseAPI.update(to);
            return ActResult.initialize("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 撤销
     *
     * @param id id
     * @des 根据id撤销干股申购记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            purchaseAPI.delete(id);
            return new ActResult("撤销成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to 干股申购传输对象
     * @version v1
     */
    @PutMapping("v1/examine/{id}")
    public Result examine(@Validated(ADD.class) PurchaseTO to, BindingResult result) throws ActException {
        try {
            purchaseAPI.examine(to);
            return ActResult.initialize("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 干股申购表列表
     *
     * @param dto 干股申购表数据传输对象
     * @return class PurchaseVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(PurchaseDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<PurchaseBO> list = purchaseAPI.list(dto);
            if (null != list && list.size() > 0) {
                return ActResult.initialize(BeanTransform.copyProperties(list, PurchaseVO.class, request));
            } else {
                return ActResult.initialize(list);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获得一条干股申购表
     *
     * @param id id
     * @return class PurchaseVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(purchaseAPI.getById(id), PurchaseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取干股申购表总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(PurchaseDTO dto) throws ActException {
        try {
            return ActResult.initialize(purchaseAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}