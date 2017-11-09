package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.customer.bo.*;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.dto.CustomerDetailDTO;
import com.bjike.goddess.customer.entity.CusFamilyMember;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;
import com.bjike.goddess.customer.entity.CustomerDetail;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.enums.*;
import com.bjike.goddess.customer.excel.CustomerBaseInfoExport;
import com.bjike.goddess.customer.excel.CustomerBaseInfoExportTemple;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.customer.to.CustomerDetailTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 客户基本信息业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.065 ]
 * @Description: [ 客户基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CustomerBaseInfoSerImpl extends ServiceImpl<CustomerBaseInfo, CustomerBaseInfoDTO> implements CustomerBaseInfoSer {

    @Autowired
    private CustomerLevelSer customerLevelSer;
    @Autowired
    private CusFamilyMemberSer cusFamilyMemberSer;
    @Autowired
    private CustomerDetailSer customerDetailSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private AreaWeightSetSer areaWeightSetSer;
    @Autowired
    private BussTypeWeightSetSer bussTypeWeightSetSer;
    @Autowired
    private ClosenessFoactorWeightSer closenessFoactorWeightSer;
    @Autowired
    private FirstFactorWeightSer firstFactorWeightSer;
    @Autowired
    private FunPowerWeightSer funPowerWeightSer;
    @Autowired
    private TimelinessFactorWeightSer timelinessFactorWeightSer;
    @Autowired
    private CustomerContactWeightSetSer customerContactWeightSetSer;
    @Autowired
    private DifficultyFoactorWeightSer difficultyFoactorWeightSer;


    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity("1");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity("3");
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
                flag = guideSeeIdentity("1");
                break;
            case ADD:
                flag = guideAddIdentity("3");
                break;
            case EDIT:
                flag = guideAddIdentity("3");
                break;
            case DELETE:
                flag = guideAddIdentity("3");
                break;
            case UPLOAD:
                flag = guideAddIdentity("3");
                break;
            case SEEFILE:
                flag = guideAddIdentity("3");
                break;
            case DOWNLOAD:
                flag = guideAddIdentity("3");
                break;
            case CONGEL:
                flag = guideAddIdentity("3");
                break;
            case THAW:
                flag = guideAddIdentity("3");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public CustomerBaseInfoBO generateCustomerNum() throws SerException {
        String[] fields = new String[]{"customerNum", "customerName"};
        List<CustomerBaseInfoBO> customerBaseInfoBOS = super.findBySql("select customernum as customerNum,customername as customerName from customer_customerbaseinfo", CustomerBaseInfoBO.class, fields);

        Set<String> cusNumberSet = customerBaseInfoBOS.stream().filter(cus -> cus.getCustomerNum().length() > 5).map(CustomerBaseInfoBO::getCustomerNum).collect(Collectors.toSet());
        List<Integer> cusNumberList = new ArrayList<>(0);
        for (String num : cusNumberSet) {
            if (NumberUtils.isNumber(num.substring(5))) {
                cusNumberList.add(Integer.valueOf(num.substring(5)));
            }
        }
        Integer maxInt = ((cusNumberList != null && cusNumberList.size() > 0) ? Collections.max(cusNumberList) : 0) + 0001;
        String customerNumber = "CS000" + maxInt;

        CustomerBaseInfoBO cbo = new CustomerBaseInfoBO();
        cbo.setCustomerNum(customerNumber);
        return cbo;
    }


    @Override
    public List<CustomerBaseInfoBO> listCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        //列表权限
        checkSeeIdentity("1");
        searchCondi(customerBaseInfoDTO);
//        customerBaseInfoDTO.getSorts().add("finalWeight=desc");
        List<CustomerBaseInfo> list = super.findByCis(customerBaseInfoDTO, true);

        List<CustomerBaseInfoBO> customerBaseInfoBOList = new ArrayList<>();
        list.stream().forEach(str -> {
            CustomerLevelBO customerLevelBO = BeanTransform.copyProperties(str.getCustomerLevel(), CustomerLevelBO.class);
            CustomerBaseInfoBO customerBaseInfoBO = BeanTransform.copyProperties(str, CustomerBaseInfoBO.class);
            customerBaseInfoBO.setCustomerLevelBO(customerLevelBO);
            customerBaseInfoBOList.add(customerBaseInfoBO);
        });

        return customerBaseInfoBOList;
    }

    public void searchCondi(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        if (StringUtils.isNotBlank(customerBaseInfoDTO.getCustomerName())) {
            customerBaseInfoDTO.getConditions().add(Restrict.eq("customerName", customerBaseInfoDTO.getCustomerName()));
        }
    }

    @Override
    public Long countCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        searchCondi(customerBaseInfoDTO);
        Long count = super.count(customerBaseInfoDTO);
        return count;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerBaseInfoBO addCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        checkAddIdentity("3");

        CustomerBaseInfoDTO dto = new CustomerBaseInfoDTO();
        dto.getConditions().add(Restrict.eq("customerName", customerBaseInfoTO.getCustomerName()));
        Long count = super.count(dto);
        if (count > 0) {
            throw new SerException("添加失败，客户名已存在,请重新命名");
        } else {
            String levelName = customerBaseInfoTO.getCustomerLevelName();
            CustomerLevelBO customerLevelBO = customerLevelSer.getCustomerLevelByName(levelName);
            CustomerLevel customerLevel = BeanTransform.copyProperties(customerLevelBO, CustomerLevel.class, true);

            RpcTransmit.transmitUserToken(userToken);

            performance(customerBaseInfoTO);
            CustomerBaseInfo customerBaseInfo = BeanTransform.copyProperties(customerBaseInfoTO, CustomerBaseInfo.class, true, "visitStatus", "marketReceptTime");
            if (StringUtils.isNotBlank(customerBaseInfoTO.getMarketReceptTime())) {
                customerBaseInfo.setMarketReceptTime(DateUtil.parseDateTime(customerBaseInfoTO.getMarketReceptTime()));
            }
            customerBaseInfo.setArea(customerBaseInfoTO.getArea().trim());
            customerBaseInfo.setCustomerName(customerBaseInfoTO.getCustomerName().trim());
            customerBaseInfo.setCreateTime(LocalDateTime.now());
            customerBaseInfo.setModifyPersion(userAPI.currentUser().getUsername());
            customerBaseInfo.setCustomerLevel(customerLevel);
            customerBaseInfo.setUpdateDate(LocalDate.now());
            customerBaseInfo.setCustomerMainStatus(CustomerMainStatus.nothing);
            if (customerBaseInfoTO.getVisitStatus() == null) {
                customerBaseInfo.setVisitStatus(VisitStatus.UNSCHEDULEDVISIT);
            } else {
                customerBaseInfo.setVisitStatus(customerBaseInfoTO.getVisitStatus());
            }
            customerBaseInfo.setCustomerPosition(
                    Double.parseDouble(customerBaseInfoTO.getCustomerNum().substring(5, customerBaseInfoTO.getCustomerNum().length())));

            super.save(customerBaseInfo);

            //添加客户详细信息
            CustomerDetailTO customerDetailTO = new CustomerDetailTO();
            customerDetailTO.setCustomerNum(customerBaseInfo.getCustomerNum());
            customerDetailSer.addCustomerDetail(customerDetailTO);

            return BeanTransform.copyProperties(customerBaseInfoTO, CustomerBaseInfoBO.class);
        }
    }


    //TODO 客户维护状态的变动,具体请看需求excel  lijuntao
    //TODO 市场招待,商务洽谈,业务拓展添加中将该客户所对应的市场编号获取过来,有多个(市场编号1,市场编号2,市场编号3) lijuntao
    //TODO 在市场招待中添加时查询这边是否有这条数据如果有,就将完成时间更新最近一次市场招待时间字段
    //TODO 商务活动记录字段的值到商务洽谈中获取,具体获取什么,由于此模块还未重新开发待需求说明
    //TODO 市场招待记录汇总字段的值到市场招待中获取,具体获取什么,由于此模块还未重新开发待需求说明
    //TODO 市场信息记录汇总字段的值到市场信息中获取,具体获取什么,由于此模块还未重新开发待需求说明
    //TODO 市场招待添加时对相应的客户信息进行修改是否需进行市场招待改为是

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerBaseInfoBO editCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块编辑权限
        checkAddIdentity("3");

        //查一遍级别
        CustomerBaseInfo cusBase = null;
        try {
            String levelName = customerBaseInfoTO.getCustomerLevelName();

            CustomerLevelBO customerLevelBO = customerLevelSer.getCustomerLevelByName(levelName);
            CustomerLevel customerLevel = BeanTransform.copyProperties(customerLevelBO, CustomerLevel.class, true);
            //先查一遍基本信息
            cusBase = super.findById(customerBaseInfoTO.getId());
            performance(customerBaseInfoTO);

            CustomerBaseInfo customerBaseInfo = BeanTransform.copyProperties(customerBaseInfoTO, CustomerBaseInfo.class, true);
            customerBaseInfo.setArea(customerBaseInfoTO.getArea().trim());
            customerBaseInfo.setCustomerName(customerBaseInfoTO.getCustomerName().trim());
            customerBaseInfo.setCustomerLevel(customerLevel);
            if (customerBaseInfoTO.getVisitStatus() == null) {
                customerBaseInfo.setVisitStatus(VisitStatus.UNSCHEDULEDVISIT);
            } else {
                customerBaseInfo.setVisitStatus(customerBaseInfoTO.getVisitStatus());
            }

            BeanUtils.copyProperties(customerBaseInfo, cusBase, "customerNum", "status", "customerDetail", "createTime", "id", "customerPosition");
            cusBase.setModifyTime(LocalDateTime.now());
            RpcTransmit.transmitUserToken(userToken);
            cusBase.setModifyPersion(userAPI.currentUser().getUsername());
            super.update(cusBase);
        } catch (SerException e) {
            throw new SerException("更新失败" + e.getMessage());
        }
        CustomerBaseInfoBO b = BeanTransform.copyProperties(cusBase, CustomerBaseInfoBO.class);
        return b;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCustomerBaseInfo(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块删除权限
        checkAddIdentity("3");

        RpcTransmit.transmitUserToken(userToken);
        try {
            CustomerBaseInfo customerBaseInfo = super.findById(id);
            CustomerDetail detail = customerBaseInfo.getCustomerDetail();
            if (detail != null) {
                CusFamilyMemberDTO familyMemberDTO = new CusFamilyMemberDTO();
                familyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id", detail.getId()));
                List<CusFamilyMember> cusFamilyMemberList = cusFamilyMemberSer.findByCis(familyMemberDTO);
                if (cusFamilyMemberList != null && cusFamilyMemberList.size() > 0) {
                    //删除家庭信息
                    cusFamilyMemberSer.remove(cusFamilyMemberList);
                }
                //删除详细信息
                customerDetailSer.remove(detail);
                super.remove(customerBaseInfo);
            } else {
                super.remove(customerBaseInfo);
            }
        } catch (SerException e) {
            throw new SerException("删除出现错误，删除失败" + e.getMessage());
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCustomerBaseInfo(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块冻结权限
        checkAddIdentity("3");

        RpcTransmit.transmitUserToken(userToken);
        try {
            CustomerBaseInfo customerBaseInfo = super.findById(id);
            customerBaseInfo.setModifyPersion(userAPI.currentUser().getUsername());
            customerBaseInfo.setModifyTime(LocalDateTime.now());
            customerBaseInfo.setStatus(Status.CONGEAL);

            super.update(customerBaseInfo);
        } catch (SerException e) {
            throw new SerException("冻结出现错误，冻结失败" + e.getMessage());
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCustomerBaseInfo(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块解冻权限
        checkAddIdentity("3");
        RpcTransmit.transmitUserToken(userToken);
        try {
            CustomerBaseInfo customerBaseInfo = super.findById(id);
            customerBaseInfo.setModifyPersion(userAPI.currentUser().getUsername());
            customerBaseInfo.setModifyTime(LocalDateTime.now());
            customerBaseInfo.setStatus(Status.THAW);

            super.update(customerBaseInfo);
        } catch (SerException e) {
            throw new SerException("解冻出现错误，解冻失败" + e.getMessage());
        }
    }

    @Override
    public List<String> getCustomerBaseInfoCusNum() throws SerException {
        String[] fields = new String[]{"customerNum"};
        List<CustomerBaseInfoBO> customerBaseInfoBOS = super.findBySql(
                "select customerNum  from customer_customerbaseinfo where customerNum not in ( select d.customerNum from customer_customerdetail AS d\n" +
                        "  ) order by customerNum asc ", CustomerBaseInfoBO.class, fields);

        List<String> areaList = customerBaseInfoBOS.stream().map(CustomerBaseInfoBO::getCustomerNum)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());
        List<Integer> num = new ArrayList<>();
        areaList.stream().forEach(str -> {
            num.add(Integer.parseInt(str.substring(4, str.length())));
        });
        Collections.sort(num, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        List<String> numList = new ArrayList<>();
        num.stream().forEach(str -> {
            numList.add("CS000" + str);
        });

        return numList;
    }

    @Override
    public List<String> getCustomerBaseInfoArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<CustomerBaseInfoBO> customerBaseInfoBOS = super.findBySql("select distinct area from customer_customerbaseinfo group by area  order by area asc ", CustomerBaseInfoBO.class, fields);

        List<String> areaList = customerBaseInfoBOS.stream().map(CustomerBaseInfoBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());
        List<String> areas = new ArrayList<>();
        areaList.stream().forEach(str -> {
            str = str.trim();
            if (!areas.contains(str)) {
                areas.add(str);
            }
        });

        return areas;
    }


    @Override
    public List<String> getCustomerBaseInfoName() throws SerException {
        String[] fields = new String[]{"customerName"};
        List<CustomerBaseInfoBO> customerBaseInfoBOS = super.findBySql("select distinct customername  from customer_customerbaseinfo", CustomerBaseInfoBO.class, fields);

        List<String> customerNameList = customerBaseInfoBOS.stream().map(CustomerBaseInfoBO::getCustomerName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return customerNameList;
    }

    /**
     * 客户信息详细完成度
     *
     * @param customerBaseInfoTO
     */
    private void performance(CustomerBaseInfoTO customerBaseInfoTO) {
        double sum = 0, now = 0;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getCustomerNum())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getArea())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getCustomerName())) {
            now++;
        }
        sum++;
        if (customerBaseInfoTO.getCustomerSex() != null) {
            now++;
        }
        sum++;
        if (customerBaseInfoTO.getCustomerType() != null) {
            now++;
        }
        sum++;
        if (customerBaseInfoTO.getCustomerStatus() != null) {
            now++;
        }
        sum++;
        if (customerBaseInfoTO.getRelation() != null && StringUtils.isNotBlank(String.valueOf(customerBaseInfoTO.getRelation()))) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getCustomerLevelName())) {
            now++;
        }
        sum++;
        if (customerBaseInfoTO.getOrigin() != null) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getIntroducer())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getCusEmail())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getTel())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getPhone())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWeChart())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getQq())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWorkProfession())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getOriganizion())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getOriganizationSize())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWorkPosition())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWorkLevel())) {
            now++;
        }
        sum++;
        if (customerBaseInfoTO.getWorkRight() != null) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getLifeArea())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getGrouthArea())) {
            now++;
        }
        sum++;
        if (StringUtils.isNotBlank(customerBaseInfoTO.getOldWorkPlace())) {
            now++;
        }
        sum++;

        double completeness = (now / sum) * 100;
        customerBaseInfoTO.setInfoComplet(new BigDecimal(completeness).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "%");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerBaseInfoBO addMarketCustomerInfo(@NotBlank String customerName, String origanizion) throws SerException {
        if (StringUtils.isNotBlank(customerName)) {
            return null;
        } else {
            /**
             * 生成客户编号
             */
            CustomerBaseInfoBO cBO = generateCustomerNum();

            CustomerBaseInfo cbaseInfo = new CustomerBaseInfo();
            cbaseInfo.setCustomerName(customerName);
            cbaseInfo.setOriganizion(origanizion);
            cbaseInfo.setCustomerNum(cBO.getCustomerNum());
            cbaseInfo.setCustomerSex(CustomerSex.NONE);
            cbaseInfo.setCreateTime(LocalDateTime.now());
            cbaseInfo.setModifyPersion(userAPI.currentUser().getUsername());

            super.save(cbaseInfo);
            return BeanTransform.copyProperties(cbaseInfo, CustomerBaseInfoBO.class);
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerBaseInfoBO getCustomerInfoByNum(String customerNum) throws SerException {
        CustomerBaseInfoDTO dto = new CustomerBaseInfoDTO();
        dto.getConditions().add(Restrict.eq("customerNum", customerNum));
        CustomerBaseInfo customerBaseInfo = super.findOne(dto);

        CustomerBaseInfoBO customerBaseInfoBO = BeanTransform.copyProperties(customerBaseInfo, CustomerBaseInfoBO.class);
        if (customerBaseInfoBO != null) {
            customerBaseInfoBO.setCustomerLevelBO(BeanTransform.copyProperties(
                    customerBaseInfo.getCustomerLevel(), CustomerLevelBO.class));
        }
        return customerBaseInfoBO;
    }

    @Override
    public List<String> getCustomerBaseInfoWorks() throws SerException {
        String[] fields = new String[]{"workProfession"};
        List<CustomerBaseInfoBO> customerBaseInfoBOS = super.findBySql("select workProfession  from customer_customerbaseinfo group by workProfession", CustomerBaseInfoBO.class, fields);

        List<String> customerNameList = customerBaseInfoBOS.stream().map(CustomerBaseInfoBO::getWorkProfession)
                .filter(name -> (name != null || !"".equals(name))).distinct().collect(Collectors.toList());


        return customerNameList;
    }

    @Override
    //chenjunhao
    public List<CustomerBaseInfoBO> findByOriganizion(String origanizion) throws SerException {
        CustomerBaseInfoDTO dto = new CustomerBaseInfoDTO();
        dto.getConditions().add(Restrict.eq("origanizion", origanizion));
        return BeanTransform.copyProperties(super.findByCis(dto), CustomerBaseInfoBO.class);
    }

    @Override
    public List<CustomerNameNumBO> findNameNum() throws SerException {
        List<CustomerBaseInfo> customerBaseInfos = super.findAll();
        return BeanTransform.copyProperties(customerBaseInfos, CustomerNameNumBO.class);
    }

    @Override
    public List<String> findBussType() throws SerException {
        List<CustomerBaseInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (CustomerBaseInfo model : list) {
            String businessType = model.getBusinessType();
            if (StringUtils.isNotBlank(model.getBusinessType())) {
                set.add(businessType);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAreaByBussType(String bussType) throws SerException {
        CustomerBaseInfoDTO customerBaseInfoDTO = new CustomerBaseInfoDTO();
        customerBaseInfoDTO.getConditions().add(Restrict.eq("businessType", bussType));
        List<CustomerBaseInfo> list = super.findByCis(customerBaseInfoDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (CustomerBaseInfo model : list) {
            String area = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<SummationBO> summaDay(String summDate) throws SerException {
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;

        return totalMethod(startDate, endDate);
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
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startDate = date[0];
        String endDate = date[1];
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        if (year == null || quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarterChange(year, quarter);
        return totalMethod(date[0], date[1]);
    }

    @Override
    public List<SummationBO> summaYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
        if (StringUtils.isBlank(endDate)) {
            endDate = LocalDate.now().toString();
        }
        List<SummationBO> summationBOList = new ArrayList<>();
        List<String> bussTypes = findBussType();
        if (bussTypes != null && bussTypes.size() > 0) {
            for (String bussType : bussTypes) {
                List<String> areas = findAreaByBussType(bussType);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        StringBuilder sql = new StringBuilder();
                        sql.append("SELECT * FROM ( ");
                        sql.append(" (SELECT count(*) as newCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate < '" + endDate + "' AND area ='" + area + "') a, ");
                        sql.append(" (SELECT count(*) as toVisitNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND recommendVisitTime < '" + endDate + "' AND area ='" + area + "' AND visitStatus = 0) b, ");
                        sql.append(" (SELECT count(*) as haveVisitNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND recommendVisitTime < '" + endDate + "' AND area ='" + area + "' AND visitStatus = 1) c, ");
                        sql.append(" (SELECT count(*) as notVisitNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND recommendVisitTime < '" + endDate + "' AND area ='" + area + "' AND visitStatus = 3) d, ");
                        sql.append(" (SELECT count(*) as unconnectedCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND area ='" + area + "' marketInfoNum is NULL) e, ");
                        sql.append(" (SELECT count(*) as associatedCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND area ='" + area + "' marketInfoNum is NOT NULL) f, ");
                        sql.append(" (SELECT count(*) as marketActivitNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate < '" + endDate + "' AND area ='" + area + "' AND proceedMarketTreat = 1) p, ");
                        sql.append(" (SELECT count(*) as custIntrodNewCustNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate < '" + endDate + "' AND area ='" + area + "' AND origin = 0) h, ");
                        sql.append(" (SELECT count(*) as marketEnNewCustNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate < '" + endDate + "' AND area ='" + area + "' AND origin = 1) i, ");
                        sql.append(" (SELECT count(*) as bussNegotiationNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate < '" + endDate + "' AND area ='" + area + "' AND origin = 2) j, ");
                        sql.append(" (SELECT count(*) as biddingNewCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate < '" + endDate + "' AND area ='" + area + "' AND origin = 3) k, ");
                        sql.append(" (SELECT count(*) as websiteNewCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate < '" + endDate + "' AND area ='" + area + "' AND origin = 4) l, ");
                        sql.append(" (SELECT count(*) as staffIntroNewCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate < '" + endDate + "' AND area ='" + area + "' AND origin = 5) m, ");
                        sql.append(" (SELECT count(*) as otherResouceNewCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate < '" + endDate + "' AND area ='" + area + "' AND origin = 6) n ");
                        sql.append(" )");
                        List<Object> objectList = super.findBySql(sql.toString());
                        Object[] objects = (Object[]) objectList.get(0);
                        Integer newCustomerNum = Integer.parseInt(String.valueOf(objects[0]));
                        Integer toVisitNum = Integer.parseInt(String.valueOf(objects[1]));
                        Integer haveVisitNum = Integer.parseInt(String.valueOf(objects[2]));
                        Integer notVisitNum = Integer.parseInt(String.valueOf(objects[3]));
                        Integer unconnectedCustomerNum = Integer.parseInt(String.valueOf(objects[4]));
                        Integer associatedCustomerNum = Integer.parseInt(String.valueOf(objects[5]));
                        Integer marketActivitNum = Integer.parseInt(String.valueOf(objects[6]));
                        Integer custIntrodNewCustNum = Integer.parseInt(String.valueOf(objects[7]));
                        Integer marketEnNewCustNum = Integer.parseInt(String.valueOf(objects[8]));
                        Integer bussNegotiationNum = Integer.parseInt(String.valueOf(objects[9]));
                        Integer biddingNewCustomerNum = Integer.parseInt(String.valueOf(objects[10]));
                        Integer websiteNewCustomerNum = Integer.parseInt(String.valueOf(objects[11]));
                        Integer staffIntroNewCustomerNum = Integer.parseInt(String.valueOf(objects[12]));
                        Integer otherResouceNewCustomerNum = Integer.parseInt(String.valueOf(objects[13]));
                        //TODO 由于市场招待还未改需求所有为做汇总数据字段为已进行市场招待的客户数量和未进行市场招待的客户数量,具体怎么获取该数据看需求excel
                        Integer haveMaketCustomerNum = 0;
                        Integer notMaketCustomerNum = 0;


                        SummationBO summationBO = new SummationBO();
                        summationBO.setBusinessType(bussType);
                        summationBO.setArea(area);
                        summationBO.setNewCustomerNum(newCustomerNum);
                        summationBO.setToVisitNum(toVisitNum);
                        summationBO.setHaveVisitNum(haveVisitNum);
                        summationBO.setNotVisitNum(notVisitNum);
                        summationBO.setUnconnectedCustomerNum(unconnectedCustomerNum);
                        summationBO.setAssociatedCustomerNum(associatedCustomerNum);
                        summationBO.setMarketActivitNum(marketActivitNum);
                        summationBO.setCustIntrodNewCustNum(custIntrodNewCustNum);
                        summationBO.setMarketEnNewCustNum(marketEnNewCustNum);
                        summationBO.setBussNegotiationNum(bussNegotiationNum);
                        summationBO.setBiddingNewCustomerNum(biddingNewCustomerNum);
                        summationBO.setWebsiteNewCustomerNum(websiteNewCustomerNum);
                        summationBO.setStaffIntroNewCustomerNum(staffIntroNewCustomerNum);
                        summationBO.setOtherResouceNewCustomerNum(otherResouceNewCustomerNum);
                        summationBO.setHaveMaketCustomerNum(haveMaketCustomerNum);
                        summationBO.setNotMaketCustomerNum(notMaketCustomerNum);
                        summationBOList.add(summationBO);
                    }
                }
            }
        }
        return summationBOList;
    }

    public String[] quarterChange(Integer year, Integer quarter) throws SerException {
        String startDate = LocalDate.now().toString();
        String endDate = LocalDate.now().toString();
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
                startDate = LocalDate.now().toString();
                endDate = LocalDate.now().toString();
                break;
        }
        return new String[]{startDate, endDate};
    }

    public List<SummationBO> totalMethod(String startDate, String endDate) throws SerException {
        List<SummationBO> summationBOList = new ArrayList<>();
        List<String> bussTypes = findBussType();
        if (bussTypes != null && bussTypes.size() > 0) {
            for (String bussType : bussTypes) {
                List<String> areas = findAreaByBussType(bussType);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        StringBuilder sql = new StringBuilder();
                        sql.append("SELECT * FROM ( ");
                        sql.append(" (SELECT count(*) as newCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "') a, ");
                        sql.append(" (SELECT count(*) as toVisitNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND recommendVisitTime BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND visitStatus = 0) b, ");
                        sql.append(" (SELECT count(*) as haveVisitNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND recommendVisitTime BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND visitStatus = 1) c, ");
                        sql.append(" (SELECT count(*) as notVisitNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND recommendVisitTime BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND visitStatus = 3) d, ");
                        sql.append(" (SELECT count(*) as unconnectedCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND marketInfoNum is NULL) e, ");
                        sql.append(" (SELECT count(*) as associatedCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND marketInfoNum is NOT NULL) f, ");
                        sql.append(" (SELECT count(*) as marketActivitNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND proceedMarketTreat = 1) p, ");
                        sql.append(" (SELECT count(*) as custIntrodNewCustNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 0) h, ");
                        sql.append(" (SELECT count(*) as marketEnNewCustNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 1) i, ");
                        sql.append(" (SELECT count(*) as bussNegotiationNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 2) j, ");
                        sql.append(" (SELECT count(*) as biddingNewCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 3) k, ");
                        sql.append(" (SELECT count(*) as websiteNewCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area  = '" + area + "' AND origin = 4) l, ");
                        sql.append(" (SELECT count(*) as staffIntroNewCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 5) m, ");
                        sql.append(" (SELECT count(*) as otherResouceNewCustomerNum FROM customer_customerbaseinfo WHERE businessType = '" + bussType + "' AND updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 6) n ");
                        sql.append(" )");
                        List<Object> objectList = super.findBySql(sql.toString());
                        Object[] objects = (Object[]) objectList.get(0);
                        Integer newCustomerNum = Integer.parseInt(String.valueOf(objects[0]));
                        Integer toVisitNum = Integer.parseInt(String.valueOf(objects[1]));
                        Integer haveVisitNum = Integer.parseInt(String.valueOf(objects[2]));
                        Integer notVisitNum = Integer.parseInt(String.valueOf(objects[3]));
                        Integer unconnectedCustomerNum = Integer.parseInt(String.valueOf(objects[4]));
                        Integer associatedCustomerNum = Integer.parseInt(String.valueOf(objects[5]));
                        Integer marketActivitNum = Integer.parseInt(String.valueOf(objects[6]));
                        Integer custIntrodNewCustNum = Integer.parseInt(String.valueOf(objects[7]));
                        Integer marketEnNewCustNum = Integer.parseInt(String.valueOf(objects[8]));
                        Integer bussNegotiationNum = Integer.parseInt(String.valueOf(objects[9]));
                        Integer biddingNewCustomerNum = Integer.parseInt(String.valueOf(objects[10]));
                        Integer websiteNewCustomerNum = Integer.parseInt(String.valueOf(objects[11]));
                        Integer staffIntroNewCustomerNum = Integer.parseInt(String.valueOf(objects[12]));
                        Integer otherResouceNewCustomerNum = Integer.parseInt(String.valueOf(objects[13]));
                        //TODO 由于市场招待还未改需求所有为做汇总数据字段为已进行市场招待的客户数量和未进行市场招待的客户数量,具体怎么获取该数据看需求excel
                        Integer haveMaketCustomerNum = 0;
                        Integer notMaketCustomerNum = 0;


                        SummationBO summationBO = new SummationBO();
                        summationBO.setBusinessType(bussType);
                        summationBO.setArea(area);
                        summationBO.setNewCustomerNum(newCustomerNum);
                        summationBO.setToVisitNum(toVisitNum);
                        summationBO.setHaveVisitNum(haveVisitNum);
                        summationBO.setNotVisitNum(notVisitNum);
                        summationBO.setUnconnectedCustomerNum(unconnectedCustomerNum);
                        summationBO.setAssociatedCustomerNum(associatedCustomerNum);
                        summationBO.setMarketActivitNum(marketActivitNum);
                        summationBO.setCustIntrodNewCustNum(custIntrodNewCustNum);
                        summationBO.setMarketEnNewCustNum(marketEnNewCustNum);
                        summationBO.setBussNegotiationNum(bussNegotiationNum);
                        summationBO.setBiddingNewCustomerNum(biddingNewCustomerNum);
                        summationBO.setWebsiteNewCustomerNum(websiteNewCustomerNum);
                        summationBO.setStaffIntroNewCustomerNum(staffIntroNewCustomerNum);
                        summationBO.setOtherResouceNewCustomerNum(otherResouceNewCustomerNum);
                        summationBO.setHaveMaketCustomerNum(haveMaketCustomerNum);
                        summationBO.setNotMaketCustomerNum(notMaketCustomerNum);
                        summationBOList.add(summationBO);
                    }
                }
            }
        }
        return summationBOList;
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<CustomerBaseInfo> baseInfoList = super.findAll();
        List<CustomerBaseInfoExport> customerBaseInfoExportList = new ArrayList<>();
        List<Integer> maxNum = new ArrayList<>();
        if (baseInfoList != null && baseInfoList.size() > 0) {
            for (CustomerBaseInfo customerBaseInfo : baseInfoList) {
                CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
                customerDetailDTO.getConditions().add(Restrict.eq("customerNum", customerBaseInfo.getCustomerNum()));
                CustomerDetail customerDetail = customerDetailSer.findOne(customerDetailDTO);
                CusFamilyMemberDTO cusFamilyMemberDTO = new CusFamilyMemberDTO();
                cusFamilyMemberDTO.getConditions().add(Restrict.eq("customerDetail.id", customerDetail.getId()));
                List<CusFamilyMember> cusFamilyMemberList = cusFamilyMemberSer.findByCis(cusFamilyMemberDTO);
                if (cusFamilyMemberList == null || cusFamilyMemberList.size() == 0) {
                    CustomerBaseInfoExport customerBaseInfoExport = BeanTransform.copyProperties(customerBaseInfo, CustomerBaseInfoExport.class, "proceedMarketTreat", "customerLevel");
                    customerBaseInfoExport.setProceedMarketTreat(boolByString(customerBaseInfo.getProceedMarketTreat()));
                    customerBaseInfoExport.setGradeName(customerBaseInfo.getCustomerLevel().getName());
                    customerBaseInfoExport.setRemark(customerBaseInfo.getCustomerLevel().getRemark());
                    BeanTransform.copyProperties(customerDetail, customerBaseInfoExport);
                    customerBaseInfoExportList.add(customerBaseInfoExport);
                }

                for (CusFamilyMember cusFamilyMember : cusFamilyMemberList) {
                    CustomerBaseInfoExport customerBaseInfoExport = BeanTransform.copyProperties(customerBaseInfo, CustomerBaseInfoExport.class, "proceedMarketTreat", "customerLevel");
                    customerBaseInfoExport.setProceedMarketTreat(boolByString(customerBaseInfo.getProceedMarketTreat()));
                    customerBaseInfoExport.setGradeName(customerBaseInfo.getCustomerLevel().getName());
                    customerBaseInfoExport.setRemark(customerBaseInfo.getCustomerLevel().getRemark());
                    BeanTransform.copyProperties(customerDetail, customerBaseInfoExport);
                    BeanTransform.copyProperties(cusFamilyMember, customerBaseInfoExport);
                    customerBaseInfoExportList.add(customerBaseInfoExport);
                }
                maxNum.add(cusFamilyMemberList.size());
            }
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(customerBaseInfoExportList, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            int rowSize = baseInfoList.size();
            List<Field> fields = ClazzUtils.getFields(CustomerBaseInfoExport.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
            int index = 1;
            for (int j = 0; j < rowSize; j++) {
                int mergeRowCount = maxNum.get(j);
                if (mergeRowCount > 1) {
                    int firstRow = index;
                    int lastRow = 0;
                    lastRow = firstRow + mergeRowCount - 1;
                    for (int i = 0; i < 63; i++) {
                        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                    }
                    index = lastRow + 1;
                } else {
                    index++;
                }
            }

            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<CustomerBaseInfoExportTemple> customerBaseInfoExportTemples = new ArrayList<>();
        CustomerBaseInfoExportTemple customerBaseInfoExportTemple = new CustomerBaseInfoExportTemple();
        customerBaseInfoExportTemple.setCustomerNum("CS0002");
        customerBaseInfoExportTemple.setCustomerName("王五");
        customerBaseInfoExportTemple.setArea("广州市");
        customerBaseInfoExportTemple.setCustomerSex("男");
        customerBaseInfoExportTemple.setCustomerType("VIP客户");
        customerBaseInfoExportTemple.setCustomerStatus("已完成项目客户");
        customerBaseInfoExportTemple.setRelation(22d);
        customerBaseInfoExportTemple.setCustomerLevelName("一星");
        customerBaseInfoExportTemple.setOrigin("市场招待");
        customerBaseInfoExportTemple.setIntroducer("李四");
        customerBaseInfoExportTemple.setCusEmail("wangwu_aj@163.com");
        customerBaseInfoExportTemple.setTel("13698569546");
        customerBaseInfoExportTemple.setPhone("669545");
        customerBaseInfoExportTemple.setWeChart("69542215");
        customerBaseInfoExportTemple.setQq("5465226");
        customerBaseInfoExportTemple.setWorkProfession("金融");
        customerBaseInfoExportTemple.setOriganizion("text");
        customerBaseInfoExportTemple.setOriganizationSize("17x");
        customerBaseInfoExportTemple.setWorkPosition("测试");
        customerBaseInfoExportTemple.setWorkLevel("12");
        customerBaseInfoExportTemple.setWorkRight("54");
        customerBaseInfoExportTemple.setLifeArea("广州");
        customerBaseInfoExportTemple.setGrouthArea("广州");
        customerBaseInfoExportTemple.setOldWorkPlace("广州");
        customerBaseInfoExportTemple.setMarketReceptTime("2017/12/12 12:12:12");
        customerBaseInfoExportTemple.setInfoComplet("65");
        customerBaseInfoExportTemple.setMarketInfoNum("1001,1002,1003");
        customerBaseInfoExportTemple.setProvinces("广东省");
        customerBaseInfoExportTemple.setContactPhace("了解");
        customerBaseInfoExportTemple.setTimeliness("十分紧急");
        customerBaseInfoExportTemple.setIntimacy("亲密");
        customerBaseInfoExportTemple.setDifficultyLevel("一般");
        customerBaseInfoExportTemple.setVisitStatus("待拜访");
        customerBaseInfoExportTemple.setBusinessType("通信");
        customerBaseInfoExportTemple.setBusinessWay("督导");
        customerBaseInfoExportTemple.setRelatedProject("八大平台");
        customerBaseInfoExportTemple.setCooProjectAreaDistri("text");
        customerBaseInfoExportTemple.setBeforeCooCompanise("text");
        customerBaseInfoExportTemple.setCurrentBusiness("text");
        customerBaseInfoExportTemple.setFutureDevelopment("text");
        customerBaseInfoExportTemple.setCustomerSatisfation("一般");
        customerBaseInfoExportTemple.setSatisfationWithCompany("一般");
        customerBaseInfoExportTemple.setPointsMatters("text");
        customerBaseInfoExportTemple.setCustomerReten(10d);
        customerBaseInfoExportTemple.setBusinessActivityRecord("text");
        customerBaseInfoExportTemple.setMarketReceptionRecord("text");
        customerBaseInfoExportTemple.setMarketInfoRecord("text");
        customerBaseInfoExportTemple.setProceedMarketTreat("text");
        customerBaseInfoExportTemple.setAge(18);
        customerBaseInfoExportTemple.setBirthday("2017/12/12");
        customerBaseInfoExportTemple.setWorkExperience("text");
        customerBaseInfoExportTemple.setGraduatedSchool("text");
        customerBaseInfoExportTemple.setStudyExperience("text");
        customerBaseInfoExportTemple.setLove("text");
        customerBaseInfoExportTemple.setCharacterEvaluation("text");
        customerBaseInfoExportTemple.setTitle("父亲");
        customerBaseInfoExportTemple.setName("赵六");
        customerBaseInfoExportTemple.setRelationWay("15695642365");
        customerBaseInfoExportTemple.setCharactLove("想干嘛就干嘛");
        customerBaseInfoExportTemple.setWorkPlace("text");
        customerBaseInfoExportTemple.setJobPost("text");
        customerBaseInfoExportTemples.add(customerBaseInfoExportTemple);

        CustomerBaseInfoExportTemple customerBaseInfoExportTemple2 = new CustomerBaseInfoExportTemple();
        customerBaseInfoExportTemple2.setCustomerNum("CS0002");
        customerBaseInfoExportTemple2.setCustomerName("王五");
        customerBaseInfoExportTemple2.setArea("广州市");
        customerBaseInfoExportTemple2.setCustomerSex("男");
        customerBaseInfoExportTemple2.setCustomerType("VIP客户");
        customerBaseInfoExportTemple2.setCustomerStatus("已完成项目客户");
        customerBaseInfoExportTemple2.setRelation(22d);
        customerBaseInfoExportTemple2.setCustomerLevelName("一星");
        customerBaseInfoExportTemple2.setOrigin("市场招待");
        customerBaseInfoExportTemple2.setIntroducer("李四");
        customerBaseInfoExportTemple2.setCusEmail("wangwu_aj@163.com");
        customerBaseInfoExportTemple2.setTel("13698569546");
        customerBaseInfoExportTemple2.setPhone("669545");
        customerBaseInfoExportTemple2.setWeChart("69542215");
        customerBaseInfoExportTemple2.setQq("5465226");
        customerBaseInfoExportTemple2.setWorkProfession("金融");
        customerBaseInfoExportTemple2.setOriganizion("text");
        customerBaseInfoExportTemple2.setOriganizationSize("17x");
        customerBaseInfoExportTemple2.setWorkPosition("测试");
        customerBaseInfoExportTemple2.setWorkLevel("12");
        customerBaseInfoExportTemple2.setWorkRight("54");
        customerBaseInfoExportTemple2.setLifeArea("广州");
        customerBaseInfoExportTemple2.setGrouthArea("广州");
        customerBaseInfoExportTemple2.setOldWorkPlace("广州");
        customerBaseInfoExportTemple2.setMarketReceptTime("2017/12/12 12:12:12");
        customerBaseInfoExportTemple2.setInfoComplet("65");
        customerBaseInfoExportTemple2.setMarketInfoNum("1001,1002,1003");
        customerBaseInfoExportTemple2.setProvinces("广东省");
        customerBaseInfoExportTemple2.setContactPhace("了解");
        customerBaseInfoExportTemple2.setTimeliness("十分紧急");
        customerBaseInfoExportTemple2.setIntimacy("亲密");
        customerBaseInfoExportTemple2.setDifficultyLevel("一般");
        customerBaseInfoExportTemple2.setVisitStatus("待拜访");
        customerBaseInfoExportTemple2.setBusinessType("通信");
        customerBaseInfoExportTemple2.setBusinessWay("督导");
        customerBaseInfoExportTemple2.setRelatedProject("八大平台");
        customerBaseInfoExportTemple2.setCooProjectAreaDistri("text");
        customerBaseInfoExportTemple2.setBeforeCooCompanise("text");
        customerBaseInfoExportTemple2.setCurrentBusiness("text");
        customerBaseInfoExportTemple2.setFutureDevelopment("text");
        customerBaseInfoExportTemple2.setCustomerSatisfation("一般");
        customerBaseInfoExportTemple2.setSatisfationWithCompany("一般");
        customerBaseInfoExportTemple2.setPointsMatters("text");
        customerBaseInfoExportTemple2.setCustomerReten(10d);
        customerBaseInfoExportTemple2.setBusinessActivityRecord("text");
        customerBaseInfoExportTemple2.setMarketReceptionRecord("text");
        customerBaseInfoExportTemple2.setMarketInfoRecord("text");
        customerBaseInfoExportTemple2.setProceedMarketTreat("text");
        customerBaseInfoExportTemple2.setAge(18);
        customerBaseInfoExportTemple2.setBirthday("2017/12/12");
        customerBaseInfoExportTemple2.setWorkExperience("text");
        customerBaseInfoExportTemple2.setGraduatedSchool("text");
        customerBaseInfoExportTemple2.setStudyExperience("text");
        customerBaseInfoExportTemple2.setLove("text");
        customerBaseInfoExportTemple2.setCharacterEvaluation("text");
        customerBaseInfoExportTemple2.setTitle("母亲");
        customerBaseInfoExportTemple2.setName("赵六");
        customerBaseInfoExportTemple2.setRelationWay("15695642365");
        customerBaseInfoExportTemple2.setCharactLove("想干嘛就干嘛");
        customerBaseInfoExportTemple2.setWorkPlace("text");
        customerBaseInfoExportTemple2.setJobPost("text");
        customerBaseInfoExportTemples.add(customerBaseInfoExportTemple2);

        CustomerBaseInfoExportTemple customerBaseInfoExportTemple3 = new CustomerBaseInfoExportTemple();
        customerBaseInfoExportTemple3.setCustomerNum("CS0002");
        customerBaseInfoExportTemple3.setCustomerName("王五");
        customerBaseInfoExportTemple3.setArea("广州市");
        customerBaseInfoExportTemple3.setCustomerSex("男");
        customerBaseInfoExportTemple3.setCustomerType("VIP客户");
        customerBaseInfoExportTemple3.setCustomerStatus("已完成项目客户");
        customerBaseInfoExportTemple3.setRelation(22d);
        customerBaseInfoExportTemple3.setCustomerLevelName("一星");
        customerBaseInfoExportTemple3.setOrigin("市场招待");
        customerBaseInfoExportTemple3.setIntroducer("李四");
        customerBaseInfoExportTemple3.setCusEmail("wangwu_aj@163.com");
        customerBaseInfoExportTemple3.setTel("13698569546");
        customerBaseInfoExportTemple3.setPhone("669545");
        customerBaseInfoExportTemple3.setWeChart("69542215");
        customerBaseInfoExportTemple3.setQq("5465226");
        customerBaseInfoExportTemple3.setWorkProfession("金融");
        customerBaseInfoExportTemple3.setOriganizion("text");
        customerBaseInfoExportTemple3.setOriganizationSize("17x");
        customerBaseInfoExportTemple3.setWorkPosition("测试");
        customerBaseInfoExportTemple3.setWorkLevel("12");
        customerBaseInfoExportTemple3.setWorkRight("54");
        customerBaseInfoExportTemple3.setLifeArea("广州");
        customerBaseInfoExportTemple3.setGrouthArea("广州");
        customerBaseInfoExportTemple3.setOldWorkPlace("广州");
        customerBaseInfoExportTemple3.setMarketReceptTime("2017/12/12 12:12:12");
        customerBaseInfoExportTemple3.setInfoComplet("65");
        customerBaseInfoExportTemple3.setMarketInfoNum("1001,1002,1003");
        customerBaseInfoExportTemple3.setProvinces("广东省");
        customerBaseInfoExportTemple3.setContactPhace("了解");
        customerBaseInfoExportTemple3.setTimeliness("十分紧急");
        customerBaseInfoExportTemple3.setIntimacy("亲密");
        customerBaseInfoExportTemple3.setDifficultyLevel("一般");
        customerBaseInfoExportTemple3.setVisitStatus("待拜访");
        customerBaseInfoExportTemple3.setBusinessType("通信");
        customerBaseInfoExportTemple3.setBusinessWay("督导");
        customerBaseInfoExportTemple3.setRelatedProject("八大平台");
        customerBaseInfoExportTemple3.setCooProjectAreaDistri("text");
        customerBaseInfoExportTemple3.setBeforeCooCompanise("text");
        customerBaseInfoExportTemple3.setCurrentBusiness("text");
        customerBaseInfoExportTemple3.setFutureDevelopment("text");
        customerBaseInfoExportTemple3.setCustomerSatisfation("一般");
        customerBaseInfoExportTemple3.setSatisfationWithCompany("一般");
        customerBaseInfoExportTemple3.setPointsMatters("text");
        customerBaseInfoExportTemple3.setCustomerReten(10d);
        customerBaseInfoExportTemple3.setBusinessActivityRecord("text");
        customerBaseInfoExportTemple3.setMarketReceptionRecord("text");
        customerBaseInfoExportTemple3.setMarketInfoRecord("text");
        customerBaseInfoExportTemple3.setProceedMarketTreat("text");
        customerBaseInfoExportTemple3.setAge(18);
        customerBaseInfoExportTemple3.setBirthday("2017/12/12");
        customerBaseInfoExportTemple3.setWorkExperience("text");
        customerBaseInfoExportTemple3.setGraduatedSchool("text");
        customerBaseInfoExportTemple3.setStudyExperience("text");
        customerBaseInfoExportTemple3.setLove("text");
        customerBaseInfoExportTemple3.setCharacterEvaluation("text");
        customerBaseInfoExportTemple3.setTitle("儿子");
        customerBaseInfoExportTemple3.setName("赵六");
        customerBaseInfoExportTemple3.setRelationWay("15695642365");
        customerBaseInfoExportTemple3.setCharactLove("想干嘛就干嘛");
        customerBaseInfoExportTemple3.setWorkPlace("text");
        customerBaseInfoExportTemple3.setJobPost("text");
        customerBaseInfoExportTemples.add(customerBaseInfoExportTemple3);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(customerBaseInfoExportTemples, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
//            int rowSize = baseInfoList.size();
            List<Field> fields = ClazzUtils.getFields(CustomerBaseInfoExport.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
            int index = 1;
            for (int j = 0; j < 1; j++) {
//                int mergeRowCount = maxNum.get(j);
//                if(mergeRowCount>1){
//                    int firstRow = index;
//                    int lastRow =0;
//                    lastRow = firstRow+mergeRowCount-1;
                for (int i = 0; i < 54; i++) {
                    sheet.addMergedRegion(new CellRangeAddress(1, 3, i, i));
                }
//                    index =lastRow+1;
//                }else{
//                    index++;
//                }
            }

            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    public String boolByString(Boolean name) throws SerException {
        String type = "";
        if (name != null) {
            if (name) {
                type = "是";
            } else {
                type = "否";
            }
        }
        return type;
    }

    public List<String> findArea() throws SerException {
        List<CustomerBaseInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(CustomerBaseInfo::getArea).distinct().collect(Collectors.toList());
    }

    @Override
    public OptionBO figureShowDay(String summDate) throws SerException {
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;
        String text_1 = "客户信息日统计(" + startDate + ")";
        return totalFigureMenthod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "客户信息周统计(" + startDate + "--" + endDate + ")";
        return totalFigureMenthod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "客户信息月统计(" + startDate + "--" + endDate + ")";
        return totalFigureMenthod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        if (year == null || quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarterChange(year, quarter);
        String text_1 = "客户信息季度统计(" + date[0] + "--" + date[1] + ")";
        return totalFigureMenthod(date[0], date[1], text_1);
    }

    @Override
    public OptionBO figureShowYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "客户信息年度统计(" + startDate + "--" + endDate + ")";
        return totalFigureMenthod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        if (StringUtils.isBlank(endDate)) {
            endDate = LocalDate.now().toString();
        }
        List<String> areas = findArea();
        List<FigureSummationBO> figureSummationBOList = new ArrayList<>();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM ( ");
                sql.append(" (SELECT count(*) as newCustomerNum FROM customer_customerbaseinfo WHERE updateDate < '" + endDate + "' AND area = '" + area + "') a, ");
                sql.append(" (SELECT count(*) as toVisitNum FROM customer_customerbaseinfo WHERE recommendVisitTime < '" + endDate + "' AND area = '" + area + "' AND visitStatus = 0) b, ");
                sql.append(" (SELECT count(*) as haveVisitNum FROM customer_customerbaseinfo WHERE recommendVisitTime < '" + endDate + "' AND area = '" + area + "' AND visitStatus = 1) c, ");
                sql.append(" (SELECT count(*) as notVisitNum FROM customer_customerbaseinfo WHERE recommendVisitTime < '" + endDate + "' AND area = '" + area + "' AND visitStatus = 3) d, ");
                sql.append(" (SELECT count(*) as unconnectedCustomerNum FROM customer_customerbaseinfo WHERE area = '" + area + "' AND marketInfoNum is NULL) e, ");
                sql.append(" (SELECT count(*) as associatedCustomerNum FROM customer_customerbaseinfo WHERE area = '" + area + "' AND marketInfoNum is NOT NULL) f, ");
                sql.append(" (SELECT count(*) as marketActivitNum FROM customer_customerbaseinfo WHERE updateDate < '" + endDate + "' AND area = '" + area + "' AND proceedMarketTreat = 1) p, ");
                sql.append(" (SELECT count(*) as custIntrodNewCustNum FROM customer_customerbaseinfo WHERE updateDate < '" + endDate + "' AND area = '" + area + "' AND origin = 0) h, ");
                sql.append(" (SELECT count(*) as marketEnNewCustNum FROM customer_customerbaseinfo WHERE updateDate < '" + endDate + "' AND area = '" + area + "' AND origin = 1) i, ");
                sql.append(" (SELECT count(*) as bussNegotiationNum FROM customer_customerbaseinfo WHERE updateDate < '" + endDate + "' AND area = '" + area + "' AND origin = 2) j, ");
                sql.append(" (SELECT count(*) as biddingNewCustomerNum FROM customer_customerbaseinfo WHERE updateDate < '" + endDate + "' AND area = '" + area + "' AND origin = 3) k, ");
                sql.append(" (SELECT count(*) as websiteNewCustomerNum FROM customer_customerbaseinfo WHERE updateDate < '" + endDate + "' AND area  = '" + area + "' AND origin = 4) l, ");
                sql.append(" (SELECT count(*) as staffIntroNewCustomerNum FROM customer_customerbaseinfo WHERE updateDate < '" + endDate + "' AND area = '" + area + "' AND origin = 5) m, ");
                sql.append(" (SELECT count(*) as otherResouceNewCustomerNum FROM customer_customerbaseinfo WHERE updateDate < '" + endDate + "' AND area = '" + area + "' AND origin = 6) n ");
                sql.append(" )");
                List<Object> objectList = super.findBySql(sql.toString());
                Object[] objects = (Object[]) objectList.get(0);
                Integer newCustomerNum = Integer.parseInt(String.valueOf(objects[0]));
                Integer toVisitNum = Integer.parseInt(String.valueOf(objects[1]));
                Integer haveVisitNum = Integer.parseInt(String.valueOf(objects[2]));
                Integer notVisitNum = Integer.parseInt(String.valueOf(objects[3]));
                Integer unconnectedCustomerNum = Integer.parseInt(String.valueOf(objects[4]));
                Integer associatedCustomerNum = Integer.parseInt(String.valueOf(objects[5]));
                Integer marketActivitNum = Integer.parseInt(String.valueOf(objects[6]));
                Integer custIntrodNewCustNum = Integer.parseInt(String.valueOf(objects[7]));
                Integer marketEnNewCustNum = Integer.parseInt(String.valueOf(objects[8]));
                Integer bussNegotiationNum = Integer.parseInt(String.valueOf(objects[9]));
                Integer biddingNewCustomerNum = Integer.parseInt(String.valueOf(objects[10]));
                Integer websiteNewCustomerNum = Integer.parseInt(String.valueOf(objects[11]));
                Integer staffIntroNewCustomerNum = Integer.parseInt(String.valueOf(objects[12]));
                Integer otherResouceNewCustomerNum = Integer.parseInt(String.valueOf(objects[13]));
                //TODO 由于市场招待还未改需求所有为做汇总数据字段为已进行市场招待的客户数量和未进行市场招待的客户数量,具体怎么获取该数据看需求excel
                Integer haveMaketCustomerNum = 0;
                Integer notMaketCustomerNum = 0;

                FigureSummationBO figureSummationBO = new FigureSummationBO();
                figureSummationBO.setArea(area);
                figureSummationBO.setNewCustomerNum(newCustomerNum);
                figureSummationBO.setToVisitNum(toVisitNum);
                figureSummationBO.setHaveVisitNum(haveVisitNum);
                figureSummationBO.setNotVisitNum(notVisitNum);
                figureSummationBO.setUnconnectedCustomerNum(unconnectedCustomerNum);
                figureSummationBO.setAssociatedCustomerNum(associatedCustomerNum);
                figureSummationBO.setMarketActivitNum(marketActivitNum);
                figureSummationBO.setCustIntrodNewCustNum(custIntrodNewCustNum);
                figureSummationBO.setMarketEnNewCustNum(marketEnNewCustNum);
                figureSummationBO.setBussNegotiationNum(bussNegotiationNum);
                figureSummationBO.setBiddingNewCustomerNum(biddingNewCustomerNum);
                figureSummationBO.setWebsiteNewCustomerNum(websiteNewCustomerNum);
                figureSummationBO.setStaffIntroNewCustomerNum(staffIntroNewCustomerNum);
                figureSummationBO.setOtherResouceNewCustomerNum(otherResouceNewCustomerNum);
                figureSummationBO.setHaveMaketCustomerNum(haveMaketCustomerNum);
                figureSummationBO.setNotMaketCustomerNum(notMaketCustomerNum);
                figureSummationBOList.add(figureSummationBO);

            }
        }
        String text_1 = "客户信息统计情况(累计)";
        return figureShow(figureSummationBOList, text_1);
    }

    //图形展示总方法
    public OptionBO totalFigureMenthod(String startDate, String endDate, String text_1) throws SerException {
        List<String> areas = findArea();
        List<FigureSummationBO> figureSummationBOList = new ArrayList<>();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM ( ");
                sql.append(" (SELECT count(*) as newCustomerNum FROM customer_customerbaseinfo WHERE updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "') a, ");
                sql.append(" (SELECT count(*) as toVisitNum FROM customer_customerbaseinfo WHERE recommendVisitTime BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND visitStatus = 0) b, ");
                sql.append(" (SELECT count(*) as haveVisitNum FROM customer_customerbaseinfo WHERE recommendVisitTime BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND visitStatus = 1) c, ");
                sql.append(" (SELECT count(*) as notVisitNum FROM customer_customerbaseinfo WHERE recommendVisitTime BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND visitStatus = 3) d, ");
                sql.append(" (SELECT count(*) as unconnectedCustomerNum FROM customer_customerbaseinfo WHERE area = '" + area + "' AND marketInfoNum is NULL) e, ");
                sql.append(" (SELECT count(*) as associatedCustomerNum FROM customer_customerbaseinfo WHERE area = '" + area + "' AND marketInfoNum is NOT NULL) f, ");
                sql.append(" (SELECT count(*) as marketActivitNum FROM customer_customerbaseinfo WHERE updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND proceedMarketTreat = 1) p, ");
                sql.append(" (SELECT count(*) as custIntrodNewCustNum FROM customer_customerbaseinfo WHERE updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 0) h, ");
                sql.append(" (SELECT count(*) as marketEnNewCustNum FROM customer_customerbaseinfo WHERE updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 1) i, ");
                sql.append(" (SELECT count(*) as bussNegotiationNum FROM customer_customerbaseinfo WHERE updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 2) j, ");
                sql.append(" (SELECT count(*) as biddingNewCustomerNum FROM customer_customerbaseinfo WHERE updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 3) k, ");
                sql.append(" (SELECT count(*) as websiteNewCustomerNum FROM customer_customerbaseinfo WHERE updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area  = '" + area + "' AND origin = 4) l, ");
                sql.append(" (SELECT count(*) as staffIntroNewCustomerNum FROM customer_customerbaseinfo WHERE updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 5) m, ");
                sql.append(" (SELECT count(*) as otherResouceNewCustomerNum FROM customer_customerbaseinfo WHERE updateDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND area = '" + area + "' AND origin = 6) n ");
                sql.append(" )");
                List<Object> objectList = super.findBySql(sql.toString());
                Object[] objects = (Object[]) objectList.get(0);
                Integer newCustomerNum = Integer.parseInt(String.valueOf(objects[0]));
                Integer toVisitNum = Integer.parseInt(String.valueOf(objects[1]));
                Integer haveVisitNum = Integer.parseInt(String.valueOf(objects[2]));
                Integer notVisitNum = Integer.parseInt(String.valueOf(objects[3]));
                Integer unconnectedCustomerNum = Integer.parseInt(String.valueOf(objects[4]));
                Integer associatedCustomerNum = Integer.parseInt(String.valueOf(objects[5]));
                Integer marketActivitNum = Integer.parseInt(String.valueOf(objects[6]));
                Integer custIntrodNewCustNum = Integer.parseInt(String.valueOf(objects[7]));
                Integer marketEnNewCustNum = Integer.parseInt(String.valueOf(objects[8]));
                Integer bussNegotiationNum = Integer.parseInt(String.valueOf(objects[9]));
                Integer biddingNewCustomerNum = Integer.parseInt(String.valueOf(objects[10]));
                Integer websiteNewCustomerNum = Integer.parseInt(String.valueOf(objects[11]));
                Integer staffIntroNewCustomerNum = Integer.parseInt(String.valueOf(objects[12]));
                Integer otherResouceNewCustomerNum = Integer.parseInt(String.valueOf(objects[13]));
                //TODO 由于市场招待还未改需求所有为做汇总数据字段为已进行市场招待的客户数量和未进行市场招待的客户数量,具体怎么获取该数据看需求excel
                Integer haveMaketCustomerNum = 0;
                Integer notMaketCustomerNum = 0;

                FigureSummationBO figureSummationBO = new FigureSummationBO();
                figureSummationBO.setArea(area);
                figureSummationBO.setNewCustomerNum(newCustomerNum);
                figureSummationBO.setToVisitNum(toVisitNum);
                figureSummationBO.setHaveVisitNum(haveVisitNum);
                figureSummationBO.setNotVisitNum(notVisitNum);
                figureSummationBO.setUnconnectedCustomerNum(unconnectedCustomerNum);
                figureSummationBO.setAssociatedCustomerNum(associatedCustomerNum);
                figureSummationBO.setMarketActivitNum(marketActivitNum);
                figureSummationBO.setCustIntrodNewCustNum(custIntrodNewCustNum);
                figureSummationBO.setMarketEnNewCustNum(marketEnNewCustNum);
                figureSummationBO.setBussNegotiationNum(bussNegotiationNum);
                figureSummationBO.setBiddingNewCustomerNum(biddingNewCustomerNum);
                figureSummationBO.setWebsiteNewCustomerNum(websiteNewCustomerNum);
                figureSummationBO.setStaffIntroNewCustomerNum(staffIntroNewCustomerNum);
                figureSummationBO.setOtherResouceNewCustomerNum(otherResouceNewCustomerNum);
                figureSummationBO.setHaveMaketCustomerNum(haveMaketCustomerNum);
                figureSummationBO.setNotMaketCustomerNum(notMaketCustomerNum);
                figureSummationBOList.add(figureSummationBO);

            }
        }
        return figureShow(figureSummationBOList, text_1);
    }

    public OptionBO figureShow(List<FigureSummationBO> figureSummationBOList, String text_1) throws SerException {
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"新增客户数量", "待拜访",
                "已拜访", "未拜访", "与市场无关联的客户数量",
                "与市场信息关联的客户数量", "需进行市场活动客户数量", "已进行市场招待的客户数量", "未进行市场招待的客户数量", "客户介绍新增客户数量", "市场招待新增客户数量",
                "商务洽谈新增客户数量", "招投标新增客户数量", "网站新增客户数量", "员工介绍新增客户数量", "其他来源新增客户数量"};
        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();//1

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        //        tooltipBO.setTrigger("axis");

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (figureSummationBOList != null && figureSummationBOList.size() > 0) {
            List<Integer> newCustomerNums = new ArrayList<>();
            List<Integer> toVisitNums = new ArrayList<>();
            List<Integer> haveVisitNums = new ArrayList<>();
            List<Integer> notVisitNums = new ArrayList<>();
            List<Integer> unconnectedCustomerNums = new ArrayList<>();
            List<Integer> associatedCustomerNums = new ArrayList<>();
            List<Integer> marketActivitNums = new ArrayList<>();
            List<Integer> haveMaketCustomerNums = new ArrayList<>();
            List<Integer> notMaketCustomerNums = new ArrayList<>();
            List<Integer> custIntrodNewCustNums = new ArrayList<>();
            List<Integer> marketEnNewCustNums = new ArrayList<>();
            List<Integer> bussNegotiationNums = new ArrayList<>();
            List<Integer> biddingNewCustomerNums = new ArrayList<>();
            List<Integer> websiteNewCustomerNums = new ArrayList<>();
            List<Integer> staffIntroNewCustomerNums = new ArrayList<>();
            List<Integer> otherResouceNewCustomerNums = new ArrayList<>();
            for (FigureSummationBO figureSummationBO : figureSummationBOList) {
                text_list_3.add(figureSummationBO.getArea());

                //柱状图数据
                newCustomerNums.add(figureSummationBO.getNewCustomerNum());
                toVisitNums.add(figureSummationBO.getToVisitNum());
                haveVisitNums.add(figureSummationBO.getHaveVisitNum());
                notVisitNums.add(figureSummationBO.getNotVisitNum());
                unconnectedCustomerNums.add(figureSummationBO.getUnconnectedCustomerNum());
                associatedCustomerNums.add(figureSummationBO.getAssociatedCustomerNum());
                marketActivitNums.add(figureSummationBO.getMarketActivitNum());
                haveMaketCustomerNums.add(figureSummationBO.getHaveMaketCustomerNum());
                notMaketCustomerNums.add(figureSummationBO.getNotMaketCustomerNum());
                custIntrodNewCustNums.add(figureSummationBO.getCustIntrodNewCustNum());
                marketEnNewCustNums.add(figureSummationBO.getMarketEnNewCustNum());
                bussNegotiationNums.add(figureSummationBO.getBussNegotiationNum());
                biddingNewCustomerNums.add(figureSummationBO.getBiddingNewCustomerNum());
                websiteNewCustomerNums.add(figureSummationBO.getWebsiteNewCustomerNum());
                staffIntroNewCustomerNums.add(figureSummationBO.getStaffIntroNewCustomerNum());
                otherResouceNewCustomerNums.add(figureSummationBO.getOtherResouceNewCustomerNum());
            }
            String[] dataNames = new String[]{"新增客户数量", "待拜访",
                    "已拜访", "未拜访", "与市场无关联的客户数量",
                    "与市场信息关联的客户数量", "需进行市场活动客户数量", "已进行市场招待的客户数量", "未进行市场招待的客户数量", "客户介绍新增客户数量", "市场招待新增客户数量",
                    "商务洽谈新增客户数量", "招投标新增客户数量", "网站新增客户数量", "员工介绍新增客户数量", "其他来源新增客户数量"};
            List<List<Integer>> datas = new ArrayList<>();
            datas.add(newCustomerNums);
            datas.add(toVisitNums);
            datas.add(haveVisitNums);
            datas.add(notVisitNums);
            datas.add(unconnectedCustomerNums);
            datas.add(associatedCustomerNums);
            datas.add(marketActivitNums);
            datas.add(haveMaketCustomerNums);
            datas.add(notMaketCustomerNums);
            datas.add(custIntrodNewCustNums);
            datas.add(marketEnNewCustNums);
            datas.add(bussNegotiationNums);
            datas.add(biddingNewCustomerNums);
            datas.add(websiteNewCustomerNums);
            datas.add(staffIntroNewCustomerNums);
            datas.add(otherResouceNewCustomerNums);
            for (int i = 0; i < datas.size(); i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(dataNames[i]);
                seriesBO.setType("bar");
                Integer[] nums = new Integer[]{datas.get(i).size()};
                nums = datas.get(i).toArray(nums);
                seriesBO.setData(nums);
                seriesBOList.add(seriesBO);
            }

        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    @Override
    public PieOptionBO areaPieShow() throws SerException {
        List<String> areas = findArea();

        List<DataBO> dataBOList = new ArrayList<>();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {
                DataBO dataBO = new DataBO();
                String sql = "SELECT count(*) FROM customer_customerbaseinfo WHERE area = '" + area + "'";
                List<Object> objectList = super.findBySql(sql);
                dataBO.setName(area);
                dataBO.setValue(Integer.parseInt(String.valueOf(objectList.get(0))));
                dataBOList.add(dataBO);
            }
        }
        String[] text_area = new String[areas.size()];
        text_area = areas.toArray(text_area);
        DataBO[] dataBOS = new DataBO[dataBOList.size()];
        dataBOS = dataBOList.toArray(dataBOS);
        TitleBO titleBO = new TitleBO();
        titleBO.setText("客户地区分布图");
        TooltipBO tooltipBO = new TooltipBO();
        LegendBO legendBO = new LegendBO();
        legendBO.setData(text_area);
        PieSeriesBO pieSeriesBO = new PieSeriesBO();
        pieSeriesBO.setName("客户地区分布情况");
        pieSeriesBO.setType("pie");
        pieSeriesBO.setData(dataBOS);

        PieOptionBO pieOptionBO = new PieOptionBO();
        pieOptionBO.setTitle(titleBO);
        pieOptionBO.setTooltip(tooltipBO);
        pieOptionBO.setLegend(legendBO);
        pieOptionBO.setSeries(pieSeriesBO);

        return pieOptionBO;
    }

    public List<String> findBussTypeByArea(String area) throws SerException {
        CustomerBaseInfoDTO customerBaseInfoDTO = new CustomerBaseInfoDTO();
        customerBaseInfoDTO.getConditions().add(Restrict.eq("area", area));
        List<CustomerBaseInfo> customerBaseInfoList = super.findByCis(customerBaseInfoDTO);
        if (CollectionUtils.isEmpty(customerBaseInfoList)) {
            return Collections.emptyList();
        }
        return customerBaseInfoList.stream().map(CustomerBaseInfo::getBusinessType).distinct().collect(Collectors.toList());
    }

    @Override
    public PieOptionBO areaBussTypePieShow(String area) throws SerException {
        List<String> areas = findArea();

        List<DataBO> dataBOList = new ArrayList<>();
        List<String> types = new ArrayList<>();
        if (areas != null && areas.size() > 0) {
            if (StringUtils.isBlank(area)) {
                area = areas.get(0);
            }
            List<String> bussTypes = findBussTypeByArea(area);
            if (bussTypes != null && bussTypes.size() > 0) {
                for (String bussType : bussTypes) {
                    types.add(bussType);
                    DataBO dataBO = new DataBO();
                    String sql = "SELECT count(*) FROM customer_customerbaseinfo WHERE area = '" + area + "' AND businessType = '" + bussType + "'";
                    List<Object> objectList = super.findBySql(sql);
                    dataBO.setName(bussType);
                    dataBO.setValue(Integer.parseInt(String.valueOf(objectList.get(0))));
                    dataBOList.add(dataBO);
                }
            }
        }
        String[] text_type = new String[types.size()];
        text_type = types.toArray(text_type);
        DataBO[] dataBOS = new DataBO[dataBOList.size()];
        dataBOS = dataBOList.toArray(dataBOS);
        TitleBO titleBO = new TitleBO();
        titleBO.setText(area == null ? "" : area + "地区客户类型分类分析");
        TooltipBO tooltipBO = new TooltipBO();
        LegendBO legendBO = new LegendBO();
        legendBO.setData(text_type);
        PieSeriesBO pieSeriesBO = new PieSeriesBO();
        pieSeriesBO.setName(area == null ? "" : area + "地区客户类型分类分析");
        pieSeriesBO.setType("pie");
        pieSeriesBO.setData(dataBOS);

        PieOptionBO pieOptionBO = new PieOptionBO();
        pieOptionBO.setTitle(titleBO);
        pieOptionBO.setTooltip(tooltipBO);
        pieOptionBO.setLegend(legendBO);
        pieOptionBO.setSeries(pieSeriesBO);

        return pieOptionBO;
    }

    @Override
    public OptionBO bussTypeAreaBaiShow() throws SerException {
        List<String> bussTypes = findBussType();
        List<BirAreaDataBO> birAreaDataBOS = new ArrayList<>();
        List<String> areas = findArea();
        if (bussTypes != null && bussTypes.size() > 0) {
            for (String bussType : bussTypes) {
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        String sql = "SELECT count(*) FROM customer_customerbaseinfo WHERE area = '" + area + "' AND businessType = '" + bussType + "'";
                        List<Object> objectList = super.findBySql(sql);
                        BirAreaDataBO birAreaDataBO = new BirAreaDataBO();
                        birAreaDataBO.setArea(area);
                        birAreaDataBO.setBusinessType(bussType);
                        birAreaDataBO.setCustomerNum(Integer.parseInt(String.valueOf(objectList.get(0))));
                        birAreaDataBOS.add(birAreaDataBO);
                    }
                }
            }
        }

        List<SeriesBO> seriesBOS = new ArrayList<>();
        if (birAreaDataBOS != null && birAreaDataBOS.size() > 0) {
            for (String area : areas) {
                List<Integer> list = new ArrayList<>();
                for (String bussType : bussTypes) {
                    int num = birAreaDataBOS.stream().filter(birAreaDataBO -> bussType.equals(birAreaDataBO.getBusinessType()) && area.equals(birAreaDataBO.getArea())).mapToInt(BirAreaDataBO::getCustomerNum).sum();
                    list.add(num);
                }
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setType("bar");
                seriesBO.setName(area);
                Integer[] strings = new Integer[list.size()];
                strings = list.toArray(strings);
                seriesBO.setData(strings);
                seriesBOS.add(seriesBO);
            }
        }
        TitleBO titleBO = new TitleBO();
        titleBO.setText("各业务类型客户地区分布情况");

        TooltipBO tooltipBO = new TooltipBO();

        LegendBO legendBO = new LegendBO();
        String[] text_area = new String[areas.size()];
        text_area = areas.toArray(text_area);
        legendBO.setData(text_area);

        XAxisBO xAxisBO = new XAxisBO();
        String[] text_bussType = new String[bussTypes.size()];
        text_bussType = bussTypes.toArray(text_bussType);
        xAxisBO.setData(text_bussType);

        YAxisBO yAxisBO = new YAxisBO();

        SeriesBO[] text_seriesBO = new SeriesBO[seriesBOS.size()];
        text_seriesBO = seriesBOS.toArray(text_seriesBO);

        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_seriesBO);
        return optionBO;
    }

    @Override
    public OptionBO resouceBaiShow() throws SerException {
        List<String> bussTypes = findBussType();
        List<ResouceSumBO> resouceSumBOList = new ArrayList<>();
        if (bussTypes != null && bussTypes.size() > 0) {
            for (String bussType : bussTypes) {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM ( ");
                sql.append(" (SELECT count(*) as custIntrodNewCustNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 0) h, ");
                sql.append(" (SELECT count(*) as marketEnNewCustNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 1) i, ");
                sql.append(" (SELECT count(*) as bussNegotiationNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 2) j, ");
                sql.append(" (SELECT count(*) as biddingNewCustomerNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 3) k, ");
                sql.append(" (SELECT count(*) as websiteNewCustomerNum FROM customer_customerbaseinfo WHERE  businessType  = '" + bussType + "' AND origin = 4) l, ");
                sql.append(" (SELECT count(*) as staffIntroNewCustomerNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 5) m, ");
                sql.append(" (SELECT count(*) as otherResouceNewCustomerNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 6) n ");
                sql.append(" )");
                List<Object> objectList = super.findBySql(sql.toString());
                Object[] objects = (Object[]) objectList.get(0);
                Integer custIntrodNewCustNum = Integer.parseInt(String.valueOf(objects[0]));
                Integer marketEnNewCustNum = Integer.parseInt(String.valueOf(objects[1]));
                Integer bussNegotiationNum = Integer.parseInt(String.valueOf(objects[2]));
                Integer biddingNewCustomerNum = Integer.parseInt(String.valueOf(objects[3]));
                Integer staffIntroNewCustomerNum = Integer.parseInt(String.valueOf(objects[4]));
                Integer otherResouceNewCustomerNum = Integer.parseInt(String.valueOf(objects[5]));
                ResouceSumBO resouceSumBO = new ResouceSumBO();
                resouceSumBO.setBusinessType(bussType);
                resouceSumBO.setCustIntrodNewCustNum(custIntrodNewCustNum);
                resouceSumBO.setMarketEnNewCustNum(marketEnNewCustNum);
                resouceSumBO.setBussNegotiationNum(bussNegotiationNum);
                resouceSumBO.setBiddingNewCustomerNum(biddingNewCustomerNum);
                resouceSumBO.setStaffIntroNewCustomerNum(staffIntroNewCustomerNum);
                resouceSumBO.setOtherResouceNewCustomerNum(otherResouceNewCustomerNum);
                resouceSumBOList.add(resouceSumBO);
            }
        }
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText("客户来源分析");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_3 = new String[]{"客户介绍新增客户数量", "市场招待新增客户数量",
                "商务洽谈新增客户数量", "招投标新增客户数量", "网站新增客户数量",
                "员工介绍新增客户数量", "其他来源新增客户数量"};
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (resouceSumBOList != null && resouceSumBOList.size() > 0) {
            for (ResouceSumBO resouceSumBO : resouceSumBOList) {
                text_list2.add(resouceSumBO.getBusinessType());

                //柱状图数据
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(resouceSumBO.getBusinessType());
                seriesBO.setType("bar");

                Integer[] number = new Integer[]{resouceSumBO.getCustIntrodNewCustNum(), resouceSumBO.getMarketEnNewCustNum(),
                        resouceSumBO.getBussNegotiationNum(), resouceSumBO.getStaffIntroNewCustomerNum(), resouceSumBO.getOtherResouceNewCustomerNum()};
                seriesBO.setData(number);
                seriesBOList.add(seriesBO);
            }
        }

        String[] text_2 = new String[text_list2.size()];
        text_2 = text_list2.toArray(text_2);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);
        optionBO.setTooltip(tooltipBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    @Override
    public PieOptionBO resoucePieShowBybussType(String bussType) throws SerException {
        List<String> bussTypes = findBussType();
        List<DataBO> dataBOList = new ArrayList<>();
        String[] datas = new String[]{"客户介绍新增客户数量", "市场招待新增客户数量", "商务洽谈新增客户数量", "招投标新增客户数量", "网站新增客户数量", "员工介绍新增客户数量", "其他来源新增客户数量"};
        if (bussTypes != null && bussTypes.size() > 0) {
            if (StringUtils.isBlank(bussType)) {
                bussType = bussTypes.get(0);
            }
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ( ");
            sql.append(" (SELECT count(*) as custIntrodNewCustNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 0) h, ");
            sql.append(" (SELECT count(*) as marketEnNewCustNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 1) i, ");
            sql.append(" (SELECT count(*) as bussNegotiationNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 2) j, ");
            sql.append(" (SELECT count(*) as biddingNewCustomerNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 3) k, ");
            sql.append(" (SELECT count(*) as websiteNewCustomerNum FROM customer_customerbaseinfo WHERE  businessType  = '" + bussType + "' AND origin = 4) l, ");
            sql.append(" (SELECT count(*) as staffIntroNewCustomerNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 5) m, ");
            sql.append(" (SELECT count(*) as otherResouceNewCustomerNum FROM customer_customerbaseinfo WHERE  businessType = '" + bussType + "' AND origin = 6) n ");
            sql.append(" )");
            List<Object> objectList = super.findBySql(sql.toString());
            Object[] objects = (Object[]) objectList.get(0);
            for (int i = 0; i < 7; i++) {
                DataBO dataBO = new DataBO();
                dataBO.setName(datas[i]);
                dataBO.setValue(Integer.parseInt(String.valueOf(objects[i])));
                dataBOList.add(dataBO);
            }
        }


//        String[] text_type = new String[bussTypes.size()];
//        text_type = bussTypes.toArray(text_type);
        DataBO[] dataBOS = new DataBO[dataBOList.size()];
        dataBOS = dataBOList.toArray(dataBOS);
        TitleBO titleBO = new TitleBO();
        titleBO.setText(bussType == null ? "" : bussType + "客户来源分析");
        TooltipBO tooltipBO = new TooltipBO();
        LegendBO legendBO = new LegendBO();
        legendBO.setData(datas);
        PieSeriesBO pieSeriesBO = new PieSeriesBO();
        pieSeriesBO.setName(bussType == null ? "" : bussType + "客户来源分析");
        pieSeriesBO.setType("pie");
        pieSeriesBO.setData(dataBOS);

        PieOptionBO pieOptionBO = new PieOptionBO();
        pieOptionBO.setTitle(titleBO);
        pieOptionBO.setTooltip(tooltipBO);
        pieOptionBO.setLegend(legendBO);
        pieOptionBO.setSeries(pieSeriesBO);

        return pieOptionBO;
    }

    @Override
    public List<CustomerBaseInfoBO> computations() throws SerException {
        List<CustomerBaseInfo> customerBaseInfos = super.findAll();
        if (customerBaseInfos != null && customerBaseInfos.size() > 0) {
            for (CustomerBaseInfo customerBaseInfo : customerBaseInfos) {
                //地区权重
                AreaWeightSetBO areaWeightSetBO = areaWeightSetSer.findByProArea(customerBaseInfo.getProvinces(), customerBaseInfo.getArea());
                //业务权重
                BussTypeWeightSetBO bussTypeWeightSetBO = bussTypeWeightSetSer.findByProArea(customerBaseInfo.getBusinessType(), customerBaseInfo.getBusinessWay());
                //客户接触阶段权重
                CustomerContactWeightSetBO customerContactWeightSetBO = customerContactWeightSetSer.findByCustomerType(ContactPhace.enumToString(customerBaseInfo.getContactPhace()));
                //职权一层权重
                FirstFactorWeightBO funPowerBO = firstFactorWeightSer.findByName("职权");
                //职权二级权重
                FunPowerWeightBO funPowerWeightBO = funPowerWeightSer.findByName(WorkRight.enumToString(customerBaseInfo.getWorkRight()));
                //时效性一层权重
                FirstFactorWeightBO timelinessPowerBO = firstFactorWeightSer.findByName("时效性");
                //时效性二层权重
                TimelinessFactorWeightBO timelinessFactorWeightBO = timelinessFactorWeightSer.findByName(Timeliness.enumToString(customerBaseInfo.getTimeliness()));
                //亲密度一层权重
                FirstFactorWeightBO closenessPowerBO = firstFactorWeightSer.findByName("亲密度");
                //亲密度二层权重
                ClosenessFoactorWeightBO closenessFoactorWeightBO = closenessFoactorWeightSer.findByName(Intimacy.enumToString(customerBaseInfo.getIntimacy()));
                //难易度一层权重
                FirstFactorWeightBO difficultyBO = firstFactorWeightSer.findByName("难易度");
                //难易度二层权重
                DifficultyFoactorWeightBO difficultyFoactorWeightBO = difficultyFoactorWeightSer.findByName(DifficultyLevel.enumToString(customerBaseInfo.getDifficultyLevel()));

                if (areaWeightSetBO != null && bussTypeWeightSetBO != null && customerContactWeightSetBO != null && funPowerBO != null && funPowerWeightBO != null && timelinessPowerBO != null && timelinessFactorWeightBO != null && closenessPowerBO != null && closenessFoactorWeightBO != null && difficultyBO != null && difficultyFoactorWeightBO != null) {
                    Double finalWeight = areaWeightSetBO.getProvincesWeight() * areaWeightSetBO.getAreaWeight()
                            + bussTypeWeightSetBO.getBusinessTypeWeight() * bussTypeWeightSetBO.getBusinessWayWeight()
                            + customerContactWeightSetBO.getCustomerContactTypeWeight()
                            + funPowerBO.getFirstFactorWeight() * funPowerWeightBO.getFunPowerTypeWeight()
                            + timelinessPowerBO.getFirstFactorWeight() * timelinessFactorWeightBO.getTimelinessWeight()
                            + closenessPowerBO.getFirstFactorWeight() * closenessFoactorWeightBO.getClosenessWeight()
                            + difficultyBO.getFirstFactorWeight() * difficultyFoactorWeightBO.getDifficWeight();
                    customerBaseInfo.setFinalWeight(finalWeight);
                    super.update(customerBaseInfo);
                }
            }
        }
        CustomerBaseInfoDTO customerBaseInfoDTO = new CustomerBaseInfoDTO();
        int limit = customerBaseInfoDTO.getLimit();
        int start = limit * customerBaseInfoDTO.getPage();
        List<CustomerBaseInfo> customerBaseInfoList = customerBaseInfos.stream().skip(start).limit(limit).collect(Collectors.toList());
        return BeanTransform.copyProperties(customerBaseInfoList, CustomerBaseInfo.class);
    }
}