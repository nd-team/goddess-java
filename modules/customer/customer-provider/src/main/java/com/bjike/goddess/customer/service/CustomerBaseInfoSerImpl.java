package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CusPermissionAPI;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.entity.CusFamilyMember;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;
import com.bjike.goddess.customer.entity.CustomerDetail;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.enums.CustomerSex;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private CustomerLevelSer customerLevelAPI;
    @Autowired
    private CusFamilyMemberSer cusFamilyMemberAPI;
    @Autowired
    private CustomerDetailSer customerDetailAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;




    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity(String flagId ) throws SerException {
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
    private void checkAddIdentity(String flagId ) throws SerException {
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

        customerBaseInfoDTO.getSorts().add("createTime=desc");
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

    @Override
    public Long countCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
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
            CustomerLevelBO customerLevelBO = customerLevelAPI.getCustomerLevelByName(levelName);
            CustomerLevel customerLevel = BeanTransform.copyProperties(customerLevelBO, CustomerLevel.class, true);

            RpcTransmit.transmitUserToken(userToken);

            performance(customerBaseInfoTO);
            CustomerBaseInfo customerBaseInfo = BeanTransform.copyProperties(customerBaseInfoTO, CustomerBaseInfo.class, true);
            customerBaseInfo.setCreateTime(LocalDateTime.now());
            customerBaseInfo.setModifyPersion(userAPI.currentUser().getUsername());
            customerBaseInfo.setCustomerLevel(customerLevel);
            customerBaseInfo.setCustomerPosition(
                    Double.parseDouble(customerBaseInfoTO.getCustomerNum().substring(5, customerBaseInfoTO.getCustomerNum().length())));

            super.save(customerBaseInfo);
            return BeanTransform.copyProperties(customerBaseInfoTO, CustomerBaseInfoBO.class);
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerBaseInfoBO editCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO ) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块编辑权限
        checkAddIdentity("3");
        //查一遍级别
        CustomerBaseInfo cusBase = null;
        try {
            String levelName = customerBaseInfoTO.getCustomerLevelName();

            CustomerLevelBO customerLevelBO = customerLevelAPI.getCustomerLevelByName(levelName);
            CustomerLevel customerLevel = BeanTransform.copyProperties(customerLevelBO, CustomerLevel.class, true);
            //先查一遍基本信息
            cusBase = super.findById(customerBaseInfoTO.getId());
            performance(customerBaseInfoTO);

            CustomerBaseInfo customerBaseInfo = BeanTransform.copyProperties(customerBaseInfoTO, CustomerBaseInfo.class, true);
            customerBaseInfo.setCustomerLevel(customerLevel);

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
                List<CusFamilyMember> cusFamilyMemberList = cusFamilyMemberAPI.findByCis(familyMemberDTO);
                if (cusFamilyMemberList != null && cusFamilyMemberList.size() > 0) {
                    //删除家庭信息
                    cusFamilyMemberAPI.remove(cusFamilyMemberList);
                }
                //删除详细信息
                customerDetailAPI.remove(detail);
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
        areaList.stream().forEach(str->{
            num.add( Integer.parseInt(str.substring(4,str.length())));
        });
        Collections.sort(num,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        List<String> numList = new ArrayList<>();
        num.stream().forEach(str->{
            numList.add( "CS000"+str );
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
        if (StringUtils.isNotBlank(customerBaseInfoTO.getOrigin())) {
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
        if (StringUtils.isNotBlank(customerBaseInfoTO.getWorkRight())) {
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
            cbaseInfo.setOrigin(origanizion);
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
}