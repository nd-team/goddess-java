package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salarymanage.bo.*;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateDetailDTO;
import com.bjike.goddess.salarymanage.dto.SalaryTestCollectDTO;
import com.bjike.goddess.salarymanage.entity.SalaryCalculateDetail;
import com.bjike.goddess.salarymanage.entity.SalaryTestCollect;
import com.bjike.goddess.salarymanage.enums.GuideAddrStatus;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryTestCollectTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* 薪资测算汇总表业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-18 11:50 ]
* @Description:	[ 薪资测算汇总表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="salarymanageSerCache")
@Service
public class SalaryTestCollectSerImpl extends ServiceImpl<SalaryTestCollect, SalaryTestCollectDTO> implements SalaryTestCollectSer {
    @Autowired
    private SalaryCalculateDetailSer salaryCalculateDetailSer;

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
    public List<AreaBO> pageList(SalaryTestCollectDTO dto) throws SerException {
        SalaryCalculateDetailDTO detailDTO = new SalaryCalculateDetailDTO();
        List<SalaryCalculateDetailBO> salaryCalculateDetailBOS = salaryCalculateDetailSer.findList(detailDTO);
        List<AreaBO> areaBOList = new ArrayList<>();
        if(salaryCalculateDetailBOS !=null && salaryCalculateDetailBOS.size() >0){
            AreaBO areaBO = new AreaBO();
            for(SalaryCalculateDetailBO salaryCalculateDetailBO : salaryCalculateDetailBOS){
                areaBO.setArea(salaryCalculateDetailBO.getArea());
                detailDTO.getConditions().add(Restrict.eq("area",areaBO.getArea()));
                Set<DepartmentBO> departmentBOSet = new HashSet<>();
                List<SalaryCalculateDetail> salaryCalculateDetails = salaryCalculateDetailSer.findByCis(detailDTO);
                if(salaryCalculateDetails !=null && salaryCalculateDetails.size() > 0){
                    for(SalaryCalculateDetail salaryCalculateDetail : salaryCalculateDetails){
                        DepartmentBO departmentBO = new DepartmentBO();
                        departmentBO.setDepartment(salaryCalculateDetail.getDepartment());
                        detailDTO.getConditions().add(Restrict.eq("department",departmentBO.getDepartment()));
                        Set<BusinessDirectionBO> businessDirectionBOSet = new HashSet<>();
                        List<SalaryCalculateDetail> salaryCalculateDetails1 = salaryCalculateDetailSer.findByCis(detailDTO);
                        if(salaryCalculateDetails1 !=null && salaryCalculateDetails1.size() > 0){
                            for(SalaryCalculateDetail salaryCalculateDetail1 : salaryCalculateDetails1) {
                                BusinessDirectionBO businessDirectionBO = new BusinessDirectionBO();
                                businessDirectionBO.setBusinessDirection(salaryCalculateDetail1.getBusinessDirection());
                                Set<SkillPositionBO> skillPositionBOSet = new HashSet<>();
                                detailDTO.getConditions().add(Restrict.eq("businessDirection",businessDirectionBO.getBusinessDirection()));
                                List<SalaryCalculateDetail> salaryCalculateDetails2 = salaryCalculateDetailSer.findByCis(detailDTO);
                                if(salaryCalculateDetails2 !=null && salaryCalculateDetails2.size() > 0){
                                    for(SalaryCalculateDetail salaryCalculateDetail2 : salaryCalculateDetails2){
                                        SkillPositionBO skillPositionBO = new SkillPositionBO();
                                        skillPositionBO.setPosition(salaryCalculateDetail2.getPosition());
                                        skillPositionBO.setSkill(salaryCalculateDetail2.getSkill());
                                        Set<WorkAgeBO> workAgeBOSet = new HashSet<>();
                                        detailDTO.getConditions().add(Restrict.eq("skill",skillPositionBO.getSkill()));
                                        detailDTO.getConditions().add(Restrict.eq("position",skillPositionBO.getPosition()));
                                        List<SalaryCalculateDetail> salaryCalculateDetails3 = salaryCalculateDetailSer.findByCis(detailDTO);
                                        if(salaryCalculateDetails3 !=null && salaryCalculateDetails3.size() > 0){
                                            for(SalaryCalculateDetail salaryCalculateDetail3 : salaryCalculateDetails3){
                                                WorkAgeBO workAgeBO = new WorkAgeBO();
                                                workAgeBO.setWorkAge(salaryCalculateDetail3.getWorkAge());
                                                detailDTO.getConditions().add(Restrict.eq("workAge",workAgeBO.getWorkAge()));
                                                List<SalaryCalculateDetail> salaryCalculateDetails4 = salaryCalculateDetailSer.findByCis(detailDTO);
                                                if(salaryCalculateDetails4 !=null && salaryCalculateDetails4.size() > 0){
                                                    SalaryTestCollectBO salaryTestCollectBO = new SalaryTestCollectBO(0,0,0.0,0,0,0.0,0);
                                                    Integer peopleNum = salaryCalculateDetails4.size();
                                                    salaryTestCollectBO.setPeopleNum(peopleNum);
                                                    Integer allExpectation = salaryCalculateDetails4.stream().filter(p -> null != p.getExpectation()).mapToInt(p -> p.getExpectation()).sum();
                                                    salaryTestCollectBO.setAllExpectation(allExpectation);
                                                    Double averageExpectation = (double)(allExpectation/peopleNum);
                                                    salaryTestCollectBO.setAverageExpectation(averageExpectation);
                                                    Integer zhilain = salaryCalculateDetails4.stream().filter(p -> null != p.getZhilian()).mapToInt(p -> p.getZhilian()).sum();
                                                    Integer zhonghua = salaryCalculateDetails4.stream().filter(p -> null != p.getZhonghua()).mapToInt(p -> p.getZhonghua()).sum();
                                                    Integer lieping = salaryCalculateDetails4.stream().filter(p -> null != p.getLieping()).mapToInt(p -> p.getLieping()).sum();
                                                    Integer wuyou = salaryCalculateDetails4.stream().filter(p -> null != p.getWuyou()).mapToInt(p -> p.getWuyou()).sum();
                                                    Integer boss = salaryCalculateDetails4.stream().filter(p -> null != p.getBoss()).mapToInt(p -> p.getBoss()).sum();
                                                    Integer total = zhilain + zhonghua + lieping + wuyou + boss;
                                                    salaryTestCollectBO.setTotal(total);
                                                    long zhilainCount = salaryCalculateDetails4.stream().filter(p -> null != p.getZhilian()).count();
                                                    long zhonhuaCount = salaryCalculateDetails4.stream().filter(p -> null != p.getZhonghua()).count();
                                                    long liepingCount = salaryCalculateDetails4.stream().filter(p -> null != p.getLieping()).count();
                                                    long wuyouCount = salaryCalculateDetails4.stream().filter(p -> null != p.getLieping()).count();
                                                    long bossCount = salaryCalculateDetails4.stream().filter(p -> null != p.getBoss()).count();
                                                    Integer number = (int)(zhilainCount + zhonhuaCount + liepingCount + wuyouCount + bossCount);
                                                    salaryTestCollectBO.setNumber(number);
                                                    if(total > 1){
                                                        Double average = (double)(number/total);
                                                        salaryTestCollectBO.setAverage(average);
                                                    }else{
                                                        salaryTestCollectBO.setAverage(0.0);
                                                    }
                                                    workAgeBO.setSalaryTestCollect(salaryTestCollectBO);
                                                    workAgeBOSet.add(workAgeBO);
                                                }
                                            }
                                            skillPositionBO.setWorkAge(workAgeBOSet);
                                            skillPositionBOSet.add(skillPositionBO);
                                        }
                                    }
                                    businessDirectionBO.setPosition(skillPositionBOSet);
                                    businessDirectionBOSet.add(businessDirectionBO);
                                }
                            }
                            departmentBO.setBusinessDirection(businessDirectionBOSet);
                            departmentBOSet.add(departmentBO);
                        }
                    }
                    areaBO.setSalaryTestDepartmentSet(departmentBOSet);
                    areaBOList.add(areaBO);
                }
            }
        }
        return areaBOList;
    }

    @Override
    public void makeSalary(SalaryTestCollectTO to) throws SerException {
        SalaryTestCollect model = BeanTransform.copyProperties(to,SalaryTestCollect.class);
        SalaryTestCollectDTO dto = new SalaryTestCollectDTO();
        dto.getConditions().add(Restrict.eq("area",model.getArea()));
        dto.getConditions().add(Restrict.eq("department",model.getDepartment()));
        dto.getConditions().add(Restrict.eq("businessDirection",model.getBusinessDirection()));
        dto.getConditions().add(Restrict.eq("position",model.getPosition()));
        dto.getConditions().add(Restrict.eq("skill",model.getSkill()));
        dto.getConditions().add(Restrict.eq("workAge",model.getWorkAge()));
        List<SalaryTestCollect> list = super.findByCis(dto);
        if(list !=null && list.size() > 0){
            super.update(model);
        }else{
            super.save(model);
        }

    }
}