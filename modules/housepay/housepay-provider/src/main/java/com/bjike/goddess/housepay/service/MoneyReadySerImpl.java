package com.bjike.goddess.housepay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.bo.CollectCompareBO;
import com.bjike.goddess.housepay.bo.MoneyReadyBO;
import com.bjike.goddess.housepay.dto.MoneyReadyDTO;
import com.bjike.goddess.housepay.entity.MoneyReady;
import com.bjike.goddess.housepay.enums.GuideAddrStatus;
import com.bjike.goddess.housepay.to.GuidePermissionTO;
import com.bjike.goddess.housepay.to.MoneyReadyTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 资金准备审核表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:32 ]
 * @Description: [ 资金准备审核表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "housepaySerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer {
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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        Long count = super.count(moneyReadyDTO);
        return count;
    }

    @Override
    public MoneyReadyBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        MoneyReady moneyReady = super.findById(id);
        return BeanTransform.copyProperties(moneyReady,MoneyReadyBO.class);
    }

    @Override
    public List<MoneyReadyBO> findListMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        checkSeeIdentity();
        moneyReadyDTO.getSorts().add("createTime=desc");
        List<MoneyReady> moneyReadies = super.findByPage(moneyReadyDTO);
        List<MoneyReadyBO> moneyReadyBOS = BeanTransform.copyProperties(moneyReadies,MoneyReadyBO.class);
        return moneyReadyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyReadyBO insertMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        checkAddIdentity();
        MoneyReady moneyReady = BeanTransform.copyProperties(moneyReadyTO,MoneyReady.class,true);
        moneyReady.setCreateTime(LocalDateTime.now());
        super.save(moneyReady);
        return BeanTransform.copyProperties(moneyReady,MoneyReadyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyReadyBO editMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        checkAddIdentity();
        MoneyReady moneyReady = super.findById(moneyReadyTO.getId());
        BeanTransform.copyProperties(moneyReadyTO,moneyReady,true);
        moneyReady.setModifyTime(LocalDateTime.now());
        super.update(moneyReady);
        return BeanTransform.copyProperties(moneyReady,MoneyReadyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeMoneyReady(String id) throws SerException {
        checkAddIdentity();
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
//    public List<CollectCompareBO> time(String startMonth,String endMonth) throws SerException {
//        MoneyReadyDTO dto = new MoneyReadyDTO();
//        LocalDate[] dates = new LocalDate[0];
//        try {
//            dates = new LocalDate[]{LocalDate.parse(startMonth), LocalDate.parse(endMonth)};
//        } catch (Exception e) {
//            throw new SerException("时间格式错误(例:2010-12-31)");
//        }
//        dto.getConditions().add(Restrict.between("time", dates));
//        return super.findByCis(dto);
//    }
    @Override
    public List<CollectCompareBO> collectCompare(Integer month) throws SerException {
        Integer year = LocalDateTime.now().getYear();
        Set<String> projectGroups = findAllProjectGroup();
        MoneyReadyDTO dto = new MoneyReadyDTO();
        List<MoneyReady> list = super.findByCis(dto);
        List<CollectCompareBO> boList = new ArrayList<>();
        Double reserveSum = 0.0;
        Double lastReserveSum = 0.0;
        if (month != 1) {
            for (String projectGroup : projectGroups) {
                for (MoneyReady m : list) {
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        reserveSum += m.getReserves();
                    }
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year) && m.getMonth().equals(month - 1)) {
                        lastReserveSum += m.getReserves();
                    }
                }
                if ((reserveSum != 0) || (lastReserveSum != 0)) {
                    CollectCompareBO bo = new CollectCompareBO();
                    bo.setProjectGroup(projectGroup);
                    bo.setMonth(month);
                    bo.setReserveSum(reserveSum);
                    bo.setLastReserveSum(lastReserveSum);
                    bo.setBalance(reserveSum-lastReserveSum);
                    bo.setIncrease((reserveSum-lastReserveSum)/lastReserveSum*100);
                    /*if (lastReserveSum != 0) {
                        bo.setIncrease((reserveSum - lastReserveSum) / lastReserveSum);
                    } else {
                        bo.setIncrease(1.00);
                    }*/
                    boList.add(bo);
                    reserveSum = 0.00;
                    lastReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        } else {
            for (String projectGroup : projectGroups) {
                for (MoneyReady m : list) {
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        reserveSum += m.getReserves();
                    }
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year - 1) && m.getMonth().equals(12)) {
                        lastReserveSum += m.getReserves();
                    }
                }
                if ((reserveSum != 0) || (lastReserveSum != 0)) {
                    CollectCompareBO bo = new CollectCompareBO();
                    bo.setProjectGroup(projectGroup);
                    bo.setMonth(month);
                    bo.setReserveSum(reserveSum);
                    bo.setLastReserveSum(lastReserveSum);
                    bo.setBalance(reserveSum-lastReserveSum);
                    bo.setIncrease((reserveSum-lastReserveSum)/lastReserveSum*100);
                    /*if (lastReserveSum != 0) {
                        bo.setIncrease((reserveSum - lastReserveSum) / lastReserveSum);
                    } else {
                        bo.setIncrease(1.00);
                    }*/
                    boList.add(bo);
                    reserveSum = 0.00;
                    lastReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        }
    }
    private Set<String> findAllProjectGroup() throws SerException {
        List<MoneyReady> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (MoneyReady m : list) {
            set.add(m.getProjectGroup());
        }
        return set;
    }
        /*DateFormat format = new SimpleDateFormat("yyyy-MM");
        try {
            Date startDate =  format.parse(startMonth);
          if(startDate.getMonth() == 0){
              startDate.setYear(startDate.getYear()+1900-1);
              startDate.setMonth(12);
            }else{
                startDate.setMonth(startDate.getMonth()-1);
            }
        } catch (ParseException e) {
           e.printStackTrace();
       }      */

        /*List<CollectCompareBO> returnList = new ArrayList<>();
        MoneyReadyDTO moneyReadyDTO = new MoneyReadyDTO();
        if(StringUtils.isBlank(dto.getStartTime()) || StringUtils.isBlank(dto.getEndTime())){
            return returnList;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(dto.getStartTime()+"-01",format);
        LocalDate end = LocalDate.parse(dto.getEndTime()+"-01",format);
        int flag = 0 ;
        while (start.getYear() != end.getYear() || start.getMonthValue() != end.getMonthValue()){
            if(flag == 0){
                returnList = caculateActual(dto,returnList,start);
            }
            start = start.plusMonths(1);
            System.out.println(start);
            flag = 0;
            if(start.getMonthValue()  == 12){
                returnList = caculateActual(dto,returnList,start);
                flag = 1;
                if(start.plusYears(1).getYear() < end.getYear()){
                    start = start.plusYears(1);
                    start = LocalDate.parse(start.getYear() + "-01-01",DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    System.out.println(start);
                    returnList = caculateActual(dto,returnList,start);
                    flag = 2;
                }
            }
        }
        return returnList;*/
        /*DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(startTime.toString(),format);
        MoneyReadyDTO dto = new MoneyReadyDTO();
        dto.getConditions().add(Restrict.between("time",new String[]{startTime.toString(),endTime.toString()}));
        List<CollectCompareBO> boList =
                BeanTransform.copyProperties(super.findByCis(dto), CollectCompareBO.class);
        date.minusMonths(1);

        if(boList != null && !boList.isEmpty()){
            Double balance = 0.0;//差额
            Double increase = 0.0;//增长率

            for(CollectCompareBO bo : boList){

                Double sum = 0.0;
                sum += bo.getLastMonthReserves();

                if(bo.getBalance() != null){
                    balance = bo.getReserves()-bo.getLastMonthReserves();
                }
                if(bo.getIncrease() != null){
                    increase = balance/bo.getLastMonthReserves() * 0.1 ;
                }
             }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT dates ,projectGroup ,sum(reserves) as reserves FROM ");
        sb.append(" (SELECT date_format(time,'%Y-%m') as dates ,projectGroup,reserves from ");
        sb.append(" housepay_moneyready where time BETWEEN '"+ startTime +"' AND '"+ endTime +"')A GROUP BY dates,projectGroup ");
        String sql = sb.toString();
        String [] fields = new String[]{"dates","projectGroup","reserves"};
        List<CollectCompareBO> collectCompareBOS = super.findBySql(sql,CollectCompareBO.class,fields);
        return collectCompareBOS;*/

    /*public List<CollectCompareBO> caculateActual(MoneyReadyDTO dto,List<CollectCompareBO> returnList, LocalDate time) {
        int year = time.getYear();
        int month = time.getMonthValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate begin = LocalDate.parse(year + "-" + (month < 10 ? "0" + month : month) + "-01", formatter);
        LocalDate end = LocalDate.parse(year + "-" + (month < 10 ? "0" + month : month) + "-31", formatter);
        dto = new MoneyReadyDTO();
        dto.getConditions().add(Restrict.between("time",new String[]{begin.toString(),end.toString()}));
        List<CollectCompareBO> boList = null;
        try {
            boList = BeanTransform.copyProperties(super.findByCis(dto), CollectCompareBO.class);
        } catch (SerException e) {
            e.printStackTrace();
        }

        if(boList != null && !boList.isEmpty()){
            Double balance = 0.0;//差额
            Double increase = 0.0;//增长率

            for(CollectCompareBO bo : boList){

                Double sum = 0.0;
                sum += bo.getLastMonthReserves();

                if(bo.getBalance() != null){
                    balance = bo.getReserves()-bo.getLastMonthReserves();
                }
                if(bo.getIncrease() != null){
                    increase = balance/bo.getLastMonthReserves() * 100;
                }
            }
        }
        return null;
    }*/
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        /*LocalDate startDate = LocalDate.parse("2017-11-11",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate startDate1 = startDate.minusMonths(1);
        String date = String.valueOf(startDate1);
        date = date.substring(0,7);
        System.out.println(date);*/

       /* System.out.println(DateUtil.dateToString(LocalDate.now()));
        LocalDate now = LocalDate.now().withYear(2017).withMonth(11).withDayOfMonth(1);
        System.out.println(now.plusMonths(1));
        System.out.println(now.minusMonths(1));
        System.out.println(now.getMonthValue()-1);*/

}
