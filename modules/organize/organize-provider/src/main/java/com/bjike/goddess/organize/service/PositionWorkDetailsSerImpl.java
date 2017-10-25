package com.bjike.goddess.organize.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.IndicatorDTO;
import com.bjike.goddess.organize.dto.ModulesDTO;
import com.bjike.goddess.organize.dto.PositionWorkDetailsDTO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.entity.Indicator;
import com.bjike.goddess.organize.entity.Modules;
import com.bjike.goddess.organize.entity.PositionWorkDetails;
import com.bjike.goddess.organize.excel.*;
import com.bjike.goddess.organize.to.IndicatorTO;
import com.bjike.goddess.organize.to.ModulesTO;
import com.bjike.goddess.organize.to.PositionWorkDetailsTO;
import com.bjike.goddess.organize.utils.IndicatorA;
import com.bjike.goddess.organize.utils.ModuleA;
import com.bjike.goddess.organize.utils.PositionWorkDetailsA;
import com.bjike.goddess.organize.vo.ActResultOrgan;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 岗位工作明细表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:41 ]
 * @Description: [ 岗位工作明细表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "organizeSerCache")
@Service
public class PositionWorkDetailsSerImpl extends ServiceImpl<PositionWorkDetails, PositionWorkDetailsDTO> implements PositionWorkDetailsSer {
    @Autowired
    private ModulesSer modulesSer;
    @Autowired
    private IndicatorSer indicatorSer;
    @Autowired
    private DepartmentDetailSer departmentDetailSer;
    @Autowired
    private PositionDetailSer positionDetailSer;

    @Transactional
    @Override
    public void save(PositionWorkDetailsTO to) throws SerException {
        PositionWorkDetails entity = BeanTransform.copyProperties(to, PositionWorkDetails.class, "modulesTOList");
        entity = transForm(entity);
        //todo: 注意这张表要重新建一下，因为有可能查不到Purpose和Version
        entity.setPurpose("asf");
        entity.setVersion("saf");
        entity = super.save(entity);
        List<ModulesTO> modulesTOList = to.getModulesTOList();
        for (ModulesTO modulesTO : modulesTOList) {
            Modules modules = BeanTransform.copyProperties(modulesTO, Modules.class, "indicatorTOList", "workDetailsId");
            modules.setWorkDetailsId(entity.getId());
            modules = modulesSer.save(modules);
            for (IndicatorTO indicatorTO : modulesTO.getIndicatorTOList()) {
                Indicator indicator = BeanTransform.copyProperties(indicatorTO, Indicator.class, "modulesId");
                indicator.setModulesId(modules.getId());
                indicatorSer.save(indicator);
            }
        }

    }

    @Transactional
    @Override
    public void update(PositionWorkDetailsTO to) throws SerException {
        PositionWorkDetails entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        BeanTransform.copyProperties(to, entity, "modulesTOList");
        entity = transForm(entity);
//        entity.setPurpose("asf");
//        entity.setVersion("saf");
        super.update(entity);
        for (ModulesTO modulesTO : to.getModulesTOList()) {
            Modules modules = modulesSer.findById(modulesTO.getId());
            BeanTransform.copyProperties(modulesTO, modules);
            modulesSer.update(modules);
            for (IndicatorTO indicatorTO : modulesTO.getIndicatorTOList()) {
                Indicator indicator = indicatorSer.findById(indicatorTO.getId());
                BeanTransform.copyProperties(indicatorTO, indicator, "modulesId");
                indicatorSer.update(indicator);
            }
        }
    }

    @Transactional
    @Override
    public void delete(String id) throws SerException {
        PositionWorkDetails entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        ModulesDTO modulesDTO = new ModulesDTO();
        modulesDTO.getConditions().add(Restrict.eq("workDetailsId", entity.getId()));
        List<Modules> modulesList = modulesSer.findByCis(modulesDTO);
        for (Modules modules : modulesList) {
            IndicatorDTO indicatorDTO = new IndicatorDTO();
            indicatorDTO.getConditions().add(Restrict.eq("modulesId", modules.getId()));
            indicatorSer.remove(indicatorSer.findByCis(indicatorDTO));
        }
        modulesSer.remove(modulesList);
        super.remove(entity);
    }

    @Override
    public List<PositionWorkDetailsBO> maps(PositionWorkDetailsDTO dto) throws SerException {
        searchCondition(dto);


        List<PositionWorkDetails> positionWorkDetailsList = super.findByPage(dto);
        if (null != positionWorkDetailsList && positionWorkDetailsList.size() > 0) {
            List<PositionWorkDetailsBO> positionWorkDetailsBOList = BeanTransform.copyProperties(positionWorkDetailsList, PositionWorkDetailsBO.class, "modulesBOList");
            for (PositionWorkDetailsBO positionWorkDetailsBO : positionWorkDetailsBOList) {
                ModulesDTO modulesDTO = new ModulesDTO();
                if (StringUtils.isNotBlank(dto.getModulesName())) {
                    modulesDTO.getConditions().add(Restrict.eq("name", dto.getModulesName()));
                    modulesDTO.getConditions().add(Restrict.eq("hasConnet", 1));
                }
//                ModulesDTO modulesDTO1 = new ModulesDTO();
                modulesDTO.getConditions().add(Restrict.eq("workDetailsId", positionWorkDetailsBO.getId()));
                List<Modules> modulesList = modulesSer.findByCis(modulesDTO);
                List<ModulesBO> modulesBOList = BeanTransform.copyProperties(modulesList, ModulesBO.class, "indicatorBOList");
                for (ModulesBO modulesBO : modulesBOList) {
                    IndicatorDTO indicatorDTO = new IndicatorDTO();
                    indicatorDTO.getConditions().add(Restrict.eq("modulesId", modulesBO.getId()));
                    List<Indicator> indicatorList = indicatorSer.findByCis(indicatorDTO);
                    List<IndicatorBO> indicatorBOList = BeanTransform.copyProperties(indicatorList, IndicatorBO.class);
                    modulesBO.setIndicatorBOList(indicatorBOList);
                }
                positionWorkDetailsBO.setModulesBOList(modulesBOList);
            }
            return positionWorkDetailsBOList;
        }
        return null;
    }

    @Override
    public Long getTotal(PositionWorkDetailsDTO dto) throws SerException {
        searchCondition(dto);
        if (StringUtils.isNotBlank(dto.getModulesName())) {
            ModulesDTO modulesDTO = new ModulesDTO();
            modulesDTO.getConditions().add(Restrict.eq("name", dto.getModulesName()));
            modulesDTO.getConditions().add(Restrict.eq("hasConnet", 1));
            List<Modules> modulesList = modulesSer.findByCis(modulesDTO);
            if (null != modulesList && modulesList.size() > 0) {
                List<String> list = modulesList.stream().map(Modules::getWorkDetailsId).distinct().collect(Collectors.toList());
                return Long.valueOf(list.size());
            }
        }
        return super.count(dto);
    }

    @Override
    public PositionWorkDetailsBO getById(String id) throws SerException {
        PositionWorkDetails positionWorkDetails = super.findById(id);
        if (null == positionWorkDetails) {
            throw new SerException("目标数据对象为空");
        }
        PositionWorkDetailsBO positionWorkDetailsBO = BeanTransform.copyProperties(positionWorkDetails, PositionWorkDetailsBO.class, "modulesBOList");
        ModulesDTO modulesDTO = new ModulesDTO();
        modulesDTO.getConditions().add(Restrict.eq("workDetailsId", positionWorkDetails.getId()));
        List<Modules> modulesList = modulesSer.findByCis(modulesDTO);
        List<ModulesBO> modulesBOList = BeanTransform.copyProperties(modulesList, ModulesBO.class, "indicatorBOList");
        for (ModulesBO modulesBO : modulesBOList) {
            IndicatorDTO indicatorDTO = new IndicatorDTO();
            indicatorDTO.getConditions().add(Restrict.eq("modulesId", modulesBO.getId()));
            List<Indicator> indicatorList = indicatorSer.findByCis(indicatorDTO);
            List<IndicatorBO> indicatorBOList = BeanTransform.copyProperties(indicatorList, IndicatorBO.class);
            modulesBO.setIndicatorBOList(indicatorBOList);
        }
        positionWorkDetailsBO.setModulesBOList(modulesBOList);

        return positionWorkDetailsBO;
    }

    @Override
    public List<ManagerBO> weekCollect(String startTime, String endTime) throws SerException {
        if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
            startTime = DateUtil.dateToString(DateUtil.getStartWeek());
            endTime = DateUtil.dateToString(LocalDate.now());
        }
        List<ManagerBO> list = new ArrayList<>(0);
        list.add(setManagerBO(startTime, endTime));
        return list;
    }

    @Override
    public List<ManagerBO> monthCollect(String month) throws SerException {
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        month = month + "-01";
        String startTime = findFirstDayOfMonth(month);
        String endTime = findEndDayOfMonth(month);
        List<ManagerBO> list = new ArrayList<>(0);
        list.add(setManagerBO(startTime, endTime));
//        list = setManagerBO1(startTime, endTime);
        return list;
    }

    @Override
    public List<ManagerBO> totalCollect() throws SerException {
        String startTime = "";
        String endTime = "";
        List<ManagerBO> list = new ArrayList<>(0);
        list.add(setManagerBO(startTime, endTime));
//        list = setManagerBO1(startTime, endTime);
        return list;
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startTime = date[0];
        String endTime = date[1];
        List<ManagerBO> list = new ArrayList<>(0);
        list = setManagerBO1(startTime, endTime);
//        return list;

        String text_1 = "组织结构管理周汇" + "(" + startTime + "-" + endTime + ")";

        return getOptionBO(text_1, list);
    }

    //    @Override
    public OptionBO figureShowMonth1(String month) throws SerException {
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        month = month + "-01";
        String startTime = findFirstDayOfMonth(month);
        String endTime = findEndDayOfMonth(month);
        List<ManagerBO> list = new ArrayList<>(0);
        list = setManagerBO1(startTime, endTime);
        String text_1 = "组织结构管理月汇总" + "(" + startTime + "-" + endTime + ")";

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_3 = new String[]{"内部项目数", "岗位数",
                "岗位无任职人数", "兼任数", "已有岗位说明书",
                "无岗位说明书", "被调动人数", "通过（轮岗人数）", "待入职人数", "在职人员数", "已离职人员数", "待离职人数"};
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (ManagerBO managerBO : list) {
                text_list2.add(String.valueOf(managerBO.getDepartments()));

                //柱状图数据
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(managerBO.getDepartments());
                seriesBO.setType("bar");

                Integer[] number = new Integer[]{managerBO.getProjectNum().intValue(), managerBO.getPositionNum().intValue(),
                        managerBO.getNoEmployeesNum().intValue(), managerBO.getPartjobNum().intValue(), managerBO.getJobDescriptionNum().intValue(),
                        managerBO.getNoJobNum().intValue(), managerBO.getMoveNum().intValue(), managerBO.getRotationNum().intValue(),
                        managerBO.getWaitEntryNum().intValue(), managerBO.getEntryNum().intValue(), managerBO.getDimissionNum().intValue(), managerBO.getWaitDimissionNum().intValue()};
                seriesBO.setData(number);
                seriesBOList.add(seriesBO);
            }
        }

        String[] text_2 = new String[text_list2.size()];
        text_2 = text_list2.toArray(text_2);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;

    }


    @Override
    public OptionBO figureShowMonth(String month) throws SerException {
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        month = month + "-01";
        String startTime = findFirstDayOfMonth(month);
        String endTime = findEndDayOfMonth(month);
        List<ManagerBO> list = new ArrayList<>(0);
        list = setManagerBO1(startTime, endTime);
        String text_1 = "组织结构管理月汇总" + "(" + startTime + "-" + endTime + ")";

        return getOptionBO(text_1, list);
    }

    @Override
    public OptionBO figureShowAll() throws SerException {
        String startTime = "";
        String endTime = "";
        List<ManagerBO> list = new ArrayList<>(0);
        list = setManagerBO1(startTime, endTime);
        String text_1 = "组织结构管理累计汇总";
        return getOptionBO(text_1, list);
    }

    @Override
    public OptionBO figureShowDay(String day) throws SerException {
        if (StringUtils.isBlank(day)) {
            day = DateUtil.dateToString(LocalDate.now());
        }
        String startTime = day;
        String endTime = getSpecifiedDayAfter(day);
        List<ManagerBO> list = new ArrayList<>(0);
        list = setManagerBO1(startTime, endTime);
        String text_1 = "组织结构管理日汇总" + "(" + startTime + "-" + endTime + ")";

        return getOptionBO(text_1, list);
    }

    @Override
    public byte[] exportExcel() throws SerException {

        List<PositionWorkDetailsExport1> returnExcelDataList = new ArrayList<>();
        List<Integer> mainTableLen = new ArrayList<>();
        List<Map<String, Map<String, List<Integer>>>> modulTableLen = new ArrayList<>();

        List<PositionWorkDetailsA> resultData = new ArrayList<>();
        //主表
        List<PositionWorkDetails> list = super.findAll();
        resultData = BeanTransform.copyProperties(list, PositionWorkDetailsA.class);
        if (resultData != null && resultData.size() > 0) {
            int seqNum = 0;
            for (PositionWorkDetailsA positionWorkDetailsA : resultData) {
                seqNum++;
                ModulesDTO modulesDTO = new ModulesDTO();
                modulesDTO.getSorts().add("name=desc");
                modulesDTO.getConditions().add(Restrict.eq("workDetailsId", positionWorkDetailsA.getId()));
                List<Modules> moduleses = modulesSer.findByCis(modulesDTO);
                positionWorkDetailsA.setModuleLendth(moduleses.size());
                positionWorkDetailsA.setModuleAList(BeanTransform.copyProperties(moduleses, ModuleA.class, "createTime", "modifyTime"));
                //查指标表
                if (null != moduleses && moduleses.size() > 0) {//14
                    List<ModuleA> mAL = new ArrayList<>();
                    if (null != positionWorkDetailsA.getModuleAList() && positionWorkDetailsA.getModuleAList().size() > 0) {
                        for (ModuleA moduleA : positionWorkDetailsA.getModuleAList()) {
                            IndicatorDTO indicatorDTO = new IndicatorDTO();
                            indicatorDTO.getConditions().add(Restrict.eq("modulesId", moduleA.getId()));
                            List<Indicator> indicators = indicatorSer.findByCis(indicatorDTO);
                            if (null != indicators && indicators.size() > 0) {
                                moduleA.setIndicatorAList(BeanTransform.copyProperties(indicators, IndicatorA.class, "createTime", "modifyTime"));
                                moduleA.setIndicatorALength(indicators.size());
                                mAL.add(moduleA);
                            }
                        }
                    }
                    positionWorkDetailsA.setModuleLendth(moduleses.size());
                    positionWorkDetailsA.setModuleAList(mAL);
                } else {
                    positionWorkDetailsA.setModuleLendth(0);
                    positionWorkDetailsA.setModuleAList(BeanTransform.copyProperties(moduleses, ModuleA.class));
                }


                //取出所有数据,竖着放
                List<PositionWorkDetailsExportUtil> tempPwdu = new ArrayList<>();
                if (positionWorkDetailsA != null && null != positionWorkDetailsA.getModuleAList()) {
                    List<ModuleA> mL = positionWorkDetailsA.getModuleAList();

                    for (ModuleA m : mL) {
                        List<IndicatorA> iL = m.getIndicatorAList();
                        for (IndicatorA iA : iL) {

                            PositionWorkDetailsExportUtil excel = new PositionWorkDetailsExportUtil();

                            excel.setNameLendth1(m.getIndicatorALength());
                            excel.setGoals(positionWorkDetailsA.getGoals());
                            excel.setCompany(positionWorkDetailsA.getCompany());
                            excel.setDepartmentalGoals(positionWorkDetailsA.getDepartmentalGoals());
                            excel.setDepartment(positionWorkDetailsA.getDepartment());
                            excel.setPositionGoals(positionWorkDetailsA.getPositionGoals());
                            excel.setPosition(positionWorkDetailsA.getPosition());
                            excel.setSerialNumber(positionWorkDetailsA.getSerialNumber());
                            excel.setAngle(positionWorkDetailsA.getAngle());
                            excel.setDimension(positionWorkDetailsA.getDimension());
                            excel.setClassification(positionWorkDetailsA.getClassification());
                            excel.setProjectStage(positionWorkDetailsA.getProjectStage());
                            excel.setFunction(positionWorkDetailsA.getFunction());
                            excel.setFrequency(positionWorkDetailsA.getFrequency());
                            excel.setTimeNode(positionWorkDetailsA.getTimeNode());
                            excel.setOperationType(positionWorkDetailsA.getOperationType());
                            excel.setWorkContent(positionWorkDetailsA.getWorkContent());
                            excel.setAccessories(positionWorkDetailsA.getAccessories());
                            excel.setHasMould(positionWorkDetailsA.getHasMould());
                            excel.setMouldStorage(positionWorkDetailsA.getMouldStorage());
                            excel.setPaperStorage(positionWorkDetailsA.getPaperStorage());
                            excel.setSummarize(positionWorkDetailsA.getSummarize());
                            excel.setInformTimeNode(positionWorkDetailsA.getInformTimeNode());
                            excel.setNotificationForm(positionWorkDetailsA.getNotificationForm());
                            excel.setNotificationContent(positionWorkDetailsA.getNotificationContent());
                            excel.setNotificationObj(positionWorkDetailsA.getNotificationObj());
                            excel.setName(positionWorkDetailsA.getName());
                            excel.setAgent(positionWorkDetailsA.getAgent());
                            excel.setCompleteOpition(positionWorkDetailsA.getCompleteOpition());
                            excel.setProjectStageNum(positionWorkDetailsA.getProjectStageNum());
                            excel.setComplete(positionWorkDetailsA.getComplete());
                            excel.setPurpose(positionWorkDetailsA.getPurpose());
                            excel.setVersion(positionWorkDetailsA.getVersion());
                            //模快id
                            //TODO :待设置数据
                            excel.setName1(m.getName());
                            excel.setHasConnet1(m.getHasConnet());
                            excel.setInformTimeNode1(m.getInformTimeNode());
                            excel.setNotificationForm1(m.getNotificationForm());
                            excel.setNotificationContent1(m.getNotificationContent());
                            excel.setTimeNode1(m.getTimeNode());
                            excel.setLetterForm1(m.getLetterForm());
                            excel.setContentTemplate1(m.getContentTemplate());
                            excel.setFunctions1(m.getFunctions());
                            //指标
                            //TODO :待设置数据
                            excel.setNumber1(iA.getNumber());
                            excel.setType1(iA.getType());
                            excel.setAssessment1(iA.getAssessment());
                            excel.setTargetValue1(iA.getTargetValue());
                            excel.setReflect1(iA.getReflect());
                            excel.setGambling1(iA.getGambling());
                            excel.setUnGambling1(iA.getUnGambling());
                            excel.setReach1(iA.getReach());
                            excel.setRequestSide1(iA.getRequestSide());
                            excel.setGamblingSide1(iA.getGamblingSide());
                            excel.setRule1(iA.getRule());
                            tempPwdu.add(excel);
                        }

//                        if (mapModulLen.get(m.getName()) != null) {
//                            //临时存放之前相同模块名的长度
//                            List<Integer> tempModuleLen = mapModulLen.get(m.getName());
//                            mlenList.addAll(tempModuleLen);
//                            mlenList.add(iL.size());
//                            mapModulLen.put(m.getName(), mlenList);
//                        } else {
//                            mlenList.add(iL.size());
//                            mapModulLen.put(m.getName(), mlenList);
//
//                        }

                    }
                }

                Map<String, Map<String, List<Integer>>> mapAllModulLen = new HashedMap<>();
                Map<String, List<Integer>> mapModulLen = new HashedMap<>();
                //将数据横起来放
                List<Integer> lenList = new ArrayList<>();
                //处理数据
                if (tempPwdu != null && tempPwdu.size() > 0) {
                    List<PositionWorkDetailsExportUtil> guihua = tempPwdu.stream().filter(str -> "规划模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(guihua.size());
                    //取里面不同规划模快的长度
                    List<Integer> ghSublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "规划模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("规划模块", ghSublenList);

                    //2
                    List<PositionWorkDetailsExportUtil> fuli = tempPwdu.stream().filter(str -> "福利模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(fuli.size());
                    //取里面不同规划模快的长度
                    List<Integer> flSublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "福利模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("福利模块", flSublenList);

                    //3
                    List<PositionWorkDetailsExportUtil> suyang = tempPwdu.stream().filter(str -> "素养模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(suyang.size());
                    //取里面不同规划模快的长度
                    List<Integer> sySublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "素养模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("素养模块", sySublenList);

                    //4
                    List<PositionWorkDetailsExportUtil> yusuan = tempPwdu.stream().filter(str -> "预算模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(yusuan.size());
                    //取里面不同规划模快的长度
                    List<Integer> ysSublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "预算模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("预算模块", ysSublenList);

                    //5
                    List<PositionWorkDetailsExportUtil> zijin = tempPwdu.stream().filter(str -> "资金模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(zijin.size());
                    //取里面不同规划模快的长度
                    List<Integer> zjSublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "资金模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("资金模块", zjSublenList);

                    //6
                    List<PositionWorkDetailsExportUtil> caiwu = tempPwdu.stream().filter(str -> "财务模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(caiwu.size());
                    //取里面不同规划模快的长度
                    List<Integer> cwSublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "财务模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("财务模块", cwSublenList);

                    //7
                    List<PositionWorkDetailsExportUtil> kehu = tempPwdu.stream().filter(str -> "客户管理模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(kehu.size());
                    //取里面不同规划模快的长度
                    List<Integer> khSublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "客户管理模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("客户管理模块", khSublenList);

                    //8
                    List<PositionWorkDetailsExportUtil> jindu = tempPwdu.stream().filter(str -> "进度管理模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(jindu.size());
                    //取里面不同规划模快的长度
                    List<Integer> jdSublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "进度管理模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("进度管理模块", jdSublenList);

                    //9
                    List<PositionWorkDetailsExportUtil> yewu = tempPwdu.stream().filter(str -> "业务管理模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(yewu.size());
                    //取里面不同规划模快的长度
                    List<Integer> ywSublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "业务管理模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("业务管理模块", ywSublenList);

                    //10
                    List<PositionWorkDetailsExportUtil> xiangmuzu = tempPwdu.stream().filter(str -> "项目组模块".equals(str.getName1())).collect(Collectors.toList());
                    lenList.add(xiangmuzu.size());
                    //取里面不同规划模快的长度
                    List<Integer> xmzSublenList = positionWorkDetailsA.getModuleAList().stream().filter(str -> "项目组模块".equals(str.getName())).map(ModuleA::getIndicatorALength).collect(Collectors.toList());
                    mapModulLen.put("项目组模块", xmzSublenList);

                    mapAllModulLen.put(positionWorkDetailsA.getId(), mapModulLen);

                    //获取最大的长度
                    int maxLen = lenList.stream().max(new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o1.compareTo(o2);
                        }
                    }).get().intValue();
                    mainTableLen.add(maxLen);


                    //将数据横起来放
                    for (int i = 0; i < maxLen; i++) {
                        PositionWorkDetailsExport1 excel = new PositionWorkDetailsExport1();
                        excel.setSeqNum(seqNum);
                        excel.setGoals(positionWorkDetailsA.getGoals());
                        excel.setCompany(positionWorkDetailsA.getCompany());
                        excel.setDepartmentalGoals(positionWorkDetailsA.getDepartmentalGoals());
                        excel.setDepartment(positionWorkDetailsA.getDepartment());
                        excel.setPositionGoals(positionWorkDetailsA.getPositionGoals());
                        excel.setPosition(positionWorkDetailsA.getPosition());
                        excel.setSerialNumber(positionWorkDetailsA.getSerialNumber());
                        excel.setAngle(positionWorkDetailsA.getAngle());
                        excel.setDimension(positionWorkDetailsA.getDimension());
                        excel.setClassification(positionWorkDetailsA.getClassification());
                        excel.setProjectStage(positionWorkDetailsA.getProjectStage());
                        excel.setFunction(positionWorkDetailsA.getFunction());
                        excel.setFrequency(positionWorkDetailsA.getFrequency());
                        excel.setTimeNode(positionWorkDetailsA.getTimeNode());
                        excel.setOperationType(positionWorkDetailsA.getOperationType());
                        excel.setWorkContent(positionWorkDetailsA.getWorkContent());
                        excel.setAccessories(positionWorkDetailsA.getAccessories());
                        excel.setHasMould(positionWorkDetailsA.getHasMould());
                        excel.setMouldStorage(positionWorkDetailsA.getMouldStorage());
                        excel.setPaperStorage(positionWorkDetailsA.getPaperStorage());
                        excel.setSummarize(positionWorkDetailsA.getSummarize());
                        excel.setInformTimeNode(positionWorkDetailsA.getInformTimeNode());
                        excel.setNotificationForm(positionWorkDetailsA.getNotificationForm());
                        excel.setNotificationContent(positionWorkDetailsA.getNotificationContent());
                        excel.setNotificationObj(positionWorkDetailsA.getNotificationObj());
                        excel.setName(positionWorkDetailsA.getName());
                        excel.setAgent(positionWorkDetailsA.getAgent());
                        excel.setCompleteOpition(positionWorkDetailsA.getCompleteOpition());
                        excel.setProjectStageNum(positionWorkDetailsA.getProjectStageNum());
                        excel.setComplete(positionWorkDetailsA.getComplete());
                        excel.setPurpose(positionWorkDetailsA.getPurpose());
                        excel.setVersion(positionWorkDetailsA.getVersion());
                        if (guihua.size() <= maxLen && i <= guihua.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = guihua.get(i);
                            excel.setHasConnet1(temp1.getHasConnet1());
                            excel.setInformTimeNode1(temp1.getInformTimeNode1());
                            excel.setNotificationForm1(temp1.getNotificationForm1());
                            excel.setNotificationContent1(temp1.getNotificationContent1());
                            excel.setTimeNode1(temp1.getTimeNode1());
                            excel.setLetterForm1(temp1.getLetterForm1());
                            excel.setContentTemplate1(temp1.getContentTemplate1());
                            excel.setFunctions1(temp1.getFunctions1());
                            //指标
                            excel.setNumber1(temp1.getNumber1());
                            excel.setType1(temp1.getType1());
                            excel.setAssessment1(temp1.getAssessment1());
                            excel.setTargetValue1(temp1.getTargetValue1());
                            excel.setReflect1(temp1.getReflect1());
                            excel.setGambling1(temp1.getGambling1());
                            excel.setUnGambling1(temp1.getUnGambling1());
                            excel.setReach1(temp1.getReach1());
                            excel.setRequestSide1(temp1.getRequestSide1());
                            excel.setGamblingSide1(temp1.getGamblingSide1());
                            excel.setRule1(temp1.getRule1());
                        }
                        if (fuli.size() <= maxLen && i <= fuli.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = fuli.get(i);
                            excel.setHasConnet2(temp1.getHasConnet1());
                            excel.setInformTimeNode2(temp1.getInformTimeNode1());
                            excel.setNotificationForm2(temp1.getNotificationForm1());
                            excel.setNotificationContent2(temp1.getNotificationContent1());
                            excel.setTimeNode2(temp1.getTimeNode1());
                            excel.setLetterForm2(temp1.getLetterForm1());
                            excel.setContentTemplate2(temp1.getContentTemplate1());
                            excel.setFunctions2(temp1.getFunctions1());
                            //指标
                            excel.setNumber2(temp1.getNumber1());
                            excel.setType2(temp1.getType1());
                            excel.setAssessment2(temp1.getAssessment1());
                            excel.setTargetValue2(temp1.getTargetValue1());
                            excel.setReflect2(temp1.getReflect1());
                            excel.setGambling2(temp1.getGambling1());
                            excel.setUnGambling2(temp1.getUnGambling1());
                            excel.setReach2(temp1.getReach1());
                            excel.setRequestSide2(temp1.getRequestSide1());
                            excel.setGamblingSide2(temp1.getGamblingSide1());
                            excel.setRule2(temp1.getRule1());
                        }
                        if (suyang.size() <= maxLen && i <= suyang.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = suyang.get(i);
                            excel.setHasConnet3(temp1.getHasConnet1());
                            excel.setInformTimeNode3(temp1.getInformTimeNode1());
                            excel.setNotificationForm3(temp1.getNotificationForm1());
                            excel.setNotificationContent3(temp1.getNotificationContent1());
                            excel.setTimeNode3(temp1.getTimeNode1());
                            excel.setLetterForm3(temp1.getLetterForm1());
                            excel.setContentTemplate3(temp1.getContentTemplate1());
                            excel.setFunctions3(temp1.getFunctions1());
                            //指标
                            excel.setNumber3(temp1.getNumber1());
                            excel.setType3(temp1.getType1());
                            excel.setAssessment3(temp1.getAssessment1());
                            excel.setTargetValue3(temp1.getTargetValue1());
                            excel.setReflect3(temp1.getReflect1());
                            excel.setGambling3(temp1.getGambling1());
                            excel.setUnGambling3(temp1.getUnGambling1());
                            excel.setReach3(temp1.getReach1());
                            excel.setRequestSide3(temp1.getRequestSide1());
                            excel.setGamblingSide3(temp1.getGamblingSide1());
                            excel.setRule3(temp1.getRule1());
                        }
                        if (yusuan.size() <= maxLen && i <= yusuan.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = yusuan.get(i);
                            excel.setHasConnet4(temp1.getHasConnet1());
                            excel.setInformTimeNode4(temp1.getInformTimeNode1());
                            excel.setNotificationForm4(temp1.getNotificationForm1());
                            excel.setNotificationContent4(temp1.getNotificationContent1());
                            excel.setTimeNode4(temp1.getTimeNode1());
                            excel.setLetterForm4(temp1.getLetterForm1());
                            excel.setContentTemplate4(temp1.getContentTemplate1());
                            excel.setFunctions4(temp1.getFunctions1());
                            //指标
                            excel.setNumber4(temp1.getNumber1());
                            excel.setType4(temp1.getType1());
                            excel.setAssessment4(temp1.getAssessment1());
                            excel.setTargetValue4(temp1.getTargetValue1());
                            excel.setReflect4(temp1.getReflect1());
                            excel.setGambling4(temp1.getGambling1());
                            excel.setUnGambling4(temp1.getUnGambling1());
                            excel.setReach4(temp1.getReach1());
                            excel.setRequestSide4(temp1.getRequestSide1());
                            excel.setGamblingSide4(temp1.getGamblingSide1());
                            excel.setRule4(temp1.getRule1());
                        }
                        if (zijin.size() <= maxLen && i <= zijin.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = zijin.get(i);
                            excel.setHasConnet5(temp1.getHasConnet1());
                            excel.setInformTimeNode5(temp1.getInformTimeNode1());
                            excel.setNotificationForm5(temp1.getNotificationForm1());
                            excel.setNotificationContent5(temp1.getNotificationContent1());
                            excel.setTimeNode5(temp1.getTimeNode1());
                            excel.setLetterForm5(temp1.getLetterForm1());
                            excel.setContentTemplate5(temp1.getContentTemplate1());
                            excel.setFunctions5(temp1.getFunctions1());
                            //指标
                            excel.setNumber5(temp1.getNumber1());
                            excel.setType5(temp1.getType1());
                            excel.setAssessment5(temp1.getAssessment1());
                            excel.setTargetValue5(temp1.getTargetValue1());
                            excel.setReflect5(temp1.getReflect1());
                            excel.setGambling5(temp1.getGambling1());
                            excel.setUnGambling5(temp1.getUnGambling1());
                            excel.setReach5(temp1.getReach1());
                            excel.setRequestSide5(temp1.getRequestSide1());
                            excel.setGamblingSide5(temp1.getGamblingSide1());
                            excel.setRule5(temp1.getRule1());
                        }
                        if (caiwu.size() <= maxLen && i <= caiwu.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = caiwu.get(i);
                            excel.setHasConnet6(temp1.getHasConnet1());
                            excel.setInformTimeNode6(temp1.getInformTimeNode1());
                            excel.setNotificationForm6(temp1.getNotificationForm1());
                            excel.setNotificationContent6(temp1.getNotificationContent1());
                            excel.setTimeNode6(temp1.getTimeNode1());
                            excel.setLetterForm6(temp1.getLetterForm1());
                            excel.setContentTemplate6(temp1.getContentTemplate1());
                            excel.setFunctions6(temp1.getFunctions1());
                            //指标
                            excel.setNumber6(temp1.getNumber1());
                            excel.setType6(temp1.getType1());
                            excel.setAssessment6(temp1.getAssessment1());
                            excel.setTargetValue6(temp1.getTargetValue1());
                            excel.setReflect6(temp1.getReflect1());
                            excel.setGambling6(temp1.getGambling1());
                            excel.setUnGambling6(temp1.getUnGambling1());
                            excel.setReach6(temp1.getReach1());
                            excel.setRequestSide6(temp1.getRequestSide1());
                            excel.setGamblingSide6(temp1.getGamblingSide1());
                            excel.setRule6(temp1.getRule1());
                        }
                        if (kehu.size() <= maxLen && i <= kehu.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = kehu.get(i);
                            excel.setHasConnet7(temp1.getHasConnet1());
                            excel.setInformTimeNode7(temp1.getInformTimeNode1());
                            excel.setNotificationForm7(temp1.getNotificationForm1());
                            excel.setNotificationContent7(temp1.getNotificationContent1());
                            excel.setTimeNode7(temp1.getTimeNode1());
                            excel.setLetterForm7(temp1.getLetterForm1());
                            excel.setContentTemplate7(temp1.getContentTemplate1());
                            excel.setFunctions7(temp1.getFunctions1());
                            //指标
                            excel.setNumber7(temp1.getNumber1());
                            excel.setType7(temp1.getType1());
                            excel.setAssessment7(temp1.getAssessment1());
                            excel.setTargetValue7(temp1.getTargetValue1());
                            excel.setReflect7(temp1.getReflect1());
                            excel.setGambling7(temp1.getGambling1());
                            excel.setUnGambling7(temp1.getUnGambling1());
                            excel.setReach7(temp1.getReach1());
                            excel.setRequestSide7(temp1.getRequestSide1());
                            excel.setGamblingSide7(temp1.getGamblingSide1());
                            excel.setRule7(temp1.getRule1());
                        }
                        if (jindu.size() <= maxLen && i <= jindu.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = jindu.get(i);
                            excel.setHasConnet8(temp1.getHasConnet1());
                            excel.setInformTimeNode8(temp1.getInformTimeNode1());
                            excel.setNotificationForm8(temp1.getNotificationForm1());
                            excel.setNotificationContent8(temp1.getNotificationContent1());
                            excel.setTimeNode8(temp1.getTimeNode1());
                            excel.setLetterForm8(temp1.getLetterForm1());
                            excel.setContentTemplate8(temp1.getContentTemplate1());
                            excel.setFunctions8(temp1.getFunctions1());
                            //指标
                            excel.setNumber8(temp1.getNumber1());
                            excel.setType8(temp1.getType1());
                            excel.setAssessment8(temp1.getAssessment1());
                            excel.setTargetValue8(temp1.getTargetValue1());
                            excel.setReflect8(temp1.getReflect1());
                            excel.setGambling8(temp1.getGambling1());
                            excel.setUnGambling8(temp1.getUnGambling1());
                            excel.setReach8(temp1.getReach1());
                            excel.setRequestSide8(temp1.getRequestSide1());
                            excel.setGamblingSide8(temp1.getGamblingSide1());
                            excel.setRule8(temp1.getRule1());
                        }
                        if (yewu.size() <= maxLen && i <= yewu.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = yewu.get(i);
                            excel.setHasConnet9(temp1.getHasConnet1());
                            excel.setInformTimeNode9(temp1.getInformTimeNode1());
                            excel.setNotificationForm9(temp1.getNotificationForm1());
                            excel.setNotificationContent9(temp1.getNotificationContent1());
                            excel.setTimeNode9(temp1.getTimeNode1());
                            excel.setLetterForm9(temp1.getLetterForm1());
                            excel.setContentTemplate9(temp1.getContentTemplate1());
                            excel.setFunctions9(temp1.getFunctions1());
                            //指标
                            excel.setNumber9(temp1.getNumber1());
                            excel.setType9(temp1.getType1());
                            excel.setAssessment9(temp1.getAssessment1());
                            excel.setTargetValue9(temp1.getTargetValue1());
                            excel.setReflect9(temp1.getReflect1());
                            excel.setGambling9(temp1.getGambling1());
                            excel.setUnGambling9(temp1.getUnGambling1());
                            excel.setReach9(temp1.getReach1());
                            excel.setRequestSide9(temp1.getRequestSide1());
                            excel.setGamblingSide9(temp1.getGamblingSide1());
                            excel.setRule9(temp1.getRule1());
                        }
                        if (xiangmuzu.size() <= maxLen && i <= xiangmuzu.size() - 1) {
                            PositionWorkDetailsExportUtil temp1 = xiangmuzu.get(i);
                            excel.setHasConnet0(temp1.getHasConnet1());
                            excel.setInformTimeNode0(temp1.getInformTimeNode1());
                            excel.setNotificationForm0(temp1.getNotificationForm1());
                            excel.setNotificationContent0(temp1.getNotificationContent1());
                            excel.setTimeNode0(temp1.getTimeNode1());
                            excel.setLetterForm0(temp1.getLetterForm1());
                            excel.setContentTemplate0(temp1.getContentTemplate1());
                            excel.setFunctions0(temp1.getFunctions1());
                            //指标
                            excel.setNumber0(temp1.getNumber1());
                            excel.setType0(temp1.getType1());
                            excel.setAssessment0(temp1.getAssessment1());
                            excel.setTargetValue0(temp1.getTargetValue1());
                            excel.setReflect0(temp1.getReflect1());
                            excel.setGambling0(temp1.getGambling1());
                            excel.setUnGambling0(temp1.getUnGambling1());
                            excel.setReach0(temp1.getReach1());
                            excel.setRequestSide0(temp1.getRequestSide1());
                            excel.setGamblingSide0(temp1.getGamblingSide1());
                            excel.setRule0(temp1.getRule1());
                        }
                        returnExcelDataList.add(excel);
                    }

                }

                modulTableLen.add(mapAllModulLen);
            }

        }

        //导出
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(returnExcelDataList, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            //主表行数
            int rowSize = list.size();
            List<Field> fields = ClazzUtils.getFields(PositionWorkDetailsExport1.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);

            int index = 0;
            int lastRow = 0;
            for (int j = 0; j < rowSize; j++) {

                int x = 0;
                //List<int> maxList所有子表的长度
                if (null != mainTableLen && mainTableLen.size() > 0) {
                    PositionWorkDetails mainPwd = list.get(j);
                    int mergeRowCount = mainTableLen.get(j);
                    //5
                    if (mergeRowCount != 1) {
                        int firstRow = index;
                        lastRow = firstRow + mergeRowCount - 1;
                        //合并主表共33列
                        for (int i = 1; i < 33; i++) {
                            //1,5
                            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                            x = 33;
                        }
                        //合并模快
                        Map<String, List<Integer>> mergeMLen = modulTableLen.get(j).get(mainPwd.getId());
                        //获取规划模块合并长度数据
                        List<Integer> ghMergeLen = mergeMLen.get("规划模块");
                        if (null != ghMergeLen && ghMergeLen.size() > 0) {
                            int mfirstRow = firstRow;
                            int mMergeRowCount = 0;
                            for (Integer mi : ghMergeLen) {
                                if (mi != 1) {
                                    int mlastRow = (firstRow - 1) + mMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mfirstRow, mlastRow, z, z));
                                    }
                                    x += 19;
                                    mfirstRow = mlastRow + 1;
                                    mMergeRowCount = mMergeRowCount + mi;
                                }
                            }
                        }
                        //获取福利模块合并长度数据
                        List<Integer> flMergeLen = mergeMLen.get("福利模块");
                        if (null != flMergeLen && flMergeLen.size() > 0) {
                            int mflfirstRow = firstRow;
                            int mflMergeRowCount = 0;
                            for (Integer mi : flMergeLen) {
                                if (mi != 1) {
                                    int mfllastRow = (firstRow - 1) + mflMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mflfirstRow, mfllastRow, z, z));
                                    }
                                    x += 19;
                                    mflfirstRow = mfllastRow + 1;
                                    mflMergeRowCount = mflMergeRowCount + mi;
                                }
                            }
                        }
                        //获取素养模块合并长度数据
                        List<Integer> syMergeLen = mergeMLen.get("素养模块");
                        if (null != syMergeLen && syMergeLen.size() > 0) {
                            int msyfirstRow = firstRow;
                            int msyMergeRowCount = 0;
                            for (Integer mi : syMergeLen) {
                                if (mi != 1) {
                                    int msylastRow = (firstRow - 1) + msyMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(msyfirstRow, msylastRow, z, z));
                                    }
                                    x += 19;
                                    msyfirstRow = msylastRow + 1;
                                    msyMergeRowCount = msyMergeRowCount + mi;
                                }
                            }
                        }

                        //获预算模块合并长度数据
                        List<Integer> ysMergeLen = mergeMLen.get("预算模块");
                        if (null != ysMergeLen && ysMergeLen.size() > 0) {
                            int mysfirstRow = firstRow;
                            int mysMergeRowCount = 0;
                            for (Integer mi : ysMergeLen) {
                                if (mi != 1) {
                                    int myslastRow = (firstRow - 1) + mysMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mysfirstRow, myslastRow, z, z));
                                    }
                                    x += 19;
                                    mysfirstRow = myslastRow + 1;
                                    mysMergeRowCount = mysMergeRowCount + mi;
                                }
                            }
                        }

                        //获取资金模块合并长度数据
                        List<Integer> zjMergeLen = mergeMLen.get("资金模块");
                        if (null != zjMergeLen && zjMergeLen.size() > 0) {
                            int mzjfirstRow = firstRow;
                            int mzjMergeRowCount = 0;
                            for (Integer mi : zjMergeLen) {
                                if (mi != 1) {
                                    int mzjlastRow = (firstRow - 1) + mzjMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mzjfirstRow, mzjlastRow, z, z));
                                    }
                                    x += 19;
                                    mzjfirstRow = mzjlastRow + 1;
                                    mzjMergeRowCount = mzjMergeRowCount + mi;
                                }
                            }
                        }
                        //获取账务模块合并长度数据
                        List<Integer> cyMergeLen = mergeMLen.get("账务模块");
                        if (null != cyMergeLen && cyMergeLen.size() > 0) {
                            int mcyfirstRow = firstRow;
                            int mcyMergeRowCount = 0;
                            for (Integer mi : cyMergeLen) {
                                if (mi != 1) {
                                    int mcylastRow = (firstRow - 1) + mcyMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mcyfirstRow, mcylastRow, z, z));
                                    }
                                    x += 19;
                                    mcyfirstRow = mcylastRow + 1;
                                    mcyMergeRowCount = mcyMergeRowCount + mi;
                                }
                            }
                        }

                        //获取福利模块合并长度数据
                        List<Integer> khMergeLen = mergeMLen.get("客户管理模块");
                        if (null != khMergeLen && khMergeLen.size() > 0) {
                            int mkhfirstRow = firstRow;
                            int mkhMergeRowCount = 0;
                            for (Integer mi : khMergeLen) {
                                if (mi != 1) {
                                    int mkhlastRow = (firstRow - 1) + mkhMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mkhfirstRow, mkhlastRow, z, z));
                                    }
                                    x += 19;
                                    mkhfirstRow = mkhlastRow + 1;
                                    mkhMergeRowCount = mkhMergeRowCount + mi;
                                }
                            }
                        }
                        //获取进度管理模块合并长度数据
                        List<Integer> jdMergeLen = mergeMLen.get("进度管理模块");
                        if (null != jdMergeLen && jdMergeLen.size() > 0) {
                            int mjdfirstRow = firstRow;
                            int mjdMergeRowCount = 0;
                            for (Integer mi : jdMergeLen) {
                                if (mi != 1) {
                                    int mjdlastRow = (firstRow - 1) + mjdMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mjdfirstRow, mjdlastRow, z, z));
                                    }
                                    x += 19;
                                    mjdfirstRow = mjdlastRow + 1;
                                    mjdMergeRowCount = mjdMergeRowCount + mi;
                                }
                            }
                        }
                        //获取业务管理模块合并长度数据
                        List<Integer> ywMergeLen = mergeMLen.get("业务管理模块");
                        if (null != ywMergeLen && ywMergeLen.size() > 0) {
                            int mywfirstRow = firstRow;
                            int mywMergeRowCount = 0;
                            for (Integer mi : ywMergeLen) {
                                if (mi != 1) {
                                    int mywlastRow = (firstRow - 1) + mywMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mywfirstRow, mywlastRow, z, z));
                                    }
                                    x += 19;
                                    mywfirstRow = mywlastRow + 1;
                                    mywMergeRowCount = mywMergeRowCount + mi;
                                }
                            }
                        }
                        //获取项目组合并长度数据
                        List<Integer> xmzMergeLen = mergeMLen.get("项目组");
                        if (null != xmzMergeLen && xmzMergeLen.size() > 0) {
                            int mxmzfirstRow = firstRow;
                            int mxmzMergeRowCount = 0;
                            for (Integer mi : xmzMergeLen) {
                                if (mi != 1) {
                                    int mxmzlastRow = (firstRow - 1) + mxmzMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mxmzfirstRow, mxmzlastRow, z, z));
                                    }
                                    x += 19;
                                    mxmzfirstRow = mxmzlastRow + 1;
                                    mxmzMergeRowCount = mxmzMergeRowCount + mi;
                                }
                            }
                        }
                        lastRow--;
                    }
                }
                lastRow++;
                index = lastRow + 1;
            }


//            int index = 1;
//            for (int j = 0; j < rowSize; j++) {
//                //List<int> maxList所有子表的长度
//                if (null != maxList && maxList.size() > 0) {
//                    int mergeRowCount = maxList.get(j);
//                    if (mergeRowCount != 1) {
//                        int firstRow = index;
//                        int lastRow = 0;
//                        lastRow = firstRow + mergeRowCount - 1;
//                        for (int i = 0; i < 1; i++) {
//                            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
//                        }
//                        index = lastRow + 1;
//                    }
//                }
//            }

            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }

    @Transactional
    @Override
    public void importExcel(List<PositionWorkDetailsImport2> tos) throws SerException {
        try {
//            Excel excel = new Excel(0, 1);
//            List<PositionWorkDetailsImport> tos = ExcelUtil.mergeExcelToClazz(is, PositionWorkDetailsImport.class, excel);

            Set<Integer> seqNum = new HashSet<>();
            if (null != tos && tos.size() > 0) {
                List<PositionWorkDetailsImport> tocs = new ArrayList<>();
                List<PositionWorkDetails> positionWorkDetailses = new ArrayList<>(0);
                for (PositionWorkDetailsImport2 positionWorkDetailsImport2 : tos) {
                    seqNum.add(positionWorkDetailsImport2.getSeqNum());
                }
                for (Integer seq : seqNum) {
                    for (PositionWorkDetailsImport2 positionWorkDetailsImport2 : tos) {
                        if (seq.equals(positionWorkDetailsImport2.getSeqNum())) {
                            PositionWorkDetailsImport positionWorkDetailsImport = BeanTransform.copyProperties(positionWorkDetailsImport2, PositionWorkDetailsImport.class, "serialNumber", "gambling1", "unGambling1", "gambling2", "unGambling2", "gambling3", "unGambling3", "gambling4", "unGambling4", "gambling5", "unGambling5", "gambling6", "unGambling6", "gambling7", "unGambling7", "gambling8", "unGambling8", "gambling9", "unGambling9", "gambling0", "unGambling0");
                            positionWorkDetailsImport.setSerialNumber(positionWorkDetailsImport2.getSerialNumber().longValue());
                            if (null != positionWorkDetailsImport2.getGambling1()) {
                                positionWorkDetailsImport.setGambling1(positionWorkDetailsImport2.getGambling1().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling1()) {
                                positionWorkDetailsImport.setUnGambling1(positionWorkDetailsImport2.getUnGambling1().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getGambling2()) {
                                positionWorkDetailsImport.setGambling2(positionWorkDetailsImport2.getGambling2().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling2()) {
                                positionWorkDetailsImport.setUnGambling2(positionWorkDetailsImport2.getUnGambling2().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getGambling3()) {
                                positionWorkDetailsImport.setGambling3(positionWorkDetailsImport2.getGambling3().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling3()) {
                                positionWorkDetailsImport.setUnGambling3(positionWorkDetailsImport2.getUnGambling3().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getGambling4()) {
                                positionWorkDetailsImport.setGambling4(positionWorkDetailsImport2.getGambling4().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling4()) {
                                positionWorkDetailsImport.setUnGambling4(positionWorkDetailsImport2.getUnGambling4().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getGambling5()) {
                                positionWorkDetailsImport.setGambling5(positionWorkDetailsImport2.getGambling5().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling5()) {
                                positionWorkDetailsImport.setUnGambling5(positionWorkDetailsImport2.getUnGambling5().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getGambling6()) {
                                positionWorkDetailsImport.setGambling6(positionWorkDetailsImport2.getGambling6().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling6()) {
                                positionWorkDetailsImport.setUnGambling6(positionWorkDetailsImport2.getUnGambling6().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getGambling7()) {
                                positionWorkDetailsImport.setGambling7(positionWorkDetailsImport2.getGambling7().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling7()) {
                                positionWorkDetailsImport.setUnGambling7(positionWorkDetailsImport2.getUnGambling7().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getGambling8()) {
                                positionWorkDetailsImport.setGambling8(positionWorkDetailsImport2.getGambling8().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling8()) {
                                positionWorkDetailsImport.setUnGambling8(positionWorkDetailsImport2.getUnGambling8().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getGambling9()) {
                                positionWorkDetailsImport.setGambling9(positionWorkDetailsImport2.getGambling9().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling9()) {
                                positionWorkDetailsImport.setUnGambling9(positionWorkDetailsImport2.getUnGambling9().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getGambling0()) {
                                positionWorkDetailsImport.setGambling0(positionWorkDetailsImport2.getGambling0().longValue());
                            }
                            if (null != positionWorkDetailsImport2.getUnGambling0()) {
                                positionWorkDetailsImport.setUnGambling0(positionWorkDetailsImport2.getUnGambling0().longValue());
                            }

                            tocs.add(positionWorkDetailsImport);
                        }
                    }
                }
                List<PositionWorkDetailsImport> tocs1 = new ArrayList<>(0);
                tocs1 = tocs;
                for (Integer seq : seqNum) {
                    tocs = tocs1.stream().filter(str -> seq.equals(str.getSeqNum())).collect(Collectors.toList());

                    if (null != tocs && tocs.size() > 0) {
                        List<ModulesBO> modulesBOList = new ArrayList<>(0);
                        PositionWorkDetails entity = BeanTransform.copyProperties(tocs.get(0), PositionWorkDetails.class, true);
                        entity = super.save(entity);
                        if (tocs.size() >= 2) {
                            int total = tocs.size();
                            for (int j = 1; j < 11; j++) {
                                //得到的模块进行判断,是否是相同的数据
                                for (int i = 0; i < total; i++) {
                                    PositionWorkDetailsImport temp1 = tocs.get(i);
                                    PositionWorkDetailsImport temp2 = new PositionWorkDetailsImport();
                                    if (i + 1 < total) {
                                        temp2 = tocs.get(i + 1);
                                    }
                                    if (null != temp1 && null != temp2) {
                                        Boolean tar = isRepeat(temp1, temp2, j);
                                        if (!tar) {
                                            List<ModulesBO> modulesBOList1 = getModulesBOList(temp1, temp2, tar, j);
                                            if (null != modulesBOList1 && modulesBOList1.size() > 0) {
                                                for (ModulesBO bo : modulesBOList1) {
                                                    modulesBOList.add(bo);
                                                }
                                                i++;
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (tocs.size() == 1) {
                            int total = tocs.size();
                            for (int j = 1; j < 11; j++) {
                                //得到的模块进行判断,是否是相同的数据
                                PositionWorkDetailsImport temp1 = tocs.get(0);
                                PositionWorkDetailsImport temp2 = new PositionWorkDetailsImport();
                                List<ModulesBO> modulesBOList1 = getModulesBOList(temp1, temp2, false, j);
                                if (null != modulesBOList1 && modulesBOList1.size() > 0) {
                                    for (ModulesBO bo : modulesBOList1) {
                                        modulesBOList.add(bo);
                                    }
                                }
                            }
                        }
                        modulesBOList.size();
                    }
                }

            }
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    //判断两个对象中的第j张模块表的数据是否相同
    private Boolean isRepeat(PositionWorkDetailsImport temp1, PositionWorkDetailsImport temp2, int j) throws SerException {
        //是否相同
        Boolean tar = true;
        List<ModulesBO> modulesBOList = new ArrayList<>(0);
        Field[] fields1 = temp1.getClass().getDeclaredFields();
        Field[] fields2 = temp2.getClass().getDeclaredFields();
        List<String> nameList1 = new ArrayList<>(0);
        List<String> nameList2 = new ArrayList<>(0);
        List<Class> typeList1 = new ArrayList<>(0);
        List<Class> typeList2 = new ArrayList<>(0);
        List<Object> valList1 = new ArrayList<>(0);
        List<Object> valList2 = new ArrayList<>(0);
        try {
//            for (int j = 0; j < 10; j++) {
            for (Field fi : fields1) {
                nameList1.add(fi.getName());
                typeList1.add(fi.getType());
                fi.setAccessible(true); // 设置些属性是可以访问的
                Object val = fi.get(temp1);
                valList1.add(val);
            }
            for (Field fi : fields2) {
                nameList2.add(fi.getName());
                typeList2.add(fi.getType());
                fi.setAccessible(true); // 设置些属性是可以访问的
                Object val = fi.get(temp2);
                valList2.add(val);
            }
            ModulesImportTempBO modulesImportTempBO1 = new ModulesImportTempBO();
            ModulesImportTempBO modulesImportTempBO2 = new ModulesImportTempBO();
            Field[] fields3 = modulesImportTempBO1.getClass().getDeclaredFields();
            Field[] fields4 = modulesImportTempBO2.getClass().getDeclaredFields();

            int z = 33 + (j - 1) * 19;//到模块子表的长度
            for (Field fi : fields3) {
                if (z < 33 + (j - 1) * 19 + 8) {
                    fi.setAccessible(true); // 设置些属性是可以访问的
                    fi.set(modulesImportTempBO1, valList1.get(z));
                }
                z++;
            }

            z = 33 + (j - 1) * 19;//到模块子表的长度
            for (Field fi : fields4) {
                if (z < 33 + (j - 1) * 19 + 8) {
                    fi.setAccessible(true); // 设置些属性是可以访问的
                    fi.set(modulesImportTempBO2, valList2.get(z));
                }
                z++;
            }

            if (isCheckNull(modulesImportTempBO1) && !isCheckNull(modulesImportTempBO2)) {
                tar = false;
            } else if (isCheckNull(modulesImportTempBO1) && isCheckNull(modulesImportTempBO2)) {
                // 第j张模块表的第一个字段--->模块表最后一个字段
                z = 33 + (j - 1) * 19;
                for (; z < 33 + (j - 1) * 19 + 8; z++) {
                    Object object1 = valList1.get(z);
                    Object object2 = valList2.get(z);
                    if (tar && !object1.equals(object2)) {
                        tar = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tar;
    }

    private List<ModulesBO> getModulesBOList(PositionWorkDetailsImport temp1, PositionWorkDetailsImport temp2, Boolean tar, int j) throws SerException {
        try {
            List<ModulesBO> modulesBOList = new ArrayList<>(0);
            Field[] fields1 = temp1.getClass().getDeclaredFields();
            Field[] fields2 = temp2.getClass().getDeclaredFields();
            List<String> nameList1 = new ArrayList<>(0);
            List<String> nameList2 = new ArrayList<>(0);
            List<Class> typeList1 = new ArrayList<>(0);
            List<Class> typeList2 = new ArrayList<>(0);
            List<Object> valList1 = new ArrayList<>(0);
            List<Object> valList2 = new ArrayList<>(0);
            for (Field fi : fields1) {
                nameList1.add(fi.getName());
                typeList1.add(fi.getType());
                fi.setAccessible(true); // 设置些属性是可以访问的
                Object val = fi.get(temp1);
                valList1.add(val);
            }
            for (Field fi : fields2) {
                nameList2.add(fi.getName());
                typeList2.add(fi.getType());
                fi.setAccessible(true); // 设置些属性是可以访问的
                Object val = fi.get(temp2);
                valList2.add(val);
            }

            //如果模块表属性值相同,则该条数据的该模块与下一条数据的该模块相同
            if (tar) {
                ModulesBO modulesBO = transToModuleBO(valList1, valList2, tar, j);
                if (null != modulesBO) {
                    modulesBOList.add(modulesBO);
                }
            } else {
                ModulesBO modulesBO1 = transToModuleBO(valList1, valList2, tar, j);
                if (null != modulesBO1) {
                    modulesBOList.add(modulesBO1);
                }
                ModulesBO modulesBO2 = transToModuleBO(valList2, valList2, tar, j);
                if (null != modulesBO2 && modulesBO2.getIndicatorBOList() != null) {
                    modulesBOList.add(modulesBO2);
                }
            }
            return modulesBOList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private List<ModulesBO> getModulesBOList2(PositionWorkDetailsImport temp1, PositionWorkDetailsImport temp2) throws
//            SerException {
//        List<ModulesBO> modulesBOList = new ArrayList<>(0);
//        Field[] fields1 = temp1.getClass().getDeclaredFields();
//        Field[] fields2 = temp2.getClass().getDeclaredFields();
//        List<String> nameList1 = new ArrayList<>(0);
//        List<String> nameList2 = new ArrayList<>(0);
//        List<Class> typeList1 = new ArrayList<>(0);
//        List<Class> typeList2 = new ArrayList<>(0);
//        List<Object> valList1 = new ArrayList<>(0);
//        List<Object> valList2 = new ArrayList<>(0);
//        try {
//            for (int j = 0; j < 10; j++) {
//                for (Field fi : fields1) {
//                    nameList1.add(fi.getName());
//                    typeList1.add(fi.getType());
//                    fi.setAccessible(true); // 设置些属性是可以访问的
//                    Object val = fi.get(temp1);
//                    valList1.add(val);
//                }
//                for (Field fi : fields2) {
//                    nameList2.add(fi.getName());
//                    typeList2.add(fi.getType());
//                    fi.setAccessible(true); // 设置些属性是可以访问的
//                    Object val = fi.get(temp2);
//                    valList2.add(val);
//                }
//
//                ModulesImportTempBO modulesImportTempBO1 = new ModulesImportTempBO();
//                Field[] fields3 = modulesImportTempBO1.getClass().getDeclaredFields();
//
//                // 第j张模块表的第一个字段--->模块表最后一个字段
//                int z = 33 + (j - 1) * 19;
//                //是否相同
//                Boolean tar = true;
//                for (; z < 33 + (j - 1) * 19 + 8; z++) {
//                    Object object1 = valList1.get(z);
//                    Object object2 = valList2.get(z);
//                    if (tar && null != object1 && !"".equals(object1)) {
//                        tar = false;
//                    }
////                    if (null == object1 || null == object2 || object1.equals("") || object2.equals("")) {
////                        tar = false;
////                    }
//                    //如果模块表属性值相同,则该条数据的该模块与下一条数据的该模块相同
//                    if (tar) {
//                        ModulesBO modulesBO = transToModuleBO(fields3, z, j, modulesImportTempBO1, valList1, valList2, tar);
//                        if (null != modulesBO) {
//                            modulesBOList.add(modulesBO);
//                        }
//                    } else {
//                        ModulesBO modulesBO1 = transToModuleBO(fields3, z, j, modulesImportTempBO1, valList1, valList2, tar);
//                        if (null != modulesBO1) {
//                            modulesBOList.add(modulesBO1);
//                        }
//                        ModulesBO modulesBO2 = transToModuleBO(fields3, z, j, modulesImportTempBO1, valList2, valList2, tar);
//                        if (null != modulesBO2) {
//                            modulesBOList.add(modulesBO2);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return modulesBOList;
//    }

    //将属性值相同的模块表和两个指标表放到同一个modulesBO中
    private ModulesBO transToModuleBO(List<Object> valList1, List<Object> valList2, Boolean tar, int j) throws SerException {
        try {
//            if (!tar) {
//                valList1 = valList2;
//            }
            List<IndicatorBO> indicatorBOs = new ArrayList<>(0);
            ModulesBO modulesBO = new ModulesBO();
            ModulesImportTempBO modulesImportTempBO1 = new ModulesImportTempBO();
            ModulesImportTempBO modulesImportTempBO2 = new ModulesImportTempBO();
            Field[] fields3 = modulesImportTempBO1.getClass().getDeclaredFields();
            Field[] fields4 = modulesImportTempBO2.getClass().getDeclaredFields();

            //把模块的数据跟模块子表的数据存放到ModulesBO里面
            int z = 33 + (j - 1) * 19;//到模块子表的长度
            for (Field fi : fields3) {
                if (z < 33 + (j - 1) * 19 + 8) {
                    fi.setAccessible(true); // 设置些属性是可以访问的
                    fi.set(modulesImportTempBO1, valList1.get(z));
                }
                z++;
            }
            if (isCheckNull(modulesImportTempBO1)) {
                BeanUtils.copyProperties(modulesImportTempBO1, modulesBO);
                switch (j) {
                    case 1:
                        modulesBO.setName("规划模块");
                        break;
                    case 2:
                        modulesBO.setName("福利模块");
                        break;
                    case 3:
                        modulesBO.setName("素养模块");
                        break;
                    case 4:
                        modulesBO.setName("预算模块");
                        break;
                    case 5:
                        modulesBO.setName("资金模块");
                        break;
                    case 6:
                        modulesBO.setName("财务模块");
                        break;
                    case 7:
                        modulesBO.setName("客户管理模块");
                        break;
                    case 8:
                        modulesBO.setName("进度管理模块");
                        break;
                    case 9:
                        modulesBO.setName("业务管理模块");
                        break;
                    case 10:
                        modulesBO.setName("项目组模块");
                        break;
                    default:
                        break;
                }

                //存放第一张指标表的数据
                IndicatorBO indicatorBO = transInditorToModulesBO(j, valList1, modulesBO);
                if (null != indicatorBO) {
                    indicatorBOs.add(indicatorBO);
                    modulesBO.setIndicatorBOList(indicatorBOs);
                }
                //如果tar=true,表示模块表的属性值是相同的,需要存放第二张指标表的数据
                //存放第二张指标表的数据
                if (tar) {
                    IndicatorBO indicatorBO1 = transInditorToModulesBO(j, valList2, modulesBO);
                    if (null != indicatorBO1) {
                        indicatorBOs.add(indicatorBO);
                        modulesBO.setIndicatorBOList(indicatorBOs);
                    }
                }
            }
            return modulesBO;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    //将属性值相同的模块表和两个指标表放到同一个modulesBO中
//    private ModulesBO transToModuleBO1(Field[] fields3, int z, int j, ModulesImportTempBO modulesImportTempBO1, List<Object> valList1, List<Object> valList2, Boolean tar) throws SerException {
//        try {
//            ModulesBO modulesBO = new ModulesBO();
//            //把模块的数据跟模块子表的数据存放到ModulesBO里面
//            for (Field fi : fields3) {
////          z = 33 + (j - 1) * 19;//到下一个模块的长度
//                z = 33 + (j - 1) * 19;//到模块子表的长度
//                for (; z < 33 + (j - 1) * 19 + 8; z++) {
//                    fi.setAccessible(true); // 设置些属性是可以访问的
//                    fi.set(modulesImportTempBO1, valList1.get(z));
//                }
//            }
//            if (isCheckNull(modulesImportTempBO1)) {
//                BeanUtils.copyProperties(modulesImportTempBO1, modulesBO);
//                //存放第一张指标表的数据
//                IndicatorBO indicatorBO = transInditorToModulesBO(z, j, valList1, modulesBO);
//                if (null != indicatorBO) {
//                    modulesBO.getIndicatorBOList().add(indicatorBO);
//                }
//                //如果tar=true,表示模块表的属性值是相同的,需要存放第二张指标表的数据
//                if (tar) {
//                    //存放第二张指标表的数据
//                    IndicatorBO indicatorBO1 = transInditorToModulesBO(z, j, valList2, modulesBO);
//                    if (null != indicatorBO1) {
//                        modulesBO.getIndicatorBOList().add(indicatorBO1);
//                    }
//                }
//                return modulesBO;
//            }
//
//            return null;
//        } catch (Exception e) {
//            throw new SerException(e.getMessage());
//        }
//    }

    /**
     * 将两个指标表的数据放到modulesBO中
     */
    private IndicatorBO transInditorToModulesBO(int j, List<Object> valList1, ModulesBO modulesBO) throws SerException {
        try {
            //把模块表下的指标表存放到modulesBO中
            IndicatorImportTempBO indicatorImportTempBO1 = new IndicatorImportTempBO();
            Field[] fields5 = indicatorImportTempBO1.getClass().getDeclaredFields();

            int z = 33 + (j - 1) * 19 + 8;//到模块子表的长度
            //把模块的子表(指标表)的数据存放到ModulesBO里面
            for (Field fi : fields5) {
//              z = 33 + (j - 1) * 19;//到下一个模块的长度
                if (z < 33 + (j - 1) * 19 + 19) {
                    fi.setAccessible(true); // 设置些属性是可以访问的
                    fi.set(indicatorImportTempBO1, valList1.get(z));
                }
                z++;
            }
            IndicatorBO indicatorBO = new IndicatorBO();
            if (isCheckNull(indicatorImportTempBO1)) {
                BeanUtils.copyProperties(indicatorImportTempBO1, indicatorBO);
                return indicatorBO;
            }
            return null;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    /**
     * 将两个指标表的数据放到modulesBO中
     */
    private IndicatorBO transInditorToModulesBO1(int z, int j, List<Object> valList1, ModulesBO modulesBO) throws SerException {
        try {
            //把模块表下的指标表存放到modulesBO中
            IndicatorImportTempBO indicatorImportTempBO1 = new IndicatorImportTempBO();
            Field[] fields5 = indicatorImportTempBO1.getClass().getDeclaredFields();

            //把模块的子表(指标表)的数据存放到ModulesBO里面
            for (Field fi : fields5) {
//              z = 33 + (j - 1) * 19;//到下一个模块的长度
                z = 33 + (j - 1) * 19 + 8;//到模块子表的长度
                for (; z < 33 + (j - 1) * 19 + 11; z++) {
                    fi.setAccessible(true); // 设置些属性是可以访问的
                    fi.set(indicatorImportTempBO1, valList1.get(z));
                }
            }
            IndicatorBO indicatorBO = new IndicatorBO();
            if (isCheckNull(indicatorImportTempBO1)) {
                BeanUtils.copyProperties(indicatorImportTempBO1, indicatorBO);
                return indicatorBO;
            }
            return null;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }


    //判断某个类的所有属性是否都为空
    private Boolean isCheckNull(Object obj) {
        Boolean bool = false;
        if (null == obj) {
            bool = false;
        }
        for (Field f : obj.getClass().getDeclaredFields()) {
            String name = f.getName(); // 获取属性的名字
            name = name.substring(0, 1).toUpperCase() + name.substring(1);// 将属性的首字符大写，方便构造get，set方法
            try {
                Method m = obj.getClass().getMethod("get" + name);
                Object value = m.invoke(obj);// 调用getter方法获取属性值
                if (null != value && !value.equals("")) {    //判断该属性值是否为空
                    bool = true;
                }
            } catch (Exception e) {

            }
        }
        return bool;
    }

    //            @Override
    public byte[] exportExcelTHX() throws SerException {
        int maxs = 0;
        int subMax = 0;
        List<PositionWorkDetails> list = super.findAll();
        List<PositionWorkDetailsExport> positionWorkDetailsExports = new ArrayList<>();
        //子表的最大长度
        List<Integer> sublist = new ArrayList<>(0);
        //子表中子表的最大长度
        List<Integer> maxList = new ArrayList<>();
        //用于判断是否有重复的模块
        List<String> nameList = new ArrayList<>(0);
        List<Integer> namelengthList = new ArrayList<>(0);
        List<String> nameList1 = new ArrayList<>(0);
        Map<String, Integer> maps = new HashedMap<>(0);
        Map<String, Integer> maps1 = new HashedMap<>(0);
        //子表中子表的所有长度
        List<Integer> nums = new ArrayList<>(0);

        if (list != null && list.size() > 0) {
            //子表中的所有长度
            List<Integer> subNums = new ArrayList<>(0);
            for (PositionWorkDetails positionWorkDetails : list) {
                ModulesDTO modulesDTO = new ModulesDTO();
                modulesDTO.getConditions().add(Restrict.eq("workDetailsId", positionWorkDetails.getId()));
                List<Modules> moduleses = modulesSer.findByCis(modulesDTO);
                if (null != moduleses && moduleses.size() > 0) {//14
                    for (Modules modules : moduleses) {
                        String name = modules.getName();
                        if (null != nameList && nameList.size() > 0 && null != maps && maps.size() > 0) {
                            if (nameList.contains(name)) {
                                IndicatorDTO indicatorDTO = new IndicatorDTO();
                                indicatorDTO.getConditions().add(Restrict.eq("modulesId", modules.getId()));
                                List<Indicator> indicators = indicatorSer.findByCis(indicatorDTO);
                                int number = indicators == null ? 0 : indicators.size();
                                int number1 = maps.get(name) + number;
                                maps.put(name, number1);
                                maps1.put(name, 1 + 1);
                            } else {
                                nameList.add(name);
                                IndicatorDTO indicatorDTO = new IndicatorDTO();
                                indicatorDTO.getConditions().add(Restrict.eq("modulesId", modules.getId()));
                                List<Indicator> indicators = indicatorSer.findByCis(indicatorDTO);
                                int number = indicators == null ? 0 : indicators.size();
                                maps.put(name, number);
                                maps1.put(name, 1);
                            }
                        } else {
                            nameList.add(name);
                            IndicatorDTO indicatorDTO = new IndicatorDTO();
                            indicatorDTO.getConditions().add(Restrict.eq("modulesId", modules.getId()));
                            List<Indicator> indicators = indicatorSer.findByCis(indicatorDTO);
                            int number = indicators == null ? 0 : indicators.size();
                            maps.put(name, number);
                            maps1.put(name, 1);
                        }
                    }
                }
                nameList1 = getNameList();
                if (null != maps1 && maps1.size() > 0) {
                    Set<Map.Entry<String, Integer>> entry = maps1.entrySet();
                    Iterator<Map.Entry<String, Integer>> it = entry.iterator();
                    while (it.hasNext()) {
                        //将键值关系取出存入Map.Entry这个映射关系集合接口中
                        Map.Entry<String, Integer> me = it.next();
                        //使用Map.Entry中的方法获取键和值
                        String key = me.getKey();
                        Integer value = me.getValue();
                        subNums.add(value);
                    }
                }
                if (null != maps && maps.size() > 0) {
                    Set<Map.Entry<String, Integer>> entry = maps.entrySet();
                    Iterator<Map.Entry<String, Integer>> it = entry.iterator();
                    while (it.hasNext()) {
                        //将键值关系取出存入Map.Entry这个映射关系集合接口中
                        Map.Entry<String, Integer> me = it.next();
                        //使用Map.Entry中的方法获取键和值
                        String key = me.getKey();
                        Integer value = me.getValue();
                        nums.add(value);
                    }
                }
            }
            if (null != subNums && subNums.size() > 0) {
                subMax = subNums.stream().max(Comparator.comparing(u -> u)).get();
            }
            sublist.add(subMax);

            Integer seqNum = 1;//排序
            for (PositionWorkDetails positionWorkDetails : list) {
                ModulesDTO modulesDTO = new ModulesDTO();
                modulesDTO.getConditions().add(Restrict.eq("workDetailsId", positionWorkDetails.getId()));
                List<Modules> moduleses = modulesSer.findByCis(modulesDTO);

//                if (null != moduleses && moduleses.size() > 0) {
//                    for (Modules modules : moduleses) {
//                        IndicatorDTO indicatorDTO = new IndicatorDTO();
//                        indicatorDTO.getConditions().add(Restrict.eq("modulesId", modules.getId()));
//                        List<Indicator> indicators = indicatorSer.findByCis(indicatorDTO);
//                        int number = indicators == null ? 0 : indicators.size();
//                        nums.add(number);
//                    }
//                }
                if (null != nums && nums.size() > 0) {
                    maxs = nums.stream().max(Comparator.comparing(u -> u)).get();
                }
                maxList.add(maxs);
                if (null != moduleses && moduleses.size() > 0) {
                    PositionWorkDetailsExport excel = new PositionWorkDetailsExport();
                    excel.setSeqNum(seqNum);

                    //存放相同模块名的数据
                    List<Modules> modulesListSame = new ArrayList<>(0);
                    modulesListSame = moduleses;

                    BeanTransform.copyProperties(positionWorkDetails, excel);
                    for (int i = 0; i < maxs; i++) {
                        int j = 1;

                        ModuleAndIndicatorExport moduleAndIndicatorExport = new ModuleAndIndicatorExport();
                        nameList = new ArrayList<>();
                        namelengthList = new ArrayList<>();
                        List<Modules> temps = new ArrayList<>(0);
                        int nameLengthIndex = 0;
                        for (Modules modules : moduleses) {
                            String name = modules.getName();
                            if (null != nameList && nameList.size() > 0) {
                                if (nameList.contains(name)) {
                                    //相同模快名才会进来
//                                    excel = getData(excel, modules, j, i, positionWorkDetails, moduleAndIndicatorExport);
                                    temps.add(modules);
                                    //存放记录相同名指标的长度
//                                    if () {
//                                        namelengthList.add();
//                                    }
                                } else {
                                    //之前所有模快名与当前模快名不一样,则进来
                                    nameList.add(name);
                                    excel = getData(excel, modules, j, i, positionWorkDetails, moduleAndIndicatorExport);

                                }
                            } else {
                                //只有moduleses[0]进来,后面不会进来
                                nameList.add(name);
                                excel = getData(excel, modules, j, i, positionWorkDetails, moduleAndIndicatorExport);
                            }
                        }
//                        positionWorkDetailsExports.add(excel);
                        int c = temps.size();
                        for (int x = c; x > 0; ) {
                            excel = new PositionWorkDetailsExport();
                            excel.setSeqNum(seqNum);
                            BeanTransform.copyProperties(positionWorkDetails, excel);

                            moduleAndIndicatorExport = new ModuleAndIndicatorExport();
                            nameList = new ArrayList<>();
                            moduleses = temps;
                            temps = new ArrayList<>(0);
                            for (Modules modules : moduleses) {
                                String name = modules.getName();
                                if (null != nameList && nameList.size() > 0) {
                                    if (nameList.contains(name)) {
//                                    excel = getData(excel, modules, j, i, positionWorkDetails, moduleAndIndicatorExport);
                                        temps.add(modules);
                                    } else {
                                        nameList.add(name);
                                        if (nameList1.contains(name)) {
                                            excel = getData(excel, modules, j, i, positionWorkDetails, moduleAndIndicatorExport);
                                        } else {
                                            excel = null;
                                        }
                                    }
                                } else {
                                    nameList.add(name);
                                    if (nameList1.contains(name)) {
                                        excel = getData(excel, modules, j, i, positionWorkDetails, moduleAndIndicatorExport);
                                    } else {
                                        excel = null;
                                    }
                                }
                            }
                            if (null != excel) {
                                positionWorkDetailsExports.add(excel);
                            }
                            x = temps.size();
                        }

                    }
                    if (null != excel) {
                        positionWorkDetailsExports.add(excel);
                    }
                }
                seqNum += 1;
            }
        }

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(positionWorkDetailsExports, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            int rowSize = list.size();
//            int rowSize = positionWorkDetailsExports.size();
            List<Field> fields = ClazzUtils.getFields(PositionWorkDetailsExport.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);

            int index = 1;
//            for (int j = 0; j < rowSize; j++) {
//                if (null != maxList && maxList.size() > 0) {
//                    int mergeRowCount = maxList.get(j);
//                    if (mergeRowCount != 1) {
//                        int firstRow = index;
//                        int lastRow = 0;
//                        lastRow = firstRow + mergeRowCount - 1;
//                        for (int i = 0; i < 1; i++) {
//                            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
//                        }
//                        index = lastRow + 1;
//                    }
//                }
//            }

            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }

    /**
     * @param excel                    最终导出的数据
     * @param modules                  模快表
     * @param j                        孙子表的序号,规则参照方法getNum()
     * @param i                        上面的循环数
     * @param positionWorkDetails      最外层的主表
     * @param moduleAndIndicatorExport 起转换作用的中间类
     * @return
     * @throws SerException
     */
    private PositionWorkDetailsExport getData(PositionWorkDetailsExport excel, Modules modules, int j,
                                              int i, PositionWorkDetails positionWorkDetails, ModuleAndIndicatorExport moduleAndIndicatorExport) throws
            SerException {
        j = getNum(modules, j);
        if (j != 0) {
            IndicatorDTO indicatorDTO = new IndicatorDTO();
            indicatorDTO.getConditions().add(Restrict.eq("modulesId", modules.getId()));
            List<Indicator> indicators = indicatorSer.findByCis(indicatorDTO);
            int number = indicators == null ? 0 : indicators.size();
            Indicator indicator = number > i ? indicators.get(i) : new Indicator();
            //最外层主表(父表)处理
            BeanTransform.copyProperties(positionWorkDetails, excel);

            //处理儿子表
            BeanTransform.copyProperties(modules, moduleAndIndicatorExport);
            //处理孙子表
            BeanTransform.copyProperties(indicator, moduleAndIndicatorExport);

            //转换
            excel = transForm(excel, moduleAndIndicatorExport, j);
//                            j += 1;
        }
        return excel;
    }

    //    @Override
    public byte[] exportExcel1() throws SerException {
        int maxs = 0;
        List<PositionWorkDetails> list = super.findAll();
        List<PositionWorkDetailsExport> positionWorkDetailsExports = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            Integer seqNum = 1;//排序
            for (PositionWorkDetails positionWorkDetails : list) {
                ModulesDTO modulesDTO = new ModulesDTO();
                modulesDTO.getConditions().add(Restrict.eq("workDetailsId", positionWorkDetails.getId()));
                List<Modules> moduleses = modulesSer.findByCis(modulesDTO);
                List<Integer> nums = new ArrayList<>(0);
                if (null != moduleses && moduleses.size() > 0) {
                    for (Modules modules : moduleses) {
                        IndicatorDTO indicatorDTO = new IndicatorDTO();
                        indicatorDTO.getConditions().add(Restrict.eq("modulesId", modules.getId()));
                        List<Indicator> indicators = indicatorSer.findByCis(indicatorDTO);
                        int number = indicators == null ? 0 : indicators.size();
                        nums.add(number);
                    }
                }
                if (null != nums && nums.size() > 0) {
                    maxs = nums.stream().max(Comparator.comparing(u -> u)).get();
                    maxList.add(maxs);
                }
                if (null != moduleses && moduleses.size() > 0) {
                    PositionWorkDetailsExport excel = new PositionWorkDetailsExport();
                    excel.setSeqNum(seqNum);
                    BeanTransform.copyProperties(positionWorkDetails, excel);
                    for (int i = 0; i < maxs; i++) {
                        int j = 1;
                        for (Modules modules : moduleses) {
                            ModuleAndIndicatorExport moduleAndIndicatorExport = new ModuleAndIndicatorExport();
                            IndicatorDTO indicatorDTO = new IndicatorDTO();
                            indicatorDTO.getConditions().add(Restrict.eq("modulesId", modules.getId()));
                            List<Indicator> indicators = indicatorSer.findByCis(indicatorDTO);
                            int number = indicators == null ? 0 : indicators.size();
                            Indicator indicator = number > i ? indicators.get(i) : new Indicator();
                            BeanTransform.copyProperties(positionWorkDetails, excel);

                            BeanTransform.copyProperties(indicator, moduleAndIndicatorExport);
                            BeanTransform.copyProperties(modules, moduleAndIndicatorExport);
                            //转换
                            excel = transForm(excel, moduleAndIndicatorExport, j);
                            j += 1;
                        }
                    }
                    positionWorkDetailsExports.add(excel);
                }
                seqNum += 1;
            }
        }

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(positionWorkDetailsExports, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            int rowSize = list.size();
            List<Field> fields = ClazzUtils.getFields(PositionWorkDetailsExport.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
            int index = 1;
            for (int j = 0; j < rowSize; j++) {
                if (null != maxList && maxList.size() > 0) {
                    int mergeRowCount = maxList.get(j);
                    if (mergeRowCount != 1) {
                        int firstRow = index;
                        int lastRow = 0;
                        lastRow = firstRow + mergeRowCount - 1;
                        for (int i = 0; i < 33 && i > 55; i++) {
                            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                        }
                        index = lastRow + 1;
                    }
                }
            }

            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }

    private Integer getNum(Modules modules, int j) throws SerException {
        switch (modules.getName()) {
            case "规划模块":
                j = 1;
                break;
            case "福利模块":
                j = 2;
                break;
            case "素养模块":
                j = 3;
                break;
            case "预算模块":
                j = 4;
                break;
            case "资金模块":
                j = 5;
                break;
            case "财务模块":
                j = 6;
                break;
            case "客户管理模块":
                j = 7;
                break;
            case "进度管理模块":
                j = 8;
                break;
            case "业务管理模块":
                j = 9;
                break;
            case "项目组模块":
                j = 10;
                break;
            default:
                j = 0;
                break;
        }
        return j;
    }

    private PositionWorkDetailsExport transForm(PositionWorkDetailsExport excel, ModuleAndIndicatorExport
            moduleAndIndicatorExport, int j) throws SerException {
        Field[] fields = moduleAndIndicatorExport.getClass().getDeclaredFields();
        List<String> nameList = new ArrayList<>(0);
        List<Class> typeList = new ArrayList<>(0);
        List<Object> valList = new ArrayList<>(0);
        try {
            for (Field fi : fields) {
                nameList.add(fi.getName() + j);
                typeList.add(fi.getType());
                fi.setAccessible(true); // 设置些属性是可以访问的
                Object val = fi.get(moduleAndIndicatorExport);
                System.out.println(val);
                valList.add(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nameList + "====" + typeList);
        Field[] fields1 = excel.getClass().getDeclaredFields();
        try {
            int i = 3 + (j - 1) * 4;
            int z = 0;
            for (Field fi : fields1) {
                if (z < 4) {
                    if (fi.getName().equals(fields1[i].getName())) {
                        if (null != valList.get(z)) {
                            fi.setAccessible(true); // 设置些属性是可以访问的
                            fi.set(excel, valList.get(z));
                        }
                        z += 1;
                        i += 1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excel;
    }

    private PositionWorkDetailsExport transForm1(PositionWorkDetailsExport excel, ModuleAndIndicatorExport
            moduleAndIndicatorExport, int j) throws SerException {
        Field[] fields = moduleAndIndicatorExport.getClass().getDeclaredFields();
        List<String> nameList = new ArrayList<>(0);
        List<Class> typeList = new ArrayList<>(0);
        List<Object> valList = new ArrayList<>(0);
        try {
            for (Field fi : fields) {
                nameList.add(fi.getName() + j);
                typeList.add(fi.getType());
                fi.setAccessible(true); // 设置些属性是可以访问的
                Object val = fi.get(moduleAndIndicatorExport);
                System.out.println(val);
                valList.add(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nameList + "====" + typeList);

        List<Model> mos = new ArrayList<>(0);
        for (int i = 0; i < nameList.size() - 1; i++) {
            Model mo = new Model();
            mo.setFieldName(nameList.get(i));
            mo.setSwapCaseName("ModuleAndIndicatorExport");
            mo.setAnnotation(mo.getFieldName().substring(0, 1).toUpperCase() + mo.getFieldName().substring(1, mo.getFieldName().length()).toLowerCase());
            mo.setType(typeList.get(i).toString().substring(typeList.get(i).toString().lastIndexOf(".") + 1, typeList.get(i).toString().length()));
            mos.add(mo);
        }
        createModel(mos, "create");
        ModuleAndIndicatorExportBO bo = new ModuleAndIndicatorExportBO();
        Field[] fields1 = bo.getClass().getDeclaredFields();
        try {
            int i = 0;
            for (Field fi : fields1) {
                if (null != valList.get(i)) {
                    fi.setAccessible(true); // 设置些属性是可以访问的
                    fi.set(bo, valList.get(i));
                }
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanTransform.copyProperties(bo, excel);
        return excel;
    }

    private List<String> getNameList() throws SerException {
        List<String> list = new ArrayList<>(0);
        list.add("规划模块");
        list.add("福利模块");
        list.add("素养模块");
        list.add("预算模块");
        list.add("资金模块");
        list.add("财务模块");
        list.add("客户管理模块");
        list.add("进度管理模块");
        list.add("业务管理模块");
        list.add("项目组模块");
        return list;
    }

//    public static void main(String args[]) {
//        ModuleAndIndicatorExport moduleAndIndicatorExport = new ModuleAndIndicatorExport();
//        moduleAndIndicatorExport.setAssessment("qqqqqqqqqqqqqqqqqqqq");
//        Field[] fields = moduleAndIndicatorExport.getClass().getDeclaredFields();
////        String[] fieldNames = new String[fields.length];
////        String[] type = new String[fields.length];
//        List<String> nameList = new ArrayList<>(0);
//        List<Class> typeList = new ArrayList<>(0);
//        List<Object> valList = new ArrayList<>(0);
//        try {
//            for (Field fi : fields) {
//                nameList.add(fi.getName() + 2);
//                typeList.add(fi.getType());
//                fi.setAccessible(true); // 设置些属性是可以访问的
//                Object val = fi.get(moduleAndIndicatorExport);
//                System.out.println(val);
//                valList.add(val);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(nameList + "====" + typeList);
//
//        List<Model> mos = new ArrayList<>(0);
//        for (int i = 0; i < nameList.size() - 1; i++) {
//            Model mo = new Model();
////            mo.setAnnotation("ModuleAndIndicatorExport");
//            mo.setFieldName(nameList.get(i));
//            mo.setSwapCaseName("ModuleAndIndicatorExport");
//            mo.setAnnotation(mo.getFieldName().substring(0, 1).toUpperCase() + mo.getFieldName().substring(1, mo.getFieldName().length()).toLowerCase());
//            mo.setType(typeList.get(i).toString().substring(typeList.get(i).toString().lastIndexOf(".") + 1, typeList.get(i).toString().length()));
//            mos.add(mo);
//        }
//        createModel(mos, "create");
//        ModuleAndIndicatorExportBO bo = new ModuleAndIndicatorExportBO();
//        Field[] fields1 = bo.getClass().getDeclaredFields();
//        try {
////            int i = 0;
////            for (Field fi : fields1) {
////                if (null != valList.get(i)) {
////                    fi.setAccessible(true); // 设置些属性是可以访问的
////                    fi.set(bo, valList.get(i));
////                }
////                i += 1;
////            }
//            int i = 10;
//            for (Field fi : fields1) {
//                if (fi.getName().equals(fields1[i].getName())) {
//                    int z = 0;
//                    if (null != valList.get(i)) {
//                        fi.setAccessible(true); // 设置些属性是可以访问的
//                        fi.set(bo, valList.get(i));
//                    }
//                    z += 1;
//                    i += 1;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(bo);
//    }

    //柱状图数据
    private OptionBO getOptionBO(String text_1, List<ManagerBO> list) throws SerException {
        //增加合计
        ManagerBO bo = new ManagerBO();
        bo.setDepartments("合计");
        bo.setProjectNum(list.stream().mapToLong(ManagerBO::getProjectNum).sum());
        bo.setPositionNum(list.stream().mapToLong(ManagerBO::getPositionNum).sum());
        bo.setNoEmployeesNum(list.stream().mapToLong(ManagerBO::getNoEmployeesNum).sum());
        bo.setPartjobNum(list.stream().mapToLong(ManagerBO::getPartjobNum).sum());
        bo.setJobDescriptionNum(list.stream().mapToLong(ManagerBO::getJobDescriptionNum).sum());
        bo.setNoJobNum(list.stream().mapToLong(ManagerBO::getNoJobNum).sum());
        bo.setMoveNum(list.stream().mapToLong(ManagerBO::getMoveNum).sum());
        bo.setRotationNum(list.stream().mapToLong(ManagerBO::getRotationNum).sum());
        bo.setWaitEntryNum(list.stream().mapToLong(ManagerBO::getWaitEntryNum).sum());
        bo.setEntryNum(list.stream().mapToLong(ManagerBO::getEntryNum).sum());
        bo.setDimissionNum(list.stream().mapToLong(ManagerBO::getDimissionNum).sum());
        bo.setWaitDimissionNum(list.stream().mapToLong(ManagerBO::getWaitDimissionNum).sum());
        list.add(bo);

        List<String> departList = list.stream().map(ManagerBO::getDepartments).collect(Collectors.toList());
        String[] text_3 = departList.toArray(new String[departList.size()]);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_2 = new String[]{"内部项目数", "岗位数",
                "岗位无任职人数", "兼任数", "已有岗位说明书",
                "无岗位说明书", "被调动人数", "通过（轮岗人数）", "待入职人数", "在职人员数", "已离职人员数", "待离职人数"};
        text_list2 = Arrays.stream(text_2).collect(Collectors.toList());
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (list != null && list.size() > 0) {
//            for (ManagerBO managerBO : list) {
//
////                text_list2.add(String.valueOf(managerBO.getDepartments()));
//
//                //柱状图数据
//                SeriesBO seriesBO = new SeriesBO();
//                seriesBO.setName(managerBO.());
//                seriesBO.setType("bar");
//
//                Integer[] number = new Integer[]{managerBO.getProjectNum().intValue(), managerBO.getPositionNum().intValue(),
//                        managerBO.getNoEmployeesNum().intValue(), managerBO.getPartjobNum().intValue(), managerBO.getJobDescriptionNum().intValue(),
//                        managerBO.getNoJobNum().intValue(), managerBO.getMoveNum().intValue(), managerBO.getRotationNum().intValue(),
//                        managerBO.getWaitEntryNum().intValue(), managerBO.getEntryNum().intValue(), managerBO.getDimissionNum().intValue(), managerBO.getWaitDimissionNum().intValue()};
//                seriesBO.setData(number);
//                seriesBOList.add(seriesBO);
//            }
            for (String str : text_list2) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(str);
                seriesBO.setType("bar");
                List<Integer> number = new ArrayList<>(0);
                for (ManagerBO managerBO : list) {
                    Integer i = 0;
                    if (str.equals("内部项目数")) {
                        Long j = managerBO.getProjectNum();
                        i = j.intValue();
                    }
                    if (str.equals("岗位数")) {
                        Long j = managerBO.getPositionNum();
                        i = j.intValue();
                    }
                    if (str.equals("岗位无任职人数")) {
                        Long j = managerBO.getNoEmployeesNum();
                        i = j.intValue();
                    }
                    if (str.equals("兼任数")) {
                        Long j = managerBO.getPartjobNum();
                        i = j.intValue();
                    }
                    if (str.equals("已有岗位说明书")) {
                        Long j = managerBO.getJobDescriptionNum();
                        i = j.intValue();
                    }
                    if (str.equals("无岗位说明书")) {
                        Long j = managerBO.getNoJobNum();
                        i = j.intValue();
                    }
                    if (str.equals("被调动人数")) {
                        Long j = managerBO.getMoveNum();
                        i = j.intValue();
                    }
                    if (str.equals("通过（轮岗人数）")) {
                        Long j = managerBO.getRotationNum();
                        i = j.intValue();
                    }
                    if (str.equals("待入职人数")) {
                        Long j = managerBO.getWaitEntryNum();
                        i = j.intValue();
                    }
                    if (str.equals("在职人员数")) {
                        Long j = managerBO.getEntryNum();
                        i = j.intValue();
                    }
                    if (str.equals("已离职人员数")) {
                        Long j = managerBO.getDimissionNum();
                        i = j.intValue();
                    }
                    if (str.equals("待离职人数")) {
                        Long j = managerBO.getWaitDimissionNum();
                        i = j.intValue();
                    }
                    number.add(i);
                }
                //柱状图数据
                Integer[] numbers = number.toArray(new Integer[number.size()]);
                seriesBO.setData(numbers);
                seriesBOList.add(seriesBO);
            }
        }

//        String[] text_2 = new String[text_list2.size()];
//        text_2 = text_list2.toArray(text_2);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        TooltipBO tooltipBO = new TooltipBO();
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private void searchCondition(PositionWorkDetailsDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getAngle())) {
            dto.getConditions().add(Restrict.eq("angle", dto.getAngle()));
        }
        if (StringUtils.isNotBlank(dto.getDimension())) {
            dto.getConditions().add(Restrict.eq("dimension", dto.getDimension()));
        }
        if (StringUtils.isNotBlank(dto.getClassification())) {
            dto.getConditions().add(Restrict.eq("classification", dto.getClassification()));
        }
        if (StringUtils.isNotBlank(dto.getProjectStage())) {
            dto.getConditions().add(Restrict.eq("projectStage", dto.getProjectStage()));
        }
        if (StringUtils.isNotBlank(dto.getFunction())) {
            dto.getConditions().add(Restrict.eq("function", dto.getFunction()));
        }
        if (StringUtils.isNotBlank(dto.getFrequency())) {
            dto.getConditions().add(Restrict.eq("frequency", dto.getFrequency()));
        }
        if (StringUtils.isNotBlank(dto.getTimeNode())) {
            dto.getConditions().add(Restrict.eq("timeNode", dto.getTimeNode()));
        }
    }

    private PositionWorkDetails transForm(PositionWorkDetails entity) throws SerException {
        String projectStage = entity.getProjectStage();
        projectStage = projectStage.substring(projectStage.length() - 1, projectStage.length());
        entity.setProjectStageNum(projectStage);

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("https://system.issp.bjike.com:8080/featurelist/v1/getPurpose/"+ entity.getFunction() + "");//线上
        HttpGet httpGet = new HttpGet("https://system.issp.bjike.com:8080/featurelist/v1/getPurpose/" + entity.getFunction() + "");//线上
//        HttpGet httpGet = new HttpGet("http://localhost:51654/featurelist/v1/getPurpose/" +entity.getFunction() +"");//线下测试
        String userToken = RpcTransmit.getUserToken();
        httpGet.setHeader("userToken", userToken);
        RpcTransmit.transmitUserToken(userToken);
        ActResultOrgan resultOrgan = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            String purpose = (String) resultOrgan.getData();
            entity.setPurpose(purpose);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CloseableHttpClient closeableHttpClient1 = HttpClients.createDefault();
        HttpGet httpGet1 = new HttpGet("https://system.issp.bjike.com:8080/featurelist/v1/getVersion/" + entity.getFunction() + "");//线上
//        HttpGet httpGet1 = new HttpGet("http://localhost:51654/featurelist/v1/getVersion/" +entity.getFunction() +"");//线下测试
        httpGet1.setHeader("userToken", userToken);
        RpcTransmit.transmitUserToken(userToken);
        ActResultOrgan resultOrgan1 = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient1.execute(httpGet1);
            resultOrgan1 = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            String version = (String) resultOrgan1.getData();
            entity.setVersion(version);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    //获取内部项目个数
    private Long getProjectNum(String startTime, String endTime) throws SerException {
//        startTime = "2017-01-01";
//        endTime = "2017-09-09";
        CloseableHttpClient closeableHttpClient1 = HttpClients.createDefault();
//        HttpGet httpGet1 = new HttpGet("https://businessproject.issp.bjike.com:8080/baseinfomanage/v1/getInterProject?startTime=" + startTime + "endTime=" + endTime);//线上
        HttpGet httpGet1 = new HttpGet("http://localhost:51204/baseinfomanage/v1/getInterProject?startTime=" + startTime + "&endTime=" + endTime);//线下测试
        String userToken = RpcTransmit.getUserToken();
        httpGet1.setHeader("userToken", userToken);
        RpcTransmit.transmitUserToken(userToken);
        ActResultOrgan resultOrgan1 = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient1.execute(httpGet1);
            resultOrgan1 = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            Integer data = (Integer) resultOrgan1.getData();
            if (data != null) {
                return Long.valueOf(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    //获取内部项目个数
    private Long getProjectNum(String startTime, String endTime, String project) throws SerException {
//        startTime = "2017-01-01";
//        endTime = "2017-09-09";
        CloseableHttpClient closeableHttpClient1 = HttpClients.createDefault();
//        HttpGet httpGet1 = new HttpGet("https://businessproject.issp.bjike.com:8080/baseinfomanage/v1/getInterProject?startTime=" + startTime + "endTime=" + endTime + "project=" + project);//线上
        HttpGet httpGet1 = new HttpGet("http://localhost:51204/baseinfomanage/v1/getInterProject?startTime=" + startTime + "endTime=" + endTime + "project=" + project);//线下测试
        String userToken = RpcTransmit.getUserToken();
        httpGet1.setHeader("userToken", userToken);
        RpcTransmit.transmitUserToken(userToken);
        ActResultOrgan resultOrgan1 = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient1.execute(httpGet1);
            resultOrgan1 = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            Integer data = (Integer) resultOrgan1.getData();
            if (null != data) {
                return Long.valueOf(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    //查询岗位无任职人数
    private Long findNoEmployeesNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"noEmployeesNum"};
//        StringBuilder sql = new StringBuilder(" select a.num - b.num as noEmployeesNum FROM ");
//        sql.append(" ((SELECT count(d.user_id) as num from ");
//        sql.append("  (SELECT id from ");
//        sql.append(" organize_position_detail_user ");
//        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
//            sql.append(" WHERE modifyTime BETWEEN '" + startTime + "' ");
//            sql.append(" and '" + endTime + "'");
//        }
//        sql.append(" ) c , ");
//        sql.append(" organize_position_detail_user_table  d ");
//        sql.append("  WHERE c.id =d.user_id) )a, ");
//        sql.append(" (SELECT count(id) as num FROM organize_position_detail ");
//        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
//            sql.append(" WHERE modifyTime BETWEEN '" + startTime + "' ");
//            sql.append(" and '" + endTime + "'");
//        }
//        sql.append(" ) b; ");

        StringBuilder sql = new StringBuilder(" select a.num-b.num as noEmployeesNum FROM ");
        sql.append(" (SELECT count(id) as num from ");
        sql.append(" organize_position_detail_user ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" WHERE modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" and '" + endTime + "'");
        }
        sql.append(" ) a , ");
        sql.append(" ((SELECT count(d.user_id) as num from ");
        sql.append(" (SELECT id from organize_position_detail_user ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" WHERE modifyTime BETWEEN '" + startTime + "' ");
            sql.append(" and '" + endTime + "'");
        }
        sql.append(" ) c , ");
        sql.append(" organize_position_detail_user_table d ");
        sql.append(" WHERE c.id =d.user_id))b; ");
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> noEmployeesNums = managerBOs.stream().map(ManagerBO::getNoEmployeesNum).collect(Collectors.toList());
            Long noEmployeesNum = noEmployeesNums.get(0);
            if (noEmployeesNum < 0l) {
                noEmployeesNum = 0l - noEmployeesNum;
            }
            return noEmployeesNum;
        }
        return 0l;
    }

    //查询岗位无任职人数
    private Long findNoEmployeesNum(String startTime, String endTime, String project) throws SerException {
        Long num = 0l;
        String[] fields = new String[]{"value"};
        StringBuilder sql = new StringBuilder(" SELECT id as value from organize_position_detail_user ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" where createTime BETWEEN '" + startTime + "' ");
            sql.append(" and '" + endTime + "'");
        }
        StringBuilder sql2 = new StringBuilder(" SELECT user_id as value from organize_position_detail_user_table ");
//        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
//            sql2.append(" where createTime BETWEEN '" + startTime + "' ");
//            sql2.append(" and '" + endTime + "'");
//        }
        List<String> ids1 = new ArrayList<>(0);
        List<String> ids2 = new ArrayList<>(0);
        List<String> ids3 = new ArrayList<>(0);

        List<OpinionBO> opinionBOs1 = super.findBySql(sql.toString(), OpinionBO.class, fields);
        if (null != opinionBOs1 && opinionBOs1.size() > 0) {
            ids1 = opinionBOs1.stream().map(OpinionBO::getValue).distinct().collect(Collectors.toList());
        }
        List<OpinionBO> opinionBOs2 = super.findBySql(sql2.toString(), OpinionBO.class, fields);
        if (null != opinionBOs2 && opinionBOs2.size() > 0) {
            ids2 = opinionBOs2.stream().map(OpinionBO::getValue).distinct().collect(Collectors.toList());
        }
        if (null != ids1 && ids1.size() > 0 && ids2.size() == 0) {
            ids3 = ids1;
        }
        if (null != ids1 && ids1.size() > 0 && null != ids2 && ids2.size() > 0) {
            for (String id : ids1) {
                if (!ids2.contains(id)) {
                    ids3.add(id);
                }
            }
        }
        if (null != ids3 && ids3.size() > 0) {
            for (String id : ids3) {
                StringBuilder sql3 = new StringBuilder(" SELECT a.department_id as value FROM organize_position_detail a, ");
                sql3.append(" (SELECT position_id from organize_position_detail_user_table ");
                sql3.append(" WHERE user_id = '" + id + "') b WHERE a.id = b.position_id; ");
                List<OpinionBO> opinionBOs3 = super.findBySql(sql3.toString(), OpinionBO.class, fields);
                if (null != opinionBOs3 && opinionBOs3.size() > 0) {
                    for (OpinionBO opinionBO : opinionBOs3) {
                        DepartmentDetail departmentDetail = departmentDetailSer.findById(opinionBO.getValue());
                        if (null != departmentDetail) {
                            if (project.equals(departmentDetail.getDepartment())) {
                                num += 1;
                            }
                        }
                    }
                }
            }

        }
        return num;

    }

    private Long findPartjobNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"partjobNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as partjobNum FROM organize_position_user_detail ");
        sql.append(" WHERE workStatus = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> partjobNums = managerBOs.stream().map(ManagerBO::getPartjobNum).collect(Collectors.toList());
            return partjobNums.get(0);
        }
        return 0l;
    }

    private Long findPartjobNum(String startTime, String endTime, String project) throws SerException {
        Long num = 0l;
        String[] fields = new String[]{"value"};
        StringBuilder sql = new StringBuilder(" select id as value FROM organize_position_user_detail ");
        sql.append(" WHERE workStatus = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }

        List<OpinionBO> opinionBOs = super.findBySql(sql.toString(), OpinionBO.class, fields);
        if (null != opinionBOs && opinionBOs.size() > 0) {
            for (OpinionBO opinionBO : opinionBOs) {
                DepartmentDetail departmentDetail = departmentDetailSer.findById(opinionBO.getValue());
                if (null != departmentDetail) {
                    if (project.equals(departmentDetail.getDepartment())) {
                        num += 1;
                    }
                }
            }
        }
        return num;
    }

    //查询已有岗位说明数个数
    private Long findJobDescriptionNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"jobDescriptionNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as jobDescriptionNum FROM organize_position_detail ");
        sql.append(" WHERE book = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> jobDescriptionNums = managerBOs.stream().map(ManagerBO::getJobDescriptionNum).collect(Collectors.toList());
            return jobDescriptionNums.get(0);
        }
        return 0l;
    }

    //查询已有岗位说明数个数
    private Long findJobDescriptionNum(String startTime, String endTime, String project) throws SerException {
        Long num = 0l;
        String[] fields = new String[]{"value"};
        StringBuilder sql = new StringBuilder(" select id as value FROM organize_position_detail ");
        sql.append(" WHERE book = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
//        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
//        if (null != managerBOs && managerBOs.size() > 0) {
//            List<Long> jobDescriptionNums = managerBOs.stream().map(ManagerBO::getJobDescriptionNum).collect(Collectors.toList());
//            return jobDescriptionNums.get(0);
//        }
//        return 0l;
        List<OpinionBO> opinionBOs = super.findBySql(sql.toString(), OpinionBO.class, fields);
        if (null != opinionBOs && opinionBOs.size() > 0) {
            for (OpinionBO opinionBO : opinionBOs) {
                DepartmentDetail departmentDetail = departmentDetailSer.findById(opinionBO.getValue());
                if (null != departmentDetail) {
                    if (project.equals(departmentDetail.getDepartment())) {
                        num += 1;
                    }
                }
            }
        }
        return num;
    }

    //查询无岗位说明数个数
    private Long findNoJobNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"noJobNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as noJobNum FROM organize_position_detail ");
        sql.append(" WHERE book = 0 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> noJobNums = managerBOs.stream().map(ManagerBO::getNoJobNum).collect(Collectors.toList());
            return noJobNums.get(0);
        }
        return 0l;
    }

    //查询无岗位说明数个数
    private Long findNoJobNum(String startTime, String endTime, String project) throws SerException {
        Long num = 0l;
        String[] fields = new String[]{"value"};
        StringBuilder sql = new StringBuilder(" select id as value FROM organize_position_detail ");
        sql.append(" WHERE book = 0 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
//        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
//        if (null != managerBOs && managerBOs.size() > 0) {
//            List<Long> noJobNums = managerBOs.stream().map(ManagerBO::getNoJobNum).collect(Collectors.toList());
//            return noJobNums.get(0);
//        }
//        return 0l;
        List<OpinionBO> opinionBOs = super.findBySql(sql.toString(), OpinionBO.class, fields);
        if (null != opinionBOs && opinionBOs.size() > 0) {
            for (OpinionBO opinionBO : opinionBOs) {
                DepartmentDetail departmentDetail = departmentDetailSer.findById(opinionBO.getValue());
                if (null != departmentDetail) {
                    if (project.equals(departmentDetail.getDepartment())) {
                        num += 1;
                    }
                }
            }
        }
        return num;
    }

    //查询在职人员数
    private Long findEntryNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"entryNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as entryNum FROM organize_position_detail_user ");
        sql.append(" WHERE staffStatus = 0 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> entryNums = managerBOs.stream().map(ManagerBO::getEntryNum).collect(Collectors.toList());
            return entryNums.get(0);
        }
        return 0l;
    }

    //查询在职人员数
    private Long findEntryNum(String startTime, String endTime, String project) throws SerException {
        Long num = 0l;
        String[] fields = new String[]{"value"};
        StringBuilder sql = new StringBuilder(" select id as value FROM organize_position_detail_user ");
        sql.append(" WHERE staffStatus = 0 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<OpinionBO> opinionBOs = super.findBySql(sql.toString(), OpinionBO.class, fields);
        if (null != opinionBOs && opinionBOs.size() > 0) {
            for (OpinionBO opinionBO : opinionBOs) {
                StringBuilder sql2 = new StringBuilder(" SELECT b.department_id as value from organize_position_detail_user_table a,organize_position_detail b ");
                sql2.append(" WHERE a.user_id = '" + opinionBO.getValue() + "' ");
                sql2.append(" and a.position_id = b.id ");
                List<OpinionBO> opinionBOs1 = super.findBySql(sql2.toString(), OpinionBO.class, fields);
                if (null != opinionBOs1 && opinionBOs1.size() > 0) {
                    for (OpinionBO opinionBO1 : opinionBOs1) {
                        DepartmentDetail departmentDetail = departmentDetailSer.findById(opinionBO1.getValue());
                        if (null != departmentDetail) {
                            if (project.equals(departmentDetail.getDepartment())) {
                                num += 1;
                            }
                        }
                    }
                }
            }
        }
        return num;
    }

    //查询离职人员数
    private Long findDimissionNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"dimissionNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as dimissionNum FROM organize_position_detail_user ");
        sql.append(" WHERE staffStatus = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> dimissionNums = managerBOs.stream().map(ManagerBO::getDimissionNum).collect(Collectors.toList());
            return dimissionNums.get(0);
        }
        return 0l;
    }

    //查询离职人员数
    private Long findDimissionNum(String startTime, String endTime, String project) throws SerException {
        Long num = 0l;
        String[] fields = new String[]{"value"};
        StringBuilder sql = new StringBuilder(" select id as value FROM organize_position_detail_user ");
        sql.append(" WHERE staffStatus = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
//        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
//        if (null != managerBOs && managerBOs.size() > 0) {
//            List<Long> dimissionNums = managerBOs.stream().map(ManagerBO::getDimissionNum).collect(Collectors.toList());
//            return dimissionNums.get(0);
//        }
//        return 0l;
        List<OpinionBO> opinionBOs = super.findBySql(sql.toString(), OpinionBO.class, fields);
        if (null != opinionBOs && opinionBOs.size() > 0) {
            for (OpinionBO opinionBO : opinionBOs) {
                StringBuilder sql2 = new StringBuilder(" SELECT b.department_id as value from organize_position_detail_user_table a,organize_position_detail b ");
                sql2.append(" WHERE a.user_id = '" + opinionBO.getValue() + "' ");
                sql2.append(" and a.position_id = b.id ");
                List<OpinionBO> opinionBOs1 = super.findBySql(sql2.toString(), OperateBO.class, fields);
                if (null != opinionBOs1 && opinionBOs1.size() > 0) {
                    for (OpinionBO opinionBO1 : opinionBOs1) {
                        DepartmentDetail departmentDetail = departmentDetailSer.findById(opinionBO1.getValue());
                        if (null != departmentDetail) {
                            if (project.equals(departmentDetail.getDepartment())) {
                                num += 1;
                            }
                        }
                    }
                }
            }
        }
        return num;
    }

    //查询待离职人员数
    private Long findWaitDimissionNum(String startTime, String endTime) throws SerException {
        String[] fields = new String[]{"waitDimissionNum"};
        StringBuilder sql = new StringBuilder(" select count(id) as waitDimissionNum FROM organize_position_detail_user ");
        sql.append(" WHERE staffStatus = 2 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
        if (null != managerBOs && managerBOs.size() > 0) {
            List<Long> waitDimissionNums = managerBOs.stream().map(ManagerBO::getWaitDimissionNum).collect(Collectors.toList());
            return waitDimissionNums.get(0);
        }
        return 0l;
    }

    //查询待离职人员数
    private Long findWaitDimissionNum(String startTime, String endTime, String project) throws SerException {
        Long num = 0l;
        String[] fields = new String[]{"value"};
        StringBuilder sql = new StringBuilder(" select id as value FROM organize_position_detail_user ");
        sql.append(" WHERE staffStatus = 2 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" AND createTime BETWEEN '" + startTime + "' ");
            sql.append(" AND '" + endTime + "'");
        }
//        List<ManagerBO> managerBOs = super.findBySql(sql.toString(), ManagerBO.class, fields);
//        if (null != managerBOs && managerBOs.size() > 0) {
//            List<Long> waitDimissionNums = managerBOs.stream().map(ManagerBO::getWaitDimissionNum).collect(Collectors.toList());
//            return waitDimissionNums.get(0);
//        }
//        return 0l;
        List<OpinionBO> opinionBOs = super.findBySql(sql.toString(), OpinionBO.class, fields);
        if (null != opinionBOs && opinionBOs.size() > 0) {
            for (OpinionBO opinionBO : opinionBOs) {
                StringBuilder sql2 = new StringBuilder(" SELECT b.department_id as value from organize_position_detail_user_table a,organize_position_detail b ");
                sql2.append(" WHERE a.user_id = '" + opinionBO.getValue() + "' ");
                sql2.append(" and a.position_id = b.id ");
                List<OpinionBO> opinionBOs1 = super.findBySql(sql2.toString(), OperateBO.class, fields);
                if (null != opinionBOs1 && opinionBOs1.size() > 0) {
                    for (OpinionBO opinionBO1 : opinionBOs1) {
                        DepartmentDetail departmentDetail = departmentDetailSer.findById(opinionBO1.getValue());
                        if (null != departmentDetail) {
                            if (project.equals(departmentDetail.getDepartment())) {
                                num += 1;
                            }
                        }
                    }
                }
            }
        }
        return num;
    }

    private ManagerBO setManagerBO(String startTime, String endTime) throws SerException {
        ManagerBO managerBO = new ManagerBO();
        // TODO: 17-9-13 商务合同管理－预估金额
        managerBO.setArea(departmentDetailSer.getAreaNum(startTime, endTime));
        managerBO.setDepartment(departmentDetailSer.getDepartmentNum(startTime, endTime));
        managerBO.setProjectNum(getProjectNum(startTime, endTime));
        managerBO.setPositionNum(positionDetailSer.getPositionNum(startTime, endTime));
        managerBO.setNoEmployeesNum(findNoEmployeesNum(startTime, endTime));
        managerBO.setPartjobNum(findPartjobNum(startTime, endTime));
        managerBO.setJobDescriptionNum(findJobDescriptionNum(startTime, endTime));
        managerBO.setNoJobNum(findNoJobNum(startTime, endTime));
        // TODO: 17-9-13  被调动人数
        // TODO: 17-9-13 通过（轮岗人数）
        // TODO: 17-9-13 待入职人数
        managerBO.setEntryNum(findEntryNum(startTime, endTime));
        managerBO.setDimissionNum(findDimissionNum(startTime, endTime));
        managerBO.setWaitDimissionNum(findWaitDimissionNum(startTime, endTime));
        return managerBO;
    }

    private List<ManagerBO> setManagerBO1(String startTime, String endTime) throws SerException {
        List<ManagerBO> managerBOs = new ArrayList<>(0);
        //地区
        List<String> areaList = departmentDetailSer.getAreas(startTime, endTime);
        //项目组
        List<String> projectList = departmentDetailSer.getDepartments(startTime, endTime);
        if (null != projectList && projectList.size() > 0) {
//            for (String area : areaList) {
            for (String project : projectList) {
                ManagerBO managerBO = new ManagerBO();
                // TODO: 17-9-13 商务合同管理－预估金额
//                    managerBO.setAreas(area);
                managerBO.setDepartments(project);
                managerBO.setProjectNum(getProjectNum(startTime, endTime, project));
                managerBO.setPositionNum(positionDetailSer.getPositionNum(startTime, endTime, project));
                managerBO.setNoEmployeesNum(findNoEmployeesNum(startTime, endTime, project));
                managerBO.setPartjobNum(findPartjobNum(startTime, endTime, project));
                managerBO.setJobDescriptionNum(findJobDescriptionNum(startTime, endTime, project));
                managerBO.setNoJobNum(findNoJobNum(startTime, endTime, project));
                // TODO: 17-9-13  被调动人数
                managerBO.setMoveNum(0l);
                // TODO: 17-9-13 通过（轮岗人数）
                managerBO.setRotationNum(0l);
                // TODO: 17-9-13 待入职人数
                managerBO.setWaitEntryNum(0l);
                managerBO.setEntryNum(findEntryNum(startTime, endTime, project));
                managerBO.setDimissionNum(findDimissionNum(startTime, endTime, project));
                managerBO.setWaitDimissionNum(findWaitDimissionNum(startTime, endTime, project));
                managerBOs.add(managerBO);
            }
        }
//        }
        return managerBOs;
    }

    private List<ManagerBO> setManagerBO2(String startTime, String endTime) throws SerException {
        List<ManagerBO> managerBOs = new ArrayList<>(0);
        //地区
        List<String> areaList = departmentDetailSer.getAreas(startTime, endTime);
        //项目组
        List<String> projectList = departmentDetailSer.getDepartments(startTime, endTime);
        if (null != areaList && areaList.size() > 0 && null != projectList && projectList.size() > 0) {
            for (String area : areaList) {
                for (String project : projectList) {
                    ManagerBO managerBO = new ManagerBO();
                    // TODO: 17-9-13 商务合同管理－预估金额
                    managerBO.setAreas(area);
                    managerBO.setDepartments(project);
                    managerBO.setProjectNum(getProjectNum(startTime, endTime, project));
                    managerBO.setPositionNum(positionDetailSer.getPositionNum(startTime, endTime, project));
                    managerBO.setNoEmployeesNum(findNoEmployeesNum(startTime, endTime, project));
                    managerBO.setPartjobNum(findPartjobNum(startTime, endTime, project));
                    managerBO.setJobDescriptionNum(findJobDescriptionNum(startTime, endTime, project));
                    managerBO.setNoJobNum(findNoJobNum(startTime, endTime, project));
                    // TODO: 17-9-13  被调动人数
                    managerBO.setMoveNum(0l);
                    // TODO: 17-9-13 通过（轮岗人数）
                    managerBO.setRotationNum(0l);
                    // TODO: 17-9-13 待入职人数
                    managerBO.setWaitEntryNum(0l);
                    managerBO.setEntryNum(findEntryNum(startTime, endTime, project));
                    managerBO.setDimissionNum(findDimissionNum(startTime, endTime, project));
                    managerBO.setWaitDimissionNum(findWaitDimissionNum(startTime, endTime, project));
                    managerBOs.add(managerBO);
                }
            }
        }
        return managerBOs;
    }

    //获取某一个月的第一天
    private String findFirstDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(firstDayOfMonth);
        return startTime;
    }

    //获取某一个月的最后一天
    private String findEndDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = sdf.format(lastDayOfMonth);
        return endTime;
    }

    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
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
        String[] time = new String[]{start, endTime};
        return time;
    }

    //获取指定日期的后一天
    public String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    //动态创建对象
    public static void createModel(List<Model> models, String createOrDelete) {

        Map<String, String> cus = new HashMap<>(0);
        cus.put("模块名", "organize");
        cus.put("类名", "/ModuleAndIndicatorExport");
        cus.put("作者", "zhuangkaiqin");
        cus.put("描述", "转换数据");

        String packageName = cus.get("模块名");
        String className = cus.get("类名");
        className = className.substring(className.lastIndexOf("/") + 1, className.length());
        String author = cus.get("作者");
        String desc = cus.get("描述") + "业务传输对象";
        LocalDateTime date = LocalDateTime.now();
        String time = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        String relativePath = "";
        if (cus.get("类名").contains("/")) {
            relativePath = cus.get("类名").substring(0, cus.get("类名").lastIndexOf("/"));
        }
        int size = 0;
        if (models != null && models.size() > 0) {
            size = models.size(); //属性字段长度
        }


        StringBuilder sb = new StringBuilder("");
        sb.append("package com.bjike.goddess." + packageName + ".bo" + (relativePath.equals("") ? "" : "." + relativePath).replaceAll("/", ".") + ";\n\n")
                .append("import com.bjike.goddess.common.api.bo.BaseBO;\n\n")
                .append("import javax.persistence.Column;\n");

        //类创建
        sb.append("public class " + className + "BO extends BaseBO { \n\n");

        //拼接属性
        for (int i = 0; i < size; i++) {
            Model model = models.get(i);

            sb.append(" private " + model.getType() + "  " + model.getFieldName() + "; ");
            if (i == size - 1) {
                sb.append("\n\n\n\n");
            } else {
                sb.append("\n\n");
            }
        }

        //拼接get和set
        for (int i = 0; i < size; i++) {
            Model m = models.get(i);

            sb.append(" public " + m.getType() + " get" + m.getAnnotation() + " () { \n")
                    .append(" return " + m.getFieldName() + ";\n")
                    .append(" } \n")
                    .append(" public void set" + m.getAnnotation() + " (" + m.getType() + " " + m.getFieldName() + " ) { \n")
                    .append(" this." + m.getFieldName().trim() + " = " + m.getFieldName().trim() + " ; \n")
                    .append(" } \n");
        }

        //拼接类完成
        sb.append(" }");

        //文件创建路径
        StringBuffer filePath = new StringBuffer(System.getProperty("user.dir") + "/modules/")
                .append(packageName.toLowerCase() + "/")
                .append(packageName.toLowerCase() + "-api/src/main/java/com/bjike/goddess/")
                .append(packageName.toLowerCase() + "/bo/");
        System.out.println(filePath);

        //相对包路径
        if (!relativePath.trim().equals("")) {
            filePath.append(relativePath + "/");
        }

        //文件创建
        File file = new File(filePath.toString());
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        filePath.append(className + "BO.java");
        file = new File(filePath.toString());
        if (createOrDelete.equals("create")) {

            try {
                FileWriter writer = new FileWriter(file);
                writer.write(sb.toString(), 0, sb.toString().length());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (createOrDelete.equals("delete")) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}