package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.DataAPI;
import com.bjike.goddess.reportmanagement.bo.DataBO;
import com.bjike.goddess.reportmanagement.dto.DataDTO;
import com.bjike.goddess.reportmanagement.to.DataTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 补充资料表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 02:29 ]
 * @Description: [ 补充资料表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("data")
public class DataAction {

    @Autowired
    private DataAPI dataAPI;

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

            Boolean isHasPermission = dataAPI.guidePermission(guidePermissionTO);
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
     * @param dto 项目数据传输
     * @return class DataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DataDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<DataBO> list = dataAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DataVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 项目传输对象
     * @return class DataVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DataTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            dataAPI.save(to);
            return ActResult.initialize("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 项目id
     * @return class DataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/data/{id}")
    public Result data(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DataBO bo = dataAPI.findByID(id);
            DataVO vo = BeanTransform.copyProperties(bo, DataVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 项目传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) DataTO to, BindingResult result) throws ActException {
        try {
            dataAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            dataAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 项目数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DataDTO dto) throws ActException {
        try {
            return ActResult.initialize(dataAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}