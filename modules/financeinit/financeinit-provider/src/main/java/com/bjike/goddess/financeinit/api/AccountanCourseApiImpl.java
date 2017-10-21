package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.AccountanCourseBO;
import com.bjike.goddess.financeinit.bo.CourseDateBO;
import com.bjike.goddess.financeinit.dto.AccountanCourseDTO;
import com.bjike.goddess.financeinit.entity.AccountanCourse;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.service.AccountanCourseSer;
import com.bjike.goddess.financeinit.to.AccountanCourseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会计科目业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accountanCourseApiImpl")
public class AccountanCourseApiImpl implements AccountanCourseAPI {
    @Autowired
    private AccountanCourseSer accountanCourseSer;
    @Override
    public Long countCourse(AccountanCourseDTO accountanCourseDTO,CategoryName belongCategory) throws SerException {
        return accountanCourseSer.countCourse(accountanCourseDTO,belongCategory);
    }

    @Override
    public AccountanCourseBO getOneById(String id) throws SerException {
        return accountanCourseSer.getOneById(id);
    }

    @Override
    public List<AccountanCourseBO> listCourse(AccountanCourseDTO accountanCourseDTO,CategoryName belongCategory) throws SerException {
        return accountanCourseSer.listCourse(accountanCourseDTO,belongCategory);
    }

    @Override
    public CourseDateBO findByCode(String code) throws SerException {
        return accountanCourseSer.findByCode(code);
    }

    @Override
    public AccountanCourseBO addCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        return accountanCourseSer.addCourse(accountanCourseTO);
    }

    @Override
    public AccountanCourseBO editCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        return accountanCourseSer.editCourse(accountanCourseTO);
    }

    @Override
    public void deleteCourse(String id) throws SerException {
        accountanCourseSer.deleteCourse(id);
    }

    @Override
    public byte[] exportExcel(CategoryName belongCategory) throws SerException {
        return accountanCourseSer.exportExcel(belongCategory);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return accountanCourseSer.templateExport();
    }

    @Override
    public void importExcel(List<AccountanCourseTO> accountanCourseTOS) throws SerException {
        accountanCourseSer.importExcel(accountanCourseTOS);
    }

    @Override
    public CategoryName belongByName(String accountanName) throws SerException {
        return accountanCourseSer.belongByName(accountanName);
    }
}