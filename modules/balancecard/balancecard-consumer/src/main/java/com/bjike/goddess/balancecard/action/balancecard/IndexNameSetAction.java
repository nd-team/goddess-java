package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.IndexNameSetAPI;
import com.bjike.goddess.balancecard.bo.IndexNameSetBO;
import com.bjike.goddess.balancecard.dto.IndexNameSetDTO;
import com.bjike.goddess.balancecard.to.IndexNameSetTO;
import com.bjike.goddess.balancecard.vo.IndexNameSetVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 指标名称设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:02 ]
 * @Description: [ 指标名称设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("indexnameset")
public class IndexNameSetAction {


    @Autowired
    private IndexNameSetAPI indexNameSetAPI;

    /**
     * 列表总条数
     *
     * @param indexNameSetDTO  指标名称信息dto
     * @des 获取所有指标名称信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(IndexNameSetDTO indexNameSetDTO) throws ActException {
        try {
            Long count = indexNameSetAPI.countIndexNameSet(indexNameSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 指标名称列表
     *
     * @param indexNameSetDTO 指标名称信息dto
     * @param request 前端过滤参数
     * @des 获取所有指标名称信息
     * @return  class IndexNameSetVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListIndexNameSet(IndexNameSetDTO indexNameSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<IndexNameSetVO> indexNameSetVOList = BeanTransform.copyProperties(
                    indexNameSetAPI.listIndexNameSet(indexNameSetDTO), IndexNameSetVO.class, request);
            return ActResult.initialize(indexNameSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个指标名称
     *
     * @param id 指标名称信息id
     * @des 获取所有指标名称信息
     * @return  class IndexNameSetVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            IndexNameSetVO indexNameSetVOList = BeanTransform.copyProperties(
                    indexNameSetAPI.getOneById( id), IndexNameSetVO.class);
            return ActResult.initialize(indexNameSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加指标名称
     *
     * @param indexNameSetTO 指标名称基本信息数据to
     * @des 添加指标名称
     * @return  class IndexNameSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addIndexNameSet(@Validated IndexNameSetTO indexNameSetTO, BindingResult bindingResult) throws ActException {
        try {
            IndexNameSetBO indexNameSetBO1 = indexNameSetAPI.addIndexNameSet(indexNameSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(indexNameSetBO1,IndexNameSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑指标名称
     *
     * @param indexNameSetTO 指标名称基本信息数据bo
     * @des 编辑指标名称
     * @return  class IndexNameSetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editIndexNameSet(@Validated IndexNameSetTO indexNameSetTO) throws ActException {
        try {
            IndexNameSetBO indexNameSetBO1 = indexNameSetAPI.editIndexNameSet(indexNameSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(indexNameSetBO1,IndexNameSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除指标名称信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteIndexNameSet(@PathVariable String id) throws ActException {
        try {
            indexNameSetAPI.deleteIndexNameSet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 获取所有指标名称
     *
     * @des 获取所有指标名称
     * @version v1
     */
    @GetMapping("v1/listName")
    public Result listName( ) throws ActException {
        try {
            List<String> dimensionSetVOList = indexNameSetAPI.listName( ) ;
            return ActResult.initialize(dimensionSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}