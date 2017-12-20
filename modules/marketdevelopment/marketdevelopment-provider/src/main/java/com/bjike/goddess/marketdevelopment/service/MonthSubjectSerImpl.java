package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.MonthBusinessDataBO;
import com.bjike.goddess.marketdevelopment.bo.MonthMonthMoneyBO;
import com.bjike.goddess.marketdevelopment.bo.MonthSubjectBO;
import com.bjike.goddess.marketdevelopment.bo.MonthSubjectDataBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessDataDTO;
import com.bjike.goddess.marketdevelopment.dto.MonthMoneyDTO;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectDTO;
import com.bjike.goddess.marketdevelopment.dto.SubjectDataDTO;
import com.bjike.goddess.marketdevelopment.entity.BusinessData;
import com.bjike.goddess.marketdevelopment.entity.MonthMoney;
import com.bjike.goddess.marketdevelopment.entity.MonthSubject;
import com.bjike.goddess.marketdevelopment.entity.SubjectData;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectUpdateTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 月计划的业务科目业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-06 05:04 ]
 * @Description: [ 月计划的业务科目业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class MonthSubjectSerImpl extends ServiceImpl<MonthSubject, MonthSubjectDTO> implements MonthSubjectSer {

    @Autowired
    private MonthMoneySer monthMoneySer;
    @Autowired
    private BusinessDataSer businessDataSer;
    @Autowired
    private SubjectDataSer subjectDataSer;
    @Autowired
    private MonthSubjectSer monthSubjectSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = marPermissionSer.getMarPermission(marketCheck);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = marPermissionSer.getMarPermission(marketManage);
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
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
            flag = marPermissionSer.getMarPermission(marketCheck);
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
            flag = marPermissionSer.getMarPermission(marketManage);
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
    public List<MonthMonthMoneyBO> maps(MonthSubjectDTO dto) throws SerException {
//        String year = LocalDate.now().getYear() + "年";
//        String month = LocalDate.now().getMonthValue() + "月";
//        if (StringUtils.isNotBlank(dto.getYear()) && StringUtils.isNotBlank(dto.getMonth())) {
//            year = dto.getYear();
//            month = dto.getMonth();
//        }

        MonthMoneyDTO monthMoneyDTO = new MonthMoneyDTO();
//        monthMoneyDTO.getConditions().add(Restrict.eq("year", year));
//        monthMoneyDTO.getConditions().add(Restrict.eq("month", month));
        List<MonthMoney> monthMoneys = monthMoneySer.findByCis(monthMoneyDTO);
        List<MonthMonthMoneyBO> monthMonthMoneyBOs = new ArrayList<>(0);
        MonthMonthMoneyBO monthMonthMoneyBO = new MonthMonthMoneyBO();
        if (null != monthMoneys && monthMoneys.size() > 0) {
            BeanTransform.copyProperties(monthMoneys.get(0), monthMonthMoneyBO, false);
        } else {
            return null;
        }

        BusinessDataDTO businessDataDTO = new BusinessDataDTO();
        businessDataDTO.getConditions().add(Restrict.eq("monthMoneyId", monthMonthMoneyBO.getId()));
        List<BusinessData> businessDatas = businessDataSer.findByCis(businessDataDTO);
        if (null != businessDatas && businessDatas.size() > 0) {
            List<MonthBusinessDataBO> monthBusinessDataBOs = new ArrayList<>();
            for (BusinessData businessData : businessDatas) {
                MonthBusinessDataBO monthBusinessDataBO = BeanTransform.copyProperties(businessData, MonthBusinessDataBO.class, false);
                monthBusinessDataBOs.add(monthBusinessDataBO);

                SubjectDataDTO subjectDataDTO = new SubjectDataDTO();
                subjectDataDTO.getConditions().add(Restrict.eq("businessDataId", monthBusinessDataBO.getId()));
                List<SubjectData> subjectDatas = subjectDataSer.findByCis(subjectDataDTO);
                if (null != subjectDatas && subjectDatas.size() > 0) {
                    List<MonthSubjectDataBO> monthSubjectDataBOs = new ArrayList<>(0);
                    for (SubjectData subjectData : subjectDatas) {
                        MonthSubjectDataBO monthSubjectDataBO = BeanTransform.copyProperties(subjectData, MonthSubjectDataBO.class, false);
                        monthSubjectDataBOs.add(monthSubjectDataBO);

                        MonthSubjectDTO monthSubjectDTO = new MonthSubjectDTO();
                        monthSubjectDTO.getConditions().add(Restrict.eq("subjectDataId", monthSubjectDataBO.getId()));
                        List<MonthSubject> monthSubjects = monthSubjectSer.findByCis(monthSubjectDTO);
                        if (null != monthSubjects && monthSubjects.size() > 0) {
                            List<MonthSubjectBO> monthSubjectBOs = new ArrayList<>(0);
                            for (MonthSubject monthSubject : monthSubjects) {
                                MonthSubjectBO monthSubjectBO = BeanTransform.copyProperties(monthSubject, MonthSubjectBO.class, false);
                                monthSubjectBOs.add(monthSubjectBO);
                            }
                        }
                        monthSubjectDataBOs.add(monthSubjectDataBO);
                    }
                    monthBusinessDataBO.setMonthSubjectDataVOs(monthSubjectDataBOs);
                    monthBusinessDataBOs.add(monthBusinessDataBO);
                }
                monthMonthMoneyBO.setMonthBusinessDataVOs(monthBusinessDataBOs);
                monthMonthMoneyBOs.add(monthMonthMoneyBO);
            }
        }
        return monthMonthMoneyBOs;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(MonthSubjectTO to) throws SerException {
        //月份金额表的id
        String moneyMonthId = "";
        MonthMoney monthMoney = BeanTransform.copyProperties(to, MonthMoney.class, true);
        MonthMoneyDTO monthMoneyDTO = new MonthMoneyDTO();
        monthMoneyDTO.getConditions().add(Restrict.eq("year", monthMoney.getYear()));
        monthMoneyDTO.getConditions().add(Restrict.eq("month", monthMoney.getMonth()));
        List<MonthMoney> monthMoneys = monthMoneySer.findByCis(monthMoneyDTO);
        if (null != monthMoneys && monthMoneys.size() > 0) {
            MonthMoney monthMoney1 = monthMoneys.get(0);
            moneyMonthId = monthMoney1.getId();
            BeanTransform.copyProperties(monthMoney, monthMoney1);
            monthMoney1.setModifyTime(LocalDateTime.now());
            monthMoney1.setDiferenceMoney(monthMoney1.getTargetMoney() - monthMoney1.getActualMoney());
            monthMoneySer.update(monthMoney1);
        } else {
            monthMoney.setDiferenceMoney(monthMoney.getTargetMoney() - monthMoney.getActualMoney());
            monthMoney = monthMoneySer.save(monthMoney);
            moneyMonthId = monthMoney.getId();
        }

        //业务方向类型id
        String businessTypeId = "";
        BusinessData businessData = BeanTransform.copyProperties(to, BusinessData.class, true);
        businessData.setTargerMoney(to.getVariousTargerMoney());
        businessData.setActualMoney(to.getVariousActualMoney());
        businessData.setDifference(to.getVariousTargerMoney() - to.getVariousActualMoney());
        BusinessDataDTO businessDataDTO = new BusinessDataDTO();
        businessDataDTO.getConditions().add(Restrict.eq("monthMoneyId", moneyMonthId));
        businessDataDTO.getConditions().add(Restrict.eq("businessType", businessData.getBusinessType()));
        List<BusinessData> businessDatas = businessDataSer.findByCis(businessDataDTO);
        if (null != businessDatas && businessDatas.size() > 0) {
            BusinessData businessData1 = businessDatas.get(0);
            BeanUtils.copyProperties(businessData, businessData1);
            businessData1.setModifyTime(LocalDateTime.now());
            businessDataSer.update(businessData1);
            businessTypeId = businessData1.getId();
        } else {
            businessData.setMonthMoneyId(moneyMonthId);
            businessData = businessDataSer.save(businessData);
            businessTypeId = businessData.getId();
        }

        //业务方向科目Id
        String subjectId = "";
        SubjectData subjectData = BeanTransform.copyProperties(to, SubjectData.class, true);

        SubjectDataDTO subjectDataDTO = new SubjectDataDTO();
        subjectDataDTO.getConditions().add(Restrict.eq("businessDataId", businessTypeId));
        subjectDataDTO.getConditions().add(Restrict.eq("subject", subjectData.getSubject()));
        List<SubjectData> subjectDatas = subjectDataSer.findByCis(subjectDataDTO);
        if (null != subjectDatas && subjectDatas.size() > 0) {
            SubjectData subjectData1 = subjectDatas.get(0);
            BeanTransform.copyProperties(subjectData, subjectData1);
            subjectData1.setModifyTime(LocalDateTime.now());
            subjectDataSer.update(subjectData1);
            subjectId = subjectData1.getId();
        } else {
            subjectData.setBusinessDataId(businessTypeId);
            subjectData = subjectDataSer.save(subjectData);
            subjectId = subjectData.getId();
        }

        MonthSubject monthSubject = BeanTransform.copyProperties(to, MonthSubject.class, true);
        MonthSubjectDTO monthSubjectDTO = new MonthSubjectDTO();
        monthSubjectDTO.getConditions().add(Restrict.eq("subjectDataId", subjectId));
        MonthSubject monthSubject1 = monthSubjectSer.findOne(monthSubjectDTO);

        if (null != monthSubject1) {
            monthSubjectSer.remove(monthSubject1);
        }
        monthSubject.setYearProportion(to.getWorkWeight() / 100 * to.getProportion() / 100 * 100);
        monthSubject.setDiffTotal(to.getPlanTotal() - to.getActualTotal());
        monthSubject.setSubjectDataId(subjectId);
        monthSubjectSer.save(monthSubject);
    }

    @Override
    public void update(MonthSubjectUpdateTO to) throws SerException {
        SubjectData subjectData = subjectDataSer.findById(to.getSubjectDataId());
        if (null == subjectData) {
            throw new SerException("目标数据不能为空");
        }
        MonthSubjectDTO monthSubjectDTO = new MonthSubjectDTO();
        monthSubjectDTO.getConditions().add(Restrict.eq("subjectDataId", to.getSubjectDataId()));
        List<MonthSubject> monthSubjects = monthSubjectSer.findByCis(monthSubjectDTO);
        if (null == monthSubjects || monthSubjects.size() < 1) {
            throw new SerException("目标数据不能为空");
        }
        MonthSubject monthSubject = monthSubjects.get(0);
        BeanUtils.copyProperties(to, monthSubject, "id");
        monthSubject.setModifyTime(LocalDateTime.now());
        monthSubject.setDiffTotal(monthSubject.getPlanTotal() - monthSubject.getActualTotal());

        BusinessData businessData = businessDataSer.findById(subjectData.getBusinessDataId());
        monthSubject.setYearProportion(businessData.getWorkWeight() / 100 * monthSubject.getProportion() / 100 * 100);
        monthSubjectSer.update(monthSubject);
    }

    @Override
    public void delete(String monthSubjectId) throws SerException {
        SubjectData subjectData = subjectDataSer.findById(monthSubjectId);
        if (null == subjectData) {
            throw new SerException("目标数据不能为空");
        }
        MonthSubjectDTO monthSubjectDTO = new MonthSubjectDTO();
        monthSubjectDTO.getConditions().add(Restrict.eq("subjectDataId", monthSubjectId));
        List<MonthSubject> monthSubjects = monthSubjectSer.findByCis(monthSubjectDTO);
        if (null == monthSubjects || monthSubjects.size() < 1) {
            throw new SerException("目标数据不能为空");
        }
        monthSubjectSer.remove(monthSubjects);
    }

    @Override
    public MonthSubjectBO getById(String monthSubjectId) throws SerException {
        SubjectData subjectData = subjectDataSer.findById(monthSubjectId);
        if (null == subjectData) {
            throw new SerException("目标数据不能为空");
        }
        MonthSubjectDTO monthSubjectDTO = new MonthSubjectDTO();
        monthSubjectDTO.getConditions().add(Restrict.eq("subjectDataId", monthSubjectId));
        List<MonthSubject> monthSubjects = monthSubjectSer.findByCis(monthSubjectDTO);
        if (null == monthSubjects || monthSubjects.size() < 1) {
            throw new SerException("目标数据不能为空");
        }
        MonthSubjectBO bo = BeanTransform.copyProperties(monthSubjects.get(0), MonthSubject.class);
        bo.setSubject(subjectData.getSubject());
        bo.setSubjectDataId(subjectData.getId());
        return bo;
    }

    @Override
    public Long getTotal(MonthSubjectDTO dto) throws SerException {
        MonthMoneyDTO monthMoneyDTO = BeanTransform.copyProperties(dto, MonthMoneyDTO.class);
        return monthMoneySer.count(monthMoneyDTO);
    }

    @Override
    public void congeal(String monthSubjectId) throws SerException {
        SubjectData subjectData = subjectDataSer.findById(monthSubjectId);
        if (null == subjectData) {
            throw new SerException("目标数据不能为空");
        }

        MonthSubjectDTO monthSubjectDTO = new MonthSubjectDTO();
        monthSubjectDTO.getConditions().add(Restrict.eq("subjectDataId", monthSubjectId));
        List<MonthSubject> monthSubjects = monthSubjectSer.findByCis(monthSubjectDTO);
        if (null == monthSubjects || monthSubjects.size() < 1) {
            throw new SerException("目标数据不能为空");
        }

        monthSubjects.get(0).setStatus(Status.CONGEAL);
        monthSubjects.get(0).setModifyTime(LocalDateTime.now());
        monthSubjectSer.update(monthSubjects.get(0));
    }

    @Override
    public void thaw(String monthSubjectId) throws SerException {
        SubjectData subjectData = subjectDataSer.findById(monthSubjectId);
        if (null == subjectData) {
            throw new SerException("目标数据不能为空");
        }

        MonthSubjectDTO monthSubjectDTO = new MonthSubjectDTO();
        monthSubjectDTO.getConditions().add(Restrict.eq("subjectDataId", monthSubjectId));
        List<MonthSubject> monthSubjects = monthSubjectSer.findByCis(monthSubjectDTO);
        if (null == monthSubjects || monthSubjects.size() < 1) {
            throw new SerException("目标数据不能为空");
        }

        monthSubjects.get(0).setStatus(Status.THAW);
        monthSubjects.get(0).setModifyTime(LocalDateTime.now());
        monthSubjectSer.update(monthSubjects.get(0));
    }
}