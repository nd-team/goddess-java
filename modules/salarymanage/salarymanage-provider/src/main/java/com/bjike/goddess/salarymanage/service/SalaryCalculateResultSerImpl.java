package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salarymanage.bo.*;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateDetailDTO;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateResultDTO;
import com.bjike.goddess.salarymanage.entity.SalaryCalculateDetail;
import com.bjike.goddess.salarymanage.entity.SalaryCalculateResult;
import com.bjike.goddess.salarymanage.enums.GuideAddrStatus;
import com.bjike.goddess.salarymanage.enums.WorkAge;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryCalculateResultTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import scala.util.parsing.combinator.testing.Str;

import java.util.*;
import java.util.stream.Collectors;

/**
* 薪资测算结果业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 01:59 ]
* @Description:	[ 薪资测算结果业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="salarymanageSerCache")
@Service
public class SalaryCalculateResultSerImpl extends ServiceImpl<SalaryCalculateResult, SalaryCalculateResultDTO> implements SalaryCalculateResultSer {

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
    public List<ResultAreaBO> pageList() throws SerException {
        List<SalaryCalculateDetail> salaryCalculateDetails = salaryCalculateDetailSer.findAll();
//        TreeSet<ResultAreaBO> resultAreaBOS = new TreeSet<>(new Comparator<ResultAreaBO>() {
//            @Override
//            public int compare(ResultAreaBO o1, ResultAreaBO o2) {
//                o1.getResultDepartment();
//                return o1.getArea().compareTo(o2.getArea());
//            }
//        });
        List<ResultAreaBO> resultAreaBOS = new ArrayList<>();
        Set<String> areas = salaryCalculateDetails.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getArea()).collect(Collectors.toSet());
        for (String area : areas) {
            ResultAreaBO resultAreaBO = new ResultAreaBO();
            resultAreaBO.setArea(area);
            SalaryCalculateDetailDTO detailDTO = new SalaryCalculateDetailDTO();
            detailDTO.getConditions().add(Restrict.eq("area", area));
            List<SalaryCalculateDetail> salaryCalculateDetails1 = salaryCalculateDetailSer.findByCis(detailDTO);
            Set<String> departs = salaryCalculateDetails1.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getDepartment()).collect(Collectors.toSet());
            List<ResultDepartmentBO> resultDepartmentBOS = new ArrayList<>();

            for (String depart : departs) {
                SalaryCalculateDetailDTO detailDTO1 = new  SalaryCalculateDetailDTO();
                detailDTO1.getConditions().add(Restrict.eq("area",area));
                detailDTO1.getConditions().add(Restrict.eq("department", depart));
                ResultDepartmentBO resultDepartmentBO = new ResultDepartmentBO();
                List<SalaryCalculateDetail> salaryCalculateDetails2 = salaryCalculateDetailSer.findByCis(detailDTO1);
                Set<String> directions = salaryCalculateDetails2.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getBusinessDirection()).collect(Collectors.toSet());
                List<ResultDirectionBO> resultDirectionBOS = new ArrayList<>();
                for (String direction : directions) {
                    SalaryCalculateDetailDTO detailDTO2 = new  SalaryCalculateDetailDTO();
                    detailDTO2.getConditions().add(Restrict.eq("area",area));
                    detailDTO2.getConditions().add(Restrict.eq("department", depart));
                    detailDTO2.getConditions().add(Restrict.eq("businessDirection", direction));
                    ResultDirectionBO resultDirectionBO = new ResultDirectionBO();
                    List<SalaryCalculateDetail> salaryCalculateDetails3 = salaryCalculateDetailSer.findByCis(detailDTO2);
                    Set<String> skillPosition = salaryCalculateDetails3.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getSkill()).collect(Collectors.toSet());
                    Set<String> posit = salaryCalculateDetails3.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getPosition()).collect(Collectors.toSet());
                    List<ResultSkillPositionBO> resultSkillPositionBOS = new ArrayList<>();
                    for (String skill : skillPosition) {
                        for (String position : posit) {
                            SalaryCalculateDetailDTO detailDTO3 = new  SalaryCalculateDetailDTO();
                            detailDTO3.getConditions().add(Restrict.eq("area",area));
                            detailDTO3.getConditions().add(Restrict.eq("department", depart));
                            detailDTO3.getConditions().add(Restrict.eq("businessDirection", direction));
                            detailDTO3.getConditions().add(Restrict.eq("skill", skill));
                            detailDTO3.getConditions().add(Restrict.eq("position", position));
                            List<SalaryCalculateDetail> salaryCalculateDetails4 = salaryCalculateDetailSer.findByCis(detailDTO3);
                            List<SalaryCalculateDetail> list = salaryCalculateDetails4.stream().filter(salaryCalculateDetail -> skill.equals(salaryCalculateDetail.getSkill()) && position.equals(salaryCalculateDetail.getPosition())).collect(Collectors.toList());
                            Set<WorkAge> ages = list.stream().map(salaryCalculateDetail -> salaryCalculateDetail.getWorkAge()).collect(Collectors.toSet());
                            ResultSkillPositionBO resultSkillPositionBO = new ResultSkillPositionBO();
                            List<ResultWorkAgeBO> workAgeBOS = new ArrayList<>();
                            for (WorkAge workAge : ages) {
                                ResultWorkAgeBO resultWorkAgeBO = new ResultWorkAgeBO();
                                resultSkillPositionBO.setSkill(skill);
                                resultSkillPositionBO.setPosition(position);
                                resultSkillPositionBOS.add(resultSkillPositionBO);
                                resultWorkAgeBO.setWorkAge(workAge);
                                SalaryCalculateDetailDTO detailDTO4 = new SalaryCalculateDetailDTO();
                                detailDTO4.getConditions().add(Restrict.eq("area", area));
                                detailDTO4.getConditions().add(Restrict.eq("department", depart));
                                detailDTO4.getConditions().add(Restrict.eq("businessDirection", direction));
                                detailDTO4.getConditions().add(Restrict.eq("skill", skill));
                                detailDTO4.getConditions().add(Restrict.eq("position", position));
                                detailDTO4.getConditions().add(Restrict.eq("workAge", workAge));
                                SalaryCalculateResultDTO resultDTO = new SalaryCalculateResultDTO();
                                resultDTO.getConditions().add(Restrict.eq("area", area));
                                resultDTO.getConditions().add(Restrict.eq("department", depart));
                                resultDTO.getConditions().add(Restrict.eq("businessDirection", direction));
                                resultDTO.getConditions().add(Restrict.eq("skill", skill));
                                resultDTO.getConditions().add(Restrict.eq("position", position));
                                resultDTO.getConditions().add(Restrict.eq("workAge", workAge));
                                List<SalaryCalculateResult> results = super.findByCis(resultDTO);
                                if(results !=null && results.size() >0){
                                    Integer skillRankLot = results.stream().filter(p -> null != p.getSkillRankLot()).mapToInt(p -> p.getSkillRankLot()).sum();
                                    Integer manageRankLot = results.stream().filter(p -> null != p.getManageRankLot()).mapToInt(p -> p.getManageRankLot()).sum();
                                    resultSkillPositionBO.setAllManageRankLot(manageRankLot);
                                    resultSkillPositionBO.setAllSkillRankLot(skillRankLot);
                                }
                                SalaryCalculateResultBO salaryCalculateResultBO = new SalaryCalculateResultBO();
                                List<SalaryCalculateDetail> salaryCalculateDetails5 = salaryCalculateDetailSer.findByCis(detailDTO4);
                                if (salaryCalculateDetails5 != null && salaryCalculateDetails5.size() > 0) {
                                    Integer minExpectation = 0;
                                    Integer maxExpectation = 0;
                                    for (int i = 0; i < salaryCalculateDetails5.size(); i++) {
                                        int temp = salaryCalculateDetails5.get(i).getExpectation();
                                        if (i == 0) {
                                            minExpectation = temp;
                                            maxExpectation = temp;
                                        }

                                        if (temp > maxExpectation) {
                                            maxExpectation = temp;
                                        }
                                        if (temp < minExpectation) {
                                            minExpectation = temp;
                                        }
                                    }
                                    salaryCalculateResultBO.setMaxExpectation(maxExpectation);
                                    salaryCalculateResultBO.setMinExpectation(minExpectation);
                                    Double averageExpection = (double) ((maxExpectation + minExpectation) / 2);
                                    salaryCalculateResultBO.setAverageExpectation(averageExpection);
                                    Integer interval = maxExpectation - minExpectation;
                                    salaryCalculateResultBO.setInterval(interval);
                                    resultWorkAgeBO.setSalaryCalculateResult(salaryCalculateResultBO);
                                    workAgeBOS.add(resultWorkAgeBO);
                                }
                            }
                            resultSkillPositionBO.setResultWorkAge(workAgeBOS);
                        }
                        resultDirectionBO.setBusinessDirection(direction);
                        resultDirectionBO.setResultSkillPosition(resultSkillPositionBOS);
                        resultDirectionBOS.add(resultDirectionBO);
                    }
                }
                resultDepartmentBO.setDepartment(depart);
                resultDepartmentBO.setResultDirection(resultDirectionBOS);
                resultDepartmentBOS.add(resultDepartmentBO);
            }
            resultAreaBO.setArea(area);
            resultAreaBO.setResultDepartment(resultDepartmentBOS);
            resultAreaBOS.add(resultAreaBO);
        }
        return resultAreaBOS;
    }





//        if(salaryCalculateDetails !=null && salaryCalculateDetails.size() >0){
//            for(SalaryCalculateDetail salaryCalculateDetail : salaryCalculateDetails){
//                ResultAreaBO resultAreaBO = new ResultAreaBO();
//                resultAreaBO.setArea(salaryCalculateDetail.getArea());
//                SalaryCalculateDetailDTO detailDTO = new SalaryCalculateDetailDTO();
//                TreeSet<ResultDepartmentBO> resultDepartmentBOS = new TreeSet<>(new Comparator<ResultDepartmentBO>() {
//                    @Override
//                    public int compare(ResultDepartmentBO o1, ResultDepartmentBO o2) {
//                        return o1.getDepartment().compareTo(o2.getDepartment());
//                    }
//                });
//
//                detailDTO.getConditions().add(Restrict.eq("area",resultAreaBO.getArea()));
//                List<SalaryCalculateDetail> salaryCalculateDetails1 = salaryCalculateDetailSer.findByCis(detailDTO);
//                if(salaryCalculateDetails1 !=null && salaryCalculateDetails1.size() > 0){
//                    for(SalaryCalculateDetail salaryCalculateDetail1 : salaryCalculateDetails1){
//                        ResultDepartmentBO resultDepartmentBO = new ResultDepartmentBO();
//                        resultDepartmentBO.setDepartment(salaryCalculateDetail1.getDepartment());
//                        TreeSet<ResultDirectionBO> resultDirectionBOS = new TreeSet<>(new Comparator<ResultDirectionBO>() {
//                            @Override
//                            public int compare(ResultDirectionBO o1, ResultDirectionBO o2) {
//                                return o1.getBusinessDirection().compareTo(o2.getBusinessDirection());
//                            }
//                        });
//                        detailDTO.getConditions().add(Restrict.eq("department",resultDepartmentBO.getDepartment()));
//                        List<SalaryCalculateDetail> salaryCalculateDetails2 = salaryCalculateDetailSer.findByCis(detailDTO);
//                        if(salaryCalculateDetails2 !=null && salaryCalculateDetails2.size() > 0){
//                            for(SalaryCalculateDetail salaryCalculateDetail2 : salaryCalculateDetails2){
//                                ResultDirectionBO resultDirectionBO = new ResultDirectionBO();
//                                resultDirectionBO.setBusinessDirection(salaryCalculateDetail2.getBusinessDirection());
//                                TreeSet<ResultSkillPositionBO> resultSkillPositionBOS = new TreeSet<>(new Comparator<ResultSkillPositionBO>() {
//                                    @Override
//                                    public int compare(ResultSkillPositionBO o1, ResultSkillPositionBO o2) {
//                                        if (o1.getPosition().equals(o2.getPosition())){
//                                            return o1.getSkill().compareTo(o2.getSkill());
//                                        }else {
//                                            return o1.getPosition().compareTo(o2.getPosition());
//                                        }
//                                    }
//                                });
//                                detailDTO.getConditions().add(Restrict.eq("businessDirection",resultDirectionBO.getBusinessDirection()));
//                                List<SalaryCalculateDetail> salaryCalculateDetails3 = salaryCalculateDetailSer.findByCis(detailDTO);
//                                if(salaryCalculateDetails3 !=null && salaryCalculateDetails3.size() > 0) {
//                                    for (SalaryCalculateDetail salaryCalculateDetail3 : salaryCalculateDetails3) {
//                                        ResultSkillPositionBO resultSkillPositionBO = new ResultSkillPositionBO();
//                                        resultSkillPositionBO.setSkill(salaryCalculateDetail3.getSkill());
//                                        resultSkillPositionBO.setPosition(salaryCalculateDetail3.getPosition());
//                                        TreeSet<ResultWorkAgeBO> resultWorkAgeBOS = new TreeSet<>(new Comparator<ResultWorkAgeBO>() {
//                                            @Override
//                                            public int compare(ResultWorkAgeBO o1, ResultWorkAgeBO o2) {
//                                                return o1.getWorkAge().compareTo(o2.getWorkAge());
//                                            }
//                                        });
//                                        detailDTO.getConditions().add(Restrict.eq("skill", resultSkillPositionBO.getSkill()));
//                                        detailDTO.getConditions().add(Restrict.eq("position", resultSkillPositionBO.getPosition()));
//                                        SalaryCalculateResultDTO resultDTO = new SalaryCalculateResultDTO();
//                                        resultDTO.getConditions().add(Restrict.eq("department",resultDepartmentBO.getDepartment()));
//                                        resultDTO.getConditions().add(Restrict.eq("businessDirection",resultDirectionBO.getBusinessDirection()));
//                                        resultDTO.getConditions().add(Restrict.eq("skill",resultSkillPositionBO.getSkill()));
//                                        resultDTO.getConditions().add(Restrict.eq("position",resultSkillPositionBO.getPosition()));
//                                        List<SalaryCalculateResult> salaryCalculateResults = super.findByCis(resultDTO);
//                                        if(salaryCalculateResults !=null && salaryCalculateResults.size() >0){
//                                            Integer skillRankLot = salaryCalculateResults.stream().filter(p -> null != p.getSkillRankLot()).mapToInt(p -> p.getSkillRankLot()).sum();
//                                            Integer manageRankLot = salaryCalculateResults.stream().filter(p -> null != p.getManageRankLot()).mapToInt(p -> p.getManageRankLot()).sum();
//                                            resultSkillPositionBO.setAllManageRankLot(manageRankLot);
//                                            resultSkillPositionBO.setAllSkillRankLot(skillRankLot);
//                                        }
//                                        List<SalaryCalculateDetail> salaryCalculateDetails4 = salaryCalculateDetailSer.findByCis(detailDTO);
//                                        if (salaryCalculateDetails4 != null && salaryCalculateDetails4.size() > 0) {
//                                            for (SalaryCalculateDetail salaryCalculateDetail4 : salaryCalculateDetails4) {
//                                                ResultWorkAgeBO resultWorkAgeBO = new ResultWorkAgeBO();
//                                                resultWorkAgeBO.setWorkAge(salaryCalculateDetail4.getWorkAge());
//                                                SalaryCalculateResultBO salaryCalculateResultBO = new SalaryCalculateResultBO();
//                                                detailDTO.getConditions().add(Restrict.eq("workAge",resultWorkAgeBO.getWorkAge()));
//                                                List<SalaryCalculateDetail> salaryCalculateDetails5 = salaryCalculateDetailSer.findByCis(detailDTO);
//                                                if(salaryCalculateDetails5 !=null && salaryCalculateDetails5.size() > 0){
//                                                    Integer minExpectation = 0;
//                                                    Integer maxExpectation = 0;
//                                                    for(int i = 0; i< salaryCalculateDetails5.size();i++){
//                                                        int temp = salaryCalculateDetails5.get(i).getExpectation();
//                                                        if(i == 0){
//                                                            minExpectation = temp;
//                                                            maxExpectation = temp;
//                                                        }
//
//                                                        if(temp > maxExpectation){
//                                                            maxExpectation = temp;
//                                                        }
//                                                        if(temp < minExpectation){
//                                                            minExpectation = temp;
//                                                        }
//                                                    }
//                                                    salaryCalculateResultBO.setMaxExpectation(maxExpectation);
//                                                    salaryCalculateResultBO.setMinExpectation(minExpectation);
//                                                    Double averageExpection = (double)((maxExpectation + minExpectation)/2);
//                                                    salaryCalculateResultBO.setAverageExpectation(averageExpection);
//                                                    Integer interval = maxExpectation - minExpectation;
//                                                    salaryCalculateResultBO.setInterval(interval);
//                                                }
//                                                resultWorkAgeBO.setSalaryCalculateResult(salaryCalculateResultBO);
//                                                resultWorkAgeBOS.add(resultWorkAgeBO);
//
//                                            }
//                                        }
//                                        resultSkillPositionBO.setResultWorkAge(resultWorkAgeBOS);
//                                        resultSkillPositionBOS.add(resultSkillPositionBO);
//                                    }
//                                }
//                                resultDirectionBO.setResultSkillPosition(resultSkillPositionBOS);
//                                resultDirectionBOS.add(resultDirectionBO);
//                            }
//                        }
//                        resultDepartmentBO.setResultDirection(resultDirectionBOS);
//                        resultDepartmentBOS.add(resultDepartmentBO);
//                    }
//                }
//                resultAreaBO.setResultDepartment(resultDepartmentBOS);
//                resultAreaBOS.add(resultAreaBO);
//            }
//        }
//            return resultAreaBOS;
//        }
//    }
//
//}



    @Override
    public void makeShare(SalaryCalculateResultTO to) throws SerException {
        SalaryCalculateResult model = BeanTransform.copyProperties(to,SalaryCalculateResult.class);
        SalaryCalculateResultDTO dto = new SalaryCalculateResultDTO();
        dto.getConditions().add(Restrict.eq("area",to.getArea()));
        dto.getConditions().add(Restrict.eq("department",to.getDepartment()));
        dto.getConditions().add(Restrict.eq("businessDirection",to.getBusinessDirection()));
        dto.getConditions().add(Restrict.eq("skill",to.getSkill()));
        dto.getConditions().add(Restrict.eq("position",to.getPosition()));
        dto.getConditions().add(Restrict.eq("workAge",to.getWorkAge()));
        List<SalaryCalculateResult> list = super.findByCis(dto);
        if(list !=null && list.size() > 0){
            super.update(model);
        }else{
            super.save(model);
        }
    }
}