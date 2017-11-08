package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.bo.AccountAddDateBO;
import com.bjike.goddess.financeinit.bo.AccountanCourseBO;
import com.bjike.goddess.financeinit.bo.CourseDateBO;
import com.bjike.goddess.financeinit.dto.AccountanCourseDTO;
import com.bjike.goddess.financeinit.entity.AccountanCourse;
import com.bjike.goddess.financeinit.entity.InitDateEntry;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.excel.AccountanCourseExport;
import com.bjike.goddess.financeinit.excel.AccountanCourseExportTemple;
import com.bjike.goddess.financeinit.to.AccountanCourseTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private InitDateEntrySer initDateEntrySer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应财务部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应财务部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
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
        checkSeeIdentity();
        searchCodi(accountanCourseDTO);
        accountanCourseDTO.getSorts().add("code=asc");
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
    public List<String> findSendNameByCode(String code) throws SerException {
        List<String> names = new ArrayList<>();
        if (StringUtils.isNotBlank(code)) {
            String[] field = {"code","accountanName","belongCategory","balanceDirection"};
            String sql = " SELECT b.code,b.accountanName,b.belongCategory,b.balanceDirection FROM " +
                    " (SELECT substring(code,1,1) as code,id " +
                    " FROM financeinit_accountancourse)a, " +
                    " financeinit_accountancourse b " +
                    " where a.id=b.id and a.code='%s' ";
            sql = String.format(sql, code.substring(0, 1));
            List<AccountanCourseBO> accountanCourseBOS = super.findBySql(sql,AccountanCourseBO.class,field);
            if(accountanCourseBOS!=null&&accountanCourseBOS.size()>0){
                for (AccountanCourseBO accountanCourseBO : accountanCourseBOS){
                    if(accountanCourseBO.getCode().length()==6){
                        names.add(accountanCourseBO.getAccountanName());
                    }
                }
            }
        }
        return names;
    }

    @Override
    public List<String> findThirdNameByCode(String code) throws SerException {
        List<String> names = new ArrayList<>();
        if (StringUtils.isNotBlank(code)) {
            String[] field = {"code","accountanName","belongCategory","balanceDirection"};
            String sql = " SELECT b.code,b.accountanName,b.belongCategory,b.balanceDirection FROM " +
                    " (SELECT substring(code,1,1) as code,id " +
                    " FROM financeinit_accountancourse)a, " +
                    " financeinit_accountancourse b " +
                    " where a.id=b.id and a.code='%s' ";
            sql = String.format(sql, code.substring(0, 1));
            List<AccountanCourseBO> accountanCourseBOS = super.findBySql(sql,AccountanCourseBO.class,field);
            if(accountanCourseBOS!=null&&accountanCourseBOS.size()>0){
                for (AccountanCourseBO accountanCourseBO : accountanCourseBOS){
                    if(accountanCourseBO.getCode().length()==8){
                        names.add(accountanCourseBO.getAccountanName());
                    }
                }
            }
        }
        return names;
    }

    @Override
    public List<AccountAddDateBO> findNameCode() throws SerException {
        List<AccountanCourse> accountanCourses = super.findAll();
        List<AccountAddDateBO> accountAddDateBOS = new ArrayList<>();
        if(accountanCourses!=null&&accountanCourses.size()>0){
            for (AccountanCourse accountanCourse : accountanCourses){
                AccountAddDateBO accountAddDateBO = new AccountAddDateBO();
                accountAddDateBO.setCode(accountanCourse.getCode());
                accountAddDateBO.setAccountanName(accountanCourse.getAccountanName());
                accountAddDateBOS.add(accountAddDateBO);
            }
        }
        return accountAddDateBOS;
    }

    @Override
    public List<AccountAddDateBO> findFirstNameCode() throws SerException {
        List<AccountanCourse> accountanCourses = super.findAll();
        List<AccountAddDateBO> accountAddDateBOS = new ArrayList<>();
        if(accountanCourses!=null&&accountanCourses.size()>0){
            for (AccountanCourse accountanCourse : accountanCourses){
                if(accountanCourse.getCode().length()==4){
                    AccountAddDateBO accountAddDateBO = new AccountAddDateBO();
                    accountAddDateBO.setCode(accountanCourse.getCode());
                    accountAddDateBO.setAccountanName(accountanCourse.getAccountanName());
                    accountAddDateBOS.add(accountAddDateBO);
                }
            }
        }
        return accountAddDateBOS;
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public AccountanCourseBO addCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        checkAddIdentity();
        AccountanCourse accountanCourse = BeanTransform.copyProperties(accountanCourseTO, AccountanCourse.class, true);
        accountanCourse.setCreateTime(LocalDateTime.now());
        super.save(accountanCourse);
        //如果代码是四位数(该数据是一级科目)添加到财务初始化表中
        if(accountanCourse.getCode().length()==4){
            InitDateEntry initDateEntry = new InitDateEntry();
            initDateEntry.setCode(accountanCourse.getCode());
            initDateEntry.setAccountanName(accountanCourse.getAccountanName());
            initDateEntry.setBalanceDirection(accountanCourse.getBalanceDirection());
            initDateEntrySer.save(initDateEntry);
        }
        return BeanTransform.copyProperties(accountanCourse, AccountanCourseBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public AccountanCourseBO editCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        checkAddIdentity();
        AccountanCourse accountanCourse = super.findById(accountanCourseTO.getId());
        LocalDateTime date = accountanCourse.getCreateTime();
        accountanCourse = BeanTransform.copyProperties(accountanCourseTO,AccountanCourse.class,true);
        accountanCourse.setCreateTime(date);
        accountanCourse.setModifyTime(LocalDateTime.now());
        super.update(accountanCourse);
        return BeanTransform.copyProperties(accountanCourse,AccountanCourseBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void deleteCourse(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public byte[] exportExcel(CategoryName belongCategory) throws SerException {
//        checkAddIdentity();
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
        accountanCourseExportTemples.add(accountanCourseExportTemple);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(accountanCourseExportTemples, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<AccountanCourseTO> accountanCourseTOS) throws SerException {
        checkAddIdentity();
        List<AccountanCourse> accountanCourses = BeanTransform.copyProperties(accountanCourseTOS, AccountanCourse.class, true);
        for (AccountanCourse str : accountanCourses){
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
            super.save(accountanCourses);
            if(str.getCode().length()==4){
                InitDateEntry initDateEntry = new InitDateEntry();
                initDateEntry.setCode(str.getCode());
                initDateEntry.setAccountanName(str.getAccountanName());
                initDateEntry.setBalanceDirection(str.getBalanceDirection());
                initDateEntrySer.save(initDateEntry);
            }
        }
    }
}