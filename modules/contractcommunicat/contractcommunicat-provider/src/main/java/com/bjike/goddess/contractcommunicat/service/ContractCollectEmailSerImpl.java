package com.bjike.goddess.contractcommunicat.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.contractcommunicat.bo.*;
import com.bjike.goddess.contractcommunicat.dto.CollectEmailDTO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.entity.CollectEmail;
import com.bjike.goddess.contractcommunicat.entity.ProjectContract;
import com.bjike.goddess.contractcommunicat.entity.ProjectOutsourcing;
import com.bjike.goddess.contractcommunicat.enums.CollectSendUnit;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.GuideAddrStatus;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.CollectEmailTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

/**
 * 商务项目合同邮件发送定制业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 商务项目合同邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class ContractCollectEmailSerImpl extends ServiceImpl<CollectEmail, CollectEmailDTO> implements ContractCollectEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProjectContractSer projectContractSer;
    @Autowired
    private ProjectOutsourcingSer projectOutsourcingSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ContractCollectEmailSer ContractCollectEmailSer;

    @Autowired
    private MessageAPI messageAPI;

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
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
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
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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
    public Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        Long count = super.count(collectEmailDTO);
        return count;
    }

    @Override
    public CollectEmailBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        CollectEmail selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability, CollectEmailBO.class);

    }

    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        checkSeeIdentity();

        collectEmailDTO.getSorts().add("createTime=desc");
        List<CollectEmail> list = super.findByPage(collectEmailDTO);
        return BeanTransform.copyProperties(list, CollectEmailBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        String useToken = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(useToken);

        if (collectEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }
        if (collectEmailTO.getSendNum() < 30 && collectEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (collectEmailTO.getSendNum() > collectEmailTO.getSendNum().longValue() && collectEmailTO.getSendNum() < (collectEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ";");
            }
        }
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        collectEmail.setCreateTime(LocalDateTime.now());
        collectEmail.setStatus(Status.THAW);
        collectEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        if (null == collectEmail.getCollectSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置汇总条件
//        StringBuffer condiSb = new StringBuffer("");
//        String[] condis = collectEmailTO.getCondis();
//        if (condis != null && condis.length >= 0) {
//            for (String condiStr : condis) {
//                condiSb.append(condiStr + ";");
//            }
//            collectEmail.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
//        } else {
//            throw new SerException("发送条件不能为空");
//        }

        //设置发送对象
        collectEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        collectEmail.setLastSendTime(LocalDateTime.now());


        super.save(collectEmail);

        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        String useToken = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(useToken);

        if (collectEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }

        if (collectEmailTO.getSendNum() < 30 && collectEmailTO.getCollectSendUnit().equals(CollectSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (collectEmailTO.getSendNum() > collectEmailTO.getSendNum().longValue() && collectEmailTO.getSendNum() < (collectEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ";");
            }
        }
        CollectEmail temp = super.findById(collectEmailTO.getId());
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);

        BeanUtils.copyProperties(collectEmail, temp, "id", "createTime", "createPersion", "lastSendTime", "status");
        temp.setModifyTime(LocalDateTime.now());
        temp.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        temp.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置汇总条件
//        StringBuffer condiSb = new StringBuffer("");
//        String[] condis = collectEmailTO.getCondis();
//        if (condis != null && condis.length >= 0) {
//            for (String condiStr : condis) {
//                condiSb.append(condiStr + ";");
//            }
//            temp.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
//        } else {
//            throw new SerException("发送条件不能为空");
//        }


        //设置发送对象
        temp.setSendObject(String.valueOf(emails));

        super.update(temp);
        return BeanTransform.copyProperties(temp, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCollectEmail(String id) throws SerException {

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCollectEmail(String id) throws SerException {

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.CONGEAL);
        super.update(collectEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCollectEmail(String id) throws SerException {

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.THAW);
        super.update(collectEmail);
    }

    /**
     * 汇总项目承包商务洽谈
     *
     * @param to to
     * @return
     * @throws SerException
     */
    @Override
    public List<ProjectContractCollectBO> gatherPb(CollectEmail to) throws SerException {
//        getCusPermission();
        ProjectContractDTO dto = new ProjectContractDTO();
        dto.getSorts().add("createTime=desc");
        if (!StringUtils.isEmpty(to.getType())) {
            dto.getConditions().add(Restrict.like("contractInProject", to.getType()));
        }
        if (to.getCollectUnit() == QuartzCycleType.YEAR) {
            String startTime = LocalDateTime.now().getYear() + "-01-01 00:00:00";
            LocalDateTime sta = DateUtil.parseDateTime(startTime);
            LocalDateTime[] interval = new LocalDateTime[]{sta, LocalDateTime.now()};
            dto.getConditions().add(Restrict.between("createTime", interval));
        } else if (to.getCollectUnit() == QuartzCycleType.QUARTER) {
            LocalDateTime start = DateUtil.getStartQuart();
            LocalDateTime end = DateUtil.getEndQuart();
            LocalDateTime[] time = new LocalDateTime[]{start,end};
            dto.getConditions().add(Restrict.between("createTime", time));
        } else if (to.getCollectUnit() == QuartzCycleType.MONTH) {
            String startTime = LocalDate.now().getYear() + "-" + LocalDate.now().getMonth() + "-01";
            String endTime = LocalDate.now().getYear() + "-" + LocalDate.now().getMonth() + "-" + LocalDate.now().getDayOfMonth();
            LocalDate sta2 = DateUtil.parseDate(endTime);
            LocalDate sta = DateUtil.parseDate(startTime);
            LocalDate[] interval = new LocalDate[]{sta, sta2};
            dto.getConditions().add(Restrict.between("createTime", interval));
        } else if (to.getCollectUnit() == QuartzCycleType.WEEK) {
            LocalDate startTime = DateUtil.getStartWeek();
            LocalDate endTime = LocalDate.now();
            LocalDate[] interval = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("createTime", interval));
        } else if (to.getCollectUnit() == QuartzCycleType.DAY) {
            dto.getConditions().add(Restrict.eq("createTime", LocalDate.now()));
        }
        return setCollectFieldPc(projectContractSer.findByCis(dto));
    }

    //设置汇总项目承包商务洽谈字段
    public List<ProjectContractCollectBO> setCollectFieldPc(List<ProjectContract> list) throws SerException {
        List<ProjectContractBO> boList = BeanTransform.copyProperties(list, ProjectContractBO.class);

        List<ProjectContractCollectBO> returnBoList = BeanTransform.copyProperties(list, ProjectContractCollectBO.class);
        Integer totalCooperate = 0;
        Integer totalTrail = 0;
        Integer totalAbandon = 0;
        Double totalCostBudget = 0.0;
        if (returnBoList != null && !returnBoList.isEmpty()) {
            for (ProjectContractCollectBO bo : returnBoList) {
                if (bo.getProjectResult() == CommunicateResult.COOPERATE) {
                    bo.setCooperate("项目合作");
                    totalCooperate++;
                } else if (bo.getProjectResult() == CommunicateResult.TRAIL) {
                    bo.setTrail("项目跟进");
                    totalTrail++;
                } else if (bo.getProjectResult() == CommunicateResult.ABANDON) {
                    bo.setAbandon("项目丢弃");
                    totalAbandon++;
                }

            }
            totalCostBudget = returnBoList.stream().mapToDouble(p -> p.getCostBudget()).sum();
        } else {
            returnBoList = new ArrayList<ProjectContractCollectBO>();
        }

        ProjectContractCollectBO total = new ProjectContractCollectBO("合计", null, null, null, null,
                totalCostBudget, totalCooperate.toString(), totalTrail.toString(), totalAbandon.toString());
        returnBoList.add(total);

        return returnBoList;
    }


    public void getCusPermission() throws SerException {

        Boolean permission = cusPermissionSer.getCusPermission("1");

        if (!permission) {
            throw new SerException("该模块只有商务部可操作，您的帐号尚无权限");
        }
    }

    //汇总项目外包商务洽谈
    @Override
    public List<ProjectOutsourcingCollectBO> gatherPc(CollectEmail to) throws SerException {
//        getCusPermission();
        ProjectOutsourcingDTO dto = new ProjectOutsourcingDTO();
        dto.getSorts().add("createTime=desc");
        if (!StringUtils.isEmpty(to.getType())) {
            dto.getConditions().add(Restrict.like("contractInProject", to.getType()));
        }
        if (to.getCollectUnit() == QuartzCycleType.YEAR) {
            String startTime = LocalDateTime.now().getYear() + "-01-01";
            LocalDate sta = DateUtil.parseDate(startTime);
            LocalDate[] interval = new LocalDate[]{sta, LocalDate.now()};
            dto.getConditions().add(Restrict.between("createTime", interval));
        } else if (to.getCollectUnit() == QuartzCycleType.QUARTER) {
            LocalDateTime start = DateUtil.getStartQuart();
            LocalDateTime end = DateUtil.getEndQuart();
            LocalDateTime[] time = new LocalDateTime[]{start,end};
            dto.getConditions().add(Restrict.between("createTime", time));
        } else if (to.getCollectUnit() == QuartzCycleType.MONTH) {
            String startTime = LocalDate.now().getYear() + "-" + LocalDate.now().getMonth() + "-01";
            String endTime = LocalDate.now().getYear() + "-" + LocalDate.now().getMonth() + "-" + LocalDate.now().getDayOfMonth();
            LocalDate sta2 = DateUtil.parseDate(endTime);
            LocalDate sta = DateUtil.parseDate(startTime);
            LocalDate[] interval = new LocalDate[]{sta, sta2};
            dto.getConditions().add(Restrict.between("createTime", interval));
        } else if (to.getCollectUnit() == QuartzCycleType.WEEK) {
            LocalDate startTime = DateUtil.getStartWeek();
            LocalDate endTime = LocalDate.now();
            LocalDate[] interval = new LocalDate[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("interval", interval));
        } else if (to.getCollectUnit() == QuartzCycleType.DAY) {
            dto.getConditions().add(Restrict.eq("createTime", LocalDate.now()));
        }
        return setCollectField(projectOutsourcingSer.findByCis(dto));
    }

    //设置汇总项目外包商务洽谈字段
    public List<ProjectOutsourcingCollectBO> setCollectField(List<ProjectOutsourcing> list) throws SerException {

        List<ProjectOutsourcingBO> boList = BeanTransform.copyProperties(list, ProjectOutsourcingBO.class);

        List<ProjectOutsourcingCollectBO> returnBoList = BeanTransform.copyProperties(list, ProjectOutsourcingCollectBO.class);
        Integer totalCooperate = 0;
        Integer totalTrail = 0;
        Integer totalAbandon = 0;
        Double totalCostBudget = 0.0;
        if (returnBoList != null && !returnBoList.isEmpty()) {
            for (ProjectOutsourcingCollectBO bo : returnBoList) {
                if (bo.getProjectResult() == CommunicateResult.COOPERATE) {
                    bo.setCooperate("项目合作");
                    totalCooperate++;
                } else if (bo.getProjectResult() == CommunicateResult.TRAIL) {
                    bo.setTrail("项目跟进");
                    totalTrail++;
                } else if (bo.getProjectResult() == CommunicateResult.ABANDON) {
                    bo.setAbandon("项目丢弃");
                    totalAbandon++;
                }
            }
            totalCostBudget = boList.stream().mapToDouble(p -> p.getCostBudget()).sum();
        } else {
            returnBoList = new ArrayList<ProjectOutsourcingCollectBO>();
        }

        ProjectOutsourcingCollectBO total = new ProjectOutsourcingCollectBO("合计", null, null, null, null, null,
                totalCostBudget, totalCooperate.toString(), totalTrail.toString(), totalAbandon.toString());
        returnBoList.add(total);

        return returnBoList;
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
    public void checkSendEmail() throws SerException {
        List<CollectEmail> allEmails = new ArrayList<>();
        List<CollectEmail> signEmails = new ArrayList<>();
        List<CollectEmail> dispatchEmails = new ArrayList<>();

        //检测有哪些需要发送的
        //上次发送时间
        //现在时间
        //发送间隔
        //发送单位
        //发送类型
        //发送对象
        CollectEmailDTO dto = new CollectEmailDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<CollectEmail> list = super.findByCis(dto);
        LocalDateTime nowTime = LocalDateTime.now();
        for (CollectEmail str : list) {
            //上次发送时间
            LocalDateTime lastTime = str.getLastSendTime();
            //发送间隔
            Double sendNum = str.getSendNum();
            //发送单位
            CollectSendUnit collectSendUnit = str.getCollectSendUnit();
            //发送类型
            String type = str.getType();
            //发送对象;隔开
            String sendObject = str.getSendObject();
            Boolean flagSend = false;

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
                        str.setLastSendTime(nowTime.plusMinutes(sendNum.longValue()));
                    }
                    break;
                case HOURS:
                    temp_sendNum = sendNum * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusHours(sendNum.longValue()));
                    }
                    break;
                case DAY:
                    temp_sendNum = sendNum * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusDays(sendNum.longValue()));
                    }
                    break;
                case WEEK:
                    temp_sendNum = sendNum * 7 * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusWeeks(sendNum.longValue()));
                    }
                    break;
                case MONTH:
                    if (nowTime.minusMonths(sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusMonths(sendNum.longValue()));
                    }
                    break;
                case QUARTER:
                    if (nowTime.minusMonths(3 * sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(3 * sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusMonths(3 * sendNum.longValue()));
                    }
                    break;
                case YEAR:
                    if (nowTime.minusYears(sendNum.longValue()).isEqual(lastTime) || nowTime.minusYears(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusYears(sendNum.longValue()));
                    }
                    break;
            }

            if (flag && type.equals("项目承包商务洽谈")) {
                signEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals("项目外包商务洽谈")) {
                dispatchEmails.add(str);
                allEmails.add(str);
            }
        }

        //调用发邮件
        allEmails = sendObject(signEmails, dispatchEmails);

        //修改上次发送时间
        super.update(allEmails);

    }


    private String htmlBaseInfo(List<ProjectContractCollectBO> baseinfoBOList) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (baseinfoBOList != null && baseinfoBOList.size() > 0) {
            sb = new StringBuffer("<h4>项目承包商务洽谈汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            ProjectContractCollectBO title = baseinfoBOList.get(baseinfoBOList.size() - 1);
            sb.append("<tr>");
            sb.append("<td>合同外部项目名称</td>");
            sb.append("<td>内部项目名称(元)</td>");
            sb.append("<td>洽谈轮次</td>");
            sb.append("<td>洽谈对象</td>");
            sb.append("<td>费用预算</td>");
            sb.append("<td>项目合作</td>");
            sb.append("<td>项目跟进</td>");
            sb.append("<td>项目丢弃</td>");
            sb.append("<tr>");
            for(int i = 0;i <= baseinfoBOList.size()-2;i++){
                if(baseinfoBOList.get(i).getCooperate() != null){
                    baseinfoBOList.get(i).setTrail(" ");
                    baseinfoBOList.get(i).setAbandon(" ");
                }else if(baseinfoBOList.get(i).getTrail() != null){
                    baseinfoBOList.get(i).setCooperate(" ");
                    baseinfoBOList.get(i).setAbandon(" ");
                }else if(baseinfoBOList.get(i).getAbandon() == null){
                    baseinfoBOList.get(i).setTrail(" ");
                    baseinfoBOList.get(i).setCooperate(" ");
                }
            }
            baseinfoBOList.get(baseinfoBOList.size()-1).setContractInProject(" ");
            baseinfoBOList.get(baseinfoBOList.size()-1).setCommunicateUser(" ");
            baseinfoBOList.get(baseinfoBOList.size()-1).setCommunicateTimes(" ");

            //拼body部分
            for (ProjectContractCollectBO bo : baseinfoBOList) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getContractExtProject() + "</td>");
                sb.append("<td>" + bo.getContractInProject() + "</td>");
                sb.append("<td>" + bo.getCommunicateTimes() + "</td>");
                sb.append("<td>" + bo.getCommunicateUser() + "</td>");
                sb.append("<td>" + bo.getCostBudget() + "</td>");
                sb.append("<td>" + bo.getCooperate() + "</td>");
                sb.append("<td>" + bo.getTrail() + "</td>");
                sb.append("<td>" + bo.getAbandon() + "</td>");
                sb.append("<tr>");
            }
            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private String htmlDispatch(List<ProjectOutsourcingCollectBO> dispatchEmails) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (dispatchEmails != null && dispatchEmails.size() > 0) {
            sb = new StringBuffer("<h4>项目外包商务洽谈汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            ProjectOutsourcingCollectBO title = dispatchEmails.get(dispatchEmails.size() - 1);
            sb.append("<tr>");
            sb.append("<td>合同外部项目名称</td>");
            sb.append("<td>内部项目名称(元)</td>");
            sb.append("<td>外包项目名称(元)</td>");
            sb.append("<td>洽谈轮次</td>");
            sb.append("<td>洽谈对象</td>");
            sb.append("<td>费用预算</td>");
            sb.append("<td>项目合作</td>");
            sb.append("<td>项目跟进</td>");
            sb.append("<td>项目丢弃</td>");
            sb.append("<tr>");
            for(int i = 0;i <= dispatchEmails.size()-2;i++){
                if(dispatchEmails.get(i).getCooperate() != null){
                    dispatchEmails.get(i).setTrail(" ");
                    dispatchEmails.get(i).setAbandon(" ");
                }else if(dispatchEmails.get(i).getTrail() != null){
                    dispatchEmails.get(i).setCooperate(" ");
                    dispatchEmails.get(i).setAbandon(" ");
                }else if(dispatchEmails.get(i).getAbandon() == null){
                    dispatchEmails.get(i).setTrail(" ");
                    dispatchEmails.get(i).setCooperate(" ");
                }
            }
            dispatchEmails.get(dispatchEmails.size()-1).setContractInProject(" ");
            dispatchEmails.get(dispatchEmails.size()-1).setCommunicateUser(" ");
            dispatchEmails.get(dispatchEmails.size()-1).setCommunicateTimes(" ");
            dispatchEmails.get(dispatchEmails.size()-1).setOutsourcingProject(" ");

            //拼body部分
            for (ProjectOutsourcingCollectBO bo : dispatchEmails) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getContractExtProject() + "</td>");
                sb.append("<td>" + bo.getContractInProject() + "</td>");
                sb.append("<td>" + bo.getOutsourcingProject() + "</td>");
                sb.append("<td>" + bo.getCommunicateTimes() + "</td>");
                sb.append("<td>" + bo.getCommunicateUser() + "</td>");
                sb.append("<td>" + bo.getCostBudget() + "</td>");
                sb.append("<td>" + bo.getCooperate() + "</td>");
                sb.append("<td>" + bo.getTrail() + "</td>");
                sb.append("<td>" + bo.getAbandon() + "</td>");
                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private List<CollectEmail> sendObject(List<CollectEmail> signEmails,List<CollectEmail> dispatchEmails) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<CollectEmail> allEmails = new ArrayList<>();
        //项目承包商务洽谈汇总
        //项目外包商务洽谈汇总
        if (signEmails != null && signEmails.size() > 0) {
            for (CollectEmail sign : signEmails) {
//                String[] condis = sign.getCondi().split(";");
                List<ProjectContractCollectBO> signBOList = ContractCollectEmailSer.gatherPb(sign);
                //拼表格
                String content = htmlBaseInfo(signBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送项目承包商务洽谈");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(sign.getSendObject().split(";"));
                messageAPI.send(messageTO);

                sign.setModifyTime(LocalDateTime.now());
                allEmails.add(sign);
            }

        }

        RpcTransmit.transmitUserToken(userToken);
        if (dispatchEmails != null && dispatchEmails.size() > 0) {
            for (CollectEmail dispa : dispatchEmails) {
                List<ProjectOutsourcingCollectBO> dispatchBOList = ContractCollectEmailSer.gatherPc(dispa);
                //拼表格
                String content = htmlDispatch(dispatchBOList);
                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送项目外包商务洽谈");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(dispa.getSendObject().split(";"));
                messageAPI.send(messageTO);

                dispa.setModifyTime(LocalDateTime.now());
                allEmails.add(dispa);
            }
        }
        return allEmails;

    }

}