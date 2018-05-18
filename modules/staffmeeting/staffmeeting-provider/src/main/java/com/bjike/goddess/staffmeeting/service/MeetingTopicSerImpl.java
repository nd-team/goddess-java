package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.MeetingTopicBO;
import com.bjike.goddess.staffmeeting.dto.MeetingLayDTO;
import com.bjike.goddess.staffmeeting.dto.MeetingTopicDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingLay;
import com.bjike.goddess.staffmeeting.entity.MeetingTopic;
import com.bjike.goddess.staffmeeting.enums.GuideAddrStatus;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import com.bjike.goddess.staffmeeting.to.MeetingTopicTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 议题管理业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:31 ]
 * @Description: [ 议题管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmeetingSerCache")
@Service
public class MeetingTopicSerImpl extends ServiceImpl<MeetingTopic, MeetingTopicDTO> implements MeetingTopicSer {

    @Autowired
    private MeetingLaySer meetingLaySer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingTopicBO insertModel(MeetingTopicTO to) throws SerException {
        checkAddIdentity();
        checkData(to);
        MeetingTopic model = BeanTransform.copyProperties(to, MeetingTopic.class);
        super.save(model);
        return BeanTransform.copyProperties(model, MeetingTopicBO.class);
    }

    //检查数据是否重复
    public void checkData(MeetingTopicTO to) throws SerException{
        MeetingTopicDTO dto = new MeetingTopicDTO();
        dto.getConditions().add(Restrict.eq("topic",to.getTopic()));
        dto.getConditions().add(Restrict.eq("topicContent",to.getTopicContent()));
        dto.setLimit(1);
        List<MeetingTopic> list = super.findByPage(dto);
        if(list!=null && !list.isEmpty()){
            //新增
            if(StringUtils.isEmpty(to.getId())){
                throw new SerException("该主题对应的议题包含内容已存在!");
            }else if(to.getId().equals(list.get(0).getId())){//编辑时判断是否同一条数据

            }else{
                throw new SerException("该主题对应的议题包含内容已存在!");
            }

        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingTopicBO updateModel(MeetingTopicTO to) throws SerException {
        checkAddIdentity();
        MeetingTopic model = super.findById(to.getId());
        if (model != null) {

            checkData(to);
            BeanTransform.copyProperties(to, model);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空!");
        }
        return BeanTransform.copyProperties(model,MeetingTopicBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MeetingTopicBO> pageList(MeetingTopicDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto),MeetingTopicBO.class);
    }

    @Override
    public MeetingTopicBO findByLay(String layId) throws SerException {
        checkSeeIdentity();
        MeetingLay lay = meetingLaySer.findById(layId);
        if(lay!=null){
            return BeanTransform.copyProperties(super.findById(lay.getMeetingTopic().getId()),MeetingTopicBO.class);
        }else{
            throw new SerException("非法层面id，层面对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        checkAddIdentity();
        MeetingLayDTO layDTO = new MeetingLayDTO();
        layDTO.getConditions().add(Restrict.eq("meetingTopic.id",id));
        List<MeetingLay> layList = meetingLaySer.findByCis(layDTO);
        if(CollectionUtils.isEmpty(layList)){
            super.remove(id);
        }else{
            throw new SerException("该会议主题存在层面关联,请确保会议主题无数据关联!");
        }
    }

}