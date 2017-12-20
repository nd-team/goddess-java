package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businessproject.api.BusinessContractAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.*;
import com.bjike.goddess.marketdevelopment.dto.BusinessDataDTO;
import com.bjike.goddess.marketdevelopment.dto.MonthMoneyDTO;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectAreaDTO;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectProvincialDTO;
import com.bjike.goddess.marketdevelopment.entity.BusinessData;
import com.bjike.goddess.marketdevelopment.entity.MonthMoney;
import com.bjike.goddess.marketdevelopment.entity.MonthSubjectArea;
import com.bjike.goddess.marketdevelopment.entity.MonthSubjectProvincial;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 月计划省市方向业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:29 ]
 * @Description: [ 月计划省市方向业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class MonthSubjectProvincialSerImpl extends ServiceImpl<MonthSubjectProvincial, MonthSubjectProvincialDTO> implements MonthSubjectProvincialSer {
    @Autowired
    private MonthMoneySer monthMoneySer;
    @Autowired
    private BusinessDataSer businessDataSer;
    @Autowired
    private MonthSubjectAreaSer monthSubjectAreaSer;
    @Autowired
    private MonthSubjectSer monthSubjectSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private BusinessContractAPI businessContractAPI;

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
    public List<MonthMoneyProvincialBO> maps(MonthSubjectProvincialDTO dto) throws SerException {
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
        List<MonthMoneyProvincialBO> monthMoneyProvincialBOs = new ArrayList<>(0);
        MonthMoneyProvincialBO monthMoneyProvincialBO = new MonthMoneyProvincialBO();
        if (null != monthMoneys && monthMoneys.size() > 0) {
            BeanTransform.copyProperties(monthMoneys.get(0), monthMoneyProvincialBO, false);
        } else {
            return null;
        }

        BusinessDataDTO businessDataDTO = new BusinessDataDTO();
        businessDataDTO.getConditions().add(Restrict.eq("monthMoneyId", monthMoneyProvincialBO.getId()));
        List<BusinessData> businessDatas = businessDataSer.findByCis(businessDataDTO);
        List<BusinessDataProvincialBO> businessDataProvincialBOs = new ArrayList<>();
        if (null != businessDatas && businessDatas.size() > 0) {
            for (BusinessData businessData : businessDatas) {
                BusinessDataProvincialBO businessDataProvincialBO = BeanTransform.copyProperties(businessData, BusinessDataProvincialBO.class, false);

                MonthSubjectProvincialDTO monthSubjectProvincialDTO = new MonthSubjectProvincialDTO();
                monthSubjectProvincialDTO.getConditions().add(Restrict.eq("businessDataId", businessDataProvincialBO.getId()));
                List<MonthSubjectProvincial> monthSubjectProvincials = super.findByCis(monthSubjectProvincialDTO);
                List<MonthSubjectProvincialBO> monthSubjectProvincialBOs = new ArrayList<>(0);
                if (null != monthSubjectProvincials && monthSubjectProvincials.size() > 0) {
                    for (MonthSubjectProvincial monthSubjectProvincial : monthSubjectProvincials) {
                        MonthSubjectProvincialBO monthSubjectProvincialBO = BeanTransform.copyProperties(monthSubjectProvincial, MonthSubjectProvincialBO.class, false);

                        MonthSubjectAreaDTO monthSubjectAreaDTO = new MonthSubjectAreaDTO();
                        monthSubjectAreaDTO.getConditions().add(Restrict.eq("provincialId", monthSubjectProvincialBO.getId()));
                        List<MonthSubjectArea> monthSubjectAreas = monthSubjectAreaSer.findByCis(monthSubjectAreaDTO);
                        List<MonthSubjectAreaBO> monthSubjectAreaBOs = new ArrayList<>(0);
                        if (null != monthSubjectAreas && monthSubjectAreas.size() > 0) {
                            for (MonthSubjectArea monthSubjectArea : monthSubjectAreas) {
                                MonthSubjectAreaBO monthSubjectAreaBO = BeanTransform.copyProperties(monthSubjectArea, MonthSubjectAreaBO.class, false);
                                monthSubjectAreaBOs.add(monthSubjectAreaBO);
                            }
                        }
                        monthSubjectProvincialBO.setMonthSubjectAreaVOs(monthSubjectAreaBOs);
                        monthSubjectProvincialBOs.add(monthSubjectProvincialBO);
                    }
                }
                businessDataProvincialBO.setMonthSubjectProvincialVOs(monthSubjectProvincialBOs);
                businessDataProvincialBOs.add(businessDataProvincialBO);
            }
        }
        monthMoneyProvincialBO.setBusinessDataProvincialVOs(businessDataProvincialBOs);
        monthMoneyProvincialBOs.add(monthMoneyProvincialBO);
        return monthMoneyProvincialBOs;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(MonthSubjectProvincialADDTO to) throws SerException {
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

        //省市分类
        String monthSubjectProvincialId = "";
        List<MonthSubjectProvincialTO> monthSubjectProvincialTOs = to.getMonthSubjectProvincialTOs();
        if (null == monthSubjectProvincialTOs || monthSubjectProvincialTOs.size() < 1) {
            throw new SerException("省市分类数据不能为空");
        }
        for (MonthSubjectProvincialTO monthSubjectProvincialTO : monthSubjectProvincialTOs) {
            MonthSubjectProvincial monthSubjectProvincial = BeanTransform.copyProperties(monthSubjectProvincialTO, MonthSubjectProvincial.class, true);
            MonthSubjectProvincialDTO monthSubjectProvincialDTO = new MonthSubjectProvincialDTO();
            monthSubjectProvincialDTO.getConditions().add(Restrict.eq("businessDataId", businessTypeId));
            monthSubjectProvincialDTO.getConditions().add(Restrict.eq("provincial", monthSubjectProvincialTO.getProvincial()));
            List<MonthSubjectProvincial> monthSubjectProvincials = super.findByCis(monthSubjectProvincialDTO);
            if (null != monthSubjectProvincials && monthSubjectProvincials.size() > 0) {
                MonthSubjectProvincial monthSubjectProvincial1 = monthSubjectProvincials.get(0);
                BeanUtils.copyProperties(monthSubjectProvincial, monthSubjectProvincial1, "id");
                monthSubjectProvincial1.setModifyTime(LocalDateTime.now());
                super.update(monthSubjectProvincial1);
                monthSubjectProvincialId = monthSubjectProvincial1.getId();
            } else {
                monthSubjectProvincial.setBusinessDataId(businessTypeId);
                monthSubjectProvincial = super.save(monthSubjectProvincial);
                monthSubjectProvincialId = monthSubjectProvincial.getId();
            }

            List<MonthSubjectAreaTO> monthSubjectAreaTOs = monthSubjectProvincialTO.getMonthSubjectAreaTOs();
            if (null == monthSubjectAreaTOs || monthSubjectAreaTOs.size() < 1) {
                throw new SerException("输入的各地区分类不能为空");
            }
            for (MonthSubjectAreaTO monthSubjectAreaTO : monthSubjectAreaTOs) {
                MonthSubjectArea monthSubjectArea = BeanTransform.copyProperties(monthSubjectAreaTO, MonthSubjectArea.class, true);
                MonthSubjectAreaDTO monthSubjectAreaDTO = new MonthSubjectAreaDTO();
                monthSubjectAreaDTO.getConditions().add(Restrict.eq("provincialId", monthSubjectProvincialId));
                monthSubjectAreaDTO.getConditions().add(Restrict.eq("areaClassify", monthSubjectAreaTO.getAreaClassify()));
                List<MonthSubjectArea> monthSubjectAreas = monthSubjectAreaSer.findByCis(monthSubjectAreaDTO);
                if (null != monthSubjectAreas && monthSubjectAreas.size() > 0) {
                    MonthSubjectArea monthSubjectArea1 = monthSubjectAreas.get(0);
                    BeanUtils.copyProperties(monthSubjectArea, monthSubjectArea1, "id");
                    monthSubjectArea1.setModifyTime(LocalDateTime.now());
                    monthSubjectAreaSer.update(monthSubjectArea1);
                } else {
                    monthSubjectArea.setProvincialId(monthSubjectProvincialId);
                    monthSubjectAreaSer.save(monthSubjectArea);
                }
            }
        }
    }

    @Override
    public void update(MonthSubjectProvincialUpdateTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }

        MonthMoney monthMoney = monthMoneySer.findById(to.getId());
        if (null == monthMoney) {
            throw new SerException("目标数据不能为空");
        }
        BeanUtils.copyProperties(to, monthMoney, "id");
        monthMoney.setDiferenceMoney(monthMoney.getTargetMoney() - monthMoney.getActualMoney());
        monthMoney.setModifyTime(LocalDateTime.now());
        monthMoneySer.update(monthMoney);

        BusinessData businessData = businessDataSer.findById(to.getBusinessDataId());
        if (null != businessData) {
            BeanUtils.copyProperties(to, businessData, "id");
            businessData.setActualMoney(to.getVariousActualMoney());
            businessData.setTargerMoney(to.getVariousTargerMoney());
            businessData.setDifference(businessData.getTargerMoney() - businessData.getActualMoney());
            businessData.setModifyTime(LocalDateTime.now());
            businessDataSer.update(businessData);
        }

        MonthSubjectProvincial entity = super.findById(to.getProvincialId());
        if (null != entity) {
            BeanUtils.copyProperties(to, entity, "id");
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        }

        MonthSubjectArea monthSubjectArea = monthSubjectAreaSer.findById(to.getProvincialAreaId());
        if (null != monthSubjectArea) {
            BeanUtils.copyProperties(to, monthSubjectArea, "id");
            monthSubjectArea.setModifyTime(LocalDateTime.now());
            monthSubjectAreaSer.update(monthSubjectArea);
        }

    }

    @Override
    public void delete(String provincialAreaId) throws SerException {
        MonthSubjectArea monthSubjectArea = monthSubjectAreaSer.findById(provincialAreaId);
        if (null == monthSubjectArea) {
            throw new SerException("目标数据不能为空");
        }
        monthSubjectAreaSer.remove(monthSubjectArea);
    }

    @Override
    public Long getTotal(MonthSubjectProvincialDTO dto) throws SerException {
        MonthMoneyDTO monthMoneyDTO = BeanTransform.copyProperties(dto, MonthMoneyDTO.class);
        return monthMoneySer.count(monthMoneyDTO);
    }

    @Override
    public MonthSubjectProvincialUpdateBO getById(String provincialAreaId) throws SerException {
        MonthSubjectArea monthSubjectArea = monthSubjectAreaSer.findById(provincialAreaId);
        if (null == monthSubjectArea) {
            throw new SerException("目标数据不能为空");
        }
        MonthSubjectProvincialUpdateBO monthSubjectProvincialUpdateBO = new MonthSubjectProvincialUpdateBO();
        BeanTransform.copyProperties(monthSubjectArea, monthSubjectProvincialUpdateBO);
        MonthSubjectProvincial monthSubjectProvincial = super.findById(monthSubjectArea.getProvincialId());
        BeanTransform.copyProperties(monthSubjectProvincial, monthSubjectProvincialUpdateBO);
        BusinessData businessData = businessDataSer.findById(monthSubjectProvincial.getBusinessDataId());
        BeanTransform.copyProperties(businessData, monthSubjectProvincialUpdateBO);
        MonthMoney monthMoney = monthMoneySer.findById(businessData.getMonthMoneyId());
        BeanTransform.copyProperties(monthMoney, monthSubjectProvincialUpdateBO);
        return monthSubjectProvincialUpdateBO;
    }

    @Override
    public void congeal(String monthmoneyId) throws SerException {
        MonthMoney monthMoney = monthMoneySer.findById(monthmoneyId);
        if (null == monthMoney) {
            throw new SerException("目标数据对象不能为空");
        }
        monthMoney.setStatus(Status.CONGEAL);
    }

    @Override
    public void thaw(String monthmoneyId) throws SerException {
        MonthMoney monthMoney = monthMoneySer.findById(monthmoneyId);
        if (null == monthMoney) {
            throw new SerException("目标数据对象不能为空");
        }
        monthMoney.setStatus(Status.THAW);
    }

    @Override
    public Double findActualMoney(String year, String month) throws SerException {
//        if(moduleAPI.isCheck("businessproject")){
        String time = "";
        try {
//            year = year.substring(0, year.indexOf("年"));
//            month = month.substring(0, month.indexOf("月"));
            time = year + "-" + month;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return businessContractAPI.findMakeMoney(time);

//        }
    }

    @Override
    public Double findActualMoney1(String year, String month, String businessType) throws SerException {
        //        if(moduleAPI.isCheck("businessproject")){
        String time = "";
        try {
//            year = year.substring(0, year.indexOf("年"));
//            month = month.substring(0, month.indexOf("月"));
            time = year + "-" + month;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return businessContractAPI.findMakeMoney(time, businessType);

//        }
    }
}