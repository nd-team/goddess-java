package com.bjike.goddess.rentcar.service;

import com.bjike.goddess.carinfo.api.DriverRecruitAPI;
import com.bjike.goddess.carinfo.dto.DriverRecruitDTO;
import com.bjike.goddess.carinfo.entity.DriverRecruit;
import com.bjike.goddess.carinfo.service.*;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.rentcar.bo.AreaBO;
import com.bjike.goddess.rentcar.bo.CollectDriverInfoBO;
import com.bjike.goddess.rentcar.bo.DepartmentBO;
import com.bjike.goddess.rentcar.bo.DriverInfoBO;
import com.bjike.goddess.rentcar.dto.CollectDriverInfoDTO;
import com.bjike.goddess.rentcar.dto.DriverInfoDTO;
import com.bjike.goddess.rentcar.entity.CollectDriverInfo;
import com.bjike.goddess.rentcar.entity.DriverInfo;
import com.bjike.goddess.rentcar.enums.AgreementStatus;
import com.bjike.goddess.rentcar.enums.GuideAddrStatus;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* 租车协议管理汇总业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-07 11:56 ]
* @Description:	[ 租车协议管理汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="rentcarSerCache")
@Service
public class CollectDriverInfoSerImpl extends ServiceImpl<CollectDriverInfo, CollectDriverInfoDTO> implements CollectDriverInfoSer {

    @Autowired
    private DriverInfoSer driverInfoSer;

    @Autowired
    private com.bjike.goddess.carinfo.api.DriverInfoAPI driverInfoAPI;

    @Autowired
    private DriverRecruitAPI driverRecruitAPI;

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
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
    public List<AreaBO> monthCollect(Integer year, Integer month) throws SerException {
        LocalDate[] startTime = null;
//        if(year !=null && month !=null){
//            if(month.length() == 2) {
//                startTime = DateUtil.parseDate(year+" -"+month+"-01 00:00:00.000");
//            }else{
//                startTime = DateUtil.parseDate(year+" -0"+month+"-01 00:00:00.000");
//            }
//        }
        if (year != null && month != null){
            LocalDate startDate = DateUtil.getStartDayOfMonth(year,month);
            LocalDate endDate = DateUtil.getEndDaYOfMonth(year,month);
            startTime = new LocalDate[]{startDate,endDate};
        }else{
            LocalDate startDate = DateUtil.getStartMonth();
            LocalDate endDate = DateUtil.getEndMonth();
            startTime = new LocalDate[]{startDate,endDate};
        }

        return collect(startTime);
    }

    @Override
    public List<AreaBO> allCollect(Integer year) throws SerException {
        LocalDate[] startTime = null;
        if (year != null){
            LocalDate startDate = DateUtil.parseDate(year+"-01-01");
            LocalDate endDate = DateUtil.parseDate(year+"-12-31");
            startTime = new LocalDate[]{startDate,endDate};
        }else{
            LocalDate startDate = DateUtil.getStartYear();
            LocalDate endDate = DateUtil.getEndYear();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }


    private List<AreaBO> collect(LocalDate[] time) throws SerException{
        DriverInfoDTO driverInfoDTO = new DriverInfoDTO();
        List<DriverInfoBO> driverInfos = driverInfoSer.pageList(driverInfoDTO);
        List<AreaBO> areaBOList = new ArrayList<>();
        Set<String> areas = driverInfos.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        for(String area : areas){
            AreaBO areaBO = new AreaBO();
            DriverInfoDTO driverInfoDTO1 = new DriverInfoDTO();
            DepartmentBO departmentBO = new DepartmentBO();
            driverInfoDTO1.getConditions().add(Restrict.eq("area",area));
            List<DriverInfoBO> driverInfoBOS = driverInfoSer.pageList(driverInfoDTO1);
            Set<String> departments = driverInfoBOS.stream().map(p -> p.getDepartment()).collect(Collectors.toSet());
            List<DepartmentBO> departmentBOS = new ArrayList<>();
            List<CollectDriverInfoBO> collectDriverInfoBOList = new ArrayList<>();


            for(String department : departments){
                CollectDriverInfoBO collectDriverInfoBO = new CollectDriverInfoBO();
                Integer businessDemand = 0;
                Integer projectDemand = 0;
                Integer availalbeDriver = 0;
                Integer waitSignDealNumber = 0;
                Integer needDriver = 0;
                Integer waitRelieveDealNumber = 0;


                //todo 商务需求
                //todo 项目需求
                //todo 还需司机数
                DriverRecruitDTO driverRecruitDTO = new DriverRecruitDTO();
                driverRecruitDTO.getConditions().add(Restrict.eq("area",area));
                driverRecruitDTO.getConditions().add(Restrict.eq("department",department));
                driverRecruitDTO.getConditions().add(Restrict.between("informationCollectionTime",time));
                driverRecruitDTO.getConditions().add(Restrict.eq("enSureAgreement",true));
//                List<DriverRecruit> driverInfoList1 = driverRecruitAPI.findByCis(driverRecruitDTO);

//                waitSignDealNumber = driverInfoList1.size();


//                StringBuilder sql = new StringBuilder("select *");
//                sql.append(" from rentcar_driverinfo where area ='"+area);
//                sql.append(" and department ='"+department1);
//                sql.append(" and startDate <='"+startTime+"'");
//                sql.append(" and endDate >'"+startTime+"'");
//                sql.append(" and agreementStatus = '0'");
                String[] filed = new String[]{"area"};
                DriverInfoDTO driverInfoDTO2 = new DriverInfoDTO();
                driverInfoDTO2.getConditions().add(Restrict.eq("area",area));
                driverInfoDTO2.getConditions().add(Restrict.eq("department",department));
                driverInfoDTO2.getConditions().add(Restrict.eq("agreementStatus", AgreementStatus.INLEASE));
                List<DriverInfo> driverInfoList = driverInfoSer.findByCis(driverInfoDTO2);
                availalbeDriver = driverInfoList.size();

                needDriver = businessDemand + projectDemand + availalbeDriver;


                StringBuilder sql2 = new StringBuilder("select *");
                sql2.append(" from rentcar_driverinfo where area ='"+area+"'");
                sql2.append(" and department ='"+department+"'");
                sql2.append(" and agreementStatus = '3'");
                List<DriverInfo> driverInfoList2 = driverInfoSer.findBySql(sql2.toString(),DriverInfo.class,filed);
                waitRelieveDealNumber = driverInfoList2.size();
                collectDriverInfoBO.setWaitRelieveDealNumber(waitRelieveDealNumber);
                collectDriverInfoBO.setAvailalbeDriver(availalbeDriver);
                collectDriverInfoBO.setWaitSignDealNumber(waitSignDealNumber);
                collectDriverInfoBO.setNeedDriver(needDriver);
                collectDriverInfoBO.setBusinessDemand(businessDemand);
                collectDriverInfoBO.setProjectDemand(projectDemand);

                collectDriverInfoBOList.add(collectDriverInfoBO);
                departmentBO.setDepartment(department);
                departmentBO.setCollectDriverInfo(collectDriverInfoBO);
                departmentBOS.add(departmentBO);
            }
            areaBO.setDepartmentList(departmentBOS);
            areaBO.setArea(area);
            areaBOList.add(areaBO);
        }
        return areaBOList;
    }
}