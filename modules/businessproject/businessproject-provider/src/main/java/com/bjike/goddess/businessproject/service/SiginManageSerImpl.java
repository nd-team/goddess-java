package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.*;
import com.bjike.goddess.businessproject.dto.SiginManageDTO;
import com.bjike.goddess.businessproject.entity.SiginManage;
import com.bjike.goddess.businessproject.enums.*;
import com.bjike.goddess.businessproject.excel.SiginManageExport;
import com.bjike.goddess.businessproject.excel.SiginManageTemplateExport;
import com.bjike.goddess.businessproject.excel.SonPermissionObject;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.to.SiginManageTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商务项目合同签订与立项管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T19:37:28.303 ]
 * @Description: [ 商务项目合同签订与立项管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class SiginManageSerImpl extends ServiceImpl<SiginManage, SiginManageDTO> implements SiginManageSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DispatchSheetSer dispatchSheetSer;
    @Autowired
    private ContractCategorySer contractCategorySer;
    @Autowired
    private CollectEmailSer collectEmailSer;
    @Autowired
    private BaseInfoManageSer baseInfoManageSer;


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
            flag = cusPermissionSer.getCusPermission("1",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
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
            flag = cusPermissionSer.getCusPermission("2",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.busCusPermission("2",null);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
//        String userToken = RpcTransmit.getUserToken();
//        Boolean flagSeeSign = guideSeeIdentity();
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagAddSign = guideAddIdentity();
        Boolean flag1 = false;
        Boolean flag2 = false;
        Boolean flag3 = false;
        Boolean flag4 = false;
        Boolean flag5 = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag1 = cusPermissionSer.getCusPermission("1",userBO);
            flag2 = cusPermissionSer.getCusPermission("2",userBO);
            flag3 = cusPermissionSer.busCusPermission("3",userBO);
            flag4 = cusPermissionSer.modCusPermission("4",userBO);
            flag5 = cusPermissionSer.modCusPermission("5",userBO);
        } else {
            flag1 = true;
            flag2 = true;
            flag3 = true;
            flag4 = true;
            flag5 = true;
        }
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("siginmanage");
        obj.setDescribesion("商务项目合同签订与立项");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        obj = new SonPermissionObject();
        obj.setName("dispatchsheet");
        obj.setDescribesion("商务项目派工单信息管理");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("contractcategory");
        obj.setDescribesion("商务项目合同类型");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("collectemail");
        obj.setDescribesion("商务项目合同邮件发送");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("baseinfomanage");
        obj.setDescribesion("商务项目合同基本信息");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("businesscontract");
        obj.setDescribesion("商务项目合同");
        if (flag1 || flag2 || flag3 || flag4 || flag5) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("outsourcbusinesscontract");
        obj.setDescribesion("外包半外包项目合同管理");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
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
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    @Override
    public Long countSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        searchCondition(siginManageDTO);
        Long count = super.count(siginManageDTO);
        return count;
    }

    @Override
    public SiginManageBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }

        SiginManage siginManage = super.findById(id);
        return BeanTransform.copyProperties(siginManage, SiginManageBO.class);
    }

    @Override
    public List<SiginManageBO> listSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        siginManageDTO.getSorts().add("createTime=desc");
        checkSeeIdentity();

        searchCondition(siginManageDTO);
        List<SiginManage> list = super.findByPage(siginManageDTO);
        List<SiginManageBO> siginManageBOS = new ArrayList<>();
        list.stream().forEach(str -> {
            SiginManageBO bo = BeanTransform.copyProperties(str, SiginManageBO.class, "businessType", "businessCooperate", "contractProperty");
            bo.setBusinessType(str.getBusinessType());
            bo.setBusinessCooperate(str.getBusinessCooperate());
            bo.setContractProperty(str.getContractProperty());
            bo.setRemark(str.getRemark());
            siginManageBOS.add(bo);
        });
        return siginManageBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO addSiginManage(SiginManageTO siginManageTO) throws SerException {
        checkAddIdentity();
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate date = LocalDate.parse(siginManageTO.getStartProjectTime(),format);
        try {
            DateUtil.parseDate(siginManageTO.getStartProjectTime());
            DateUtil.parseDate(siginManageTO.getEndProjectTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对");
        }
        SiginManage siginManage = BeanTransform.copyProperties(siginManageTO, SiginManage.class, true);

        siginManage.setCreateTime(LocalDateTime.now());
        super.save(siginManage);

        SiginManageBO siginManageBO = BeanTransform.copyProperties(siginManage, SiginManageBO.class);
        return siginManageBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO editSiginManage(SiginManageTO siginManageTO) throws SerException {
        checkAddIdentity();

        SiginManage temp = super.findById(siginManageTO.getId());

        try {
            DateUtil.parseDate(siginManageTO.getStartProjectTime());
            DateUtil.parseDate(siginManageTO.getEndProjectTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对");
        }

        SiginManage siginManage = BeanTransform.copyProperties(siginManageTO, SiginManage.class, true);
        BeanUtils.copyProperties(siginManage, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        SiginManageBO siginManageBO = BeanTransform.copyProperties(temp, SiginManageBO.class);
        return siginManageBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSiginManage(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO auditSiginManage(SiginManageTO siginManageTO) throws SerException {
        if (StringUtils.isBlank(siginManageTO.getId())) {
            throw new SerException("id不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        checkAddIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SiginManage temp = super.findById(siginManageTO.getId());

        temp.setManager(userAPI.currentUser().getUsername());
        temp.setAuditAdvice(siginManageTO.getAuditAdvice());
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        SiginManageBO siginManageBO = BeanTransform.copyProperties(temp, SiginManageBO.class);
        return siginManageBO;
    }


    public void searchCondition(SiginManageDTO siginManageDTO) throws SerException {
        /**
         * 业务类型
         */
        if (siginManageDTO.getBusinessType() != null) {
            siginManageDTO.getConditions().add(Restrict.eq("businessType", siginManageDTO.getBusinessType()));
        }
        /**
         * 业务方向科目
         */
        if (StringUtils.isNotBlank(siginManageDTO.getBusinessSubject())) {
            siginManageDTO.getConditions().add(Restrict.like("businessSubject", siginManageDTO.getBusinessSubject()));
        }
        /**
         * 合作方式
         */
        if (siginManageDTO.getBusinessCooperate() != null) {
            siginManageDTO.getConditions().add(Restrict.eq("businessCooperate", siginManageDTO.getBusinessCooperate()));
        }
        /**
         * 甲方公司
         */
        if (StringUtils.isNotBlank(siginManageDTO.getFirstCompany())) {
            siginManageDTO.getConditions().add(Restrict.like("firstCompany", siginManageDTO.getFirstCompany()));
        }
        /**
         * 乙方公司
         */
        if (StringUtils.isNotBlank(siginManageDTO.getSecondCompany())) {
            siginManageDTO.getConditions().add(Restrict.like("secondCompany", siginManageDTO.getSecondCompany()));
        }
        /**
         * 地区
         */
        if (StringUtils.isNotBlank(siginManageDTO.getArea())) {
            siginManageDTO.getConditions().add(Restrict.like("area", siginManageDTO.getArea()));
        }
        /**
         * 合同属性
         */
        if (siginManageDTO.getContractProperty() != null) {
            siginManageDTO.getConditions().add(Restrict.eq("contractProperty", siginManageDTO.getContractProperty()));
        }
        /**
         * 立项情况
         */
        if (StringUtils.isNotBlank(siginManageDTO.getMakeProject())) {
            siginManageDTO.getConditions().add(Restrict.eq("makeProject", siginManageDTO.getMakeProject()));
        }


    }

    @Override
    public List<String> listArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<SiginManageBO> siginManageBOS = super.findBySql("select area from businessproject_siginmanage group by area order by area asc ", SiginManageBO.class, fields);

        List<String> areaList = siginManageBOS.stream().map(SiginManageBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO importExcel(List<SiginManageTO> siginManageTO) throws SerException {

        List<SiginManage> siginManage = BeanTransform.copyProperties(siginManageTO, SiginManage.class, true);
        siginManage.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(siginManage);

        SiginManageBO siginManageBO = BeanTransform.copyProperties(new SiginManage(), SiginManageBO.class);
        return siginManageBO;
    }

    @Override
    public byte[] exportExcel(SiginManageDTO dto) throws SerException {
//        getCusPermission();

        if (StringUtils.isNotBlank(dto.getInnerProject())) {
            dto.getConditions().add(Restrict.eq("innerProject", dto.getInnerProject()));
        }

        List<SiginManage> list = super.findByCis(dto);

        List<SiginManageExport> siginManageExports = new ArrayList<>();
        list.stream().forEach(str -> {
            SiginManageExport excel = BeanTransform.copyProperties(str, SiginManageExport.class, "businessType", "businessCooperate", "contractProperty", "projectStatus", "makeProject");
            excel.setBusinessType(BusinessType.exportStrConvert(str.getBusinessType()));
            excel.setBusinessCooperate(BusinessCooperate.exportStrConvert(str.getBusinessCooperate()));
            excel.setContractProperty(ContractProperty.exportStrConvert(str.getContractProperty()));
            excel.setProjectStatus(ProjectStatus.exportStrConvert(str.getProjectStatus()));
            excel.setMakeProject(MakeContract.exportStrConvert(str.getMakeProject()));
            siginManageExports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(siginManageExports, excel);
        return bytes;
    }


    @Override
    public byte[] templateExport() throws SerException {
//        getCusPermission();

        List<SiginManageTemplateExport> siginManageExports = new ArrayList<>();

        SiginManageTemplateExport excel = new SiginManageTemplateExport();
        excel.setBusinessType("移动通信类");
        excel.setBusinessSubject("test");
        excel.setBusinessCooperate("租赁合同");
        excel.setOuterProject("test");
        excel.setFirstCompany("test");
        excel.setSecondCompany("test");
        excel.setArea("test");
        excel.setMoney(12.0d);
        excel.setStartProjectTime(LocalDate.now());
        excel.setEndProjectTime(LocalDate.now());
        excel.setSiginStatus("已签订");
        excel.setContractProperty("框架合同");
        excel.setMakeProject("立项");
        excel.setInnerProject("test");
        excel.setProjectGroup("test");
        excel.setProjectCharge("test");
        excel.setRemark("");
        excel.setProjectStatus("进场");
        excel.setTaskNum("test");
        excel.setContractScale(0d);
        excel.setScale(0d);
        excel.setMajor("test");
        siginManageExports.add(excel);

        SiginManageTemplateExport excel2 = new SiginManageTemplateExport();
        BeanUtils.copyProperties(excel, excel2);
        excel.setSiginStatus("未签订");
        excel.setMakeProject("预立项");
        siginManageExports.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(siginManageExports, exce);
        return bytes;
    }


    @Override
    public List<String> listInnerProject() throws SerException {
        String[] fields = new String[]{"innerProject"};
        List<SiginManageBO> siginManageBOS = super.findBySql("select innerProject from businessproject_siginmanage group by innerProject order by innerProject asc ", SiginManageBO.class, fields);

        List<String> innerProjectList = siginManageBOS.stream().map(SiginManageBO::getInnerProject)
                .filter(str -> (str != null || !"".equals(str.trim()))).distinct().collect(Collectors.toList());


        return innerProjectList;
    }

    @Override
    public SiginManageBO findByProject(String name) throws SerException {
        if (StringUtils.isNotBlank(name)) {
            SiginManageDTO dto = new SiginManageDTO();
            dto.getConditions().add(Restrict.eq("innerProject", name));
            List<SiginManage> siginManageList = super.findByCis(dto);
            if (null != siginManageList && siginManageList.size() > 0) {
                return BeanTransform.copyProperties(siginManageList.get(0), SiginManageBO.class, false);
            }
        }
        return null;
    }

    @Override
    public Boolean findCompleteStatus(String projectName) throws SerException {
        if (StringUtils.isNotBlank(projectName)) {
            SiginManageDTO dto = new SiginManageDTO();
            dto.getConditions().add(Restrict.eq("innerProject", projectName));
            List<SiginManage> siginManageList = super.findByCis(dto);
            if (null != siginManageList && siginManageList.size() > 0) {
                String time = DateUtil.dateToString(siginManageList.get(0).getEndProjectTime());
                String localTime = DateUtil.dateToString(LocalDate.now());
                //time<localTime,返回true
                return isOrder(time, localTime);
            }
        }
        return null;
    }

    private Boolean isOrder(String date1, String date2) throws SerException {
        Boolean tar = false;
        try {
            //a1报名截止时间，b1,当前时间
            Date a1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            Date b1 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
            int result = a1.compareTo(b1);
            if (result <= 0) {
                //a1<a2
                tar = true;
            }
            return tar;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    public static void main(String args[]) throws SerException {
        Boolean tar = false;
        String date1 = "2017-08-09";
        String date2 = "2017-08-10";
        try {
            //a1报名截止时间，b1,当前时间
            Date a1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            Date b1 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
            int result = a1.compareTo(b1);
            if (result <= 0) {
                //能报名,a1<a2
                tar = true;
            }
            System.out.println(tar);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public Set<String> makeProjects() throws SerException {
        Set<String> makeProjects = new HashSet<>();
        List<SiginManage> list = super.findAll();
        for (SiginManage siginManage : list) {
            makeProjects.add(String.valueOf(siginManage.getMakeProject()));
        }
        return makeProjects;
    }

    @Override
    public OptionMakeBO weekCollectFigure(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各地区立项情况金额周汇总" + startDate + "-" + endDate;
        return makeContractFigure(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO monthCollectFigure(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各地区立项情况金额月汇总" + startDate + "-" + endDate;
        return makeContractFigure(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO quarterCollectFigure(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各地区立项情况金额季度汇总" + startDate + "-" + endDate;
        return makeContractFigure(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO yearCollectFigure(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各地区立项情况金额年汇总" + startDate + "-" + endDate;
        return makeContractFigure(startDate, endDate, text_1);
    }

    private OptionMakeBO makeContractFigure(String startDate, String endDate, String text_1) throws SerException {
        List<MakeContractFigureBO> figureBOS = new ArrayList<>();
        List<MakeContractFigureBO> contractFigureBOS = new ArrayList<>();
        String[] fields = new String[]{"area", "noMakeNum"};
        StringBuilder sb = new StringBuilder();
        //预立项 立项 不立项
        sb.append(" SELECT area AS area,ifnull(sum(money) ,0) AS noMakeNum FROM businessproject_siginmanage ");
        sb.append(" WHERE  startProjectTime BETWEEN '" + startDate + "' AND '" + endDate + "' AND makeProject=0 ");
        sb.append(" GROUP BY area ");
        List<MakeContractFigureBO> boList = super.findBySql(sb.toString(), MakeContractFigureBO.class, fields);
        for (MakeContractFigureBO bo : boList) {
            String[] hadFields = new String[]{"hadMakeNum"};
            String hadSql = " SELECT ifnull(sum(money) ,0) AS hadMakeNum FROM businessproject_siginmanage WHERE startProjectTime BETWEEN '" + startDate + "' AND '" + endDate + "' AND makeProject=1 AND area='" + bo.getArea() + "' ";
            contractFigureBOS = super.findBySql(hadSql, MakeContractFigureBO.class, hadFields);
            for (MakeContractFigureBO bo1 : contractFigureBOS) {
                bo.setHadMakeNum(bo1.getHadMakeNum());
            }
            String[] notFields = new String[]{"notMakeNum"};
            String notSql = " SELECT ifnull(sum(money) ,0) AS notMakeNum FROM businessproject_siginmanage WHERE startProjectTime BETWEEN '" + startDate + "' AND '" + endDate + "' AND makeProject=2 AND area='" + bo.getArea() + "' ";
            contractFigureBOS = super.findBySql(notSql, MakeContractFigureBO.class, notFields);
            for (MakeContractFigureBO bo1 : contractFigureBOS) {
                bo.setNotMakeNum(bo1.getNotMakeNum());
            }
            figureBOS.add(bo);
        }
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"未立项合同金额", "立项合同金额", "不立项合同金额"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Double> noNum = new ArrayList<>();
            List<Double> hadNum = new ArrayList<>();
            List<Double> notNum = new ArrayList<>();
            for (MakeContractFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getArea());

                //柱状图数据
                noNum.add(figureBO.getNoMakeNum());
                hadNum.add(figureBO.getHadMakeNum());
                notNum.add(figureBO.getNotMakeNum());
            }
            List<List<Double>> nums = new ArrayList<>();
            nums.add(noNum);
            nums.add(hadNum);
            nums.add(notNum);
            String[] ziduan = new String[]{"未立项合同金额", "立项合同金额", "不立项合同金额"};
            for (int i = 0; i < 3; i++) {
                SeriesBBO seriesBO = new SeriesBBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Double[] text_int_4 = new Double[nums.get(i).size()];
                text_int_4 = nums.get(i).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBBO[] text_4 = new SeriesBBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionMakeBO optionBO = new OptionMakeBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);
        optionBO.setTooltip(tooltipBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    //季度
    private String[] quarter(Integer year, Integer quarter) throws SerException {
        String startDate = null;
        String endDate = null;
        switch (quarter) {
            case 1:
                startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 3, DateUtil.getDayByDate(year, 3)));
                break;
            case 2:
                startDate = DateUtil.dateToString(LocalDate.of(year, 4, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 6, DateUtil.getDayByDate(year, 6)));
                break;
            case 3:
                startDate = DateUtil.dateToString(LocalDate.of(year, 7, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 9, DateUtil.getDayByDate(year, 9)));
                break;
            case 4:
                startDate = DateUtil.dateToString(LocalDate.of(year, 10, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
                break;
            default:
                startDate = DateUtil.dateToString(LocalDate.now());
                endDate = DateUtil.dateToString(LocalDate.now());
                break;
        }

        return new String[]{startDate, endDate};
    }

}