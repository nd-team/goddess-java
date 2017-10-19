package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.CategoryAPI;
import com.bjike.goddess.financeinit.bo.CategoryBO;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.financeinit.to.CategoryTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.vo.CategoryVO;
import com.bjike.goddess.financeinit.vo.FirstSubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 类别
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:18 ]
 * @Description: [ 类别 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("category")
public class CategoryAction {


    @Autowired
    private CategoryAPI categoryAPI;


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

            Boolean isHasPermission = categoryAPI.guidePermission(guidePermissionTO);
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
     * @param categoryDTO 类别信息dto
     * @des 获取所有类别信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated(CategoryDTO.TestList.class) CategoryDTO categoryDTO) throws ActException {
        try {
            Long count = categoryAPI.countCategory(categoryDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个类别
     *
     * @param id 项目类别信息id
     * @des 根据id获取项目类别信息
     * @return  class CategoryVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CategoryBO categoryBO = BeanTransform.copyProperties(categoryAPI.getOneById(id), CategoryBO.class );
            CategoryVO projectCarryVO = BeanTransform.copyProperties(
                    categoryBO, CategoryVO.class);
            if( categoryBO != null && null != categoryBO.getFirstSubjectBO()){
                FirstSubjectVO firstSubjectVO = BeanTransform.copyProperties(categoryBO.getFirstSubjectBO(), FirstSubjectVO.class);
                projectCarryVO.setFirstSubjectVO(firstSubjectVO);
            }


            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 类别列表
     *
     * @param categoryDTO 类别信息dto
     * @return class CategoryVO
     * @des 获取所有类别信息
     * @version v1
     */
    @GetMapping("v1/listCategory")
    public Result findListCategory(@Validated(CategoryDTO.TestList.class) CategoryDTO categoryDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CategoryBO> categoryBOList = categoryAPI.listCategory(categoryDTO);
            List<CategoryVO> categoryVOList = BeanTransform.copyProperties(
                    categoryBOList , CategoryVO.class , request);
            List<CategoryVO> categoryVOLists = new ArrayList<>();
            categoryBOList.stream().forEach(str -> {
                FirstSubjectVO firstSubjectVO = BeanTransform.copyProperties(str.getFirstSubjectBO(), FirstSubjectVO.class);
                CategoryVO cv= BeanTransform.copyProperties(str, CategoryVO.class , request);
                cv.setFirstSubjectVO(firstSubjectVO);
                categoryVOLists.add(cv);
            });
            return ActResult.initialize(categoryVOLists);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加类别
     *
     * @param categoryTO 类别基本信息数据to
     * @return class CategoryVO
     * @des 添加类别
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCategory(@Validated(CategoryTO.TestAdd.class) CategoryTO categoryTO, BindingResult bindingResult) throws ActException {
        try {
            CategoryBO categoryBO1 = categoryAPI.addCategory(categoryTO);
            return ActResult.initialize(BeanTransform.copyProperties(categoryBO1, CategoryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑类别
     *
     * @param categoryTO 类别基本信息数据bo
     * @return class CategoryVO
     * @des 添加类别
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCategory(@Validated(CategoryTO.TestAdd.class) CategoryTO categoryTO) throws ActException {
        try {
            CategoryBO categoryBO1 = categoryAPI.editCategory(categoryTO);
            return ActResult.initialize(BeanTransform.copyProperties(categoryBO1, CategoryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除类别信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCategory(@PathVariable String id) throws ActException {
        try {
            categoryAPI.deleteCategory(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 获取对应类的一级名
     *
     * @param categoryTO 类别基本信息数据bo
     * @des 获取对应类的一级名
     * @version v1
     */
    @GetMapping("v1/listFirstName")
    public Result listFirstName(@Validated(CategoryTO.TestGetFirst.class) CategoryTO categoryTO) throws ActException {
        try {
            List<String> projectCarryVO =categoryAPI.listFirstName(categoryTO);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}