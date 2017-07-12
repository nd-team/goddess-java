package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CollectData;
import com.bjike.goddess.capability.bo.CollectEmailBO;
import com.bjike.goddess.capability.dto.CollectEmailDTO;
import com.bjike.goddess.capability.dto.CompanyCapabilityDTO;
import com.bjike.goddess.capability.entity.CollectEmail;
import com.bjike.goddess.capability.entity.CompanyCapability;
import com.bjike.goddess.capability.enums.CollectSendUnit;
import com.bjike.goddess.capability.enums.GuideAddrStatus;
import com.bjike.goddess.capability.to.CollectEmailTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.bjike.goddess.message.to.MessageTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CollectEmailSerImpl extends ServiceImpl<CollectEmail, CollectEmailDTO> implements CollectEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CompanyCapabilitySer companyCapabilityAPI;
    @Autowired
    private CooperCapabilitySer cooperCapabilityAPI;
    @Autowired
    private SelfCapabilitySer selfCapabilityAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer ;
    @Autowired
    private MessageAPI messageAPI ;



    @Override
    public Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        Long count = super.count(collectEmailDTO);
        return count;
    }

    @Override
    public CollectEmailBO getOne(String id) throws SerException {

        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空哦");
        }
        CollectEmail selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability,CollectEmailBO.class);

    }

    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您的帐号没有权限");
        }

        collectEmailDTO.getSorts().add("createTime=desc");
        List<CollectEmail> list = super.findByPage(collectEmailDTO);
        return BeanTransform.copyProperties(list, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行添加操作");
        }

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }

        RpcTransmit.transmitUserToken(userToken);

        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        collectEmail.setCreateTime(LocalDateTime.now());
        collectEmail.setStatus(Status.THAW);
        collectEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置汇总公司名和人名
        String companyOrName ="";
        for(String str : collectEmailTO.getCompanyOrNames()){
            companyOrName += str+",";
        }
        collectEmail.setCompanyOrName( companyOrName );

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

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
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行编辑操作");
        }

        if (StringUtils.isBlank(collectEmailTO.getId() )) {
            throw new SerException("id不能为空");
        }
        CollectEmail temp = super.findById(collectEmailTO.getId());

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        BeanUtils.copyProperties( collectEmail,temp ,"id","createTime","createPersion","lastSendTime","status");
        temp.setModifyTime(LocalDateTime.now());

        //设置汇总公司名和人名
        String companyOrName ="";
        for(String str : collectEmailTO.getCompanyOrNames()){
            companyOrName += str+",";
        }
        temp.setCompanyOrName( companyOrName );

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        temp.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置发送对象
        temp.setSendObject(String.valueOf(emails));

        super.update(temp);
        return BeanTransform.copyProperties(temp, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCollectEmail(String id) throws SerException {
        //商务模块编辑权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除操作");
        }

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCollectEmail(String id) throws SerException {

        //商务模块冻结权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除操作");
        }

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.CONGEAL);
        super.update(collectEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCollectEmail(String id) throws SerException {
        //商务模块解冻权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除操作");
        }

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.THAW);
        super.update(collectEmail);
    }


    @Override
    public List<CollectEmailBO> collectCompanyEmail(String[] companyOrName) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<CollectData> collectDataList = new ArrayList<>();
        List<String> companys = Arrays.asList(companyOrName);
        for(String str : companys ) {
            collectDataList = new ArrayList<>();
            String[] fields = new String[]{"counts","remark"};
            //专业资质认证数量
            String sql = "SELECT count(DISTINCT zy.name) AS counts,'专业资质认证数量' AS remark FROM capability_companycapability as company , capability_professionauthen as zy WHERE company = '"+str+"' and company.id = zy.baseId ;";
            List<CollectEmailBO> collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            CollectData collectData = new CollectData();
            collectData.setName("专业资质认证数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0));
            collectDataList.add( collectData );

            //管理资质认证数量
            sql = "select count(DISTINCT gl.name) as counts , '管理资质认证数量' as remark from capability_companycapability as company ,capability_manageauthen as gl where company = '"+str+"' and company.id = gl.baseId ;";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("管理资质认证数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0) );
            collectDataList.add( collectData );

            //公司荣誉证书数量
            sql = "select count(DISTINCT ry.name) as counts , '公司荣誉证书数量' as remark from capability_companycapability as company ,capability_companycertificate as ry where company = '"+str+"' and company.id = ry.baseId; ";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("公司荣誉证书数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0) );
            collectDataList.add( collectData );

            //公司人员组成
            String[] fields2 = new String[]{"companyOrName", "remark"};
            sql = "select personForm as companyOrName , '公司人员组成' as remark from capability_companycapability where company = '"+str+"'  ";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields2);
            collectData = new CollectData();
            collectData.setName("公司人员组成");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCompanyOrName()):String.valueOf(0) );
            collectDataList.add( collectData );


            //公司已完成项目数量
            sql = "select count(DISTINCT comple.name) as counts , '公司已完成项目数量' as remark from capability_companycapability as company ,capability_companyproject as comple where company = '"+str+"'  and company.id = comple.baseId;";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("公司已完成项目数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0));
            collectDataList.add( collectData );

            //公司正在进行中项目数量
            sql = "select count(DISTINCT inprojct.name) as counts , '公司正在进行中项目数量' as remark from capability_companycapability as company ,capability_inprojct as inprojct where company = '"+str+"' and company.id = inprojct.baseId;";
            collectEmailBOS = companyCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("公司正在进行中项目数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ?String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0));
            collectDataList.add( collectData );

            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setRemark( str );
            cbo.setCollectDataList( collectDataList );
            collectEmailBOList.add( cbo );
        }
        return collectEmailBOList;
    }

    @Override
    public List<CollectEmailBO> collectSelfEmail(String[] name) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<CollectData> collectDataList = new ArrayList<>();
        List<String> names = Arrays.asList(name);
        for(String str : names ) {
            collectDataList = new ArrayList<>();
            String[] fields = new String[]{"counts", "remark"};
            //个人资质数量
            String sql = "select count(capacity.name) as counts , '个人资质数量' as remark from capability_selfcapability  as capability ,capability_capacity as capacity where capability.name = '"+str+"'  and capability.id = capacity.baseId;";
            List<CollectEmailBO> collectEmailBOS = selfCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            CollectData collectData = new CollectData();
            collectData.setName("个人资质数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0) );
            collectDataList.add( collectData );
            //个人经手项目数量
            sql = "select count(selfproject.name) as counts , '个人经手项目数量' as remark from capability_selfcapability as capability , capability_selfproject as selfproject where capability.name = '"+str+"' and capability.id = selfproject.baseId; ";
            collectEmailBOS = selfCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("个人经手项目数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0));
            collectDataList.add( collectData );
            //个人社交资源数量
            sql = "select count(a.selfCapabilityId) as counts , '个人社交资源数量' as remark from capability_selfcapability self , capability_selfcapabilitysocial a  where self.id = a.selfCapabilityId and  self.name = '"+str+"'  ";
            collectEmailBOS = selfCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("个人社交资源数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0));
            collectDataList.add( collectData );

            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setRemark( str );
            cbo.setCollectDataList( collectDataList );
            collectEmailBOList.add( cbo );
        }
        return collectEmailBOList;
    }

    @Override
    public List<CollectEmailBO> collectCooperEmail(String[] companyOrName) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<CollectData> collectDataList = new ArrayList<>();
        List<String> companys = Arrays.asList(companyOrName);
        for(String str : companys ) {
            collectDataList = new ArrayList<>();
            String[] fields = new String[]{"counts", "remark"};
            //专业资质认证数量
            String sql = "SELECT count(DISTINCT zy.name) AS counts,'专业资质认证数量' AS remark FROM capability_coopercapability as company , capability_professionauthen as zy WHERE companyName = '"+str+"' and company.id = zy.baseId ; ";
            List<CollectEmailBO> collectEmailBOS = cooperCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            CollectData collectData = new CollectData();
            collectData.setName("专业资质认证数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0) );
            collectDataList.add( collectData );
            //管理资质认证数量
            sql = "select count(DISTINCT gl.name) as counts , '管理资质认证数量' as remark from capability_coopercapability as company ,capability_manageauthen as gl where companyName = '"+str+"' and company.id = gl.baseId ; ";
            collectEmailBOS = cooperCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("管理资质认证数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ?String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0) );
            collectDataList.add( collectData );


            //公司参与项目数量
            sql = "select count(DISTINCT comple.name) as counts , '公司参与项目数量' as remark from capability_coopercapability as company ,capability_companyproject as comple where companyName = '"+str+"'  and company.id = comple.baseId; ";
            collectEmailBOS = cooperCapabilityAPI.findBySql(sql, CollectEmailBO.class, fields);
            collectData = new CollectData();
            collectData.setName("公司参与项目数量");
            collectData.setCounts( collectEmailBOS!=null && collectEmailBOS.size()>0 ? String.valueOf(collectEmailBOS.get(0).getCounts()):String.valueOf(0));
            collectDataList.add( collectData );



            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setRemark( str );
            cbo.setCollectDataList( collectDataList );
            collectEmailBOList.add( cbo );
        }
        return collectEmailBOList;
    }

    @Override
    public List<String> listName(String type) throws SerException {
        List<String> list = new ArrayList<>();
        //商业能力
        if( "0".equals(type)){
            list = companyCapabilityAPI.listAllCompanyName();
        }else if( "1".equals(type)){
            //个人能力
            list = selfCapabilityAPI.listAllSelfName();
        }else if( "2".equals(type)){
            //合作对象
            list = cooperCapabilityAPI.listAllCompanyName();
        }
        return list;
    }
    

    @Override
    public void checkSendEmail() throws SerException {
        List<CollectEmail> allEmails = new ArrayList<>();
        List<CollectEmail> companyCapEmails = new ArrayList<>();
        List<CollectEmail> cooperCapEmails = new ArrayList<>();
        List<CollectEmail> selfCapEmails = new ArrayList<>();

        //检测有哪些需要发送的
        //上次发送时间
        //现在时间
        //发送间隔
        //发送单位
        //发送类型
        //发送对象
        CollectEmailDTO dto = new CollectEmailDTO();
        dto.getConditions().add(Restrict.eq("status",Status.THAW));
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

            if (flag && type.equals("公司能力展示汇总")) {
                companyCapEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals("合作对象商务展示汇总")) {
                cooperCapEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals("个人能力展示汇总")) {
                selfCapEmails.add(str);
                allEmails.add(str);
            }


        }

        //调用发邮件
        allEmails = sendObject(companyCapEmails, cooperCapEmails, selfCapEmails);

        //修改上次发送时间
        super.update(allEmails);

    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        if (flagSee) {
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case AUDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case CONGEL:
                flag = guideSeeIdentity();
                break;
            case THAW:
                flag = guideSeeIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case IMPORT:
                flag = guideSeeIdentity();
                break;
            case EXPORT:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideSeeIdentity();
                break;
            case DOWNLOAD:
                flag = guideSeeIdentity();
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

    private String htmlcompanyCap(List<CollectEmailBO> collectEmailBOList) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (collectEmailBOList != null && collectEmailBOList.size() > 0) {
            sb = new StringBuffer("<h4>公司能力展示汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            CollectEmailBO title = collectEmailBOList.get(collectEmailBOList.size() - 1);
            sb.append("<tr>");
            sb.append("<td>公司名称</td>");
            for(CollectEmailBO bo : collectEmailBOList){
                for(CollectData collectData : bo.getCollectDataList()){
                    sb.append("<td>" + collectData.getName() + "<td>");
                }
            }
            sb.append("<tr>");

            //拼body部分
            for (CollectEmailBO bo : collectEmailBOList) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getType() + "</td>");
                for (CollectData collectData : bo.getCollectDataList()) {
                    sb.append("<td>" + collectData.getCounts() + "</td>");
                }
                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private String htmlcooperCap(List<CollectEmailBO> collectEmailBOList) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (collectEmailBOList != null && collectEmailBOList.size() > 0) {
            sb = new StringBuffer("<h4>合作对象商务展示汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            CollectEmailBO title = collectEmailBOList.get(collectEmailBOList.size() - 1);
            sb.append("<tr>");
            sb.append("<td>公司名称</td>");
            for(CollectEmailBO bo : collectEmailBOList){
                for(CollectData collectData : bo.getCollectDataList()){
                    sb.append("<td>" + collectData.getName() + "<td>");
                }
            }
            sb.append("<tr>");

            //拼body部分
            for (CollectEmailBO bo : collectEmailBOList) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getType() + "</td>");
                for (CollectData collectData : bo.getCollectDataList()) {
                    sb.append("<td>" + collectData.getCounts() + "</td>");
                }
                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private String htmlselfCap(List<CollectEmailBO> selfCapEmails) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (selfCapEmails != null && selfCapEmails.size() > 0) {
            sb = new StringBuffer("<h4>个人能力展示汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            CollectEmailBO title = selfCapEmails.get(selfCapEmails.size() - 1);
            sb.append("<tr>");
            sb.append("<td>个人名字</td>");
            for(CollectEmailBO bo : selfCapEmails){
                for(CollectData collectData : bo.getCollectDataList()){
                    sb.append("<td>" + collectData.getName() + "<td>");
                }
            }
            sb.append("<tr>");

            //拼body部分
            for (CollectEmailBO bo : selfCapEmails) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getType() + "</td>");
                for (CollectData collectData : bo.getCollectDataList()) {
                    sb.append("<td>" + collectData.getCounts() + "</td>");
                }
                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private List<CollectEmail> sendObject(List<CollectEmail> companyCapEmails, List<CollectEmail> cooperCapEmails, List<CollectEmail> selfCapEmails) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<CollectEmail> allEmails = new ArrayList<>();
        //公司能力展示汇总
        //个人能力展示汇总
        //合作对象商务展示汇总
        if (companyCapEmails != null && companyCapEmails.size() > 0) {
            for (CollectEmail collectEmail : companyCapEmails) {
                String[] condis = collectEmail.getCompanyOrName().split(";");
                List<CollectEmailBO> collectEmailBOList = collectCompanyEmail(condis);
                //拼表格
                String content = htmlcompanyCap(collectEmailBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent( content );
                messageTO.setTitle("定时发送公司能力展示汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType( SendType.EMAIL);
                messageTO.setRangeType( RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(collectEmail.getSendObject().split(";") );
                messageAPI.send(  messageTO );

                collectEmail.setModifyTime(LocalDateTime.now());
                allEmails.add(collectEmail);
            }
        }

        RpcTransmit.transmitUserToken( userToken );
        if (selfCapEmails != null && selfCapEmails.size() > 0) {
            for (CollectEmail baseinfo : selfCapEmails) {
                String[] condis = baseinfo.getCompanyOrName().split(";");
                List<CollectEmailBO> collectEmailBOList = collectCooperEmail(condis);
                //拼表格
                String content = htmlcooperCap(collectEmailBOList);
                MessageTO messageTO = new MessageTO();
                messageTO.setContent( content );
                messageTO.setTitle("定时发送合作对象商务展示汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType( SendType.EMAIL);
                messageTO.setRangeType( RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(baseinfo.getSendObject().split(";") );
                messageAPI.send(  messageTO );

                baseinfo.setModifyTime(LocalDateTime.now());
                allEmails.add(baseinfo);
            }
        }
        RpcTransmit.transmitUserToken( userToken );
        if (cooperCapEmails != null && cooperCapEmails.size() > 0) {
            for (CollectEmail dispa : cooperCapEmails) {
                String[] condis = dispa.getCompanyOrName().split(";");
                List<CollectEmailBO> dispatchBOList = collectSelfEmail(condis);
                //拼表格
                String content = htmlselfCap(dispatchBOList);
                MessageTO messageTO = new MessageTO();
                messageTO.setContent( content );
                messageTO.setTitle("定时发送个人能力展示汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType( SendType.EMAIL);
                messageTO.setRangeType( RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(dispa.getSendObject().split(";") );
                messageAPI.send(  messageTO );

                dispa.setModifyTime(LocalDateTime.now());
                allEmails.add(dispa);
            }
        }
        return allEmails;

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



}