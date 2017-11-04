package com.bjike.goddess.rentutilitiespay.action.rentutilitiespay;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.api.StencilAPI;
import com.bjike.goddess.rentutilitiespay.bo.StencilBO;
import com.bjike.goddess.rentutilitiespay.dto.StencilDTO;
import com.bjike.goddess.rentutilitiespay.to.StencilTO;
import com.bjike.goddess.rentutilitiespay.vo.StencilVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 模板
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-24 10:50 ]
* @Description:	[ 模板 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("stencil")
public class StencilAction {
    @Autowired
    private StencilAPI stencilAPI;

    /**
     * 查询
     * @param stencilDTO
     * @return class StencilVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(StencilDTO stencilDTO) throws ActException{
        try {
            List<StencilBO> boList = stencilAPI.pageList(stencilDTO);
            List<StencilVO> voList = BeanTransform.copyProperties(boList,StencilVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增
     * @param stencilTO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) StencilTO stencilTO,BindingResult bindingResult) throws ActException{
        try {
            stencilAPI.add(stencilTO);
            return new ActResult("添加成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     * @param stencilTO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/modify")
    public Result edit(@Validated(EDIT.class) StencilTO stencilTO) throws ActException{
        try {
            stencilAPI.edit(stencilTO);
            return new ActResult("修改成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     * @param id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@RequestParam String id) throws ActException{
        try {
            stencilAPI.delete(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询单条数据
     * @param id
     * @return class StencilVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException{
        try {
            StencilBO bo = stencilAPI.findOne(id);
            StencilVO vo = BeanTransform.copyProperties(bo,StencilVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表总条数
     * @param stencilDTO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StencilDTO stencilDTO) throws ActException{
        try {
            Long number = stencilAPI.count(stencilDTO);
            return ActResult.initialize(number);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }




}