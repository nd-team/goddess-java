package com.bjike.goddess.problemhandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.problemhandle.bo.CommunicationFormworkBO;
import com.bjike.goddess.problemhandle.dto.CommunicationFormworkDTO;
import com.bjike.goddess.problemhandle.entity.CommunicationFormwork;
import com.bjike.goddess.problemhandle.enums.GuideAddrStatus;
import com.bjike.goddess.problemhandle.to.CommunicationFormworkTO;
import com.bjike.goddess.problemhandle.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 各类沟通模板业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-09 10:17 ]
 * @Description: [ 各类沟通模板业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "problemhandleSerCache")
@Service
public class CommunicationFormworkSerImpl extends ServiceImpl<CommunicationFormwork, CommunicationFormworkDTO> implements CommunicationFormworkSer {
    @Autowired
    private MessageAPI messageAPI;

    @Autowired
    private ProPermissionSer proPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（部门级别）
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
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = proPermissionSer.getProPermission("1");
        }else{
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = proPermissionSer.busProPermission("2");
        }else{
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken( userToken );
        Boolean flagAdd = guideAddIdentity();
        if( flagSee || flagAdd ){
            return true;
        }else{
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public Long countCommuni(CommunicationFormworkDTO communicationFormworkDTO) throws SerException {
        Long count = super.count(communicationFormworkDTO);
        return count;
    }

    @Override
    public CommunicationFormworkBO getOne(String id) throws SerException {
        CommunicationFormwork communicationFormwork = super.findById(id);
        return BeanTransform.copyProperties(communicationFormwork, CommunicationFormworkBO.class);
    }

    @Override
    public List<CommunicationFormworkBO> findListCommuni(CommunicationFormworkDTO communicationFormworkDTO) throws SerException {
       checkSeeIdentity();
        List<CommunicationFormwork> communicationFormworkList = super.findByCis(communicationFormworkDTO, true);
        return BeanTransform.copyProperties(communicationFormworkList, CommunicationFormworkBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CommunicationFormworkBO insertCommuni(CommunicationFormworkTO communicationFormworkTO) throws SerException {
       checkAddIdentity();
        CommunicationFormwork communicationFormwork = BeanTransform.copyProperties(communicationFormworkTO, CommunicationFormwork.class, true, "lordSendObj", "ccObj");
        StringBuffer emails = new StringBuffer("");
        if (communicationFormworkTO.getLordSendObj() != null && communicationFormworkTO.getLordSendObj().size() > 0) {
            for (String emailStr : communicationFormworkTO.getLordSendObj()) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        StringBuffer ccEmails = new StringBuffer("");
        if (communicationFormworkTO.getCcObj() != null && communicationFormworkTO.getCcObj().size() > 0) {
            for (String emailStr : communicationFormworkTO.getCcObj()) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                ccEmails.append(emailStr + ",");
            }
        }
        String lordSendObj = emails.substring(0, emails.toString().lastIndexOf(","));
        String ccSendObj = emails.substring(0, ccEmails.toString().lastIndexOf(","));
        communicationFormwork.setCreateTime(LocalDateTime.now());
        communicationFormwork.setLordSendObj(lordSendObj);
        communicationFormwork.setCcObj(ccSendObj);
        super.save(communicationFormwork);
        return BeanTransform.copyProperties(communicationFormwork, CommunicationFormworkBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CommunicationFormworkBO editCommuni(CommunicationFormworkTO communicationFormworkTO) throws SerException {
      checkAddIdentity();
        CommunicationFormwork communicationFormwork = super.findById(communicationFormworkTO.getId());
        LocalDateTime dateTime = communicationFormwork.getCreateTime();
        communicationFormwork = BeanTransform.copyProperties(communicationFormworkTO, CommunicationFormwork.class, true, "lordSendObj", "ccObj");
        StringBuffer emails = new StringBuffer("");
        if (communicationFormworkTO.getLordSendObj() != null && communicationFormworkTO.getLordSendObj().size() > 0) {
            for (String emailStr : communicationFormworkTO.getLordSendObj()) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        StringBuffer ccEmails = new StringBuffer("");
        if (communicationFormworkTO.getCcObj() != null && communicationFormworkTO.getCcObj().size() > 0) {
            for (String emailStr : communicationFormworkTO.getCcObj()) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                ccEmails.append(emailStr + ",");
            }
        }
        String lordSendObj = emails.substring(0, emails.toString().lastIndexOf(","));
        String ccSendObj = emails.substring(0, ccEmails.toString().lastIndexOf(","));
        communicationFormwork.setCreateTime(dateTime);
        communicationFormwork.setLordSendObj(lordSendObj);
        communicationFormwork.setCcObj(ccSendObj);
        communicationFormwork.setModifyTime(LocalDateTime.now());
        super.save(communicationFormwork);
        return BeanTransform.copyProperties(communicationFormwork, CommunicationFormworkBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeCommuni(String id) throws SerException {
       checkAddIdentity();
        super.remove(id);
    }

    @Override
    public CommunicationFormworkBO findByClassification(String classification) throws SerException {
        CommunicationFormworkDTO communicationFormworkDTO = new CommunicationFormworkDTO();
        communicationFormworkDTO.getConditions().add(Restrict.eq("classification", classification));
        CommunicationFormwork communicationFormwork = super.findOne(communicationFormworkDTO);
        return BeanTransform.copyProperties(communicationFormwork, CommunicationFormworkBO.class);
    }

    @Override
    public List<String> findAllType() throws SerException {
        List<CommunicationFormwork> communicationFormworkList = super.findAll();
        if(CollectionUtils.isEmpty(communicationFormworkList)){
            return Collections.emptyList();
        }
        return communicationFormworkList.stream().map(CommunicationFormwork::getClassification).distinct().collect(Collectors.toList());
    }

    @Override
    public void notification(String classification,String emailModule) throws SerException {
        CommunicationFormworkBO communicationFormworkBO = findByClassification(classification);
        if(communicationFormworkBO!=null){
            String[] sendLordObject = communicationFormworkBO.getLordSendObj().split(",");
            String[] sendCcObject = communicationFormworkBO.getCcObj().split(",");
            MessageTO messageTO = new MessageTO();
            messageTO.setContent(emailModule);
            messageTO.setTitle("通报");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);

            messageTO.setReceivers(ArrayUtils.addAll(sendLordObject,sendCcObject));
            messageAPI.send(messageTO);
        }
    }

}