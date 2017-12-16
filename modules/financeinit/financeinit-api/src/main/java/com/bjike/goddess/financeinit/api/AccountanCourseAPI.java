package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.AccountAddDateBO;
import com.bjike.goddess.financeinit.bo.AccountanCourseBO;
import com.bjike.goddess.financeinit.bo.CourseDateBO;
import com.bjike.goddess.financeinit.dto.AccountanCourseDTO;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.to.AccountanCourseTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;

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
public interface AccountanCourseAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 会计科目列表总条数
     */
    default Long countCourse(AccountanCourseDTO accountanCourseDTO,CategoryName belongCategory) throws SerException {
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
    default List<AccountanCourseBO> listCourse(AccountanCourseDTO accountanCourseDTO,CategoryName belongCategory) throws SerException {
        return null;
    }

    /**
     * 根据代码获取信息
     *
     * @param code
     * @return
     * @throws SerException
     */
    default CourseDateBO findByCode(String code) throws SerException {
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

    /**
     * 导入
     *
     * @param accountanCourseTOS 会计科目
     */
    void importExcel(List<AccountanCourseTO> accountanCourseTOS) throws SerException;
    /**
     * 根据会计科目名称查询所属类型
     */
    default CategoryName belongByName(String accountanName) throws SerException {
        return null;
    }
    /**
     * 获取所有的会计科目名称和对应的代码
     * @return
     * @throws SerException
     */
    default List<AccountAddDateBO> findNameCode() throws SerException{
        return null;
    }
    /**
     * 根据一级科目代码获取二级会计科目名称
     * @param code
     * @return
     * @throws SerException
     */
    default List<String> findSendNameByCode(String code) throws SerException{
        return null;
    }

    /**
     * 根据一级科目代码获取二级科目名称
     * zhuangkaiqin
     */
    default List<AccountAddDateBO>  findSecondName(String code) throws SerException{
        return null;
    }

    /**
     * 根据二级科目代码获取三级科目
     * zhuangkaiqin
     */
    default List<AccountAddDateBO> findThirdName( String secondCode) throws SerException{
        return null;
    }

    /**
     * 根据一级科目代码获取三级会计科目名称
     * @param code
     * @return
     * @throws SerException
     */
    default List<String> findThirdNameByCode(String code) throws SerException{
        return null;
    }
    /**
     * 获取所有的一级科目名称和对应的代码
     * @return
     * @throws SerException
     */
    default List<AccountAddDateBO> findFirstNameCode() throws SerException{
        return null;
    }
}