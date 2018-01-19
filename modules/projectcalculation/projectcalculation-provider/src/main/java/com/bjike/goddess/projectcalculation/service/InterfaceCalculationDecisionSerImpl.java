package com.bjike.goddess.projectcalculation.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcalculation.bo.InterfaceCalculationDecisionBO;
import com.bjike.goddess.projectcalculation.dto.InterfaceCalculationDecisionDTO;
import com.bjike.goddess.projectcalculation.entity.InterfaceCalculationDecision;
import com.bjike.goddess.projectcalculation.enums.GuideAddrStatus;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.projectcalculation.to.InterfaceCalculationDecisionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 界面测算决策业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-09 04:23 ]
 * @Description: [ 界面测算决策业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcalculationSerCache")
@Service
public class InterfaceCalculationDecisionSerImpl extends ServiceImpl<InterfaceCalculationDecision, InterfaceCalculationDecisionDTO> implements InterfaceCalculationDecisionSer {

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
    public List<InterfaceCalculationDecisionBO> getList() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), InterfaceCalculationDecisionBO.class);
    }

    @Override
    public List<InterfaceCalculationDecisionBO> searchByArea(String area) throws SerException {
        InterfaceCalculationDecisionDTO dto = new InterfaceCalculationDecisionDTO();
        dto.getConditions().add(Restrict.like("area", area));
        return BeanTransform.copyProperties(super.findByCis(dto), InterfaceCalculationDecisionBO.class);
    }

    @Override
    public List<InterfaceCalculationDecisionBO> searchByProjectNum(String projectnum) throws SerException {
        InterfaceCalculationDecisionDTO dto = new InterfaceCalculationDecisionDTO();
        dto.getConditions().add(Restrict.like("internalProjectNum", projectnum));
        return BeanTransform.copyProperties(super.findByCis(dto), InterfaceCalculationDecisionBO.class);
    }

    @Override
    public void save(InterfaceCalculationDecisionTO to) throws SerException {
        InterfaceCalculationDecision interfaceCalculationDecision = BeanTransform.copyProperties(to, InterfaceCalculationDecision.class, true);
        super.save(interfaceCalculationDecision);
    }

    @Override
    public InterfaceCalculationDecisionBO editor(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), InterfaceCalculationDecisionBO.class);
    }

    @Override
    public void upDate(InterfaceCalculationDecisionTO to) throws SerException {
        InterfaceCalculationDecision interfaceCalculationDecision = BeanTransform.copyProperties(to, InterfaceCalculationDecision.class, true);
        super.update(interfaceCalculationDecision);
    }

    @Override
    public void submit(InterfaceCalculationDecisionTO to) {

    }

}