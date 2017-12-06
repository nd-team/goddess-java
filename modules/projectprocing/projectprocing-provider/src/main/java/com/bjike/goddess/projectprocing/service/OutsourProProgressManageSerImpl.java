package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.projectprocing.bo.OutsourProProgressManageBO;
import com.bjike.goddess.projectprocing.dto.OutsourProProgressManageDTO;
import com.bjike.goddess.projectprocing.entity.OutsourProProgressManage;
import com.bjike.goddess.projectprocing.enums.GuideAddrStatus;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.OutsourProProgressManageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 外包,半外包项目结算进度管理业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:03 ]
 * @Description: [ 外包,半外包项目结算进度管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class OutsourProProgressManageSerImpl extends ServiceImpl<OutsourProProgressManage, OutsourProProgressManageDTO> implements OutsourProProgressManageSer {

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private MessageAPI messageAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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

    /**
     * 资金模块意见(模块)
     *
     * @throws SerException
     */
    private void checkMoneyPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busModulPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是资金模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }
    /**
     * 财务模块(模块)
     *
     * @throws SerException
     */
    private void checkFinancePermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busModulPermission("5");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 项目经理意见(岗位)
     *
     * @throws SerException
     */
    private void checkPositionPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是该职位人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }
    /**
     * 结算进度负责人填写(岗位)
     *
     * @throws SerException
     */
    private void checkResponsiblePermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是该职位人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

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

    /**
     * 资金模块意见(模块)
     */
    private Boolean guideModuleIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busModulPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }
    /**
     * 财务模块(模块)
     */
    private Boolean guideFinanceIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busModulPermission("5");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 项目经理(岗位)
     */
    private Boolean guidePositionIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }
    /**
     * 结算进度负责人(岗位)
     */
    private Boolean guideResponsibleIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagModule = guideModuleIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPosition = guidePositionIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagResponsible = guideResponsibleIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagFinance = guideFinanceIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagModule || flagPosition || flagResponsible || flagFinance) {
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
            case PROJECTMANAGEOPINION:
                flag = guidePositionIdentity();
                break;
            case RECEICONFIRMA:
                flag = guideModuleIdentity();
                break;
            case NOTICEINVOICE:
                flag = guideResponsibleIdentity();
                break;
            case PAYMONEY:
                flag = guideFinanceIdentity();
                break;
            case SCHEDULECONFIRM:
                flag = guideResponsibleIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countOuts(OutsourProProgressManageDTO outsourProProgressManageDTO) throws SerException {
        Long count = super.count(outsourProProgressManageDTO);
        return count;
    }

    @Override
    public OutsourProProgressManageBO getOneById(String id) throws SerException {
        OutsourProProgressManage outsourProProgressManage = super.findById(id);
        return BeanTransform.copyProperties(outsourProProgressManage, OutsourProProgressManageBO.class);
    }

    @Override
    public List<OutsourProProgressManageBO> listOuts(OutsourProProgressManageDTO outsourProProgressManageDTO) throws SerException {
        checkPermission();
        List<OutsourProProgressManage> outsourProProgressManageList = super.findByCis(outsourProProgressManageDTO, true);
        return BeanTransform.copyProperties(outsourProProgressManageList, OutsourProProgressManageBO.class);
    }

    @Override
    public OutsourProProgressManageBO addOuts(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        checkPermission();
        OutsourProProgressManage outsourProProgressManage = BeanTransform.copyProperties(outsourProProgressManageTO, OutsourProProgressManage.class, true);
        outsourProProgressManage.setCreateTime(LocalDateTime.now());
        super.save(outsourProProgressManage);
        return BeanTransform.copyProperties(outsourProProgressManage, OutsourProProgressManageBO.class);
    }

    @Override
    public OutsourProProgressManageBO editOuts(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
      checkPermission();
        OutsourProProgressManage outsourProProgressManage = super.findById(outsourProProgressManageTO.getId());
        LocalDateTime dateTime = outsourProProgressManage.getCreateTime();
        outsourProProgressManage = BeanTransform.copyProperties(outsourProProgressManageTO, OutsourProProgressManage.class, true);
        outsourProProgressManage.setCreateTime(dateTime);
        outsourProProgressManage.setModifyTime(LocalDateTime.now());
        super.update(outsourProProgressManage);
        return BeanTransform.copyProperties(outsourProProgressManage, OutsourProProgressManageBO.class);
    }

    @Override
    public void deleteOuts(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    @Override
    public void manageOpinion(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
       checkPositionPermission();
        OutsourProProgressManage outsourProProgressManage = super.findById(outsourProProgressManageTO.getId());
        outsourProProgressManage.setModifyTime(LocalDateTime.now());
        outsourProProgressManage.setProjectManOpinion(outsourProProgressManageTO.getProjectManOpinion());
        outsourProProgressManage.setCompletion(outsourProProgressManageTO.getCompletion());
        outsourProProgressManage.setProvideQualified(outsourProProgressManageTO.getProvideQualified());
        outsourProProgressManage.setAcceptance(outsourProProgressManageTO.getAcceptance());
        outsourProProgressManage.setAcceptancetor(outsourProProgressManageTO.getAcceptancetor());
        outsourProProgressManage.setAcceptanceThrough(outsourProProgressManageTO.getAcceptanceThrough());
        super.update(outsourProProgressManage);
    }

    @Override
    public void remindingEmail() throws SerException {
        List<OutsourProProgressManage> outsourProProgressManageList = super.findAll();
        if (outsourProProgressManageList != null && outsourProProgressManageList.size() > 0) {
            for (OutsourProProgressManage outsourProProgressManage : outsourProProgressManageList) {
                if (outsourProProgressManage.getEstimatedComDate().plusDays(2).equals(LocalDate.now())) {
                    String name = positionDetailUserAPI.findManageByDepart(outsourProProgressManage.getProjectTeam());//查询所属项目组对应的项目经理
                    if (StringUtils.isNotBlank(name)) {
                        StringBuffer content = new StringBuffer();
                        content.append(name + ":项目经理请在: " + outsourProProgressManage.getEstimatedComDate() + "确定" + outsourProProgressManage.getInternalName() + "的完成情况");
                        MessageTO messageTO = new MessageTO();
                        messageTO.setContent(content.toString());
                        messageTO.setTitle("确认完成情况");
                        messageTO.setMsgType(MsgType.SYS);
                        messageTO.setSendType(SendType.EMAIL);
                        messageTO.setRangeType(RangeType.SPECIFIED);

                        messageTO.setReceivers(new String[]{name});
                        messageAPI.send(messageTO);
                    }
                }

            }
        }
    }

    @Override
    public void receivaConfir(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        checkMoneyPermission();
        OutsourProProgressManage outsourProProgressManage = super.findById(outsourProProgressManageTO.getId());
        if (StringUtils.isNotBlank(outsourProProgressManage.getProjectManOpinion())) {
            outsourProProgressManage.setModifyTime(LocalDateTime.now());
            outsourProProgressManage.setAccount(outsourProProgressManageTO.getAccount());
            super.update(outsourProProgressManage);
        } else {
            throw new SerException("请先进行项目经理意见填写");
        }
    }

    @Override
    public void noticeInvoice(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        checkResponsiblePermission();
        OutsourProProgressManage outsourProProgressManage = super.findById(outsourProProgressManageTO.getId());
        if (StringUtils.isNotBlank(outsourProProgressManage.getProjectManOpinion())) {
            outsourProProgressManage.setModifyTime(LocalDateTime.now());
            outsourProProgressManage.setSettlementProcess(outsourProProgressManageTO.getSettlementProcess());
            outsourProProgressManage.setReceivedInvoice(outsourProProgressManageTO.getReceivedInvoice());
            outsourProProgressManage.setAccoutModuleDate(DateUtil.parseDate(outsourProProgressManageTO.getAccoutModuleDate()));
            super.update(outsourProProgressManage);
        } else {
            throw new SerException("请先进行项目经理意见填写");
        }
    }

    @Override
    public void payMoney(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        checkFinancePermission();
        OutsourProProgressManage outsourProProgressManage = super.findById(outsourProProgressManageTO.getId());
        if (StringUtils.isBlank(outsourProProgressManage.getSettlementProcess())) {
            throw new SerException("请先进行回款确认");
        }
        if (outsourProProgressManage.getAccount() == null) {
            throw new SerException("请先进行增值税发票通报");
        } else {
            outsourProProgressManage.setModifyTime(LocalDateTime.now());
            outsourProProgressManage.setPayDone(outsourProProgressManageTO.getPayDone());
            outsourProProgressManage.setClearDate(DateUtil.parseDate(outsourProProgressManageTO.getClearDate()));
            super.update(outsourProProgressManage);
        }
    }

    @Override
    public void scheduleConfirm(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        checkResponsiblePermission();
        OutsourProProgressManage outsourProProgressManage = super.findById(outsourProProgressManageTO.getId());
        if (outsourProProgressManage.getPayDone() != null) {
            outsourProProgressManage.setModifyTime(LocalDateTime.now());
            outsourProProgressManage.setClosedLoop(outsourProProgressManageTO.getClosedLoop());
            super.update(outsourProProgressManage);
        } else {
            throw new SerException("请先进行付款操作");
        }
    }
}