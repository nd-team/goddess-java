package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.bo.AccountanCourseBO;
import com.bjike.goddess.financeinit.bo.CourseDateBO;
import com.bjike.goddess.financeinit.dto.AccountanCourseDTO;
import com.bjike.goddess.financeinit.entity.AccountanCourse;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.excel.AccountanCourseExport;
import com.bjike.goddess.financeinit.excel.AccountanCourseExportTemple;
import com.bjike.goddess.financeinit.to.AccountanCourseTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 会计科目业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class AccountanCourseSerImpl extends ServiceImpl<AccountanCourse, AccountanCourseDTO> implements AccountanCourseSer {
    @Override
    public Long countCourse(AccountanCourseDTO accountanCourseDTO,CategoryName belongCategory) throws SerException {
        searchCodi(accountanCourseDTO);
        accountanCourseDTO.getConditions().add(Restrict.eq("belongCategory",belongCategory));
        Long count = super.count(accountanCourseDTO);
        return count;
    }

    @Override
    public AccountanCourseBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        AccountanCourse accountanCourse = super.findById(id);
        return BeanTransform.copyProperties(accountanCourse, AccountanCourseBO.class);
    }
    public void searchCodi(AccountanCourseDTO accountanCourseDTO)throws SerException{

        if(StringUtils.isNotBlank(accountanCourseDTO.getAccountanName())){
            accountanCourseDTO.getConditions().add(Restrict.eq("accountanName",accountanCourseDTO.getAccountanName()));
        }
    }
    @Override
    public List<AccountanCourseBO> listCourse(AccountanCourseDTO accountanCourseDTO,CategoryName belongCategory) throws SerException {
        searchCodi(accountanCourseDTO);
        accountanCourseDTO.getConditions().add(Restrict.eq("belongCategory",belongCategory));
        List<AccountanCourse> list = super.findByCis(accountanCourseDTO, true);
        return BeanTransform.copyProperties(list, AccountanCourseBO.class);
    }

    @Override
    public CategoryName belongByName(String accountanName) throws SerException {
        CategoryName belongCategory = null;
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("accountanName",accountanName));
        AccountanCourse accountanCourse = super.findOne(accountanCourseDTO);
        if(accountanCourse!=null){
            belongCategory = accountanCourse.getBelongCategory();
        }
        return belongCategory;
    }

    @Override
    public CourseDateBO findByCode(String code) throws SerException {
        CourseDateBO courseDateBO = new CourseDateBO();
        if (StringUtils.isNotBlank(code)) {
            String[] field = {"belongCategory","balanceDirection"};
            String sql = " SELECT b.belongCategory,b.balanceDirection FROM " +
                    " (SELECT substring(code,1,1) as code,id " +
                    " FROM financeinit_accountancourse)a, " +
                    " financeinit_accountancourse b " +
                    " where a.id=b.id and a.code='%s' ORDER BY a.code DESC LIMIT 0,1 ";
            sql = String.format(sql, code.substring(0, 1));
            List<CourseDateBO> courseDateBOS = super.findBySql(sql,courseDateBO.getClass(),field);
            if(courseDateBOS!=null&&courseDateBOS.size()>0){
                courseDateBO = courseDateBOS.get(0);
            }
        }
        return courseDateBO;
    }

    @Override
    public AccountanCourseBO addCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        AccountanCourse accountanCourse = BeanTransform.copyProperties(accountanCourseTO, AccountanCourse.class, true);
        accountanCourse.setCreateTime(LocalDateTime.now());
        super.save(accountanCourse);
        return BeanTransform.copyProperties(accountanCourse, AccountanCourseBO.class);
    }

    @Override
    public AccountanCourseBO editCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        AccountanCourse accountanCourse = super.findById(accountanCourseTO.getId());
        LocalDateTime date = accountanCourse.getCreateTime();
        accountanCourse = BeanTransform.copyProperties(accountanCourseTO,AccountanCourse.class,true);
        accountanCourse.setCreateTime(date);
        accountanCourse.setModifyTime(LocalDateTime.now());
        super.update(accountanCourse);
        return BeanTransform.copyProperties(accountanCourse,AccountanCourseBO.class);
    }

    @Override
    public void deleteCourse(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public byte[] exportExcel(CategoryName belongCategory) throws SerException {
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("belongCategory",belongCategory));
        List<AccountanCourse> list = super.findByCis(accountanCourseDTO);
        List<AccountanCourseExport> accountanCourseExports = new ArrayList<>();

        for (AccountanCourse accountanCourse : list){
            AccountanCourseExport excel = BeanTransform.copyProperties(accountanCourse, AccountanCourseExport.class);
            accountanCourseExports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(accountanCourseExports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<AccountanCourseExportTemple> accountanCourseExportTemples = new ArrayList<>();
        AccountanCourseExportTemple accountanCourseExportTemple = new AccountanCourseExportTemple();
        accountanCourseExportTemple.setCode("1000101");
        accountanCourseExportTemple.setAccountanName("一级科目");
        accountanCourseExportTemple.setBelongCategory("资产类");
        accountanCourseExportTemple.setBalanceDirection("借");
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(accountanCourseExportTemples, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<AccountanCourseTO> accountanCourseTOS) throws SerException {
        List<AccountanCourse> accountanCourses = BeanTransform.copyProperties(accountanCourseTOS, AccountanCourse.class, true);
        accountanCourses.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(accountanCourses);
    }
}