package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.CategoryBO;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.financeinit.entity.Category;
import com.bjike.goddess.financeinit.entity.FirstSubject;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.to.CategoryTO;
import com.bjike.goddess.financeinit.vo.FirstSubjectVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 类别业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:18 ]
 * @Description: [ 类别业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class CategorySerImpl extends ServiceImpl<Category, CategoryDTO> implements CategorySer {

    @Autowired
    private FirstSubjectSer firstSubjectSer;

    @Override
    public List<CategoryBO> listCategory(CategoryDTO categoryDTO) throws SerException {
        List<Category> list = super.findByCis(categoryDTO, true);
        List<CategoryBO> categoryBOList = new ArrayList<>();
        list.stream().forEach(str -> {
            FirstSubjectBO firstSubjectBO = BeanTransform.copyProperties(str.getFirstSubject(), FirstSubjectBO.class);
            CategoryBO cb = BeanTransform.copyProperties(str, CategoryBO.class);
            cb.setFirstSubjectBO(firstSubjectBO);
            categoryBOList.add(cb);
        });
        return categoryBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CategoryBO addCategory(CategoryTO categoryTO) throws SerException {
        String firstSubjectName = categoryTO.getFirstSubjectName();
        FirstSubjectBO firstSubjectBO = firstSubjectSer.getFirstSubject(firstSubjectName);
        if (firstSubjectBO == null || StringUtils.isBlank(firstSubjectBO.getCode())) {
            throw new SerException("不存在该一级科目，请重新选择一级科目");
        }
        CategoryDTO dto = new CategoryDTO();
        dto.getConditions().add(Restrict.eq("secondSubject", categoryTO.getSecondSubject()));
        dto.getConditions().add(Restrict.eq("thirdSubject", categoryTO.getThirdSubject()));
        dto.getConditions().add(Restrict.eq("firstSubject_id", BeanTransform.copyProperties(firstSubjectBO, FirstSubject.class, true)));
        Long count = super.count(dto);
        if (count > 0) {
            throw new SerException("已存在相同数据");
        }

        String categoryFromFirstSubject = firstSubjectBO.getCategory();
        CategoryName categoryName = CategoryName.ASSETS;
        switch (categoryFromFirstSubject) {
            case "资产类":
                categoryName = CategoryName.ASSETS;
                break;
            case "负债类":
                categoryName = CategoryName.LIABILITIES;
                break;
            case "共同类":
                categoryName = CategoryName.COMMON;
                break;
            case "权益类":
                categoryName = CategoryName.RIGHTSINTERESTS;
                break;
            case "成本类":
                categoryName = CategoryName.COST;
                break;
            case "损益类":
                categoryName = CategoryName.PROFITLOSS;
                break;
            default:
                categoryName = CategoryName.ASSETS;
                break;
        }

        //1000 2000 3000 ...
        dto = new CategoryDTO();
        String code = generateCode(categoryTO, firstSubjectBO, dto);

        FirstSubject firstSubject = BeanTransform.copyProperties(firstSubjectBO, FirstSubject.class, true);
        Category category = BeanTransform.copyProperties(categoryTO, Category.class, true);
        category.setFirstSubject(firstSubject);
        category.setCreateTime(LocalDateTime.now());
        category.setCategoryName(categoryName);
        category.setCode(code);
        super.save(category);
        return BeanTransform.copyProperties(category, CategoryBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CategoryBO editCategory(CategoryTO categoryTO) throws SerException {
        String firstSubjectName = categoryTO.getFirstSubjectName();
        FirstSubjectBO firstSubjectBO = firstSubjectSer.getFirstSubject(firstSubjectName);
        if (firstSubjectBO == null || StringUtils.isBlank(firstSubjectBO.getCode())) {
            throw new SerException("不存在该一级科目，请重新选择一级科目");
        }

        Category tranCategory = BeanTransform.copyProperties(categoryTO, Category.class, true);
        Category category = super.findById(categoryTO.getId());
        BeanUtils.copyProperties(tranCategory, category, "id", "firstSubject_id", "createTime");
        category.setFirstSubject(BeanTransform.copyProperties(firstSubjectBO, FirstSubject.class, true));

        super.update(category);
        return BeanTransform.copyProperties(category, CategoryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCategory(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<String> getSecondSubject(CategoryDTO categoryDTO) throws SerException {
        String[] fields = new String[]{"secondSubject"};
        List<CategoryBO> categoryBOList = super.findBySql("select fc.secondSubject ,1 from financeinit_category fc " +
                " inner join financeinit_firstsubject ff on fc.firstSubject_id = ff.id where ff.name = '" + categoryDTO.getFirstSubjectName() + "'", CategoryBO.class, fields);

        List<String> list = categoryBOList.stream().map(CategoryBO::getSecondSubject)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());

        return list;
    }

    @Override
    public List<String> getThirdSubject(CategoryDTO categoryDTO) throws SerException {
        String[] fields = new String[]{"thirdSubject"};
        List<CategoryBO> categoryBOList = super.findBySql("select fc.thirdSubject ,1 from financeinit_category fc " +
                " inner join financeinit_firstsubject ff on fc.firstSubject_id = ff.id " +
                " where ff.name = '" + categoryDTO.getFirstSubjectName() + "' and fc.secondSubject = '" + categoryDTO.getSecondSubject() + "'", CategoryBO.class, fields);

        List<String> list = categoryBOList.stream().map(CategoryBO::getThirdSubject)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());

        return list;
    }


    public String generateCode(CategoryTO categoryTO, FirstSubjectBO firstSubjectBO, CategoryDTO dto) throws SerException {
        String code = "";
        CategoryDTO cdto = new CategoryDTO();
        String firstSubjectCode = firstSubjectBO.getCode();
        categoryTO.setFirstCode(firstSubjectCode);

        switch (firstSubjectBO.getCategory()) {
            case "资产类":
                cdto.getConditions().add(Restrict.eq("categoryName", CategoryName.ASSETS));
                break;
            case "负债类":
                cdto.getConditions().add(Restrict.eq("categoryName", CategoryName.LIABILITIES));
                break;
            case "共同类":
                cdto.getConditions().add(Restrict.eq("categoryName", CategoryName.COMMON));
                break;
            case "权益类":
                cdto.getConditions().add(Restrict.eq("categoryName", CategoryName.RIGHTSINTERESTS));
                break;
            case "成本类":
                cdto.getConditions().add(Restrict.eq("categoryName", CategoryName.COST));
                break;
            case "损益类":
                cdto.getConditions().add(Restrict.eq("categoryName", CategoryName.PROFITLOSS));
                break;
            default:
                cdto.getConditions().add(Restrict.eq("categoryName", CategoryName.ASSETS));
                break;
        }


        if (StringUtils.isNotBlank(categoryTO.getFirstSubjectName())) {
            dto.getConditions().add(Restrict.eq("firstSubject_id", BeanTransform.copyProperties(firstSubjectBO, FirstSubject.class, true)));
        }
        //有一二级情况下
        if (StringUtils.isNotBlank(categoryTO.getSecondSubject())
                && StringUtils.isBlank(categoryTO.getThirdSubject())) {
            dto.getConditions().add(Restrict.eq("secondSubject", categoryTO.getSecondSubject()));
            List<Category> categories = super.findByCis(cdto);
            if (categories != null && categories.size() > 0) {
                throw new SerException("已存在该一级科目下的二级科目");

            } else {
                //不存在就要取最大加一
                CategoryDTO temp_cdto = new CategoryDTO();
                temp_cdto.getConditions().add(Restrict.eq("firstSubject_id", BeanTransform.copyProperties(firstSubjectBO, FirstSubject.class, true)));
                temp_cdto.getSorts().add("secondCode=desc");
                List<Category> tempCategory = super.findByCis(temp_cdto);
                if (tempCategory != null && tempCategory.size() > 0) {
                    Category maxCodeCategory = tempCategory.get(0);
                    int secondCode = Integer.parseInt(maxCodeCategory.getSecondSubject()) + 1;
                    if (secondCode < 99) {
                        code = firstSubjectCode + String.valueOf(secondCode < 10 ? "0" + secondCode : secondCode);
                        categoryTO.setSecondCode(String.valueOf(secondCode));
                    } else {
                        throw new SerException("不能再继续添加，二级编号超过了99");
                    }
                } else {
                    code = firstSubjectCode + "01";
                    categoryTO.setSecondCode("01");
                }

            }
            categoryTO.setCode(code);
        } else if (StringUtils.isNotBlank(categoryTO.getSecondSubject())
                && StringUtils.isNotBlank(categoryTO.getThirdSubject())) {
            //先查三级是否有（有：抛异常 无：根据一二级查最大三级加一）
            dto = new CategoryDTO();
            dto.getConditions().add(Restrict.eq("firstSubject_id", BeanTransform.copyProperties(firstSubjectBO, FirstSubject.class, true)));
            dto.getConditions().add(Restrict.eq("secondSubject", categoryTO.getSecondSubject()));
            dto.getConditions().add(Restrict.eq("thirdSubject", categoryTO.getThirdSubject()));
            List<Category> categories = super.findByCis(dto);
            if (categories != null && categories.size() > 0) {
                throw new SerException("已存在该一级科目和二级科目下的三级科目");

            } else {
                //不存在就要取最大加一
                dto = new CategoryDTO();
                dto.getConditions().add(Restrict.eq("firstSubject_id", BeanTransform.copyProperties(firstSubjectBO, FirstSubject.class, true)));
                dto.getConditions().add(Restrict.eq("secondSubject", categoryTO.getSecondSubject()));
                dto.getSorts().add("thirdCode=desc");
                List<Category> tempCategory = super.findByCis(dto);
                if (tempCategory != null && tempCategory.size() > 0) {
                    Category maxCodeCategory = tempCategory.get(0);
                    int thirdCode = Integer.parseInt(maxCodeCategory.getThirdCode()) + 1;
                    if (thirdCode < 99) {
                        code = maxCodeCategory.getCode() + String.valueOf(thirdCode < 10 ? "0" + thirdCode : thirdCode);
                        categoryTO.setThirdCode(String.valueOf(thirdCode));
                    } else {
                        throw new SerException("不能再继续添加，二级编号超过了99");
                    }
                } else {
                    //如果二级也不存在
                    //查询二级最大加一
                    dto = new CategoryDTO();
                    dto.getConditions().add(Restrict.eq("firstSubject_id", BeanTransform.copyProperties(firstSubjectBO, FirstSubject.class, true)));
                    dto.getSorts().add("secondCode=desc");
                    categories = super.findByCis(cdto);
                    if (categories != null && categories.size() > 0) {
                        int secondCode = Integer.parseInt(categories.get(0).getSecondSubject() == null ? "00" : categories.get(0).getSecondSubject()) + 1;
                        categoryTO.setSecondCode(firstSubjectCode + (secondCode < 10 ? "0" + secondCode : secondCode));
                        categoryTO.setThirdCode("01");
                        code = firstSubjectCode + categoryTO.getSecondCode() + "01";
                        categoryTO.setCode(code);
                    } else {
                        //不存在就要01
                        categoryTO.setSecondCode("01");
                        categoryTO.setThirdCode("01");
                        code = firstSubjectCode + "01"+"01";
                        categoryTO.setCode( code );
                    }

                }

            }
        }

        return code;
    }
}