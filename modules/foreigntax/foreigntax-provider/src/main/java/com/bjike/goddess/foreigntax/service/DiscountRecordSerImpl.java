package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.DiscountRecordBO;
import com.bjike.goddess.foreigntax.dto.DiscountRecordDTO;
import com.bjike.goddess.foreigntax.entity.DiscountRecord;
import com.bjike.goddess.foreigntax.enums.GuideAddrStatus;
import com.bjike.goddess.foreigntax.to.DiscountRecordTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 优惠备案业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:21 ]
 * @Description: [ 优惠备案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class DiscountRecordSerImpl extends ServiceImpl<DiscountRecord, DiscountRecordDTO> implements DiscountRecordSer {
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
    public Long count(DiscountRecordDTO dto) throws SerException {
        view(dto);
        Long count = super.count(dto);
        return count;
    }

    @Override
    public DiscountRecordBO getOne(String id) throws SerException {
        DiscountRecord discountRecord = super.findById(id);
        DiscountRecordBO bo = BeanTransform.copyProperties(discountRecord, DiscountRecordBO.class);
        return bo;
    }

    @Override
    public List<DiscountRecordBO> list(DiscountRecordDTO dto) throws SerException {
        checkSeeIdentity();
        view(dto);
        List<DiscountRecord> discountRecords = super.findByCis(dto);
        List<DiscountRecordBO> discountRecordBOS = BeanTransform.copyProperties(discountRecords, DiscountRecordBO.class);
        return discountRecordBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DiscountRecordBO insert(DiscountRecordTO to) throws SerException {
        checkAddIdentity();
        DiscountRecord discountRecord = BeanTransform.copyProperties(to, DiscountRecord.class, true);
        discountRecord.setCreateTime(LocalDateTime.now());
        super.save(discountRecord);
        DiscountRecordBO bo = BeanTransform.copyProperties(discountRecord, DiscountRecordBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DiscountRecordBO edit(DiscountRecordTO to) throws SerException {
        checkAddIdentity();
        DiscountRecord discountRecord = super.findById(to.getId());
        LocalDateTime createTime = discountRecord.getCreateTime();
        discountRecord = BeanTransform.copyProperties(to, DiscountRecord.class, true);
        discountRecord.setCreateTime(createTime);
        discountRecord.setModifyTime(LocalDateTime.now());
        DiscountRecordBO bo = BeanTransform.copyProperties(discountRecord, DiscountRecordBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    private void view(DiscountRecordDTO dto) throws SerException {
        //减免审批事项
        if (StringUtils.isNotBlank(dto.getApprovalItems())) {
            dto.getConditions().add(Restrict.like("approvalItems", dto.getApprovalItems()));
        }
        //减免税类型
        if (StringUtils.isNotBlank(dto.getTaxDeductibleType())) {
            dto.getConditions().add(Restrict.like("taxDeductibleType", dto.getTaxDeductibleType()));
        }
        //申报日期
        if (StringUtils.isNotBlank(dto.getDeclarationDate())) {
            dto.getConditions().add(Restrict.eq("declarationDate", dto.getDeclarationDate()));
        }
        //减免税种
        if (StringUtils.isNotBlank(dto.getTaxRelief())) {
            dto.getConditions().add(Restrict.like("taxRelief", dto.getTaxRelief()));
        }
        //免征/减征期限起
        if (StringUtils.isNotBlank(dto.getReductionStart())) {
            dto.getConditions().add(Restrict.eq("reductionStart", dto.getReductionStart()));
        }
        //免征/减征期限止
        if (StringUtils.isNotBlank(dto.getReductionEnd())) {
            dto.getConditions().add(Restrict.eq("reductionEnd", dto.getReductionEnd()));
        }
    }
}