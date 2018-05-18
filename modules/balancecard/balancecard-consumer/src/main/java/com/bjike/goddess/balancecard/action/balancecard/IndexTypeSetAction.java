package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.IndexTypeSetAPI;
import com.bjike.goddess.balancecard.bo.IndexTypeSetBO;
import com.bjike.goddess.balancecard.dto.IndexTypeSetDTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.IndexTypeSetTO;
import com.bjike.goddess.balancecard.vo.IndexTypeSetVO;
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
 * 指标类型
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 指标类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("indextypeset")
public class IndexTypeSetAction {



    @Autowired
    private IndexTypeSetAPI indexTypeSetAPI;

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

            Boolean isHasPermission = indexTypeSetAPI.guidePermission(guidePermissionTO);
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
     * @param indexTypeSetDTO  指标类型信息dto
     * @des 获取所有指标类型信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(IndexTypeSetDTO indexTypeSetDTO) throws ActException {
        try {
            Long count = indexTypeSetAPI.countIndexTypeSet(indexTypeSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 指标类型列表
     *
     * @param indexTypeSetDTO 指标类型信息dto
     * @param request 前端过滤参数
     * @des 获取所有指标类型信息
     * @return  class IndexTypeSetVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListIndexTypeSet(IndexTypeSetDTO indexTypeSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<IndexTypeSetVO> indexTypeSetVOList = BeanTransform.copyProperties(
                    indexTypeSetAPI.listIndexTypeSet(indexTypeSetDTO), IndexTypeSetVO.class, request);
            return ActResult.initialize(indexTypeSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个指标类型
     *
     * @param id 指标类型信息id
     * @des 获取所有指标类型信息
     * @return  class IndexTypeSetVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            IndexTypeSetVO indexTypeSetVOList = BeanTransform.copyProperties(
                    indexTypeSetAPI.getOneById( id), IndexTypeSetVO.class);
            return ActResult.initialize(indexTypeSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加指标类型
     *
     * @param indexTypeSetTO 指标类型基本信息数据to
     * @des 添加指标类型
     * @return  class IndexTypeSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addIndexTypeSet(@Validated IndexTypeSetTO indexTypeSetTO, BindingResult bindingResult) throws ActException {
        try {
            IndexTypeSetBO indexTypeSetBO1 = indexTypeSetAPI.addIndexTypeSet(indexTypeSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(indexTypeSetBO1,IndexTypeSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑指标类型
     *
     * @param indexTypeSetTO 指标类型基本信息数据bo
     * @des 编辑指标类型
     * @return  class IndexTypeSetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editIndexTypeSet(@Validated IndexTypeSetTO indexTypeSetTO) throws ActException {
        try {
            IndexTypeSetBO indexTypeSetBO1 = indexTypeSetAPI.editIndexTypeSet(indexTypeSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(indexTypeSetBO1,IndexTypeSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除指标类型信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteIndexTypeSet(@PathVariable String id) throws ActException {
        try {
            indexTypeSetAPI.deleteIndexTypeSet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 获取所有指标类型
     *
     * @des 获取所有指标类型
     * @version v1
     */
    @GetMapping("v1/listName")
    public Result listName( ) throws ActException {
        try {
            List<String> dimensionSetVOList = indexTypeSetAPI.listName( ) ;
            return ActResult.initialize(dimensionSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    
}