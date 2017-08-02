package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.materialbuy.api.MaterialBuyAPI;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.entity.MaterialBuy;
import com.bjike.goddess.materialsummary.bo.PersonalBuySummBO;
import com.bjike.goddess.materialsummary.bo.SendEmailBO;
import com.bjike.goddess.materialsummary.bo.TypeBuySummBO;
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
                    List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(devType, area, date);
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setType(devType);//设备类型
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(materialBuyBO.getProjectTeam());//部门(项目组)
                        typeBuySummBO.setTotalNum(materialBuyBO.getQuantity());//数量
                        typeBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());//金额
                        typeBuySummBOList.add(typeBuySummBO);

                        arLevNum += materialBuyBO.getQuantity();
                        arLevAmount += materialBuyBO.getTotalSum();
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
                    List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(devType, area, getTimes(year, month, week));
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setType(devType);//设备类型
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(materialBuyBO.getProjectTeam());//部门(项目组)
                        typeBuySummBO.setTotalNum(materialBuyBO.getQuantity());//数量
                        typeBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());//金额
                        typeBuySummBOList.add(typeBuySummBO);

                        arLevNum += materialBuyBO.getQuantity();
                        arLevAmount += materialBuyBO.getTotalSum();
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
                    List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(devType, area, date);
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setType(devType);//设备类型
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(materialBuyBO.getProjectTeam());//部门(项目组)
                        typeBuySummBO.setTotalNum(materialBuyBO.getQuantity());//数量
                        typeBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());//金额
                        typeBuySummBOList.add(typeBuySummBO);

                        arLevNum += materialBuyBO.getQuantity();
                        arLevAmount += materialBuyBO.getTotalSum();
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
                    List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(devType, area, date);
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setType(devType);//设备类型
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(materialBuyBO.getProjectTeam());//部门(项目组)
                        typeBuySummBO.setTotalNum(materialBuyBO.getQuantity());//数量
                        typeBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());//金额
                        typeBuySummBOList.add(typeBuySummBO);

                        arLevNum += materialBuyBO.getQuantity();
                        arLevAmount += materialBuyBO.getTotalSum();
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
                    List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTeamAnArea(area, dep, date);
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(dep);//部门(项目组)
                        typeBuySummBO.setType(materialBuyBO.getDeviceType());//设备类型
                        typeBuySummBO.setTotalNum(materialBuyBO.getQuantity());//数量
                        typeBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());//金额
                        areaBuySummBOList.add(typeBuySummBO);

                        arLevNum += materialBuyBO.getQuantity();
                        arLevAmount += materialBuyBO.getTotalSum();
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
                    List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTeamAnArea(area, dep, getTimes(year, month, week));
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(dep);//部门(项目组)
                        typeBuySummBO.setType(materialBuyBO.getDeviceType());//设备类型
                        typeBuySummBO.setTotalNum(materialBuyBO.getQuantity());//数量
                        typeBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());//金额
                        areaBuySummBOList.add(typeBuySummBO);

                        arLevNum += materialBuyBO.getQuantity();
                        arLevAmount += materialBuyBO.getTotalSum();
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
                    List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTeamAnArea(area, dep, date);
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(dep);//部门(项目组)
                        typeBuySummBO.setType(materialBuyBO.getDeviceType());//设备类型
                        typeBuySummBO.setTotalNum(materialBuyBO.getQuantity());//数量
                        typeBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());//金额
                        areaBuySummBOList.add(typeBuySummBO);

                        arLevNum += materialBuyBO.getQuantity();
                        arLevAmount += materialBuyBO.getTotalSum();
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
                    List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTeamAnArea(area, dep, date);
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS) {
                        TypeBuySummBO typeBuySummBO = new TypeBuySummBO();
                        typeBuySummBO.setArea(area);//地区
                        typeBuySummBO.setDepartment(dep);//部门(项目组)
                        typeBuySummBO.setType(materialBuyBO.getDeviceType());//设备类型
                        typeBuySummBO.setTotalNum(materialBuyBO.getQuantity());//数量
                        typeBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());//金额
                        areaBuySummBOList.add(typeBuySummBO);

                        arLevNum += materialBuyBO.getQuantity();
                        arLevAmount += materialBuyBO.getTotalSum();
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
            List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByRequis(person, date);
            if (materialBuyBOS != null && materialBuyBOS.size() > 0) {
                for (MaterialBuyBO materialBuyBO : materialBuyBOS){
                    PersonalBuySummBO personalBuySummBO = new PersonalBuySummBO();
                    personalBuySummBO.setName(person);
                    personalBuySummBO.setType(materialBuyBO.getDeviceType());
                    personalBuySummBO.setTotalNum(materialBuyBO.getQuantity());
                    personalBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());
                    personalBuySummBOS.add(personalBuySummBO);

                    tyLevNum += materialBuyBO.getQuantity();
                    tyLevAmount += materialBuyBO.getTotalSum();
                }
                PersonalBuySummBO personalBuySummBO1 = new PersonalBuySummBO();
                personalBuySummBO1.setType("合计");
                personalBuySummBO1.setTotalNum(tyLevNum);
                personalBuySummBO1.setTotalAmount(tyLevAmount);
                personalBuySummBOS.add(personalBuySummBO1);
            }

        }
        return personalBuySummBOS;
    }

        @Override
        public List<PersonalBuySummBO> personBuySummWeek (Integer year, Integer month, Integer week) throws SerException {
            if (year == null || month == null || week == null) {
                year = LocalDate.now().getYear();
                month = LocalDate.now().getMonthValue();
                Calendar c = Calendar.getInstance();
                week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
            }
            List<PersonalBuySummBO> personalBuySummBOS = new ArrayList<>();
            List<String> personList = materialBuyAPI.findRequis(getTimes(year,month,week));
            for (String person : personList) {
                Integer tyLevNum = 0;
                Double tyLevAmount = 0d;
                List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByRequis(person, getTimes(year,month,week));
                if (materialBuyBOS != null && materialBuyBOS.size() > 0) {
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS){
                        PersonalBuySummBO personalBuySummBO = new PersonalBuySummBO();
                        personalBuySummBO.setName(person);
                        personalBuySummBO.setType(materialBuyBO.getDeviceType());
                        personalBuySummBO.setTotalNum(materialBuyBO.getQuantity());
                        personalBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());
                        personalBuySummBOS.add(personalBuySummBO);

                        tyLevNum += materialBuyBO.getQuantity();
                        tyLevAmount += materialBuyBO.getTotalSum();
                    }
                    PersonalBuySummBO personalBuySummBO1 = new PersonalBuySummBO();
                    personalBuySummBO1.setType("合计");
                    personalBuySummBO1.setTotalNum(tyLevNum);
                    personalBuySummBO1.setTotalAmount(tyLevAmount);
                    personalBuySummBOS.add(personalBuySummBO1);
                }

            }
            return personalBuySummBOS;
        }

        @Override
        public List<PersonalBuySummBO> personBuySummMonth (Integer year, Integer month) throws SerException {
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
                List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByRequis(person, date);
                if (materialBuyBOS != null && materialBuyBOS.size() > 0) {
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS){
                        PersonalBuySummBO personalBuySummBO = new PersonalBuySummBO();
                        personalBuySummBO.setName(person);
                        personalBuySummBO.setType(materialBuyBO.getDeviceType());
                        personalBuySummBO.setTotalNum(materialBuyBO.getQuantity());
                        personalBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());
                        personalBuySummBOS.add(personalBuySummBO);

                        tyLevNum += materialBuyBO.getQuantity();
                        tyLevAmount += materialBuyBO.getTotalSum();
                    }
                    PersonalBuySummBO personalBuySummBO1 = new PersonalBuySummBO();
                    personalBuySummBO1.setType("合计");
                    personalBuySummBO1.setTotalNum(tyLevNum);
                    personalBuySummBO1.setTotalAmount(tyLevAmount);
                    personalBuySummBOS.add(personalBuySummBO1);
                }

            }
            return personalBuySummBOS;
        }

        @Override
        public List<PersonalBuySummBO> personBuySummYear (Integer year) throws SerException {
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
                List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByRequis(person, date);
                if (materialBuyBOS != null && materialBuyBOS.size() > 0) {
                    for (MaterialBuyBO materialBuyBO : materialBuyBOS){
                        PersonalBuySummBO personalBuySummBO = new PersonalBuySummBO();
                        personalBuySummBO.setName(person);
                        personalBuySummBO.setType(materialBuyBO.getDeviceType());
                        personalBuySummBO.setTotalNum(materialBuyBO.getQuantity());
                        personalBuySummBO.setTotalAmount(materialBuyBO.getTotalSum());
                        personalBuySummBOS.add(personalBuySummBO);

                        tyLevNum += materialBuyBO.getQuantity();
                        tyLevAmount += materialBuyBO.getTotalSum();
                    }
                    PersonalBuySummBO personalBuySummBO1 = new PersonalBuySummBO();
                    personalBuySummBO1.setType("合计");
                    personalBuySummBO1.setTotalNum(tyLevNum);
                    personalBuySummBO1.setTotalAmount(tyLevAmount);
                    personalBuySummBOS.add(personalBuySummBO1);
                }

            }
            return personalBuySummBOS;
        }
    }