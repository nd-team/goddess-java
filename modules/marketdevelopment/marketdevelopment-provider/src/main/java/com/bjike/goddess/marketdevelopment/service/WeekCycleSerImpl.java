package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.*;
import com.bjike.goddess.marketdevelopment.dto.*;
import com.bjike.goddess.marketdevelopment.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 周计划的周期业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:37 ]
 * @Description: [ 周计划的周期业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class WeekCycleSerImpl extends ServiceImpl<WeekCycle, WeekCycleDTO> implements WeekCycleSer {
    @Autowired
    private MonthMoneySer monthMoneySer;
    @Autowired
    private BusinessDataSer businessDataSer;
    @Autowired
    private SubjectDataSer subjectDataSer;
    @Autowired
    private WeekCycleSer weekCycleSer;
    @Autowired
    private WeekFilesSer weekFilesSer;
    @Autowired
    private WeekTotalSer weekTotalSer;
    @Autowired
    private FilesDataTotalSer filesDataTotalSer;

    @Override
    public List<WeekMonthMoneyBO> maps(WeekCycleDTO dto) throws SerException {
        String year = LocalDate.now().getYear() + "年";
        String month = LocalDate.now().getMonthValue() + "月";
        if (StringUtils.isNotBlank(dto.getYear()) && StringUtils.isNotBlank(dto.getMonth())) {
            year = dto.getYear();
            month = dto.getMonth();
        }

        MonthMoneyDTO monthMoneyDTO = new MonthMoneyDTO();
        monthMoneyDTO.getConditions().add(Restrict.eq("year", year));
        monthMoneyDTO.getConditions().add(Restrict.eq("month", month));
        List<MonthMoney> monthMoneys = monthMoneySer.findByCis(monthMoneyDTO);
        List<WeekMonthMoneyBO> weekMonthMoneyBOs = new ArrayList<>(0);
        WeekMonthMoneyBO weekMonthMoneyBO = new WeekMonthMoneyBO();
        if (null != monthMoneys && monthMoneys.size() > 0) {
            BeanTransform.copyProperties(monthMoneys.get(0), weekMonthMoneyBO, false);
        } else {
            return null;
        }

        BusinessDataDTO businessDataDTO = new BusinessDataDTO();
        businessDataDTO.getConditions().add(Restrict.eq("monthMoneyId", weekMonthMoneyBO.getId()));
        List<BusinessData> businessDatas = businessDataSer.findByCis(businessDataDTO);
        if (null != businessDatas && businessDatas.size() > 0) {
            List<WeekBusinessDataBO> weekBusinessDataBOs = new ArrayList<>();
            for (BusinessData businessData : businessDatas) {
                WeekBusinessDataBO weekBusinessDataBO = BeanTransform.copyProperties(businessData, WeekBusinessDataBO.class, false);
                weekBusinessDataBOs.add(weekBusinessDataBO);

                SubjectDataDTO subjectDataDTO = new SubjectDataDTO();
                subjectDataDTO.getConditions().add(Restrict.eq("businessDataId", weekBusinessDataBO.getId()));
                List<SubjectData> subjectDatas = subjectDataSer.findByCis(subjectDataDTO);
                if (null != subjectDatas && subjectDatas.size() > 0) {
                    List<WeekSubjectDataBO> weekSubjectDataBOs = new ArrayList<>(0);
                    for (SubjectData subjectData : subjectDatas) {
                        WeekSubjectDataBO weekSubjectDataBO = BeanTransform.copyProperties(subjectData, WeekSubjectDataBO.class, false);
                        weekSubjectDataBOs.add(weekSubjectDataBO);

                        WeekCycleDTO weekCycleDTO = new WeekCycleDTO();
                        weekCycleDTO.getConditions().add(Restrict.eq("subjectDataId", weekBusinessDataBO.getId()));
                        List<WeekCycle> weekCycles = weekCycleSer.findByCis(weekCycleDTO);
                        if (null != weekCycles && weekCycles.size() > 0) {
                            List<WeekCycleBO> weekCycleBOs = new ArrayList<>(0);
                            for (WeekCycle weekCycle : weekCycles) {
                                WeekCycleBO weekCycleBO = BeanTransform.copyProperties(weekCycle, WeekCycleBO.class);

                                WeekTotalDTO weekTotalDTO = new WeekTotalDTO();
                                weekTotalDTO.getConditions().add(Restrict.eq("weekCycleId", weekCycleBO.getId()));
                                WeekTotal weekTotal = weekTotalSer.findOne(weekTotalDTO);
                                String[] total = new String[2];
                                if (null != weekTotal) {
                                    total[0] = weekTotal.getTotal();
                                    total[1] = weekTotal.getRemark();
                                }

                                WeekFilesDTO weekFilesDTO = new WeekFilesDTO();
                                weekFilesDTO.getConditions().add(Restrict.eq("weekCycleId", weekCycleBO.getId()));
                                List<WeekFiles> weekFiles = weekFilesSer.findByCis(weekFilesDTO);

                                WeekFilesBO weekFilesBO1 = new WeekFilesBO();
                                weekFilesBO1.setTable("阶段");

                                if (null != weekFiles && weekFiles.size() > 0) {
                                    List<WeekFilesBO> weekFilesBOs = new ArrayList<>(0);
                                    for (WeekFiles weekFiles1 : weekFiles) {
                                        WeekFilesBO weekFilesBO = BeanTransform.copyProperties(weekFiles1, WeekFilesBO.class, false);
                                        weekFilesBOs.add(weekFilesBO);
                                    }
                                    weekFilesBO1.setWeekFilesVOs(weekFilesBOs);
                                }
                                weekCycleBO.setWeekFilesVO(weekFilesBO1);
                                weekCycleBO.setTotal(total);
                                weekCycleBOs.add(weekCycleBO);
                            }
                            weekSubjectDataBO.setWeekCycleVOs(weekCycleBOs);
                            weekSubjectDataBOs.add(weekSubjectDataBO);
                        }
                    }
                    weekBusinessDataBO.setWeekSubjectDataVOs(weekSubjectDataBOs);
                    weekBusinessDataBOs.add(weekBusinessDataBO);
                }
            }
            weekMonthMoneyBO.setWeekBusinessDataVOs(weekBusinessDataBOs);
            weekMonthMoneyBOs.add(weekMonthMoneyBO);
        }
        return weekMonthMoneyBOs;
    }
}