package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
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
import com.bjike.goddess.materialsummary.excel.SonPermissionObject;
import com.bjike.goddess.materialsummary.to.GuidePermissionTO;
import com.bjike.goddess.materialsummary.to.SendEmailTO;
import com.bjike.goddess.materialsummary.type.*;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
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
import java.time.ZoneId;
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
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;


    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        {
            List<SonPermissionObject> list = new ArrayList<>();
            String userToken = RpcTransmit.getUserToken();
            Boolean flagInvenDai = guideIdentity();
            RpcTransmit.transmitUserToken(userToken);

            SonPermissionObject obj = new SonPermissionObject();

            obj = new SonPermissionObject();
            obj.setName("typebuysummary");
            obj.setDescribesion("物质分类购买情况汇总");
            if (flagInvenDai ) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);

            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("areabuysummary");
            obj.setDescribesion("部门地区购买情况汇总");
            if (flagInvenDai ) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("personbuysummary");
            obj.setDescribesion("个人物质购买情况汇总");
            if (flagInvenDai ) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("socestocksummary");
            obj.setDescribesion("入库来源汇总");
            if (flagInvenDai ) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("areastocksummary");
            obj.setDescribesion("地区入库情况汇总");
            if (flagInvenDai ) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("statedevicsummary");
            obj.setDescribesion("维修状态分类情况汇总");
            if (flagInvenDai ) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("warrdevicsummary");
            obj.setDescribesion("保修状态分类情况汇总");
            if (flagInvenDai ) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);

            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("sendEmail");
            obj.setDescribesion("发送邮件");
            if (flagInvenDai ) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);

            return list;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }
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
        checkPermission();
        SendEmailDTO.getSorts().add("createTime=desc");
        List<SendEmail> list = super.findByPage(SendEmailDTO);
        return BeanTransform.copyProperties(list, SendEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SendEmailBO addCollectEmail(SendEmailTO SendEmailTO) throws SerException {
        checkPermission();
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
        checkPermission();
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
        checkPermission();
        super.remove(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        checkPermission();
        SendEmail buySendEmail = super.findById(id);
        buySendEmail.setStatus(Status.CONGEAL);
        super.update(buySendEmail);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        checkPermission();
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
        List<SummaryType> typeList = new ArrayList<>(0);
        if (moduleType.equals(ModuleType.MATERIALBUY)) {
            typeList = Arrays.asList(SummaryType.MATCLASSIFBUY, SummaryType.DEPARTAREABUY, SummaryType.PERSONMATBUY);
        } else if (moduleType.equals(ModuleType.MATERIALSTOR)) {
            typeList = Arrays.asList(SummaryType.STOCKSOURCE, SummaryType.AREASTOCK);
        } else if (moduleType.equals(ModuleType.MATERIALMAINTEN)) {
            typeList = Arrays.asList(SummaryType.MAINTENSTATUS, SummaryType.WARRANTYSTATUS);
        }
        return typeList;
    }

    @Override
    public List<TypeBuySummBO> typeBuySummDay(String summTime) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        return typeBuySendEmail(date);
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
        checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        return typeBuySendEmail(date);
    }

    @Override
    public List<TypeBuySummBO> typeBuySummMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        month = month != null ? month : LocalDate.now().getMonthValue();
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, 12));
        String[] date = new String[]{startDate, endDate};
        return typeBuySendEmail(date);
    }

    @Override
    public List<TypeBuySummBO> typeBuySummYear(Integer year) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, 31));
        String[] date = new String[]{startDate, endDate};
        return typeBuySendEmail(date);
    }

    /**
     * 年月周日,发送邮件物质购买类型汇总
     *
     * @param dateTime
     * @return
     * @throws SerException
     */
    public List<TypeBuySummBO> typeBuySendEmail(String[] dateTime) throws SerException {
        List<TypeBuySummBO> typeBuySummBOList = new ArrayList<>();
        List<String> typeList = materialBuyAPI.findDevType(dateTime);
        for (String devType : typeList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialBuyAPI.findAreaByType(devType, dateTime);
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> departments = materialBuyAPI.findDeparByTyAre(devType, area, dateTime);
                    for (String depart : departments) {
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(devType, area, depart, dateTime);
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
                typeBuySummBO2.setType("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                typeBuySummBOList.add(typeBuySummBO2);
            }
        }
        return typeBuySummBOList;
    }


    @Override
    public List<TypeBuySummBO> areaBuySummDay(String summTime) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        return areaBuySendEmail(date);
    }

    @Override
    public List<TypeBuySummBO> areaBuySummWeek(Integer year, Integer month, Integer week) throws SerException {
        checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        return areaBuySendEmail(date);
    }

    @Override
    public List<TypeBuySummBO> areaBuySummMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        month = month != null ? month : LocalDate.now().getMonthValue();
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, 12));
        String[] date = new String[]{startDate, endDate};
        return areaBuySendEmail(date);
    }

    @Override
    public List<TypeBuySummBO> areaBuySummYear(Integer year) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, 31));
        String[] date = new String[]{startDate, endDate};
        return areaBuySendEmail(date);
    }

    /**
     * 年月周日,发送邮件地区购买情况邮件发送汇总
     *
     * @param dateTime
     * @return
     * @throws SerException
     */
    public List<TypeBuySummBO> areaBuySendEmail(String[] dateTime) throws SerException {
        List<TypeBuySummBO> areaBuySummBOList = new ArrayList<>();
        List<String> areaList = materialBuyAPI.findArea(dateTime);
        for (String area : areaList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> depList = materialBuyAPI.findDepByArea(area, dateTime);
            if (depList != null && depList.size() > 0) {
                for (String dep : depList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> devType = materialBuyAPI.findDevByAreaDev(area, dep, dateTime);
                    for (String type : devType) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialBuyBO> materialBuyBOS = materialBuyAPI.findByTyAndAr(type, area, dep, dateTime);
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
                typeBuySummBO2.setArea("合计");
                typeBuySummBO2.setTotalNum(tyLevNum);
                typeBuySummBO2.setTotalAmount(tyLevAmount);
                areaBuySummBOList.add(typeBuySummBO2);
            }
        }

        return areaBuySummBOList;
    }

    @Override
    public List<PersonalBuySummBO> personBuySummDay(String summTime) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        return personBuySendEmail(date);
    }

    @Override
    public List<PersonalBuySummBO> personBuySummWeek(Integer year, Integer month, Integer week) throws SerException {
        checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        return personBuySendEmail(date);
    }

    @Override
    public List<PersonalBuySummBO> personBuySummMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        month = month != null ? month : LocalDate.now().getMonthValue();
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, 12));
        String[] date = new String[]{startDate, endDate};
        return personBuySendEmail(date);
    }

    @Override
    public List<PersonalBuySummBO> personBuySummYear(Integer year) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, 31));
        String[] date = new String[]{startDate, endDate};
        return personBuySendEmail(date);
    }

    /**
     * 年月周日,发送邮件个人购买情况邮件发送汇总
     *
     * @param dateTime
     * @return
     * @throws SerException
     */
    public List<PersonalBuySummBO> personBuySendEmail(String[] dateTime) throws SerException {
        List<PersonalBuySummBO> personalBuySummBOS = new ArrayList<>();
        List<String> personList = materialBuyAPI.findRequis(dateTime);
        for (String person : personList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> devType = materialBuyAPI.findByRequis(person, dateTime);
            for (String type : devType) {
                List<MaterialBuyBO> materialBuyBOList = materialBuyAPI.findByRequisType(person, type, dateTime);
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
            personalBuySummBO1.setName("合计");
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
        checkPermission();
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        return sourStockSendEmail(date);
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummWeek(Integer year, Integer month, Integer week) throws SerException {
        checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        return sourStockSendEmail(date);
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        month = month != null ? month : LocalDate.now().getMonthValue();
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, 12));
        String[] date = new String[]{startDate, endDate};
        return sourStockSendEmail(date);
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummYear(Integer year) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, 31));
        String[] date = new String[]{startDate, endDate};
        return sourStockSendEmail(date);
    }

    /**
     * 年月周日,发送邮件入库来源邮件发送汇总
     *
     * @param dateTime
     * @return
     * @throws SerException
     */
    public List<ResouceStockSummBO> sourStockSendEmail(String[] dateTime) throws SerException {
        List<ResouceStockSummBO> resouceStockSummBOS = new ArrayList<>();
        List<InstockType> typeStocks = materialInStockAPI.findStockType(dateTime);
        for (InstockType type : typeStocks) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areaList = materialInStockAPI.findAreaByType(type, dateTime);
            if (areaList != null && areaList.size() > 0) {
                for (String area : areaList) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> depart = materialInStockAPI.findDepartByTyAnAr(type, area, dateTime);
                    for (String dep : depart) {
                        Integer underlyNum = 0;
                        Double underlyAmount = 0d;
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByTyAnAr(type, area, dep, dateTime);
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
                    resouceStockSummBO1.setArea("合计");
                    resouceStockSummBO1.setTotalNum(arLevNum);
                    resouceStockSummBO1.setTotalAmount(arLevAmount);
                    resouceStockSummBOS.add(resouceStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                ResouceStockSummBO resouceStockSummBO2 = new ResouceStockSummBO();
                resouceStockSummBO2.setSources("合计");
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
        checkPermission();
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String[] date = new String[]{summTime, summTime};
        return areaStockSendEmail(date);
    }

    @Override
    public List<AreaStockSummBO> areaStockSummWeek(Integer year, Integer month, Integer week) throws SerException {
        checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        return areaStockSendEmail(date);
    }

    @Override
    public List<AreaStockSummBO> areaStockSummMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        month = month != null ? month : LocalDate.now().getMonthValue();
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, 12));
        String[] date = new String[]{startDate, endDate};
        return areaStockSendEmail(date);
    }

    @Override
    public List<AreaStockSummBO> areaStockSummYear(Integer year) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, 31));
        String[] date = new String[]{startDate, endDate};
        return areaStockSendEmail(date);
    }

    /**
     * 年月周日,发送邮件地区入库情况汇总
     *
     * @param dateTime
     * @return
     * @throws SerException
     */
    public List<AreaStockSummBO> areaStockSendEmail(String[] dateTime) throws SerException {
        List<AreaStockSummBO> areaStockSummBOS = new ArrayList<>();
        List<String> areas = materialInStockAPI.findAllArea(dateTime);
        for (String area : areas) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> projectGroups = materialInStockAPI.findProByAre(dateTime, area);
            if (projectGroups != null && projectGroups.size() > 0) {
                for (String project : projectGroups) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<MaterialState> status = materialInStockAPI.findStatusByAreAnpro(dateTime, area, project);
                    for (MaterialState state : status) {
                        List<MaterialInStockBO> materialInStockBOS = materialInStockAPI.findByAreAnpro(dateTime, area, project, state);
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
                    areaStockSummBO1.setProjectGroup("合计");
                    areaStockSummBO1.setTotalNum(arLevNum);
                    areaStockSummBO1.setTotalAmount(arLevAmount);
                    areaStockSummBOS.add(areaStockSummBO1);

                    tyLevNum += arLevNum;
                    tyLevAmount += arLevAmount;
                }
                AreaStockSummBO areaStockSummBO2 = new AreaStockSummBO();
                areaStockSummBO2.setArea("合计");
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
        checkPermission();
        if (StringUtils.isBlank(summTime)) {
            summTime = LocalDate.now().toString();
        }
        String start = DateUtil.dateToString(LocalDate.parse(summTime).atTime(00, 00, 00));
        String end = DateUtil.dateToString(LocalDate.parse(summTime).atTime(23, 59, 59));
        String[] date = new String[]{start, end};
        return statusDeviceSendEmail(date);

    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummWeek(Integer year, Integer month, Integer week) throws SerException {
        checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] dateTime = getTimes(year, month, week);
        LocalDateTime start = LocalDate.parse(dateTime[0]).atTime(00, 00, 00);
        LocalDateTime end = LocalDate.parse(dateTime[1]).atTime(23, 59, 59);
        String[] date = new String[]{start.toString(), end.toString()};
        return statusDeviceSendEmail(date);
    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummMonth(Integer year, Integer month) throws SerException {
        year = year != null ? year : LocalDate.now().getYear();
        month = month != null ? month : LocalDate.now().getMonthValue();
        String startDate = DateUtil.dateToString(LocalDateTime.of(year, month, 1, 00, 00, 00));
        String endDate = DateUtil.dateToString(LocalDateTime.of(year, month, DateUtil.getDayByDate(year, month), 23, 59, 59));
        String[] date = new String[]{startDate, endDate};
        return statusDeviceSendEmail(date);
    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummYear(Integer year) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        String startDate = DateUtil.dateToString(LocalDateTime.of(year, 1, 1,00, 00, 00));
        String endDate = DateUtil.dateToString(LocalDateTime.of(year, 12, 31, 23, 59, 59));
        String[] date = new String[]{startDate, endDate};
        return statusDeviceSendEmail(date);
    }

    /**
     * 年月周日,邮件发送维修状态情况汇总
     *
     * @param dateTime
     * @return
     * @throws SerException
     */
    public List<StatusDeviceSummBO> statusDeviceSendEmail(String[] dateTime) throws SerException {
        List<StatusDeviceSummBO> statusDeviceSummBOS = new ArrayList<>();
        List<com.bjike.goddess.devicerepair.type.MaterialState> materialStateList = deviceRepairAPI.findDeviceStatus(dateTime);
        if (materialStateList != null && materialStateList.size() > 0) {
            Integer outNum = 0;
            Double outAmount = 0d;
            for (com.bjike.goddess.devicerepair.type.MaterialState materialState : materialStateList) {
                Integer tyLevNum = 0;
                Double tyLevAmount = 0d;
                List<String> areas = deviceRepairAPI.findAreaByStatus(dateTime, materialState);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        Integer arLevNum = 0;
                        Double arLevAmount = 0d;
                        List<String> deptment = deviceRepairAPI.findProjectByStaAnAr(dateTime, materialState, area);
                        for (String dept : deptment) {
                            Double underlyAmount = 0d;
                            List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByStaAnAr(dateTime, materialState, area, dept);
                            for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                                underlyAmount += deviceRepairBO.getRepairPrice() == null ? 0 : deviceRepairBO.getRepairPrice();
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
        checkPermission();
        summTime = summTime != null ? summTime : LocalDate.now().toString();
        String start = DateUtil.dateToString(LocalDate.parse(summTime).atTime(00, 00, 00));
        String end = DateUtil.dateToString(LocalDate.parse(summTime).atTime(23, 59, 59));
        String[] date = new String[]{start, end};
        return warranDeviceSendEmail(date);
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummWeek(Integer year, Integer month, Integer week) throws SerException {
        checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] dateTime = getTimes(year, month, week);
        LocalDateTime start = LocalDate.parse(dateTime[0]).atTime(00, 00, 00);
        LocalDateTime end = LocalDate.parse(dateTime[1]).atTime(23, 59, 59);
        String[] date = new String[]{start.toString(), end.toString()};
        return warranDeviceSendEmail(date);
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        month = month != null ? month : LocalDate.now().getMonth().getValue();
        String startDate = DateUtil.dateToString(LocalDateTime.of(year, month, 1, 00, 00, 00));
        String endDate = DateUtil.dateToString(LocalDateTime.of(year, month, DateUtil.getDayByDate(year, month), 23, 59, 59));
        String[] date = new String[]{startDate, endDate};
        return warranDeviceSendEmail(date);
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummYear(Integer year) throws SerException {
        checkPermission();
        year = year != null ? year : LocalDate.now().getYear();
        String startDate = DateUtil.dateToString(LocalDateTime.of(year, 1, 1, 00, 00, 00));
        String endDate = DateUtil.dateToString(LocalDateTime.of(year, 12, 31, 23, 59, 59));
        String[] date = new String[]{startDate, endDate};
        return warranDeviceSendEmail(date);
    }

    /**
     * 年月周日,邮件发送是否保修情况汇总
     *
     * @param dateTime
     * @return
     * @throws SerException
     */
    public List<WarrantyDeviceSummBO> warranDeviceSendEmail(String[] dateTime) throws SerException {
        List<WarrantyDeviceSummBO> warrantyDeviceSummBOS = new ArrayList<>();
        List<Boolean> boolList = deviceRepairAPI.findBool(dateTime);
        for (Boolean bool : boolList) {
            Integer tyLevNum = 0;
            Double tyLevAmount = 0d;
            List<String> areas = deviceRepairAPI.findAreaByBool(dateTime, bool);
            if (areas != null && areas.size() > 0) {
                for (String area : areas) {
                    Integer arLevNum = 0;
                    Double arLevAmount = 0d;
                    List<String> deptment = deviceRepairAPI.findProjByBoArea(dateTime, area, bool);
                    for (String dept : deptment) {
                        Double underlyAmount = 0d;
                        List<DeviceRepairBO> deviceRepairBOList = deviceRepairAPI.findByBoAnArDep(dateTime, area, bool, dept);
                        for (DeviceRepairBO deviceRepairBO : deviceRepairBOList) {
                            underlyAmount += deviceRepairBO.getRepairPrice() == null ? 0 : deviceRepairBO.getRepairPrice();
                        }
                        WarrantyDeviceSummBO warrantyDeviceSummBO = new WarrantyDeviceSummBO();
                        warrantyDeviceSummBO.setWarranty(bool ? "是保修期" : "非保修期");//是否在保修期
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

    public String[] changDateTime(CollectUnit collectUnit) {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        switch (collectUnit) {
            case EVERYDAY:
                start = LocalDate.now().atTime(00, 00, 01);
                end = LocalDate.now().atTime(23, 59, 59);
                break;
            case EVERYWEEK:
                start = DateUtil.getStartWeek().atStartOfDay();
                end = DateUtil.getEndWeek().atStartOfDay().plusDays(1).minusNanos(1);
                break;
            case EVERYMONTH:
                start = DateUtil.getStartMonth().atStartOfDay();
                end = DateUtil.getEndMonth().atStartOfDay().plusDays(1).minusNanos(1);
                break;
            case EVERYYEAR:
                start = DateUtil.getStartYear().atStartOfDay();
                end = DateUtil.getEndYear().atStartOfDay().plusDays(1).minusNanos(1);
                break;
            default:
                start = LocalDate.now().atStartOfDay();
                end = start.plusDays(1).minusNanos(1);
                break;
        }
        return new String[]{start.toString(), end.toString()};
    }

    @Override
    public void checkSendEmail() throws SerException {
        List<SendEmail> allEmails = new ArrayList<>();//所有邮件
        List<SendEmail> areaStockEmails = new ArrayList<>();//地区入库邮件
        List<SendEmail> resoStockEmails = new ArrayList<>();//入库来源邮件
        List<SendEmail> typBuyEmails = new ArrayList<>();//分类购买邮件
        List<SendEmail> areaBuyEmails = new ArrayList<>();//地区购买邮件
        List<SendEmail> persoBuyEmails = new ArrayList<>();//个人购买邮件
        List<SendEmail> stateDevEmails = new ArrayList<>();//状态分类维修邮件
        List<SendEmail> warrDevEmails = new ArrayList<>();//保修维修邮件

        //检测有哪些需要发送的
        //上次发送时间
        //现在时间
        //发送间隔
        //发送单位
        //发送类型
        //发送对象
        SendEmailDTO dto = new SendEmailDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<SendEmail> list = super.findByCis(dto);
        LocalDateTime nowTime = LocalDateTime.now();
        for (SendEmail str : list) {
            //上次发送时间
            LocalDateTime lastTime = str.getLastSendTime();
            //发送间隔
            Double sendNum = str.getSendNum();
            //发送单位
            CollectSendUnit collectSendUnit = str.getCollectSendUnit();
            //发送类型
            SummaryType type = str.getSummaryType();
            //发送对象,隔开
            String sendObject = str.getSendObject();

            Long mis = nowTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - lastTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Double temp_sendNum = 0d;
            Boolean flag = false;
            switch (collectSendUnit) {
                case MINUTE:
                    //毫秒数
                    temp_sendNum = sendNum * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(LocalDateTime.now());
                    }
                    break;
                case HOURS:
                    temp_sendNum = sendNum * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(LocalDateTime.now());
                    }
                    break;
                case DAY:
                    temp_sendNum = sendNum * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(LocalDateTime.now());
                    }
                    break;
                case WEEK:
                    temp_sendNum = sendNum * 7 * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(LocalDateTime.now());
                    }
                    break;
                case MONTH:
                    if (nowTime.minusMonths(sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastSendTime(LocalDateTime.now());
                    }
                    break;
                case QUARTER:
                    if (nowTime.minusMonths(3 * sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(3 * sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastSendTime(LocalDateTime.now());
                    }
                    break;
                case YEAR:
                    if (nowTime.minusYears(sendNum.longValue()).isEqual(lastTime) || nowTime.minusYears(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastSendTime(LocalDateTime.now());
                    }
                    break;
            }

            if (flag && type.equals(SummaryType.MATCLASSIFBUY)) {
                typBuyEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals(SummaryType.DEPARTAREABUY)) {
                areaBuyEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals(SummaryType.PERSONMATBUY)) {
                persoBuyEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals(SummaryType.STOCKSOURCE)) {
                resoStockEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals(SummaryType.AREASTOCK)) {
                areaStockEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals(SummaryType.MAINTENSTATUS)) {
                stateDevEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals(SummaryType.WARRANTYSTATUS)) {
                warrDevEmails.add(str);
                allEmails.add(str);
            }
        }

        //调用发邮件
        allEmails = sendObject(typBuyEmails, areaBuyEmails, persoBuyEmails, resoStockEmails, areaStockEmails, stateDevEmails, warrDevEmails);

        //修改上次发送时间
        super.update(allEmails);
    }

    //分类购买html
    private String htmlTyBuy(List<TypeBuySummBO> typeBuySummBOS) throws SerException {
        StringBuffer sb = new StringBuffer("");

        sb = new StringBuffer("<h4>针对各物资分类购买情况汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>类型</td>");
        sb.append("<td>地区(项目组)</td>");
        sb.append("<td>部门</td>");
        sb.append("<td>总数量（“数量”的合计）</td>");
        sb.append("<td>总金额（“总额”的合计）</td>");
        sb.append("<tr>");
        if (typeBuySummBOS != null && typeBuySummBOS.size() > 0) {
            //拼body部分
            for (TypeBuySummBO bo : typeBuySummBOS) {
                sb.append("<tr>");
                sb.append("<td>" + (StringUtils.isBlank(bo.getType()) ? " ":bo.getType()) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank(bo.getArea()) ? " " : bo.getArea()) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank(bo.getDepartment()) ? " ":bo.getDepartment()) + "</td>");
                sb.append("<td>" + bo.getTotalNum() + "</td>");
                sb.append("<td>" + bo.getTotalAmount() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }

    //地区购买html
    private String htmlAreaBuy(List<TypeBuySummBO> areaBuySummBOS) throws SerException {
        StringBuffer sb = new StringBuffer("");

        sb = new StringBuffer("<h4>针对各部门地区物资购买情况汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>地区</td>");
        sb.append("<td>部门(项目组)</td>");
        sb.append("<td>类型</td>");
        sb.append("<td>总数量（“数量”的合计）</td>");
        sb.append("<td>总金额（“总额”的合计）</td>");
        sb.append("<tr>");
        if (areaBuySummBOS != null && areaBuySummBOS.size() > 0) {
            //拼body部分
            for (TypeBuySummBO bo : areaBuySummBOS) {
                sb.append("<tr>");
                sb.append("<td>" + (StringUtils.isBlank(bo.getArea()) ? " ":bo.getArea()) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank(bo.getDepartment()) ? " " : bo.getDepartment()) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank(bo.getType()) ? " " : bo.getType()) + "</td>");
                sb.append("<td>" + bo.getTotalNum() + "</td>");
                sb.append("<td>" + bo.getTotalAmount() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }

    //个人购买html
    private String htmlPerBuy(List<PersonalBuySummBO> personalBuySummBOS) throws SerException {
        StringBuffer sb = new StringBuffer("");

        sb = new StringBuffer("<h4>个人物资购买情况汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>姓名</td>");
        sb.append("<td>类型</td>");
        sb.append("<td>总数量（“数量”的合计）</td>");
        sb.append("<td>总金额（“总额”的合计）</td>");
        sb.append("<tr>");
        if (personalBuySummBOS != null && personalBuySummBOS.size() > 0) {
            //拼body部分
            for (PersonalBuySummBO bo : personalBuySummBOS) {
                sb.append("<tr>");
                sb.append("<td>" +(StringUtils.isBlank( bo.getName()) ? " ": bo.getName()) + "</td>");
                sb.append("<td>" +(StringUtils.isBlank( bo.getType()) ? " ": bo.getType()) + "</td>");
                sb.append("<td>" + bo.getTotalNum() + "</td>");
                sb.append("<td>" + bo.getTotalAmount() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }

    //入库来源html
    private String htmlSocStock(List<ResouceStockSummBO> resouceStockSummBOS) throws SerException {
        StringBuffer sb = new StringBuffer("");

        sb = new StringBuffer("<h4>整体针对入库来源的物资汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>入库来源</td>");
        sb.append("<td>现入库地区</td>");
        sb.append("<td>部门（项目）</td>");
        sb.append("<td>总数量（“数量”的合计）</td>");
        sb.append("<td>总金额（“总额”的合计）</td>");
        sb.append("<tr>");
        if (resouceStockSummBOS != null && resouceStockSummBOS.size() > 0) {
            //拼body部分
            for (ResouceStockSummBO bo : resouceStockSummBOS) {
                sb.append("<tr>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getSources()) ? " ": bo.getSources()) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getArea()) ? " ": bo.getArea()) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getDepartment()) ? " ": bo.getDepartment()) + "</td>");
                sb.append("<td>" + bo.getTotalNum() + "</td>");
                sb.append("<td>" + bo.getTotalAmount() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }

    //地区入库html
    private String htmlAreaStock(List<AreaStockSummBO> areaStockSummBOS) throws SerException {
        StringBuffer sb = new StringBuffer("");

        sb = new StringBuffer("<h4>各地区入库情况汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>现入库地区</td>");
        sb.append("<td>部门（项目组）</td>");
        sb.append("<td>入库物资状态</td>");
        sb.append("<td>总数量（“数量”的合计）</td>");
        sb.append("<td>总金额（“总额”的合计）</td>");
        sb.append("<tr>");
        if (areaStockSummBOS != null && areaStockSummBOS.size() > 0) {
            //拼body部分
            for (AreaStockSummBO bo : areaStockSummBOS) {
                sb.append("<tr>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getArea()) ? " ": bo.getArea()) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getProjectGroup()) ? " ": bo.getProjectGroup()) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getStockStatus()) ? " ": bo.getStockStatus()) + "</td>");
                sb.append("<td>" + bo.getTotalNum() + "</td>");
                sb.append("<td>" + bo.getTotalAmount() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }

    //维修状态html
    private String htmlDstateStock(List<StatusDeviceSummBO> statusDeviceSummBOS) throws SerException {
        StringBuffer sb = new StringBuffer("");

        sb = new StringBuffer("<h4>针对维修状态分类情况汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>维修状态</td>");
        sb.append("<td>地区</td>");
        sb.append("<td>部门（项目）</td>");
        sb.append("<td>总数量（“数量”的合计）</td>");
        sb.append("<td>总金额（“总额”的合计）</td>");
        sb.append("<tr>");
        if (statusDeviceSummBOS != null && statusDeviceSummBOS.size() > 0) {
            //拼body部分
            for (StatusDeviceSummBO bo : statusDeviceSummBOS) {
                sb.append("<tr>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getDeviceStatus()) ? " ": bo.getDeviceStatus())  + "</td>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getArea()) ? " ": bo.getArea())  + "</td>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getDepartment()) ? " ": bo.getDepartment()) + "</td>");
                sb.append("<td>" + bo.getTotalNum()==null?"0":bo.getTotalNum() + "</td>");
                sb.append("<td>" + bo.getTotalAmount()==null?"0":bo.getTotalAmount() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }

    //保修状态html
    private String htmlWarrStock(List<WarrantyDeviceSummBO> warrantyDeviceSummBOS) throws SerException {
        StringBuffer sb = new StringBuffer("");

        sb = new StringBuffer("<h4>针对保修状态分类情况汇总:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
        //拼表头
        sb.append("<tr>");
        sb.append("<td>是否在保修期</td>");
        sb.append("<td>地区</td>");
        sb.append("<td>部门（项目）</td>");
        sb.append("<td>总数量（“数量”的合计）</td>");
        sb.append("<td>总金额（“总额”的合计）</td>");
        sb.append("<tr>");
        if (warrantyDeviceSummBOS != null && warrantyDeviceSummBOS.size() > 0) {
            //拼body部分
            for (WarrantyDeviceSummBO bo : warrantyDeviceSummBOS) {
                sb.append("<tr>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getWarranty() ) ? " ": bo.getWarranty() ) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getArea() ) ? " ": bo.getArea() ) + "</td>");
                sb.append("<td>" + (StringUtils.isBlank( bo.getDepartment() ) ? " ": bo.getDepartment() ) + "</td>");
                sb.append("<td>" + bo.getTotalNum()==null?"0":bo.getTotalNum() + "</td>");
                sb.append("<td>" + bo.getTotalAmount()==null?"0":bo.getTotalAmount() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }

    private List<SendEmail> sendObject(List<SendEmail> typBuyEmails, List<SendEmail> areaBuyEmails, List<SendEmail> persoBuyEmails, List<SendEmail> resoStockEmails, List<SendEmail> areaStockEmails, List<SendEmail> stateDevEmails, List<SendEmail> warrDevEmails) throws SerException {
        List<SendEmail> allEmails = new ArrayList<>();

        if (typBuyEmails != null && typBuyEmails.size() > 0) {
            for (SendEmail typeBuy : typBuyEmails) {
                CollectUnit type = typeBuy.getCollectUnit();
                String start = changDateTime(type)[0];
                String end = changDateTime(type)[1];
                String startDate = DateUtil.dateToString(LocalDateTime.parse(start).toLocalDate());
                String endDate = DateUtil.dateToString(LocalDateTime.parse(end).toLocalDate());
                String[] dateTime = new String[]{startDate, endDate};
                List<TypeBuySummBO> typBuyList = typeBuySendEmail(dateTime);
                //拼表格
                String content = htmlTyBuy(typBuyList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送各物资分类购买情况汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(typeBuy.getSendObject().split(","));
                messageAPI.send(messageTO);

                typeBuy.setModifyTime(LocalDateTime.now());
                allEmails.add(typeBuy);
            }
        } else if (areaBuyEmails != null && areaBuyEmails.size() > 0) {
            for (SendEmail areaBuy : areaBuyEmails) {
                CollectUnit type = areaBuy.getCollectUnit();
                LocalDate startDate = LocalDate.parse(changDateTime(type)[0]);
                LocalDate endDate = LocalDate.parse(changDateTime(type)[1]);
                String[] dateTime = new String[]{startDate.toString(), endDate.toString()};
                List<TypeBuySummBO> typBuyList = areaBuySendEmail(dateTime);
                //拼表格
                String content = htmlAreaBuy(typBuyList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送各部门地区物资购买情况汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(areaBuy.getSendObject().split(","));
                messageAPI.send(messageTO);

                areaBuy.setModifyTime(LocalDateTime.now());
                allEmails.add(areaBuy);
            }
        }else if (persoBuyEmails != null && persoBuyEmails.size() > 0) {
            for (SendEmail persoBuy : persoBuyEmails) {
                CollectUnit type = persoBuy.getCollectUnit();
                LocalDate startDate = LocalDate.parse(changDateTime(type)[0]);
                LocalDate endDate = LocalDate.parse(changDateTime(type)[1]);
                String[] dateTime = new String[]{startDate.toString(), endDate.toString()};
                List<PersonalBuySummBO> personalBuySummBOList = personBuySendEmail(dateTime);
                //拼表格
                String content = htmlPerBuy(personalBuySummBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送个人物资购买情况汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(persoBuy.getSendObject().split(","));
                messageAPI.send(messageTO);

                persoBuy.setModifyTime(LocalDateTime.now());
                allEmails.add(persoBuy);
            }
        }else if (resoStockEmails != null && resoStockEmails.size() > 0) {
            for (SendEmail resoStock : resoStockEmails) {
                CollectUnit type = resoStock.getCollectUnit();
                LocalDate startDate = LocalDate.parse(changDateTime(type)[0]);
                LocalDate endDate = LocalDate.parse(changDateTime(type)[1]);
                String[] dateTime = new String[]{startDate.toString(), endDate.toString()};
                List<ResouceStockSummBO> resouceStockSummBOList = sourStockSendEmail(dateTime);
                //拼表格
                String content = htmlSocStock(resouceStockSummBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送入库来源的物资汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(resoStock.getSendObject().split(","));
                messageAPI.send(messageTO);

                resoStock.setModifyTime(LocalDateTime.now());
                allEmails.add(resoStock);
            }
        }else if (areaStockEmails != null && areaStockEmails.size() > 0) {
            for (SendEmail areaStock : areaStockEmails) {
                CollectUnit type = areaStock.getCollectUnit();
                LocalDate startDate = LocalDate.parse(changDateTime(type)[0]);
                LocalDate endDate = LocalDate.parse(changDateTime(type)[1]);
                String[] dateTime = new String[]{startDate.toString(), endDate.toString()};
                List<AreaStockSummBO> areaStockSummBOList = areaStockSendEmail(dateTime);
                //拼表格
                String content = htmlAreaStock(areaStockSummBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送地区入库的物资汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(areaStock.getSendObject().split(","));
                messageAPI.send(messageTO);

                areaStock.setModifyTime(LocalDateTime.now());
                allEmails.add(areaStock);
            }
        }else if (stateDevEmails != null && stateDevEmails.size() > 0) {
            for (SendEmail stateDev : stateDevEmails) {
                CollectUnit type = stateDev.getCollectUnit();
                String[] dateTime = changDateTime(type);
                List<StatusDeviceSummBO> statusDeviceSummBOList = statusDeviceSendEmail(dateTime);
                //拼表格
                String content = htmlDstateStock(statusDeviceSummBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送维修状态分类情况汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(stateDev.getSendObject().split(","));
                messageAPI.send(messageTO);

                stateDev.setModifyTime(LocalDateTime.now());
                allEmails.add(stateDev);
            }
        }else if (warrDevEmails != null && warrDevEmails.size() > 0) {
            for (SendEmail warrDev : warrDevEmails) {
                CollectUnit type = warrDev.getCollectUnit();
                String[] dateTime = changDateTime(type);
                List<WarrantyDeviceSummBO> warrantyDeviceSummBOS = warranDeviceSendEmail(dateTime);
                //拼表格
                String content = htmlWarrStock(warrantyDeviceSummBOS);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送保修状态分类情况汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(warrDev.getSendObject().split(","));
                messageAPI.send(messageTO);

                warrDev.setModifyTime(LocalDateTime.now());
                allEmails.add(warrDev);
            }
        }
        return allEmails;
    }
}