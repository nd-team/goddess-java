package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.WaitPayBO;
import com.bjike.goddess.staffpay.dto.WaitPayDTO;
import com.bjike.goddess.staffpay.entity.FirstPayRecord;
import com.bjike.goddess.staffpay.entity.WaitPay;
import com.bjike.goddess.staffpay.enums.ConfirmStatus;
import com.bjike.goddess.staffpay.enums.GuideAddrStatus;
import com.bjike.goddess.staffpay.excel.SonPermissionObject;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;
import com.bjike.goddess.staffpay.to.WaitPayTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 等待付款业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 11:38 ]
 * @Description: [ 等待付款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffpaySerCache")
@Service
public class WaitPaySerImpl extends ServiceImpl<WaitPay, WaitPayDTO> implements WaitPaySer {

    @Autowired
    private FirstPayRecordSer firstPayRecordSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private PayRecordSer payRecordSer;
    @Autowired
    private MoneyReadySer moneyReadySer;

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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("waitpay");
        obj.setDescribesion("等待付款");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnswer = firstPayRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("firstpayrecord");
        obj.setDescribesion("第一次付款记录");
        if (flagSeeAnswer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeWeb = payRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("payrecord");
        obj.setDescribesion("已付款记录");
        if (flagSeeWeb) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeOpen = moneyReadySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("moneyready");
        obj.setDescribesion("资金准备审核表");
        if (flagSeeOpen) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
    public Long countWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        Long count = super.count(waitPayDTO);
        return count;
    }

    @Override
    public WaitPayBO getOne(String id) throws SerException {
        WaitPay waitPay = super.findById(id);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Override
    public List<WaitPayBO> findListWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        checkSeeIdentity();
        waitPayDTO.getSorts().add("createTime=desc");
        List<WaitPay> waitPays = super.findByPage(waitPayDTO);
        List<WaitPayBO> waitPayBOS = BeanTransform.copyProperties(waitPays, WaitPayBO.class);
        return waitPayBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WaitPayBO insertWaitPay(WaitPayTO waitPayTO) throws SerException {
        checkAddIdentity();
        WaitPay waitPay = BeanTransform.copyProperties(waitPayTO, WaitPay.class, true);
        if (ConfirmStatus.YES.equals(waitPay.getConfirmFirstSalary())) {
            throw new SerException("添加失败，未做付款操作都是否");
        }
        waitPay.setCreateTime(LocalDateTime.now());
        super.save(waitPay);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WaitPayBO editWaitPay(WaitPayTO waitPayTO) throws SerException {
        checkAddIdentity();
        WaitPay waitPay = super.findById(waitPayTO.getId());
        BeanTransform.copyProperties(waitPayTO, waitPay, true);
        waitPay.setModifyTime(LocalDateTime.now());
        super.update(waitPay);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeWaitPay(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public FirstPayRecordBO payment(WaitPayTO waitPayTO) throws SerException {
        WaitPay waitPay = super.findById(waitPayTO.getId());
        BeanTransform.copyProperties(waitPayTO, waitPay, true);
        if (ConfirmStatus.NO.equals(waitPay.getConfirmFirstSalary())) {
            waitPay.setConfirmFirstSalary(ConfirmStatus.YES);
            super.update(waitPay);
        }

        FirstPayRecord firstPayRecord = new FirstPayRecord();
        BeanUtils.copyProperties(waitPay, firstPayRecord);
        firstPayRecordSer.save(firstPayRecord);
        return BeanTransform.copyProperties(firstPayRecord, FirstPayRecordBO.class);
    }
}