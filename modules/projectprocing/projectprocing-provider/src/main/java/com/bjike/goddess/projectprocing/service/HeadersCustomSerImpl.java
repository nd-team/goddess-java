package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.HeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.HeadersCustomDTO;
import com.bjike.goddess.projectprocing.entity.HeadersCustom;
import com.bjike.goddess.projectprocing.enums.GuideAddrStatus;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.HeadersCustomTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 表头定制业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 表头定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class HeadersCustomSerImpl extends ServiceImpl<HeadersCustom, HeadersCustomDTO> implements HeadersCustomSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public Long countHeaders(HeadersCustomDTO headersCustomDTO) throws SerException {
        headersCustomDTO.getConditions().add(Restrict.isNull("prossManageId"));
        Long count = super.count(headersCustomDTO);
        return count;
    }

    @Override
    public HeadersCustomBO getOneById(String id) throws SerException {
        HeadersCustom headersCustom = super.findById(id);
        return BeanTransform.copyProperties(headersCustom, HeadersCustomBO.class);
    }

    @Override
    public List<HeadersCustomBO> listHeaders(HeadersCustomDTO headersCustomDTO) throws SerException {
        checkPermission();
        headersCustomDTO.getConditions().add(Restrict.isNull("prossManageId"));
        List<HeadersCustom> headersCustoms = super.findByCis(headersCustomDTO, true);
        return BeanTransform.copyProperties(headersCustoms, HeadersCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HeadersCustomBO addHeaders(HeadersCustomTO headersCustomTO) throws SerException {
        checkPermission();
        HeadersCustom headersCustom = BeanTransform.copyProperties(headersCustomTO, HeadersCustom.class, true);
        headersCustom.setCreateTime(LocalDateTime.now());
        super.save(headersCustom);
        return BeanTransform.copyProperties(headersCustom, HeadersCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HeadersCustomBO editHeaders(HeadersCustomTO headersCustomTO) throws SerException {
        checkPermission();
        HeadersCustom headersCustom = super.findById(headersCustomTO.getId());
        LocalDateTime dateTime = headersCustom.getCreateTime();
        headersCustom = BeanTransform.copyProperties(headersCustomTO, HeadersCustom.class, true);
        headersCustom.setCreateTime(dateTime);
        headersCustom.setModifyTime(LocalDateTime.now());
        super.update(headersCustom);
        return BeanTransform.copyProperties(headersCustom, HeadersCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteHeaders(String id) throws SerException {
        HeadersCustomDTO headersCustomDTO = new HeadersCustomDTO();
        headersCustomDTO.getConditions().add(Restrict.eq("fatherId", id));
        List<HeadersCustom> headersCustomList = super.findByCis(headersCustomDTO);
        super.remove(headersCustomList);
        super.remove(id);
    }

    @Override
    public List<HeadersCustomBO> getHeaderByOutUnit(String outUnit) throws SerException {
        HeadersCustomDTO headersCustomDTO = new HeadersCustomDTO();
        headersCustomDTO.getConditions().add(Restrict.eq("outUnit", outUnit));
        headersCustomDTO.getConditions().add(Restrict.isNull("prossManageId"));
        List<HeadersCustom> headersCustomList = super.findByCis(headersCustomDTO);
        return BeanTransform.copyProperties(headersCustomList, HeadersCustomBO.class);
    }

    @Override
    public List<HeadersCustomBO> getByManageId(String prossManageId) throws SerException {
        HeadersCustomDTO headersCustomDTO = new HeadersCustomDTO();
        headersCustomDTO.getConditions().add(Restrict.eq("prossManageId", prossManageId));
        List<HeadersCustom> headersCustoms = super.findByCis(headersCustomDTO);
        if (headersCustoms != null && headersCustoms.size() > 0) {
            for (HeadersCustom headersCustom : headersCustoms) {
                HeadersCustom headersCustom1 = super.findById(headersCustom.getFatherId());
                headersCustom.setHeader(headersCustom1.getHeader());
                headersCustom.setOutUnit(headersCustom1.getOutUnit());
                headersCustom.setRemark(headersCustom1.getRemark());
                headersCustom.setRequiredFill(headersCustom1.getRequiredFill());
                headersCustom.setTypes(headersCustom1.getTypes());
            }
        }
        return BeanTransform.copyProperties(headersCustoms, HeadersCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeByManageId(String prossManageId) throws SerException {
        checkPermission();
        HeadersCustomDTO headersCustomDTO = new HeadersCustomDTO();
        headersCustomDTO.getConditions().add(Restrict.eq("prossManageId", prossManageId));
        List<HeadersCustom> headersCustoms = super.findByCis(headersCustomDTO);
//        if (headersCustoms != null && headersCustoms.size() > 0) {

        super.remove(headersCustoms);
//        }
    }
}