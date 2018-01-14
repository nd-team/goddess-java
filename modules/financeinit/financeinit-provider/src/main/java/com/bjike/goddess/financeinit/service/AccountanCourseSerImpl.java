package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.bo.*;
import com.bjike.goddess.financeinit.dto.AccountanCourseDTO;
import com.bjike.goddess.financeinit.entity.AccountanCourse;
import com.bjike.goddess.financeinit.entity.InitDateEntry;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.excel.AccountanCourseExport;
import com.bjike.goddess.financeinit.excel.AccountanCourseExportTemple;
import com.bjike.goddess.financeinit.to.AccountanCourseTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

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
    public Long countCourse(AccountanCourseDTO accountanCourseDTO, CategoryName belongCategory) throws SerException {
        searchCodi(accountanCourseDTO);
        accountanCourseDTO.getConditions().add(Restrict.eq("belongCategory", belongCategory));
        accountanCourseDTO.getConditions().add(Restrict.eq("subjectsLeve", "一级科目"));
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

    public void searchCodi(AccountanCourseDTO accountanCourseDTO) throws SerException {

        if (StringUtils.isNotBlank(accountanCourseDTO.getAccountanName())) {
            accountanCourseDTO.getConditions().add(Restrict.eq("accountanName", accountanCourseDTO.getAccountanName()));
        }
    }

    @Override
    public List<AccountanCourseBO> listCourse(AccountanCourseDTO accountanCourseDTO, CategoryName belongCategory) throws SerException {
        checkSeeIdentity();
        searchCodi(accountanCourseDTO);
        accountanCourseDTO.getSorts().add("code=asc");
        accountanCourseDTO.getConditions().add(Restrict.eq("belongCategory", belongCategory));
        accountanCourseDTO.getConditions().add(Restrict.isNull("belongSubjectsId"));
        accountanCourseDTO.getConditions().add(Restrict.eq("subjectsLeve", "一级科目"));
        List<AccountanCourse> list = super.findByCis(accountanCourseDTO, true);
        return BeanTransform.copyProperties(list, AccountanCourseBO.class);
    }

    @Override
    public CategoryName belongByName(String accountanName) throws SerException {

        CategoryName belongCategory = null;
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("accountanName", accountanName));
        AccountanCourse accountanCourse = super.findOne(accountanCourseDTO);
        if (accountanCourse != null) {
            belongCategory = accountanCourse.getBelongCategory();
        }
        return belongCategory;
    }

    @Override
    public CourseDateBO findByCode(String code) throws SerException {
        CourseDateBO courseDateBO = new CourseDateBO();
        if (StringUtils.isNotBlank(code)) {
            String[] field = {"belongCategory", "balanceDirection"};
            String sql = " SELECT b.belongCategory,b.balanceDirection FROM " +
                    " (SELECT substring(code,1,1) as code,id " +
                    " FROM financeinit_accountancourse)a, " +
                    " financeinit_accountancourse b " +
                    " where a.id=b.id and a.code='%s' ORDER BY a.code DESC LIMIT 0,1 ";
            sql = String.format(sql, code.substring(0, 1));
            List<CourseDateBO> courseDateBOS = super.findBySql(sql, courseDateBO.getClass(), field);
            if (courseDateBOS != null && courseDateBOS.size() > 0) {
                courseDateBO = courseDateBOS.get(0);
            }
        }
        return courseDateBO;
    }


    @Override
    public List<AccountanCourseBO> findSendSubjectByOne(String id) throws SerException {
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("belongSubjectsId", id));
        accountanCourseDTO.getConditions().add(Restrict.eq("subjectsLeve", "二级科目"));
        List<AccountanCourse> accountanCourseList = super.findByCis(accountanCourseDTO);
        return BeanTransform.copyProperties(accountanCourseList, AccountanCourseBO.class);
    }

    @Override
    public List<AccountanCourseBO> findThirdSubjectBySend(String id) throws SerException {
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("belongSubjectsId", id));
        accountanCourseDTO.getConditions().add(Restrict.eq("subjectsLeve", "三级科目"));
        List<AccountanCourse> accountanCourseList = super.findByCis(accountanCourseDTO);
        return BeanTransform.copyProperties(accountanCourseList, AccountanCourseBO.class);
    }

    @Override
    public List<String> findSendNameByOne(String id) throws SerException {
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("belongSubjectsId", id));
        accountanCourseDTO.getConditions().add(Restrict.eq("subjectsLeve", "二级科目"));
        List<AccountanCourse> accountanCourseList = super.findByCis(accountanCourseDTO);
        if (CollectionUtils.isEmpty(accountanCourseList)) {
            return Collections.emptyList();
        }
        return accountanCourseList.stream().map(AccountanCourse::getAccountanName).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> findThirdNameBySend(String id) throws SerException {
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("belongSubjectsId", id));
        accountanCourseDTO.getConditions().add(Restrict.eq("subjectsLeve", "三级科目"));
        List<AccountanCourse> accountanCourseList = super.findByCis(accountanCourseDTO);
        if (CollectionUtils.isEmpty(accountanCourseList)) {
            return Collections.emptyList();
        }
        return accountanCourseList.stream().map(AccountanCourse::getAccountanName).distinct().collect(Collectors.toList());
    }

//    @Override
//    public List<String> findSendNameByCode(String code) throws SerException {
//        List<String> names = new ArrayList<>();
//        if (StringUtils.isNotBlank(code)) {
//            String[] field = {"code", "accountanName", "belongCategory", "balanceDirection"};
//            String sql = " SELECT b.code,b.accountanName,b.belongCategory,b.balanceDirection FROM " +
//                    " (SELECT substring(code,1,1) as code,id " +
//                    " FROM financeinit_accountancourse)a, " +
//                    " financeinit_accountancourse b " +
//                    " where a.id=b.id and a.code='%s' ";
//            sql = String.format(sql, code.substring(0, 1));
//            List<AccountanCourseBO> accountanCourseBOS = super.findBySql(sql, AccountanCourseBO.class, field);
//            if (accountanCourseBOS != null && accountanCourseBOS.size() > 0) {
//                for (AccountanCourseBO accountanCourseBO : accountanCourseBOS) {
//                    if (accountanCourseBO.getCode().length() == 6) {
//                        names.add(accountanCourseBO.getAccountanName());
//                    }
//                }
//            }
//        }
//        return names;
//    }
//
//    @Override
//    public List<String> findThirdNameByCode(String code) throws SerException {
//        List<String> names = new ArrayList<>();
//        if (StringUtils.isNotBlank(code)) {
//            String[] field = {"code", "accountanName", "belongCategory", "balanceDirection"};
//            String sql = " SELECT b.code,b.accountanName,b.belongCategory,b.balanceDirection FROM " +
//                    " (SELECT substring(code,1,1) as code,id " +
//                    " FROM financeinit_accountancourse)a, " +
//                    " financeinit_accountancourse b " +
//                    " where a.id=b.id and a.code='%s' ";
//            sql = String.format(sql, code.substring(0, 1));
//            List<AccountanCourseBO> accountanCourseBOS = super.findBySql(sql, AccountanCourseBO.class, field);
//            if (accountanCourseBOS != null && accountanCourseBOS.size() > 0) {
//                for (AccountanCourseBO accountanCourseBO : accountanCourseBOS) {
//                    if (accountanCourseBO.getCode().length() == 8) {
//                        names.add(accountanCourseBO.getAccountanName());
//                    }
//                }
//            }
//        }
//        return names;
//    }

    @Override
    public List<AccountAddDateBO> findNameCode() throws SerException {
        AccountanCourseDTO dto = new AccountanCourseDTO();
        dto.getSorts().add("code=asc");
        List<AccountanCourse> accountanCourses = super.findByCis(dto);
        List<AccountAddDateBO> accountAddDateBOS = new ArrayList<>();
        if (accountanCourses != null && accountanCourses.size() > 0) {
            for (AccountanCourse accountanCourse : accountanCourses) {
                AccountAddDateBO accountAddDateBO = new AccountAddDateBO();
                accountAddDateBO.setId(accountanCourse.getId());
                accountAddDateBO.setCode(accountanCourse.getCode());
                accountAddDateBO.setAccountanName(accountanCourse.getAccountanName());
                accountAddDateBOS.add(accountAddDateBO);
            }
        }
        return accountAddDateBOS;
    }

    @Override
    public List<AccountAddDateBO> findFirstNameCode() throws SerException {
        AccountanCourseDTO dto = new AccountanCourseDTO();
        dto.getSorts().add("code=asc");
        dto.getConditions().add(Restrict.eq("subjectsLeve", "一级科目"));
        List<AccountanCourse> accountanCourses = super.findByCis(dto);
        List<AccountAddDateBO> accountAddDateBOS = new ArrayList<>();
        if (accountanCourses != null && accountanCourses.size() > 0) {
            for (AccountanCourse accountanCourse : accountanCourses) {
                AccountAddDateBO accountAddDateBO = new AccountAddDateBO();
                accountAddDateBO.setId(accountanCourse.getId());
                accountAddDateBO.setCode(accountanCourse.getCode());
                accountAddDateBO.setAccountanName(accountanCourse.getAccountanName());
                accountAddDateBOS.add(accountAddDateBO);
            }
        }
        return accountAddDateBOS;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AccountanCourseBO addOneCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        checkAddIdentity();
        //将to接收的数据cope到实体类中
        AccountanCourse accountanCourse = BeanTransform.copyProperties(accountanCourseTO, AccountanCourse.class, true);
        //设置开始时间
        accountanCourse.setCreateTime(LocalDateTime.now());
        //调此方法添加进来的一定是一级科目
        accountanCourse.setSubjectsLeve("一级科目");
        //将转换的数据插入到数据库中
        super.save(accountanCourse);
        //一级科目添加到财务初始化表中
        InitDateEntry initDateEntry = new InitDateEntry();//初始化一个实体类
        initDateEntry.setCode(accountanCourse.getCode());
        initDateEntry.setAccountanName(accountanCourse.getAccountanName());
        initDateEntry.setBalanceDirection(accountanCourse.getBalanceDirection());
        initDateEntrySer.save(initDateEntry);
        return BeanTransform.copyProperties(accountanCourse, AccountanCourseBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AccountanCourseBO addSendCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        checkAddIdentity();

        //对应的一级科目数据
        AccountanCourse accountanCourse2 = super.findById(accountanCourseTO.getBelongSubjectsId());

        //将to接收的数据cope到实体类中
        AccountanCourse accountanCourse = BeanTransform.copyProperties(accountanCourseTO, AccountanCourse.class, true);
        //设置开始时间
        accountanCourse.setCreateTime(LocalDateTime.now());
        //调此方法添加进来的一定是二级科目
        accountanCourse.setSubjectsLeve("二级科目");
        //将转换的数据插入到数据库中
        super.save(accountanCourse);

        //二级科目添加到财务初始化表中
        InitDateEntry initDateEntry = new InitDateEntry();//初始化一个实体类
        initDateEntry.setCode(accountanCourse2.getAccountanName() + "-" + accountanCourse.getCode());
        initDateEntry.setAccountanName(accountanCourse.getAccountanName());
        initDateEntry.setBalanceDirection(accountanCourse.getBalanceDirection());
        initDateEntrySer.save(initDateEntry);

        return BeanTransform.copyProperties(accountanCourse, AccountanCourseBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AccountanCourseBO addThreeCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        checkAddIdentity();

        //对应的二级科目数据
        AccountanCourse accountanCourse2 = super.findById(accountanCourseTO.getBelongSubjectsId());
        //二级科目数据对应的一级科目数据
        AccountanCourse accountanCourse3 = super.findById(accountanCourse2.getBelongSubjectsId());


        //将to接收的数据cope到实体类中
        AccountanCourse accountanCourse = BeanTransform.copyProperties(accountanCourseTO, AccountanCourse.class, true);
        //设置开始时间
        accountanCourse.setCreateTime(LocalDateTime.now());
        //调此方法添加进来的一定是二级科目
        accountanCourse.setSubjectsLeve("三级科目");
        //将转换的数据插入到数据库中
        super.save(accountanCourse);

        //二级科目添加到财务初始化表中
        InitDateEntry initDateEntry = new InitDateEntry();//初始化一个实体类
        initDateEntry.setCode(accountanCourse3.getAccountanName() + "-" + accountanCourse2.getAccountanName() + "-" + accountanCourse.getCode());
        initDateEntry.setAccountanName(accountanCourse.getAccountanName());
        initDateEntry.setBalanceDirection(accountanCourse.getBalanceDirection());
        initDateEntrySer.save(initDateEntry);

        return BeanTransform.copyProperties(accountanCourse, AccountanCourseBO.class);
    }

//    @Transactional(rollbackFor = {SerException.class})
//    @Override
//    public AccountanCourseBO addCourse(AccountanCourseTO accountanCourseTO) throws SerException {
//        checkAddIdentity();
//        AccountanCourse accountanCourse = BeanTransform.copyProperties(accountanCourseTO, AccountanCourse.class, true);
//        accountanCourse.setCreateTime(LocalDateTime.now());
//        super.save(accountanCourse);
//        //如果代码是四位数(该数据是一级科目)添加到财务初始化表中
//        if (accountanCourse.getCode().length() == 4) {
//            InitDateEntry initDateEntry = new InitDateEntry();
//            initDateEntry.setCode(accountanCourse.getCode());
//            initDateEntry.setAccountanName(accountanCourse.getAccountanName());
//            initDateEntry.setBalanceDirection(accountanCourse.getBalanceDirection());
//            initDateEntrySer.save(initDateEntry);
//        }
//        return BeanTransform.copyProperties(accountanCourse, AccountanCourseBO.class);
//    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public AccountanCourseBO editCourse(AccountanCourseTO accountanCourseTO) throws SerException {
        checkAddIdentity();
        AccountanCourse accountanCourse = super.findById(accountanCourseTO.getId());
        LocalDateTime date = accountanCourse.getCreateTime();
        String belongSubjectsId = accountanCourse.getBelongSubjectsId();
        String subjectsLeve = accountanCourse.getSubjectsLeve();
        accountanCourse = BeanTransform.copyProperties(accountanCourseTO, AccountanCourse.class, true, "belongSubjectsId", "subjectsLeve");
        accountanCourse.setCreateTime(date);
        accountanCourse.setModifyTime(LocalDateTime.now());
        accountanCourse.setBelongSubjectsId(belongSubjectsId);
        accountanCourse.setSubjectsLeve(subjectsLeve);
        super.update(accountanCourse);
        return BeanTransform.copyProperties(accountanCourse, AccountanCourseBO.class);
    }

    @Transactional(rollbackFor = {SerException.class})
    @Override
    public void deleteCourse(String id) throws SerException {
        checkAddIdentity();
        AccountanCourse accountanCourse = super.findById(id);
        List<AccountanCourse> accountanCourseList = new ArrayList<>();
        //判断这条数据是一级科目还是二级科目还是三级科目
        switch (accountanCourse.getSubjectsLeve()) {
            //如果是一级科目就要删除对应的二级和对应的三级
            case "一级科目":
                accountanCourseList.add(accountanCourse);
                AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
                accountanCourseDTO.getConditions().add(Restrict.eq("belongSubjectsId", id));
                List<AccountanCourse> accountanCourseList1 = super.findByCis(accountanCourseDTO);
                if (accountanCourseList1 != null && accountanCourseList1.size() > 0) {
                    accountanCourseList.addAll(accountanCourseList1);
                    for (AccountanCourse accountanCourse1 : accountanCourseList) {
                        AccountanCourseDTO accountanCourseDTO1 = new AccountanCourseDTO();
                        accountanCourseDTO1.getConditions().add(Restrict.eq("belongSubjectsId", accountanCourse1.getId()));
                        List<AccountanCourse> accountanCourseList2 = super.findByCis(accountanCourseDTO);
                        accountanCourseList.addAll(accountanCourseList2);
                    }
                }
                super.remove(accountanCourseList);
                break;
            //如果是二级科目就删除对应的三级科目
            case "二级科目":
                accountanCourseList.add(accountanCourse);
                AccountanCourseDTO accountanCourseDTO2 = new AccountanCourseDTO();
                accountanCourseDTO2.getConditions().add(Restrict.eq("belongSubjectsId", id));
                List<AccountanCourse> accountanCourseList2 = super.findByCis(accountanCourseDTO2);
                accountanCourseList.addAll(accountanCourseList2);
                super.remove(accountanCourseList);
                break;
            default:
                super.remove(id);
                break;
        }

    }

    @Override
    public byte[] exportExcel(CategoryName belongCategory) throws SerException {
//        checkAddIdentity();
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("belongCategory", belongCategory));
        accountanCourseDTO.getConditions().add(Restrict.eq("subjectsLeve", "一级科目"));
        List<AccountanCourse> firsts = super.findByCis(accountanCourseDTO);
        //一级科目
        if (firsts != null && firsts.size() > 0) {

            for (AccountanCourse accountanCourse : firsts) {
                AccountanCourseDTO accountanCourseDTO1 = new AccountanCourseDTO();
                accountanCourseDTO1.getConditions().add(Restrict.eq("belongSubjectsId", accountanCourse.getId()));
                accountanCourseDTO1.getConditions().add(Restrict.eq("subjectsLeve", "二级科目"));
                List<AccountanCourse> seconds = super.findByCis(accountanCourseDTO1);
                accountanCourse.setLevel(seconds);
                //二级科目
                if (seconds != null && seconds.size() > 0) {
                    for (AccountanCourse accountanCourse1 : seconds) {
                        AccountanCourseDTO accountanCourseDTO2 = new AccountanCourseDTO();
                        accountanCourseDTO2.getConditions().add(Restrict.eq("belongSubjectsId", accountanCourse1.getId()));
                        accountanCourseDTO2.getConditions().add(Restrict.eq("subjectsLeve", "三级科目"));
                        List<AccountanCourse> three = super.findByCis(accountanCourseDTO2);
                        accountanCourse1.setLevel(three);
                    }
                }
            }
        }

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("test");

        XSSFCellStyle headerStyle = ExcelUtil.getStyle(wb, IndexedColors.WHITE.getIndex());
        headerStyle.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
        headerStyle.setWrapText(true);
        int rowIndex = 0;
        XSSFRow row = null;
        row = sheet.createRow(rowIndex++);
        List<Field> fields = ClazzUtils.getFields(new AccountanCourseExport().getClass()); //获得列表对象属性
        List<ExcelHeader> excelHeaders = ExcelUtil.getExcelHeaders(fields, null); //获得表头
        int i = 0;
        for (ExcelHeader eh : excelHeaders) {
            XSSFCell cell = row.createCell(i++);
            cell.setCellValue(eh.name());
        }
        //创建内容行
        int startIndex = 1;
        int secondIndex = 1;
        for (AccountanCourse ac1 : firsts) {
            List<AccountanCourse> seconds = ac1.getLevel();
            if (null != seconds && seconds.size() > 0) {
                for (AccountanCourse ac2 : seconds) {
                    List<AccountanCourse> threes = ac2.getLevel();
                    if (null != threes && threes.size() > 0) {
                        for (AccountanCourse ac3 : threes) {
                            //从三级节点开始创建行数据
                            row = sheet.createRow(rowIndex++);
                            row.createCell(0).setCellValue(ac1.getCode());
                            row.createCell(1).setCellValue(ac1.getAccountanName());
                            row.createCell(2).setCellValue(ac1.getBelongCategory().getCode());
                            row.createCell(3).setCellValue(ac1.getBalanceDirection().getCode());
                            row.createCell(4).setCellValue(ac2.getCode());
                            row.createCell(5).setCellValue(ac2.getAccountanName());
                            row.createCell(6).setCellValue(ac2.getBelongCategory().getCode());
                            row.createCell(7).setCellValue(ac2.getBalanceDirection().getCode());
                            row.createCell(8).setCellValue(ac3.getCode());
                            row.createCell(9).setCellValue(ac3.getAccountanName());
                            row.createCell(10).setCellValue(ac3.getBelongCategory().getCode());
                            row.createCell(11).setCellValue(ac3.getBalanceDirection().getCode());
                        }

                    } else {
                        //假如没有三级节点 创建二级行数据 row
                        row = sheet.createRow(rowIndex++);
                        row.createCell(0).setCellValue(ac1.getCode());
                        row.createCell(1).setCellValue(ac1.getAccountanName());
                        row.createCell(2).setCellValue(ac1.getBelongCategory().getCode());
                        row.createCell(3).setCellValue(ac1.getBalanceDirection().getCode());
                        row.createCell(4).setCellValue(ac2.getCode());
                        row.createCell(5).setCellValue(ac2.getAccountanName());
                        row.createCell(6).setCellValue(ac2.getBelongCategory().getCode());
                        row.createCell(7).setCellValue(ac2.getBalanceDirection().getCode());
                    }
                    //1-3 4-5 6-6 7-7
                    int start = secondIndex;
                    int end =rowIndex-1;
                    if(end-start>0){
                        sheet.addMergedRegion(new CellRangeAddress(secondIndex, end, 4, 4));
                        sheet.addMergedRegion(new CellRangeAddress(secondIndex, end, 5, 5));
                        sheet.addMergedRegion(new CellRangeAddress(secondIndex, end, 6, 6));
                        sheet.addMergedRegion(new CellRangeAddress(secondIndex, end, 7, 7));
                    }
                    secondIndex=rowIndex;


                }

            } else {
                //假如没有二级节点  创建一级行数据 row
                row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(ac1.getCode());
                row.createCell(1).setCellValue(ac1.getAccountanName());
                row.createCell(2).setCellValue(ac1.getBelongCategory().getCode());
                row.createCell(3).setCellValue(ac1.getBalanceDirection().getCode());

            }
            if ((rowIndex - 1) - startIndex > 1) {//超过两行才合并
                sheet.addMergedRegion(new CellRangeAddress(startIndex, rowIndex - 1, 0, 0));
                sheet.addMergedRegion(new CellRangeAddress(startIndex, rowIndex - 1, 1, 1));
                sheet.addMergedRegion(new CellRangeAddress(startIndex, rowIndex - 1, 2, 2));
                sheet.addMergedRegion(new CellRangeAddress(startIndex, rowIndex - 1, 3, 3));
                startIndex = rowIndex - 1;

            }
//            //设置样式
           int rows =  sheet.getLastRowNum();
            for(int j=0;j<rows;j++){
                if(j!=0){
                    XSSFRow xrow = sheet.getRow(j);
                    for(int k=0;k<xrow.getLastCellNum();k++){
                        xrow.getCell(k).setCellStyle(headerStyle);
                    }
                }

            }


        }


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();


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
        for (AccountanCourse str : accountanCourses) {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
            super.save(str);
            if (str.getCode().length() == 4) {
                InitDateEntry initDateEntry = new InitDateEntry();
                initDateEntry.setCode(str.getCode());
                initDateEntry.setAccountanName(str.getAccountanName());
                initDateEntry.setBalanceDirection(str.getBalanceDirection());
                initDateEntrySer.save(initDateEntry);
            }
        }
    }

    @Override
    public List<AccountAddDateBO> findSecondName(String code) throws SerException {
//        StringBuilder sql = new StringBuilder("SELECT substring(code,1,6) as code,accountanName  FROM financeinit_accountancourse ");
        StringBuilder sql = new StringBuilder("SELECT code,accountanName  FROM financeinit_accountancourse ");
        List<Object> objectList = super.findBySql(sql.toString());
        List<AccountAddDateBO> list = new ArrayList<>(0);
        if (null != objectList && objectList.size() > 0) {
            for (Object obj : objectList) {
                Object[] objects = (Object[]) obj;
                String code1 = String.valueOf(objects[0]);
                if (code1.length() == 6 && code1.substring(0, 4).equals(code)) {
                    AccountAddDateBO bo = new AccountAddDateBO();
                    bo.setAccountanName(String.valueOf(objects[1]));
                    bo.setCode(code1);
                    list.add(bo);
                }
            }
        }

        return list;
    }

    @Override
    public List<AccountAddDateBO> findThirdName(String secondCode) throws SerException {
//        StringBuilder sql = new StringBuilder("SELECT substring(code,1,8) as code,accountanName  FROM financeinit_accountancourse ");
        StringBuilder sql = new StringBuilder("SELECT code,accountanName  FROM financeinit_accountancourse ");
        List<Object> objectList = super.findBySql(sql.toString());
        List<AccountAddDateBO> list = new ArrayList<>(0);
        if (null != objectList && objectList.size() > 0) {
            for (Object obj : objectList) {
                Object[] objects = (Object[]) obj;
                String code1 = String.valueOf(objects[0]);
                if (code1.length() == 8 && code1.substring(0, 6).equals(secondCode)) {
                    AccountAddDateBO bo = new AccountAddDateBO();
                    bo.setAccountanName(String.valueOf(objects[1]));
                    bo.setCode(code1);
                    list.add(bo);
                }
            }
        }

        return list;
    }

    @Override
    public SubjectDataBO findSubjects(String name) throws SerException {
        SubjectDataBO bo = new SubjectDataBO();
        //获取部门
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(name);
        if (null != positionDetailBOs && positionDetailBOs.size() > 0) {
            String department = positionDetailBOs.get(0).getDepartmentName();
            if (StringUtils.isBlank(department)) {
                return null;
            }

            //获取体系
            String hierarchy = departmentDetailAPI.findHierarchy(department);
            if ("职能体系".equals(hierarchy) && !"商务发展部".equals(department)) {
                department = "职能部门";
            }

            AccountanCourseDTO dto = new AccountanCourseDTO();
            dto.getConditions().add(Restrict.eq("accountanName", department));
            List<AccountanCourse> accountanCourses = super.findByCis(dto);
            if (null != accountanCourses && accountanCourses.size() > 0) {
                List<AccountanCourse> accountanCourses1 = accountanCourses.stream().filter(obj -> obj.getCode().length() == 8).collect(Collectors.toList());

                if (null != accountanCourses1 && accountanCourses1.size() > 0) {
                    bo.setThirdSubject(accountanCourses1.get(0).getAccountanName());
                    bo.setThirdSubjectCode(accountanCourses1.get(0).getCode());
                    //根据三级科目获取一级科目
                    AccountanCourseDTO dto1 = new AccountanCourseDTO();
                    dto1.getConditions().add(Restrict.eq("code", bo.getThirdSubjectCode().substring(0, 4)));
                    List<AccountanCourse> accountanCourses2 = super.findByCis(dto1);
                    if (null != accountanCourses2 && accountanCourses2.size() > 0) {
//                        accountanCourses2 = accountanCourses2.stream().filter(obj -> "管理费用".equals(obj.getAccountanName()) || "主营业务成本".equals(obj.getAccountanName()) || "营业费用".equals(obj.getAccountanName())).collect(Collectors.toList());
                        if (null != accountanCourses2 && accountanCourses2.size() > 0) {
                            bo.setFirstSubject(accountanCourses2.get(0).getAccountanName());
                            bo.setFirstSubjectCode(accountanCourses2.get(0).getCode());
                        }
                    }
//                    //根据三级科目获取二级科目
//                    AccountanCourseDTO dto2 = new AccountanCourseDTO();
//                    dto2.getConditions().add(Restrict.eq("code", bo.getThirdSubjectCode().substring(0, 6)));
//                    List<AccountanCourse> accountanCourses3 = super.findByCis(dto2);
//                    if (null != accountanCourses3 && accountanCourses3.size() > 0) {
//                        bo.setSecondSubject(accountanCourses3.get(0).getAccountanName());
//                        bo.setSecondSubjectCode(accountanCourses3.get(0).getCode());
//                    }
                }
            }
        }
        return bo;
    }

    @Override
    public SubjectDatasBO findSubjects1(String name) throws SerException {
        SubjectDatasBO bo = new SubjectDatasBO();
        //获取部门
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(name);
        if (null != positionDetailBOs && positionDetailBOs.size() > 0) {
            String department = positionDetailBOs.get(0).getDepartmentName();
            if (StringUtils.isBlank(department)) {
                return null;
            }

            //获取体系
            String hierarchy = departmentDetailAPI.findHierarchy(department);
            if ("职能体系".equals(hierarchy) && !"商务发展部".equals(department)) {
                department = "职能部门";
            }
            AccountanCourseDTO dto = new AccountanCourseDTO();
            dto.getConditions().add(Restrict.eq("accountanName", department));
            List<AccountanCourse> accountanCourses = super.findByCis(dto);
            if (null != accountanCourses && accountanCourses.size() > 0) {
                List<AccountanCourse> accountanCourses1 = accountanCourses.stream().filter(obj -> obj.getCode().length() == 8).collect(Collectors.toList());
                if (null != accountanCourses1 && accountanCourses1.size() > 0) {
                    bo.setThirdSubject(accountanCourses1.get(0).getAccountanName());
                    bo.setThirdSubjectCode(accountanCourses1.get(0).getCode());
                    //根据三级科目获取一级科目
                    List<FirstSubjectDataBO> firstSubjectDataBOList = new ArrayList<>(0);
                    AccountanCourseDTO dto1 = new AccountanCourseDTO();
                    dto1.getConditions().add(Restrict.eq("code", bo.getThirdSubjectCode().substring(0, 4)));
                    dto1.getSorts().add("accountanName=asc");
                    List<AccountanCourse> accountanCourses2 = super.findByCis(dto1);
                    if (null != accountanCourses2 && accountanCourses2.size() > 0) {
                        for (AccountanCourse accountanCourse : accountanCourses2) {
                            FirstSubjectDataBO firstSubjectDataBO = new FirstSubjectDataBO();
                            firstSubjectDataBO.setFirstSubjectCode(accountanCourse.getCode());
                            firstSubjectDataBO.setFirstSubject(accountanCourse.getAccountanName());
                            firstSubjectDataBOList.add(firstSubjectDataBO);
                        }
                    }
                    //如果三级科目是职能部门,一级科目加上其他应收款,其他应付款
                    if ("职能部门".equals(department)) {
                        findOtherFirstSubject(firstSubjectDataBOList);
//                        bo.setFirstSubjectDataBOs(firstSubjectDataBOList);
                    } else if ("商务发展部".equals(department)) {
                        findOtherFirstSubject(firstSubjectDataBOList);
//                        bo.setFirstSubjectDataBOs(firstSubjectDataBOList);
                    } else if ("一线项目".equals(department)) {
                        firstSubjectDataBOList = new ArrayList<>(0);
                        findOtherFirstSubject(firstSubjectDataBOList);
                    }
                    bo.setFirstSubjectDataBOs(firstSubjectDataBOList);
                }
            }
        }
        return bo;
    }

    @Override
    public List<SecondSubjectDataBO> findSecondSubject(String firstSubjectCode) throws SerException {
        if (StringUtils.isBlank(firstSubjectCode)) {
            return null;
        }
        String[] files = new String[]{"secondSubjectCode", "secondSubject"};
        //查询二级科目代码
        StringBuilder sql = new StringBuilder("select code as secondSubjectCode ,accountanName as secondSubject from financeinit_accountancourse ");
        sql.append(" where substring(code,1,4) = '" + firstSubjectCode + "' ");
        sql.append(" and LENGTH(code) = 6 ");
        List<SecondSubjectDataBO> bos = super.findBySql(sql.toString(), SecondSubjectDataBO.class, files);
        return bos;

    }

    //获取其他应收款和其他应付款
    private void findOtherFirstSubject(List<FirstSubjectDataBO> firstSubjectDataBOList) throws SerException {
        //获取其他应收款和其他应付款
        AccountanCourseDTO dto = new AccountanCourseDTO();
        dto.getConditions().add(Restrict.eq("accountanName", "其他应收款"));
        List<AccountanCourse> accountanCourses3 = super.findByCis(dto);
        if (null != accountanCourses3 && accountanCourses3.size() > 0) {
            FirstSubjectDataBO firstSubjectDataBO = new FirstSubjectDataBO();
            firstSubjectDataBO.setFirstSubject(accountanCourses3.get(0).getAccountanName());
            firstSubjectDataBO.setFirstSubjectCode(accountanCourses3.get(0).getCode());
            firstSubjectDataBOList.add(firstSubjectDataBO);
        }
        dto = new AccountanCourseDTO();
        dto.getConditions().add(Restrict.eq("accountanName", "其他应付款"));
        List<AccountanCourse> accountanCourses4 = super.findByCis(dto);
        if (null != accountanCourses4 && accountanCourses4.size() > 0) {
            FirstSubjectDataBO firstSubjectDataBO = new FirstSubjectDataBO();
            firstSubjectDataBO.setFirstSubject(accountanCourses4.get(0).getAccountanName());
            firstSubjectDataBO.setFirstSubjectCode(accountanCourses4.get(0).getCode());
            firstSubjectDataBOList.add(firstSubjectDataBO);
        }
    }

    @Override
    public List<String> findByFixedAssets() throws SerException {
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("accountanName", "固定资产"));
        List<AccountanCourse> accountanCourseList = super.findByCis(accountanCourseDTO);
        List<String> names = new ArrayList<>();
        if (accountanCourseList != null && accountanCourseList.size() > 0) {
            String id = accountanCourseList.get(0).getId();
            String[] files = new String[]{"secondSubjectCode", "secondSubject"};
            //查询二级科目代码
            StringBuilder sql = new StringBuilder("select code as secondSubjectCode ,accountanName as secondSubject from financeinit_accountancourse ");
            sql.append(" where belongSubjectsId = '" + id + "' ");
            sql.append(" and subjectsLeve = '二级科目' ");
            List<SecondSubjectDataBO> bos = super.findBySql(sql.toString(), SecondSubjectDataBO.class, files);
            if (bos != null && bos.size() > 0) {
                for (SecondSubjectDataBO secondSubjectDataBO : bos) {
                    String name = secondSubjectDataBO.getSecondSubjectCode() + ":" + secondSubjectDataBO.getSecondSubject();
                    names.add(name);
                }
            }
        }
        return names;
    }

    @Override
    public String findByCourseName(String courseName) throws SerException {
        String name = "";
        AccountanCourseDTO accountanCourseDTO = new AccountanCourseDTO();
        accountanCourseDTO.getConditions().add(Restrict.eq("accountanName", courseName));
        List<AccountanCourse> accountanCourseList = super.findByCis(accountanCourseDTO);
        if (accountanCourseList != null && accountanCourseList.size() > 0) {
            name = accountanCourseList.get(0).getCode() + ":" + courseName;
        }
        return name;
    }

    @Override
    public List<String> findDepreciationAccount() throws SerException {
        List<String> depreciationAccount = new ArrayList<>();
        StringBuffer sql = new StringBuffer("SELECT code from financeinit_accountancourse WHERE accountanName = '折旧费' AND belongSubjectsId in (SELECT id from financeinit_accountancourse WHERE accountanName = '管理费用' AND subjectsLeve = '一级科目') ");
        List<Object> objectList = super.findBySql(sql.toString());
        if (objectList != null && objectList.size() > 0) {
            String code = String.valueOf(objectList.get(0));
            String name = code + "费用管理-折旧费";
            depreciationAccount.add(name);
        }
        StringBuffer sql1 = new StringBuffer(" SELECT code from financeinit_accountancourse WHERE accountanName = '折旧费' AND belongSubjectsId in (SELECT id from financeinit_accountancourse WHERE accountanName = '研发费用' AND subjectsLeve = '一级科目') ");

        List<Object> objectList1 = super.findBySql(sql1.toString());
        if (objectList1 != null && objectList1.size() > 0) {
            String code = String.valueOf(objectList1.get(0));
            String name = code + "研发费用-折旧费";
            depreciationAccount.add(name);
        }
        return depreciationAccount;
    }

    @Override
    public String findtaxSubject() throws SerException {
        StringBuffer sql = new StringBuffer();
        String name = "";
        sql.append(" SELECT id FROM financeinit_accountancourse WHERE accountanName = '进项税额' AND belongSubjectsId IN ");
        sql.append(" (SELECT id FROM financeinit_accountancourse WHERE accountanName = '应交增值税' AND belongSubjectsId IN ");
        sql.append(" (SELECT id FROM financeinit_accountancourse WHERE accountanName = '应交税金' AND subjectsLeve = '一级科目')) ");
        List<Object> objectList = super.findBySql(sql.toString());
        if (objectList != null && objectList.size() > 0) {
            String code = String.valueOf(objectList.get(0));
            name = code + "应交税金-应交增值税-进项税额";
        }
        return name;
    }

    @Override
    public List<String> findByFirstName(String firstSubject) throws SerException {
        if (StringUtils.isBlank(firstSubject)) {
            return null;
        }
        String code = "";
        List<AccountAddDateBO> list = this.findFirstNameCode();
        if (null != list && list.size() > 0) {
            for (AccountAddDateBO bo : list) {
                if (firstSubject.equals(bo.getAccountanName())) {
                    code = bo.getCode();
                }
            }
        }
        String[] fildes = new String[]{"accountanName"};
        if (StringUtils.isNotBlank(code)) {
            StringBuilder sql = new StringBuilder("SELECT accountanName FROM financeinit_accountancourse ");
            sql.append(" WHERE substring(code,1,4) = '" + code + "' ");
            sql.append(" and length(code) = 8;");
            List<AccountAddDateBO> list1 = super.findBySql(sql.toString(), AccountAddDateBO.class, fildes);
            if (null != list1 && list1.size() > 0) {
                List<String> strings = list1.stream().map(AccountAddDateBO::getAccountanName).distinct().collect(Collectors.toList());
                return strings;
            }
        }
        return null;
    }
}