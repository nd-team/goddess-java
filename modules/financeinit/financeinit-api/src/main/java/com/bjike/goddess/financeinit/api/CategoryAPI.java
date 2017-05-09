package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.CategoryBO;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.financeinit.to.CategoryTO;

import java.util.List;

/**
 * 类别业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:18 ]
 * @Description: [ 类别业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CategoryAPI {

    /**
     * 类别列表总条数
     *
     */
    default Long countCategory(CategoryDTO categoryDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取类别列表
     * @return class CategoryBO
     */
    default CategoryBO getOneById(String id) throws SerException {return null;}

    
    /**
     * 类别列表
     * @return class CategoryBO
     */
    default List<CategoryBO> listCategory(CategoryDTO categoryDTO) throws SerException {return null;}
    /**
     *  添加
     * @param categoryTO 类别信息
     * @return class CategoryBO
     */
    default CategoryBO addCategory(CategoryTO categoryTO) throws SerException { return null;}

    /**
     *  编辑
     * @param categoryTO 类别信息
     * @return class CategoryBO
     */
    default CategoryBO editCategory(CategoryTO categoryTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteCategory(String id ) throws SerException {return;};

    /**
     * 类别列表
     * @return class CategoryBO
     */
    default List<CategoryBO> listAllCategory(CategoryDTO categoryDTO) throws SerException {return null;}

    /**
     * 根据id查一级和二级列表
     * @return class CategoryBO
     */
    default CategoryBO listById (String id) throws SerException {return null;}
    /**
     * 通过一级查二级级别列表
     * @return class String
     */
    default List<String> getSecondSubject(CategoryDTO categoryDTO) throws SerException {return null;}

    /**
     * 通过一级和二级查询三级级别列表
     * @return class String
     */
    default List<String> getThirdSubject(CategoryDTO categoryDTO) throws SerException {return null;}


    /**
     * 获取对应类的一级名
     */
    default List<String> listFirstName(CategoryTO categoryTO) throws SerException {return null;}




}