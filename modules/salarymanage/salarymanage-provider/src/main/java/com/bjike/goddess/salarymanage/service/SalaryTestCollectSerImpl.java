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
import com.bjike.goddess.salarymanage.entity.SalaryCalculateResult;
import com.bjike.goddess.salarymanage.entity.SalaryTestCollect;
import com.bjike.goddess.salarymanage.enums.GuideAddrStatus;
import com.bjike.goddess.salarymanage.enums.WorkAge;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryTestCollectTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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
        Set<String> areas = salaryCalculateDetailBOS.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getArea()).collect(Collectors.toSet());
        if(areas.size () > 0 && areas != null)
        if(salaryCalculateDetailBOS !=null && salaryCalculateDetailBOS.size() >0){
            for(String area : areas){
                AreaBO areaBO = new AreaBO();
                areaBO.setArea(area);
                SalaryCalculateDetailDTO detailDTO1 = new SalaryCalculateDetailDTO();
                detailDTO1.getConditions().add(Restrict.eq("area",areaBO.getArea()));
                List<SalaryCalculateDetail> salaryCalculateDetails = salaryCalculateDetailSer.findByCis(detailDTO1);
                Set<String> departs = salaryCalculateDetails.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getDepartment()).collect(Collectors.toSet());
                List<DepartmentBO> departmentBOSet = new ArrayList<>();
                if(salaryCalculateDetails !=null && salaryCalculateDetails.size() > 0){
                    for(String depart : departs){
                        SalaryCalculateDetailDTO detailDTO2 = new SalaryCalculateDetailDTO();
                        DepartmentBO departmentBO = new DepartmentBO();
                        departmentBO.setDepartment(depart);
                        detailDTO2.getConditions().add(Restrict.eq("area",areaBO.getArea()));
                        detailDTO2.getConditions().add(Restrict.eq("department",departmentBO.getDepartment()));
                        List<BusinessDirectionBO> businessDirectionBOSet = new ArrayList<>();
                        List<SalaryCalculateDetail> salaryCalculateDetails1 = salaryCalculateDetailSer.findByCis(detailDTO2);
                        Set<String> directions = salaryCalculateDetails1.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getBusinessDirection()).collect(Collectors.toSet());
                        if(salaryCalculateDetails1 !=null && salaryCalculateDetails1.size() > 0){
                            for(String direction : directions) {
                                SalaryCalculateDetailDTO detailDTO3 = new SalaryCalculateDetailDTO();
                                BusinessDirectionBO businessDirectionBO = new BusinessDirectionBO();
                                businessDirectionBO.setBusinessDirection(direction);
                                List<SkillPositionBO> skillPositionBOSet = new ArrayList<>();
                                detailDTO3.getConditions().add(Restrict.eq("area",areaBO.getArea()));
                                detailDTO3.getConditions().add(Restrict.eq("department",departmentBO.getDepartment()));
                                detailDTO3.getConditions().add(Restrict.eq("businessDirection",businessDirectionBO.getBusinessDirection()));
                                List<SalaryCalculateDetail> salaryCalculateDetails2 = salaryCalculateDetailSer.findByCis(detailDTO3);
                                Set<String> skills = salaryCalculateDetails2.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getSkill()).collect(Collectors.toSet());
                                Set<String> posits = salaryCalculateDetails2.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getPosition()).collect(Collectors.toSet());
                                if(salaryCalculateDetails2 !=null && salaryCalculateDetails2.size() > 0){
                                    for(String skill : skills){
                                        for(String posit : posits) {
                                            SkillPositionBO skillPositionBO = new SkillPositionBO();
                                            skillPositionBO.setPosition(posit);
                                            skillPositionBO.setSkill(skill);
                                            List<WorkAgeBO> workAgeBOSet = new ArrayList<>();
                                            SalaryCalculateDetailDTO detailDTO4 = new SalaryCalculateDetailDTO();
                                            detailDTO4.getConditions().add(Restrict.eq("area", area));
                                            detailDTO4.getConditions().add(Restrict.eq("department", depart));
                                            detailDTO4.getConditions().add(Restrict.eq("businessDirection",direction));
                                            detailDTO4.getConditions().add(Restrict.eq("skill",skill));
                                            detailDTO4.getConditions().add(Restrict.eq("position",posit));
                                            List<SalaryCalculateDetail> salaryCalculateDetails3 = salaryCalculateDetailSer.findByCis(detailDTO4);
                                            Set<WorkAge> ages = salaryCalculateDetails3.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getWorkAge()).collect(Collectors.toSet());
                                            if (salaryCalculateDetails3 != null && salaryCalculateDetails3.size() > 0) {
                                                for (WorkAge workAge : ages) {
                                                    WorkAgeBO workAgeBO = new WorkAgeBO();
                                                    workAgeBO.setWorkAge(workAge);
                                                    SalaryCalculateDetailDTO detailDTO5 = new SalaryCalculateDetailDTO();
                                                    detailDTO5.getConditions().add(Restrict.eq("area", area));
                                                    detailDTO5.getConditions().add(Restrict.eq("department", depart));
                                                    detailDTO5.getConditions().add(Restrict.eq("businessDirection",direction));
                                                    detailDTO5.getConditions().add(Restrict.eq("skill",skill));
                                                    detailDTO5.getConditions().add(Restrict.eq("position",posit));
                                                    detailDTO5.getConditions().add(Restrict.eq("workAge", workAge));
                                                    List<SalaryCalculateDetail> salaryCalculateDetails4 = salaryCalculateDetailSer.findByCis(detailDTO5);
                                                    if (salaryCalculateDetails4 != null && salaryCalculateDetails4.size() > 0) {
                                                        SalaryTestCollectBO salaryTestCollectBO = new SalaryTestCollectBO(0, 0, 0.0, 0, 0, 0.0, 0);
                                                        Integer peopleNum = salaryCalculateDetails4.size();
                                                        salaryTestCollectBO.setPeopleNum(peopleNum);
                                                        Integer allExpectation = salaryCalculateDetails4.stream().filter(p -> null != p.getExpectation()).mapToInt(p -> p.getExpectation()).sum();
                                                        salaryTestCollectBO.setAllExpectation(allExpectation);
                                                        Double averageExpectation = (double) (allExpectation / peopleNum);
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
                                                        Integer number = (int) (zhilainCount + zhonhuaCount + liepingCount + wuyouCount + bossCount);

                                                        SalaryTestCollectDTO resultDTO = new SalaryTestCollectDTO();
                                                        resultDTO.getConditions().add(Restrict.eq("area", area));
                                                        resultDTO.getConditions().add(Restrict.eq("department", depart));
                                                        resultDTO.getConditions().add(Restrict.eq("businessDirection", direction));
                                                        resultDTO.getConditions().add(Restrict.eq("skill", skill));
                                                        resultDTO.getConditions().add(Restrict.eq("position", posit));
                                                        resultDTO.getConditions().add(Restrict.eq("workAge", workAge));
                                                        List<SalaryTestCollect> results = super.findByCis(resultDTO);
                                                        if(results !=null && results.size() >0){
                                                            Integer skillRankLot = results.stream().filter(p -> null != p.getConsultStandard()).mapToInt(p -> p.getConsultStandard()).sum();
                                                            salaryTestCollectBO.setConsultStandard(skillRankLot);
                                                        }

                                                        salaryTestCollectBO.setNumber(number);
                                                        if (total > 1) {
                                                            Double average = (double) (number / total);
                                                            salaryTestCollectBO.setAverage(average);
                                                        } else {
                                                            salaryTestCollectBO.setAverage(0.0);
                                                        }
                                                        workAgeBO.setSalaryTestCollect(salaryTestCollectBO);
                                                        workAgeBOSet.add(workAgeBO);
                                                    }
                                                }

                                            }
                                            skillPositionBO.setWorkAge(workAgeBOSet);
                                            skillPositionBOSet.add(skillPositionBO);
                                        }
                                    }
                                    businessDirectionBO.setPosition(skillPositionBOSet);
                                    businessDirectionBO.setBusinessDirection(direction);
                                    businessDirectionBOSet.add(businessDirectionBO);
                                }
                            }
                            departmentBO.setBusinessDirection(businessDirectionBOSet);
                            departmentBO.setDepartment(depart);
                            departmentBOSet.add(departmentBO);
                        }
                    }
                    areaBO.setSalaryTestDepartmentSet(departmentBOSet);
                    areaBO.setArea(area);
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
            SalaryTestCollect salaryTestCollect = list.get(0);
            super.update(salaryTestCollect);
        }else{
            super.save(model);
        }

    }

    private <T> TreeSet<T> filter() throws SerException {
        TreeSet<T> treeSet = new TreeSet<>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Field[] field = o1.getClass().getDeclaredFields();//获取实体类的所有属性，返回field数组
                int num = 0;   //用于识别属性相同的个数
                int sum = 0;    //用于识别该对象除了集合的属性值个数
                for (Field f : field) {//遍历所有的属性
                    String type = f.getGenericType().toString();//获取属性的类型
                    if (type.indexOf("java.util.List") < 0) {
                        sum++;
                        String name = f.getName(); // 获取属性的名字
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);// 将属性的首字符大写，方便构造get，set方法
                        try {
                            Method m = o1.getClass().getMethod("get" + name);
                            Object value = m.invoke(o1);// 调用getter方法获取属性值
                            Method m1 = o2.getClass().getMethod("get" + name);
                            Object value1 = m1.invoke(o2);
                            if (value.equals(value1)) {    //判断该属性值是否相同
                                num++;
                            }
                        } catch (Exception e) {

                        }
                    }
                }
                if (num == sum) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return treeSet;
    }

}