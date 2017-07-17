package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.CollectData;
import com.bjike.goddess.businessinteraction.bo.CollectEmailBO;
import com.bjike.goddess.businessinteraction.dto.CollectEmailDTO;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.CollectEmail;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.businessinteraction.enums.CustomerSendUnit;
import com.bjike.goddess.businessinteraction.enums.GuideAddrStatus;
import com.bjike.goddess.businessinteraction.to.CollectEmailTO;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 邮件发送定制业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class CollectEmailSerImpl extends ServiceImpl<CollectEmail, CollectEmailDTO> implements CollectEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private InteractionRelationSer interactionRelationSer;
    @Autowired
    private LeavingMessageSer leavingMessageSer;
    @Autowired
    private DemandSer demandSer;
    @Autowired
    private TalkDetailSer talkDetailSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;
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
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
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

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
        return flag;
    }

    @Override
    public Long countInter(CollectEmailDTO collectEmailDTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        RpcTransmit.transmitUserToken(userToken);
//        if (!permissionLevel) {
//            throw new SerException("您的帐号没有权限");
//        }
        checkSeeIdentity();
        Long count = super.count(collectEmailDTO);
        return count;
    }

    @Override
    public CollectEmailBO getOneById(String id) throws SerException {


        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CollectEmail collectEmail = super.findById(id);

        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Cacheable
    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        RpcTransmit.transmitUserToken(userToken);
//        if (!permissionLevel) {
//            throw new SerException("您的帐号没有权限");
//        }
        checkSeeIdentity();
        collectEmailDTO.getSorts().add("createTime=desc");
        List<CollectEmail> list = super.findByCis(collectEmailDTO, true);
        return BeanTransform.copyProperties(list, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if ( !permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行添加操作");
//        }
//        RpcTransmit.transmitUserToken(userToken);
        checkAddIdentity();
        if (collectEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }
        if (null == collectEmailTO.getCustomerSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        if (collectEmailTO.getSendNum() < 30 && collectEmailTO.getCustomerSendUnit().equals(CustomerSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (collectEmailTO.getSendNum() > collectEmailTO.getSendNum().longValue() && collectEmailTO.getSendNum() < (collectEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }


        List<String> works = Arrays.asList(collectEmailTO.getWorks());
        //设置行业
        StringBuffer workBuf = new StringBuffer("");
        if (works != null && works.size() > 0) {
            for (String work : works) {
                workBuf.append(work.trim() + ";");
            }
        }


        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if(!Validator.isEmail( emailStr )){
                    throw new SerException("邮箱输入不正确");
                }
                emails.append(emailStr + ";");
            }
        }


        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        collectEmail.setCreateTime(LocalDateTime.now());
        collectEmail.setStatus(Status.THAW);
        collectEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCustomerSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置发送对象
        collectEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        collectEmail.setLastSendTime(LocalDateTime.now());
        //设置行业
        collectEmail.setWork(String.valueOf(workBuf));

        super.save(collectEmail);

        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        //商务模块添加权限
////        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
////        if (!permissionLevel) {
////            throw new SerException("您不是相应的人员，不可以进行编辑操作");
////        }
//        RpcTransmit.transmitUserToken(userToken);
        checkAddIdentity();
        if (collectEmailTO.getSendNum() < 0) {
            throw new SerException("发送间隔不能小于0");
        }
        if (null == collectEmailTO.getCustomerSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        if (collectEmailTO.getSendNum() < 30 && collectEmailTO.getCustomerSendUnit().equals(CustomerSendUnit.MINUTE)) {
            throw new SerException("发送间隔单位为分钟的间隔数不能小于30分钟");
        }

        if (collectEmailTO.getSendNum() > collectEmailTO.getSendNum().longValue() && collectEmailTO.getSendNum() < (collectEmailTO.getSendNum().longValue() + 1)) {
            throw new SerException("发送间隔不能为小数");
        }


        List<String> works = Arrays.asList(collectEmailTO.getWorks());
        //设置行业
        StringBuffer workBuf = new StringBuffer("");
        if (works != null && works.size() > 0) {
            for (String work : works) {
                workBuf.append(work.trim() + ";");
            }
        }

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if(!Validator.isEmail( emailStr )){
                    throw new SerException("邮箱输入不正确");
                }
                emails.append(emailStr + ";");
            }
        }

        //先查出来
        CollectEmail getEmail = super.findById(collectEmailTO.getId());
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        BeanUtils.copyProperties(collectEmail, getEmail, "sendNumAndUnit",
                "sendObject", "work", "createTime", "id", "status", "lastSendTime");
        getEmail.setModifyTime(LocalDateTime.now());
        getEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCustomerSendUnit().getCode());
        getEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置发送对象
        getEmail.setSendObject(String.valueOf(emails));
        //设置行业
        getEmail.setWork(String.valueOf(workBuf));

        super.update(getEmail);
        return BeanTransform.copyProperties(getEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCollectEmail(String id) throws SerException {
        //商务模块编辑权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if (!permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行删除操作");
//        }
        checkAddIdentity();
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCollectEmail(String id) throws SerException {
        //商务模块冻结权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if (!permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行删除操作");
//        }

        checkAddIdentity();
        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.CONGEAL);
        super.update(collectEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCollectEmail(String id) throws SerException {
//        //商务模块解冻权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if (!permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行删除操作");
//        }
        checkAddIdentity();
        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.THAW);
        super.update(collectEmail);
    }


    @Override
    public List<CollectEmailBO> collectCollectEmail(String[] works) throws SerException {
        if (works == null || works.length <= 0) {
            throw new SerException("汇总失败，请选择地区");
        }
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<CollectData> collectDataList = new ArrayList<>();
        List<String> areas = Arrays.asList(works);
        StringBuffer sb = new StringBuffer("");
        for (String str : areas) {
            collectDataList = new ArrayList<>();
            sb.append(str.replaceAll(str, "'" + str + "'")).append(",");

            String[] fields = new String[]{"counts", "remark"};
            //互动联系信息数量
            InteractionRelationDTO interDTO = new InteractionRelationDTO();
            interDTO.getConditions().add(Restrict.eq("area", str));
            Long interCount = interactionRelationSer.count(interDTO);
            CollectData collectData = new CollectData();
            collectData.setName("互动联系信息数量");
            collectData.setCounts(interCount != null ? interCount : 0);
            collectDataList.add(collectData);

            //留言数量
            String sql = "select count(*) as counts , '留言数量' as remark from businessinteraction_leavingmessage  bl " +
                    "  INNER JOIN  businessinteraction_interactionrelation bi  on bl.interactionRelation_id = bi.id where bi.area = '" + str + "'  ";
            List<CollectEmailBO> collectEmailBOS = leavingMessageSer.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("留言数量");
            collectData.setCounts(collectEmailBOS != null && collectEmailBOS.size() > 0
                    ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())) : 0);
            collectDataList.add(collectData);

            //需求记录数量
            DemandDTO demandDTO = new DemandDTO();
            demandDTO.getConditions().add(Restrict.eq("area", str));
            Long demandCount = demandSer.count(demandDTO);
            collectData = new CollectData();
            collectData.setName("需求记录数量");
            collectData.setCounts(demandCount != null ? demandCount : 0);
            collectDataList.add(collectData);

            //直接合作方数量
            TalkDetailDTO talkDetailDTO = new TalkDetailDTO();
            talkDetailDTO.getConditions().add(Restrict.eq("area", str));
            talkDetailDTO.getConditions().add(Restrict.eq("cooperWay", "直接合作"));
            Long talkCount = talkDetailSer.count(talkDetailDTO);
            collectData = new CollectData();
            collectData.setName("直接合作方数量");
            collectData.setCounts(talkCount != null ? talkCount : 0);
            collectDataList.add(collectData);

            //中介合作方数量
            talkDetailDTO = new TalkDetailDTO();
            talkDetailDTO.getConditions().add(Restrict.eq("area", str));
            talkDetailDTO.getConditions().add(Restrict.eq("cooperWay", "中介"));
            talkCount = talkDetailSer.count(talkDetailDTO);
            collectData = new CollectData();
            collectData.setName("中介合作方数量");
            collectData.setCounts(talkCount != null ? talkCount : 0);
            collectDataList.add(collectData);

            //达成合作数量
            talkDetailDTO = new TalkDetailDTO();
            talkDetailDTO.getConditions().add(Restrict.eq("area", str));
            talkDetailDTO.getConditions().add(Restrict.eq("cooperCondition", "已达成"));
            talkCount = talkDetailSer.count(talkDetailDTO);
            collectData = new CollectData();
            collectData.setName("达成合作数量");
            collectData.setCounts(talkCount != null ? talkCount : 0);
            collectDataList.add(collectData);

            //未达成合作数量
            talkDetailDTO = new TalkDetailDTO();
            talkDetailDTO.getConditions().add(Restrict.eq("area", str));
            talkDetailDTO.getConditions().add(Restrict.eq("cooperCondition", "未达成"));
            talkCount = talkDetailSer.count(talkDetailDTO);
            collectData = new CollectData();
            collectData.setName("未达成合作数量");
            collectData.setCounts(talkCount != null ? talkCount : 0);
            collectDataList.add(collectData);

            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setRemark(str);
            cbo.setCollectDataList(collectDataList);
            collectEmailBOList.add(cbo);
        }

        //合计
        collectDataList = new ArrayList<>();
        calculateTotal(sb.substring(0, sb.lastIndexOf(",")), collectDataList, collectEmailBOList);
        return collectEmailBOList;
    }

    public void calculateTotal(String area, List<CollectData> collectDataList, List<CollectEmailBO> collectEmailBOList) throws SerException {
        String[] fields = new String[]{"counts", "remark"};
        //互动联系信息数量
        String sql = " select count(*) as counts , '互动联系信息数量' as remark from businessinteraction_interactionrelation where area in(" + area + ")";
        List<CollectEmailBO> collectEmailBOS = interactionRelationSer.findBySql(sql, CollectEmailBO.class, fields);
        CollectData collectData = new CollectData();
        collectData.setName("互动联系信息数量");
        collectData.setCounts(collectEmailBOS != null && collectEmailBOS.size() > 0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())) : 0);
        collectDataList.add(collectData);


        //留言数量
        sql = " select count(*) as counts , '留言数量' as remark  " +
                " from businessinteraction_leavingmessage  bl " +
                " INNER JOIN  businessinteraction_interactionrelation bi  on bl.interactionRelation_id = bi.id where bi.area in ( " + area + ")";
        collectEmailBOS = leavingMessageSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("留言数量");
        collectData.setCounts(collectEmailBOS != null && collectEmailBOS.size() > 0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())) : 0);
        collectDataList.add(collectData);

        //需求记录数量
        sql = " select count(*) as counts , '需求记录数量' as remark from businessinteraction_demand where area in(" + area + ")";
        collectEmailBOS = demandSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("需求记录数量");
        collectData.setCounts(collectEmailBOS != null && collectEmailBOS.size() > 0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())) : 0);
        collectDataList.add(collectData);

        //直接合作方数量
        sql = " select count(*) as counts , '直接合作方数量' as remark from businessinteraction_talkdetail where cooperWay ='直接合作' and area in(" + area + ")";
        collectEmailBOS = talkDetailSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("直接合作方数量");
        collectData.setCounts(collectEmailBOS != null && collectEmailBOS.size() > 0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())) : 0);
        collectDataList.add(collectData);

        //中介合作方数量
        sql = " select count(*) as counts , '中介合作方数量' as remark from businessinteraction_talkdetail where cooperWay = '中介' and  area in(" + area + ")";
        collectEmailBOS = talkDetailSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("中介合作方数量");
        collectData.setCounts(collectEmailBOS != null && collectEmailBOS.size() > 0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())) : 0);
        collectDataList.add(collectData);

        //达成合作数量
        sql = " select count(*) as counts , '达成合作数量' as remark from businessinteraction_talkdetail where cooperCondition = '已达成' and  area in(" + area + ")";
        collectEmailBOS = talkDetailSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("达成合作数量");
        collectData.setCounts(collectEmailBOS != null && collectEmailBOS.size() > 0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())) : 0);
        collectDataList.add(collectData);

        //未达成合作数量
        sql = " select count(*) as counts , '未达成合作数量' as remark from businessinteraction_talkdetail where cooperCondition = '未达成' and  area in(" + area + ")";
        collectEmailBOS = talkDetailSer.findBySql(sql, CollectEmailBO.class, fields);
        collectData = new CollectData();
        collectData.setName("未达成合作数量");
        collectData.setCounts(collectEmailBOS != null && collectEmailBOS.size() > 0
                ? Long.parseLong(String.valueOf(collectEmailBOS.get(0).getCounts())) : 0);
        collectDataList.add(collectData);

        CollectEmailBO cbo = new CollectEmailBO();
        cbo.setRemark("合计");
        cbo.setCollectDataList(collectDataList);
        collectEmailBOList.add(cbo);
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
    public List<String> areaList() throws SerException {
        String[] field = new String[]{"area"};
        String sql = "select area from businessinteraction_interactionrelation group by area";

        List<InteractionRelation> list = interactionRelationSer.findBySql(sql, InteractionRelation.class, field);
        List<String> areaList = list.stream().map(InteractionRelation::getArea).collect(Collectors.toList());
        return areaList;
    }

    private String html(List<CollectEmailBO> collectEmailBOs) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (collectEmailBOs != null && collectEmailBOs.size() > 0) {
            sb = new StringBuffer("<h4>汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
//            CollectEmailBO title = dispatchEmails.get(dispatchEmails.size() - 1);
            sb.append("<tr>");
            sb.append("<td>地区</td>");
            sb.append("<td>互动联系信息数量</td>");
            sb.append("<td>留言数量</td>");
            sb.append("<td>需求记录数量</td>");
            sb.append("<td>直接合作方数量</td>");
            sb.append("<td>中介合作方数量</td>");
            sb.append("<td>达成合作数量</td>");
            sb.append("<td>未达成合作数量</td>");
            sb.append("</tr>");

            //拼body部分
            for (CollectEmailBO bo : collectEmailBOs) {
                List<CollectData> collectDatas = bo.getCollectDataList();
                sb.append("<tr>");
                sb.append("<td>" + bo.getRemark() + "</td>");
                if ((collectDatas != null) && (!collectDatas.isEmpty())) {
                    for (CollectData collectData : collectDatas) {
                        if ("互动联系信息数量".equals(collectData.getName())) {
                            sb.append("<td>" + collectData.getCounts() + "</td>");
                        }
                        if ("留言数量".equals(collectData.getName())) {
                            sb.append("<td>" + collectData.getCounts() + "</td>");
                        }
                        if ("需求记录数量".equals(collectData.getName())) {
                            sb.append("<td>" + collectData.getCounts() + "</td>");
                        }
                        if ("直接合作方数量".equals(collectData.getName())) {
                            sb.append("<td>" + collectData.getCounts() + "</td>");
                        }
                        if ("中介合作方数量".equals(collectData.getName())) {
                            sb.append("<td>" + collectData.getCounts() + "</td>");
                        }
                        if ("达成合作数量".equals(collectData.getName())) {
                            sb.append("<td>" + collectData.getCounts() + "</td>");
                        }
                        if ("未达成合作数量".equals(collectData.getName())) {
                            sb.append("<td>" + collectData.getCounts() + "</td>");
                        }
                    }
                }
                sb.append("</tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }

    @Override
    public void checkSendEmail() throws SerException {
        List<CollectEmail> allEmails = new ArrayList<>();

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
            CustomerSendUnit collectSendUnit = str.getCustomerSendUnit();
            //发送对象;隔开
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
                        str.setLastSendTime(lastTime.plusMinutes( sendNum.longValue() ));
                    }
                    break;
                case HOURS:
                    temp_sendNum = sendNum * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusHours( sendNum.longValue() ));
                    }
                    break;
                case DAY:
                    temp_sendNum = sendNum * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusDays( sendNum.longValue() ));
                    }
                    break;
                case WEEK:
                    temp_sendNum = sendNum * 7 * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusWeeks( sendNum.longValue() ));
                    }
                    break;
                case MONTH:
                    if (nowTime.minusMonths(sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusMonths( sendNum.longValue() ));
                    }
                    break;
                case QUARTER:
                    if (nowTime.minusMonths(3*sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(3*sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusMonths( 3* sendNum.longValue() ));
                    }
                    break;
                case YEAR:
                    if (nowTime.minusYears(sendNum.longValue()).isEqual(lastTime) || nowTime.minusYears(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastSendTime(lastTime.plusYears( sendNum.longValue() ));
                    }
                    break;
            }
            if (flag) {
                allEmails.add(str);
            }
        }

        //调用发邮件
        allEmails = sendObject(allEmails);

        //修改上次发送时间
        super.update(allEmails);

    }

    private List<CollectEmail> sendObject(List<CollectEmail> emails) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(userToken);
        List<CollectEmail> allEmails = new ArrayList<>();
        if (emails != null && emails.size() > 0) {
            for (CollectEmail email : emails) {
                String work = email.getWork();
                String[] works = work.split(";");
                List<CollectEmailBO> collectEmailBOs = collectCollectEmail(works);
                //拼表格
                String content = html(collectEmailBOs);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(email.getSendObject().split(";"));
                messageAPI.send(messageTO);

                email.setModifyTime(LocalDateTime.now());
                allEmails.add(email);
            }
        }
        return allEmails;

    }
}