package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.bo.ManagerBO;
import com.bjike.goddess.salarymanage.bo.*;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateDetailDTO;
import com.bjike.goddess.salarymanage.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.dto.SalaryManageCollectDTO;
import com.bjike.goddess.salarymanage.entity.SalaryCalculateDetail;
import com.bjike.goddess.salarymanage.entity.SalaryConfirmRecord;
import com.bjike.goddess.salarymanage.entity.SalaryInformation;
import com.bjike.goddess.salarymanage.entity.SalaryManageCollect;
import com.bjike.goddess.salarymanage.enums.GuideAddrStatus;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* 薪资管理汇总业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 09:36 ]
* @Description:	[ 薪资管理汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="salarymanageSerCache")
@Service
public class SalaryManageCollectSerImpl extends ServiceImpl<SalaryManageCollect, SalaryManageCollectDTO> implements SalaryManageCollectSer {

    @Autowired
    private SalaryCalculateDetailSer salaryCalculateDetailSer;

    @Autowired
    private SalaryConfirmRecordSer salaryConfirmRecordSer;

    @Autowired
    private SalaryInformationSer salaryInformationSer;

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
            flag = cusPermissionSer.busCusPermission("2");
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
            flag = cusPermissionSer.getCusPermission("1");
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
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
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
    public List<ManageAreaBO> dayCollect(String theDay) throws SerException {
        if(!"".equals(theDay) && theDay != null){
            LocalDate startTime = DateUtil.parseDate(theDay);
        }
        List<SalaryCalculateDetail> details = salaryCalculateDetailSer.findAll();
        List<ManageAreaBO> boList = new ArrayList<>();
        if(details !=null && details.size() > 0){
            Set<ManageDepartmentBO> manageDepartmentBOS = new HashSet<>();
            for(SalaryCalculateDetail salaryCalculateDetail : details){
                ManageAreaBO manageAreaBO = new ManageAreaBO();
                manageAreaBO.setArea(salaryCalculateDetail.getArea());
                boList.add(manageAreaBO);
                SalaryCalculateDetailDTO detailDTO = new SalaryCalculateDetailDTO();
                detailDTO.getConditions().add(Restrict.eq("area",manageAreaBO.getArea()));
                List<SalaryCalculateDetail> details1 = salaryCalculateDetailSer.findByCis(detailDTO);
                List<SalaryManageCollectBO> salaryManageCollectBOS = new ArrayList<>();
                if(details1 !=null && details1.size() > 0){
                    for(SalaryCalculateDetail salaryCalculateDetail1 : details1){
                        ManageDepartmentBO manageDepartmentBO = new ManageDepartmentBO();
                        manageDepartmentBO.setDepartment(salaryCalculateDetail1.getDepartment());
                        manageDepartmentBOS.add(manageDepartmentBO);
                        SalaryConfirmRecordDTO salaryConfirmRecordDTO = new SalaryConfirmRecordDTO();
                        salaryConfirmRecordDTO.getConditions().add(Restrict.eq("isEntry",true));
                        salaryConfirmRecordDTO.getConditions().add(Restrict.eq("ifAcceptRemove",true));
                        salaryConfirmRecordDTO.getConditions().add(Restrict.eq("entryDate", LocalDate.now()));
                        List<SalaryConfirmRecord> confirmRecords = salaryConfirmRecordSer.findByCis(salaryConfirmRecordDTO);
                        SalaryManageCollectBO salaryManageCollectBO = new SalaryManageCollectBO();
                        if(confirmRecords !=null && confirmRecords.size() > 0){
                            salaryManageCollectBO.setAcceptNumber(confirmRecords.size());
                        }
                        SalaryInformationDTO salaryInformationDTO = new SalaryInformationDTO();
                        salaryInformationDTO.getConditions().add(Restrict.eq("area",manageAreaBO.getArea()));
                        salaryInformationDTO.getConditions().add(Restrict.eq("department",manageDepartmentBO.getDepartment()));
                        List<SalaryInformation> salaryInformations = salaryInformationSer.findByCis(salaryInformationDTO);
                        if(salaryInformations !=null && salaryInformations.size() > 0){
                            Double salary = salaryInformations.stream().filter(p-> null != p.getSalary()).mapToDouble(p-> p.getSalary()).sum();
                            salaryManageCollectBO.setAllSalary(salary);
                            //todo 根据地区和部门在招聘管理的招聘汇总表里面获取
                            //todo 根据地区和部门在招聘管理的招聘汇总表里面获取
                            //todo 根据地区和部门在入职管理的招聘汇总表里面获取
                            //todo 根据地区和部门在招聘面谈薪资确认记录中是入职的是否已确认为是的汇总
                            //todo 根据地区和部门在组织结构汇总表里面获取
                            //todo 根据地区和部门在离职管理模块汇总表里面获取数据
                            //todo 根据地区和部门在转正管理模块汇总表里面获取数据
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<ManageAreaBO> weekCollect(String theWeek) throws SerException {
        if(!"".equals(theWeek) && theWeek !=null){

        }
        List<SalaryCalculateDetail> details = salaryCalculateDetailSer.findAll();
        List<ManageAreaBO> boList = new ArrayList<>();
        if(details !=null && details.size() > 0){
            Set<ManageDepartmentBO> manageDepartmentBOS = new HashSet<>();
            for(SalaryCalculateDetail salaryCalculateDetail : details){
                ManageAreaBO manageAreaBO = new ManageAreaBO();
                manageAreaBO.setArea(salaryCalculateDetail.getArea());
                boList.add(manageAreaBO);
                SalaryCalculateDetailDTO detailDTO = new SalaryCalculateDetailDTO();
                detailDTO.getConditions().add(Restrict.eq("area",manageAreaBO.getArea()));
                List<SalaryCalculateDetail> details1 = salaryCalculateDetailSer.findByCis(detailDTO);
                List<SalaryManageCollectBO> salaryManageCollectBOS = new ArrayList<>();
                if(details1 !=null && details1.size() > 0){
                    for(SalaryCalculateDetail salaryCalculateDetail1 : details1){
                        ManageDepartmentBO manageDepartmentBO = new ManageDepartmentBO();
                        manageDepartmentBO.setDepartment(salaryCalculateDetail1.getDepartment());
                        manageDepartmentBOS.add(manageDepartmentBO);
                        SalaryConfirmRecordDTO salaryConfirmRecordDTO = new SalaryConfirmRecordDTO();
                        salaryConfirmRecordDTO.getConditions().add(Restrict.eq("isEntry",true));
                        salaryConfirmRecordDTO.getConditions().add(Restrict.eq("ifAcceptRemove",true));
                        salaryConfirmRecordDTO.getConditions().add(Restrict.eq("entryDate", LocalDate.now()));
                        List<SalaryConfirmRecord> confirmRecords = salaryConfirmRecordSer.findByCis(salaryConfirmRecordDTO);
                        SalaryManageCollectBO salaryManageCollectBO = new SalaryManageCollectBO();
                        if(confirmRecords !=null && confirmRecords.size() > 0){
                            salaryManageCollectBO.setAcceptNumber(confirmRecords.size());
                        }
                        SalaryInformationDTO salaryInformationDTO = new SalaryInformationDTO();
                        salaryInformationDTO.getConditions().add(Restrict.eq("area",manageAreaBO.getArea()));
                        salaryInformationDTO.getConditions().add(Restrict.eq("department",manageDepartmentBO.getDepartment()));
                        List<SalaryInformation> salaryInformations = salaryInformationSer.findByCis(salaryInformationDTO);
                        if(salaryInformations !=null && salaryInformations.size() > 0){
                            Double salary = salaryInformations.stream().filter(p-> null != p.getSalary()).mapToDouble(p-> p.getSalary()).sum();
                            salaryManageCollectBO.setAllSalary(salary);
                            //todo 根据地区和部门在招聘管理的招聘汇总表里面获取
                            //todo 根据地区和部门在招聘管理的招聘汇总表里面获取
                            //todo 根据地区和部门在入职管理的招聘汇总表里面获取
                            //todo 根据地区和部门在招聘面谈薪资确认记录中是入职的是否已确认为是的汇总
                            //todo 根据地区和部门在组织结构汇总表里面获取
                            //todo 根据地区和部门在离职管理模块汇总表里面获取数据
                            //todo 根据地区和部门在转正管理模块汇总表里面获取数据
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<ManageAreaBO> monthCollect(String theMonth) throws SerException {
        return null;
    }

    @Override
    public List<ManageAreaBO> allCollect(String allDay) throws SerException {
        return null;
    }
}