package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.dto.FirstPayRecordDTO;
import com.bjike.goddess.staffpay.entity.FirstPayRecord;
import com.bjike.goddess.staffpay.entity.PayRecord;
import com.bjike.goddess.staffpay.enums.ConfirmStatus;
import com.bjike.goddess.staffpay.enums.GuideAddrStatus;
import com.bjike.goddess.staffpay.to.FirstPayRecordTO;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 第一次已付款记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:01 ]
 * @Description: [ 第一次已付款记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffpaySerCache")
@Service
public class FirstPayRecordSerImpl extends ServiceImpl<FirstPayRecord, FirstPayRecordDTO> implements FirstPayRecordSer {

    @Autowired
    private PayRecordSer payRecordSer;
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
    public Long countFirstPayRecord(FirstPayRecordDTO firstPayRecordDTO) throws SerException {
        Long count = super.count(firstPayRecordDTO);
        return count;
    }

    @Override
    public FirstPayRecordBO getOne(String id) throws SerException {
        FirstPayRecord firstPayRecord = super.findById(id);
        return BeanTransform.copyProperties(firstPayRecord,FirstPayRecordBO.class);
    }

    @Override
    public List<FirstPayRecordBO> findListFirstPayRecord(FirstPayRecordDTO firstPayRecordDTO) throws SerException {
        checkSeeIdentity();
        firstPayRecordDTO.getSorts().add("createTime=desc");
        List<FirstPayRecord> firstPayRecords = super.findByPage(firstPayRecordDTO);
        List<FirstPayRecordBO> firstPayRecordBOS = BeanTransform.copyProperties(firstPayRecords,FirstPayRecordBO.class);
        return firstPayRecordBOS;
    }
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeFirstPayRecord(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
    @Override
    public PayRecordBO payment(FirstPayRecordTO firstPayRecordTO) throws SerException {
        FirstPayRecord firstPayRecord = super.findById(firstPayRecordTO.getId());
        BeanTransform.copyProperties(firstPayRecordTO,firstPayRecord,true);
        if (ConfirmStatus.NO.equals(firstPayRecord.getConfirmFirstSalary())) {
            firstPayRecord.setConfirmFirstSalary(ConfirmStatus.YES);
            super.update(firstPayRecord);
        }

        PayRecord payRecord = new PayRecord();
        BeanUtils.copyProperties(firstPayRecord,payRecord);
        payRecordSer.save(payRecord);
        return BeanTransform.copyProperties(payRecord, PayRecordBO.class);
    }
}