package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.recruit.bo.FirstPhoneRecordBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.entity.FirstPhoneRecord;
import com.bjike.goddess.recruit.to.FirstPhoneRecordTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 第一次电访记录
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class FirstPhoneRecordSerImpl extends ServiceImpl<FirstPhoneRecord, FirstPhoneRecordDTO> implements FirstPhoneRecordSer {
    @Autowired
    private UserAPI userAPI;
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
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

    /**
     * 分页查询第一次电访记录
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<FirstPhoneRecordBO> list(FirstPhoneRecordDTO dto) throws SerException {
        checkSeeIdentity();
        List<FirstPhoneRecord> list = super.findByPage(dto);
        List<FirstPhoneRecordBO> listBO = BeanTransform.copyProperties(list, FirstPhoneRecordBO.class);
        return listBO;
    }

    /**
     * 保存第一次电访记录
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public FirstPhoneRecordBO save(FirstPhoneRecordTO to) throws SerException {
        checkAddIdentity();
        FirstPhoneRecord failFirstInterviewReason = BeanTransform.copyProperties(to, FirstPhoneRecord.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        FirstPhoneRecordBO bo = BeanTransform.copyProperties(failFirstInterviewReason, FirstPhoneRecordBO.class);
        String name = failFirstInterviewReason.getName();
        String email = failFirstInterviewReason.getEmail();
        LocalDateTime time = failFirstInterviewReason.getFirstInterviewTime();
        String position = failFirstInterviewReason.getPosition();
        if (time != null) {
            String content = "" + name + "先生/小姐：\n" +
                    "您好！感谢您对本公司" + position + "一职的青睐。 \n" +
                    "     现诚邀您于" + time.getYear() + "年" + time.getMonthValue() + "月" + time.getDayOfMonth() + "日上午" + time.getHour() + "：" + time.getMinute() + "到北京艾佳 广州分公司 面试，届时请带齐简历、身份证、等相关资料参加。公司网址：http://www.bjike.com/  如不能按时参加请提前告知，谢谢！（收到邮件请回复）\n" +
                    "电话：23337353或29046363\n" +
                    "面试地址：广东省广州市天河区棠东毓南路13号冠达商务中心E栋1楼111-112房(在BRT天朗明居站下看到凯尔卡顿大酒店,直走即可看到冠达商务楼，往前直走100米后向左转直走即可看到我们公司111-112房)";
            String title = "面试邀请函";
            if ((null != failFirstInterviewReason.getWhetherFirstInviteSuccess()) && failFirstInterviewReason.getWhetherFirstInviteSuccess()) {
                if (email != null) {
                    MessageTO messageTO = new MessageTO();
                    messageTO.setTitle(title);
                    messageTO.setContent(content);
                    messageTO.setReceivers(new String[]{email});
                    messageAPI.send(messageTO);
                }
            }
        }
        return bo;
    }

    /**
     * 更新第一次电访记录
     *
     * @param to 第一次电访记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(FirstPhoneRecordTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            FirstPhoneRecord model = super.findById(to.getId());
            if (model != null) {
                updateFirstPhoneRecord(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新第一次电访记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateFirstPhoneRecord(FirstPhoneRecordTO to, FirstPhoneRecord model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除第一次电访记录
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(FirstPhoneRecord entity) throws SerException {
        checkAddIdentity();
        super.remove(entity);
    }

    @Override
    public Set<String> allFirstName() throws SerException {
        Set<String> set = new HashSet<>();
        List<FirstPhoneRecord> list = super.findAll();
        for (FirstPhoneRecord f : list) {
            set.add(f.getName());
        }
        return set;
    }
}
