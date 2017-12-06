package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.SettlementProcessBO;
import com.bjike.goddess.projectprocing.dto.SettlementProcessDTO;
import com.bjike.goddess.projectprocing.entity.SettlementProcess;
import com.bjike.goddess.projectprocing.enums.GuideAddrStatus;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.SettlementProcessTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 结算流程存储记录业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:16 ]
 * @Description: [ 结算流程存储记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class SettlementProcessSerImpl extends ServiceImpl<SettlementProcess, SettlementProcessDTO> implements SettlementProcessSer {

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
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case SEEFILE:
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
    public Long countSetProcess(SettlementProcessDTO settlementProcessDTO) throws SerException {
        searchCondi(settlementProcessDTO);
        Long count = super.count(settlementProcessDTO);
        return count;
    }

    @Override
    public SettlementProcessBO getOneById(String id) throws SerException {
        SettlementProcess settlementProcess = super.findById(id);
        return BeanTransform.copyProperties(settlementProcess, SettlementProcessBO.class);
    }

    @Override
    public List<SettlementProcessBO> listSetProcess(SettlementProcessDTO settlementProcessDTO) throws SerException {
       checkPermission();
        searchCondi(settlementProcessDTO);
        List<SettlementProcess> settlementProcessList = super.findByCis(settlementProcessDTO, true);
        return BeanTransform.copyProperties(settlementProcessList, SettlementProcessBO.class);
    }

    public void searchCondi(SettlementProcessDTO settlementProcessDTO) throws SerException {
        if (StringUtils.isNotBlank(settlementProcessDTO.getOutUnit())) {
            settlementProcessDTO.getConditions().add(Restrict.eq("outUnit", settlementProcessDTO.getOutUnit()));
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettlementProcessBO addSetProcess(SettlementProcessTO settlementProcessTO) throws SerException {
        checkPermission();
        SettlementProcess settlementProcess = BeanTransform.copyProperties(settlementProcessTO, SettlementProcess.class, true);
        settlementProcess.setUpdateDate(LocalDate.now());
        super.save(settlementProcess);
        return BeanTransform.copyProperties(settlementProcess, SettlementProcessBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettlementProcessBO editSetProcess(SettlementProcessTO settlementProcessTO) throws SerException {
        checkPermission();
        SettlementProcess settlementProcess = super.findById(settlementProcessTO.getId());
        LocalDateTime dateTime = settlementProcess.getCreateTime();
        settlementProcess = BeanTransform.copyProperties(settlementProcessTO, SettlementProcess.class, true);
        settlementProcess.setCreateTime(dateTime);
        settlementProcess.setModifyTime(LocalDateTime.now());
        settlementProcess.setUpdateDate(LocalDate.now());
        super.update(settlementProcess);
        return BeanTransform.copyProperties(settlementProcess, SettlementProcessBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSetProcess(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateSettProceAttach(String id) throws SerException {
        SettlementProcess settlementProcess = super.findById(id);
        if (settlementProcess != null) {
            if (settlementProcess.getSettProceAttach()==null || !settlementProcess.getSettProceAttach()) {
                settlementProcess.setSettProceAttach(true);
                settlementProcess.setModifyTime(LocalDateTime.now());
                super.update(settlementProcess);
            }
        }
    }

    @Override
    public List<String> findOutUnit() throws SerException {
        List<SettlementProcess> settlementProcessList = super.findAll();
        if (CollectionUtils.isEmpty(settlementProcessList)) {
            return Collections.emptyList();
        }
        return settlementProcessList.stream().distinct().map(SettlementProcess::getOutUnit).collect(Collectors.toList());
    }
}