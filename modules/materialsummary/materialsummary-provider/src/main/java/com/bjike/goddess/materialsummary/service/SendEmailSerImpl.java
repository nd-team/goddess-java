package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.devicerepair.api.DeviceRepairAPI;
import com.bjike.goddess.devicerepair.bo.DeviceRepairBO;
import com.bjike.goddess.materialbuy.api.MaterialBuyAPI;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.type.InstockType;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialsummary.bo.*;
import com.bjike.goddess.materialsummary.dto.SendEmailDTO;
import com.bjike.goddess.materialsummary.entity.SendEmail;
import com.bjike.goddess.materialsummary.to.SendEmailTO;
import com.bjike.goddess.materialsummary.type.CollectSendUnit;
import com.bjike.goddess.materialsummary.type.ModuleType;
import com.bjike.goddess.materialsummary.type.SummaryType;
import com.bjike.goddess.user.api.UserAPI;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * 物质购买发送邮件业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 物质购买发送邮件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialsummarySerCache")
@Service
public class SendEmailSerImpl extends ServiceImpl<SendEmail, SendEmailDTO> implements SendEmailSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MaterialBuyAPI materialBuyAPI;
    @Autowired
    private MaterialInStockAPI materialInStockAPI;
    @Autowired
    private DeviceRepairAPI deviceRepairAPI;

    @Override
    public Long counts(SendEmailDTO SendEmailDTO) throws SerException {
        Long count = super.count(SendEmailDTO);
        return count;
    }

    @Override
    public SendEmailBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        SendEmail buySendEmail = super.findById(id);
        return BeanTransform.copyProperties(buySendEmail, SendEmailBO.class);
    }

    @Override
    public List<SendEmailBO> listCollectEmail(SendEmailDTO SendEmailDTO) throws SerException {
        SendEmailDTO.getSorts().add("createTime=desc");
        List<SendEmail> list = super.findByPage(SendEmailDTO);
        return BeanTransform.copyProperties(list, SendEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SendEmailBO addCollectEmail(SendEmailTO SendEmailTO) throws SerException {

        if (SendEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }
        if (SendEmailTO.getSendNum() < 30 && SendEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (SendEmailTO.getSendNum() > SendEmailTO.getSendNum().longValue() && SendEmailTO.getSendNum() < (SendEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = SendEmailTO.getSendObject();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        SendEmail sendEmail = BeanTransform.copyProperties(SendEmailTO, SendEmail.class, true, "sendObject");
        sendEmail.setCreateTime(LocalDateTime.now());
        sendEmail.setStatus(Status.THAW);
        sendEmail.setCreatePersion(userAPI.currentUser().getUsername());
        //设置发送对象
        sendEmail.setSendObject(String.valueOf(emails));
        //设置发送间隔
        if (null == sendEmail.getCollectSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        String unit = sendUnitConverse(sendEmail.getCollectSendUnit().getCode());
        sendEmail.setSendNumAndUnit(sendEmail.getSendNum() + unit);

        //设置上次发送时间
        sendEmail.setLastSendTime(LocalDateTime.now());


        super.save(sendEmail);

        return BeanTransform.copyProperties(sendEmail, SendEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SendEmailBO editCollectEmail(SendEmailTO SendEmailTO) throws SerException {
        if (SendEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }

        if (SendEmailTO.getSendNum() < 30 && SendEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (SendEmailTO.getSendNum() > SendEmailTO.getSendNum().longValue() && SendEmailTO.getSendNum() < (SendEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = SendEmailTO.getSendObject();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        SendEmail temp = super.findById(SendEmailTO.getId());
        SendEmail buySendEmail = BeanTransform.copyProperties(SendEmailTO, SendEmail.class, true, "sendObject");
        //设置发送对象
        buySendEmail.setSendObject(String.valueOf(emails));

        BeanUtils.copyProperties(buySendEmail, temp, "id", "createTime", "createPersion", "lastSendTime", "status");
        temp.setModifyTime(LocalDateTime.now());
        temp.setCreatePersion(userAPI.currentUser().getUsername());


        //设置发送间隔
        String unit = sendUnitConverse(buySendEmail.getCollectSendUnit().getCode());
        temp.setSendNumAndUnit(buySendEmail.getSendNum() + unit);

        super.update(temp);
        return BeanTransform.copyProperties(temp, SendEmailBO.class);

    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        SendEmail buySendEmail = super.findById(id);
        buySendEmail.setStatus(Status.CONGEAL);
        super.update(buySendEmail);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        SendEmail buySendEmail = super.findById(id);
        buySendEmail.setStatus(Status.THAW);
        super.update(buySendEmail);
    }

    /**
     * 发送间隔单位转换
     */
    private String sendUnitConverse(int code) {
        String unit = "";
        switch (code) {
            case 0:
                unit = "分钟";
                break;
            case 1:
                unit = "小时";
                break;
            case 2:
                unit = "天";
                break;
            case 3:
                unit = "周";
                break;
            case 4:
                unit = "月";
                break;
            case 5:
                unit = "季度";
                break;
            case 6:
                unit = "年";
                break;
            default:
                unit = "";
                break;
        }
        return unit;
    }

    @Override
    public List<SummaryType> summaryType(ModuleType moduleType) throws SerException {
        List<SummaryType> typeList = Arrays.asList();
        if (moduleType.equals(ModuleType.MATERIALBUY)) {
            typeList = Arrays.asList(SummaryType.MATCLASSIFBUY, SummaryType.DEPARTAREABUY, SummaryType.PERSONMATBUY);
        } else if (moduleType.equals(ModuleType.MATERIALSTOR)) {
            typeList = Arrays.asList(SummaryType.STOCKSOURCE, SummaryType.AREASTOCK);
        } else if (moduleType.equals(ModuleType.MATERIALCHECK)) {
            typeList = Arrays.asList(SummaryType.AREAMATCHECK, SummaryType.DEPARMATCHECK);
        } else if (moduleType.equals(ModuleType.MATERIALMAINTEN)) {
            typeList = Arrays.asList(SummaryType.MAINTENSTATUS, SummaryType.WARRANTYSTATUS);
        }
        return typeList;
    }

    @Override
    public List<TypeBuySummBO> typeBuySummDay(String summTime) throws SerException {
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        List<TypeBuySummBO> typeBuySummBOList = new ArrayList<>();
        List<String> typeList = materialBuyAPI.findDevType(date);
        for (String devType : typeList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialBuyAPI.findAreaByType(devType, date);
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> departments = materialBuyAPI.findDeparByTyAre(devType, area, date);
                    for (String depart : departments) {
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(devType, area, depart, date);
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                            underlyNum += materialBuyBO.getQuantity();
                            underlyAmount += materialBuyBO.getTotalSum();
                        }
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setType(devType);//设备类型
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(depart);//部门(项目组)
                        typeBuySummBO.setTotalNum(underlyNum);//数量
                        typeBuySummBO.setTotalAmount(underlyAmount);//金额
                        typeBuySummBOList.add(typeBuySummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }

                    TypeBuySummBO typeBuySummBO1 = new TypeBuySummBO();
                    typeBuySummBO1.setDepartment("合计");
                    typeBuySummBO1.setTotalNum(arLevNum);
                    typeBuySummBO1.setTotalAmount(arLevAmount);
                    typeBuySummBOList.add(typeBuySummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                TypeBuySummBO typeBuySummBO2 = new TypeBuySummBO();
                typeBuySummBO2.setDepartment("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                typeBuySummBOList.add(typeBuySummBO2);
            }
        }

        return typeBuySummBOList;
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

    @Override
    public List<TypeBuySummBO> typeBuySummWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        List<TypeBuySummBO> typeBuySummBOList = new ArrayList<>();
        List<String> typeList = materialBuyAPI.findDevType(getTimes(year, month, week));
        for (String devType : typeList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialBuyAPI.findAreaByType(devType, getTimes(year, month, week));
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> departments = materialBuyAPI.findDeparByTyAre(devType, area, getTimes(year, month, week));
                    for (String depart : departments) {
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(devType, area, depart, getTimes(year, month, week));
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                            underlyNum += materialBuyBO.getQuantity();
                            underlyAmount += materialBuyBO.getTotalSum();
                        }
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setType(devType);//设备类型
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(depart);//部门(项目组)
                        typeBuySummBO.setTotalNum(underlyNum);//数量
                        typeBuySummBO.setTotalAmount(underlyAmount);//金额
                        typeBuySummBOList.add(typeBuySummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }

                    TypeBuySummBO typeBuySummBO1 = new TypeBuySummBO();
                    typeBuySummBO1.setDepartment("合计");
                    typeBuySummBO1.setTotalNum(arLevNum);
                    typeBuySummBO1.setTotalAmount(arLevAmount);
                    typeBuySummBOList.add(typeBuySummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                TypeBuySummBO typeBuySummBO2 = new TypeBuySummBO();
                typeBuySummBO2.setDepartment("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                typeBuySummBOList.add(typeBuySummBO2);
            }
        }

        return typeBuySummBOList;
    }

    @Override
    public List<TypeBuySummBO> typeBuySummMonth(Integer year, Integer month) throws SerException {
        Integer ye = LocalDate.now().getYear();
        Integer mon = LocalDate.now().getMonthValue();
        if (year != null) {
            ye = year;
        }
        if (month != null) {
            mon = month;
        }
        String startDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + "01";
        String endDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<TypeBuySummBO> typeBuySummBOList = new ArrayList<>();
        List<String> typeList = materialBuyAPI.findDevType(date);
        for (String devType : typeList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialBuyAPI.findAreaByType(devType, date);
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> departments = materialBuyAPI.findDeparByTyAre(devType, area, date);
                    for (String depart : departments) {
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(devType, area, depart, date);
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                            underlyNum += materialBuyBO.getQuantity();
                            underlyAmount += materialBuyBO.getTotalSum();
                        }
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setType(devType);//设备类型
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(depart);//部门(项目组)
                        typeBuySummBO.setTotalNum(underlyNum);//数量
                        typeBuySummBO.setTotalAmount(underlyAmount);//金额
                        typeBuySummBOList.add(typeBuySummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }

                    TypeBuySummBO typeBuySummBO1 = new TypeBuySummBO();
                    typeBuySummBO1.setDepartment("合计");
                    typeBuySummBO1.setTotalNum(arLevNum);
                    typeBuySummBO1.setTotalAmount(arLevAmount);
                    typeBuySummBOList.add(typeBuySummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                TypeBuySummBO typeBuySummBO2 = new TypeBuySummBO();
                typeBuySummBO2.setDepartment("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                typeBuySummBOList.add(typeBuySummBO2);
            }
        }

        return typeBuySummBOList;
    }

    @Override
    public List<TypeBuySummBO> typeBuySummYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = year + "-" + "01" + "-" + "01";
        String endDate = year + "-" + 12 + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<TypeBuySummBO> typeBuySummBOList = new ArrayList<>();
        List<String> typeList = materialBuyAPI.findDevType(date);
        for (String devType : typeList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialBuyAPI.findAreaByType(devType, date);
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> departments = materialBuyAPI.findDeparByTyAre(devType, area, date);
                    for (String depart : departments) {
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(devType, area, depart, date);
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                            underlyNum += materialBuyBO.getQuantity();
                            underlyAmount += materialBuyBO.getTotalSum();
                        }
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setType(devType);//设备类型
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(depart);//部门(项目组)
                        typeBuySummBO.setTotalNum(underlyNum);//数量
                        typeBuySummBO.setTotalAmount(underlyAmount);//金额
                        typeBuySummBOList.add(typeBuySummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }

                    TypeBuySummBO typeBuySummBO1 = new TypeBuySummBO();
                    typeBuySummBO1.setDepartment("合计");
                    typeBuySummBO1.setTotalNum(arLevNum);
                    typeBuySummBO1.setTotalAmount(arLevAmount);
                    typeBuySummBOList.add(typeBuySummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                TypeBuySummBO typeBuySummBO2 = new TypeBuySummBO();
                typeBuySummBO2.setDepartment("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                typeBuySummBOList.add(typeBuySummBO2);
            }
        }

        return typeBuySummBOList;
    }

    @Override
    public List<TypeBuySummBO> areaBuySummDay(String summTime) throws SerException {
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        List<TypeBuySummBO> areaBuySummBOList = new ArrayList<>();
        List<String> areaList = materialBuyAPI.findArea(date);
        for (String area : areaList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> depList = materialBuyAPI.findDepByArea(area, date);
            if (depList != null && depList.size() > 0) {
                for (String dep : depList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> devType = materialBuyAPI.findDevByAreaDev(area, dep, date);
                    for (String type : devType) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTeamAnArea(area, dep, type, date);
                        for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                            underlyNum += materialBuyBO.getQuantity();
                            underlyAmount += materialBuyBO.getTotalSum();
                        }
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(dep);//部门(项目组)
                        typeBuySummBO.setType(type);//设备类型
                        typeBuySummBO.setTotalNum(underlyNum);//数量
                        typeBuySummBO.setTotalAmount(underlyAmount);//金额
                        areaBuySummBOList.add(typeBuySummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    TypeBuySummBO typeBuySummBO1 = new TypeBuySummBO();
                    typeBuySummBO1.setDepartment("合计");
                    typeBuySummBO1.setTotalNum(arLevNum);
                    typeBuySummBO1.setTotalAmount(arLevAmount);
                    areaBuySummBOList.add(typeBuySummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                TypeBuySummBO typeBuySummBO2 = new TypeBuySummBO();
                typeBuySummBO2.setDepartment("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                areaBuySummBOList.add(typeBuySummBO2);
            }
        }

        return areaBuySummBOList;
    }

    @Override
    public List<TypeBuySummBO> areaBuySummWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        List<TypeBuySummBO> areaBuySummBOList = new ArrayList<>();
        List<String> areaList = materialBuyAPI.findArea(getTimes(year, month, week));
        for (String area : areaList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> depList = materialBuyAPI.findDepByArea(area, getTimes(year, month, week));
            if (depList != null && depList.size() > 0) {
                for (String dep : depList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> devType = materialBuyAPI.findDevByAreaDev(area, dep, getTimes(year, month, week));
                    for (String type : devType) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTeamAnArea(area, dep, type, getTimes(year, month, week));
                        for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                            underlyNum += materialBuyBO.getQuantity();
                            underlyAmount += materialBuyBO.getTotalSum();
                        }
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(dep);//部门(项目组)
                        typeBuySummBO.setType(type);//设备类型
                        typeBuySummBO.setTotalNum(underlyNum);//数量
                        typeBuySummBO.setTotalAmount(underlyAmount);//金额
                        areaBuySummBOList.add(typeBuySummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    TypeBuySummBO typeBuySummBO1 = new TypeBuySummBO();
                    typeBuySummBO1.setDepartment("合计");
                    typeBuySummBO1.setTotalNum(arLevNum);
                    typeBuySummBO1.setTotalAmount(arLevAmount);
                    areaBuySummBOList.add(typeBuySummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                TypeBuySummBO typeBuySummBO2 = new TypeBuySummBO();
                typeBuySummBO2.setDepartment("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                areaBuySummBOList.add(typeBuySummBO2);
            }
        }

        return areaBuySummBOList;
    }

    @Override
    public List<TypeBuySummBO> areaBuySummMonth(Integer year, Integer month) throws SerException {
        Integer ye = LocalDate.now().getYear();
        Integer mon = LocalDate.now().getMonthValue();
        if (year != null) {
            ye = year;
        }
        if (month != null) {
            mon = month;
        }
        String startDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + "01";
        String endDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<TypeBuySummBO> areaBuySummBOList = new ArrayList<>();
        List<String> areaList = materialBuyAPI.findArea(date);
        for (String area : areaList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> depList = materialBuyAPI.findDepByArea(area, date);
            if (depList != null && depList.size() > 0) {
                for (String dep : depList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> devType = materialBuyAPI.findDevByAreaDev(area, dep, date);
                    for (String type : devType) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTeamAnArea(area, dep, type, date);
                        for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                            underlyNum += materialBuyBO.getQuantity();
                            underlyAmount += materialBuyBO.getTotalSum();
                        }
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(dep);//部门(项目组)
                        typeBuySummBO.setType(type);//设备类型
                        typeBuySummBO.setTotalNum(underlyNum);//数量
                        typeBuySummBO.setTotalAmount(underlyAmount);//金额
                        areaBuySummBOList.add(typeBuySummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    TypeBuySummBO typeBuySummBO1 = new TypeBuySummBO();
                    typeBuySummBO1.setDepartment("合计");
                    typeBuySummBO1.setTotalNum(arLevNum);
                    typeBuySummBO1.setTotalAmount(arLevAmount);
                    areaBuySummBOList.add(typeBuySummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                TypeBuySummBO typeBuySummBO2 = new TypeBuySummBO();
                typeBuySummBO2.setDepartment("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                areaBuySummBOList.add(typeBuySummBO2);
            }
        }

        return areaBuySummBOList;
    }

    @Override
    public List<TypeBuySummBO> areaBuySummYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = year + "-" + "01" + "-" + "01";
        String endDate = year + "-" + 12 + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<TypeBuySummBO> areaBuySummBOList = new ArrayList<>();
        List<String> areaList = materialBuyAPI.findArea(date);
        for (String area : areaList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> depList = materialBuyAPI.findDepByArea(area, date);
            if (depList != null && depList.size() > 0) {
                for (String dep : depList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> devType = materialBuyAPI.findDevByAreaDev(area, dep, date);
                    for (String type : devType) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTeamAnArea(area, dep, type, date);
                        for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                            underlyNum += materialBuyBO.getQuantity();
                            underlyAmount += materialBuyBO.getTotalSum();
                        }
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(dep);//部门(项目组)
                        typeBuySummBO.setType(type);//设备类型
                        typeBuySummBO.setTotalNum(underlyNum);//数量
                        typeBuySummBO.setTotalAmount(underlyAmount);//金额
                        areaBuySummBOList.add(typeBuySummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    TypeBuySummBO typeBuySummBO1 = new TypeBuySummBO();
                    typeBuySummBO1.setDepartment("合计");
                    typeBuySummBO1.setTotalNum(arLevNum);
                    typeBuySummBO1.setTotalAmount(arLevAmount);
                    areaBuySummBOList.add(typeBuySummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                TypeBuySummBO typeBuySummBO2 = new TypeBuySummBO();
                typeBuySummBO2.setDepartment("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                areaBuySummBOList.add(typeBuySummBO2);
            }
        }

        return areaBuySummBOList;
    }

    @Override
    public List<PersonalBuySummBO> personBuySummDay(String summTime) throws SerException {
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        List<PersonalBuySummBO> personalBuySummBOS = new ArrayList<>();
        List<String> personList = materialBuyAPI.findRequis(date);
        for (String person : personList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> devType = materialBuyAPI.findByRequis(person, date);
            for (String type : devType) {
                List<MaterialBuyBO> materialBuyBOList = materialBuyAPI.findByRequisType(person, type, date);
                Integer underlyNum = 0;
                Double underlyAmount = 0d;
                for (MaterialBuyBO materialBuyBO : materialBuyBOList) {
                    underlyNum += materialBuyBO.getQuantity();
                    underlyAmount += materialBuyBO.getTotalSum();
                }
                PersonalBuySummBO personalBuySummBO = new PersonalBuySummBO();
                personalBuySummBO.setName(person);
                personalBuySummBO.setType(type);
                personalBuySummBO.setTotalNum(underlyNum);
                personalBuySummBO.setTotalAmount(underlyAmount);
                personalBuySummBOS.add(personalBuySummBO);

                tyLevNum += underlyNum;
                tyLevAmount += underlyAmount;
            }
            PersonalBuySummBO personalBuySummBO1 = new PersonalBuySummBO();
            personalBuySummBO1.setType("合计");
            personalBuySummBO1.setTotalNum(tyLevNum);
            personalBuySummBO1.setTotalAmount(tyLevAmount);
            personalBuySummBOS.add(personalBuySummBO1);
        }
        return personalBuySummBOS;
    }

    @Override
    public List<PersonalBuySummBO> personBuySummWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        List<PersonalBuySummBO> personalBuySummBOS = new ArrayList<>();
        List<String> personList = materialBuyAPI.findRequis(getTimes(year, month, week));
        for (String person : personList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> devType = materialBuyAPI.findByRequis(person, getTimes(year, month, week));
            for (String type : devType) {
                List<MaterialBuyBO> materialBuyBOList = materialBuyAPI.findByRequisType(person, type, getTimes(year, month, week));
                Integer underlyNum = 0;
                Double underlyAmount = 0d;
                for (MaterialBuyBO materialBuyBO : materialBuyBOList) {
                    underlyNum += materialBuyBO.getQuantity();
                    underlyAmount += materialBuyBO.getTotalSum();
                }
                PersonalBuySummBO personalBuySummBO = new PersonalBuySummBO();
                personalBuySummBO.setName(person);
                personalBuySummBO.setType(type);
                personalBuySummBO.setTotalNum(underlyNum);
                personalBuySummBO.setTotalAmount(underlyAmount);
                personalBuySummBOS.add(personalBuySummBO);

                tyLevNum += underlyNum;
                tyLevAmount += underlyAmount;
            }
            PersonalBuySummBO personalBuySummBO1 = new PersonalBuySummBO();
            personalBuySummBO1.setType("合计");
            personalBuySummBO1.setTotalNum(tyLevNum);
            personalBuySummBO1.setTotalAmount(tyLevAmount);
            personalBuySummBOS.add(personalBuySummBO1);
        }
        return personalBuySummBOS;
    }

    @Override
    public List<PersonalBuySummBO> personBuySummMonth(Integer year, Integer month) throws SerException {
        Integer ye = LocalDate.now().getYear();
        Integer mon = LocalDate.now().getMonthValue();
        if (year != null) {
            ye = year;
        }
        if (month != null) {
            mon = month;
        }
        String startDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + "01";
        String endDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<PersonalBuySummBO> personalBuySummBOS = new ArrayList<>();
        List<String> personList = materialBuyAPI.findRequis(date);
        for (String person : personList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> devType = materialBuyAPI.findByRequis(person, date);
            for (String type : devType) {
                List<MaterialBuyBO> materialBuyBOList = materialBuyAPI.findByRequisType(person, type, date);
                Integer underlyNum = 0;
                Double underlyAmount = 0d;
                for (MaterialBuyBO materialBuyBO : materialBuyBOList) {
                    underlyNum += materialBuyBO.getQuantity();
                    underlyAmount += materialBuyBO.getTotalSum();
                }
                PersonalBuySummBO personalBuySummBO = new PersonalBuySummBO();
                personalBuySummBO.setName(person);
                personalBuySummBO.setType(type);
                personalBuySummBO.setTotalNum(underlyNum);
                personalBuySummBO.setTotalAmount(underlyAmount);
                personalBuySummBOS.add(personalBuySummBO);

                tyLevNum += underlyNum;
                tyLevAmount += underlyAmount;
            }
            PersonalBuySummBO personalBuySummBO1 = new PersonalBuySummBO();
            personalBuySummBO1.setType("合计");
            personalBuySummBO1.setTotalNum(tyLevNum);
            personalBuySummBO1.setTotalAmount(tyLevAmount);
            personalBuySummBOS.add(personalBuySummBO1);
        }
        return personalBuySummBOS;
    }

    @Override
    public List<PersonalBuySummBO> personBuySummYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = year + "-" + "01" + "-" + "01";
        String endDate = year + "-" + 12 + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<PersonalBuySummBO> personalBuySummBOS = new ArrayList<>();
        List<String> personList = materialBuyAPI.findRequis(date);
        for (String person : personList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> devType = materialBuyAPI.findByRequis(person, date);
            for (String type : devType) {
                List<MaterialBuyBO> materialBuyBOList = materialBuyAPI.findByRequisType(person, type, date);
                Integer underlyNum = 0;
                Double underlyAmount = 0d;
                for (MaterialBuyBO materialBuyBO : materialBuyBOList) {
                    underlyNum += materialBuyBO.getQuantity();
                    underlyAmount += materialBuyBO.getTotalSum();
                }
                PersonalBuySummBO personalBuySummBO = new PersonalBuySummBO();
                personalBuySummBO.setName(person);
                personalBuySummBO.setType(type);
                personalBuySummBO.setTotalNum(underlyNum);
                personalBuySummBO.setTotalAmount(underlyAmount);
                personalBuySummBOS.add(personalBuySummBO);

                tyLevNum += underlyNum;
                tyLevAmount += underlyAmount;
            }
            PersonalBuySummBO personalBuySummBO1 = new PersonalBuySummBO();
            personalBuySummBO1.setType("合计");
            personalBuySummBO1.setTotalNum(tyLevNum);
            personalBuySummBO1.setTotalAmount(tyLevAmount);
            personalBuySummBOS.add(personalBuySummBO1);
        }
        return personalBuySummBOS;
    }

    private String changType(InstockType instockType) {
        String type = "";
        switch (instockType) {
            case BUY:
                type = "购买入库";
                break;
            case LEND:
                type = "外借入库";
                break;
            case TRANSFER:
                type = "调动入库";
                break;
            default:
                type = "";
                break;
        }
        return type;
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummDay(String summTime) throws SerException {
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        List<ResouceStockSummBO> resouceStockSummBOS = new ArrayList<>();
        List<InstockType> typeStocks = materialInStockAPI.findStockType(date);
        for (InstockType type : typeStocks) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialInStockAPI.findAreaByType(type, date);
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> depart = materialInStockAPI.findDepartByTyAnAr(type, area, date);
                    for (String dep : depart) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByTyAnAr(type, area, dep, date);
                        for (MaterialInStockBO materialInStockBO : materialInStockBOS) {
                            underlyNum += materialInStockBO.getQuantity();
                            underlyAmount += materialInStockBO.getTotalSum();
                        }
                        ResouceStockSummBO resouceStockSummBO = new ResouceStockSummBO();
                        resouceStockSummBO.setSources(changType(type));//入库来源
                        resouceStockSummBO.setArea(area);//现入库地区
                        resouceStockSummBO.setDepartment(dep);//项目组/部门
                        resouceStockSummBO.setTotalNum(underlyNum);//总数量
                        resouceStockSummBO.setTotalAmount(underlyAmount);//总金额
                        resouceStockSummBOS.add(resouceStockSummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    ResouceStockSummBO resouceStockSummBO1 = new ResouceStockSummBO();
                    resouceStockSummBO1.setDepartment("合计");
                    resouceStockSummBO1.setTotalNum(arLevNum);
                    resouceStockSummBO1.setTotalAmount(arLevAmount);
                    resouceStockSummBOS.add(resouceStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                ResouceStockSummBO resouceStockSummBO2 = new ResouceStockSummBO();
                resouceStockSummBO2.setDepartment("合计");
                resouceStockSummBO2.setTotalNum(tyLevNum);
                resouceStockSummBO2.setTotalAmount(tyLevAmount);
                resouceStockSummBOS.add(resouceStockSummBO2);
            }
        }

        return resouceStockSummBOS;
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        List<ResouceStockSummBO> resouceStockSummBOS = new ArrayList<>();
        List<InstockType> typeStocks = materialInStockAPI.findStockType(getTimes(year, month, week));
        for (InstockType type : typeStocks) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialInStockAPI.findAreaByType(type, getTimes(year, month, week));
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> depart = materialInStockAPI.findDepartByTyAnAr(type, area, getTimes(year, month, week));
                    for (String dep : depart) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByTyAnAr(type, area, dep, getTimes(year, month, week));
                        for (MaterialInStockBO materialInStockBO : materialInStockBOS) {
                            underlyNum += materialInStockBO.getQuantity();
                            underlyAmount += materialInStockBO.getTotalSum();
                        }
                        ResouceStockSummBO resouceStockSummBO = new ResouceStockSummBO();
                        resouceStockSummBO.setSources(changType(type));//入库来源
                        resouceStockSummBO.setArea(area);//现入库地区
                        resouceStockSummBO.setDepartment(dep);//项目组/部门
                        resouceStockSummBO.setTotalNum(underlyNum);//总数量
                        resouceStockSummBO.setTotalAmount(underlyAmount);//总金额
                        resouceStockSummBOS.add(resouceStockSummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    ResouceStockSummBO resouceStockSummBO1 = new ResouceStockSummBO();
                    resouceStockSummBO1.setDepartment("合计");
                    resouceStockSummBO1.setTotalNum(arLevNum);
                    resouceStockSummBO1.setTotalAmount(arLevAmount);
                    resouceStockSummBOS.add(resouceStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                ResouceStockSummBO resouceStockSummBO2 = new ResouceStockSummBO();
                resouceStockSummBO2.setDepartment("合计");
                resouceStockSummBO2.setTotalNum(tyLevNum);
                resouceStockSummBO2.setTotalAmount(tyLevAmount);
                resouceStockSummBOS.add(resouceStockSummBO2);
            }
        }

        return resouceStockSummBOS;
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummMonth(Integer year, Integer month) throws SerException {
        Integer ye = LocalDate.now().getYear();
        Integer mon = LocalDate.now().getMonthValue();
        if (year != null) {
            ye = year;
        }
        if (month != null) {
            mon = month;
        }
        String startDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + "01";
        String endDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<ResouceStockSummBO> resouceStockSummBOS = new ArrayList<>();
        List<InstockType> typeStocks = materialInStockAPI.findStockType(date);
        for (InstockType type : typeStocks) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialInStockAPI.findAreaByType(type, date);
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> depart = materialInStockAPI.findDepartByTyAnAr(type, area, date);
                    for (String dep : depart) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByTyAnAr(type, area, dep, date);
                        for (MaterialInStockBO materialInStockBO : materialInStockBOS) {
                            underlyNum += materialInStockBO.getQuantity();
                            underlyAmount += materialInStockBO.getTotalSum();
                        }
                        ResouceStockSummBO resouceStockSummBO = new ResouceStockSummBO();
                        resouceStockSummBO.setSources(changType(type));//入库来源
                        resouceStockSummBO.setArea(area);//现入库地区
                        resouceStockSummBO.setDepartment(dep);//项目组/部门
                        resouceStockSummBO.setTotalNum(underlyNum);//总数量
                        resouceStockSummBO.setTotalAmount(underlyAmount);//总金额
                        resouceStockSummBOS.add(resouceStockSummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    ResouceStockSummBO resouceStockSummBO1 = new ResouceStockSummBO();
                    resouceStockSummBO1.setDepartment("合计");
                    resouceStockSummBO1.setTotalNum(arLevNum);
                    resouceStockSummBO1.setTotalAmount(arLevAmount);
                    resouceStockSummBOS.add(resouceStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                ResouceStockSummBO resouceStockSummBO2 = new ResouceStockSummBO();
                resouceStockSummBO2.setDepartment("合计");
                resouceStockSummBO2.setTotalNum(tyLevNum);
                resouceStockSummBO2.setTotalAmount(tyLevAmount);
                resouceStockSummBOS.add(resouceStockSummBO2);
            }
        }

        return resouceStockSummBOS;
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = year + "-" + "01" + "-" + "01";
        String endDate = year + "-" + 12 + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<ResouceStockSummBO> resouceStockSummBOS = new ArrayList<>();
        List<InstockType> typeStocks = materialInStockAPI.findStockType(date);
        for (InstockType type : typeStocks) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialInStockAPI.findAreaByType(type, date);
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> depart = materialInStockAPI.findDepartByTyAnAr(type, area, date);
                    for (String dep : depart) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByTyAnAr(type, area, dep, date);
                        for (MaterialInStockBO materialInStockBO : materialInStockBOS) {
                            underlyNum += materialInStockBO.getQuantity();
                            underlyAmount += materialInStockBO.getTotalSum();
                        }
                        ResouceStockSummBO resouceStockSummBO = new ResouceStockSummBO();
                        resouceStockSummBO.setSources(changType(type));//入库来源
                        resouceStockSummBO.setArea(area);//现入库地区
                        resouceStockSummBO.setDepartment(dep);//项目组/部门
                        resouceStockSummBO.setTotalNum(underlyNum);//总数量
                        resouceStockSummBO.setTotalAmount(underlyAmount);//总金额
                        resouceStockSummBOS.add(resouceStockSummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    ResouceStockSummBO resouceStockSummBO1 = new ResouceStockSummBO();
                    resouceStockSummBO1.setDepartment("合计");
                    resouceStockSummBO1.setTotalNum(arLevNum);
                    resouceStockSummBO1.setTotalAmount(arLevAmount);
                    resouceStockSummBOS.add(resouceStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                ResouceStockSummBO resouceStockSummBO2 = new ResouceStockSummBO();
                resouceStockSummBO2.setDepartment("合计");
                resouceStockSummBO2.setTotalNum(tyLevNum);
                resouceStockSummBO2.setTotalAmount(tyLevAmount);
                resouceStockSummBOS.add(resouceStockSummBO2);
            }
        }

        return resouceStockSummBOS;
    }

    private String changStockStatus(MaterialState materialState) {
        String status = "";
        switch (materialState) {
            case INTACT:
                status = "完好";
                break;
            case MANUAL_DAMAGE:
                status = "人为损坏";
                break;
            case NATURAL_DAMAGE:
                status = "自然损坏";
                break;
            case REPAIRING:
                status = "维修中";
                break;
            case SCRAP:
                status = "已报废";
                break;
            default:
                status = "";
                break;
        }
        return status;
    }

    @Override
    public List<AreaStockSummBO> areaStockSummDay(String summTime) throws SerException {
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        List<AreaStockSummBO> areaStockSummBOS = new ArrayList<>();
        List<String> areas = materialInStockAPI.findAllArea(date);
        for (String area : areas) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> projectGroups = materialInStockAPI.findProByAre(date, area);
            if (projectGroups != null && projectGroups.size() > 0) {
                for (String project : projectGroups) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<MaterialState> status = materialInStockAPI.findStatusByAreAnpro(date, area, project);
                    for (MaterialState state : status) {
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByAreAnpro(date, area, project, state);
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        for (MaterialInStockBO materialInStockBO : materialInStockBOS) {
                            underlyNum += materialInStockBO.getQuantity();
                            underlyAmount += materialInStockBO.getTotalSum();
                        }
                        AreaStockSummBO areaStockSummBO = new AreaStockSummBO();
                        areaStockSummBO.setArea(area);//现入库地区
                        areaStockSummBO.setProjectGroup(project);//项目组
                        areaStockSummBO.setStockStatus(changStockStatus(state));//物质状态
                        areaStockSummBO.setTotalNum(underlyNum);//总数量
                        areaStockSummBO.setTotalAmount(underlyAmount);//总金额
                        areaStockSummBOS.add(areaStockSummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    AreaStockSummBO areaStockSummBO1 = new AreaStockSummBO();
                    areaStockSummBO1.setStockStatus("合计");
                    areaStockSummBO1.setTotalNum(arLevNum);
                    areaStockSummBO1.setTotalAmount(arLevAmount);
                    areaStockSummBOS.add(areaStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                AreaStockSummBO areaStockSummBO2 = new AreaStockSummBO();
                areaStockSummBO2.setStockStatus("合计");
                areaStockSummBO2.setTotalNum(tyLevNum);
                areaStockSummBO2.setTotalAmount(tyLevAmount);
                areaStockSummBOS.add(areaStockSummBO2);
            }
        }

        return areaStockSummBOS;
    }

    @Override
    public List<AreaStockSummBO> areaStockSummWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        List<AreaStockSummBO> areaStockSummBOS = new ArrayList<>();
        List<String> areas = materialInStockAPI.findAllArea(getTimes(year, month, week));
        for (String area : areas) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> projectGroups = materialInStockAPI.findProByAre(getTimes(year, month, week), area);
            if (projectGroups != null && projectGroups.size() > 0) {
                for (String project : projectGroups) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<MaterialState> status = materialInStockAPI.findStatusByAreAnpro(getTimes(year, month, week), area, project);
                    for (MaterialState state : status) {
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByAreAnpro(getTimes(year, month, week), area, project, state);
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        for (MaterialInStockBO materialInStockBO : materialInStockBOS) {
                            underlyNum += materialInStockBO.getQuantity();
                            underlyAmount += materialInStockBO.getTotalSum();
                        }
                        AreaStockSummBO areaStockSummBO = new AreaStockSummBO();
                        areaStockSummBO.setArea(area);//现入库地区
                        areaStockSummBO.setProjectGroup(project);//项目组
                        areaStockSummBO.setStockStatus(changStockStatus(state));//物质状态
                        areaStockSummBO.setTotalNum(underlyNum);//总数量
                        areaStockSummBO.setTotalAmount(underlyAmount);//总金额
                        areaStockSummBOS.add(areaStockSummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    AreaStockSummBO areaStockSummBO1 = new AreaStockSummBO();
                    areaStockSummBO1.setStockStatus("合计");
                    areaStockSummBO1.setTotalNum(arLevNum);
                    areaStockSummBO1.setTotalAmount(arLevAmount);
                    areaStockSummBOS.add(areaStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                AreaStockSummBO areaStockSummBO2 = new AreaStockSummBO();
                areaStockSummBO2.setStockStatus("合计");
                areaStockSummBO2.setTotalNum(tyLevNum);
                areaStockSummBO2.setTotalAmount(tyLevAmount);
                areaStockSummBOS.add(areaStockSummBO2);
            }
        }

        return areaStockSummBOS;
    }

    @Override
    public List<AreaStockSummBO> areaStockSummMonth(Integer year, Integer month) throws SerException {
        Integer ye = LocalDate.now().getYear();
        Integer mon = LocalDate.now().getMonthValue();
        if (year != null) {
            ye = year;
        }
        if (month != null) {
            mon = month;
        }
        String startDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + "01";
        String endDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<AreaStockSummBO> areaStockSummBOS = new ArrayList<>();
        List<String> areas = materialInStockAPI.findAllArea(date);
        for (String area : areas) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> projectGroups = materialInStockAPI.findProByAre(date, area);
            if (projectGroups != null && projectGroups.size() > 0) {
                for (String project : projectGroups) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<MaterialState> status = materialInStockAPI.findStatusByAreAnpro(date, area, project);
                    for (MaterialState state : status) {
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByAreAnpro(date, area, project, state);
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        for (MaterialInStockBO materialInStockBO : materialInStockBOS) {
                            underlyNum += materialInStockBO.getQuantity();
                            underlyAmount += materialInStockBO.getTotalSum();
                        }
                        AreaStockSummBO areaStockSummBO = new AreaStockSummBO();
                        areaStockSummBO.setArea(area);//现入库地区
                        areaStockSummBO.setProjectGroup(project);//项目组
                        areaStockSummBO.setStockStatus(changStockStatus(state));//物质状态
                        areaStockSummBO.setTotalNum(underlyNum);//总数量
                        areaStockSummBO.setTotalAmount(underlyAmount);//总金额
                        areaStockSummBOS.add(areaStockSummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    AreaStockSummBO areaStockSummBO1 = new AreaStockSummBO();
                    areaStockSummBO1.setStockStatus("合计");
                    areaStockSummBO1.setTotalNum(arLevNum);
                    areaStockSummBO1.setTotalAmount(arLevAmount);
                    areaStockSummBOS.add(areaStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                AreaStockSummBO areaStockSummBO2 = new AreaStockSummBO();
                areaStockSummBO2.setStockStatus("合计");
                areaStockSummBO2.setTotalNum(tyLevNum);
                areaStockSummBO2.setTotalAmount(tyLevAmount);
                areaStockSummBOS.add(areaStockSummBO2);
            }
        }

        return areaStockSummBOS;
    }

    @Override
    public List<AreaStockSummBO> areaStockSummYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = year + "-" + "01" + "-" + "01";
        String endDate = year + "-" + 12 + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<AreaStockSummBO> areaStockSummBOS = new ArrayList<>();
        List<String> areas = materialInStockAPI.findAllArea(date);
        for (String area : areas) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> projectGroups = materialInStockAPI.findProByAre(date, area);
            if (projectGroups != null && projectGroups.size() > 0) {
                for (String project : projectGroups) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<MaterialState> status = materialInStockAPI.findStatusByAreAnpro(date, area, project);
                    for (MaterialState state : status) {
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByAreAnpro(date, area, project, state);
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        for (MaterialInStockBO materialInStockBO : materialInStockBOS) {
                            underlyNum += materialInStockBO.getQuantity();
                            underlyAmount += materialInStockBO.getTotalSum();
                        }
                        AreaStockSummBO areaStockSummBO = new AreaStockSummBO();
                        areaStockSummBO.setArea(area);//现入库地区
                        areaStockSummBO.setProjectGroup(project);//项目组
                        areaStockSummBO.setStockStatus(changStockStatus(state));//物质状态
                        areaStockSummBO.setTotalNum(underlyNum);//总数量
                        areaStockSummBO.setTotalAmount(underlyAmount);//总金额
                        areaStockSummBOS.add(areaStockSummBO);

                        arLevNum += underlyNum;
                        arLevAmount += underlyAmount;
                    }
                    AreaStockSummBO areaStockSummBO1 = new AreaStockSummBO();
                    areaStockSummBO1.setStockStatus("合计");
                    areaStockSummBO1.setTotalNum(arLevNum);
                    areaStockSummBO1.setTotalAmount(arLevAmount);
                    areaStockSummBOS.add(areaStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                AreaStockSummBO areaStockSummBO2 = new AreaStockSummBO();
                areaStockSummBO2.setStockStatus("合计");
                areaStockSummBO2.setTotalNum(tyLevNum);
                areaStockSummBO2.setTotalAmount(tyLevAmount);
                areaStockSummBOS.add(areaStockSummBO2);
            }
        }

        return areaStockSummBOS;
    }

    public String changDeviceStatus(com.bjike.goddess.devicerepair.type.MaterialState materialState) {
        String status = "";
        switch (materialState) {
            case WAITING_REPAIR:
                status = "待维修";
                break;
            case NORMALITY:
                status = "正常";
                break;
            case SCRAPED:
                status = "已报废";
                break;
            default:
                status = "";
                break;
        }
        return status;
    }


    @Override
    public List<StatusDeviceSummBO> statusDeviceSummDay(String summTime) throws SerException {
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        List<StatusDeviceSummBO> statusDeviceSummBOS = new ArrayList<>();
        List<com.bjike.goddess.devicerepair.type.MaterialState> materialStateList = deviceRepairAPI.findDeviceStatus(date);
        if (materialStateList != null && materialStateList.size() > 0) {
            Integer outNum = 0;
            Double outAmount = 0d;
            for (com.bjike.goddess.devicerepair.type.MaterialState materialState : materialStateList) {
                Integer tyLevNum = 0;
                Double tyLevAmount = 0d;
                List<String> areas = deviceRepairAPI.findAreaByStatus(date, materialState);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        Integer arLevNum = 0;
                        Double arLevAmount = 0d;
                        List<String> deptment = deviceRepairAPI.findProjectByStaAnAr(date, materialState, area);
                        for (String dept : deptment) {
                            Double underlyAmount = 0d;
                            List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByStaAnAr(date, materialState, area, dept);
                            for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                                underlyAmount += deviceRepairBO.getRepairPrice();
                            }
                            StatusDeviceSummBO statusDeviceSummBO = new StatusDeviceSummBO();
                            statusDeviceSummBO.setDeviceStatus(changDeviceStatus(materialState));//维修状态
                            statusDeviceSummBO.setArea(area);//地区
                            statusDeviceSummBO.setDepartment(dept);//项目组
                            statusDeviceSummBO.setTotalNum(deviceRepairBOList.size());//总数量
                            statusDeviceSummBO.setTotalAmount(underlyAmount);//总金额
                            statusDeviceSummBOS.add(statusDeviceSummBO);

                            arLevNum += deviceRepairBOList.size();
                            arLevAmount += underlyAmount;
                        }
                        StatusDeviceSummBO statusDeviceSummBO1 = new StatusDeviceSummBO();
                        statusDeviceSummBO1.setArea("合计");
                        statusDeviceSummBO1.setTotalNum(arLevNum);
                        statusDeviceSummBO1.setTotalAmount(arLevAmount);
                        statusDeviceSummBOS.add(statusDeviceSummBO1);

                        tyLevNum += arLevNum;
                        tyLevAmount += arLevAmount;
                    }
                    StatusDeviceSummBO statusDeviceSummBO2 = new StatusDeviceSummBO();
                    statusDeviceSummBO2.setDeviceStatus("合计");
                    statusDeviceSummBO2.setTotalNum(tyLevNum);
                    statusDeviceSummBO2.setTotalAmount(tyLevAmount);
                    statusDeviceSummBOS.add(statusDeviceSummBO2);

                    outNum += tyLevNum;
                    outAmount += tyLevAmount;
                }
                StatusDeviceSummBO statusDeviceSummBO4 = new StatusDeviceSummBO();
                statusDeviceSummBO4.setDeviceStatus("维修情况合计");
                statusDeviceSummBO4.setTotalNum(outNum);
                statusDeviceSummBO4.setTotalAmount(outAmount);
                statusDeviceSummBOS.add(statusDeviceSummBO4);
            }
        }
        return statusDeviceSummBOS;

    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummWeek(Integer year, Integer month, Integer week) throws SerException {

        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        List<StatusDeviceSummBO> statusDeviceSummBOS = new ArrayList<>();
        List<com.bjike.goddess.devicerepair.type.MaterialState> materialStateList = deviceRepairAPI.findDeviceStatus(getTimes(year, month, week));
        if (materialStateList != null && materialStateList.size() > 0) {
            Integer outNum = 0;
            Double outAmount = 0d;
            for (com.bjike.goddess.devicerepair.type.MaterialState materialState : materialStateList) {
                Integer tyLevNum = 0;
                Double tyLevAmount = 0d;
                List<String> areas = deviceRepairAPI.findAreaByStatus(getTimes(year, month, week), materialState);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        Integer arLevNum = 0;
                        Double arLevAmount = 0d;
                        List<String> deptment = deviceRepairAPI.findProjectByStaAnAr(getTimes(year, month, week), materialState, area);
                        for (String dept : deptment) {
                            Double underlyAmount = 0d;
                            List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByStaAnAr(getTimes(year, month, week), materialState, area, dept);
                            for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                                underlyAmount += deviceRepairBO.getRepairPrice();
                            }
                            StatusDeviceSummBO statusDeviceSummBO = new StatusDeviceSummBO();
                            statusDeviceSummBO.setDeviceStatus(changDeviceStatus(materialState));//维修状态
                            statusDeviceSummBO.setArea(area);//地区
                            statusDeviceSummBO.setDepartment(dept);//项目组
                            statusDeviceSummBO.setTotalNum(deviceRepairBOList.size());//总数量
                            statusDeviceSummBO.setTotalAmount(underlyAmount);//总金额
                            statusDeviceSummBOS.add(statusDeviceSummBO);

                            arLevNum += deviceRepairBOList.size();
                            arLevAmount += underlyAmount;
                        }
                        StatusDeviceSummBO statusDeviceSummBO1 = new StatusDeviceSummBO();
                        statusDeviceSummBO1.setArea("合计");
                        statusDeviceSummBO1.setTotalNum(arLevNum);
                        statusDeviceSummBO1.setTotalAmount(arLevAmount);
                        statusDeviceSummBOS.add(statusDeviceSummBO1);

                        tyLevNum += arLevNum;
                        tyLevAmount += arLevAmount;
                    }
                    StatusDeviceSummBO statusDeviceSummBO2 = new StatusDeviceSummBO();
                    statusDeviceSummBO2.setDeviceStatus("合计");
                    statusDeviceSummBO2.setTotalNum(tyLevNum);
                    statusDeviceSummBO2.setTotalAmount(tyLevAmount);
                    statusDeviceSummBOS.add(statusDeviceSummBO2);

                    outNum += tyLevNum;
                    outAmount += tyLevAmount;
                }
                StatusDeviceSummBO statusDeviceSummBO4 = new StatusDeviceSummBO();
                statusDeviceSummBO4.setDeviceStatus("维修情况合计");
                statusDeviceSummBO4.setTotalNum(outNum);
                statusDeviceSummBO4.setTotalAmount(outAmount);
                statusDeviceSummBOS.add(statusDeviceSummBO4);
            }
        }
        return statusDeviceSummBOS;
    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummMonth(Integer year, Integer month) throws SerException {
        Integer ye = LocalDate.now().getYear();
        Integer mon = LocalDate.now().getMonthValue();
        if (year != null) {
            ye = year;
        }
        if (month != null) {
            mon = month;
        }
        String startDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + "01";
        String endDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<StatusDeviceSummBO> statusDeviceSummBOS = new ArrayList<>();
        List<com.bjike.goddess.devicerepair.type.MaterialState> materialStateList = deviceRepairAPI.findDeviceStatus(date);
        if (materialStateList != null && materialStateList.size() > 0) {
            Integer outNum = 0;
            Double outAmount = 0d;
            for (com.bjike.goddess.devicerepair.type.MaterialState materialState : materialStateList) {
                Integer tyLevNum = 0;
                Double tyLevAmount = 0d;
                List<String> areas = deviceRepairAPI.findAreaByStatus(date, materialState);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        Integer arLevNum = 0;
                        Double arLevAmount = 0d;
                        List<String> deptment = deviceRepairAPI.findProjectByStaAnAr(date, materialState, area);
                        for (String dept : deptment) {
                            Double underlyAmount = 0d;
                            List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByStaAnAr(date, materialState, area, dept);
                            for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                                underlyAmount += deviceRepairBO.getRepairPrice();
                            }
                            StatusDeviceSummBO statusDeviceSummBO = new StatusDeviceSummBO();
                            statusDeviceSummBO.setDeviceStatus(changDeviceStatus(materialState));//维修状态
                            statusDeviceSummBO.setArea(area);//地区
                            statusDeviceSummBO.setDepartment(dept);//项目组
                            statusDeviceSummBO.setTotalNum(deviceRepairBOList.size());//总数量
                            statusDeviceSummBO.setTotalAmount(underlyAmount);//总金额
                            statusDeviceSummBOS.add(statusDeviceSummBO);

                            arLevNum += deviceRepairBOList.size();
                            arLevAmount += underlyAmount;
                        }
                        StatusDeviceSummBO statusDeviceSummBO1 = new StatusDeviceSummBO();
                        statusDeviceSummBO1.setArea("合计");
                        statusDeviceSummBO1.setTotalNum(arLevNum);
                        statusDeviceSummBO1.setTotalAmount(arLevAmount);
                        statusDeviceSummBOS.add(statusDeviceSummBO1);

                        tyLevNum += arLevNum;
                        tyLevAmount += arLevAmount;
                    }
                    StatusDeviceSummBO statusDeviceSummBO2 = new StatusDeviceSummBO();
                    statusDeviceSummBO2.setDeviceStatus("合计");
                    statusDeviceSummBO2.setTotalNum(tyLevNum);
                    statusDeviceSummBO2.setTotalAmount(tyLevAmount);
                    statusDeviceSummBOS.add(statusDeviceSummBO2);

                    outNum += tyLevNum;
                    outAmount += tyLevAmount;
                }
                StatusDeviceSummBO statusDeviceSummBO4 = new StatusDeviceSummBO();
                statusDeviceSummBO4.setDeviceStatus("维修情况合计");
                statusDeviceSummBO4.setTotalNum(outNum);
                statusDeviceSummBO4.setTotalAmount(outAmount);
                statusDeviceSummBOS.add(statusDeviceSummBO4);
            }
        }
        return statusDeviceSummBOS;
    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = year + "-" + "01" + "-" + "01";
        String endDate = year + "-" + 12 + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<StatusDeviceSummBO> statusDeviceSummBOS = new ArrayList<>();
        List<com.bjike.goddess.devicerepair.type.MaterialState> materialStateList = deviceRepairAPI.findDeviceStatus(date);
        if (materialStateList != null && materialStateList.size() > 0) {
            Integer outNum = 0;
            Double outAmount = 0d;
            for (com.bjike.goddess.devicerepair.type.MaterialState materialState : materialStateList) {
                Integer tyLevNum = 0;
                Double tyLevAmount = 0d;
                List<String> areas = deviceRepairAPI.findAreaByStatus(date, materialState);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        Integer arLevNum = 0;
                        Double arLevAmount = 0d;
                        List<String> deptment = deviceRepairAPI.findProjectByStaAnAr(date, materialState, area);
                        for (String dept : deptment) {
                            Double underlyAmount = 0d;
                            List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByStaAnAr(date, materialState, area, dept);
                            for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                                underlyAmount += deviceRepairBO.getRepairPrice();
                            }
                            StatusDeviceSummBO statusDeviceSummBO = new StatusDeviceSummBO();
                            statusDeviceSummBO.setDeviceStatus(changDeviceStatus(materialState));//维修状态
                            statusDeviceSummBO.setArea(area);//地区
                            statusDeviceSummBO.setDepartment(dept);//项目组
                            statusDeviceSummBO.setTotalNum(deviceRepairBOList.size());//总数量
                            statusDeviceSummBO.setTotalAmount(underlyAmount);//总金额
                            statusDeviceSummBOS.add(statusDeviceSummBO);

                            arLevNum += deviceRepairBOList.size();
                            arLevAmount += underlyAmount;
                        }
                        StatusDeviceSummBO statusDeviceSummBO1 = new StatusDeviceSummBO();
                        statusDeviceSummBO1.setArea("合计");
                        statusDeviceSummBO1.setTotalNum(arLevNum);
                        statusDeviceSummBO1.setTotalAmount(arLevAmount);
                        statusDeviceSummBOS.add(statusDeviceSummBO1);

                        tyLevNum += arLevNum;
                        tyLevAmount += arLevAmount;
                    }
                    StatusDeviceSummBO statusDeviceSummBO2 = new StatusDeviceSummBO();
                    statusDeviceSummBO2.setDeviceStatus("合计");
                    statusDeviceSummBO2.setTotalNum(tyLevNum);
                    statusDeviceSummBO2.setTotalAmount(tyLevAmount);
                    statusDeviceSummBOS.add(statusDeviceSummBO2);

                    outNum += tyLevNum;
                    outAmount += tyLevAmount;
                }
                StatusDeviceSummBO statusDeviceSummBO4 = new StatusDeviceSummBO();
                statusDeviceSummBO4.setDeviceStatus("维修情况合计");
                statusDeviceSummBO4.setTotalNum(outNum);
                statusDeviceSummBO4.setTotalAmount(outAmount);
                statusDeviceSummBOS.add(statusDeviceSummBO4);
            }
        }
        return statusDeviceSummBOS;
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummDay(String summTime) throws SerException {
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        List<WarrantyDeviceSummBO> warrantyDeviceSummBOS = new ArrayList<>();
        List<Boolean> boolList = deviceRepairAPI.findBool(date);
            for (Boolean bool : boolList) {
                Integer tyLevNum = 0;
                Double tyLevAmount = 0d;
                List<String> areas = deviceRepairAPI.findAreaByBool(date, bool);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        Integer arLevNum = 0;
                        Double arLevAmount = 0d;
                        List<String> deptment = deviceRepairAPI.findProjByBoArea(date, area, bool);
                        for (String dept : deptment) {
                            Double underlyAmount = 0d;
                            List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByBoAnArDep(date, area, bool,dept);
                            for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                                underlyAmount += deviceRepairBO.getRepairPrice();
                            }
                            WarrantyDeviceSummBO warrantyDeviceSummBO = new WarrantyDeviceSummBO();
                            warrantyDeviceSummBO.setWarranty(bool?"是保修期":"非保修期");//是否在保修期
                            warrantyDeviceSummBO.setArea(area);//地区
                            warrantyDeviceSummBO.setDepartment(dept);//项目组
                            warrantyDeviceSummBO.setTotalNum(deviceRepairBOList.size());//总数量
                            warrantyDeviceSummBO.setTotalAmount(underlyAmount);//总金额
                            warrantyDeviceSummBOS.add(warrantyDeviceSummBO);

                            arLevNum += deviceRepairBOList.size();
                            arLevAmount += underlyAmount;
                        }
                        WarrantyDeviceSummBO warrantyDeviceSummBO1 = new WarrantyDeviceSummBO();
                        warrantyDeviceSummBO1.setArea("合计");
                        warrantyDeviceSummBO1.setTotalNum(arLevNum);
                        warrantyDeviceSummBO1.setTotalAmount(arLevAmount);
                        warrantyDeviceSummBOS.add(warrantyDeviceSummBO1);

                        tyLevNum += arLevNum;
                        tyLevAmount += arLevAmount;
                    }
                    WarrantyDeviceSummBO warrantyDeviceSummBO2 = new WarrantyDeviceSummBO();
                    warrantyDeviceSummBO2.setWarranty("合计");
                    warrantyDeviceSummBO2.setTotalNum(tyLevNum);
                    warrantyDeviceSummBO2.setTotalAmount(tyLevAmount);
                    warrantyDeviceSummBOS.add(warrantyDeviceSummBO2);

                }
            }
        return warrantyDeviceSummBOS;
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        List<WarrantyDeviceSummBO> warrantyDeviceSummBOS = new ArrayList<>();
        List<Boolean> boolList = deviceRepairAPI.findBool(getTimes(year,month,week));
        for (Boolean bool : boolList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areas = deviceRepairAPI.findAreaByBool(getTimes(year,month,week), bool);
            if (areas != null && areas.size() > 0) {
                for (String area : areas) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> deptment = deviceRepairAPI.findProjByBoArea(getTimes(year,month,week), area, bool);
                    for (String dept : deptment) {
                        Double underlyAmount = 0d;
                        List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByBoAnArDep(getTimes(year,month,week), area, bool,dept);
                        for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                            underlyAmount += deviceRepairBO.getRepairPrice();
                        }
                        WarrantyDeviceSummBO warrantyDeviceSummBO = new WarrantyDeviceSummBO();
                        warrantyDeviceSummBO.setWarranty(bool?"是保修期":"非保修期");//是否在保修期
                        warrantyDeviceSummBO.setArea(area);//地区
                        warrantyDeviceSummBO.setDepartment(dept);//项目组
                        warrantyDeviceSummBO.setTotalNum(deviceRepairBOList.size());//总数量
                        warrantyDeviceSummBO.setTotalAmount(underlyAmount);//总金额
                        warrantyDeviceSummBOS.add(warrantyDeviceSummBO);

                        arLevNum += deviceRepairBOList.size();
                        arLevAmount += underlyAmount;
                    }
                    WarrantyDeviceSummBO warrantyDeviceSummBO1 = new WarrantyDeviceSummBO();
                    warrantyDeviceSummBO1.setArea("合计");
                    warrantyDeviceSummBO1.setTotalNum(arLevNum);
                    warrantyDeviceSummBO1.setTotalAmount(arLevAmount);
                    warrantyDeviceSummBOS.add(warrantyDeviceSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                WarrantyDeviceSummBO warrantyDeviceSummBO2 = new WarrantyDeviceSummBO();
                warrantyDeviceSummBO2.setWarranty("合计");
                warrantyDeviceSummBO2.setTotalNum(tyLevNum);
                warrantyDeviceSummBO2.setTotalAmount(tyLevAmount);
                warrantyDeviceSummBOS.add(warrantyDeviceSummBO2);

            }
        }
        return warrantyDeviceSummBOS;
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummMonth(Integer year, Integer month) throws SerException {
        Integer ye = LocalDate.now().getYear();
        Integer mon = LocalDate.now().getMonthValue();
        if (year != null) {
            ye = year;
        }
        if (month != null) {
            mon = month;
        }
        String startDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + "01";
        String endDate = ye + "-" + (mon > 9 ? mon : "0" + mon) + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<WarrantyDeviceSummBO> warrantyDeviceSummBOS = new ArrayList<>();
        List<Boolean> boolList = deviceRepairAPI.findBool(date);
        for (Boolean bool : boolList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areas = deviceRepairAPI.findAreaByBool(date, bool);
            if (areas != null && areas.size() > 0) {
                for (String area : areas) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> deptment = deviceRepairAPI.findProjByBoArea(date, area, bool);
                    for (String dept : deptment) {
                        Double underlyAmount = 0d;
                        List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByBoAnArDep(date, area, bool,dept);
                        for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                            underlyAmount += deviceRepairBO.getRepairPrice();
                        }
                        WarrantyDeviceSummBO warrantyDeviceSummBO = new WarrantyDeviceSummBO();
                        warrantyDeviceSummBO.setWarranty(bool?"是保修期":"非保修期");//是否在保修期
                        warrantyDeviceSummBO.setArea(area);//地区
                        warrantyDeviceSummBO.setDepartment(dept);//项目组
                        warrantyDeviceSummBO.setTotalNum(deviceRepairBOList.size());//总数量
                        warrantyDeviceSummBO.setTotalAmount(underlyAmount);//总金额
                        warrantyDeviceSummBOS.add(warrantyDeviceSummBO);

                        arLevNum += deviceRepairBOList.size();
                        arLevAmount += underlyAmount;
                    }
                    WarrantyDeviceSummBO warrantyDeviceSummBO1 = new WarrantyDeviceSummBO();
                    warrantyDeviceSummBO1.setArea("合计");
                    warrantyDeviceSummBO1.setTotalNum(arLevNum);
                    warrantyDeviceSummBO1.setTotalAmount(arLevAmount);
                    warrantyDeviceSummBOS.add(warrantyDeviceSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                WarrantyDeviceSummBO warrantyDeviceSummBO2 = new WarrantyDeviceSummBO();
                warrantyDeviceSummBO2.setWarranty("合计");
                warrantyDeviceSummBO2.setTotalNum(tyLevNum);
                warrantyDeviceSummBO2.setTotalAmount(tyLevAmount);
                warrantyDeviceSummBOS.add(warrantyDeviceSummBO2);

            }
        }
        return warrantyDeviceSummBOS;
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = year + "-" + "01" + "-" + "01";
        String endDate = year + "-" + 12 + "-" + 31;
        String[] date = new String[]{startDate, endDate};
        List<WarrantyDeviceSummBO> warrantyDeviceSummBOS = new ArrayList<>();
        List<Boolean> boolList = deviceRepairAPI.findBool(date);
        for (Boolean bool : boolList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areas = deviceRepairAPI.findAreaByBool(date, bool);
            if (areas != null && areas.size() > 0) {
                for (String area : areas) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> deptment = deviceRepairAPI.findProjByBoArea(date, area, bool);
                    for (String dept : deptment) {
                        Double underlyAmount = 0d;
                        List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByBoAnArDep(date, area, bool,dept);
                        for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                            underlyAmount += deviceRepairBO.getRepairPrice();
                        }
                        WarrantyDeviceSummBO warrantyDeviceSummBO = new WarrantyDeviceSummBO();
                        warrantyDeviceSummBO.setWarranty(bool?"是保修期":"非保修期");//是否在保修期
                        warrantyDeviceSummBO.setArea(area);//地区
                        warrantyDeviceSummBO.setDepartment(dept);//项目组
                        warrantyDeviceSummBO.setTotalNum(deviceRepairBOList.size());//总数量
                        warrantyDeviceSummBO.setTotalAmount(underlyAmount);//总金额
                        warrantyDeviceSummBOS.add(warrantyDeviceSummBO);

                        arLevNum += deviceRepairBOList.size();
                        arLevAmount += underlyAmount;
                    }
                    WarrantyDeviceSummBO warrantyDeviceSummBO1 = new WarrantyDeviceSummBO();
                    warrantyDeviceSummBO1.setArea("合计");
                    warrantyDeviceSummBO1.setTotalNum(arLevNum);
                    warrantyDeviceSummBO1.setTotalAmount(arLevAmount);
                    warrantyDeviceSummBOS.add(warrantyDeviceSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                WarrantyDeviceSummBO warrantyDeviceSummBO2 = new WarrantyDeviceSummBO();
                warrantyDeviceSummBO2.setWarranty("合计");
                warrantyDeviceSummBO2.setTotalNum(tyLevNum);
                warrantyDeviceSummBO2.setTotalAmount(tyLevAmount);
                warrantyDeviceSummBOS.add(warrantyDeviceSummBO2);

            }
        }
        return warrantyDeviceSummBOS;
    }
}