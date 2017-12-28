package com.bjike.goddess.announcement.action.announcement;

import com.bjike.goddess.announcement.api.ClassAPI;
import com.bjike.goddess.announcement.bo.ClassBO;
import com.bjike.goddess.announcement.dto.ClassDTO;
import com.bjike.goddess.announcement.to.ClassTO;
import com.bjike.goddess.announcement.to.GuidePermissionTO;
import com.bjike.goddess.announcement.vo.ClassVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公告分类
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:20 ]
 * @Description: [ 公告分类 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("classes")
public class ClassAct {
    @Autowired
    private ClassAPI classAPI;

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

            Boolean isHasPermission = classAPI.guidePermission(guidePermissionTO);
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
     * 列表
     *
     * @param dto 公告分类数据传输
     * @return class ClassVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ClassDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ClassBO> list = classAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ClassVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 公告分类传输对象
     * @return class ClassVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ClassTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ClassBO bo = classAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ClassVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 公告分类id
     * @return class ClassVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/classes/{id}")
    public Result classes(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ClassBO bo = classAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ClassVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 公告分类传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ClassTO to, BindingResult result) throws ActException {
        try {
            classAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 公告分类id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            classAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 公告分类数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ClassDTO dto) throws ActException {
        try {
            return ActResult.initialize(classAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有分类
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allClass")
    public Result allClass() throws ActException {
        try {
            return ActResult.initialize(classAPI.allClass());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}