package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.CategoryAPI;
import com.bjike.goddess.financeinit.bo.CategoryBO;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.financeinit.to.CategoryTO;
import com.bjike.goddess.financeinit.vo.CategoryVO;
import com.bjike.goddess.financeinit.vo.FirstSubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("financeinit/category")
public class CategoryAction {


    @Autowired
    private CategoryAPI categoryAPI;

    /**
     * 类别列表
     *
     * @param categoryDTO 类别信息dto
     * @return class CategoryVO
     * @des 获取所有类别信息
     * @version v1
     */
    @GetMapping("v1/listCategory")
    public Result findListCategory(CategoryDTO categoryDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CategoryBO> categoryBOList = categoryAPI.listCategory(categoryDTO);
            List<CategoryVO> categoryVOList = BeanTransform.copyProperties(
                    categoryBOList , CategoryVO.class);
            categoryBOList.stream().forEach(str -> {
                FirstSubjectVO firstSubjectVO = BeanTransform.copyProperties(str.getFirstSubjectBO(), FirstSubjectVO.class);
                CategoryVO cv= BeanTransform.copyProperties(str, CategoryVO.class);
                cv.setFirstSubjectVO(firstSubjectVO);
                categoryVOList.add(cv);
            });
            return ActResult.initialize(categoryVOList);
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
    @PostMapping("v1/add")
    public Result addCategory(@Validated CategoryTO categoryTO, BindingResult bindingResult) throws ActException {
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
    @PutMapping("v1/edit")
    public Result editCategory(@Validated CategoryTO categoryTO) throws ActException {
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
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCategory(@PathVariable String id) throws ActException {
        try {
            categoryAPI.deleteCategory(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


}