package com.bjike.goddess.otherexpenses.action.otherexpenses;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.otherexpenses.api.OtherExpensesAPI;
import com.bjike.goddess.otherexpenses.dto.OtherExpensesDTO;
import com.bjike.goddess.otherexpenses.to.CollectTO;
import com.bjike.goddess.otherexpenses.to.OtherExpensesTO;
import com.bjike.goddess.otherexpenses.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 其他费用
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-03 11:49 ]
 * @Description: [ 其他费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("otherexpenses")
public class OtherExpensesAction {

    @Autowired
    private OtherExpensesAPI otherExpensesAPI;

    /**
     * 保存
     *
     * @param to 其他费用传输对象
     * @return class OtherExpensesVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) OtherExpensesTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.save(to), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 其他费用传输对象
     * @return class OtherExpensesVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) OtherExpensesTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.update(to), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 其他费用数据id
     * @return class OtherExpensesVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.delete(id), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询其他费用数据
     *
     * @param id 其他费用数据id
     * @return class OtherExpensesVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.getById(id), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 其他费用数据传输对象
     * @return class OtherExpensesVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(OtherExpensesDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.maps(dto), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param to 汇总条件传输对象
     * @return class AreaCollectVO
     * @version v1
     */
    @GetMapping("v1/collect/area")
    public Result areaCollect(CollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.areaCollect(to), AreaCollectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目名称汇总
     *
     * @param to 汇总条件传输对象
     * @return class NameCollectVO
     * @version v1
     */
    @GetMapping("v1/collect/name")
    public Result nameCollect(CollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.nameCollect(to), NameCollectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 类型汇总
     *
     * @param to 汇总条件传输对象
     * @return class TypeCollectVO
     * @version v1
     */
    @GetMapping("v1/collect/type")
    public Result typeCollect(CollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.typeCollect(to), TypeCollectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param to 汇总条件传输对象
     * @return class ProjectCollectVO
     * @version v1
     */
    @GetMapping("v1/collect/project")
    public Result projectCollect(CollectTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.projectCollect(to), ProjectCollectVO.class, request));
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
            return ActResult.initialize(otherExpensesAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}