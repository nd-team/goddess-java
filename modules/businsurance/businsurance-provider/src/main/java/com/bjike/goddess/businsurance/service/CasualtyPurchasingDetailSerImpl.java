package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businsurance.bo.CasualtyPurchasingDetailBO;
import com.bjike.goddess.businsurance.bo.SummaryBO;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingDetailDTO;
import com.bjike.goddess.businsurance.entity.CasualtyPurchasingDetail;
import com.bjike.goddess.businsurance.enums.BuyCasualtyStatus;
import com.bjike.goddess.businsurance.enums.GuideAddrStatus;
import com.bjike.goddess.businsurance.excel.CasualtyPurchasingDetailImport;
import com.bjike.goddess.businsurance.to.CasualtyPurchasingDetailTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.secure.api.AbandonAPI;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryOptionBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 团体意外险购买详情业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class CasualtyPurchasingDetailSerImpl extends ServiceImpl<CasualtyPurchasingDetail, CasualtyPurchasingDetailDTO> implements CasualtyPurchasingDetailSer {
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private AbandonAPI abandonAPI;
    @Autowired
    private CasualtyPurchasingListSer casualtyPurchasingListSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(总经办)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是总经办岗位人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(福利模块审核)
     *
     * @throws SerException
     */
    private void checkModPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是福利模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(运营商务部审核)
     *
     * @throws SerException
     */
    private void checkBussPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是运营商务部人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对财务模块审核权限（福利模块审核）
     */
    private Boolean guideMondIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对查看权限（运营商务部）
     */
    private Boolean guideBussIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagMond = guideMondIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPosin = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagBuss = guideBussIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagMond || flagPosin || flagBuss) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case MODULEAUDIT:
                flag = guideMondIdentity();
                break;
            case MANAGEAUDIT:
                flag = guidePosinIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            case BUSINESSAUDIT:
                flag = guideBussIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countDetail(CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO) throws SerException {
        Long count = super.count(casualtyPurchasingDetailDTO);
        return count;
    }

    @Override
    public CasualtyPurchasingDetailBO getOneDetail(String id) throws SerException {
        CasualtyPurchasingDetail casualtyPurchasingDetail = super.findById(id);
        return BeanTransform.copyProperties(casualtyPurchasingDetail, CasualtyPurchasingDetailBO.class);
    }

    @Override
    public List<CasualtyPurchasingDetailBO> listDetail(CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO) throws SerException {
        checkPermission();
        casualtyPurchasingDetailDTO.getSorts().add("createTime=desc");
        List<CasualtyPurchasingDetail> list = super.findByCis(casualtyPurchasingDetailDTO, true);
        return BeanTransform.copyProperties(list, CasualtyPurchasingDetailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CasualtyPurchasingDetailBO addDetail(CasualtyPurchasingDetailTO casualtyPurchasingDetailTO) throws SerException {
        checkPermission();
        CasualtyPurchasingDetail casualtyPurchasingDetail = BeanTransform.copyProperties(casualtyPurchasingDetailTO, CasualtyPurchasingDetail.class, true);
        casualtyPurchasingDetail.setCreateTime(LocalDateTime.now());
        casualtyPurchasingDetail.setBuySocialSecurity(false);
        if (DateUtil.parseDate(casualtyPurchasingDetailTO.getEntryDate()) == LocalDate.now()) {
            casualtyPurchasingDetail.setBuyCasualtyStatus(BuyCasualtyStatus.TOINCREASE);
        }
        casualtyPurchasingDetail.setBuyCasualtyStatus(BuyCasualtyStatus.NOTTOBUY);
        super.save(casualtyPurchasingDetail);
        return BeanTransform.copyProperties(casualtyPurchasingDetail, CasualtyPurchasingDetailBO.class);
    }

    //TODO 这里的离职日期需要到离职模块中添加数据是读取过来数据还要改变购买意外险状态

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CasualtyPurchasingDetailBO editDetail(CasualtyPurchasingDetailTO casualtyPurchasingDetailTO) throws SerException {
        checkPermission();
        CasualtyPurchasingDetail casualtyPurchasingDetail = super.findById(casualtyPurchasingDetailTO.getId());
        casualtyPurchasingDetail.setStopBuyTime(DateUtil.parseDate(casualtyPurchasingDetailTO.getStopBuyTime()));
        casualtyPurchasingDetail.setModifyTime(LocalDateTime.now());
        super.update(casualtyPurchasingDetail);
        return BeanTransform.copyProperties(casualtyPurchasingDetail, CasualtyPurchasingDetailBO.class);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        checkPermission();
        List<CasualtyPurchasingDetail> list = super.findAll();
        List<CasualtyPurchasingDetailImport> casualtyPurchasingDetailImports = new ArrayList<>();
        list.stream().forEach(str -> {
            CasualtyPurchasingDetailImport excel = BeanTransform.copyProperties(str, CasualtyPurchasingDetailImport.class, "buySocialSecurity");
            excel.setBuySocialSecurity(str.getBuySocialSecurity() ? "是" : "否");
            casualtyPurchasingDetailImports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(casualtyPurchasingDetailImports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<CasualtyPurchasingDetailImport> casualtyPurchasingDetailImports = new ArrayList<>();
        CasualtyPurchasingDetailImport excel = new CasualtyPurchasingDetailImport();
        excel.setBeApplicantName("张三");
        excel.setEmployeeNo("ike001216");
        excel.setArea("广州");
        excel.setDepartment("研发部");
        excel.setJobs("项目负责人");
        excel.setEntryDate("2017-09-12");
        excel.setStopBuyTime("2017-12-12");
        casualtyPurchasingDetailImports.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(casualtyPurchasingDetailImports, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<CasualtyPurchasingDetailTO> casualtyPurchasingDetailTOS) throws SerException {
        checkPermission();
        List<CasualtyPurchasingDetail> casualtyPurchasingDetails = BeanTransform.copyProperties(casualtyPurchasingDetailTOS, CasualtyPurchasingDetail.class, true);
        casualtyPurchasingDetails.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(casualtyPurchasingDetails);
    }

    @Override
    public List<String> findEmpNo() throws SerException {
        List<String> empNums = new ArrayList<>();
        if (moduleAPI.isCheck("staffentry")) {
            empNums = entryRegisterAPI.findWorkingEmpNum();
        }
        List<String> nums = new ArrayList<>();
        List<CasualtyPurchasingDetail> casualtyPurchasingDetails = super.findAll();
        if (empNums != null && empNums.size() > 0) {
            for (String empNum : empNums) {
                Boolean bool = false;
                if (casualtyPurchasingDetails != null && casualtyPurchasingDetails.size() > 0) {
                    for (CasualtyPurchasingDetail casualtyPurchasingDetail : casualtyPurchasingDetails) {
                        if (casualtyPurchasingDetail.getEmployeeNo().equals(empNum)) {
                            bool = true;
                        }
                    }
                }
                if (!bool) {
                    nums.add(empNum);
                }
            }
        }
        return nums;
    }

    @Override
    public CasualtyPurchasingDetailBO findByEmpNo(String empNum) throws SerException {
        EntryOptionBO entryOptionBO = new EntryOptionBO();
        if (moduleAPI.isCheck("staffentry")) {
            entryOptionBO = entryRegisterAPI.getEntryOptionByEmpNum(empNum);
        }
        CasualtyPurchasingDetailBO casualtyPurchasingDetailBO = new CasualtyPurchasingDetailBO();
        casualtyPurchasingDetailBO.setBeApplicantName(entryOptionBO.getName());
        casualtyPurchasingDetailBO.setArea(entryOptionBO.getArea());
        casualtyPurchasingDetailBO.setDepartment(entryOptionBO.getDepartment());
        casualtyPurchasingDetailBO.setJobs(entryOptionBO.getPosition());
        casualtyPurchasingDetailBO.setEmployeeNo(empNum);
        casualtyPurchasingDetailBO.setEntryDate(entryOptionBO.getEntryTime());
        return casualtyPurchasingDetailBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void checkStatus() throws SerException {
        CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO = new CasualtyPurchasingDetailDTO();
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("buyCasualtyStatus", BuyCasualtyStatus.TOINCREASE));
        List<CasualtyPurchasingDetail> casualtyPurchasingDetails = super.findAll();
        if (casualtyPurchasingDetails != null && casualtyPurchasingDetails.size() > 0) {
            for (CasualtyPurchasingDetail casualtyPurchasingDetail : casualtyPurchasingDetails) {
                if (moduleAPI.isCheck("staffentry")) {
                    EntryOptionBO entryOptionBO = entryRegisterAPI.getEntryOptionByEmpNum(casualtyPurchasingDetail.getEmployeeNo());
                    if (DateUtil.parseDate(entryOptionBO.getEntryTime()) != LocalDate.now()) {
                        casualtyPurchasingDetail.setBuyCasualtyStatus(BuyCasualtyStatus.NOTTOBUY);
                        super.update(casualtyPurchasingDetail);
                    }
                }
            }
        }
    }

    public String boolToString(Boolean bool) throws SerException {
        String type = "否";
        if (bool) {
            type = "是";
        }
        return type;
    }

    @Override
    public List<String> findArea() throws SerException {
        List<CasualtyPurchasingDetail> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (CasualtyPurchasingDetail model : list) {
            String area = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findDepByArea(String area) throws SerException {
        CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO = new CasualtyPurchasingDetailDTO();
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("area", area));
        List<CasualtyPurchasingDetail> list = super.findByCis(casualtyPurchasingDetailDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (CasualtyPurchasingDetail model : list) {
            String department = model.getDepartment();
            if (StringUtils.isNotBlank(model.getDepartment())) {
                set.add(department);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<SummaryBO> summaDay(String summationDate) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(summationDate)) {
            summationDate = LocalDate.now().toString();
        }
        String[] date = new String[]{summationDate, summationDate};
        return totalMethod(date);
    }


    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }

    @Override
    public List<SummaryBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
       checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);

        return totalMethod(date);
    }

    @Override
    public List<SummaryBO> summaMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String[] date = new String[]{startDate, endDate};
        return totalMethod(date);
    }

    private List<SummaryBO> totalMethod(String[] date) throws SerException{
        Integer entryNum = 0;
        Integer givingBuyNum = 0;
        Integer leaveOffNum = 0;
        List<String> areas = findArea();
        List<SummaryBO> summaryBOS = new ArrayList<>();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {
                List<String> departments = findDepByArea(area);
                if (departments != null && departments.size() > 0) {
                    for (String department : departments) {
                        if (moduleAPI.isCheck("staffentry")) {
                            entryNum = entryRegisterAPI.findNumByEntryDate(date, area, department);
                        }
                        if (moduleAPI.isCheck("secure")) {
                            givingBuyNum = abandonAPI.findALL().size();
                            givingBuyNum = givingBuyNum == null ? 0 : givingBuyNum;
                        }
                        Integer casualtyIncreaseNum = casualtyPurchasingListSer.findCasualtyIncreaseNum(date);
                        //TODO 由于离职模块还未修改他的新功能 所有到离职模块中拿离职人数未做
                        SummaryBO summaryBO = new SummaryBO();
                        summaryBO.setArea(area);//地区
                        summaryBO.setDepartment(department);//项目组
                        summaryBO.setEntryNum(entryNum);//入职人数
                        summaryBO.setGivingBuyNum(givingBuyNum);//放弃购买社保人数
                        summaryBO.setCasualtyIncreaseNum(casualtyIncreaseNum);//意外险增员人数
                        summaryBO.setCasualtyBuyNum(casualtyBuyNum(area,department));//意外险购买人数
                        summaryBO.setNoBuyCasualtyNum(noBuyCasualtyNum(area,department));//未购买意外险人数
                        summaryBO.setLeaveOffNum(leaveOffNum);//离职人数
                        summaryBO.setCasualtyAttritionNum(casualtyAttritionNum(date,area,department));//意外险减员人数
                        summaryBOS.add(summaryBO);
                    }
                }
            }
        }
        return summaryBOS;
    }

    @Override
    public List<SummaryBO> summaTotal(String endDate) throws SerException {
      checkPermission();
        if(StringUtils.isBlank(endDate)){
            endDate = LocalDate.now().toString();
        }

        Integer entryNum = 0;//入职人数
        Integer givingBuyNum = 0;//放弃购买社保人数
        Integer leaveOffNum = 0;//离职人数
        List<String> areas = findArea();
        List<SummaryBO> summaryBOS = new ArrayList<>();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {
                List<String> departments = findDepByArea(area);
                if (departments != null && departments.size() > 0) {
                    for (String department : departments) {
                        if (moduleAPI.isCheck("staffentry")) {
                            entryNum = entryRegisterAPI.findNumByEntryDate(endDate, area, department);
                        }
                        if (moduleAPI.isCheck("secure")) {
                            givingBuyNum = abandonAPI.findALL().size();
                            givingBuyNum = givingBuyNum == null ? 0 : givingBuyNum;
                        }
                        Integer casualtyIncreaseNum = casualtyPurchasingListSer.findCasualtyIncreaseNum(endDate);
                        //TODO 由于离职模块还未修改他的新功能 所有到离职模块中拿离职人数未做
                        //意外险减员人数

                        SummaryBO summaryBO = new SummaryBO();
                        summaryBO.setArea(area);//地区
                        summaryBO.setDepartment(department);//项目组
                        summaryBO.setEntryNum(entryNum);//入职人数
                        summaryBO.setGivingBuyNum(givingBuyNum);//放弃购买社保人数
                        summaryBO.setCasualtyIncreaseNum(casualtyIncreaseNum);//意外险增员人数
                        summaryBO.setCasualtyBuyNum(casualtyBuyNum(area,department));//意外险购买人数
                        summaryBO.setNoBuyCasualtyNum(noBuyCasualtyNum(area,department));//未购买意外险人数
                        summaryBO.setLeaveOffNum(leaveOffNum);//离职人数
                        summaryBO.setCasualtyAttritionNum(casualtyAttritionNum(endDate,area,department));//意外险减员人数
                        summaryBOS.add(summaryBO);
                    }
                }
            }
        }
        return summaryBOS;
    }

    //意外险购买人数
    public Integer casualtyBuyNum(String area, String department) throws SerException {
        Integer num = 0;
        CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO = new CasualtyPurchasingDetailDTO();
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("area", area));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("department", department));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("buyCasualtyStatus", BuyCasualtyStatus.HAVETOBUY));
        List<CasualtyPurchasingDetail> casualtyPurchasingDetails = super.findByCis(casualtyPurchasingDetailDTO);
        if (casualtyPurchasingDetails != null && casualtyPurchasingDetails.size() > 0) {
            num = casualtyPurchasingDetails.size();
        }
        return num;
    }

    //未购买意外险人数
    public Integer noBuyCasualtyNum(String area, String department) throws SerException {
        Integer num = 0;
        CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO = new CasualtyPurchasingDetailDTO();
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("area", area));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("department", department));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.or("buyCasualtyStatus", BuyCasualtyStatus.WORKERSTOSTAY));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.or("buyCasualtyStatus", BuyCasualtyStatus.NOTTOBUY));
        List<CasualtyPurchasingDetail> casualtyPurchasingDetails = super.findByCis(casualtyPurchasingDetailDTO);
        if (casualtyPurchasingDetails != null && casualtyPurchasingDetails.size() > 0) {
            num = casualtyPurchasingDetails.size();
        }
        return num;
    }

    //意外险减员人数
    public Integer casualtyAttritionNum(String[] date, String area, String department) throws SerException {
        Integer num = 0;
        CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO = new CasualtyPurchasingDetailDTO();
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.between("stopBuyTime", date));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("area", area));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("department", department));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("buyCasualtyStatus", BuyCasualtyStatus.WORKERSLEAVE));
        List<CasualtyPurchasingDetail> casualtyPurchasingDetails = super.findByCis(casualtyPurchasingDetailDTO);
        if (casualtyPurchasingDetails != null && casualtyPurchasingDetails.size() > 0) {
            num = casualtyPurchasingDetails.size();
        }
        return num;
    }
    //意外险减员人数
    public Integer casualtyAttritionNum(String endDate, String area, String department) throws SerException {
        Integer num = 0;
        CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO = new CasualtyPurchasingDetailDTO();
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.lt_eq("stopBuyTime", endDate));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("area", area));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("department", department));
        casualtyPurchasingDetailDTO.getConditions().add(Restrict.eq("buyCasualtyStatus", BuyCasualtyStatus.WORKERSLEAVE));
        List<CasualtyPurchasingDetail> casualtyPurchasingDetails = super.findByCis(casualtyPurchasingDetailDTO);
        if (casualtyPurchasingDetails != null && casualtyPurchasingDetails.size() > 0) {
            num = casualtyPurchasingDetails.size();
        }
        return num;
    }
}