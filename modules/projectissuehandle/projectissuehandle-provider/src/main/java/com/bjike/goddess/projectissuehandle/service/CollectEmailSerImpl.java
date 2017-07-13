package com.bjike.goddess.projectissuehandle.service;

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
import com.bjike.goddess.projectissuehandle.bo.CollectBO;
import com.bjike.goddess.projectissuehandle.bo.CollectEmailBO;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.CollectEmailDTO;
import com.bjike.goddess.projectissuehandle.entity.CollectEmail;
import com.bjike.goddess.projectissuehandle.enums.CollectSendUnit;
import com.bjike.goddess.projectissuehandle.enums.GuideAddrStatus;
import com.bjike.goddess.projectissuehandle.to.CollectEmailTO;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目问题受理和处理邮件发送定制业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 项目问题受理和处理邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class CollectEmailSerImpl extends ServiceImpl<CollectEmail, CollectEmailDTO> implements CollectEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProPermissionSer proPermissionSer;
    @Autowired
    private CollectEmailSer collectEmailSer;

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
            flag = proPermissionSer.getProPermission("1");
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
            flag = proPermissionSer.busProPermission("2");
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
            flag = proPermissionSer.getProPermission("1");
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
            flag = proPermissionSer.busProPermission("2");
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
        checkAddIdentity();
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
        if (null == collectEmail.getCollectSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置汇总条件
        StringBuffer condiSb = new StringBuffer("");
        String[] condis = collectEmailTO.getCondis();
        if (condis != null && condis.length >= 0) {
            for (String condiStr : condis) {
                condiSb.append(condiStr + ";");
            }
            collectEmail.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
        } else {
            throw new SerException("发送条件不能为空");
        }

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
        checkAddIdentity();
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
                    throw new SerException("邮箱输入不正确");
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
        StringBuffer condiSb = new StringBuffer("");
        String[] condis = collectEmailTO.getCondis();
        if (condis != null && condis.length >= 0) {
            for (String condiStr : condis) {
                condiSb.append(condiStr + ";");
            }
            temp.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
        } else {
            throw new SerException("发送条件不能为空");
        }


        //设置发送对象
        temp.setSendObject(String.valueOf(emails));

        super.update(temp);
        return BeanTransform.copyProperties(temp, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCollectEmail(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCollectEmail(String id) throws SerException {
        checkAddIdentity();

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.CONGEAL);
        super.update(collectEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCollectEmail(String id) throws SerException {
        checkAddIdentity();

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.THAW);
        super.update(collectEmail);
    }

    @Override
    public List<CollectBO> collect(String[] areas) throws SerException {
        if (areas == null || areas.length <= 0) {
            throw new SerException("汇总失败，请选择地区");
        }
        String[] areasTemp = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            areasTemp[i] = "'" + areas[i] + "'";
        }
        String areaStr = StringUtils.join(areasTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * from(select A.*,B.person ,B.device,B.dispartch,B.settlement,C.elementary,C.emergency,C.intermediate,D.operator ");
        sb.append(" ,D.vender,D.intergrator,D.goverment,D.innerstaff,E.complete,E.uncomplete ");
        sb.append(" from ( ");
        sb.append(" select area as area , externalContractProjectName as externalContractProjectName ,internalProjectName as internalProjectName ");
        sb.append(" from projectissuehandle_problemaccept ");
        sb.append(" a WHERE area in(%s) ");
        sb.append(" GROUP BY externalContractProjectName,internalProjectName ,area ORDER BY area ");
        sb.append(" )A,( ");
        sb.append(" SELECT area ,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemTypes=0 THEN problemTypeCounts END ) AS person, ");
        sb.append(" MAX( CASE WHEN problemTypes=1 THEN problemTypeCounts END ) AS device, ");
        sb.append(" MAX( CASE WHEN problemTypes=2 THEN problemTypeCounts END ) AS dispartch, ");
        sb.append(" MAX( CASE WHEN problemTypes=3 THEN problemTypeCounts END ) AS settlement FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemTypeCounts , problemTypes as problemTypes ,area as area,externalContractProjectName,internalProjectName ");
        sb.append(" from  projectissuehandle_problemaccept a WHERE area in(%s) ");
        sb.append(" GROUP BY externalContractProjectName,internalProjectName ,problemTypes,area ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area,externalContractProjectName,internalProjectName)B, ");
        sb.append(" (SELECT area ,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=0 THEN problemEmergencyDegreeCounts END ) AS elementary, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=1 THEN problemEmergencyDegreeCounts END ) AS intermediate, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=2 THEN problemEmergencyDegreeCounts END ) AS emergency FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemEmergencyDegreeCounts , problemEmergencyDegree as problemEmergencyDegree ,area as area,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemaccept a WHERE area in(%s) ");
        sb.append(" GROUP BY problemEmergencyDegree,area,internalProjectName,externalContractProjectName ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area ,internalProjectName,externalContractProjectName)C, ");
        sb.append(" (SELECT  area,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemObject=0 THEN problemObjectCounts END ) AS operator, ");
        sb.append(" MAX( CASE WHEN problemObject=1 THEN problemObjectCounts END ) AS vender, ");
        sb.append(" MAX( CASE WHEN problemObject=2 THEN problemObjectCounts END ) AS intergrator, ");
        sb.append(" MAX( CASE WHEN problemObject=3 THEN problemObjectCounts END ) AS goverment , ");
        sb.append(" MAX( CASE WHEN problemObject=4 THEN problemObjectCounts END ) AS innerstaff FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemObjectCounts , problemObject as problemObject ,area as area ,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemhandlingresult a WHERE area in(%s) ");
        sb.append(" GROUP BY problemObject,area ,internalProjectName,externalContractProjectName ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area ,problemObject,internalProjectName,externalContractProjectName)D, ");
        sb.append(" (SELECT area,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemProcessingResult=0 THEN problemProcessingResultCounts END ) AS complete, ");
        sb.append(" MAX( CASE WHEN problemProcessingResult=1 THEN problemProcessingResultCounts END ) AS uncomplete FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemProcessingResultCounts , problemProcessingResult as problemProcessingResult ,area as area,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemhandlingresult a WHERE area in(%s) ");
        sb.append(" GROUP BY problemProcessingResult,internalProjectName,externalContractProjectName,area ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area,internalProjectName,externalContractProjectName,problemProcessingResult)E ");
        sb.append(" where A.area=B.area and A.externalContractProjectName= B.externalContractProjectName and A.externalContractProjectName=C.externalContractProjectName ");
        sb.append(" and A.internalProjectName=B.internalProjectName and C.internalProjectName=A.internalProjectName and  A.area=C.area and ");
        sb.append(" A.area=D.area and A.externalContractProjectName=D.externalContractProjectName ");
        sb.append(" and A.internalProjectName=D.internalProjectName and A.area=E.area and A.externalContractProjectName=E.externalContractProjectName ");
        sb.append(" and A.internalProjectName=E.internalProjectName ");
        sb.append(" order by area)F ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS area,NULL as externalContractProjectName,NULL as internalProjectName, ");
        sb.append(" sum(person) AS person,sum(device)AS device,sum(dispartch)AS dispartch, ");
        sb.append(" sum(settlement)AS settlement,sum(elementary)as elementary,sum(emergency)as emergency, ");
        sb.append(" sum(intermediate)as intermediate,sum(operator)as operator,sum(vender)as vender, ");
        sb.append(" sum(intergrator)as intergrator,sum(goverment)as goverment,sum(innerstaff)as innerstaff, ");
        sb.append(" sum(complete)as complete,sum(uncomplete)AS uncomplete ");
        sb.append(" FROM (select A.*,B.person ,B.device,B.dispartch,B.settlement,C.elementary,C.emergency,C.intermediate,D.operator ");
        sb.append(" ,D.vender,D.intergrator,D.goverment,D.innerstaff,E.complete,E.uncomplete ");
        sb.append(" from ( ");
        sb.append(" select area as area , externalContractProjectName as externalContractProjectName ,internalProjectName as internalProjectName ");
        sb.append(" from projectissuehandle_problemaccept ");
        sb.append(" a WHERE area in(%s) ");
        sb.append(" GROUP BY externalContractProjectName,internalProjectName ,area ORDER BY area ");
        sb.append(" )A,( ");
        sb.append(" SELECT area ,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemTypes=0 THEN problemTypeCounts END ) AS person, ");
        sb.append(" MAX( CASE WHEN problemTypes=1 THEN problemTypeCounts END ) AS device, ");
        sb.append(" MAX( CASE WHEN problemTypes=2 THEN problemTypeCounts END ) AS dispartch, ");
        sb.append(" MAX( CASE WHEN problemTypes=3 THEN problemTypeCounts END ) AS settlement FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemTypeCounts , problemTypes as problemTypes ,area as area,externalContractProjectName,internalProjectName ");
        sb.append(" from  projectissuehandle_problemaccept a WHERE area in(%s) ");
        sb.append(" GROUP BY externalContractProjectName,internalProjectName ,problemTypes,area ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area,externalContractProjectName,internalProjectName)B, ");
        sb.append(" (SELECT area ,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=0 THEN problemEmergencyDegreeCounts END ) AS elementary, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=1 THEN problemEmergencyDegreeCounts END ) AS intermediate, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=2 THEN problemEmergencyDegreeCounts END ) AS emergency FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemEmergencyDegreeCounts , problemEmergencyDegree as problemEmergencyDegree ,area as area,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemaccept a WHERE area in(%s) ");
        sb.append(" GROUP BY problemEmergencyDegree,area,internalProjectName,externalContractProjectName ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area ,internalProjectName,externalContractProjectName)C, ");
        sb.append(" (SELECT  area,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemObject=0 THEN problemObjectCounts END ) AS operator, ");
        sb.append(" MAX( CASE WHEN problemObject=1 THEN problemObjectCounts END ) AS vender, ");
        sb.append(" MAX( CASE WHEN problemObject=2 THEN problemObjectCounts END ) AS intergrator, ");
        sb.append(" MAX( CASE WHEN problemObject=3 THEN problemObjectCounts END ) AS goverment , ");
        sb.append(" MAX( CASE WHEN problemObject=4 THEN problemObjectCounts END ) AS innerstaff FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemObjectCounts , problemObject as problemObject ,area as area ,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemhandlingresult a WHERE area in(%s) ");
        sb.append(" GROUP BY problemObject,area ,internalProjectName,externalContractProjectName ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area ,problemObject,internalProjectName,externalContractProjectName)D, ");
        sb.append(" (SELECT area,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemProcessingResult=0 THEN problemProcessingResultCounts END ) AS complete, ");
        sb.append(" MAX( CASE WHEN problemProcessingResult=1 THEN problemProcessingResultCounts END ) AS uncomplete FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemProcessingResultCounts , problemProcessingResult as problemProcessingResult ,area as area,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemhandlingresult a WHERE area in(%s) ");
        sb.append(" GROUP BY problemProcessingResult,internalProjectName,externalContractProjectName,area ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area,internalProjectName,externalContractProjectName,problemProcessingResult)E ");
        sb.append(" where A.area=B.area and A.externalContractProjectName= B.externalContractProjectName and A.externalContractProjectName=C.externalContractProjectName ");
        sb.append(" and A.internalProjectName=B.internalProjectName and C.internalProjectName=A.internalProjectName and  A.area=C.area and ");
        sb.append(" A.area=D.area and A.externalContractProjectName=D.externalContractProjectName ");
        sb.append(" and A.internalProjectName=D.internalProjectName and A.area=E.area and A.externalContractProjectName=E.externalContractProjectName ");
        sb.append(" and A.internalProjectName=E.internalProjectName ");
        sb.append(" order by area)F ");

        String sql = sb.toString();
        sql = String.format(sql, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr);
        String[] fields = new String[]{"area", "externalContractProjectName", "internalProjectName",
                "person", "device", "dispartch", "settlement", "elementary", "emergency", "intermediate",
                "operator", "vender", "intergrator", "goverment", "innerstaff", "complete", "uncomplete"};
        List<CollectBO> collectBOS = super.findBySql(sql, CollectBO.class, fields);
        return collectBOS;
    }

    @Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<ProblemHandlingResultBO> problemHandlingResultBOS = super.findBySql("select distinct area from projectissuehandle_problemhandlingresult group by area order by area asc ", ProblemHandlingResultBO.class, fields);

        List<String> collectList = problemHandlingResultBOS.stream().map(ProblemHandlingResultBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return collectList;
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
        List<CollectEmail> proEmails = new ArrayList<>();

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

            if (flag && type.equals("项目问题处理和受理汇总")) {
                proEmails.add(str);
                allEmails.add(str);
            }


        }

        //调用发邮件
        allEmails = sendObject(proEmails);

        //修改上次发送时间
        super.update(allEmails);

    }


    private String htmlProject(List<CollectBO> collectBOS) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (collectBOS != null && collectBOS.size() > 0) {
            sb = new StringBuffer("<h4>项目问题受理和处理汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            CollectBO title = collectBOS.get(collectBOS.size() - 1);
            sb.append("<tr>");
            sb.append("<td>地区</td>");
            sb.append("<td>合同外部项目名称</td>");
            sb.append("<td>内部项目名称</td>");
            sb.append("<td>人员类</td>");
            sb.append("<td>设备类</td>");
            sb.append("<td>派工类</td>");
            sb.append("<td>结算类</td>");
            sb.append("<td>初级</td>");
            sb.append("<td>中级</td>");
            sb.append("<td>紧急</td>");
            sb.append("<td>运营商</td>");
            sb.append("<td>厂家</td>");
            sb.append("<td>集成商</td>");
            sb.append("<td>政府机关</td>");
            sb.append("<td>内部员工</td>");
            sb.append("<td>完成</td>");
            sb.append("<td>未完成</td>");
            sb.append("<tr>");

            //拼body部分
            for (CollectBO bo : collectBOS) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getArea() + "</td>");
                sb.append("<td>" + (StringUtils.isBlank(bo.getExternalContractProjectName())? " " :bo.getExternalContractProjectName())+ "</td>");
                sb.append("<td>" + (StringUtils.isBlank(bo.getInternalProjectName())? "":bo.getInternalProjectName() + "</td>"));
                sb.append("<td>" + ( null==bo.getPerson() ? "" :bo.getPerson()) + "</td>");
                sb.append("<td>" + ( null==bo.getDevice() ? "" :bo.getDevice()) + "</td>");
                sb.append("<td>" + ( null==bo.getDispartch() ? "" : bo.getDispartch()) + "</td>");
                sb.append("<td>" + ( null==bo.getSettlement() ? "" :bo.getSettlement()) + "</td>");
                sb.append("<td>" + ( null==bo.getElementary() ? "" :bo.getElementary()) + "</td>");
                sb.append("<td>" + ( null==bo.getEmergency() ? "" :bo.getEmergency()) + "</td>");
                sb.append("<td>" + ( null==bo.getIntermediate() ? "":bo.getIntermediate()) + "</td>");
                sb.append("<td>" + ( null==bo.getOperator() ? "":bo.getOperator()) + "</td>");
                sb.append("<td>" + ( null==bo.getVender() ? "":bo.getVender()) + "</td>");
                sb.append("<td>" + ( null==bo.getIntergrator() ? "":bo.getIntergrator()) + "</td>");
                sb.append("<td>" + ( null==bo.getGoverment() ? "":bo.getGoverment()) + "</td>");
                sb.append("<td>" + ( null==bo.getInnerstaff() ? "":bo.getInnerstaff()) + "</td>");
                sb.append("<td>" + ( null==bo.getComplete() ? "":bo.getComplete()) + "</td>");
                sb.append("<td>" + ( null==bo.getUncomplete() ? "":bo.getUncomplete()) + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private List<CollectEmail> sendObject(List<CollectEmail> proEmails) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<CollectEmail> allEmails = new ArrayList<>();
        //项目问题受理和处理汇总
        RpcTransmit.transmitUserToken(userToken);
        if (proEmails != null && proEmails.size() > 0) {
            for (CollectEmail dispa : proEmails) {
                String[] condis = dispa.getCondi().split(";");
                List<CollectBO> collectBOList = collectEmailSer.collect(condis);
                //拼表格
                String content = htmlProject(collectBOList);
                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("定时发送项目问题受理和处理汇总");
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