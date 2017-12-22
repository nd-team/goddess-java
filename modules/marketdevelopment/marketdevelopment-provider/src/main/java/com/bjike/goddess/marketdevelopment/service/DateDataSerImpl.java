package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.marketdevelopment.bo.*;
import com.bjike.goddess.marketdevelopment.dto.*;
import com.bjike.goddess.marketdevelopment.entity.*;
import com.bjike.goddess.marketdevelopment.enums.DateType;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日期数据业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class DateDataSerImpl extends ServiceImpl<DateData, DateDataDTO> implements DateDataSer {

    @Autowired
    private MonthMoneySer monthMoneySer;
    @Autowired
    private BusinessDataSer businessDataSer;
    @Autowired
    private SubjectDataSer subjectDataSer;
    @Autowired
    private CycleSer cycleSer;
    @Autowired
    private DateDataSer dateDataSer;
    @Autowired
    private FilesDataSer filesDataSer;
    @Autowired
    private FilesDataTotalSer filesDataTotalSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";


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

    @Override
    public List<MonthMoneyBO> maps(DateDataDTO dto) throws SerException {
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
        List<MonthMoneyBO> monthMoneyBOs = new ArrayList<>(0);
        MonthMoneyBO monthMoneyBO = new MonthMoneyBO();
        if (null != monthMoneys && monthMoneys.size() > 0) {
            BeanTransform.copyProperties(monthMoneys.get(0), monthMoneyBO, false);
        } else {
            return null;
        }

        BusinessDataDTO businessDataDTO = new BusinessDataDTO();
        businessDataDTO.getConditions().add(Restrict.eq("monthMoneyId", monthMoneyBO.getId()));
        List<BusinessData> businessDatas = businessDataSer.findByCis(businessDataDTO);
        List<BusinessDataBO> businessDataBOs = new ArrayList<>(0);
        if (null != businessDatas && businessDatas.size() > 0) {
            for (BusinessData businessData : businessDatas) {
                BusinessDataBO businessDataBO = BeanTransform.copyProperties(businessData, BusinessDataBO.class, false);
                SubjectDataDTO subjectDataDTO = new SubjectDataDTO();
                subjectDataDTO.getConditions().add(Restrict.eq("businessDataId", businessDataBO.getId()));
                List<SubjectData> subjectDatas = subjectDataSer.findByCis(subjectDataDTO);
                List<SubjectDataBO> subjectDataBOs = new ArrayList<>();
                if (null != subjectDatas && subjectDatas.size() > 0) {
                    for (SubjectData subjectData : subjectDatas) {
                        SubjectDataBO subjectDataBO = BeanTransform.copyProperties(subjectData, SubjectDataBO.class, false);
                        CycleDTO cycleDTO = new CycleDTO();
                        cycleDTO.getConditions().add(Restrict.eq("subjectDataId", subjectDataBO.getId()));
                        List<Cycle> cycles = cycleSer.findByCis(cycleDTO);

                        List<CycleBO> cycleBOs = new ArrayList<>(0);
                        if (null != cycles && cycles.size() > 0) {
                            for (Cycle cycle : cycles) {
                                CycleBO cycleBO = BeanTransform.copyProperties(cycle, CycleBO.class, false);
                                DateDataDTO dateDataDTO = new DateDataDTO();
                                dateDataDTO.getConditions().add(Restrict.eq("cycleId", cycleBO.getId()));
                                List<DateData> dateDatas = dateDataSer.findByCis(dateDataDTO);

                                List<DateDataBO> dataBOs = new ArrayList<>(0);
                                if (null != dateDatas && dateDatas.size() > 0) {
                                    for (DateData dateData : dateDatas) {
                                        DateDataBO dateDataBO = BeanTransform.copyProperties(dateData, DateDataBO.class, false);
                                        FilesDataDTO filesDataDTO = new FilesDataDTO();
                                        filesDataDTO.getConditions().add(Restrict.eq("dateDataId", dateDataBO.getId()));
                                        List<FilesData> filesDatas = filesDataSer.findByCis(filesDataDTO);

                                        FilesDataBO filesDataBO = new FilesDataBO();
                                        filesDataBO.setTableName("阶段");
                                        List<FilesDataBO> filesDataBOs = new ArrayList<>(0);
                                        for (FilesData filesData : filesDatas) {
                                            FilesDataBO filesDataBO1 = BeanTransform.copyProperties(filesData, FilesDataBO.class);
                                            filesDataBO.getFilesDataVO1s().add(filesDataBO1);
//                                            filesDataBOs.add(filesDataBO1);
                                        }
                                        //获取合计
                                        FilesDataTotalDTO filesDataTotalDTO = new FilesDataTotalDTO();
                                        filesDataTotalDTO.getConditions().add(Restrict.eq("dateDataId", dateData.getId()));
                                        FilesDataTotal filesDataTotal = filesDataTotalSer.findOne(filesDataTotalDTO);

                                        dateDataBO.setTotal(filesDataTotal.getTotal());
                                        dateDataBO.setFilesDataVO(filesDataBO);
                                        dataBOs.add(dateDataBO);
                                    }
                                }
                                cycleBO.setDateDataVOs(dataBOs);
                                cycleBOs.add(cycleBO);
                            }
                        }
                        subjectDataBO.setCycleVOs(cycleBOs);
                        subjectDataBOs.add(subjectDataBO);
                    }
                }
                businessDataBO.setSubjectDataVOs(subjectDataBOs);
                businessDataBOs.add(businessDataBO);
            }
        }
        monthMoneyBO.setBusinessDataVOs(businessDataBOs);
        monthMoneyBOs.add(monthMoneyBO);
        return monthMoneyBOs;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(DateDataTO to) throws SerException {
        //月份金额表的id
        String moneyMonthId = "";
        MonthMoney monthMoney = BeanTransform.copyProperties(to, MonthMoney.class, true);
        MonthMoneyDTO monthMoneyDTO = new MonthMoneyDTO();
        monthMoneyDTO.getConditions().add(Restrict.eq("year", monthMoney.getYear()));
        monthMoneyDTO.getConditions().add(Restrict.eq("month", monthMoney.getMonth()));
        List<MonthMoney> monthMoneys = monthMoneySer.findByCis(monthMoneyDTO);
        if (null != monthMoneys && monthMoneys.size() > 0) {
            MonthMoney monthMoney1 = monthMoneys.get(0);
            BeanUtils.copyProperties(monthMoney, monthMoney1);
            monthMoney1.setModifyTime(LocalDateTime.now());
            monthMoney1.setDiferenceMoney(monthMoney1.getTargetMoney() - monthMoney1.getActualMoney());
            monthMoneySer.update(monthMoney1);
            moneyMonthId = monthMoney1.getId();
        } else {
            monthMoney.setDiferenceMoney(monthMoney.getTargetMoney() - monthMoney.getActualMoney());
            monthMoney = monthMoneySer.save(monthMoney);
            moneyMonthId = monthMoney.getId();
        }

        //业务类型id
        String businessDataId = "";
        BusinessDataDTO businessDataDTO = new BusinessDataDTO();
        businessDataDTO.getConditions().add(Restrict.eq("monthMoneyId", moneyMonthId));
        BusinessData businessData = BeanTransform.copyProperties(to, BusinessData.class, true);
        businessData.setTargerMoney(to.getVariousTargerMoney());
        businessData.setActualMoney(to.getVariousActualMoney());
        businessDataDTO.getConditions().add(Restrict.eq("businessType", businessData.getBusinessType()));
        List<BusinessData> businessDatas = businessDataSer.findByCis(businessDataDTO);
        if (null != businessDatas && businessDatas.size() > 0) {
            BusinessData businessData1 = businessDatas.get(0);
            BeanUtils.copyProperties(businessData, businessData1);
            businessData1.setDifference(businessData1.getTargerMoney() - businessData1.getActualMoney());
            businessDataSer.update(businessData1);
            businessDataId = businessData1.getId();
        } else {
            businessData.setDifference(businessData.getTargerMoney() - businessData.getActualMoney());
            businessData.setMonthMoneyId(moneyMonthId);
            businessData = businessDataSer.save(businessData);
            businessDataId = businessData.getId();
        }

        //业务反向科目id
        String subjectId = "";
        SubjectDataDTO subjectDataDTO = new SubjectDataDTO();
        subjectDataDTO.getConditions().add(Restrict.eq("businessDataId", businessDataId));
        SubjectData subjectData = BeanTransform.copyProperties(to, SubjectData.class, true);
        subjectDataDTO.getConditions().add(Restrict.eq("subject", subjectData.getSubject()));
        List<SubjectData> subjectDatas = subjectDataSer.findByCis(subjectDataDTO);
        if (null != subjectDatas && subjectDatas.size() > 0) {
            SubjectData subjectData1 = subjectDatas.get(0);
            subjectId = subjectData1.getId();
        } else {
            subjectData.setBusinessDataId(businessDataId);
            subjectData = subjectDataSer.save(subjectData);
            subjectId = subjectData.getId();
        }

        //周期id
        String cycleId = "";
        CycleDTO cycleDTO = new CycleDTO();
        cycleDTO.getConditions().add(Restrict.eq("subjectDataId", subjectId));
        Cycle cycle = BeanTransform.copyProperties(to, Cycle.class, true);
        cycleDTO.getConditions().add(Restrict.eq("cycle", cycle.getCycle()));
        List<Cycle> cycles = cycleSer.findByCis(cycleDTO);
        if (null != cycles && cycles.size() > 0) {
            Cycle cycle1 = cycles.get(0);
            cycleId = cycle1.getId();
        } else {
            cycle.setSubjectDataId(subjectId);
            cycle = cycleSer.save(cycle);
            cycleId = cycle.getId();
        }

        //日期id
        String dateId = "";
        DateDataDTO dateDataDTO = new DateDataDTO();
        dateDataDTO.getConditions().add(Restrict.eq("cycleId", cycleId));
        if (null != to.getDateDataCopyTOs() && to.getDateDataCopyTOs().size() > 0) {
            for (DateDataCopyTO dateDataCopyTO : to.getDateDataCopyTOs()) {
                DateData dateData = BeanTransform.copyProperties(dateDataCopyTO, DateData.class, true);
                dateDataDTO.getConditions().add(Restrict.eq("date", dateData.getDate()));
                dateDataDTO.getConditions().add(Restrict.eq("dateType", dateData.getDateType()));
                List<DateData> dateDatas1 = dateDataSer.findByCis(dateDataDTO);
                if (null != dateDatas1 && dateDatas1.size() > 0) {
                    dateId = dateDatas1.get(0).getId();
                    BeanUtils.copyProperties(dateData, dateDatas1);
                    dateDataSer.update(dateDatas1);
                } else {
                    dateData.setCycleId(cycleId);
                    dateData = dateDataSer.save(dateData);
                    dateId = dateData.getId();
                }

                if (null != dateDataCopyTO.getFilesDataTOs() && dateDataCopyTO.getFilesDataTOs().size() > 0) {
                    for (FilesDataTO filesDataTO : dateDataCopyTO.getFilesDataTOs()) {
                        int i = 1;
                        if (null == filesDataTO.getTableIndex()) {
                            throw new SerException("表头下标不能为空");
                        }
                        if (StringUtils.isBlank(filesDataTO.getTableName())) {
                            throw new SerException("表头不能为空");
                        }
                        if (StringUtils.isBlank(filesDataTO.getContext())) {
                            throw new SerException("表头内容不能为空");
                        }

                        FilesData filesData = BeanTransform.copyProperties(filesDataTO, FilesData.class, true);
                        FilesDataDTO filesDataDTO = new FilesDataDTO();
                        filesDataDTO.getConditions().add(Restrict.eq("dateDataId", dateId));
                        filesDataDTO.getConditions().add(Restrict.eq("index", i++));
                        FilesData filesData1 = filesDataSer.findOne(filesDataDTO);
                        if (null != filesData1) {
                            BeanUtils.copyProperties(filesData, filesData1, "id");
                            filesData1.setModifyTime(LocalDateTime.now());
                            filesDataSer.update(filesData1);
                        } else {
                            filesData.setDateDataId(dateId);
                            filesDataSer.save(filesData);
                        }
                    }
                }
//                DateDataDTO dateDataDTO5 = new DateDataDTO();
//                dateDataDTO5.getConditions().add(Restrict.eq("cycleId",cycleId));
//                dateDataDTO5.getConditions().add(Restrict.eq("date",dateData.getDate()));
//                dateDataDTO5.getConditions().add(Restrict.eq("dateType",4));
//                List<DateData> dateDatas5 = super.findByCis(dateDataDTO5);

                DateDataDTO dateDataDTO3 = new DateDataDTO();
                dateDataDTO3.getConditions().add(Restrict.eq("cycleId", cycleId));
                dateDataDTO3.getConditions().add(Restrict.eq("date", dateData.getDate()));
                dateDataDTO3.getConditions().add(Restrict.eq("dateType", 2));
                List<DateData> dateDatas3 = super.findByCis(dateDataDTO3);

                DateDataDTO dateDataDTO4 = new DateDataDTO();
                dateDataDTO4.getConditions().add(Restrict.eq("cycleId", cycleId));
                dateDataDTO4.getConditions().add(Restrict.eq("date", dateData.getDate()));
                dateDataDTO4.getConditions().add(Restrict.eq("dateType", 3));
                List<DateData> dateDatas4 = super.findByCis(dateDataDTO4);
                if (null != dateDatas3 && dateDatas3.size() > 0 && null != dateDatas4 && dateDatas4.size() > 0) {
                    DateData dateData1 = new DateData();
                    dateData1.setCycleId(cycleId);
                    dateData1.setDate(dateData.getDate());
                    dateData1.setDateType(DateType.DIFFERENCE);
                    dateData1 = dateDataSer.save(dateData1);

                    FilesDataDTO filesDataDTO1 = new FilesDataDTO();
                    filesDataDTO1.getConditions().add(Restrict.eq("dateDataId", dateDatas3.get(0).getId()));
                    filesDataDTO1.getSorts().add("index=asc");
                    List<FilesData> filesDatas1 = filesDataSer.findByCis(filesDataDTO1);

                    FilesDataDTO filesDataDTO2 = new FilesDataDTO();
                    filesDataDTO2.getConditions().add(Restrict.eq("dateDataId", dateDatas4.get(0).getId()));
                    filesDataDTO2.getSorts().add("index=asc");
                    List<FilesData> filesDatas2 = filesDataSer.findByCis(filesDataDTO2);

                    if (null != filesDatas1 && filesDatas1.size() > 0 && null != filesDatas2 && filesDatas2.size() > 0) {
                        //先删除
                        FilesDataDTO filesDataDTO7 = new FilesDataDTO();
                        filesDataDTO7.getConditions().add(Restrict.eq("dateDataId", dateData1.getId()));
                        filesDataDTO7.getSorts().add("index=asc");
                        List<FilesData> filesDatas7 = filesDataSer.findByCis(filesDataDTO7);
                        if (null != filesDatas7 && filesDatas7.size() > 0) {
                            filesDataSer.remove(filesDatas7);
                        }

                        int num = 0;
                        for (FilesData filesData1 : filesDatas1) {
                            String context = filesDatas2.stream().filter(obj -> filesData1.getTableName().equals(obj.getTableName())).map(FilesData::getContext).collect(Collectors.toList()).get(0);
                            FilesData filesData = new FilesData();
                            filesData.setTableIndex(++num);
                            filesData.setDateDataId(dateData1.getId());
                            filesData.setTableName(filesData1.getTableName());
                            filesData.setContext(String.valueOf(Double.valueOf(filesData1.getContext()) - Double.valueOf(context)));
                            filesDataSer.save(filesData);
                        }

                    }
                }

                //计算合计
                FilesDataTotalDTO filesDataTotalDTO = new FilesDataTotalDTO();
                filesDataTotalDTO.getConditions().add(Restrict.eq("dateDataId", dateId));
                List<FilesDataTotal> filesDataTotals = filesDataTotalSer.findByCis(filesDataTotalDTO);
                if (null != filesDataTotals && filesDataTotals.size() > 0) {
                    filesDataTotalSer.remove(filesDataTotals);
                }

                FilesDataTotal filesDataTotal = new FilesDataTotal();
                filesDataTotal.setDateDataId(dateId);
                FilesDataDTO filesDataDTO = new FilesDataDTO();
                filesDataDTO.getConditions().add(Restrict.eq("dateDataId", dateId));
                List<FilesData> filesDatas = filesDataSer.findByCis(filesDataDTO);
                if (null != filesDatas && filesDatas.size() > 0) {
                    List<String> contexts = filesDatas.stream().map(FilesData::getContext).collect(Collectors.toList());

                    DateDataDTO dateDataDTO1 = new DateDataDTO();
                    dateDataDTO1.getConditions().add(Restrict.eq("id", dateId));
                    List<DateData> dateDatas = dateDataSer.findByCis(dateDataDTO1);
                    DateType type = null;
                    if (null != dateDatas && dateDatas.size() > 0) {
                        type = dateDatas.get(0).getDateType();
                    }
                    Double total = 0d;
                    if (DateType.NONE.equals(type)) {
                        for (String con : contexts) {
                            try {
                                con = con.substring(0, con.indexOf("%"));
                                total += Double.valueOf(con);
                            } catch (Exception e) {
                                throw new SerException("同一个日期对应的阶段数据第一行填写的应为比例,如:40%");
                            }
                        }
                    } else {
                        for (String con : contexts) {
                            try {
                                total += Double.valueOf(con);
                            } catch (Exception e) {
                                throw new SerException("同一个日期对应的阶段数据第二行至第三行填写的应为数字");
                            }
                        }
                    }

                    filesDataTotal.setTotal(String.valueOf(total));
                    filesDataTotalSer.save(filesDataTotal);
                }
            }
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(DateDataUpdateTO to) throws SerException {
        Double total = 0d;
        Double total1 = 0d;
        FilesDataDTO filesDataDTO = new FilesDataDTO();
        filesDataDTO.getConditions().add(Restrict.eq("dateDataId", to.getDateDataId()));
        filesDataDTO.getSorts().add("index=asc");
        List<FilesData> filesDatas = filesDataSer.findByCis(filesDataDTO);

        if (null == filesDatas && filesDatas.size() < 1) {
            throw new SerException("目标数据对象为空");
        }
        List<FilesDataTO> filesDataTOs = to.getFilesDataTOs();
        BeanUtils.copyProperties(filesDataTOs, filesDatas);
        filesDatas.stream().forEach(obj -> {
            obj.setDateDataId(to.getDateDataId());
            obj.setModifyTime(LocalDateTime.now());
        });
        filesDataSer.update(filesDatas);

        List<String> contexts = filesDatas.stream().map(FilesData::getContext).collect(Collectors.toList());
        DateDataDTO dateDataDTO2 = new DateDataDTO();
        dateDataDTO2.getConditions().add(Restrict.eq("id", to.getDateDataId()));
        DateData dateData2 = dateDataSer.findOne(dateDataDTO2);
        if (null != dateData2) {
            if (DateType.NONE.equals(dateData2.getDateType())) {
                for (String con : contexts) {
                    try {
                        con = con.substring(0, con.indexOf("%"));
                        total += Double.valueOf(con);
                    } catch (Exception e) {
                        throw new SerException("同一个日期对应的阶段数据第一行填写的应为比例,如:40%");
                    }
                }
            } else {
                for (String con : contexts) {
                    try {
                        total += Double.valueOf(con);
                    } catch (Exception e) {
                        throw new SerException("同一个日期对应的阶段数据第二行至第四行填写的应为数字");
                    }
                }
            }
        }

        //修改差异
        String differId = "";
        DateDataDTO dateDataDTO = new DateDataDTO();
        dateDataDTO.getConditions().add(Restrict.eq("id", to.getDateDataId()));
        DateData dateData = dateDataSer.findOne(dateDataDTO);
        DateDataDTO dateDataDTO1 = new DateDataDTO();
        dateDataDTO1.getConditions().add(Restrict.eq("date", dateData.getDate()));
        List<DateData> dateDatas = super.findByCis(dateDataDTO1);

        if (null != dateDatas && dateDatas.size() > 0) {
            List<DateData> dateDatas1 = dateDatas.stream().filter(obj -> DateType.PLAN.equals(obj.getDateType())).collect(Collectors.toList());
            List<DateData> dateDatas2 = dateDatas.stream().filter(obj -> DateType.ATCUAL.equals(obj.getDateType())).collect(Collectors.toList());
            List<DateData> dateDatas3 = dateDatas.stream().filter(obj -> DateType.DIFFERENCE.equals(obj.getDateType())).collect(Collectors.toList());

            if (null != dateDatas3 && dateDatas3.size() > 0) {
                FilesDataDTO filesDataDTO1 = new FilesDataDTO();
                filesDataDTO1.getConditions().add(Restrict.eq("dateDataId", dateDatas3.get(0).getId()));
                filesDataDTO1.getSorts().add("index=asc");
                List<FilesData> filesDatas1 = filesDataSer.findByCis(filesDataDTO1);
                if (null != filesDatas1 && filesDatas1.size() > 0) {
                    filesDataSer.remove(filesDatas1);
                }
                String dateDataId = dateDatas3.get(0).getId();

                if (null != dateDatas1 && dateDatas1.size() > 0 && null != dateDatas2 && dateDatas2.size() > 0) {
                    FilesDataDTO filesDataDTO2 = new FilesDataDTO();
                    filesDataDTO2.getConditions().add(Restrict.eq("dateDataId", dateDatas1.get(0).getId()));
                    filesDataDTO2.getSorts().add("index=asc");
                    List<FilesData> filesDatas2 = filesDataSer.findByCis(filesDataDTO2);


                    FilesDataDTO filesDataDTO3 = new FilesDataDTO();
                    filesDataDTO3.getConditions().add(Restrict.eq("dateDataId", dateDatas2.get(0).getId()));
                    filesDataDTO3.getSorts().add("index=asc");
                    List<FilesData> filesDatas3 = filesDataSer.findByCis(filesDataDTO3);


                    if (null != filesDatas2 && filesDatas2.size() > 0 && null != filesDatas3 && filesDatas3.size() > 0) {
                        int num = 1;
                        for (FilesData filesData : filesDatas2) {
                            Double context = Double.valueOf(filesDatas3.stream().map(FilesData::getContext).collect(Collectors.toList()).get(0));
                            FilesData filesData1 = new FilesData();
                            filesData1.setTableIndex(num++);
                            filesData1.setTableName(filesData.getTableName());
                            filesData1.setDateDataId(dateDataId);
                            filesData1.setContext((String.valueOf(Double.valueOf(filesData.getContext()) - context)));
                            filesData1 = filesDataSer.save(filesData1);
                            differId = filesData1.getDateDataId();
                            try {
                                total1 += Double.valueOf(filesData1.getContext());
                            } catch (Exception e) {
                                throw new SerException(e.getMessage());
                            }
                        }
                    }
                }
            }
        }

        //修改合计
        FilesDataTotalDTO filesDataTotalDTO = new FilesDataTotalDTO();
        filesDataTotalDTO.getConditions().add(Restrict.eq("dateDataId", to.getDateDataId()));
        FilesDataTotal filesDataTotal = filesDataTotalSer.findOne(filesDataTotalDTO);
        filesDataTotal.setTotal(total.toString());
        filesDataTotalSer.update(filesDataTotal);

        FilesDataTotalDTO filesDataTotalDTO1 = new FilesDataTotalDTO();
        filesDataTotalDTO1.getConditions().add(Restrict.eq("dateDataId", differId));
        FilesDataTotal filesDataTotal1 = filesDataTotalSer.findOne(filesDataTotalDTO1);
        filesDataTotal1.setTotal(total1.toString());
        filesDataTotalSer.update(filesDataTotal1);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String dateDataId) throws SerException {
        DateDataDTO dateDataDTO = new DateDataDTO();
        dateDataDTO.getConditions().add(Restrict.eq("id", dateDataId));
        DateData dateData = dateDataSer.findOne(dateDataDTO);
        if (null == dateData) {
            throw new SerException("目标数据不能为空");
        }
        DateDataDTO dateDataDTO1 = new DateDataDTO();
        dateDataDTO1.getConditions().add(Restrict.eq("date", dateData.getDate()));
        dateDataDTO1.getConditions().add(Restrict.eq("cycleId", dateData.getCycleId()));
        List<DateData> dateDatas = dateDataSer.findByCis(dateDataDTO1);
        if (null != dateDatas && dateDatas.size() > 0) {
            remove(dateDatas);
        }

        FilesDataTotalDTO filesDataTotalDTO = new FilesDataTotalDTO();
        filesDataTotalDTO.getConditions().add(Restrict.eq("dateDataId", dateDataId));
        FilesDataTotal filesDataTotal = filesDataTotalSer.findOne(filesDataTotalDTO);
        if (null != filesDataTotal) {
            filesDataTotalSer.remove(filesDataTotal);
        }
    }

    @Override
    public Long getTotal(DateDataDTO dto) throws SerException {
        MonthMoneyDTO monthMoneyDTO = BeanTransform.copyProperties(dto, MonthMoneyDTO.class);
        return monthMoneySer.count(monthMoneyDTO);
    }

    @Override
    public DateDataBO getById(String dateDataId) throws SerException {
        DateDataDTO dateDataDTO = new DateDataDTO();
        dateDataDTO.getConditions().add(Restrict.eq("id", dateDataId));
        DateData dateData = dateDataSer.findOne(dateDataDTO);
        DateDataBO bo = BeanTransform.copyProperties(dateData, DateDataBO.class);

        FilesDataBO filesDataBO = new FilesDataBO();
        filesDataBO.setTableName("阶段");

        FilesDataDTO filesDataDTO = new FilesDataDTO();
        filesDataDTO.getConditions().add(Restrict.eq("dateDataId", dateDataId));
        List<FilesData> filesDatas = filesDataSer.findByCis(filesDataDTO);
        if (null != filesDatas && filesDatas.size() > 0) {
            List<FilesDataBO> filesDataBOs = BeanTransform.copyProperties(filesDatas, FilesDataBO.class);
            filesDataBO.getFilesDataVO1s().addAll(filesDataBOs);
        }
        bo.setFilesDataVO(filesDataBO);
        return bo;
    }

    @Override
    public MonthMoneyBO findMoneyData(String year, String month) throws SerException {
        MonthMoneyDTO monthMoneyDTO = new MonthMoneyDTO();
        monthMoneyDTO.getConditions().add(Restrict.eq("year", year));
        monthMoneyDTO.getConditions().add(Restrict.eq("month", month));
        List<MonthMoney> monthMoneys = monthMoneySer.findByCis(monthMoneyDTO);
        if (null != monthMoneys && monthMoneys.size() > 0) {
            MonthMoney monthMoney = monthMoneys.get(0);
            MonthMoneyBO bo = BeanTransform.copyProperties(monthMoney, MonthMoneyBO.class);
            return bo;
        }
        return null;
    }

    @Override
    public MonthBusinessDataBO findBusinessData(String year, String month, String businessType) throws SerException {
        MonthMoneyBO monthMoneyBO = findMoneyData(year, month);
        if (null != monthMoneyBO) {
            BusinessDataDTO businessDataDTO = new BusinessDataDTO();
            businessDataDTO.getConditions().add(Restrict.eq("monthMoneyId", monthMoneyBO.getId()));
            businessDataDTO.getConditions().add(Restrict.eq("businessType", businessType));
            List<BusinessData> businessDatas = businessDataSer.findByCis(businessDataDTO);
            if (null != businessDatas && businessDatas.size() > 0) {
                MonthBusinessDataBO bo = BeanTransform.copyProperties(businessDatas.get(0), MonthBusinessDataBO.class);
                return bo;
            }
        }
        return null;
    }

    @Override
    public List<String> findDate(String year, String month, String week) throws SerException {
        List<String> list = new ArrayList<>(0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(year));
        calendar.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, Integer.valueOf(week));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        list.add(start);
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String sta = dateFormat.format(calendar.getTime());
            list.add(sta);
        }
        return list;
    }

    public static void main(String[] args) {
        String time = "2017-12";

        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int n = cal.get(Calendar.DAY_OF_WEEK);
        if (n == 1) {
            n = 7;
        } else {
            n = n - 1;
        }

        System.out.println("当天为本周第" + n + "天");
// 日期格式

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= 7; i++) {
            cal.set(Calendar.DAY_OF_MONTH, date + i - n);
            System.out.println("本周第" + i + "天：" + sdf.format(cal.getTime()));
        }

        int year = 2017;
        int month = 12;
        int week = 6;

        List<String> list = new ArrayList<>(0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        list.add(start);
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String sta = dateFormat.format(calendar.getTime());
            list.add(sta);
        }
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time1 = new String[]{start, endTime};
//        return time;
        System.out.print(time1);
    }
}