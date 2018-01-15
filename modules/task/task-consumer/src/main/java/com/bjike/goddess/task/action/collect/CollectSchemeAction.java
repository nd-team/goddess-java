package com.bjike.goddess.task.action.collect;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.api.CollectSchemeAPI;
import com.bjike.goddess.task.bo.CollectSchemeBO;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CollectSchemeDTO;
import com.bjike.goddess.task.to.CollectSchemeTO;
import com.bjike.goddess.task.vo.CollectSchemeVO;
import com.bjike.goddess.task.vo.CustomizeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 汇总方案
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 04:33 ]
 * @Description: [ 汇总方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collectscheme")
public class CollectSchemeAction {
    @Autowired
    private CollectSchemeAPI collectSchemeAPI;

    /**
     * 列表
     *
     * @param dto 汇总方案数据传输
     * @return class CollectSchemeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CollectSchemeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CollectSchemeBO> list = collectSchemeAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CollectSchemeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总表
     *
     * @return class CustomizeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/all")
    public Result all(HttpServletRequest request) throws ActException {
        try {
            List<CustomizeBO> list = collectSchemeAPI.all();
            return ActResult.initialize(BeanTransform.copyProperties(list, CustomizeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 通过id查找
     *
     * @param id 汇总方案id
     * @return class CollectSchemeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/repayanalyzeadvice/{id}")
    public Result repayanalyzeadvice(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CollectSchemeBO bo = collectSchemeAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CollectSchemeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 汇总方案传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) CollectSchemeTO to, BindingResult result) throws ActException {
        try {
            collectSchemeAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 查找总记录数
     *
     * @param dto 汇总方案数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CollectSchemeDTO dto) throws ActException {
        try {
            return ActResult.initialize(collectSchemeAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 即时汇总
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collect/{id}")
    public Result collect(@PathVariable String id) throws ActException {
        try {
            String result = collectSchemeAPI.collect(id);
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 汇总字段
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/fileds")
    public Result fileds(@Validated(CollectSchemeTO.FIELD.class) CollectSchemeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(collectSchemeAPI.fileds(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * _-------------------------------------------------------------
     *  新增功能点
     */

    /**
     * 添加
     *
     * @param to 汇总方案传输对象
     * @return class CollectSchemeVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CollectSchemeTO to, BindingResult result) throws ActException {
        try {
            collectSchemeAPI.save(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 汇总方案id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            collectSchemeAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看详情
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/detail/{id}")
    public Result detail(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(collectSchemeAPI.detail(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 现在通报
     *
     * @param
     * @throws ActException
     * @version v1
     *
     */
    @GetMapping("v1/send/{id}")
    public Result send(@PathVariable String id) throws ActException {
        try {
            collectSchemeAPI.send(id);
            return new ActResult("通报成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 设置通报
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/notice")
    public Result notice(@Validated(CollectSchemeTO.NOTICE.class) CollectSchemeTO to, BindingResult result) throws ActException {
        try {
            collectSchemeAPI.notice(to);
            return new ActResult("设置通报成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }






}