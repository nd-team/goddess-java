package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.CollectSendAPI;
import com.bjike.goddess.supplier.dto.CollectSendDTO;
import com.bjike.goddess.supplier.to.CollectSendTO;
import com.bjike.goddess.supplier.to.CollectTo;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.supplier.vo.CollectSendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 供应商汇总
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-14 11:48 ]
 * @Description: [ 供应商汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collectsend")
public class CollectSendAction {

    @Autowired
    private CollectSendAPI collectSendAPI;

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

            Boolean isHasPermission = collectSendAPI.guidePermission(guidePermissionTO);
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
     * @param to 供应商汇总传输对象
     * @return class CollectSendVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CollectSendTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectSendAPI.save(to), CollectSendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 供应商汇总传输对象
     * @return class CollectSendVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CollectSendTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectSendAPI.update(to), CollectSendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 供应商汇总数据id
     * @return class CollectSendVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectSendAPI.delete(id), CollectSendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 供应商汇总数据id
     * @return class CollectSendVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectSendAPI.congeal(id), CollectSendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 供应商汇总数据id
     * @return class CollectSendVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectSendAPI.thaw(id), CollectSendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取供应商汇总数据
     *
     * @param id 供应商汇总数据id
     * @return class CollectSendVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectSendAPI.getById(id), CollectSendVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 供应商汇总数据传输对象
     * @return class CollectSendVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CollectSendDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(collectSendAPI.maps(dto), CollectSendVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @param dto 供应商汇总数据传输对象
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(CollectSendDTO dto) throws ActException {
        try {
            return ActResult.initialize(collectSendAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 返回汇总表格
     *
     * @param to 供应商汇总传输对象
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(CollectTo to) throws ActException {
        try {
            return ActResult.initialize(collectSendAPI.collect(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}