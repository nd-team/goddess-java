package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.ProfitRegulationAdviceBO;
import com.bjike.goddess.reportmanagement.dto.ProfitRegulationAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitRegulationAdvice;
import com.bjike.goddess.reportmanagement.enums.GuideAddrStatus;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProfitRegulationAdviceTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 利润增减率分析管理建议设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 06:53 ]
 * @Description: [ 利润增减率分析管理建议设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class ProfitRegulationAdviceSerImpl extends ServiceImpl<ProfitRegulationAdvice, ProfitRegulationAdviceDTO> implements ProfitRegulationAdviceSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
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
    @Transactional(rollbackFor = {SerException.class})
    public ProfitRegulationAdviceBO save(ProfitRegulationAdviceTO to) throws SerException {
        checkAddIdentity();
        ProfitRegulationAdvice entity = BeanTransform.copyProperties(to, ProfitRegulationAdvice.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ProfitRegulationAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProfitRegulationAdviceTO to) throws SerException {
        checkAddIdentity();
        ProfitRegulationAdvice entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, ProfitRegulationAdvice.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<ProfitRegulationAdviceBO> list(ProfitRegulationAdviceDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<ProfitRegulationAdvice> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, ProfitRegulationAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        ProfitRegulationAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public ProfitRegulationAdviceBO findByID(String id) throws SerException {
        ProfitRegulationAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, ProfitRegulationAdviceBO.class);
    }

    @Override
    public Long count(ProfitRegulationAdviceDTO dto) throws SerException {
        return super.count(dto);
    }
}