package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.FirstSubjectAPI;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.dto.FirstSubjectDTO;
import com.bjike.goddess.financeinit.to.FirstSubjectTO;
import com.bjike.goddess.financeinit.vo.FirstSubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 一级科目
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("financeinit/firstsubject")
public class FirstSubjectAction {


    @Autowired
    private FirstSubjectAPI firstSubjectAPI;

    /**
     * 一级科目列表
     *
     * @param firstSubjectDTO 一级科目信息dto
     * @return class FirstSubjectVO
     * @des 获取所有一级科目信息
     * @version v1
     */
    @GetMapping("v1/listFirstSubject")
    public Result findListFirstSubject(FirstSubjectDTO firstSubjectDTO, BindingResult bindingResult) throws ActException {
        try {
            List<FirstSubjectVO> firstSubjectVOList = BeanTransform.copyProperties(
                    firstSubjectAPI.listFirstSubject(firstSubjectDTO), FirstSubjectVO.class);
            return ActResult.initialize(firstSubjectVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加一级科目
     *
     * @param firstSubjectTO 一级科目基本信息数据to
     * @return class FirstSubjectVO
     * @des 添加一级科目
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addFirstSubject(@Validated FirstSubjectTO firstSubjectTO, BindingResult bindingResult) throws ActException {
        try {
            FirstSubjectBO firstSubjectBO1 = firstSubjectAPI.addFirstSubject(firstSubjectTO);
            return ActResult.initialize(BeanTransform.copyProperties(firstSubjectBO1, FirstSubjectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑一级科目
     *
     * @param firstSubjectTO 一级科目基本信息数据bo
     * @return class FirstSubjectVO
     * @des 添加一级科目
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editFirstSubject(@Validated FirstSubjectTO firstSubjectTO) throws ActException {
        try {
            FirstSubjectBO firstSubjectBO1 = firstSubjectAPI.editFirstSubject(firstSubjectTO);
            return ActResult.initialize(BeanTransform.copyProperties(firstSubjectBO1, FirstSubjectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除一级科目信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteFirstSubject(@PathVariable String id) throws ActException {
        try {
            firstSubjectAPI.deleteFirstSubject(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 批量导入
     *
     * @return class FirstSubjectVO
     * @des 批量导入添加一级科目
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcel() throws ActException {
        return null;
    }


    /**
     * 导出
     *
     * @param categoryName 级别所属类别数组
     * @return class FirstSubjectVO
     * @des 根据级别所属类别导出所有一级科目信息
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel(String[] categoryName) throws ActException {
        return null;
    }


}