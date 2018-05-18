package com.bjike.goddess.courier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.courier.bo.CourierCompanyBO;
import com.bjike.goddess.courier.dto.CourierCompanyDTO;
import com.bjike.goddess.courier.entity.CourierCompany;
import com.bjike.goddess.courier.enums.GuideAddrStatus;
import com.bjike.goddess.courier.to.CourierCompanyTO;
import com.bjike.goddess.courier.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 快递公司信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:29 ]
 * @Description: [ 快递公司信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "courierSerCache")
@Service
public class CourierCompanySerImpl extends ServiceImpl<CourierCompany, CourierCompanyDTO> implements CourierCompanySer {
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

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CourierCompanyBO save(CourierCompanyTO to) throws SerException {
        checkAddIdentity();
        CourierCompany courierCompany = BeanTransform.copyProperties(to, CourierCompany.class, true);
        super.save(courierCompany);
        return BeanTransform.copyProperties(courierCompany, CourierCompanyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CourierCompanyBO edit(CourierCompanyTO to) throws SerException {
        checkAddIdentity();
        CourierCompany courierCompany = super.findById(to.getId());
        if (courierCompany == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = courierCompany.getCreateTime();
        courierCompany = BeanTransform.copyProperties(to, CourierCompany.class, true);
        courierCompany.setCreateTime(a);
        courierCompany.setModifyTime(LocalDateTime.now());
        super.update(courierCompany);
        return BeanTransform.copyProperties(courierCompany, CourierCompanyBO.class);
    }

    @Override
    public List<CourierCompanyBO> list(CourierCompanyDTO dto) throws SerException {
        checkSeeIdentity();
        List<CourierCompany> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, CourierCompanyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public CourierCompanyBO findByID(String id) throws SerException {
        CourierCompany courierCompany = super.findById(id);
        if (courierCompany == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(courierCompany, CourierCompanyBO.class);
    }

    @Override
    public Set<String> allCompany() throws SerException {
        List<CourierCompany> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (CourierCompany c : list) {
            set.add(c.getCourierCompany());
        }
        return set;
    }

    @Override
    public Long count(CourierCompanyDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public String findTel(String courierCompany) throws SerException {
        CourierCompanyDTO dto = new CourierCompanyDTO();
        dto.getConditions().add(Restrict.eq("courierCompany", courierCompany));
        List<CourierCompany> list = super.findByCis(dto);
        if (list != null && !list.isEmpty()) {
            return list.get(0).getCourierTel();
        }
        return "";
    }
}