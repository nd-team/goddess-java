package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.financeinit.bo.AccountanCourseBO;
import com.bjike.goddess.financeinit.bo.CourseDateBO;
import com.bjike.goddess.financeinit.dto.AccountanCourseDTO;
import com.bjike.goddess.financeinit.entity.AccountanCourse;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.to.AccountanCourseTO;

import java.util.List;

/**
 * 会计科目业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountanCourseSer extends Ser<AccountanCourse, AccountanCourseDTO> {
    /**
     * 会计科目列表总条数
     */
    default Long countCourse(AccountanCourseDTO accountanCourseDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取会计科目
     *
     * @return class AccountanCourseBO
     */
    default AccountanCourseBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 会计科目列表
     *
     * @return class AccountanCourseBO
     */
    default List<AccountanCourseBO> listCourse(AccountanCourseDTO accountanCourseDTO) throws SerException {
        return null;
    }

    /**
     * 根据代码获取信息
     * @param code
     * @return
     * @throws SerException
     */
    default CourseDateBO findByCode(String code) throws SerException{
        return null;
    }
    /**
     * 添加
     *
     * @param accountanCourseTO 会计科目
     * @return class CompanyBasicInfoBO
     */
    default AccountanCourseBO addCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param accountanCourseTO 会计科目
     * @return class CompanyBasicInfoBO
     */
    default AccountanCourseBO editCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 会计科目id
     */
    default void deleteCourse(String id) throws SerException {
        return;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel(CategoryName belongCategory) throws SerException;
    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

//    /**
//     * 导入
//     *
//     * @param casualtyPurchasingListTOS 工龄补助
//     */
//    void importExcel(List<CasualtyPurchasingListTO> casualtyPurchasingListTOS) throws SerException;
}