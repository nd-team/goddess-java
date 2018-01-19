package com.bjike.goddess.projectcalculation.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcalculation.bo.CalculationDecisionsBO;
import com.bjike.goddess.projectcalculation.bo.CalculationDetailBO;
import com.bjike.goddess.projectcalculation.dto.CalculationDecisionsDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationDecisions;
import com.bjike.goddess.projectcalculation.enums.GuideAddrStatus;
import com.bjike.goddess.projectcalculation.to.CalculationDecisionsTO;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测算决策业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-07 02:41 ]
 * @Description: [ 测算决策业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcalculationSerCache")
@Service
public class CalculationDecisionsSerImpl extends ServiceImpl<CalculationDecisions, CalculationDecisionsDTO> implements CalculationDecisionsSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
    public List<CalculationDecisionsBO> getList() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), CalculationDecisionsBO.class);
    }

    @Override
    public void save(CalculationDecisionsTO calculationDecisionsTO) throws SerException {
        CalculationDecisions calculationDecisions = BeanTransform.copyProperties(calculationDecisionsTO, CalculationDecisions.class, true);
        super.save(calculationDecisions);
    }

    @Override
    public List<CalculationDecisionsBO> getSearchList(String marketInfoNum) throws SerException {
        CalculationDecisionsDTO dto = new CalculationDecisionsDTO();
        dto.getConditions().add(Restrict.like("marketInfoNum", marketInfoNum));
        return BeanTransform.copyProperties(super.findByCis(dto), CalculationDecisionsBO.class);
    }

    @Override
    public CalculationDecisionsBO getProjectManage(CalculationDecisionsTO to) throws SerException {
        return BeanTransform.copyProperties(super.findById(to.getId()), CalculationDecisionsBO.class);
    }

    @Override
    public void upDate(CalculationDecisionsTO to) throws SerException {
        CalculationDecisions calculationDecisions = BeanTransform.copyProperties(to, CalculationDecisions.class, true);
        super.update(calculationDecisions);
    }

}