package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.CategoryBO;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.financeinit.service.CategorySer;
import com.bjike.goddess.financeinit.to.CategoryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类别业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:18 ]
 * @Description: [ 类别业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("categoryApiImpl")
public class CategoryApiImpl implements CategoryAPI {

    @Autowired
    private CategorySer categorySer;


    @Override
    public Long countCategory(CategoryDTO categoryDTO) throws SerException {
        return categorySer.countCategory( categoryDTO );
    }

    @Override
    public CategoryBO getOneById(String id) throws SerException {
        return categorySer.getOneById(id);
    }

    @Override
    public List<CategoryBO> listCategory(CategoryDTO categoryDTO) throws SerException {
        return categorySer.listCategory(categoryDTO);
    }

    @Override
    public CategoryBO addCategory(CategoryTO categoryTO) throws SerException {
        return categorySer.addCategory(categoryTO);
    }

    @Override
    public CategoryBO editCategory(CategoryTO categoryTO) throws SerException {
        return categorySer.editCategory(categoryTO);
    }

    @Override
    public void deleteCategory(String id) throws SerException {
        categorySer.deleteCategory(id);
    }

    @Override
    public List<CategoryBO> listAllCategory(CategoryDTO categoryDTO) throws SerException {
        return categorySer.listAllCategory(categoryDTO);
    }

    @Override
    public CategoryBO listById(String id) throws SerException {
        return categorySer.listById(id);
    }

    @Override
    public List<String> getSecondSubject(CategoryDTO categoryDTO) throws SerException {
        return categorySer.getSecondSubject(categoryDTO);
    }

    @Override
    public List<String> getThirdSubject(CategoryDTO categoryDTO) throws SerException {
        return categorySer.getThirdSubject(categoryDTO);
    }

    @Override
    public List<String> listFirstName(CategoryTO categoryTO) throws SerException {
        return categorySer.listFirstName(categoryTO);
    }
}