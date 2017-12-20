package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.*;
import com.bjike.goddess.marketdevelopment.dto.*;
import com.bjike.goddess.marketdevelopment.entity.*;
import com.bjike.goddess.marketdevelopment.enums.DateType;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<WeekMonthMoneyBO> maps(WeekCycleDTO dto) throws SerException {
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
        List<WeekMonthMoneyBO> weekMonthMoneyBOs = new ArrayList<>(0);
        if (null != monthMoneys && monthMoneys.size() > 0) {
            for (MonthMoney monthMoney : monthMoneys) {
                WeekMonthMoneyBO weekMonthMoneyBO = BeanTransform.copyProperties(monthMoney, WeekMonthMoneyBO.class, false);

                BusinessDataDTO businessDataDTO = new BusinessDataDTO();
                businessDataDTO.getConditions().add(Restrict.eq("monthMoneyId", weekMonthMoneyBO.getId()));
                List<BusinessData> businessDatas = businessDataSer.findByCis(businessDataDTO);
                if (null != businessDatas && businessDatas.size() > 0) {
                    List<WeekBusinessDataBO> weekBusinessDataBOs = new ArrayList<>();
                    for (BusinessData businessData : businessDatas) {
                        WeekBusinessDataBO weekBusinessDataBO = BeanTransform.copyProperties(businessData, WeekBusinessDataBO.class, false);
//                        weekBusinessDataBOs.add(weekBusinessDataBO);

                        SubjectDataDTO subjectDataDTO = new SubjectDataDTO();
                        subjectDataDTO.getConditions().add(Restrict.eq("businessDataId", weekBusinessDataBO.getId()));
                        List<SubjectData> subjectDatas = subjectDataSer.findByCis(subjectDataDTO);
                        if (null != subjectDatas && subjectDatas.size() > 0) {
                            List<WeekSubjectDataBO> weekSubjectDataBOs = new ArrayList<>(0);
                            for (SubjectData subjectData : subjectDatas) {
                                WeekSubjectDataBO weekSubjectDataBO = BeanTransform.copyProperties(subjectData, WeekSubjectDataBO.class, false);
//                                weekSubjectDataBOs.add(weekSubjectDataBO);

                                WeekCycleDTO weekCycleDTO = new WeekCycleDTO();
                                weekCycleDTO.getConditions().add(Restrict.eq("subjectDataId", weekSubjectDataBO.getId()));
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
                                        weekFilesBO1.setTableName("阶段");

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
                                }
                                weekSubjectDataBOs.add(weekSubjectDataBO);
                            }
                            weekBusinessDataBO.setWeekSubjectDataVOs(weekSubjectDataBOs);
                            weekBusinessDataBOs.add(weekBusinessDataBO);
                        }
                    }
                    weekMonthMoneyBO.setWeekBusinessDataVOs(weekBusinessDataBOs);
                    weekMonthMoneyBOs.add(weekMonthMoneyBO);
                }
            }
        }
        return weekMonthMoneyBOs;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(WeekCycleTO to) throws SerException {
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
            BeanTransform.copyProperties(businessData, businessData1, "id", "createTime");
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
            BeanTransform.copyProperties(subjectData, subjectData1, "id", "createTime");
            subjectData1.setModifyTime(LocalDateTime.now());
            subjectDataSer.update(subjectData1);
            subjectId = subjectData1.getId();
        } else {
            subjectData.setBusinessDataId(businessTypeId);
            subjectData = subjectDataSer.save(subjectData);
            subjectId = subjectData.getId();
        }

        //周期id
        String weekCycleId = "";
        if (null != to.getWeekCycleCopyTOs() && to.getWeekCycleCopyTOs().size() > 0) {
            for (WeekCycleCopyTO weekCycleCopyTO : to.getWeekCycleCopyTOs()) {
                Double total = 0d;
                WeekCycle weekCycle = BeanTransform.copyProperties(weekCycleCopyTO, WeekCycle.class, true);
                WeekCycleDTO weekCycleDTO = new WeekCycleDTO();
                weekCycleDTO.getConditions().add(Restrict.eq("subjectDataId", subjectId));
                weekCycleDTO.getConditions().add(Restrict.eq("cycle", weekCycleCopyTO.getCycle()));
                weekCycleDTO.getConditions().add(Restrict.eq("weekType", weekCycleCopyTO.getWeekType()));
                List<WeekCycle> weekCycles = weekCycleSer.findByCis(weekCycleDTO);
                if (null != weekCycles && weekCycles.size() > 0) {
                    WeekCycle weekCycle1 = weekCycles.get(0);
                    BeanTransform.copyProperties(weekCycle, weekCycle1, "id", "createTime");
                    weekCycle1.setModifyTime(LocalDateTime.now());
                    weekCycleSer.update(weekCycle1);
                    weekCycleId = weekCycle1.getId();
                    //周期存在,不保存数据
                    throw new SerException("该周期数据已存在");
                } else {
                    weekCycle.setSubjectDataId(subjectId);
                    weekCycle = weekCycleSer.save(weekCycle);
                    weekCycleId = weekCycle.getId();
//                    flag  = true;
                }


                //周期差异数据的调整
                WeekCycleDTO weekCycleDTO1 = new WeekCycleDTO();
                weekCycleDTO1.getConditions().add(Restrict.eq("cycle", weekCycleCopyTO.getCycle()));
                weekCycleDTO1.getConditions().add(Restrict.eq("subjectDataId", subjectId));
                weekCycleDTO1.getConditions().add(Restrict.eq("weekType", 2));
                List<WeekCycle> weekCycles1 = weekCycleSer.findByCis(weekCycleDTO1);

                WeekCycleDTO weekCycleDTO2 = new WeekCycleDTO();
                weekCycleDTO2.getConditions().add(Restrict.eq("cycle", weekCycleCopyTO.getCycle()));
                weekCycleDTO2.getConditions().add(Restrict.eq("subjectDataId", subjectId));
                weekCycleDTO2.getConditions().add(Restrict.eq("weekType", 3));
                List<WeekCycle> weekCycles2 = weekCycleSer.findByCis(weekCycleDTO2);

                if (null != weekCycles1 && weekCycles1.size() > 0 && null != weekCycles2 && weekCycles2.size() > 0) {
                    WeekCycle weekCycle1 = new WeekCycle();
                    weekCycle1.setSubjectDataId(subjectId);
                    weekCycle1.setCycle(weekCycleCopyTO.getCycle());
                    weekCycle1.setWeekType(DateType.DIFFERENCE);
                    // TODO: 17-12-6
//                    weekCycle1.setRadio();
                    weekCycle1 = weekCycleSer.save(weekCycle1);

                    WeekFilesDTO weekFilesDTO = new WeekFilesDTO();
                    weekFilesDTO.getConditions().add(Restrict.eq("weekCycleId", weekCycles1.get(0).getId()));
                    weekFilesDTO.getSorts().add("tableIndex=asc");
                    List<WeekFiles> weekFiles1 = weekFilesSer.findByCis(weekFilesDTO);

                    WeekFilesDTO weekFilesDTO1 = new WeekFilesDTO();
                    weekFilesDTO1.getConditions().add(Restrict.eq("weekCycleId", weekCycles2.get(0).getId()));
                    weekFilesDTO.getSorts().add("tableIndex=asc");
                    List<WeekFiles> weekFiles2 = weekFilesSer.findByCis(weekFilesDTO1);

                    if (null != weekFiles1 && weekFiles1.size() > 0 && null != weekFiles2 && weekFiles2.size() > 0) {
                        //先删除
                        WeekFilesDTO weekFilesDTO3 = new WeekFilesDTO();
                        weekFilesDTO3.getConditions().add(Restrict.eq("weekCycleId", weekCycle1.getId()));
                        weekFilesDTO.getSorts().add("tableIndex=asc");
                        List<WeekFiles> weekFiles3 = weekFilesSer.findByCis(weekFilesDTO1);
                        if (null != weekFiles3 && weekFiles3.size() > 0) {
                            weekFilesSer.remove(weekFiles3);
                        }

                        int num = 0;
                        for (WeekFiles weekFiles : weekFiles1) {
                            String comtext = weekFiles2.stream().filter(obj -> obj.getTableName().equals(weekFiles.getTableName())).map(WeekFiles::getContext).collect(Collectors.toList()).get(0);
                            WeekFiles weekFiles4 = new WeekFiles();
                            weekFiles4.setTableName(weekFiles.getTableName());
                            total += Double.valueOf(weekFiles.getContext()) - Double.valueOf(comtext);
                            weekFiles4.setContext(String.valueOf(Double.valueOf(weekFiles.getContext()) - Double.valueOf(comtext)));
                            weekFiles4.setTableIndex(num++);
                            weekFiles4.setWeekCycleId(weekCycle1.getId());
                            weekFilesSer.save(weekFiles4);
                        }
                    }
                }

                //表头数据
                if (null != weekCycleCopyTO.getWeekFilesTOs() && weekCycleCopyTO.getWeekFilesTOs().size() > 0) {
                    int i = 1;
                    for (WeekFilesTO weekFilesTO : weekCycleCopyTO.getWeekFilesTOs()) {
                        if (null == weekFilesTO.getTableIndex()) {
                            throw new SerException("表头下标不能为空");
                        }
                        if (StringUtils.isBlank(weekFilesTO.getTableName())) {
                            throw new SerException("表头不能为空");
                        }
                        if (StringUtils.isBlank(weekFilesTO.getContext())) {
                            throw new SerException("表头内容不能为空");
                        }
                        WeekFiles weekFiles = BeanTransform.copyProperties(weekFilesTO, WeekFiles.class, true);

                        WeekFilesDTO weekFilesDTO = new WeekFilesDTO();
                        weekFilesDTO.getConditions().add(Restrict.eq("weekCycleId", weekCycleId));
                        weekFilesDTO.getConditions().add(Restrict.eq("tableIndex", i));
                        WeekFiles weekFiles1 = weekFilesSer.findOne(weekFilesDTO);
                        if (null != weekFiles1) {
                            BeanTransform.copyProperties(weekFiles, weekFiles1, "id");
                            weekFiles1.setModifyTime(LocalDateTime.now());
                            weekFilesSer.update(weekFiles1);
                        } else {
                            weekFiles.setWeekCycleId(weekCycleId);

                            weekFiles = weekFilesSer.save(weekFiles);
                        }
                        i++;
                    }
                }

                //计算合计
                WeekTotalDTO weekTotalDTO = new WeekTotalDTO();
                weekTotalDTO.getConditions().add(Restrict.eq("weekCycleId", weekCycleId));
                List<WeekTotal> weekTotals = weekTotalSer.findByCis(weekTotalDTO);
                if (null != weekTotals && weekTotals.size() > 0) {
                    weekTotalSer.remove(weekTotals);
                }

                WeekCycleDTO weekCycleDTO3 = new WeekCycleDTO();
                weekCycleDTO3.getConditions().add(Restrict.eq("id", weekCycleId));
                WeekCycle weekCycle1 = weekCycleSer.findOne(weekCycleDTO3);

                WeekFilesDTO weekFilesDTO = new WeekFilesDTO();
                weekFilesDTO.getConditions().add(Restrict.eq("weekCycleId", weekCycleId));
                weekFilesDTO.getSorts().add("tableIndex=asc");
                List<WeekFiles> weekFiles = weekFilesSer.findByCis(weekFilesDTO);
                if (null != weekFiles && weekFiles.size() > 0) {
                    total = 0d;
                    if (DateType.NONE.equals(weekCycle1.getWeekType())) {
                        try {
                            for (WeekFiles week : weekFiles) {
                                total += Double.valueOf(week.getContext().substring(0, week.getContext().indexOf("%")));
                            }
                        } catch (Exception e) {
                            throw new SerException("同一个周期对应的阶段数据第一行填写的应为比例,如:40%");
                        }
                    } else {
                        try {
                            for (WeekFiles week : weekFiles) {
                                total += Double.valueOf(week.getContext());
                            }
                        } catch (Exception e) {
                            throw new SerException("同一个日期对应的阶段数据第二行至第三行填写的应为数字");
                        }
                    }
                }

                WeekTotal weekTotal = new WeekTotal();
                weekTotal.setWeekCycleId(weekCycleId);
                weekTotal.setTotal(total.toString());
                weekTotal.setRemark(weekCycleCopyTO.getRemark());
                weekTotalSer.save(weekTotal);

            }
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(WeekCycleUpdateTO to) throws SerException {
        Double total1 = 0d;
        WeekFilesDTO weekFilesDTO = new WeekFilesDTO();
        weekFilesDTO.getConditions().add(Restrict.eq("weekCycleId", to.getWeekCycleId()));
        weekFilesDTO.getSorts().add("tableIndex=asc");
        List<WeekFiles> weekFileses = weekFilesSer.findByCis(weekFilesDTO);
        if (null != weekFileses && weekFileses.size() > 0) {
            for (WeekFiles weekFiles : weekFileses) {
                for (WeekFilesTO weekFilesTO : to.getWeekFilesTOs()) {
                    if (StringUtils.isBlank(weekFilesTO.getTableName())) {
                        throw new SerException("表头字段不能为空");
                    }
                    if (null == weekFilesTO.getTableIndex()) {
                        throw new SerException("表头下表不能为空");
                    }
                    if (StringUtils.isBlank(weekFilesTO.getContext())) {
                        throw new SerException("表头内容不能为空");
                    }

                    if (weekFiles.getTableIndex().equals(weekFilesTO.getTableIndex())) {
                        BeanTransform.copyProperties(weekFilesTO, weekFiles, "id", "createTime");
                        weekFilesSer.update(weekFiles);

                        WeekCycleDTO weekCycleDTO = new WeekCycleDTO();
                        weekCycleDTO.getConditions().add(Restrict.eq("id", to.getWeekCycleId()));
                        List<WeekCycle> weekCycles = weekCycleSer.findByCis(weekCycleDTO);
                        if (null != weekCycles && weekCycles.size() > 0) {
                            if (DateType.NONE.equals(weekCycles.get(0).getWeekType())) {
                                try {
                                    total1 += Double.valueOf(weekFiles.getContext().substring(0, weekFiles.getContext().indexOf("%")));
                                } catch (Exception e) {
                                    throw new SerException("同一个周期对应的阶段数据第一行填写的应为比例,如:40%");
                                }
                            } else {
                                try {
                                    total1 += Double.valueOf(weekFiles.getContext());
                                } catch (Exception e) {
                                    throw new SerException("同一个日期对应的阶段数据第二行至第三行填写的应为数字");
                                }
                            }

                        }
                    }
                }
            }
        } else {
            throw new SerException("目标数据对象不能为空");
        }

        //修改差异
        Double total = 0d;
        String diffId = "";
        WeekCycleDTO weekCycleDTO = new WeekCycleDTO();
        weekCycleDTO.getConditions().add(Restrict.eq("id", to.getWeekCycleId()));
        WeekCycle weekCycle = weekCycleSer.findOne(weekCycleDTO);
        if (null == weekCycle) {
            throw new SerException("目标数据不能为空");
        }
        WeekCycleDTO weekCycleDTO1 = new WeekCycleDTO();
        weekCycleDTO1.getConditions().add(Restrict.eq("cycle", weekCycle.getCycle()));
        weekCycleDTO1.getConditions().add(Restrict.eq("subjectDataId", weekCycle.getSubjectDataId()));
        List<WeekCycle> weekCycles = weekCycleSer.findByCis(weekCycleDTO);
        List<WeekCycle> weekCycles1 = weekCycles.stream().filter(obj -> DateType.PLAN.equals(obj.getWeekType())).collect(Collectors.toList());
        List<WeekCycle> weekCycles2 = weekCycles.stream().filter(obj -> DateType.ATCUAL.equals(obj.getWeekType())).collect(Collectors.toList());
        List<WeekCycle> weekCycles3 = weekCycles.stream().filter(obj -> DateType.DIFFERENCE.equals(obj.getWeekType())).collect(Collectors.toList());
        if (null != weekCycles3 && weekCycles3.size() > 0) {
            diffId = weekCycles3.get(0).getId();
        } else {
            WeekCycle weekCycle1 = new WeekCycle();
            weekCycle1.setSubjectDataId(weekCycle.getSubjectDataId());
            // TODO: 17-12-6
//            weekCycle1.setRadio();
            weekCycle1.setWeekType(DateType.DIFFERENCE);
            weekCycle1.setCycle(weekCycles.get(0).getCycle());
            weekCycle1 = weekCycleSer.save(weekCycle1);
            diffId = weekCycle1.getId();
        }

        if (null != weekCycles1 && weekCycles1.size() > 0 && null != weekCycles2 && weekCycles2.size() > 0) {
            WeekFilesDTO weekFilesDTO1 = new WeekFilesDTO();
            weekFilesDTO1.getConditions().add(Restrict.eq("weekCycleId", weekCycles1.get(0).getId()));
            weekFilesDTO1.getSorts().add("tableIndex=asc");
            List<WeekFiles> weekFiles = weekFilesSer.findByCis(weekFilesDTO1);

            WeekFilesDTO weekFilesDTO2 = new WeekFilesDTO();
            weekFilesDTO2.getConditions().add(Restrict.eq("weekCycleId", weekCycles2.get(0).getId()));
            weekFilesDTO2.getSorts().add("tableIndex=asc");
            List<WeekFiles> weekFiles1 = weekFilesSer.findByCis(weekFilesDTO2);

            if (null != weekFiles && weekFiles.size() > 0 && null != weekFiles && weekFiles1.size() > 0) {
                int num = 1;
                for (WeekFiles weekFiles2 : weekFiles) {
                    List<String> context = weekFiles1.stream().filter(obj -> weekFiles2.getTableName().equals(obj.getTableName())).map(WeekFiles::getContext).collect(Collectors.toList());
                    total += Double.valueOf(context.get(0));
                    WeekFiles weekFiles3 = new WeekFiles();
                    weekFiles3.setTableName(weekFiles2.getTableName());
                    weekFiles3.setTableIndex(num++);
                    weekFiles3.setWeekCycleId(diffId);
                    weekFiles3.setContext(String.valueOf(Double.valueOf(weekFiles2.getContext()) - Double.valueOf(context.get(0))));
                    weekFilesSer.save(weekFiles3);
                }
            }
        }

        //计算合计
        WeekTotalDTO weekTotalDTO = new WeekTotalDTO();
        weekTotalDTO.getConditions().add(Restrict.eq("weekCycleId", diffId));
        List<WeekTotal> weekTotals = weekTotalSer.findByCis(weekTotalDTO);
        if (null != weekTotals && weekTotals.size() > 0) {
            WeekTotal weekTotal = weekTotals.get(0);
            weekTotal.setTotal(total1.toString());
            weekTotalSer.update(weekTotals);
        }

        WeekTotalDTO weekTotalDTO1 = new WeekTotalDTO();
        weekCycleDTO1.getConditions().add(Restrict.eq("weekCycleId", to.getWeekCycleId()));
        List<WeekTotal> weekTotals1 = weekTotalSer.findByCis(weekTotalDTO1);
        if (null != weekTotals1 && weekTotals1.size() > 0) {
            WeekTotal weekTotal = weekTotals1.get(0);
            weekTotal.setTotal(total.toString());
            weekTotalSer.update(weekTotals);
        }
    }

    @Override
    public void delete(String weekCycleId) throws SerException {
        WeekCycleDTO weekCycleDTO = new WeekCycleDTO();
        weekCycleDTO.getConditions().add(Restrict.eq("id", weekCycleId));
        WeekCycle weekCycle = weekCycleSer.findOne(weekCycleDTO);
        if (null == weekCycle) {
            throw new SerException("目标数据对象不能为空");
        }
        //删除差异
        WeekCycleDTO weekCycleDTO1 = new WeekCycleDTO();
        weekCycleDTO1.getConditions().add(Restrict.eq("subjectDataId", weekCycle.getSubjectDataId()));
        weekCycleDTO1.getConditions().add(Restrict.eq("cycle", weekCycle.getCycle()));
        weekCycleDTO1.getConditions().add(Restrict.eq("weekType", DateType.DIFFERENCE));
        List<WeekCycle> weekCycles = weekCycleSer.findByCis(weekCycleDTO1);
        if (null != weekCycles && weekCycles.size() > 0) {
            String diffId = weekCycles.get(0).getId();
            WeekFilesDTO weekFilesDTO = new WeekFilesDTO();
            weekFilesDTO.getConditions().add(Restrict.eq("weekCycleId", diffId));
            List<WeekFiles> weekFiles = weekFilesSer.findByCis(weekFilesDTO);
            if (null != weekFiles && weekFiles.size() > 0) {
                weekFilesSer.remove(weekFiles);
            }
        }

        WeekFilesDTO weekFilesDTO = new WeekFilesDTO();
        weekFilesDTO.getConditions().add(Restrict.eq("weekCycleId", weekCycleId));
        List<WeekFiles> weekFiles = weekFilesSer.findByCis(weekFilesDTO);
        if (null != weekFiles && weekFiles.size() > 0) {
            weekFilesSer.remove(weekFiles);
        }

        WeekTotalDTO weekTotalDTO = new WeekTotalDTO();
        weekTotalDTO.getConditions().add(Restrict.eq("weekCycleId", weekCycleId));
        List<WeekTotal> weekTotals = weekTotalSer.findByCis(weekTotalDTO);
        if (null != weekTotals && weekTotals.size() > 0) {
            weekTotalSer.remove(weekTotals);
        }

        super.remove(weekCycle);
    }

    @Override
    public List<WeekCycleBO> getById(String weekCycleId) throws SerException {
        WeekCycle weekCycle = weekCycleSer.findById(weekCycleId);
        if (null == weekCycle) {
            throw new SerException("目标数据不能为空");
        }
        WeekCycleBO weekCycleBO = BeanTransform.copyProperties(weekCycle, WeekCycleBO.class, false);
        WeekFilesDTO weekFilesDTO = new WeekFilesDTO();
        weekFilesDTO.getConditions().add(Restrict.eq("weekCycleId", weekCycleId));
        List<WeekFiles> weekFiles = weekFilesSer.findByCis(weekFilesDTO);
        WeekFilesBO weekFilesBO = new WeekFilesBO();
        weekFilesBO.setTableName("阶段");
        List<WeekFilesBO> weekFilesBOs = new ArrayList<>(0);
        if (null != weekFiles && weekFiles.size() > 0) {
            for (WeekFiles weekFiles1 : weekFiles) {
                WeekFilesBO bo = BeanTransform.copyProperties(weekFiles1, WeekFilesBO.class, false);
                weekFilesBOs.add(bo);
            }
        }
        weekFilesBO.setWeekFilesVOs(weekFilesBOs);
        weekCycleBO.setWeekFilesVO(weekFilesBO);
        List<WeekCycleBO> weekCycleBOList = new ArrayList<>(0);
        weekCycleBOList.add(weekCycleBO);
        return weekCycleBOList;
    }

    @Override
    public Long getTotal(WeekCycleDTO dto) throws SerException {
        MonthMoneyDTO monthMoneyDTO = BeanTransform.copyProperties(dto, MonthMoneyDTO.class);
        return monthMoneySer.count(monthMoneyDTO);
    }

    @Override
    public List<String> getCycle(String year, String month) throws SerException {
        List<String> list = new ArrayList<>(0);
        try {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, Integer.valueOf(year)); // 2010年
            c.set(Calendar.MONTH, Integer.valueOf(month) - 1); // 6 月
            int cycle = c.getActualMaximum(Calendar.WEEK_OF_MONTH);

            for (int i = 1; i <= cycle; i++) {
                list.add("第" + i + "周");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    private String

    public static void main(String[] sadfd) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Calendar cl = Calendar.getInstance();
//            cl.setTime(sdf.parse("2017-12-01"));
//            int week = cl.get(Calendar.WEEK_OF_MONTH);
//            System.out.println(week);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2017); // 2010年
        c.set(Calendar.MONTH, 11); // 6 月
        System.out.println("周数：" + c.getActualMaximum(Calendar.WEEK_OF_MONTH));
    }
}